package GraphingPackage;

public class GraphTester
{
   public static void main(String[] args)
   {
      int vertNum, edgeNum, index, innerIndex, weight,
      minWeightNN = 99999999, minWeightSE;
      Vertex vert1, vert2, startingVertex;
      vertNum = (int) (Math.random() * 10) + 10;
      edgeNum = ( vertNum * ( vertNum - 1 ) ) / 2;
      //amount of edges for a complete graph
      System.out.println(vertNum);
      Graph tester = new Graph( vertNum, edgeNum );
      for ( index = 0; index < vertNum; index++ )
      {
         tester.addVertex();
      }
      
      for ( index = 0; index < tester.getNumVertices(); index++ )
      {
         vert1 = tester.findMeantVertex( index );
         for ( innerIndex = index + 1; innerIndex < tester.getNumVertices(); innerIndex++ )
         {
            vert2 = tester.findMeantVertex( innerIndex );
            weight = (int)(Math.random() * 999) + 1;
            tester.addEdge(vert1, vert2, weight);
         }
      }
      for ( index = 0; index < tester.getNumVertices(); index++ )
      {
         startingVertex = tester.findMeantVertex( index );
         weight = tester.runNearestNeighbor( startingVertex );
         if ( weight < minWeightNN )
         {
            minWeightNN = weight;
         }
      }
      System.out.println( minWeightNN );
      minWeightSE = tester.runSortedEdges();
      
      System.out.println( minWeightSE );
   }

}
