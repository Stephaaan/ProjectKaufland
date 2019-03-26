package sk.itsovy.kaufland.Models.Drink;

import sk.itsovy.kaufland.Models.Item;

public abstract class Drink extends Item {
	public boolean sweet;
	public Drink(String name, double price,boolean sweet) {
		super(name, price);
		this.sweet = sweet;
	}
	public boolean isSweet() {
		return sweet;
	}
	public void setSweet(boolean sweet) {
		this.sweet = sweet;
	}
	
}

