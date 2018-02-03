/* NinePuzzle.java
   CSC 225 - Spring 2017
   Assignment 4 - Template for the 9-puzzle
   
   This template includes some testing code to help verify the implementation.
   Input boards can be provided with standard input or read from a file.
   
   To provide test inputs with standard input, run the program with
	java NinePuzzle
   To terminate the input, use Ctrl-D (which signals EOF).
   
   To read test inputs from a file (e.g. boards.txt), run the program with
    java NinePuzzle boards.txt
	
   The input format for both input methods is the same. Input consists
   of a series of 9-puzzle boards, with the '0' character representing the 
   empty square. For example, a sample board with the middle square empty is
   
    1 2 3
    4 0 5
    6 7 8
   
   And a solved board is
   
    1 2 3
    4 5 6
    7 8 0
   
   An input file can contain an unlimited number of boards; each will be 
   processed separately.
  
   B. Bird    - 07/11/2014
   M. Simpson - 11/07/2015
*/

import java.util.*;
import java.io.File;

public class NinePuzzle{

	//The total number of possible boards is 9! = 1*2*3*4*5*6*7*8*9 = 362880
	public static final int NUM_BOARDS = 362880;

public static int[] getzero_coord(int[][] A){
		int[] ij = new int[2]; 
		for(int i = 0 ; i < 3 ; i++){
			for(int j =0 ; j< 3 ; j++){
				if(A[i][j] == 0 ){
					ij[0] = i ; 
					ij[1] = j ; 
				}
			}
		}
		return ij ;
	}

	public static boolean SolveNinePuzzle(int[][] B){
		
		//instantiate adjacency list

		int[][] aList = new int[NUM_BOARDS+1][4];
		for(int i = 0 ; i <= NUM_BOARDS ; i++){
			for(int j = 0 ; j < 4 ; j++){
				aList[i][j] = -1 ; 
			}

		}

		
		for(int k =0 ; k<=NUM_BOARDS ; k++){
			
			int ith = 0 ;
			int jth = 0 ; 
			int ix = 0 ; 

			int [][] bb ; // board B prime 
			int [][] board = getBoardFromIndex(k); 

			
			ith =  getzero_coord(board)[0] ; 
			jth =  getzero_coord(board)[1] ; 
						
			

		
			if(ith>0){
			
				bb = getBoardFromIndex(k) ;
				bb[ith][jth] = bb[ith-1][jth] ;
				bb[ith-1][jth] = 0 ; 

				for(ix = 0 ; aList[getIndexFromBoard(bb)][ix] != -1 ; ix++ ){}

				aList[getIndexFromBoard(bb)][ix] = k ;
			
			}
			if(ith<2){
			
				bb = getBoardFromIndex(k) ;
				bb[ith][jth] = bb[ith+1][jth] ;
				bb[ith+1][jth] = 0 ; 

				for(ix = 0 ; aList[getIndexFromBoard(bb)][ix] != -1 ; ix++ ){}

				aList[getIndexFromBoard(bb)][ix] = k ;

			}
			if(jth>0){
			
				bb = getBoardFromIndex(k) ;
				bb[ith][jth] = bb[ith][jth-1] ;
				bb[ith][jth-1] = 0 ; 

				for(ix = 0 ; aList[getIndexFromBoard(bb)][ix] != -1 ; ix++ ){}

				aList[getIndexFromBoard(bb)][ix] = k ;

			}
			if(jth<2){
			
				bb = getBoardFromIndex(k) ;
				bb[ith][jth] = bb[ith][jth+1] ;
				bb[ith][jth+1] = 0 ; 

				for(ix = 0 ; aList[getIndexFromBoard(bb)][ix] != -1 ; ix++ ){}

				aList[getIndexFromBoard(bb)][ix] = k;


			}
		}//end for 


		int in = getIndexFromBoard(B) ;

		int[][] gg = new int[3][3]; 

		int c = 1 ; 


		for(int i =0 ; i < 3 ; i++){
			for(int j =0 ; j < 3 ; j++){
				if(c != 8 ){
					gg[i][j] = c ; 
					c++; 
				}else{
					gg[i][j] = 0 ; 
				}

			}
		}

		// System.out.println("\n\nWhat is this gg matric ");
		// printBoard(gg);

		int ixgoal = getIndexFromBoard(gg);

		//start BFS 

		boolean [] visited = new boolean[NUM_BOARDS+1]; 

		int curr ; 

		Queue<Integer> queue = new LinkedList<Integer>(); 

		int prev[] = new int[NUM_BOARDS+1]; 


		//instatiating prev list here. ????

		for(int i = 0 ; i <= NUM_BOARDS ; i++){
			prev[i] = -1;
		}

		queue.add(ixgoal) ; 
		visited[ixgoal] = true ;



		while(!queue.isEmpty() ){
			
			curr = queue.remove() ;  //1.remove();

			if(curr == in){
				
				int i = curr; 
				
				while(prev[i] != -1){
					
					printBoard( getBoardFromIndex(i) );
					i = prev[i]; 


				}
				
				printBoard(getBoardFromIndex(i)); 
				
				return true;



			}else{
				

				int i =0 ; 
				while(i != 4){
					
					if(aList[curr][i] != -1 ){
						if( visited[aList[curr][i]] == false ){
							
							queue.add( aList[curr][i] );
							prev[aList[curr][i]] = curr ; 
							visited[aList[curr][i]] = true ; 

						}
					}
					i++; 
				
				}

			}

		}


		return false;

		
	}
	
	/*  printBoard(B)
		Print the given 9-puzzle board. The SolveNinePuzzle method above should
		use this method when printing the sequence of moves which solves the input
		board. If any other method is used (e.g. printing the board manually), the
		submission may lose marks.
	*/
	public static void printBoard(int[][] B){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
				System.out.printf("%d ",B[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	
	/* Board/Index conversion functions
	   These should be treated as black boxes (i.e. don't modify them, don't worry about
	   understanding them). The conversion scheme used here is adapted from
		 W. Myrvold and F. Ruskey, Ranking and Unranking Permutations in Linear Time,
		 Information Processing Letters, 79 (2001) 281-284. 
	*/
	public static int getIndexFromBoard(int[][] B){
		int i,j,tmp,s,n;
		int[] P = new int[9];
		int[] PI = new int[9];
		for (i = 0; i < 9; i++){
			P[i] = B[i/3][i%3];
			PI[P[i]] = i;
		}
		int id = 0;
		int multiplier = 1;
		for(n = 9; n > 1; n--){
			s = P[n-1];
			P[n-1] = P[PI[n-1]];
			P[PI[n-1]] = s;
			
			tmp = PI[s];
			PI[s] = PI[n-1];
			PI[n-1] = tmp;
			id += multiplier*s;
			multiplier *= n;
		}
		return id;
	}
		
	public static int[][] getBoardFromIndex(int id){
		int[] P = new int[9];
		int i,n,tmp;
		for (i = 0; i < 9; i++)
			P[i] = i;
		for (n = 9; n > 0; n--){
			tmp = P[n-1];
			P[n-1] = P[id%n];
			P[id%n] = tmp;
			id /= n;
		}
		int[][] B = new int[3][3];
		for(i = 0; i < 9; i++)
			B[i/3][i%3] = P[i];
		
		return B;
	}
	

	public static void main(String[] args){
		/* Code to test your implementation */
		/* You may modify this, but nothing in this function will be marked */

		
		Scanner s;

		if (args.length > 0){
			//If a file argument was provided on the command line, read from the file
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			//Otherwise, read from standard input
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read boards until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading board %d\n",graphNum);
			int[][] B = new int[3][3];
			int valuesRead = 0;
			for (int i = 0; i < 3 && s.hasNextInt(); i++){
				for (int j = 0; j < 3 && s.hasNextInt(); j++){
					B[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < 9){
				System.out.printf("Board %d contains too few values.\n",graphNum);
				break;
			}
			System.out.printf("Attempting to solve board %d...\n",graphNum);
			long startTime = System.currentTimeMillis();
			boolean isSolvable = SolveNinePuzzle(B);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			if (isSolvable)
				System.out.printf("Board %d: Solvable.\n",graphNum);
			else
				System.out.printf("Board %d: Not solvable.\n",graphNum);
		}
		graphNum--;
		System.out.printf("Processed %d board%s.\n Average Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>1)?totalTimeSeconds/graphNum:0);

	}

}