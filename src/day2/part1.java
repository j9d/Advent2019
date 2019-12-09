package day2;

import java.io.BufferedReader;
import java.io.FileReader;

public class part1 {

	final static int ADD_CODE = 1;
	final static int MULTIPLY_CODE = 2;
	final static int EXIT_CODE = 99;
	final static int INTERVAL = 4;
	
	public static void main(String[] args) {
		try {
			int[] vals = run();
			System.out.println(vals[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int[] run() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("inputs/day2/day2.txt"));
		String[] s_vals = br.readLine().split(",");
		br.close();
		
		int[] vals = new int[s_vals.length];
		for (int i = 0; i < s_vals.length; ++i)
			vals[i] = Integer.parseInt(s_vals[i]);
		
		int first, second, toModify;
		int curr_pos = 0;
		int opcode = vals[curr_pos];
		while (opcode != EXIT_CODE) {
			first = vals[vals[curr_pos + 1]];
			second = vals[vals[curr_pos + 2]];
			toModify = vals[curr_pos + 3];
			
			if (opcode == ADD_CODE) {
				// Add
				vals[toModify] = first + second;
			} else if (opcode == MULTIPLY_CODE) {
				// Multiply
				vals[toModify] = first * second;
			} else {
				throw new Exception("Opcode error");
			}
			
			curr_pos += INTERVAL;
			opcode = vals[curr_pos];
		}
		
		return vals;
	}

}
