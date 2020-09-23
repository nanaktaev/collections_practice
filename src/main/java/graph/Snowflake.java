package graph;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.ArrayUtils;

import java.util.*;

@Slf4j
@NoArgsConstructor
public class Snowflake implements GrowingGraph {

    private int depth;
    private int size;
    private Node growingNode;
    private Map<Integer, Node> data;

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return depth;
    }

    @Override
    public void grow(int amountOfNodes) {
        for (int i = 0; i < amountOfNodes; i++) {
            growNode();
        }
        log.debug("{} new nodes has been grown on the graph. " +
                "Total depth = {}, " +
                "Total nodes = {}.", amountOfNodes, depth, size);
    }

    private void growNode() {
        Node node;
        if (data == null) {
            data = new HashMap<>();
            node = new Node(0, 0, null);
            data.put(0, node);
            growingNode = node;
            size++;
        } else {
            if (growingNode.getChildren().size() == 3) {
                growingNode = data.get(growingNode.getId() + 1);
            }
            node = new Node(size, growingNode.getDepth() + 1, growingNode);
            growingNode.getChildren().add(node);
            data.put(size, node);
            depth = node.getDepth();
            size++;
        }
        log.debug("New node. " +
                        "Id = {}, " +
                        "depth = {}, " +
                        "parent = {}, " +
                        "value = {}.",
                node.getId(),
                node.getDepth(),
                (node.getParent() == null) ? 0 : node.getParent().getId(),
                node.getNumbers());
    }

    public int search(int key) {
        for (int i = 0; i < size - 1; i++) {
            Node node = data.get(i);
            ArrayUtils.quickSort(node.getNumbers(), 0, 9);
            int index = ArrayUtils.binarySearch(node.getNumbers(), key, 0, 9);
            if (index != -1) {
                log.info("The key {} has been found on the depth {} " +
                        "in the node number {} " +
                        "and now has index {}.", key, node.getDepth(), i, index);
                return i;
            }
        }
        return -1;
    }

    @Getter
    class Node {

        private int id;
        private int depth;
        private Node parent;
        private ArrayList<Node> children;
        private int[] numbers;

        Node(int id, int depth, Node parent) {
            this.id = id;
            this.depth = depth;
            this.parent = parent;
            this.children = new ArrayList<>();
            this.numbers = ArrayUtils.generateRandomArray();
        }
    }
}
