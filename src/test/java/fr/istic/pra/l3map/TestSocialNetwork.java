package fr.istic.pra.l3map;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestSocialNetwork {

    private SocialNetwork network;

    @Before
    public void setUp() {
        network = new SocialNetwork();
        network.addRelation("Alice", "Bob");
        network.addRelation("Alice", "Charlie");
        network.addRelation("Bob", "David");
        network.addRelation("Charlie", "Alice"); // to create symmetry
        System.out.println(network);
    }

    @Test
    public void testIsDefined() {
        assertTrue(network.isDefined("Alice", "Bob"));
        assertFalse(network.isDefined("Bob", "Alice"));
    }

    @Test
    public void testNumberOfYs() {
        assertEquals(2, network.numberOfYs("Alice"));
        assertEquals(1, network.numberOfYs("Bob"));
        assertEquals(0, network.numberOfYs("David"));
    }

    @Test
    public void testNumberOfPairs() {
        assertEquals(4, network.numberOfPairs());
    }

    @Test
    public void testAddRelation_NoDuplicates() {
        network.addRelation("Alice", "Bob"); // duplicate
        assertEquals(2, network.numberOfYs("Alice"));
    }

    @Test
    public void testRemoveRelation() {
        network.removeRelation("Alice", "Bob");
        assertFalse(network.isDefined("Alice", "Bob"));
        assertEquals(1, network.numberOfYs("Alice"));
    }

    @Test
    public void testEquals() {
        SocialNetwork other = new SocialNetwork();
        other.addRelation("Alice", "Bob");
        other.addRelation("Alice", "Charlie");
        other.addRelation("Bob", "David");
        other.addRelation("Charlie", "Alice");
        assertTrue(network.equals(other));

        other.addRelation("Charlie", "David");
        assertFalse(network.equals(other));
    }

    @Test
    public void testSymmetricRelation() {
        SocialNetwork symmetric = network.symmetricRelation();
        assertTrue(symmetric.isDefined("Bob", "Alice"));
        assertTrue(symmetric.isDefined("Charlie", "Alice"));
        assertTrue(symmetric.isDefined("David", "Bob"));
    }

    @Test
    public void testIsReflexive() {
        assertFalse(network.isReflexive());
        network.addRelation("Alice", "Alice");
        network.addRelation("Bob", "Bob");
        network.addRelation("Charlie", "Charlie");
        network.addRelation("David", "David");
        assertTrue(network.isReflexive());
    }

    @Test
    public void testIsIncludedIn() {
        SocialNetwork larger = new SocialNetwork();
        larger.addRelation("Alice", "Bob");
        larger.addRelation("Alice", "Charlie");
        larger.addRelation("Bob", "David");
        larger.addRelation("Charlie", "Alice");
        larger.addRelation("Charlie", "David");

        assertTrue(network.isIncludedIn(larger));
        assertFalse(larger.isIncludedIn(network));
    }

    @Test
    public void testIntersection() {
        SocialNetwork other = new SocialNetwork();
        other.addRelation("Alice", "Charlie");
        other.addRelation("Bob", "David");
        SocialNetwork inter = network.intersection(other);

        assertTrue(inter.isDefined("Alice", "Charlie"));
        assertTrue(inter.isDefined("Bob", "David"));
        assertFalse(inter.isDefined("Alice", "Bob"));
    }

    @Test
    public void testIntersectionBis() {
        SocialNetwork other = new SocialNetwork();
        other.addRelation("Alice", "Charlie");
        other.addRelation("Bob", "David");

        network.intersectionBis(other);
        assertTrue(network.isDefined("Alice", "Charlie"));
        assertTrue(network.isDefined("Bob", "David"));
        assertFalse(network.isDefined("Alice", "Bob"));
    }

    @Test
    public void testUnion() {
        SocialNetwork other = new SocialNetwork();
        other.addRelation("Alice", "David");
        other.addRelation("Eve", "Frank");

        network.union(other);

        assertTrue(network.isDefined("Alice", "David"));
        assertTrue(network.isDefined("Eve", "Frank"));
        assertEquals(6, network.numberOfPairs());
    }

    @Test
    public void testNbTriplets() {
        // Setup a second network to test triplets
        SocialNetwork net2 = new SocialNetwork();
        net2.addRelation("Bob", "Eve");
        net2.addRelation("Charlie", "Frank");

        int nb = network.nbTriplets(net2); // Expected: Alice->Bob->Eve, Alice->Charlie->Frank
        assertEquals(2, nb);
    }
}

