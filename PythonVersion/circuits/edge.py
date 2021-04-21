
class Edge:

    def __init__( self, vertex1, vertex2, weight ):
        self._vertex1 = vertex1
        self._vertex2 = vertex2
        self._weight = weight


    def get_weight( self ):
        return self._weight

    def get_other_vertex( self, given_vertex ):
        if given_vertex.equals( self._vertex1 ):
            return self._vertex2
        return self._vertex1

    def get_vertex1( self ):
        return self._vertex1

    def get_vertex2( self ):
        return self._vertex2

    def to_string( self ):
        return self._vertex1.get_name() + " to " + self._vertex2.get_name() + ", weight = " + self._weight

    def equals( self, edge_comparison ):
        first_vertex_comparison = self._vertex1.equals( edge_comparison.get_vertex1() ) or self._vertex1.equals( edge_comparison.get_vertex2() )
        second_vertex_comparison = self._vertex2.equals( edge_comparison.get_vertex1() ) or self._vertex2.equals( edge_comparison.get_vertex2() )

        return first_vertex_comparison and second_vertex_comparison


    
