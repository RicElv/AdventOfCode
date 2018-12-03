package two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner data = new Scanner(new File("input.txt"));
		ArrayList<Character> taken;
		String cur;
		int twos = 0; 
		int threes = 0;
		
		while(data.hasNext()){
			cur = data.next();
			taken = new ArrayList<Character>();
			boolean twoH = false;
			boolean threeH = false;
			for(int i = 0; i < cur.length(); i++){
				Character ch = cur.charAt(i);
				if(!taken.contains(ch)){
					taken.add(ch);
					int hit = 1;
					for(int b = i + 1; b < cur.length(); b++){
						if(ch == cur.charAt(b)){
							hit++;
							if(hit > 3) break;
						}
					}
					if(hit == 2 && !twoH){
						twos++;
						twoH = true;
					} else if (hit == 3 && !threeH){
						threes++;
						threeH = true;
					} 
					if (twoH && threeH){
						break;
					}
				}
			}
		}
		int checksum = twos * threes;
		System.out.print(checksum);
	}

}
