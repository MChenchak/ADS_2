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
    }
}