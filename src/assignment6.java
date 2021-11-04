  // Mohammed Ali Kunda
       // Fri 4/23/2021 7:28 AM

import java.util.Arrays;
import java.util.Scanner;



//this Projects implemnets dijkstra algorithm with adjacency matrix
public class assignment6 {

                                          // a, b, c, d, e, f, g, h,  i,  j 
        final static int digraph[][] = new int[][] {{ 0, 0, 0, 0, 1, 0, 0, 10, 0, 0 },//a
                                                    { 0, 0, 2, 0, 0, 0, 0, 11, 0, 1 },//b
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },//c
                                                    { 4, 0, 7, 0, 9, 14, 0, 1, 0, 0 },//d
                                                    { 0, 0, 0, 0, 0, 3, 0, 0, 0, 0 },//e
                                                    { 0, 1, 3, 0, 0, 0, 7, 0, 1, 0 },//f           //weight simpler digraph or adjacency distance matrix
                                                    { 0, 0, 0, 0, 0, 5, 0, 0, 0, 0 },//g
                                                    { 0, 0, 0, 0, 5, 0, 0, 0, 9, 0 },//h
                                                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },//i
                                                    { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}};//j
    final static int V = digraph.length; //number of vertics
    

    public static void main(String[]args)
    {
        System.out.println("Assignment 6-implementing dijkstra Algorithm");
        System.out.println("Instruction, there is a graph which can be cities,\nyou have to enter the source node(starting point)\nthis alogritm will find the shortest path\nto all the nodes in the map with distance");
        String user_permission_to_continue;  //string to hold user input if want to run program again
        Scanner keyboard = new Scanner(System.in);
        do {
            int sourceNode;  //user input for source node where it find the distance from source node to all the vertex
            do {
                
                System.out.println("Enter the Source node(Between 0-9): ");
                sourceNode = keyboard.nextInt();
            }
            while (sourceNode < 0 || sourceNode >= V);
            {

            }
            DijkstraAlgorithm(digraph, sourceNode);

            System.out.println("\nDo you want to continue? ");
            user_permission_to_continue = keyboard.next();
        }
        while(user_permission_to_continue.equalsIgnoreCase("yes")||user_permission_to_continue.equalsIgnoreCase("y"));
       System.out.println("--------------------------------END------------------------------------");
    }
      public static int minimal(int[] currDis, boolean[] toBeChecked)
      {
          int min = Integer.MAX_VALUE;  // check the currdist with infifnity
          int result = 0;
          for(int j = 0; j < V; j++ )
          {
              if(toBeChecked[j] == false && currDis[j] < min ) {   //if currdist less then infinity then min change to currdist-i 
                  min = currDis[j];
                  result = j;
              }
          }
           return result; //return the index
      }
      //printing results
       public static void printMethod(int[] currDist,int[] predecessor, int first)
    {
       
        System.out.print("  Edges          Shortest Distance      Shortest Path");
        System.out.println("\n__________________________________________________");
          
        for (int i = 0;i < V;i++) 
        {
            if (i != first) 
            {
                  if(currDist[i]==2147483647)
                  {
                       System.out.print("\n" + first + " -> " +i + "\t\t|\t"+"∞" +"\t|\t");
                  }
                  else
                  {
                      System.out.print("\n" + first + " -> " +i + "\t\t|\t"+currDist[i] + "\t|\t");
                  }
                
                printPath(predecessor,i);
               
            }
        }
         System.out.println("\n___________________________________________________");
    }
  
   //printing shortest path to the source node
    public static void printPath(int[] predecessor,Integer i)
    {
        Scanner scan = new Scanner(String.valueOf(i));
        if (i == 0)
        {
            return;
        }
        printPath(predecessor,predecessor[i]);
     
        if(scan.hasNext() )
        {
           System.out.print("-->");
        }
      
         System.out.print(i+" ");
       
    }    
    public static void  DijkstraAlgorithm(int [][] diGraph,int first) //method to find the shortest path from the source node to every single node
    {

        int currDist[] = new int[V];     //current distance array
        int predecessor[] = new int[V];  //predcessor array
        boolean toBeChecked[] = new boolean[V];   //tobechecked array to check array

        for(int i = 0; i <V; i++)
        {
            currDist[i] = Integer.MAX_VALUE;         //for loop intialize the array with infinity
            toBeChecked[i] = false;                //setting all the array to false
        }
         predecessor[0] = -1;   //intializing the presecessor 0 to -1
         currDist[first] = 0;   //intializing the source node

         for(int i = 0; i < V; i++)  //whilem didn't work so have to us a for loop to iterate for v and as v is used in next for loop then it became nested for-loop
         {
          int v = minimal(currDist,toBeChecked);   //call method minimal to find min index
          toBeChecked[v] = true;                   // set that to true aka remove it from array
  
          for(int  u = 0; u < V; u++)
          {
              if(!toBeChecked[u]&&diGraph[v][u]!=0 && currDist[v]!=Integer.MAX_VALUE&& currDist[u] > currDist[v]+diGraph[v][u])
              {
                  currDist[u]=currDist[v]+diGraph[v][u];  //updated the all the vertex
                  predecessor[u]=v;    //update the predecessor
              }
          }
         }
      //  printMethod(currDist,predecessor,first);
        printMethod(currDist,predecessor,first);
    }
}