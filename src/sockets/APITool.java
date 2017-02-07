package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Stack;

/**
 * Use Spotify's free API to search for an artist and find their list of albums
 */

public class APITool {
	
	public APITool () {
		
	}
	
	public HashSet<String> getArtistDiscography(String artist) {
		String uri = "";
		HashSet<String> ret = new HashSet<String>();
		try {
			uri = getArtistURI(artist.replace(" ", "+"));
			return getArtistAlbums(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private HashSet<String> getArtistAlbums(String uri) throws IOException {
		if (uri.equals("")) {
			return null;
		} 
		HashSet<String> ret = new HashSet<String>();
		URLConnection conn = new URL("https://api.spotify.com/v1/artists/"+uri+"/albums?album_type=album").openConnection();
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));		
		String s;
		while ((s = br.readLine()) != null) {
			if (s.contains("\"name\"")) {
				ret.add(stripAlbum(s));
			}
		}
		br.close();
		
		return ret;
	}
	
	public String getArtistURI(String artist) throws IOException {
		URLConnection conn = new URL("https://api.spotify.com/v1/search?q="+artist+"&type=artist").openConnection();
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));		
		String s;
		while ((s = br.readLine()) != null) {
			if (s.contains("\"artist\"")) {
				s = br.readLine();
				return stripURI(s);
			}
		}
		br.close();
		return "";
		
	}
	
	private String stripURI(String s) {
		String[] pieces = s.split(":");
		return(pieces[pieces.length-1].substring(0, pieces[pieces.length-1].length()-1));
	}
	
	private String stripAlbum(String s){
		String[] pieces = s.split(":");
		return pieces[pieces.length - 1].substring(2,pieces[pieces.length - 1].length()-2);
		
	}

}

