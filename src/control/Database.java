package control;

import java.util.Stack;

public class Database {
	
	private Stack<Discography> stack = new Stack<Discography>();
	
	public Database() {
		
	}
	
	public void push(Discography d) {
		stack.push(d);
	}
	public void clear(){
		stack.clear();
	}
	public int size() {
		return stack.size();
	}

}
