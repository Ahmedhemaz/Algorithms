public class WeightedQuickUnion {
    private int[] id;
    private int[] size;
    private  int[] maxNumberArray;

    public  WeightedQuickUnion(int N) {
        this.id = new int[N];
        this.size = new int[N];
        this.maxNumberArray = new int[N];
        /**
         * set id of each object to itself (N array access)
         */
        for (int i = 0 ; i < N; i++) {
            this.id[i] = i;
            this.size[i] = 1;
            this.maxNumberArray[i] = i;
        }
    }

    /**
     * chase parent pointer until it reaches root (depth of i array access)
     */
    private  int root(int i) {
        while( i != this.id[i]) {
            id[i] = id[id[i]];
            i = this.id[i];
        }
        return  i;
    }

    /**
     * check if p and q have the same root
     */
    public boolean connected(int p, int q) {
        return  this.root(p) == this.root(q);
    }

    /**
     * link root of smaller tree size to root of larger tree
     * update the size array
     */
    public void union(int p, int q) {
        int pRoot = this.root(p);
        int qRoot = this.root(q);
        if (pRoot == qRoot) return;
        if(this.size[pRoot] < this.size[qRoot]) {
            this.id[pRoot] = qRoot;
            this.size[qRoot] =+ this.size[pRoot];
            this.setMaxNumberToConnectedComponentRoot(qRoot,
                    Math.max(this.maxNumberArray[pRoot], this.maxNumberArray[pRoot]));
        }else{
            this.id[qRoot] = pRoot;
            this.size[pRoot] =+ this.size[qRoot];
            this.setMaxNumberToConnectedComponentRoot(qRoot,
                    Math.max(this.maxNumberArray[pRoot], this.maxNumberArray[pRoot]));
        }
    }

    private void  setMaxNumberToConnectedComponentRoot(int root , int maxNum) {
        this.maxNumberArray[root] = maxNum;
    }

    public int find(int root) {
        return  this.maxNumberArray[root];
    }

}
