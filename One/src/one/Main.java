package one;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("input.txt"));
		int freq = 0;
		
		while(scan.hasNextInt()){
			freq = freq + scan.nextInt();
		}
		System.out.print(freq);
	}

}
