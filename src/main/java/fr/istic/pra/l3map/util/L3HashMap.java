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
        for (L3MapEntry<K, V> entry : this) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addValue(K key, V value) {
        for (L3MapEntry<K, V> entry : this) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        this.add(new L3MapEntry<>(key, value));
    }

    @Override
    public void modifyValue(K key, V newValue) {
        for (L3MapEntry<K, V> entry : this) {
            if (entry.getKey().equals(key)) {
                entry.setValue(newValue);
                return;
            }
        }
    }

    @Override
    public void removeValue(K key, V value) {
        L3MapEntry<K, V> toRemove = null;
        for (L3MapEntry<K, V> entry : this) {
            if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
                toRemove = entry;
                break;
            }
        }

        if (toRemove != null) {
            this.remove(toRemove);
        }
    }

    @Override
    public V getValue(K key) {
        for (L3MapEntry<K, V> entry : this) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
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
