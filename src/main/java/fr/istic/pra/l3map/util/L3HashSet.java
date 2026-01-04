package fr.istic.pra.l3map.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import fr.istic.pra.lang.L3Collection;
import fr.istic.pra.lang.L3Iterator;

public class L3HashSet<T> implements L3Collection<T> {
    
    public class L3HashSetIterator implements Iterator<T> {
        private int bucketIndex = 0;
        private Iterator<T> bucketIterator = null;

        public L3HashSetIterator() {
            while (bucketIndex < DEFAULT_CAPACITY && buckets[bucketIndex].isEmpty()) {
                bucketIndex++;
            }
            if (bucketIndex < DEFAULT_CAPACITY) {
                bucketIterator = buckets[bucketIndex].listIterator();
            }
        }

        @Override
        public boolean hasNext() {
            if (bucketIterator != null && bucketIterator.hasNext()) {
            return true;
            }
    
        int nextBucket = bucketIndex + 1;
            while (nextBucket < buckets.length) {
                if (!buckets[nextBucket].isEmpty()) {
                return true;
                }
                nextBucket++;
            }

            return false;
        }

        @Override
        public T next() {
            if (bucketIterator != null && bucketIterator.hasNext()) {
                return bucketIterator.next();
            }

        
            int nextBucket = bucketIndex + 1;
                while (nextBucket < buckets.length && buckets[nextBucket].isEmpty()) {
                nextBucket++;
            }

            if (nextBucket >= buckets.length) {
                throw new NoSuchElementException();
            }

        
            bucketIndex = nextBucket;
            bucketIterator = buckets[bucketIndex].iterator();
            return bucketIterator.next();
        }

        @Override
        public void remove() {
           
            if(!this.bucketIterator.hasNext()){
                this.bucketIterator.remove();

                while(this.bucketIndex < DEFAULT_CAPACITY && buckets[this.bucketIndex].isEmpty()){
                bucketIndex ++; 
                }
                if(bucketIndex < DEFAULT_CAPACITY){
                    this.bucketIterator = buckets[this.bucketIndex].listIterator();
                }
                size--;
            }
            else{
                this.bucketIterator.remove();
                size--;
            }
            

        }
    }




    private static final int DEFAULT_CAPACITY = 16;
    private ArrayList<T>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public L3HashSet() {
        buckets = new ArrayList[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++){
            buckets[i] = new ArrayList<>();
        }
        this.size = 0;
    }

    public L3HashSet(L3HashSet<T> set) {
        this();
        for(int i = 0 ; i< DEFAULT_CAPACITY ; i++) {
            buckets[i].addAll(set.buckets[i]);
            size += set.buckets[i].size();
        }
    }

    private int hashIndex(T value) {
        int  hashCode  = value.hashCode();
        return (Math.abs(hashCode) % 16); // Renvoie une valeur entre 0 et 15. 
    }

    @Override
    public boolean contains(T value) {
        return buckets[hashIndex(value)].contains(value);
    }

    @Override
    public void add(T value) {
        int index = hashIndex(value);
        if(buckets[index].contains(value)) {
            return;
        }
        else{
            buckets[index].add(value);
            this.size++;
        }
        
    }

    @Override
    public void remove(T value) {
        int index = hashIndex(value);
        if(buckets[index].contains(value)){
            buckets[index].remove(value);
            this.size--;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < DEFAULT_CAPACITY; i++){
            buckets[i].clear();
        }
        this.size = 0;
    }

    @Override
    public boolean isIncludedIn(L3Collection<T> set2) {
        L3HashSetIterator it = new L3HashSetIterator();
        
        while(it.hasNext()){
            T value = it.next();
            if(!set2.contains(value)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void intersection(L3Collection<T> otherSet) {
        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            T current = it.next();
            if (!otherSet.contains(current)) {
                it.remove(); // on peut supprimer en toute sécurité
            }
        }
    }
    /**
     * this devient l'union
     */
    @Override
    public void union(L3Collection<T> otherSet) {
        Iterator<T> it = otherSet.iterator();
        while(it.hasNext()) {
            this.add(it.next()); //le cas où la valeur est déjà presente est traité dans le add(value)
        }

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

