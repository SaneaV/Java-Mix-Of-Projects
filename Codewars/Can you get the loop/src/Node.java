class Node {

    private Node next;

    public Node() {
    }

    public Node(final Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    private void setNext(Node next) {
        this.next = next;
    }

    public static Node createChain(int tailSize, int loopSize) {
        Node root = new Node();

        Node current = root;

        for (int tailPosition = 1; tailPosition <= tailSize; tailPosition++) {
            current.setNext(new Node());
            current = current.getNext();
        }

        Node loopStarter = current;

        for (int loopPosition = 0; loopPosition < loopSize - 1; loopPosition++) {
            current.setNext(new Node());
            current = current.getNext();
        }

        current.setNext(loopStarter);

        return root;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}