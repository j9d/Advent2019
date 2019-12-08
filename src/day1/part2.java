package day1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class part2 {

	public static void main(String[] args) {
		System.out.println(calculate());
	}
	
	public static int calculate() {
		int total = 0;
		
		File f = new File("inputs/day1/day1.txt");
		try {
			Scanner input = new Scanner(f);
			
			while (input.hasNextLine()) {
				int module = (Integer.parseInt(input.nextLine()) / 3) - 2;
				int nextCalc = (module / 3) - 2;
				if (nextCalc > 0)
					module += nextCalc;
				
				while (true) {
					nextCalc = (nextCalc / 3) - 2;
					if (nextCalc <= 0)
						break;
					else 
						module += nextCalc;
				}
				total += module;
			}
		} catch (FileNotFoundException e) {
			total = -1;
		}
		
		return total;
	}

}
