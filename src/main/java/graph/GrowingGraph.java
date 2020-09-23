package graph;

public interface GrowingGraph {

    int search(int key);

    void grow(int amountOfNodes);

    int size();

    int depth();
}
