package one;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("input.txt"));
		ArrayList<Integer> visited = new ArrayList<Integer>();
		int freq = 0;
		boolean found = false;
		
		visited.add(freq);
		while(!found){
			freq = freq + input.nextInt();
			if(visited.contains(freq)) found = true;
			visited.add(freq);
			if(!input.hasNextInt()) input = new Scanner(new File("input.txt"));
		}
		System.out.print(freq);
		
	}

}
