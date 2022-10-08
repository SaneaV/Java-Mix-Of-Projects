import java.util.ArrayList;
import java.util.List;

public class LoopInspector {

    public static int loopSize(Node node) {
        List<Node> list = new ArrayList<>();
        boolean loop = false;
        int loopSize = 0;

        while (!loop) {
            Node currentNode = node.getNext();

            for (int i = 0; i < list.size(); i++) {
                Node tempNode = list.get(i);

                if (currentNode.equals(tempNode)) {
                    loopSize = list.size() - i;
                    loop = true;
                    break;
                }

            }
            list.add(currentNode);
            node = currentNode;
        }
        return loopSize;
    }

    public static void main(String[] args) {
        System.out.println(loopSize(Node.createChain(1, 3)));
    }
}
