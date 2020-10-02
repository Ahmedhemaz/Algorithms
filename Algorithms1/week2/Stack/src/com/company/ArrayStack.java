package com.company;

public class ArrayStack<T> {
    private T [] arr;
    private int current;
    private int size;

    public ArrayStack(){
        this.arr = (T[]) new Object[1];
        this.current = 0;
        this.size = 0;
    }
    //push
    public void push(T value) {
        if (this.size == this.arr.length/2) this.resize(this.arr.length *2);
        this.arr[current++] = value;
        this.size++;
    }
    //pop
    public T pop(){
        T item = this.arr[--current];
        this.arr[current] = null;
        this.size --;
        if (this.size == this.arr.length/4) this.resize(this.arr.length/2);
        return item;
    }
    //size
    public int size() {
        return  this.size;
    }

    private void resize(int capacity) {
        T[] tempArr = (T[]) new Object[capacity];
        for (int i =0; i < this.size; i++) {
            tempArr[i] = this.arr[i];
        }
        this.arr = tempArr;
    }

}
