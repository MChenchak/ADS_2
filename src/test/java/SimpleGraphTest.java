import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {

    @Test
    @DisplayName("Create graph and add vertex")
    void createGraphAndAddVertex() {
        SimpleGraph graph = new SimpleGraph(5);
        assertEquals(0, graph.vertexSize);

        graph.AddVertex(10);
        assertEquals(1, graph.vertexSize);
        assertEquals(10, graph.vertex[0].Value);

        graph.AddVertex(20);
        assertEquals(2, graph.vertexSize);
        assertEquals(20, graph.vertex[1].Value);
    }

    @Test
    @DisplayName("Add when full")
    void addWhenFull() {
        SimpleGraph graph = new SimpleGraph(1);
        assertEquals(0, graph.vertexSize);

        graph.AddVertex(10);
        assertEquals(1, graph.vertexSize);
        assertEquals(10, graph.vertex[0].Value);

        graph.AddVertex(20);
        assertEquals(1, graph.vertexSize);
    }

    @Test
    @DisplayName("is edge")
    void isEdge() {
        SimpleGraph graph = new SimpleGraph(5);
        assertEquals(0, graph.vertexSize);

        graph.AddVertex(10);
        graph.AddVertex(20);

        boolean result = graph.IsEdge(0,1);
        assertFalse(result);
        assertEquals(2, graph.vertexSize);
    }

    @Test
    @DisplayName("add edge")
    void addEdge() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(10);
        graph.AddVertex(20);

        boolean edgeDontExists = graph.IsEdge(0,1);
        assertFalse(edgeDontExists);

        graph.AddEdge(0,1);

        boolean edgeExists = graph.IsEdge(0,1);
        assertTrue(edgeExists);
    }

    @Test
    @DisplayName("delete edge")
    void deleteEdge() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(10);
        graph.AddVertex(20);
        graph.AddEdge(0,1);
        boolean edgeExists = graph.IsEdge(0,1);
        assertTrue(edgeExists);

        graph.RemoveEdge(0,1);

        boolean edgeDontExists = graph.IsEdge(0,1);
        assertFalse(edgeDontExists);
    }


    @Test
    @DisplayName("remove vertex with edges")
    void removeVertexWithEdges() {
        SimpleGraph graph = new SimpleGraph(5);

        graph.AddVertex(10);
        graph.AddVertex(20);
        graph.AddVertex(30);
        graph.AddVertex(40);
        graph.AddVertex(50);

        graph.AddEdge(1,2);
        graph.AddEdge(4,2);

        graph.AddEdge(3,0);
        graph.AddEdge(3,1);
        graph.AddEdge(3,4);

        graph.RemoveVertex(3);

    }

}