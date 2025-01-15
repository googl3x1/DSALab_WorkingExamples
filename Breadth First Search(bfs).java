//Breadth First Search(bfs):



class Queue_2 {
    LinkedList ll;

    public Queue_2() {
        ll = new LinkedList();
    }

    public boolean isEmpty() {
        return ll.isEmpty();
    }

    public void enqueue(Vertex new_data) {
        ll.insertLast(new_data);
    }

    public Vertex dequeue() {
        return ll.deleteFirst();
    }

    public Vertex front() {
        if (ll.front == null) {
            return null;
        } else {
            return ll.front.data;
        }
    }

    public void printGraphBF(Queue_2 queue) {
        if (queue.front() != null) {
            queue.front().isVisited = true;
            Vertex current = queue.dequeue();
            if (current == null) return;
            System.out.println(current.label);
            for (int i = 0; i < current.neighbour.length; i++) {
                if (!current.neighbour[i].isVisited) {
                    queue.enqueue(current.neighbour[i]);
                }
            }
            printGraphBF(queue);
        }
    }
}

class LinkedList {
    Node front;
    Node rear;

    static class Node {
        Vertex data;
        Node next;

        public Node(Vertex data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void insertLast(Vertex new_data) {
        Node new_node = new Node(new_data);
        if (isEmpty()) {
            front = rear = new_node;
        } else {
            rear.next = new_node;
            rear = new_node;
        }
    }

    public Vertex deleteFirst() {
        if (front == null) return null;
        Vertex temp = front.data;
        front = front.next;
        if (front == null) rear = null;
        return temp;
    }
}

class Vertex {
    String label;
    boolean isVisited;
    Vertex[] neighbour;

    public Vertex(String label) {
        this.label = label;
        this.isVisited = false;
        this.neighbour = new Vertex[0];
    }

    public void addNeighbours(Vertex[] v) {
        this.neighbour = new Vertex[v.length];
        for (int i = 0; i < v.length; i++) {
            neighbour[i] = v[i];
        }
    }
}

class Graph {
    private int v;
    private Edge[] edgelist;

    public Graph(int v) {
        this.v = v;
        this.edgelist = new Edge[0];
    }

    public Vertex createVertex(String label) {
        return new Vertex(label);
    }

    public Edge createEdge(Vertex start, Vertex end) {
        return new Edge(start, end);
    }

    public void createEdgeList(Edge[] edges) {
        this.edgelist = new Edge[edges.length];
        System.arraycopy(edges, 0, edgelist, 0, edges.length);
    }

    public void printGraphBF(Queue_2 queue) {
        if (queue.front() != null) {
            queue.front().isVisited = true;
            Vertex current = queue.dequeue();
            if (current == null) return;
            System.out.println(current.label);
            for (int i = 0; i < current.neighbour.length; i++) {
                if (!current.neighbour[i].isVisited) {
                    queue.enqueue(current.neighbour[i]);
                }
            }
            printGraphBF(queue);
        }
    }
}

class Edge {
    Vertex start;
    Vertex end;

    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
    }
}

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(5);
        Vertex a, b, c, d, e;
        a = graph.createVertex("A");
        b = graph.createVertex("B");
        c = graph.createVertex("C");
        d = graph.createVertex("D");
        e = graph.createVertex("E");

        a.addNeighbours(new Vertex[]{b, d});
        b.addNeighbours(new Vertex[]{a, c});
        c.addNeighbours(new Vertex[]{b});
        d.addNeighbours(new Vertex[]{a, e});
        e.addNeighbours(new Vertex[]{d});

        System.out.println("Printing graph: BFS");
        Queue_2 queue = new Queue_2();
        queue.enqueue(a);
        graph.printGraphBF(queue);
    }
}
