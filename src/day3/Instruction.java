package day3;

import day3.Direction;

public class Instruction {

	public Direction dir;
	public int dist;
	
	public Instruction() {}
	public Instruction(Direction dir, int dist) {
		this.dir = dir;
		this.dist = dist;
	}
	
	public String toString() {
		return ("Dir: " + dir + " Dist: " + dist);
	}
	
}
