package com.repo.assignment.util;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

    private final int vertices;
    private final LinkedList<Integer>[] adjacent;

    // Parametrised Constructor
    // Parameters => vertex (type: int)
    public Graph(int vertex) {
        vertices = vertex;
        adjacent = new LinkedList[vertex];
        for (int i = 0; i < vertex; ++i)
            adjacent[i] = new LinkedList();
    }

    //Function to add an edge to the graph
    public void addEdge(int vertex, int w) {
        adjacent[vertex].add(w);
    }

    // BFS traversal from a given source s
    public Boolean isReachable(int start, int end) {
        boolean[] visited = new boolean[vertices];

        // Create a queue to track BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[start] = true;
        queue.add(start);
        
        Iterator<Integer> i;
        while (queue.size() != 0) {
            start = queue.poll();

            int nextVertex;
            i = adjacent[start].listIterator();
            while (i.hasNext()) {
                nextVertex = i.next();
                if (nextVertex == end)
                    return true;
                if (!visited[nextVertex]) {
                    visited[nextVertex] = true;
                    queue.add(nextVertex);
                }
            }
        }
        return false;
    }

}
