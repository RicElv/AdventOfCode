package two;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner data = new Scanner(new File("input.txt"));
		Scanner dataComp = new Scanner(new File("input.txt"));
		String cur;
		String comp;
		String copy = " ";
		
		while(data.hasNext()){
			//System.out.print("j");
			cur = data.next();
			dataComp = new Scanner(new File("input.txt"));
			dataComp.useLocale(data.locale());
			while(dataComp.hasNext()){
				comp = dataComp.next();
				//System.out.println(comp);
				boolean hit = false;
				for(int i = 0; i < cur.length(); i++){
					if(cur.charAt(i) != comp.charAt(i)){
						//System.out.println("h");
						if(hit){
							//System.out.print("j");
							hit = false;
							break;
						}
						hit = true;
						copy = comp.substring(0, i) + comp.substring(i + 1, comp.length());
						
					}
				}
				if(hit){
					System.out.println(cur);
					System.out.println(copy);
					System.exit(0);
				}
			}
		}
	}
}
