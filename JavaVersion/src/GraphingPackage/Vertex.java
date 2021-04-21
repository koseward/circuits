package GraphingPackage;

import java.util.ArrayList;

public class Vertex
{
   private String name;
   private int degree;
   private int vertexNumber;
   private ArrayList<Edge> edgeSet;
   
   private static char defaultName = 'A';
   private static int numVertices = 0;
   

   /**
    * Creates a new vertex
    * @param name The name of the Vertex
    */
   public Vertex ( String name )
   {
      this.name = name;
      degree = 0;
      edgeSet = new ArrayList<>();
      setVertexNumber();
   }
   
   public Vertex () {
      name = "" + defaultName;
      defaultName++;
      degree = 0;
      edgeSet = new ArrayList<>();
      setVertexNumber();
   }
   
   /**
    * Sets the vertex number and increments the amount of vertices
    */
   private void setVertexNumber()
   {
      vertexNumber = numVertices;
      numVertices++;
   }
   
   /**
    * Returns the vertex number
    * @return the vertex number
    */
   public int getVertexNumber()
   {
      return vertexNumber;
   }
   
   /**
    * Gets the name of the vertex
    * @return the name of the vertex
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * Returns the number of vertices
    * @return the number of vertices
    */
   public int getNumvertices()
   {
      return numVertices;
   }
   
   /**
    * Returns the degree of the Vertex
    * @return the degree of the vertex
    */
   public int getDegree()
   {
      return degree;
   }
   
   /**(
    * Increases the degree of the vertex
    */
   public void increaseDegree()
   {
      degree++;
   }
   
   /**
    * Returns the shortest neighbor from this vertex
    * @param lowestAcceptableEdge The amount of times the lowest edge has been searched for
    * @return The shortest edge
    */
   public Edge getShortestNeighbor( int lowestAcceptableEdge )
   {
      sortEdges();
      return edgeSet.get( lowestAcceptableEdge );
   }
   
   /**
    * Adds an Edge to the edge list
    * @param newEdge The edge to be added
    */
   public void addEdge( Edge newEdge )
   {
      edgeSet.add( newEdge );
   }
   
   /**
    * Sorts the edges from least weight to the most using selection sort
    */
   public void sortEdges()
   {
      int listIndex, innerIndex, smallestWeight, innerComparison, smallestWeightIndex;
      for ( listIndex = 0; listIndex < edgeSet.size() - 1; listIndex++ )
      {
         smallestWeightIndex = listIndex;
         for ( innerIndex = listIndex + 1; innerIndex < edgeSet.size(); innerIndex++ )
         {
            smallestWeight = edgeSet.get( smallestWeightIndex ).getWeight();
            innerComparison = edgeSet.get( innerIndex ).getWeight();
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
      Edge temp = edgeSet.get( oneIndex );
      
      edgeSet.set( oneIndex, edgeSet.get( otherIndex ) );
      
      edgeSet.set( otherIndex, temp );
   }
   
   /**
    * Prints out all the edges of the vertex
    * <p>
    * Note: used for testing purposes
    */
   public void displayEdges()
   {
      int index;
      for ( index = 0; index < edgeSet.size(); index++ )
      {
         System.out.println( edgeSet.get(index).toString() );
      }
   }
   
   /**
    * Dumps the name of the Vertex
    */
   public String toString()
   {
      return "The name of the vertex is: " + name;
   }
   
   public boolean equals( Vertex otherVertex )
   {
      return vertexNumber == otherVertex.vertexNumber;
   }
}