/**
 *  平衡快速查找树
 */
public class WeightedQuickUnionUF {
    // 用于存放 代表父结点的 int值, 初始为n, n =1 - N，N代表总数据量
    private int[] parent;
    // 记录每个独立的单元有多少个结点，初始值都为1
    private int[] size;
    // 有多少个独立的联结单元，初始共有N个，N代表总数据量
    private int count;

    public WeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     *
     * 返回总共有多少个单元
     */
    public int count() {
        return count;
    }

    /**
     *  给定一个index p, 查找并返回其root结点
     * @param p
     * @return p
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // 检查所给的index是否在边界内
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    // 判断所给的2个值是否有联结
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // 连接给定的两个值
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // 关键点：如果两个值不在同一个单元，则合并两个单元，
        // 将较小的单元纳入到较大的单元中。最终root为较大单元的root
        // 这么做是为了尽可能的将树打平，缩短查找root的路径
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public static void main(String[] args) {
        // some test code ...
    }

}
