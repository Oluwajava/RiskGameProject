package com.soen.riskgame.module.core.utils;

import lombok.Getter;
import lombok.Setter;


/**
 * Round Robin class in to implement round robin function in the game
 *To Automatically assign the Armies on "PlaceAlL" command this function is used
 * @param <E>
 * @author John
 */
public class RoundRobin<E> {

    @Getter
    private Node<E> tail = null;
    /**
     * size variable
     */
    private int size = 0;
    /**
     * Default Constructor
     */
    public RoundRobin() {
    }
    /**
     * Method to return size
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

    public void setElement(E e) {
        tail.element = e;
    }

    /**
     * method to delete node
     * @param key
     */
    public void deleteNode(E key) {
        Node head = getTail();
        if (head == null){}

        Node curr = head, prev = new Node(null, null);
        while (curr.getElement() != key) {
            if (curr.next == head) {
                //not found
                break;
            }

            prev = curr;
            curr = curr.next;
        }

        if (curr.next == head) {
            head = null;
        }

        if (curr == head) {
            prev = head;
            while (prev.next != head)
                prev = prev.next;
            head = curr.next;
            prev.next = head;
        } else if (curr.next == head) {
            prev.next = head;
        } else {
            prev.next = curr.next;
        }
        tail = head;
        size--;
    }
    /**
     * Class represent each element that are base for Round robin function
     * @param <E>
     */
    public static class Node<E> {

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
