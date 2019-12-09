package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import day3.Coordinate;

public class part1 {

	public static void main(String[] args) {
		try {
			System.out.println(run());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int run() throws Exception {		
		BufferedReader br = new BufferedReader(new FileReader("inputs/day3/day3.txt"));
		String[] firstWire_s = br.readLine().split(",");
		String[] secondWire_s = br.readLine().split(",");
		br.close();
		
		System.out.println("First wire:");
		for (int i = 0; i < firstWire_s.length; ++i)
			System.out.print(firstWire_s[i] + ", ");
		
		Instruction[] firstWire = new Instruction[firstWire_s.length];
		for (int i = 0; i < firstWire_s.length; ++i) {
			Instruction instr = new Instruction(stod(firstWire_s[i].substring(0, 1)), Integer.parseInt(firstWire_s[i].substring(1)));
			firstWire[i] = instr;
		}
		
		System.out.println();
		for (int i = 0; i < firstWire.length; ++i)
			System.out.print(firstWire[i].toString() + ", ");
		System.out.println(firstWire_s[0].substring(0,1));
		
		Instruction[] secondWire = new Instruction[secondWire_s.length];
		for (int i = 0; i < secondWire_s.length; ++i) {
			Instruction instr = new Instruction(stod(secondWire_s[i].substring(0, 1)), Integer.parseInt(secondWire_s[i].substring(1)));
			secondWire[i] = instr;
		}
		
		HashMap<Integer, ArrayList<Integer>> coords = new HashMap<Integer, ArrayList<Integer>>(1000);
		
		Coordinate pos = new Coordinate(0,0);
		for (Instruction instr : firstWire) {
			if (instr.dir == Direction.UP) {
				for (int y = 0; y < instr.dist; ++y) {
					++pos.y;
					if (!coords.get(pos.x).contains(pos.y))
						coords.get(pos.x).add(pos.y);
				}
				
			} else if (instr.dir == Direction.RIGHT) {
				for (int x = 0; x < instr.dist; ++x) {
					++pos.x;
					if (coords.containsKey(pos.x)) {
						if (!coords.get(pos.x).contains(pos.y))
							coords.get(pos.x).add(pos.y);
					} else {
						coords.put(pos.x, new ArrayList<Integer>());
						coords.get(pos.x).add(pos.y);
					}
				}
				
			} else if (instr.dir == Direction.DOWN) {
				for (int y = 0; y < instr.dist; ++y) {
					--pos.y;
					if (!coords.get(pos.x).contains(pos.y))
						coords.get(pos.x).add(pos.y);
				}
				
			} else if (instr.dir == Direction.LEFT) {
				for (int x = 0; x < instr.dist; ++x) {
					--pos.x;
					if (coords.containsKey(pos.x)) {
						if (!coords.get(pos.x).contains(pos.y))
							coords.get(pos.x).add(pos.y);
					} else {
						coords.put(pos.x, new ArrayList<Integer>());
						coords.get(pos.x).add(pos.y);
					}
				}
				
			}
		}
		
		ArrayList<Coordinate> intersections = new ArrayList<Coordinate>();
		pos.x = 0;
		pos.y = 0;
		for (Instruction instr : secondWire) {
			if (instr.dir == Direction.UP) {
				for (int y = 0; y < instr.dist; ++y) {
					++pos.y;
					if (coords.containsKey(pos.x) && coords.get(pos.x).contains(pos.y)) {
						intersections.add(new Coordinate(pos.x, pos.y));
					} 
				}
				
			} else if (instr.dir == Direction.RIGHT) {
				for (int x = 0; x < instr.dist; ++x) {
					++pos.x;
					if (coords.containsKey(pos.x) && coords.get(pos.x).contains(pos.y)) {
						intersections.add(new Coordinate(pos.x, pos.y));
					}
				}
				
			} else if (instr.dir == Direction.DOWN) {
				for (int y = 0; y < instr.dist; ++y) {
					--pos.y;
					if (coords.containsKey(pos.x) && coords.get(pos.x).contains(pos.y)) {
						intersections.add(new Coordinate(pos.x, pos.y));
					} 
				}
				
			} else if (instr.dir == Direction.LEFT) {
				for (int x = 0; x < instr.dist; ++x) {
					--pos.x;
					if (coords.containsKey(pos.x) && coords.get(pos.x).contains(pos.y)) {
						intersections.add(new Coordinate(pos.x, pos.y));
					}
				}
				
			}
		}
		
		int minDistance = Integer.MAX_VALUE;
		for (Coordinate co : intersections) {
			if (co.distance() < minDistance)
				minDistance = co.distance();
		}
		
		System.out.println(intersections.toString());
		System.out.println(firstWire.toString());
		System.out.println(secondWire.toString());
		
		return minDistance;
	}
	
	public static Direction stod(String str) {
		Direction dir = Direction.DEFAULT;
		if ("U".equals(str))
			dir = Direction.UP;
		else if ("R".equals(str))
			dir = Direction.RIGHT;
		else if ("D".equals(str))
			dir = Direction.DOWN;
		else if ("L".equals(str))
			dir = Direction.LEFT;
		
		return dir;
	}

}
