package com.mycompany.app;


import java.util.Stack;

/**
 * Implement a Queue using two stacks
 * <p>
 * (A queue is first in first out)
 */
public class Queue<T> {

    private Stack<T> in;
    private Stack<T> out;

    public Queue() {
        in = new Stack<T>();
        out = new Stack<T>();
    }

    public void add(T value) {
        in.add(value);
    }

    public T get() {
        if (out.isEmpty()) {
            int size = in.size();
            for (int index = 0; index < size; index++){
                out.add(in.pop());
            }
        }
        return out.pop();
    }
}