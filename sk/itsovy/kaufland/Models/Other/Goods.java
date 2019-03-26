package sk.itsovy.kaufland.Models.Other;

import sk.itsovy.kaufland.Models.Item;
import sk.itsovy.kaufland.enums.Category;
import sk.itsovy.kaufland.interfaces.PcsInterface;

public class Goods extends Item implements PcsInterface{
	private Category type;
	private int amount;
	public Goods(String name, double price, int amount, Category type) {
		super(name, price);
		this.amount = amount;
		this.type = type;
		// TODO Auto-generated constructor stub
	}
	public Category getType() {
		return type;
	}
	public void setType(Category type) {
		this.type = type;
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