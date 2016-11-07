package gameFX;

public class Snake {
	private Cell head;
	
	public Snake() {
		head.setState(State.HEAD);
	}

	public void setLocation(Location loc) {
		this.head.setLocation(loc);
	}

	public Cell getHead() {
		return head;
	}
	
}
