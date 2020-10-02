package com.company;

public class LinkedListQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private Integer size = 0;
    public  LinkedListQueue() {
        this.head = this.tail = null;
    }
    private static class Node<T> {
        public Node<T> next;
        public T value;
    }
    public void enqueue(T value) {
        Node<T> newNode = new Node<>();
        if(this.tail == this.head && this. head== null) {
            this.head = this.tail = newNode;
        }else {
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }
        newNode.value = value;
        this.size ++;
    }
    public T dequeue() throws Exception {
        if (this.tail == this.head && this.tail == null) throw new Exception("Queue Already Empty");
        T value = this.head.value;
        if(this.tail == this.head) {
            this.head = this.tail = null;
        }else{
            this.head = this.head.next;
        }
        this.size --;
        return  value;
    }
    public int size() {
        return  this.size;
    }
}
