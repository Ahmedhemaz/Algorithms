# Dijstra's Two stacks

    (1+((2+3)*(4*5)))

# Steps:
- value: push onto the value stack
- operator: push onto the operator stack
- left parenthesis: ignore
- right parenthesis: pop operator and two values; push the result of the applying the operator to those values onto the operand stack
# Code
    public class Evaluate {
        public static void main(String[] args) {
            Stack<String> ops = new Stack<String>();
            Stack<Double> vals = new Stack<Double>();
            while (!StdIn.isEmpty()) {
                String s = StdIn.readString();
                if(s.equals("("));
                else if (s.equals("+"))
                ops.push(s);
                else if (s.equals("*"))
                ops.push(s);
                else if (s.equals(")")) {
                    String op = ops.pop();
                    if (op.equals("+")) vals.push(vals.pop() + vals.p   ());
                    else if (op.equals("*")) vals.push(vals.pop()   vals.pop());
                }
                else vals.push(Double.parseDouble(s));
            }
            StdOut.println(vals.pop());
        }
        
    }