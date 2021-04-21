class Vertex:
    _default_name = "A"
    _num_vertices = 0

    def __init__( self, name=None ):
        if name is None:
            self._name = Vertex._default_name
            Vertex._default_name = chr( ord( Vertex._default_name[0] ) + 1 )
        else:
            self._name = name
        self._degree = 0
        self._edge_set = []
        self._vertex_number = Vertex._num_vertices
        Vertex._num_vertices += 1

    def get_vertex_number(self):
        return self._vertex_number

    def get_name(self):
        return self._name

    def get_num_vertices(self):
        return self._num_vertices

    def get_degree(self):
        return self._degree

    def increase_degree( self ):
        self._degree += 1

    def get_shortest_neighbor(self, lowest_acceptable_edge ):
        self.sort_edges()
        return self._edge_set[ lowest_acceptable_edge ]

    def add_edge( self, new_edge ):
        self._edge_set.append( new_edge )

    def sort_edges(self):
        list_index = 0
        smallest_weight = 0
        smallest_weight_index = 0
        inner_comparison = 0
        while list_index < len( self._edge_set ):
            smallest_weight_index = list_index
            inner_index = list_index + 1
            while inner_index < len( self._edge_set ):
                smallest_weight = self._edge_set[ smallest_weight_index ].get_weight()
                inner_comparison = self._edge_set[ inner_index ].get_weight()
                if( inner_comparison < smallest_weight ):
                    smallest_weight_index = inner_index
                inner_index += 1
            self.swap_elements( list_index, smallest_weight_index )
            list_index += 1

    def swap_elements( self, one_index, other_index ):
        temp = self._edge_set[ one_index ]
        self._edge_set[ one_index ] = self._edge_set[ other_index ]
        self._edge_set[ other_index ] = temp

    def display_edges(self):
        index = 0
        while index < len( self._edge_set ):
            self._edge_set[ index ].to_string()
            index += 1

    def to_string(self):
        return  "The name of the vertex is: " + self._name

    def equals( self, other_vertex ):
        if self._vertex_number == other_vertex.get_vertex_number():
            return True
        return False

    
