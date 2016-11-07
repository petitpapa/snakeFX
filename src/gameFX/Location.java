package gameFX;

import java.util.Objects;

public class Location {
	private final int x;
	private final int y;
	
	Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		 final Location other = (Location) obj;
		 return Objects.equals(getX(), other.getX()) &&
				 Objects.equals(getY(), other.getY());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
	}
}
