package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class part2 {

	final static int ADD_CODE = 1;
	final static int MULTIPLY_CODE = 2;
	final static int EXIT_CODE = 99;
	final static int INTERVAL = 4;
	
	final static int TARGET = 19690720;
	
	public static void main(String[] args) {
		try {
			int[] result = helper();
			System.out.println(result[0] + ", " + result[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int[] helper() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("inputs/day2/day2.txt"));
		String[] s_vals = br.readLine().split(",");
		br.close();
		
		int[] vals = new int[s_vals.length];
		for (int i = 0; i < s_vals.length; ++i)
			vals[i] = Integer.parseInt(s_vals[i]);
		int[] template = new int[vals.length];
		System.arraycopy(vals, 0, template, 0, vals.length);
		
		int noun = -1;
		int verb = -1;
		boolean found = false;
		for (int i = 0; i < 100; ++i) {
			for (int j = 0; j < 100; ++j) {
				System.arraycopy(template, 0, vals, 0, template.length);
				vals[1] = i;
				vals[2] = j;
				if (run(vals) == TARGET) {
					noun = i;
					verb = j;
					found = true;
					break;
				}
			}
			if (found)
				break;
		}
		
		int[] result = {noun, verb};
		return result;
	}
	
	public static int run(int[] vals) throws Exception {
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
		
		return vals[0];
	}

}
