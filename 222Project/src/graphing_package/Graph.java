package graphing_package;

public class Graph
{
   private static final int VERTEX_VISITED = 2;

   private Edge[] edgeSet;
   private Vertex[] vertexSet;
   public static int edgeIndex = 0;
   public static int vertexIndex = 0;

   /**
    * Creates a new Graph with known vertex and edge number
    * @param numberEdges The number of edges the graph will have
    * @param numbervertices The numnber of vertices the graph will have
    */
   public Graph(int numbervertices, int numberEdges)
   {
      edgeSet = new Edge [ numberEdges ];
      vertexSet = new Vertex [ numbervertices ];
   }
   
   /**
    * Adds an edge to the graph
    * <p>
    * Note: Creates a new edge object, does not use an already created edge
    * @param vertex1 The starting vertex of the edge
    * @param vertex2 The ending vertex for the edge
    * @param weight The weight of the edge
    */
   public void addEdge(Vertex vertex1, Vertex vertex2, int weight)
   {
      Edge newEdge = new Edge ( vertex1, vertex2, weight);
      edgeSet [ edgeIndex ] = newEdge;
      edgeIndex++;
      vertex1.addEdge( newEdge );
      vertex1.increaseDegree();
      vertex2.addEdge( newEdge );
      vertex2.increaseDegree();
   }
   
   /**
    * Adds a vertex to the graph
    * <p>
    * Note: Creates a new vertex object, does not use an already created vertex
    * @param name the name of the created vertex
    */
   public void addVertex (String name)
   {
      Vertex newVertex = new Vertex ( name );
      vertexSet [ vertexIndex ] = newVertex;
      vertexIndex++;
   }
   
   /**
    * Adds a vertex to the graph, with the automatic generated name
    * <p>
    * Note: Creates a new vertex object, does not use an already created vertex
    */
   public void addVertex()
   {
      Vertex newVertex = new Vertex ( "" );
      vertexSet [ vertexIndex ] = newVertex;
      vertexIndex++;
   }
   
   public int getNumVertices()
   {
      return vertexSet.length;
   }
   
   public int getNumEdges()
   {
      return edgeSet.length;
   }
   
   /**
    * Sorts all the edges of the graph's edge set
    * For use with the sorted edges algorithm
    * Note: uses selection sort
    */
   public void sortEdges()
   {
      int listIndex, innerIndex, smallestWeight,
         innerComparison, smallestWeightIndex;
      for ( listIndex = 0; listIndex < edgeSet.length - 1; listIndex++ )
      {
         smallestWeightIndex = listIndex;
         for ( innerIndex = listIndex + 1; innerIndex < edgeSet.length;
                  innerIndex++ )
         {
            smallestWeight = edgeSet[ smallestWeightIndex ].getWeight();
            innerComparison = edgeSet[ innerIndex ].getWeight();
            if ( innerComparison < smallestWeight )
            {
               smallestWeightIndex = innerIndex;
            }
         }
         swapElements( listIndex, smallestWeightIndex );
      }
   }
   
   
   private void swapElements( int oneIndex, int otherIndex )
   {
      Edge temp = edgeSet[ oneIndex ];
      
      edgeSet[ oneIndex ] =  edgeSet[ otherIndex ];
      
      edgeSet[ otherIndex ] =  temp;
      
   }
   
   /**
    * Runs the sorted edges Algorithm
    * @return The weight of the Set
    */
   public int runSortedEdges()
   {
      int[] verticesVisited = new int[ vertexSet.length ];//counts number of visits
      sortEdges();//sorts all the edges of the graph
      int edgeCounter = 0, edgeWeight = 0,
            vert1Num, vert2Num, vert1Visits, vert2Visits;
      Edge currEdge;
      boolean acceptableEdge, allVerticesVisited = false, graphWillBreak;
      while ( !allVerticesVisited )
      {
         acceptableEdge = false;
         while ( !acceptableEdge )
         {
            currEdge = edgeSet[ edgeCounter ];
            vert1Num = currEdge.getVertex1().getVertexNumber();
            vert1Visits = verticesVisited[ vert1Num ];
            vert2Num = currEdge.getVertex2().getVertexNumber();
            vert2Visits = verticesVisited[ vert2Num ];
            graphWillBreak = willEdgeBreakSort( verticesVisited, vert1Num, vert2Num  );
            if ( vert1Visits < VERTEX_VISITED && vert2Visits < VERTEX_VISITED
                  && !graphWillBreak )//checks if circuit will close early
               {
                  acceptableEdge = true;
                  edgeWeight += currEdge.getWeight();
                  verticesVisited[ vert1Num ]++;
                  verticesVisited[ vert2Num ]++;
               }
            edgeCounter++;
         }
         allVerticesVisited = areAllVerticesVisited( verticesVisited );
      }
      return edgeWeight;
   }
   
   private boolean willEdgeBreakSort( int[] verticesVisited, int vert1Num, int vert2Num  )
   {
      int vert1Val = verticesVisited[ vert1Num ];
      int vert2Val = verticesVisited[ vert2Num ];
      int vertexFinishedCounter = 0;
      int index;
      for ( index = 0; index < verticesVisited.length; index++ )
      {
         if ( verticesVisited[ index ] == 1 && index != vert1Num && index != vert2Num )
         {
            return false;
         }
         if ( verticesVisited[ index ] == VERTEX_VISITED )
         {
            vertexFinishedCounter++;
         }
      }
      if ( vertexFinishedCounter == vertexSet.length - 2 )
      {
         return false;
      }
      if ( vert1Val == 1 && vert2Val == 1 )
      {
         return true;
      }
      return false;
   }
   
   /**
    * Runs the nearest neighbor algorithm
    * @param startingVertex The vertex where the method starts
    * @return The total weight of the graph
    */
   public int runNearestNeighbor(Vertex startingVertex)//returns total weight
   {
      Vertex currVertex = startingVertex, otherVert;
      Edge shortEdge;
      int[] verticesVisited = new int [ vertexSet.length ];//showing vertices that have been visited
      int shortestEdgeTries, otherEdgeVertNum, edgeWeight = 0;
      boolean allVerticesVisited = false, acceptableEdge;
      
      int firstVertNum = startingVertex.getVertexNumber();
      verticesVisited[ firstVertNum ] += 2;
      
      while ( !allVerticesVisited )
      {
         acceptableEdge = false;
         shortestEdgeTries = 0;
         while ( !acceptableEdge )
         {
            shortEdge = currVertex.getShortestNeighbor( shortestEdgeTries );
            //current edge to be checked if acceptable
            otherVert = shortEdge.getOtherVertex( currVertex );
            otherEdgeVertNum = otherVert.getVertexNumber();
            acceptableEdge = verticesVisited [ otherEdgeVertNum ] != VERTEX_VISITED;
            if ( acceptableEdge )
            {
               verticesVisited[ otherEdgeVertNum ] += VERTEX_VISITED;//marks visited
               edgeWeight += shortEdge.getWeight();
               currVertex = otherVert;
               allVerticesVisited = areAllVerticesVisited( verticesVisited );
               if ( allVerticesVisited )
               {
                  edgeWeight +=
                     getToFirstVertex( currVertex, startingVertex ).getWeight();
               }
            }
            shortestEdgeTries++; 
         }

      }
      
      return edgeWeight;
   }
   
   
   private boolean areAllVerticesVisited( int[] verticesVisited )
   {
      int index;
      for ( index = 0; index < verticesVisited.length ; index++ )
      {
         if ( verticesVisited [ index ] < VERTEX_VISITED )
         {
            return false;
         }
      }
      return true;
   }
   
   private Edge getToFirstVertex( Vertex currVert, Vertex startingVertex )
   {
      Edge comparedEdge = new Edge( currVert, startingVertex, 0 );
      int index;
      for ( index = 0; index < edgeSet.length; index++ )
      {
         if ( comparedEdge.equals(edgeSet[index] ) )
         {
            return edgeSet[ index ];
         }
      }
      return null;
   }
   
   
   //TODO: should be in edge class
   /**
    * Takes in the name of an existing vertex, and returns object Vertex based
    * on that name
    * 
    * <p>
    * Note: will return null if the Vertex doesn't exist, but that should never 
    * occur here
    * Note: Two vertices of the same name cannot be in the same graph, 
    * else, the last vertex named that name will be found
    * 
    * @param name The name of the desired Vertex
    * @return The vertex that was requested
    */
   public Vertex findMeantVertex ( String name ) 
   {
      int index;
      String vertexName;
      for ( index = 0; index  < vertexSet.length; index++ )
      {
         vertexName = vertexSet[ index ].getName();
         if ( vertexName.equals( name ) )
         {
            return vertexSet[ index ];
         }
      }
      return null;
   }
   
   public Vertex findMeantVertex ( int vertNum )
   {
      return vertexSet[ vertNum ];
   }
   
   
   public void dumpVertices()
   {
      int index;
      for ( index = 0; index < vertexSet.length; index++ )
      {
         System.out.println( vertexSet[ index ].toString() );
      }
   }
   
   public void dumpEdges()
   {
      int index;
      for ( index = 0; index < edgeSet.length; index++ )
      {
         System.out.println( edgeSet[ index ].toString() );
      }
   }
}