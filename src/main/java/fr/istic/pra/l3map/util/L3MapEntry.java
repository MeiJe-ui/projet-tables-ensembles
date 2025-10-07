package fr.istic.pra.l3map.util;

public class L3MapEntry<K, V> {
    private K key;
    private V value;

    public L3MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        } else {
            @SuppressWarnings("unchecked")
            L3MapEntry<K, V> entry = (L3MapEntry<K, V>) o;
            return (key == null ? entry.key == null : key.equals(entry.key)) &&
            (value == null ? entry.value == null : value.equals(entry.value));
        }
    }

    @Override
    public int hashCode() {
        // TODO: compléter la méthode hashCode()
        return -1;
    }

    @Override
    public String toString() {
        return "Key: " + this.key + ", Value: " + this.value;
    }
}
