import java.util.ArrayList;
import java.util.Stack;

class Vertex {
    public int Value;
    public boolean Hit;

    public Vertex(int val) {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;
    int vertexSize;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];


    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        Stack<Integer> path = new Stack<>();

        path.push(VFrom);
        vertex[VFrom].Hit = true;
        dfs(VTo, path);

        ArrayList<Vertex> result = new ArrayList<>();

        for (int i : path) {
            result.add(vertex[i]);
        }

        return result;
    }

    public Stack<Integer> dfs(int target, Stack<Integer> stk) {
        if (stk.empty()) return stk;
        int curr = stk.peek();
        if (curr == target) return stk;

        for (int j = 0; j < m_adjacency[curr].length; j++) {
            if (!IsEdge(curr, j)) continue;

            if (!isVisited(j)) {
                stk.push(j);
                vertex[j].Hit = true;
                return dfs(target, stk);
            }
        }
        stk.pop();
        return dfs(target, stk);
    }

    private boolean isVisited(int vertexIndex) {
        return vertex[vertexIndex].Hit;
    }

    public void AddVertex(int value) {
        // Добавление новой вершины в незанятую позицию vertex
        if (vertexSize == max_vertex) return;

        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value);
                break;
            }
        }

        vertexSize++;
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        vertex[v] = null;

        for (int row = 0; row < max_vertex; row++) {
            m_adjacency[row][v] = 0;
        }

        for (int column = 0; column < max_vertex; column++) {
            m_adjacency[v][column] = 0;
        }

    }


    public boolean IsEdge(int v1, int v2) {
        return m_adjacency[v1][v2] == 1 && m_adjacency[v2][v1] == 1;
    }

    public void AddEdge(int v1, int v2) {
        // добавление ребра между вершинами v1 и v2
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        // удаление ребра между вершинами v1 и v2
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }
}