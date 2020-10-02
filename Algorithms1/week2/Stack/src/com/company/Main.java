package com.company;

public class Main {

    public static void main(String[] args) {
//	 LinkedListStack<String> lStack = new LinkedListStack<>();
//	 lStack.push("A");
//	 lStack.push("B");
//	 lStack.push("C");
//	 lStack.push("D");
//	 lStack.push("E");
//	 while (lStack.size() > 0) {
//	     System.out.println(lStack.pop());
//     }
		ArrayStack<String> aStack = new ArrayStack<>();
		aStack.push("A");
		aStack.push("B");
		aStack.push("C");
		aStack.push("D");
		aStack.push("E");
		aStack.pop();
		aStack.pop();
		aStack.pop();
		System.out.println(aStack.size());
		while (aStack.size() > 0) {
			System.out.println(aStack.pop());
		}
    }
}
