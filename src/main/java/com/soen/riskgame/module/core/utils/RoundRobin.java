package com.soen.riskgame.module.core.utils;

import com.soen.riskgame.module.core.model.Player;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.Element;

public class RoundRobin<E> {

    @Getter
    private Node<E> tail = null;

    private int size = 0;

    public RoundRobin() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

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

    public void addList(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail) tail = null;
        else tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

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
