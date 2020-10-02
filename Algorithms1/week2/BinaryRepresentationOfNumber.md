# Binary Representation Of Given Number

    int n = 50;

    Stack<Integer> stack = new Stack<Integer>();
    while (n > 0) {
        stack.push(n % 2);
        n = n / 2;
    }

    for (int digit : stack) {
        StdOut.print(digit);
    }

    StdOut.println();
# Explanation

- first step 0  => 25
- second step 1 => 12.5 => 12
- third step 0 => 6
- forth step 0 => 3
- fifth step 1 => 1.5 => 1
- sixth step 1 => 1 => 0
- adding them to stack will be [0,1,0,0,1,1]
- as we iterate from right to left we will get the binary representation of 50

        110010 => [32][16][0][0][2][0] = 32+16+2 = 50