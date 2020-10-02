package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private Integer size;
    // construct an empty deque
    public Deque(){
        this.head = this.tail = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return  (this.head == this.tail) && (this.tail == null);
    }

    // return the number of items on the deque
    public int size(){
        return  this.size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new NullPointerException("Item can't be null");
        if (this.isEmpty()) {
            this.head = this.tail = new Node<>();
        } else {
            this.head.previous = new Node<>();
            this.head.previous.next = this.head;
            this.head = this.head.previous;
        }
        this.head.value = item;
        this.size ++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) throw new NullPointerException("Item can't be null");
        if (this.isEmpty()) {
            this.head = this.tail = new Node<>();
        } else {
            this.tail.next = new Node<>();
            this.tail.next.previous = this.tail;
            this.tail = this.tail.next;
        }
        this.tail.value = item;
        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        Item item = this.head.value;
        if (this.head == this.tail) {
            this.head = this.tail = null;
        }else {
            this.head = this.head.next;
            this.head.previous = null;
        }
        this.size --;
        return  item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        Item item = this.tail.value;
        if (this.head == this.tail) {
            this.head = this.tail = null;
        }else {
            this.tail = this.tail.previous;
            this.tail.next = null;
        }
        this.size --;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private static class Node<Item> {
        Node<Item> next;
        Node<Item> previous;
        Item value;
    }

    private class ListIterator implements Iterator<Item>{
        private Node<Item> current = head;

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("Deque Is Empty or There Is No More Elements To Get");
            Item item = this.current.value;
            this.current = this.current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(4);
        deque.addLast(10);
        deque.addLast(101);
        deque.removeFirst();
        deque.removeLast();
        for (Integer integer : deque) {
            System.out.println(integer);
        }
    }

}