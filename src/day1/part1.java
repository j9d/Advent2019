package day1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class part1 {

	public static void main(String[] args) {
		System.out.println(calculate());
	}
	
	public static int calculate() {
		int result = 0;
		
		File f = new File("inputs/day1/day1.txt");
		try {
			Scanner input = new Scanner(f);
			
			while (input.hasNextLine()) {
				result += (Integer.parseInt(input.nextLine()) / 3) - 2;
			}
		} catch (FileNotFoundException e) {
			result = -1;
		}
		
		return result;
	}

}
