package fr.istic.pra.l3map;

import fr.istic.pra.l3map.util.L3HashSet;

public class App {
    public static void main(String[] args) {
		L3HashSet<Integer> set = new L3HashSet<>();
        set.add(1);
        set.add(2);
        L3HashSet<Integer> set2 = new L3HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(set2.isIncludedIn(set));
    }
}
