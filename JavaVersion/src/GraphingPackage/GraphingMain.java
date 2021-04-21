
package GraphingPackage;

import java.util.Scanner;

public class GraphingMain
{
   static Scanner userInput = new Scanner( System.in );
   static Graph sortingGraph;
   final static int MIN_VERTICES = 7, VERTEX_RANGE = 0, MAX_VERTEX_WEIGHT = 999;

   /**
    * Allows a user to program in their own graph instead of using the provided
    * Interface
    * <p>
    * Note: Does not run without provided code
    * Note: If no code is provided, and also chosen as the graph creator, 
    * the program will break
    * Note: sortingGraph must be the name of the created graph
    */
   static void inputSelfMadeGraph()
   {
      sortingGraph = new Graph( 5, 4 );
      sortingGraph.addVertex("A");
      sortingGraph.addVertex("B");
      sortingGraph.addVertex("C");
      sortingGraph.addVertex("D");
      sortingGraph.addVertex("E");
      
      char vertex = 'A';
      int weight = 35;

      for (int index = 0; index < 4; index++ )
      {
         Vertex vertex1 = sortingGraph.findMeantVertex( "" + vertex );
         vertex++;
         Vertex vertex2 = sortingGraph.findMeantVertex( "" + vertex );
      sortingGraph.addEdge( vertex1, vertex2, weight);
      weight+=5;
      }
   }
   
   /**
    * Creates a graph using random number generators
    * <p>
    * Note: either this method or the programmed in graph method can be used,
    *       not both. 
    * Note: The number of vertices are random, the names are the default names,
    *       and the number of edges is the number which completes the graph.
    */
   static void createRandomGraph()
   {
      int vertNum, edgeNum, index, innerIndex, weight;
      Vertex vert1, vert2;
      vertNum = (int) (Math.random() * VERTEX_RANGE) + MIN_VERTICES;
      edgeNum = ( vertNum * ( vertNum - 1 ) ) / 2;
      //algorithm for amount of edges for a complete graph
      sortingGraph = new Graph( vertNum, edgeNum );
      
      System.out.println(vertNum);
      for ( index = 0; index < vertNum; index++ )
      {
         sortingGraph.addVertex();
      }
      
      for ( index = 0; index < vertNum; index++ )
      {
         vert1 = sortingGraph.findMeantVertex( index );
         for ( innerIndex = index + 1; innerIndex < sortingGraph.getNumVertices(); innerIndex++ )
         {
            vert2 = sortingGraph.(( innerIndex );
            weight = (int)(Math.random() * MAX_VERTEX_WEIGHT) + 1;
            sortingGraph.addEdge(vert1, vert2, weight);
         }
      }

   }
   
   /**
    * Is run for the creation of one graph, for use with a dummy user
    * <p>
    * Note: Is used to show off the power of the program, as this one has 
    * Significantly more output
    */
   public static void runInterface()
   {

      String inputAnswer;
      Vertex startingVertex;
      int weightNN, weightSE;
      System.out.println( "Did you enter a graph into inputSelfMadeGraph()?" );
      System.out.println( "Any answer other than \"yes\""
            + " will create a random graph" );
      System.out.println("The graph will have between " + MIN_VERTICES + 
            " and " + (MIN_VERTICES + VERTEX_RANGE) + " vertices" );
      inputAnswer = userInput.nextLine();
      if (! inputAnswer.contains( "yes" ) )
      {
         createRandomGraph();
      }
      else
      {
         inputSelfMadeGraph();
      }
      System.out.println( "Graph creation complete!" );
      sortingGraph.dumpVertices();
      sortingGraph.dumpEdges();

      System.out.println( "We will now sort by nearest neighbor!" );
      System.out.println( "Which vertex would you like to start at?" );
      inputAnswer = userInput.nextLine();
      startingVertex = sortingGraph.findMeantVertex( inputAnswer );
      weightNN = sortingGraph.runNearestNeighbor( startingVertex );
      System.out.println( " Nearest Neighbor Weight: " + weightNN );
      
      System.out.println( "We will now sort by sorted edges!" );
      weightSE = sortingGraph.runSortedEdges();
      System.out.println( "Sorted Edges Weight: " + weightSE );
   }
   
   
   public static void main ( String[] args )
   {
      runInterface();
   }
}