/* HashTable.java
   CSC 225 - Spring 2017
   Template for string hashing
   
   =================
   
   Modify the code below to use quadratic probing to resolve collisions.
   
   Your task is implement the hash, insert, find, remove, and resize methods for the hash table.
   
   The load factor should always remain in the range [0.25,0.75] and the tableSize should always be prime.
   
   =================
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java HashTable
	
   Input data should consist of a list of strings to insert into the hash table, one per line,
   followed by the token "###" on a line by itself, followed by a list of strings to search for,
   one per line, followed by the token "###" on a line by itself, followed by a list of strings to remove,
   one per line.
	
   To conveniently test the algorithm with a large input, create
   a text file containing the input data and run the program with
	java HashTable file.txt
   where file.txt is replaced by the name of the text file.

   B. Bird - 07/04/2015
   M. Simpson - 21/02/2016
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;
import java.lang.Math;

public class hashish{

     //The size of the hash table.
     int TableSize;
     
     //The current number of elements in the hash table.
     int NumElements;
	
	//The variable T represents the array used for the table.
	// DECLARE YOUR TABLE DATA STRUCTURE HERE
	private String[] hashes ; 

	public hashish(){
     	NumElements = 0;
     	TableSize = 997;
     	// INITIALZE YOUR TABLE DATA STRUCTURE HERE

     	
     	hashes = new String[TableSize]; 




	}

	
	/* hash(s) = ((3^0)*s[0] + (3^1)*s[1] + (3^2)*s[2] + ... + (3^(k-1))*s[k-1]) mod TableSize
	   (where s is a string, k is the length of s and s[i] is the i^th character of s)
	   Return the hash code for the provided string.
	   The returned value is in the range [0,TableSize-1].

	   NOTE: do NOT use Java's built in pow function to compute powers of 3. To avoid integer
	   overflows, write a method that iteratively computes powers and uses modular arithmetic
	   at each step.
	*/
	public int hash(String s){

		int h = 0 ; 

		for(int i =0 ;  i<s.length() ; i++){

			h += Math.pow(3,s.charAt(i)*(int)s.charAt(i) );  //avoid using pow
		}
		//System.out.println("calling hash: Result : "+ (h%TableSize));
		return h%TableSize;
	}
	
	/* insert(s)
	   Insert the value s into the hash table and return the index at 
	   which it was stored.
	*/
	public int insert(String s){
		
		int hx = hash(s); 

		int j = 0; 

		System.out.println("Inserted string :" + s + "     in hashes[hx] : " + hashes[hx]);
		//using quadratic probing to find empty slot 
		while(hashes[j] != null){
			j++; 
			hx = hx + (j*j);

			if(hx >= TableSize){
				hx = hx%TableSize; 
			}


		}

		hashes[hx] = s ; 
		
		return hx;
	}
	
	/* find(s)
	   Search for the string s in the hash table. If s is found, return
	   the index at which it was found. If s is not found, return -1.
	*/
	public int find(String s){

		//Get the hash value of the string and start the search at that index.
		int i = hash(s);
		
		//Use linear probing to find the string.
		// while (true){
		// 	String element = T.getElement(i);
		// 	//If the slot is empty, the provided string was not found.
		// 	if (element == null)
		// 		return -1;
		// 	//If the slot contains the desired string, return its index.
		// 	//Note that to test whether strings are equal in Java,
		// 	//the '==' operator is not correct.
		// 	if (s.equals(element))
		// 		return i;
		// 	//If the string was not at this index, continue looking.
		// 	i++;
		// 	if (i >= TableSize)
		// 		i = i%TableSize;
		// }
		return 0 ;
	}
	
	/* remove(s)
	   Remove the value s from the hash table if it is present. If s was removed, 
	   return the index at which it was removed from. If s was not removed, return -1.
	*/
	public int remove(String s){
     	return 0;
	}
	
	/* resize()
	   Resize the hash table to be a prime within the load factor requirements.
	*/
	public void resize(int newSize){
     	
	}
	
	/* **************************************************** */
	
	/* main()
	   Contains code to test the hash table methods. 
	*/

	

	public static void main(String[] args){
		
		Scanner s;
		boolean interactiveMode = false;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			interactiveMode = true;
			s = new Scanner(System.in);
			System.out.println(s);
		}
		s.useDelimiter("\n");
		if (interactiveMode){
			System.out.printf("Enter a list of strings to store in the hash table, one per line.\n");
			System.out.printf("To end the list, enter '###'.\n");
		}else{
			System.out.printf("Reading table values from %s.\n",args[0]);
		}
		
		Vector<String> tableValues = new Vector<String>();
		Vector<String> searchValues = new Vector<String>();
		Vector<String> removeValues = new Vector<String>();
		
		String nextWord;
		
		while(s.hasNext() && !(nextWord = s.next().trim()).equals("###"))
			tableValues.add(nextWord);
		System.out.printf("Read %d strings.\n",tableValues.size());
		
		if (interactiveMode){
			System.out.printf("Enter a list of strings to search for in the hash table, one per line.\n");
			System.out.printf("To end the list, enter '###'.\n");
		}else{
			System.out.printf("Reading search values from %s.\n",args[0]);
		}	
		
		while(s.hasNext() && !(nextWord = s.next().trim()).equals("###"))
			searchValues.add(nextWord);
		System.out.printf("Read %d strings.\n",searchValues.size());
		
		if (interactiveMode){
			System.out.printf("Enter a list of strings to remove from the hash table, one per line.\n");
			System.out.printf("To end the list, enter '###'.\n");
		}else{
			System.out.printf("Reading remove values from %s.\n",args[0]);
		}
		
		while(s.hasNext() && !(nextWord = s.next().trim()).equals("###"))
			removeValues.add(nextWord);
		System.out.printf("Read %d strings.\n",removeValues.size());
		
		HashTable H = new HashTable();
		long startTime, endTime;
		double totalTimeSeconds;
		
		startTime = System.currentTimeMillis();

		for(int i = 0; i < tableValues.size(); i++){
			String tableElement = tableValues.get(i);
			long index = H.insert(tableElement);
		}
		endTime = System.currentTimeMillis();
		totalTimeSeconds = (endTime-startTime)/1000.0;
		
		System.out.printf("Inserted %d elements.\n Total Time (seconds): %.2f\n",tableValues.size(),totalTimeSeconds);
		
		int foundCount = 0;
		int notFoundCount = 0;
		startTime = System.currentTimeMillis();

		for(int i = 0; i < searchValues.size(); i++){
			String searchElement = searchValues.get(i);
			long index = H.find(searchElement);
			if (index == -1)
				notFoundCount++;
			else
				foundCount++;
		}
		endTime = System.currentTimeMillis();
		totalTimeSeconds = (endTime-startTime)/1000.0;
		
		System.out.printf("Searched for %d items (%d found, %d not found).\n Total Time (seconds): %.2f\n",
							searchValues.size(),foundCount,notFoundCount,totalTimeSeconds);
							
		int removedCount = 0;
		int notRemovedCount = 0;
		startTime = System.currentTimeMillis();

		for(int i = 0; i < removeValues.size(); i++){
			String removeElement = removeValues.get(i);
			long index = H.remove(removeElement);
			if (index == -1)
				notRemovedCount++;
			else
				removedCount++;
		}
		endTime = System.currentTimeMillis();
		totalTimeSeconds = (endTime-startTime)/1000.0;
		
		System.out.printf("Tried to remove %d items (%d removed, %d not removed).\n Total Time (seconds): %.2f\n",
							removeValues.size(),removedCount,notRemovedCount,totalTimeSeconds);



	}
}