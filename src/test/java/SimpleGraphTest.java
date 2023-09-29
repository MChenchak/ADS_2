import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class SimpleGraphTest {

    @Test
    void pathExists() {
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

        ArrayList<Vertex> vertices = graph.DepthFirstSearch(2, 3);

        assertEquals(3, vertices.size());

        ArrayList<Vertex> vertices1 = graph.DepthFirstSearch(2, 3);

        assertEquals(3, vertices1.size());
    }

    @Test
    void noPath() {
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

        ArrayList<Vertex> vertices = graph.DepthFirstSearch(5, 4);

        assertEquals(0, vertices.size());

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
        assertEquals(6, vertices.size());

        ArrayList<Vertex> noPath1 = graph.BreadthFirstSearch(2, 4);
        assertEquals(0, noPath1.size());

    }
}
