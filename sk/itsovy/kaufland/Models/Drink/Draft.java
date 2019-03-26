package sk.itsovy.kaufland.Models.Drink;

import sk.itsovy.kaufland.interfaces.DraftInterface;

public class Draft extends Drink implements DraftInterface {
	private double volume = 0;
	public Draft(String name, double price, boolean sweet, double volume) {
		super(name, price, sweet);
		this.volume = volume;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getTotalPrice() {
		return volume*getPrice();
	}
	
}