package com.company;

public class Main {

    public static void main(String[] args) {
        LinkedListQueue<String> lStack = new LinkedListQueue<>();
	    lStack.enqueue("A");
	    lStack.enqueue("B");
	    lStack.enqueue("C");
	    lStack.enqueue("D");
	    lStack.enqueue("E");
	    while (lStack.size() > 0) {
			try {
				System.out.println(lStack.dequeue());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
}
