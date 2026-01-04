package fr.istic.pra.l3map;


import java.util.HashSet;

//import fr.istic.pra.map.util.L3HashMap; // a commenter en partie 3.2
import fr.istic.pra.l3map.util.L3HashMap; // a décommenter en partie 3.2
//import fr.istic.pra.map.util.L3HashSet; // a commenter en partie 3.2
import fr.istic.pra.l3map.util.L3HashSet; // a décommenter en partie 3.2

public class SocialNetwork extends L3HashMap <String, L3HashSet<String>> {
	// constructeur
	public SocialNetwork() {
		super();
	}

	/**
	* @return true si la relation (x,y) appartient à this, false sinon
	*/
	public boolean isDefined(String x, String y) {
		L3HashSet<String> set = this.getValue(x);
		return set != null && set.contains(y);
	}

	/**
	* @return nombre d’entiers y tels que (x, y) appartient à this.
	*/
	public int numberOfYs(String x) {
		L3HashSet<String> set = this.getValue(x);
		return (set == null) ? 0 : set.size();
	}

	/**
	* @return nombre total de doublets (x,y) dans this
	*/
	public int numberOfPairs() {
		int nb = 0;
		L3HashMapKeyIterator it = super.keyIterator();
		while (it.hasNext()) {
			L3HashSet<String> set = super.getValue(it.next());
			nb += set.size();
		}
		return nb;
	}
	
	/**
	* @return true si this est identique à net, false sinon
	*/
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		else if (!(o instanceof SocialNetwork net)) return false;
		else if (this.size() == net.size()) {
			boolean egaux = true;
			L3HashMapKeyIterator it = super.keyIterator();
			while (it.hasNext() && egaux) {
				String x = it.next();
				L3HashSet<String> set1 = super.getValue(x);
				L3HashSet<String> set2 = net.getValue(x);
				egaux = set2 != null && set1.equals(set2);
			}
			return egaux;
		}
		return false;
	}

	/**
	* Ajouter la relation (x,y) à this (sans effet si (x, y) est
	* déjà présent)
	*/
	public void addRelation(String x, String y) {
		L3HashSet<String> set = super.getValue(x);
		if (set == null) {
			set = new L3HashSet<>();
			set.add(y);
			this.addValue(x, set);
		} else {
			set.add(y); 
		}
	}

	/**
	* Supprimer la relation (x,y) de this (sans effet si (x, y) n’est
	* pas présent).
	*/
	public void removeRelation(String x, String y) {
		L3HashSet<String> set = super.getValue(x);
		if (set != null && !set.isEmpty() && set.contains(y)) {
			set.remove(y);
			if (set.isEmpty()) super.removeValue(x, set);
		}
	}

	/**
	* @return nombre de triplets (x, y, z) tels que (x,y) appartient
	* à this et (y,z) appartient à net
	*/
	public int nbTriplets(SocialNetwork net) {
		int nb = 0;
		L3HashMapKeyIterator it = super.keyIterator();
		while (it.hasNext()) {
			String y = it.next();
			nb += net.numberOfYs(y);
		}
		return nb;
	}

	/**
	* @return relation symétrique de this, la relation constituée des
	* (y, x) tels que (x, y) appartient à this.
	*/
	public SocialNetwork symmetricRelation() {
		SocialNetwork symetric = new SocialNetwork();
		L3HashMapKeyIterator it = super.keyIterator();
		while(it.hasNext()){
			String y = it.next(); 
			for (String r : this.getValue(y)) {
				symetric.addRelation(r, y);
			}
		}
		return symetric;
	}

	/**
	* @return true si this est une relation réflexive (i.e. si pour
	* toute entrée x de this, (x, x) appartient à this), false sinon
	*/
	public boolean isReflexive() {
		L3HashMapKeyIterator it = super.keyIterator();
		while(it.hasNext()){
			String y = it.next();
			if(!this.isDefined(y, y)){
				return false;
			}
		}
		return true;
	}

	/**
	* @return true si this est inclus dans net, false sinon
	*/
	public boolean isIncludedIn(SocialNetwork net) {
		if(this.equals(net)) return true;
		L3HashMapKeyIterator it = super.keyIterator();
		while(it.hasNext()){

			String y = it.next();

			if(!net.containsKey(y)){
				return false;

			} else {

				for (String r : this.getValue(y)) {
					if(!net.isDefined(y, r)){
						return false; 
					}
					
				}
			}
		}
		return true;
	}

	/**
	* @return intersection de this et net
	*/
	public SocialNetwork intersection(SocialNetwork net) {
		SocialNetwork intersection = new SocialNetwork();
		L3HashMapKeyIterator it = super.keyIterator();
		while(it.hasNext()){
			String y = it.next();
			for (String r : this.getValue(y)){
				if(net.isDefined(y, r)){
					intersection.addRelation(y, r);
				}
			}
		}
		return intersection;
	}

	/**
	* this devient l’intersection de this et net.
	*/
	public void intersectionBis(SocialNetwork net) {
		SocialNetwork clone = this.intersection(net);
		this.clear();
		L3HashMapKeyIterator it = clone.keyIterator();
		if(!clone.isEmpty()){
			while (it.hasNext()){
				String y = it.next();
				for (String r : clone.getValue(y)){
					this.addRelation(y,r);
				}
			}
		}
	}

	/**
	* this devient l’union de this et net.
	*/
	public void union(SocialNetwork net) {
		L3HashMapKeyIterator it = net.keyIterator();
		while(it.hasNext()){
			String y = it.next();
			for(String r : net.getValue(y)){
				if(!this.isDefined(y, r)){
					this.addRelation(y,r);
				}
			}
		}
	}
}
