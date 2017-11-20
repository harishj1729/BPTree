//package adsTest;

import java.io.*;
import java.util.*;

public class adsTest {

	//private static Node node;
	//private static int degree;
	
	
	public static void main(String[] args) throws Exception {
		
		//File file = new File(args[0]);
		File file = new File("E:\\Acads\\Eclipse\\ADSTest\\Input.txt");
		BufferedReader infile = new BufferedReader(new FileReader(file));
		
		String line;
		int degree = Integer.parseInt(infile.readLine().trim());
		BPTree tree = new BPTree(degree);
		
		File output = new File("output_file.txt");
		if(!output.exists()){
			output.createNewFile();
		}
		FileWriter fw = new FileWriter(output.getAbsoluteFile());
		PrintWriter pw = new PrintWriter(fw);
		
		/*try{
			int degree = Integer.parseInt(infile.readLine().trim());
			tree = new BPTree(degree);
		}catch(Exception e){
			System.out.println("Degree should be an integer");
			System.exit(0);
		}
		*/
		
		while((line = infile.readLine()) != null){
			if(line.startsWith("Insert")){
				tree.Insert(Double.parseDouble(line.split("Insert\\(")[1].split
						(",")[0]), line.split(",")[1].split("\\)")[0]);
			}
			else{
				if(line.contains(",")){
					String searchRange = tree.Search(Double.parseDouble(line.split("Search\\(")[1].split(",")[0]),
							Double.parseDouble(line.split("Search\\(")[1].split(",")[1].split("\\)")[0])).trim();
					//searchRange.trim();
					if( searchRange.charAt(searchRange.length() - 1) == ','){
						searchRange = searchRange.substring(0, searchRange.length()-1);
					}
					pw.println(searchRange);
				}
				else{
					String search = tree.Search(Double.parseDouble(line.split("Search\\(")[1].split("\\)")[0]));
					pw.println(search);
				}
			}
		}		
		fw.close();
		infile.close();
		
	}

}
