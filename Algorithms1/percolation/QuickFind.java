public class QuickFind {
    private int[] id;

    public QuickFind(int N) {
        this.id = new int[N];
        /**
         * set id of each object to itself (N array access)
         */
        for (int i = 0 ; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return  this.id[p] == this.id[q];
    }

    public void union(int p, int q) {
        int pId = this.id[p];
        int qId = this.id[q];
        for (int i = 0; i < this.id.length; i++) {
            if(this.id[i] == pId) this.id[i] = qId;
        }
    }
}
