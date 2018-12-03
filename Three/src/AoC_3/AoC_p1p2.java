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
	StringBuilder sb = new StringBuilder();
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
			
			//System.out.println(xCorner + " " + xLength);
			
			while((xCorner + xLength > size) || (yCorner + yLength > size)){
				resize();
			}
			
			boolean IDoverlaps = false;
			for(int y = yCorner; y < yCorner + yLength; y++){
				for(int x = xCorner; x < xCorner + xLength; x++){
					fabric[x][y]++;
					if(fabric[x][y] > 1) {
						IDoverlaps = true;
						if(fabric[x][y] == 2) overlap++;
					}
				}
			}
			if(!IDoverlaps){
				noOverlaps.put(ID, dimensions);
			}
		}
		System.out.println(overlap + " overlapping square inches");
		
		Set keys = noOverlaps.keySet();
		Iterator iter = keys.iterator();
		
		
		while (iter.hasNext()){
			ID = (int) iter.next();
			
			Integer[] dimensions = noOverlaps.get(ID);
			int xCorner  = dimensions[0];
			int yCorner  = dimensions[1];
			int xLength  = dimensions[2];
			int yLength  = dimensions[3];
			
			boolean overlap = false;
	   Out: for(int y = yCorner; y < yCorner + yLength; y++){
				for(int x = xCorner; x < xCorner + xLength; x++){
					if(fabric[x][y] > 1) {
						overlap = true;
						break Out;
					}
				}
			}
			if(!overlap) System.out.println("ID: " + ID +  " does not overlap");
		} 
	
	}
	
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
