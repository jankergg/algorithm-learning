public class Puzzle {

    public static void main(String[] args) {
        // write your code here

    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void exch(int k, int j) {

    }

    private boolean less(int k, int j) {
        return k > j;
    }
}
