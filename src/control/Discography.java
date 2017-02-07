package control;

import java.util.ArrayList;

public class Discography {
	
	private String artist = "";
	private ArrayList<String> albums = new ArrayList<String>();
	
	public Discography(String s) {
		artist = s;
	}
	
	public void addAlbum(String album) {
		albums.add(album);
	}
	
	public String toString() {
		String s = artist;
		for (String album : albums) {
			s += "\n - "+ album;
		}
		return s;
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}
