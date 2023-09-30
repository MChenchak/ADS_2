import java.util.*;

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

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        cleanStructure();
        Deque<Integer> deque = new ArrayDeque<>();

        ArrayList<Vertex> resultList = new ArrayList<>();
        resultList.add(vertex[VFrom]);

        vertex[VFrom].Hit = true;
        if (VFrom == VTo)
            return resultList;

        ArrayList<Vertex> bfs = bfs(deque, VFrom, VTo, resultList);
        return bfs.contains(vertex[VTo]) ? bfs : new ArrayList<>();
    }

    private ArrayList<Vertex> bfs(Deque<Integer> q, int curr, int target, ArrayList<Vertex> path) {
        for (int j = 0; j < m_adjacency[curr].length; j++) {
            if (!IsEdge(curr, j)) continue;

            if (!isVisited(j)) {
                if (j == target) {
                    path.add(vertex[j]);
                    return path;
                }
                vertex[j].Hit = true;
                q.push(j);
            }
        }

        if (q.isEmpty()) return path;

        curr = q.pop();
        path.add(vertex[curr]);
        return bfs(q, curr, target, path);
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
        cleanStructure();
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

    private void cleanStructure() {
        for (Vertex v : vertex) {
            v.Hit = false;
        }
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

    public ArrayList<Vertex> WeakVertices() {
        cleanStructure();
        List<Integer> weakIndexes = findWeakVerticesRec(m_adjacency, 0, new ArrayList<>());

        ArrayList<Vertex> result = new ArrayList<>();
        for (int i : weakIndexes) {
            result.add(vertex[i]);
        }

        return result;
    }

    public List<Integer> findWeakVerticesRec(int[][] graph, int vertex, List<Integer> weakVertices) {
        if (vertex == graph.length) {
            return weakVertices;
        }

        boolean isWeak = true;

        for (int j = 0; j < graph.length; j++) {
            if (graph[vertex][j] == 1) {
                for (int k = j + 1; k < graph.length; k++) {
                    if (graph[vertex][k] == 1 && graph[j][k] == 1) {
                        isWeak = false;
                        break;
                    }
                }
            }

            if (!isWeak) {
                break;
            }
        }

        if (isWeak) {
            weakVertices.add(vertex);
        }

        return findWeakVerticesRec(graph, vertex + 1, weakVertices);
    }
}