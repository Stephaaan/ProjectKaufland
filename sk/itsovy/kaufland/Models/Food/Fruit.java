package sk.itsovy.kaufland.Models.Food;

public class Fruit extends Food {
	private double weight = 0;
	public Fruit(String name, double price, int callories, double weight) {
		super(name, price, callories);
		this.weight = weight;
	}
	public Fruit(String name, double price, double weight) {
		this(name, price, -1, weight);
		
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getTotalPrice() {
		return weight*getPrice();
	}
	

}