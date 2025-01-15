//Depth First Search(dfs):


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

class LinkedListStack {
    LinkedList ll = new LinkedList();

    public void push(Vertex element) {
        ll.insertFirst(element);
    }

    public void pop() {
        ll.deleteFirst();
    }

    public void displayStack() {
        ll.displayList();
    }

    public Vertex peek() {
        return ll.front != null ? ll.front.data : null;
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

    public void insertFirst(Vertex new_data) {
        Node new_node = new Node(new_data);
        if (isEmpty()) {
            front = rear = new_node;
        } else {
            new_node.next = front;
            front = new_node;
        }
    }

    public Vertex deleteFirst() {
        if (front == null) return null;
        Vertex temp = front.data;
        front = front.next;
        if (front == null) rear = null;
        return temp;
    }

    public void displayList() {
        Node current = front;
        while (current != null) {
            System.out.println(current.data.label);
            current = current.next;
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

    public void printGraphDF(LinkedListStack s) {
        if (s.peek() == null) return;
        s.peek().isVisited = true;
        Vertex current = s.peek();
        System.out.println(current.label);
        s.pop();
        
        for (int i = 0; i < current.neighbour.length; i++) {
            if (!current.neighbour[i].isVisited) {
                s.push(current.neighbour[i]);
                printGraphDF(s);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");

        v1.addNeighbours(new Vertex[]{v2, v3});
        v2.addNeighbours(new Vertex[]{v1, v4});
        v3.addNeighbours(new Vertex[]{v1});
        v4.addNeighbours(new Vertex[]{v2});

        Graph graph = new Graph(4);
        graph.createEdgeList(new Edge[]{
            new Edge(v1, v2),
            new Edge(v2, v4),
            new Edge(v1, v3),
            new Edge(v3, v4)
        });

        LinkedListStack stack = new LinkedListStack();
        stack.push(v1);

        System.out.println("DFS Traversal starting from A:");
        graph.printGraphDF(stack);
    }
}

