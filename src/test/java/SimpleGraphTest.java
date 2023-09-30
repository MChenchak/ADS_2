import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class SimpleGraphTest {

    @Test
    void pathExists() {
        SimpleGraph graph = new SimpleGraph(6);

        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0,1);
        graph.AddEdge(0,2);
        graph.AddEdge(0,3);
        graph.AddEdge(2,5);

        ArrayList<Vertex> vertices = graph.BreadthFirstSearch(5, 1);
        assertEquals(4, vertices.size());
    }

    @Test
    void noPath() {
        SimpleGraph graph = new SimpleGraph(6);

        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0,1);
        graph.AddEdge(0,2);
        graph.AddEdge(0,3);
        graph.AddEdge(2,5);



        ArrayList<Vertex> vertices1 = graph.BreadthFirstSearch(5, 4);

        assertEquals(0, vertices1.size());
    }

    @Test
    void toItself() {
        SimpleGraph graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(5);
        graph.AddVertex(8);
        graph.AddVertex(4);

        graph.AddEdge(0,1);
        graph.AddEdge(0,2);
        graph.AddEdge(0,3);
        graph.AddEdge(2,5);

        ArrayList<Vertex> vertices = graph.DepthFirstSearch(5, 5);
        assertEquals(1, vertices.size());

        ArrayList<Vertex> vertices1 = graph.BreadthFirstSearch(5, 5);
        assertEquals(1, vertices1.size());
    }

    @Test
    void cycle() {
        SimpleGraph graph = new SimpleGraph(7);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(8);

        graph.AddEdge(0,1);
        graph.AddEdge(1,3);
        graph.AddEdge(3,4);
        graph.AddEdge(5,0);
        graph.AddEdge(3,5);
        graph.AddEdge(6,4);


        ArrayList<Vertex> vertices = graph.DepthFirstSearch(5, 6);
        ArrayList<Vertex> noPath = graph.DepthFirstSearch(2, 4);

        assertEquals(6, vertices.size());
        assertEquals(0, noPath.size());


        ArrayList<Vertex> vertices1 = graph.BreadthFirstSearch(5, 6);
        assertEquals(4, vertices1.size());

        ArrayList<Vertex> noPath1 = graph.BreadthFirstSearch(2, 4);
        assertEquals(0, noPath1.size());

    }

    @Test
    void searchWeak() {
        SimpleGraph graph = new SimpleGraph(9);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);
        graph.AddVertex(8);
        graph.AddVertex(9);

        graph.AddEdge(0,1);
        graph.AddEdge(0,2);
        graph.AddEdge(1,2);
        graph.AddEdge(1,3);
        graph.AddEdge(2,3);
        graph.AddEdge(0,6);
        graph.AddEdge(2,4);
        graph.AddEdge(6,4);
        graph.AddEdge(4,7);
        graph.AddEdge(4,8);
        graph.AddEdge(7,8);
        graph.AddEdge(5,8);

        List<Integer> weakVertices = graph.findWeakVerticesRec(graph.m_adjacency, 0, new ArrayList<>());

    }
}
