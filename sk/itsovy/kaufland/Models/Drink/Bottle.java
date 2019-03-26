package sk.itsovy.kaufland.Models.Drink;

import sk.itsovy.kaufland.interfaces.PcsInterface;

public class Bottle extends Drink implements PcsInterface{
	private int amount = 0;
	public Bottle(String name, double price, boolean sweet, int amount) {
		super(name, price, sweet);
		this.amount = amount;
	}
	public Bottle(String name, double price, int amount) {
		this(name, price, false, amount);
	}
	public Bottle(String name, double price, boolean sweet) {
		this(name, price, sweet,  1);
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public double getTotalPrice() {
		return amount * getPrice();
	}
	
}