package com.company;

public class LinkedListStack<T> {
    private Node<T> head;
    private Integer size = 0;
    public  LinkedListStack() {
        this.head = null;
    }
    private static class Node<T> {
        public Node<T> next;
        public T value;
    }
    public void push(T value) {
        Node<T> newNode = new Node<>();
        newNode.next = this.head;
        this.head = newNode;
        newNode.value = value;
        this.size ++;
    }
    public T pop() {
        T value = head.value;
        this.head = this.head.next;
        this.size --;
        return  value;
    }
    public int size() {
        return  this.size;
    }
}
