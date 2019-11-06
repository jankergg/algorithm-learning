import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkArgs(item);
        linkFirst(item);
    }

    // add the item to the back
    public void addLast(Item item) {
        checkArgs(item);
        linkLast(item);
    }

    // remove and return the item from the front
    public Item removeFirst() {
        final Node<Item> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    // remove and return the item from the back
    public Item removeLast() {
        final Node<Item> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private void linkFirst(Item item) {
        final Node<Item> f = first;
        final Node<Item> newNode = new Node<Item>(null, item, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    private void linkLast(Item item) {
        final Node<Item> l = last;
        final Node<Item> newNode = new Node<Item>(l, item, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

//    private void linkBefore(Item item, Node<Item> node) {
//        final Node<Item> p = node.prev;
//        final Node<Item> newNode = new Node<Item>(null, item, node);
//        node.prev = newNode;
//        if (p == null) {
//            first = newNode;
//        } else {
//            p.next = newNode;
//        }
//        size++;
//    }

    private Item unlinkFirst(Node<Item> front) {
        final Item element = front.item;
        final Node<Item> second = front.next;
        // unlink the first element, cut out all of its relation with others
        front.item = null;
        front.next = null;
        first = second;
        if (second == null)
            last = null;
        else
            second.prev = null;
        size--;
        return element;
    }

    private Item unlinkLast(Node<Item> link) {
        final Item element = link.item;
        final Node<Item> prev = link.prev;
        // unlink the first element, cut out all of its relation with others
        link.item = null;
        link.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            if (item == null) {
                throw new NoSuchElementException("No Such Element!");
            }
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not Supported!");
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private void checkArgs(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument should be an valid Object");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        System.out.println("isEmpty: " + dq.isEmpty());
        dq.addFirst(1);
        System.out.println("size: " + dq.size());
        System.out.println("isEmpty: " + dq.isEmpty());
        dq.addLast(10);
        System.out.println("size: " + dq.size());
        dq.addLast(11);
        System.out.println("removeFirst: " + dq.removeFirst());
        dq.addLast(12);
        System.out.println("removeLast: " + dq.removeLast());
        dq.addLast(13);
        dq.addLast(14);
        dq.addLast(15);

        System.out.println("size: " + dq.size());
        for (int item : dq) {
            System.out.println("item: " + item);
        }
    }

}

