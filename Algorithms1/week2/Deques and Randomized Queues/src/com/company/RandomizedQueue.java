package com.company;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item [] arr;
    private Integer current;
    private Integer size;
    // construct an empty randomized queue
    public RandomizedQueue()
    {
        this.arr = (Item[]) new Object[2];
        this.current = 0;
        this.size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return this.size == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return this.size;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null) throw new NullPointerException("Item can't be null");
        if (this.size == this.arr.length/2) this.resize(this.arr.length *2);
        this.arr[current++] = item;
        this.size++;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty");
        if (this.size == this.arr.length/4) this.resize(this.arr.length/2);
        Integer randomIndex = this.generateRandomIndex();
        Item item = this.arr[randomIndex];
        this.arr[randomIndex] = this.arr[--current];
        this.arr[current] = null;
        this.size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty");
        return this.arr[this.generateRandomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item [] arrCopy;
        private Integer currentPos;
        public RandomizedQueueIterator() {
            this.arrCopy = (Item[]) new Object[size];
            this.copyArray();
            StdRandom.shuffle(arrCopy);
            this.currentPos = 0;
        }
        private void copyArray()
        {
            for (int i = 0; i < size; i++) {
                arrCopy[i] = arr[i];
            }
        }
        public boolean hasNext() {
            return this.currentPos < size;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("RandomizedQueue Is Empty or There Is No More Elements To Get");
            return this.arrCopy[this.currentPos++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Unsupported method");
        }
    }

    private void resize(int capacity) {
        Item[] tempArr = (Item[]) new Object[capacity];
        for (int i = 0; i < this.size; i++) {
            tempArr[i] = this.arr[i];
        }
        this.arr = tempArr;
    }

    private Integer generateRandomIndex() {
        return StdRandom.uniform(this.size);
    }
    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
        }

        for (Integer i : randomizedQueue)
        {
            System.out.println(i);
        }

        for (int i = 0; i < 11; i++) {
            randomizedQueue.dequeue();
        }

    }

}