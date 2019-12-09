package day3;

public class Coordinate {

	public int x;
	public int y;
	
	public Coordinate() {}
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int distance() {
		return Math.abs(x) + Math.abs(y);
	}
	
}
