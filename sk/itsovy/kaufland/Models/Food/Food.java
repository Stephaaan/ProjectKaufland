package sk.itsovy.kaufland.Models.Food;

import sk.itsovy.kaufland.Models.Item;

public abstract class Food extends Item{
	private int callories;
	
	public Food(String name, double price, int callories) {
		super(name, price);
		this.callories = callories;
	}

	public int getCallories() {
		return callories;
	}

	public void setCallories(int callories) {
		this.callories = callories;
	}


}