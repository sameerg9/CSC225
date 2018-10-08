/* HeapSort.java
   CSC 225 - Spring 2017
   Assignment 2 - Template for HeapSort
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java HeapSort

   To conveniently test the algorithm with a large input, create a 
   text file containing space-separated integer values and run the program with
	java HeapSort file.txt
   where file.txt is replaced by the name of the text file.

   M. Simpson & B. Bird - 11/16/2015
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;



//Do not change the name of the HeapSort class
public class HeapSort{
	public static final int max_values =1000 ;
	public static  int n ; 
	//= 1000000;
	// int leftChild = 0 ; 
	// int rightChild = 0; 
	// int lrgrChild = 0 ; 
	// int	[]pq = new int[max_values];	
	// int n = 0;
	
	

	// /* HeapSort(A)
	// 	Sort the array A using an array-based heap sort algorithm.
	// 	You may add additional functions (or classes) if needed, but the entire program must be
	// 	contained in this file. 

	// 	Do not change the function signature (name/parameters).
	// */
	// public static void HeapSort(int[] A){
	// 		 n = A.length-1;
			 
	// 		 pq=A;
			 
	// 		 buildheap(pq);
		
	// 	for(int i=n ; i >0 ; i-- ){
	// 		exchange(0,i);
	// 		n=n-1;
	// 		maxheap(pq,0);



	// 		// int root = A[N];
	// 		// int lchild = A[2*i+1];
	// 		// int rchild = A[2*i+2];

	// 		// System.out.println( "  : "+root+" : ");
	// 		// System.out.println("/"+  "l");
	// 		// System.out.println(lchild + "---------"+rchild );		/* ... Your code here ... */
	// 	}
	// }


	
	
	private static void exchange(int[] arr , int i, int j ) {
	 	int t=arr[i];
        arr[i]=arr[j];
        arr[j]=t; 	
	}
	private static void heapify(int[] arr , int i){
		
		int lftchld = i*2; 
		int rghtchld = lftchld+1; 
		int gc = i ; 

		System.out.println("calling heapify on     gc:"+arr[gc] +"\n lftchild:"+arr[lftchld] +"   rightchild:"+arr[rghtchld] );


		if(lftchld <= max_values && arr[lftchld] > arr[gc] )
			gc = lftchld ; 
		if(rghtchld <= max_values && arr[rghtchld] > arr[gc] )
			gc = rghtchld; 

		if(gc != i ){
			exchange(arr, i , gc);
			heapify(arr,gc);
		}

	}
	public static void HeapSort(int[] A){
		 n = A.length-1; 
		
		for(int i = n/2 ; i >= 0 ; i--){
			heapify(A,i);
		}

		for(int i = n ; i>0 ; i--){
			exchange(A,0,i);
			n--; 
			heapify(A,0);
		}
	}
	 // public static void maxheap(int[] a, int i){ 
  //       lefChild=2*i;
  //       righChild=2*i+1;
        
  //       if(leftChild <= n && A[leftChild] > A[i]){
  //           lrgrChild=leftChild;
  //       }
  //       else{
  //           lrgrChild=i;
  //       }
        
  //       if(rightChild <= n && A[rightChild] > a[lrgrChild]){
  //           lrgrChild=rightChild;
  //       }
  //       if(lrgrChild!=i){
  //           exchange(i,lrgrChild);
  //           maxheap(A, lrgrChild);
  //       }
  //   }

  //     public static void buildheap(int []A){
  //       n=A.length-1;
  //       for(int i=n/2;i>=0;i--){
  //           maxheap(A,i);
  //       }
  //   }

	/* main()
	   Contains code to test the HeapSort function. Nothing in this function 
	   will be marked. You are free to change the provided code to test your 
	   implementation, but only the contents of  the HeapSort() function above 
	   will be considered during marking.
	*/
	 
}
