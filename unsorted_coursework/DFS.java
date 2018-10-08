/* DFS.java
   CSC 225 - Spring 2017
   Template for Depth First Search
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java DFS
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test graphs (in the format described below) and run
   the program with
	java DFS file.txt
   where file.txt is replaced by the name of the text file.
   
   The input consists of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   An input file can contain an unlimited number of graphs; each will be 
   processed separately.


   B. Bird - 07/11/2015
   M. Simpson - 03/09/2016
*/

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

public class DFS{

	/* DFS(G)
	   Given an adjacency matrix describing a graph, print the
	   listing of vertices encountered by a depth-first search starting at 
	   vertex 0.
	*/
	static int [] parent ;
	

	public static void DFS(int[][] G){
		
		int n = G.length;

		boolean[] visted = new boolean[n]; 

		parent = new int[n];

		for(int i =0; i < n ; i++){
			visted[i] = false ;
		}

		dfs_recur(G,visted , 0 );
		
	}

	static int i = 0 ; 

	public static void dfs_recur(int[][] G,boolean [] visted , int v){
		
		i = i+1; 
		System.out.println(v+" ");
		visted[v] = true ; 
		parent[i] = v ; 
		

		for(int i = 0 ; i< G.length ; i++){
			
			if(G[v][i]==1 && visted[i] ==false ){ //G[v][i] equivalent //&& visted[]"neighbor not visted "
				

				dfs_recur(G,visted,i);
			
			}

			
			
		}

		// if(i%2 != 0){
		// 	System.out.println("odd amount of visits");
		// }
	}
	
		
	/* main()
	   Contains code to test the DFS method.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}
			long startTime = System.currentTimeMillis();
			DFS(G.clone());
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
				
		}
		graphNum--;
		System.out.printf("Processed %d graph%s.\nAverage Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>0)?totalTimeSeconds/graphNum:0);
	}
}