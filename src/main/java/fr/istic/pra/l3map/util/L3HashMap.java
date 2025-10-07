package fr.istic.pra.l3map.util;

import java.util.Iterator;

import fr.istic.pra.lang.L3Map;
import fr.istic.pra.map.util.L3HashSet;
import fr.istic.pra.map.util.L3MapEntry;

public class L3HashMap<K, V> extends L3HashSet<L3MapEntry<K, V>> implements L3Map<K, V> {

    public class L3HashMapKeyIterator implements Iterator<K> {
        private Iterator<L3MapEntry<K, V>> setIterator;

        public L3HashMapKeyIterator() {
            this.setIterator = L3HashMap.this.iterator();
        }

        @Override
        public boolean hasNext() {
            return setIterator.hasNext();
        }

        @Override
        public K next() {
            return setIterator.next().getKey();
        }

        @Override
        public void remove() {
            setIterator.remove();
        }
    }

    @Override
    public boolean containsKey(K key) {
        // TODO : compléter la méthode containsKey(key)
        return false;
    }

    @Override
    public void addValue(K key, V value) {
        // TODO : compléter la méthode addValue(key, value)
    }

    @Override
    public void modifyValue(K key, V newValue) {
        // TODO : compléter la méthode modifyValue(key, value)
    }

    @Override
    public void removeValue(K key, V value) {
        // TODO : compléter la méthode removeValue(key, value)
    }

    @Override
    public V getValue(K key) {
        // TODO : compléter la méthode getValue(key)
        return null;
    }

    public L3HashMapKeyIterator keyIterator() {
        return new L3HashMapKeyIterator();
    }

    @Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		L3HashMapKeyIterator it = this.keyIterator();
		while (it.hasNext()) {
			K key = it.next();
			res.append(key);
			res.append("->");
			res.append(this.getValue(key));
			res.append("\n");
		}
		return res.toString();
	}
}
