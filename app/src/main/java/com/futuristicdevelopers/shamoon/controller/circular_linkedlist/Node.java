package com.futuristicdevelopers.shamoon.controller.circular_linkedlist;

public class Node<T> {
    private Node<T> previous;
    private T value;
    private Node<T> next;

    public Node(Node<T> previous, T value, Node<T> next) {
        this.previous = previous;
        this.value = value;
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
