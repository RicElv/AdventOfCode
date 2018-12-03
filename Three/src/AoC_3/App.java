package AoC_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("input3.txt"));
		AoC_p1p2 p1p2 = new AoC_p1p2(input);
		p1p2.execute();
	}

}
