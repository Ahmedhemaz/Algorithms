public class QuickUnion {
    private int[] id;

    public  QuickUnion(int N) {
        this.id = new int[N];
        /**
         * set id of each object to itself (N array access)
         */
        for (int i = 0 ; i < N; i++) {
            id[i] = i;
        }
    }

    /**
     * chase parent pointer until it reaches root (depth of i array access)
     */
    private  int root(int i) {
        while( i != this.id[i])  i = this.id[i] ;
        return  i;
    }

    /**
     * check if p and q have the same root
     */
    public boolean connected(int p, int q) {
        return  this.root(p) == this.root(q);
    }

    /**
     * change root of p to point to root of q (depth of p and q array access)
     */
    public void union(int p, int q) {
        int pRoot = this.root(p);
        int qRoot = this.root(q);
        this.id[pRoot] = qRoot;
    }

    public int delete(int number) {
        this.union(number, number + 1);
        return this.root(number);
    }
}
