import vertex
import edge

class Graph:
    #final variable
    VERTEX_VISITED = 2

    #class variable
    edge_index = 0
    vertex_index = 0

    def __init__( self ):
        self.edge_set = []
        self.vertex_set = []

    def add_edge( self, vertex1, vertex2, weight ):
        new_edge = edge.Edge( vertex1, vertex2, weight )
        self.edge_set.append( new_edge )
        vertex1.add_edge( new_edge )
        vertex1.increase_degree()
        vertex2.add_edge( new_edge )
        vertex2.increase_degree()

    def add_vertex( self, name ):
        new_vertex = vertex.Vertex( name )
        self.vertex_set.append( new_vertex )

    def get_num_vertices( self ):
        return len( self.vertex_set )

    def get_num_edges( self ):
        return len( self.edge_set )

    def sort_edges( self ):
        list_index = 0
        while list_index < len(self.edge_set) - 1:
            smallest_weight_index = list_index
            inner_index = list_index + 1
            while inner_index < len(self.edge_set):
                smallest_weight = self.edge_set[ smallest_weight_index ].get_weight()
                comparison = self.edge_set[ inner_index ].get_weight()
                if comparison < smallest_weight:
                    smallest_weight_index = inner_index
                inner_index += 1

            self.swap_elements( list_index, smallest_weight_index )    
            list_index += 1

    def swap_elements( self, one_index, other_index ):
        temp = self.edge_set[ one_index ]

        self.edge_set[ one_index ] = self.edge_set[ other_index ]

        self.edge_set[ other_index ] = temp

    def run_sorted_edges( self ):

        vertices_visited = [0] * len(self.vertex_set)
        self.sort_edges()
        edge_counter = 0
        edge_weight = 0
        all_vertices_visited = False

        while not all_vertices_visited:

            acceptable_edge = False

            while not acceptable_edge:
                
                curr_edge = self.edge_set[ edge_counter ]
                
                vert1_num = curr_edge.get_vertex1().get_vertex_number()
                vert1_visits = vertices_visited[ vert1_num ]
                vert2_num = curr_edge.get_vertex2().get_vertex_number()
                vert2_visits = vertices_visited[ vert2_num ]

                graph_will_break = self.is_edge_usable( vertices_visited, vert1_num, vert2_num )
                if vert1_visits < self.VERTEX_VISITED and vert2_visits < self.VERTEX_VISITED and not graph_will_break:
                    acceptable_edge = True
                    edge_weight += curr_edge.get_weight()
                    vertices_visited[ vert1_num ] += 1

                    vertices_visited[ vert2_num ] += 1

                edge_counter += 1

            all_vertices_visited = self.are_all_vertices_visited( vertices_visited )

        return edge_weight

    def is_edge_usable( self, vertices_visited, vert1_num, vert2_num ):

        vert1_val = vertices_visited[ vert1_num ]
        vert2_val = vertices_visited[ vert2_num ]
        vert_finished_count = 0
        index = 0

        while index < len(vertices_visited):
            if vertices_visited[ index ] == 1 and index != vert1_num and index != vert2_num:
                return False
            if vertices_visited[ index ] == self.VERTEX_VISITED:
                vert_finished_count += 1
            index += 1

        if vert_finished_count == len(self.vertex_set) - 2:
            return False
        if vert1_val == 1 and vert2_val == 1:
            return True
        return False

    def run_nearest_neighbor( self, starting_vertex ):

        curr_vert = starting_vertex;
        other_vert = None
        short_edge = None
        vertices_visited = [0] * len(self.vertex_set)
        edge_weight = 0
        all_vertices_visited = False

        vertices_visited[ starting_vertex.get_vertex_number() ] += 2

        while not all_vertices_visited:

            acceptable_edge = False
            shortest_edge_tries = 0
            
            while not acceptable_edge:
                
                short_edge = curr_vert.get_shortest_neighbor( shortest_edge_tries )
                other_vert = short_edge.get_other_vertex( curr_vert )
                other_vert_num = other_vert.get_vertex_number()

                acceptable_edge = vertices_visited[ other_vert_num ] != self.VERTEX_VISITED

                if acceptable_edge:

                   vertices_visited[ other_vert_num ] += self.VERTEX_VISITED
                   edge_weight += short_edge.get_weight()
                   curr_vert = other_vert
                   all_vertices_visited = self.are_all_vertices_visited( vertices_visited )

                   if all_vertices_visited:
                       edge_weight += self.get_starting_vertex( curr_vert, starting_vertex ).get_weight()

                shortest_edge_tries += 1
                
        return edge_weight


    def get_starting_vertex( self, curr_vert, starting_vertex ):

        compared_edge = edge.Edge( curr_vert, starting_vertex, 0 )
        index = 0
        while index < len(self.edge_set):
            if compared_edge.equals( self.edge_set[ index ] ):
                return self.edge_set[ index ]
            index += 1
        return None

        
    def are_all_vertices_visited( self, vertices_visited ):

        index = 0
        while index < len(vertices_visited):

            if vertices_visited[ index ] < self.VERTEX_VISITED:
                return False
            index += 1
        return True

    def find_meant_vertex( self, name ):

        if type( name ) is int:
            return self.vertex_set[ name ]
        
        index = 0

        while index < len(self.vertex_set):

            vertex_name = self.vertex_set[ index ].get_name()
            if vertex_name == name:
                return self.vertex_set[ index ]
            index += 1

        return None

    def dump_vertices( self ):

        print ( self.vertex_set )

    def dump_edges( self ):

        print ( self.edge_set )
