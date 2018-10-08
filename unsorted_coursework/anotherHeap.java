import java.util.*;
import java.io.File;
public class anotherHeap{
 
   private static int[] a;
   private static int n;
   private static int lChild;
   private static int rChild;
   private static int lrgr;
 
 
   public static void buildHeap(int [] a) {
      n = a.length-1;
      for(int i=n/2; i>=0; i--){
         maxHeap(a,i);
      }
   }
 
   public static void maxHeap(int[] a, int i) { 
      lChild = 2*i;
      rChild = 2*i+1;
 
      if(lChild <= n && a[lChild] > a[i]){
         lrgr=lChild ; 
      } else {
         lrgr=i;
      }
 
      if(rChild <= n && a[rChild] > a[lrgr]) {
         lrgr=rChild;
      }
      if(lrgr!=i) {
         exchange(i, lrgr);
         maxHeap(a, lrgr);
      }
   }
 
   public static void exchange(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t; 
   }
 
   public static void HeapSort(int[] A) {
      a = A;
      buildHeap(a);
      n = a.length-1;
      
      for(int i=n/2; i>=0; i--){

         if(lChild <= n && a[lChild] > a[i]){
         
            lrgr=lChild ; 
     
         }else{
            lrgr=i;
       }
 
         if(rChild <= n && a[rChild] > a[lrgr]) {
            lrgr=rChild;
         }
      
         if(lrgr!=i) {
            //exchange(i, lrgr);
            int t = a[i];
            a[i] = a[lrgr];
            a[lrgr] = t; 

            //maxHeap(a, lrgr);
      }

      }
      for(int i=n; i>0; i--) {
        
        int t = a[0];
        a[0] = a[i];
        a[i] = t; 
         
         n=n-1;
          for(int j=n/2; j>=0; j--){

         if(lChild <= n && a[lChild] > a[j]){
         lrgr=lChild ; 
      } else {
         lrgr=j;
      }
 
      if(rChild <= n && a[rChild] > a[lrgr]) {
         lrgr=rChild;
      }
      if(lrgr!=j) {
         //exchange(i, lrgr);
         int k = a[j];
         a[j] = a[lrgr];
         a[lrgr] = k; 

         //maxHeap(a, lrgr);
      }

      }
         
      }
   }
 
   public static void main(String[] args) {
     
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
         System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
      }
      Vector<Integer> inputVector = new Vector<Integer>();

      int v;
      while(s.hasNextInt() && (v = s.nextInt()) >= 0)
         inputVector.add(v);

      int[] array = new int[inputVector.size()];

      for (int i = 0; i < array.length; i++)
         array[i] = inputVector.get(i);

      System.out.printf("Read %d values.\n",array.length);


      long startTime = System.currentTimeMillis();

      HeapSort(array);

      long endTime = System.currentTimeMillis();

      double totalTimeSeconds = (endTime-startTime)/1000.0;

      //Don't print out the values if there are more than 100 of them
      if (array.length <= 100){
         System.out.println("Sorted values:");
         for (int i = 0; i < array.length; i++)
            System.out.printf("%d ",array[i]);
         System.out.println();
      }

      //Check whether the sort was successful
      boolean isSorted = true;
      for (int i = 0; i < array.length-1; i++)
         if (array[i] > array[i+1])
            isSorted = false;

      System.out.printf("Array %s sorted.\n",isSorted? "is":"is not");
      System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
   }
   
}
 