package com.futuristicdevelopers.shamoon.controller.circular_linkedlist;

import java.util.ArrayList;
import java.util.LinkedList;

public class CircularLinkedList<T> {
    private LinkedList<Node<T>> linkedList;

    public CircularLinkedList(){
        linkedList = new LinkedList<>();
    }

    public void addAll(ArrayList<T> items){
        if (items.size() > 0){
            Node<T> first = new Node<>(null, items.get(0), null);
            linkedList.add(first);
            if (items.size() > 1){
                Node<T> last = new Node<>(null, items.get(items.size() - 1), first);
                first.setPrevious(last);
                last.setNext(first);
                if (items.size() == 2){
                    first.setNext(last);
                    linkedList.set(0, first);
                    last.setPrevious(first);
                    linkedList.add(last);
                }else {
                    for (int i = 1; i < items.size() - 1; i++){
                        Node<T> node = new Node<>(
                                linkedList.get(i-1),
                                items.get(i),
                                null
                        );
                        Node<T> nodePrev = node.getPrevious();
                        nodePrev.setNext(node);
                        linkedList.add(node);
                    }
                    Node<T> secondLast = linkedList.getLast();
                    last.setPrevious(secondLast);
                    secondLast.setNext(last);
                    linkedList.add(last);
                }
            }else {
                first.setNext(first);
                first.setPrevious(first);
                linkedList.add(first);
            }
        }
    }

    public int size(){
        return linkedList.size();
    }

    public Node<T> get(int position){
        return linkedList.get(position);
    }
}
