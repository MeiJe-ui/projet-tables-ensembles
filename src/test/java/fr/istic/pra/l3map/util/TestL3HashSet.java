package fr.istic.pra.l3map.util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestL3HashSet {

    private L3HashSet<String> set;

    @Before
    public void setUp() {
        set = new L3HashSet<>();
    }

    @Test
    public void testAddAndContains() {
        assertFalse(set.contains("Alice"));
        set.add("Alice");
        assertTrue(set.contains("Alice"));
    }

    @Test
    public void testSizeAndIsEmpty() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add("Bob");
        assertFalse(set.isEmpty());
        assertEquals(1, set.size());
    }

    @Test
    public void testRemove() {
        set.add("Alice");
        set.add("Bob");
        set.remove("Alice");
        assertFalse(set.contains("Alice"));
        assertTrue(set.contains("Bob"));
        assertEquals(1, set.size());
    }

    @Test
    public void testClear() {
        set.add("X");
        set.add("Y");
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testIsIncludedIn() {
        L3HashSet<String> other = new L3HashSet<>();
        other.add("A");
        other.add("B");

        set.add("A");
        assertTrue(set.isIncludedIn(other));

        set.add("C");
        assertFalse(set.isIncludedIn(other));
    }

    @Test
    public void testUnion() {
        set.add("A");
        L3HashSet<String> other = new L3HashSet<>();
        other.add("B");
        set.union(other);
        assertTrue(set.contains("A"));
        assertTrue(set.contains("B"));
        assertEquals(2, set.size());
    }

    @Test
    public void testIntersection() {
        set.add("A");
        set.add("B");
        L3HashSet<String> other = new L3HashSet<>();
        other.add("B");
        other.add("C");
        set.intersection(other);
        assertTrue(set.contains("B"));
        assertFalse(set.contains("A"));
        assertEquals(1, set.size());
    }

    @Test
    public void testDifference() {
        set.add("A");
        set.add("B");
        L3HashSet<String> other = new L3HashSet<>();
        other.add("B");
        set.difference(other);
        assertTrue(set.contains("A"));
        assertFalse(set.contains("B"));
        assertEquals(1, set.size());
    }

    @Test
    public void testSymmetricDifference() {
        set.add("A");
        set.add("B");
        L3HashSet<String> other = new L3HashSet<>();
        other.add("B");
        other.add("C");
        set.symmetricDifference(other);
        assertTrue(set.contains("A"));
        assertTrue(set.contains("C"));
        assertFalse(set.contains("B"));
        assertEquals(2, set.size());
    }

    @Test
    public void testEqualsAndHashCode() {
        L3HashSet<String> set1 = new L3HashSet<>();
        set1.add("X");
        set1.add("Y");

        L3HashSet<String> set2 = new L3HashSet<>();
        set2.add("Y");
        set2.add("X");

        assertTrue(set1.equals(set2));
        assertEquals(set1.hashCode(), set2.hashCode());
    }

    @Test
    public void testIterator() {
        set.add("A");
        set.add("B");

        int count = 0;
        for (String s : set) {
            assertTrue(s.equals("A") || s.equals("B"));
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void testCopyConstructor() {
        set.add("X");
        set.add("Y");

        L3HashSet<String> copy = new L3HashSet<>(set);
        assertEquals(set.size(), copy.size());
        assertTrue(copy.contains("X"));
        assertTrue(copy.contains("Y"));
        assertNotSame(set, copy); // vérifier que c’est une vraie copie
    }

    @Test
    public void testToString() {
        set.add("A");
        String str = set.toString();
        assertTrue(str.contains("A"));
        assertTrue(str.startsWith("{"));
        assertTrue(str.endsWith("}"));
    }
}
