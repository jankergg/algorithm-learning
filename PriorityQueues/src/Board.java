import java.util.LinkedList;

public class Board {
    private int[][] tiles;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = copy(tiles);
    }

    // string representation of this board
    public String toString() {
        StringBuilder str = new StringBuilder(tiles[0].length + "\n");
        for (int[] i : tiles) {
            for (int j : i) {
                str.append(j);
                str.append(' ');
            }
            str.append("\n");
        }
        str.append("\n");
        return str.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int row = 0; row < tiles.length; row++)
            for (int col = 0; col < tiles.length; col++)
                if (isInPlace(row, col)) count++;

        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int row = 0; row < tiles.length; row++)
            for (int col = 0; col < tiles.length; col++)
                sum += calculateDistances(row, col);

        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int row = 0; row < tiles.length; row++)
            for (int col = 0; col < tiles.length; col++)
                if (isInPlace(row, col)) return false;

        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (!(y instanceof Board)) return false;
        Board other = (Board) y;
//        if (other.tiles.length != tiles.length) {
//            return false;
//        }
//        for (int row = 0; row < tiles.length; row++)
//            for (int col = 0; col < tiles.length; col++)
//                if (other.tiles[row][col] != block(row, col)) return false;
//
//        return true;
        return other.toString().equals(toString());
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        LinkedList<Board> nebr = new LinkedList<>();
        int[] space = getSpace();
        int spaceRow = space[0];
        int spaceCol = space[1];

        // if space not in the first row, swap it up
        if (spaceRow > 0) nebr.add(new Board(swap(spaceRow, spaceCol, spaceRow - 1, spaceCol)));
        // if space not in the last row, swap it to the right
        if (spaceRow < dimension() - 1) nebr.add(new Board(swap(spaceRow, spaceCol, spaceRow + 1, spaceCol)));
        if (spaceCol > 0) nebr.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol - 1)));
        if (spaceCol < dimension() - 1) nebr.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol + 1)));

        return nebr;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        for (int row = 0; row < tiles.length; row++)
            for (int col = 0; col < tiles.length - 1; col++)
                if (!isSpace(tile(row, col)) && !isSpace(tile(row, col + 1)))
                    return new Board(swap(row, col, row, col + 1));
        throw new RuntimeException();
    }
    /*
     *  ------------------------------------------------------------------------------------------------------------------------
     *  ------------------------------------------------------------------------------------------------------------------------
     *  Private Methods
     *  ------------------------------------------------------------------------------------------------------------------------
     *  ------------------------------------------------------------------------------------------------------------------------
     *  */

    private boolean isInPlace(int row, int col) {
        int block = tile(row, col);

        return !isSpace(block) && block != goalFor(row, col);
    }

    // mark a plot's position starting from 1
    private int goalFor(int row, int col) {
        return row * dimension() + col + 1;
    }

    // calculate the distance between a given block and the space
    private int calculateDistances(int row, int col) {
        int tile = tile(row, col);
        // 3 6 4
        // 1 7 5
        // 2 0 8
        //  distance =
        return (isSpace(tile)) ? 0 : Math.abs(row - (tile - 1) / dimension()) + Math.abs(col - (tile - 1) % dimension());
    }


    // swap the position of two blocks
    private int[][] swap(int row1, int col1, int row2, int col2) {
        int[][] copy = copy(tiles);
        int tmp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = tmp;

        return copy;
    }

    // copy a given 2-dimension array
    private int[][] copy(int[][] tiles) {
        int[][] copy = new int[tiles.length][tiles.length];
        for (int row = 0; row < tiles.length; row++)
            System.arraycopy(tiles[row], 0, copy[row], 0, tiles.length);

        return copy;
    }

    // get space.
    private int[] getSpace() {
        for (int row = 0; row < tiles.length; row++)
            for (int col = 0; col < tiles.length; col++)
                if (isSpace(tile(row, col))) {
                    return new int[]{row, col};
                }
        throw new RuntimeException();
    }

    // graphically, a member represents a block
    // returns value in the block
    private int tile(int row, int col) {
        return tiles[row][col];
    }

    // check if a given block is space, by checking if the value equals 0
    private boolean isSpace(int block) {
        return block == 0;
    }

}