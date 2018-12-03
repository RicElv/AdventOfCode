package AoC_3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class AoC_p1p2 {
	Scanner input;
	int[][] fabric;
	int size = 1000;
	int ID = 0;
	int overlap;
	HashMap<Integer,Integer[]> noOverlaps;
	
	AoC_p1p2 (Scanner input){
		this.input = input;
		overlap = 0;
	}
	
	public void execute(){
		fabric = new int[size][size];
		noOverlaps = new HashMap<Integer,Integer[]>();
		
		while(input.hasNextLine()){
			String current = input.nextLine();
			ID++;
			
			Integer[] dimensions = dimensions(current);
			int xCorner  = dimensions[0];
			int yCorner  = dimensions[1];
			int xLength  = dimensions[2];
			int yLength  = dimensions[3];
			
			while((xCorner + xLength > size) || (yCorner + yLength > size)){
				resize();
			}
			
			boolean IDoverlaps = false;
			for(int y = yCorner; y < yCorner + yLength; y++){
				for(int x = xCorner; x < xCorner + xLength; x++){
					int cur = fabric[x][y];
					if(cur != 0) {
						if(cur > 0){
							overlap++;
							noOverlaps.remove(cur);
							fabric[x][y] = -1;
						}
						IDoverlaps = true;
					} else {
						fabric[x][y] = ID;
					}
				}
			}
			if(!IDoverlaps){
				noOverlaps.put(ID, dimensions);
			}
		}
		System.out.println(overlap + " overlapping square inches");
		Set<Integer> keys = noOverlaps.keySet();
		for(int i : keys){
			System.out.println("ID: " + i + " claim does not overlap");
		}
	
	}
	
	StringBuilder sb = new StringBuilder();
	private Integer[] dimensions(String current){
		int state = 0;
		int xCorner = 0;
		int yCorner = 0;
		int xLength = 0;
		int yLength = 0;
		
		for(int i = 0; i < current.length(); i++){
			char ch = current.charAt(i);
			switch (state){
				case 0:
					if(ch == '@'){
						state++;
						i++;
					}
					break;
				case 1:
					if(ch == ','){
						xCorner = Integer.parseInt(sb.toString());
						sb = new StringBuilder();
						state++;
					} else {
						sb.append(ch);
					}
					break;
				case 2:
					if(ch == ':'){
						yCorner = Integer.parseInt(sb.toString());
						sb = new StringBuilder();
						state++;
						i++;
					} else {
						sb.append(ch);
					}
					break;
				case 3:
					if(ch == 'x'){
						xLength = Integer.parseInt(sb.toString());
						sb = new StringBuilder();
						state++;
					} else {
						sb.append(ch);
					}
					break;
				case 4:
					if(!(i + 1 < current.length())){
						sb.append(ch);
						yLength = Integer.parseInt(sb.toString());
						sb = new StringBuilder();
					} else {
						sb.append(ch);
					}
					break;
			}
		}
		Integer[] dimensions = {xCorner, yCorner, xLength, yLength};
		return dimensions;
	}
	
	private void resize(){
		int oldSize = size;
		size = size + 1000;
		int[][] resizedFabric = new int[size][size];
		
		for(int i = 0; i < oldSize; i++){
			for(int k = 0; k < oldSize; i++){
				resizedFabric[i][k] = fabric[i][k];
			}
		}
		fabric = resizedFabric;
	}

}