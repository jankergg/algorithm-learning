import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int DEFAULT_CAPACITY = 20;
    private static final int MIN_CAPACITY = 50;
    private Item[] list;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        list = (Item[]) new Object[DEFAULT_CAPACITY];
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        checkArgs(item);
        checkCapacity();
        if (size == 0) {
            list[size] = item;
            size++;
        } else {
            int randmIndex = StdRandom.uniform(size);
            Item oldVal = list[randmIndex];
            list[randmIndex] = item;
            list[size] = oldVal;
            size++;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // exchange the last element with the selected one
        int rdmIndex = StdRandom.uniform(size);
        Item i = list[rdmIndex];
        list[rdmIndex] = list[size - 1];
        list[size - 1] = null;
        size--;
        return i;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list[StdRandom.uniform(size)];
    }

    private void checkCapacity() {
        // if size grows up to 2/3 of list.length
        if (list.length / 2 - size < 0) {
            grow(MIN_CAPACITY);
        }
    }

    /**
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = list.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        // minCapacity is usually close to size, so this is a win:
        Item[] newArray = (Item[]) new Object[newCapacity];
        for (int i = 0; i < list.length; i++) {
            newArray[i] = list[i];
        }
        list = newArray;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private int cursor;
        private int[] randomIndices;

        public ListIterator() {
            randomIndices = new int[size];
            for (int i = 0; i < size; i++) {
                randomIndices[i] = i;
            }
            StdRandom.shuffle(randomIndices);
        }

        @Override
        public boolean hasNext() {
            return size > 0 && cursor < size - 1;
        }

        @Override
        public Item next() {
            // boundary check
            if (cursor == size - 1 || size == 0) {
                throw new NoSuchElementException();
            }
            cursor++;
            return list[randomIndices[cursor]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not Supported!");
        }
    }

    private void checkArgs(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument should be an valid Object");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        System.out.println("isEmpty: " + rq.isEmpty());
        System.out.println("Size: " + rq.size());
        rq.enqueue(91);
        rq.enqueue(31);
        System.out.println("Sample: " + rq.sample());
        System.out.println("Size: " + rq.size());
        rq.enqueue(3);
        System.out.println("Dequeue------: " + rq.dequeue());
        rq.enqueue(5);
//        System.out.println("Dequeue------: " + rq.dequeue());
        rq.enqueue(19);
//        System.out.println("Dequeue------: " + rq.dequeue());
//        System.out.println("Dequeue------: " + rq.dequeue());
        rq.enqueue(27);
        rq.enqueue(39);
//        System.out.println("Dequeue------: " + rq.dequeue());
        rq.enqueue(1);
//        System.out.println("Dequeue------: " + rq.dequeue());
//        System.out.println("Dequeue------: " + rq.dequeue());
        rq.enqueue(99);
//        System.out.println("Dequeue------: " + rq.dequeue());
//        System.out.println("Dequeue------: " + rq.dequeue());
        System.out.println("Size: " + rq.size());
//        rq.print();
        for (int item : rq
        ) {
            System.out.println("--------------------" + item);
        }
    }

}
