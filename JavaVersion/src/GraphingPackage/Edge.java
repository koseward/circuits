package GraphingPackage;

public class Edge
{
   private Vertex vertex1;
   private Vertex vertex2;
   private int weight;
   
   /**
    * Creates a new Edge
    * <p>
    * Note: Adds this edge to the edge sets of its component vertices
    * @param vertex1 The first vertex
    * @param vertex2 The second vertex
    * @param weight the weight of the Edge
    */
   public Edge ( Vertex vertex1, Vertex vertex2, int weight ) {
      this.vertex1 = vertex1;
      this.vertex2 = vertex2;
      this.weight = weight;
   }
   
   /**
    * Returns the weight of the Edge
    * @return The weight of the Edge
    */
   public int getWeight() {
      return weight;
   }
   
   /**
    * Gets the other vertex on the edge
    * @param firstVertex The starting vertex
    * @return The ending vertex
    */
   public Vertex getOtherVertex( Vertex firstVertex )
   {

      
      if ( firstVertex.equals( vertex1 ) )
      {
         return vertex2;
      }
      
      return vertex1;
   }
   
   public Vertex getVertex1()
   {
      return vertex1;
   }
   
   public Vertex getVertex2()
   {
      return vertex2;
   }
   
   /**
    * Returns a String dump of the Edge
    */
   public String toString()
   {
      return vertex1.getName() + " to " + vertex2.getName() +
            ", weight = " + weight;
   }
   
   public boolean equals( Edge comparison )
   {
      boolean firstVert = comparison.vertex1.equals(vertex1) || comparison.vertex1.equals(vertex2);
      boolean secondVert = comparison.vertex2.equals(vertex1) || comparison.vertex2.equals(vertex2);
      
      return firstVert && secondVert;
   }
}