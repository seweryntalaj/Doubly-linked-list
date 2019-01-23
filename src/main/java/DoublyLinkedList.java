@SuppressWarnings("unchecked")
public class DoublyLinkedList<T> {

    class Node <T>{
        private T content;
        private Node previous;
        private Node next;

        Node(T content) { this.content = content; }

        public Node previous() { return this.previous; }

        public Node next() { return this.next; }

        public T getContent() { return this.content; }

        public void setPrevious(Node previous) { this.previous = previous; }

        public void setNext(Node next) { this.next = next; }

        @Override
        public String toString() { return content.toString(); }

    }

    private Node head;
    private Node tail;
    private int size = 0;

    public Node head() { return this.head; }

    public Node tail() { return this.tail; }

    public void add(T element) {
        Node newNode = new Node();

        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.setNext(newNode);
            newNode.setPrevious(this.tail);
            this.tail = newNode;
        }

        this.size++;
    }

    public void remove(int index) {
        if (index == 0) {
            this.head = this.head.next();
        } else {
            Node removedNode = findNode(index);
            popNode(removedNode);
        }

        setTail();
        this.size--;
    }

    public void insert(int index, T element) {
        Node insertedNode = new Node(element);

        if (index == 0) {
            insertedNode.setNext(this.head);
            this.head = insertedNode;
        } else {
            Node previousNode = findNode(index).previous();
            Node nextNode = previousNode.next();

            insertedNode.setNext(nextNode);
            insertedNode.setPrevious(previousNode);
            previousNode.setNext(insertedNode);
            nextNode.setPrevious(insertedNode);
        }

        this.size++;

    }

    public Node get(int index) {
        return findNode(index);
    }

    public String toString() {     }

    public Node popNode(Node node) {
        Node precedingNode = node.previous();
        Node nextNode = node.next();

        precedingNode.setNext(nextNode);
        nextNode.setPrevious(precedingNode);
    }

    public void setTail() {
        if (this.head == null) {
            this.tail = null;
            return;
        }

        Node currentNode = this.head;

        while (currentNode.next() != null) {
            currentNode = currentNode.next();
        }

        this.tail = currentNode;
    }

    private Node findNode(int index) {
        int nodeIndex = 0;
        Node currentNode = this.head;

        while (nodeIndex != index) {
            currentNode = currentNode.next();
            nodeIndex++;
        }

        return currentNode;
    }
}
