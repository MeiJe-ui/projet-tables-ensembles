package fr.istic.pra.l3map.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import fr.istic.pra.lang.L3Collection;

public class L3HashSet<T> implements L3Collection<T> {
    public class L3HashSetIterator implements Iterator<T> {
        private int bucketIndex = 0;
        private Iterator<T> bucketIterator = null;

        public L3HashSetIterator() {
            // TODO : compléter le constrcuteur de L3HashSetIterator()
        }

        @Override
        public boolean hasNext() {
            // TODO : compléter la méthode hasNext
            return false;
        }

        @Override
        public T next() {
            // TODO : compléter la méthode next()
            return null;
        }

        @Override
        public void remove() {
            // TODO : compléter la méthode remove()
        }
    }


    private static final int DEFAULT_CAPACITY = 16;
    private ArrayList<T>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public L3HashSet() {
        // TODO : compléter le constrcuteur de L3HashSet
    }

    public L3HashSet(L3HashSet<T> set) {
        // TODO : compléter le constructeur de recopie de L3HashSet
    }

    private int hashIndex(T value) {
        // TODO : compléter la méthode hashIndex(value)
        return -1;
    }

    @Override
    public boolean contains(T value) {
        // TODO : compléter la méthode contains(value)
        return false;
    }

    @Override
    public void add(T value) {
        // TODO : compléter la méthode add(value)
    }

    @Override
    public void remove(T value) {
        // TODO : compléter la méthode remove(value)
    }

    @Override
    public int size() {
        // TODO : compléter la méthode size()
        return -1;
    }

    @Override
    public boolean isEmpty() {
        // TODO : compléter la méthode isEmpty()
        return false;
    }

    @Override
    public void clear() {
        // TODO : compléter la méthode clear()
    }

    @Override
    public boolean isIncludedIn(L3Collection<T> set2) {
        // TODO : compléter la méthode isIncludedIn(set)
        return false;
    }

    @Override
    public void intersection(L3Collection<T> otherSet) {
        // TODO : compléter la méthode intersection(set)
    }

    @Override
    public void union(L3Collection<T> otherSet) {
        // TODO : compléter la méthode union(set)
    }

    @Override
    public int hashCode() {
        // TODO : compléter la méthode hashCode()
        return -1;
    }

    @Override
    public String toString() {
        String res = "{ ";
        for (T element : this) {
            res += element + " ";
        }
        return res + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
		else if (!(o instanceof L3HashSet set)) return false;
		else if(this.size == set.size) {
            for (T element: this) {
                if (!set.contains(element)) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new L3HashSetIterator();
    }
}

