package sk.itsovy.kaufland.Models;

import sk.itsovy.kaufland.interfaces.ItemInterface;

/**
 * @author stephaaan
 *
 */
public abstract class Item implements ItemInterface{
	private String name;
	private double price;
	
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public abstract double getTotalPrice();
	
}