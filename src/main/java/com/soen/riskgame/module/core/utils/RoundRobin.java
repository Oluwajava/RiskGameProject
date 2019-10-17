package com.soen.riskgame.module.core.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * Round Robin class in to implement round robin function in the game
 *To Automatically assign the Armies on "PlaceAlL" command this function is used
 * @param <E>
 */
public class RoundRobin<E> {

    private Node<E> tail = null;

    private int size = 0;

    /**
     * Default Constructor
     */
    public RoundRobin() {
    }

    /**
     * Method to retutn size
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Method to chcek if the size is empty
     * @return size as Zero
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *Method to check first element
     * @return first element in list
     */
    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }

    /**
     * Method to return the last element
     * @return last element in the list
     */
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    /**
     * Rotate the tail
     */
    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

    /**
     * Method to add first element
     * @param e
     */
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    /**
     *Method to addList
     * @param e
     */
    public void addList(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    /**
     * Method to remove first element from list
     * @return next element in the list
     */
    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail) tail = null;
        else tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

    /**
     * Class represent each element that are base for Round robin function
     * @param <E>
     */
    private static class Node<E> {

        @Getter
        private E element;

        @Getter
        @Setter
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

    }
}
