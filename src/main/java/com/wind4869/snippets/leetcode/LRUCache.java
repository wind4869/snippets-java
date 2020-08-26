package com.wind4869.snippets.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache, https://leetcode-cn.com/problems/lru-cache/
 *
 * @author wind4869
 * @since 1.0.0
 */
public class LRUCache {
    private final Map<Integer, Node<Item>> map;
    private final MyLinkedList<Item> list;
    private final int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        list = new MyLinkedList<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node<Item> node = map.get(key);
            int value = node.item.value;
            moveToFirst(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            moveToFirst(key, value);
            return;
        }

        if (map.size() == capacity) {
            Item item = list.removeLast();
            map.remove(item.key);
        }

        addToFirst(key, value);
    }

    private void moveToFirst(int key, int value) {
        Node<Item> node = map.get(key);
        Item item = list.unlink(node);
        map.remove(item.key);
        addToFirst(key, value);
    }

    private void addToFirst(int key, int value) {
        Item item = new Item(key, value);
        Node<Item> node = list.addFirst(item);
        map.put(key, node);
    }

    private static class Item {
        private final int key;
        private final int value;

        public Item(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private static class MyLinkedList<E> {
        private Node<E> first, last;

        public Node<E> addFirst(E e) {
            final Node<E> f = first;
            final Node<E> newNode = new Node<>(null, e, f);
            first = newNode;
            if (f == null)
                last = newNode;
            else
                f.prev = newNode;
            return newNode;
        }

        public E removeLast() {
            final Node<E> l = last;
            if (l == null)
                throw new RuntimeException("No such element");

            final E element = l.item;
            final Node<E> prev = l.prev;
            l.item = null;
            l.prev = null;
            last = prev;
            if (prev == null)
                first = null;
            else
                prev.next = null;
            return element;
        }

        private E unlink(Node<E> x) {
            final E element = x.item;
            final Node<E> next = x.next;
            final Node<E> prev = x.prev;

            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                x.prev = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }

            x.item = null;
            return element;
        }
    }
}
