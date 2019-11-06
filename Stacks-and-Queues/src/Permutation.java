import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQS = new RandomizedQueue<>();
        int k = 0;

        while (!StdIn.isEmpty()) {
            randomizedQS.enqueue(StdIn.readString());
        }

        if (args.length > 0) {
            k = Integer.parseInt(args[0]);
        }

        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQS.sample());
        }
//        System.out.println(StdRandom.uniform(1));
    }
}
