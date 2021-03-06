import graph
import random

#FINAL VARIABLES
MIN_VERTICES = 4
VERTEX_RANGE = 5
MAX_VERTEX_WEIGHT = 999

def input_programmed_graph():
    sorting_graph = graph.Graph()
    return sorting_graph

#creates a complete graph
def create_random_graph():
    index = 0
    vert_num = random.randint( MIN_VERTICES, MIN_VERTICES + VERTEX_RANGE )
    edge_num = ( vert_num * (vert_num - 1))/2

    sorting_graph = graph.Graph()

    print( "There are %s vertices", vert_num )
    #adding vertices
    index = 0
    while index < vert_num:
        sorting_graph.add_vertex( None )
        index += 1

    #creating the edges
    index = 0
    while index < vert_num:
        vert1 = sorting_graph.find_meant_vertex( index )
        inner_index = index + 1
        while inner_index < sorting_graph.get_num_vertices():
            vert2 = sorting_graph.find_meant_vertex( inner_index )
            weight = random.randint( 1, MAX_VERTEX_WEIGHT )
            sorting_graph.add_edge( vert1, vert2, weight )
            inner_index += 1
        index += 1
    return sorting_graph

def main():

    print( "Did you enter a graph into input_programmed_graph()?" )
    response = input( "Any answer other than yes will create a random graph\n" )

    if response != "yes":
        print( "The graph will have between %s and %s vertices", (MIN_VERTICES, MIN_VERTICES + VERTEX_RANGE) )
        sorting_graph = create_random_graph()

    else:
        sorting_graph = input_programmed_graph()

    print( "Graph creation complete!" )
    sorting_graph.dump_vertices()
    sorting_graph.dump_edges()

    print( "We will now sort by nearest neightbor!" )
    response = input( "Which vertex would you like to start at?\n" )
    starting_vertex = sorting_graph.find_meant_vertex( response )

    weightNN = sorting_graph.run_nearest_neighbor( starting_vertex )
    print( "Nearest Neighbot Weight: %s ", ( weightNN ))

    print( "\nWe will now sort by sorted edges!" )
    weightSE = sorting_graph.run_sorted_edges()
    print( "Sorted Edges Weight: %s", ( weightSE ))


if __name__ == "__main__":
    main()
