package control;

import java.util.HashSet;
import java.util.Stack;

import sockets.APITool;

public class Driver {
	
	public static void main(String args[]) {
		
		Database db = new Database();
		
		/*
		 * Use a LibraryCrawler to propagate our DB
		 */
		
		LibraryCrawler libCrawler = new LibraryCrawler(db);
		libCrawler.setDirectory("/media/anna/307AA1F87AA1BB4C/Music/Korn");
		libCrawler.crawl();
		
		APITool API = new APITool();
		
		HashSet<String> test = API.getArtistDiscography("korn");
		for (String s : test){
			System.out.println(s);
		}
		
		
	}

}
