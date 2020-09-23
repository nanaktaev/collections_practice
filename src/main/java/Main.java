import graph.GrowingGraph;
import graph.Snowflake;
import lombok.extern.slf4j.Slf4j;
import utils.CommandReader;

@Slf4j
public class Main {

    public static void main(String[] args) {
        int amountOfNodes;
        int key;
        int nodeId;

        System.out.println("Enter the desired amount of nodes in the graph:");
        amountOfNodes = CommandReader.readNumericValue();
        System.out.println("Enter the key to search for:");
        key = CommandReader.readNumericValue();

        GrowingGraph graph = new Snowflake();
        graph.grow(amountOfNodes);

        nodeId = graph.search(key);

        if (nodeId == -1) {
            System.out.println("The key has not been found in the graph.");
        } else {
            System.out.println("The key has been found in the node number " + nodeId + ".");
        }
    }
}

