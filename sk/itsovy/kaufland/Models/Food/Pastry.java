package sk.itsovy.kaufland.Models.Food;

import sk.itsovy.kaufland.interfaces.PcsInterface;

public class Pastry extends Food implements PcsInterface{
	private int amount = 0;
	public Pastry(String name, double price, int callories, int amount) {
		super(name, price, callories);
		this.amount = amount;
	}
	public Pastry(String name, double price, int amount) {
		this(name, price, -1, amount);
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getTotalPrice() {
		return amount*getPrice();
	}
}