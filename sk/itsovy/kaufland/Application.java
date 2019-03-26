package sk.itsovy.kaufland;

import sk.itsovy.kaufland.Models.Item;
import sk.itsovy.kaufland.Models.Drink.Bottle;
import sk.itsovy.kaufland.Models.Drink.Draft;
import sk.itsovy.kaufland.Models.Food.Fruit;
import sk.itsovy.kaufland.Models.Food.Pastry;
import sk.itsovy.kaufland.Models.Other.Goods;
import sk.itsovy.kaufland.enums.Category;

public class Application {
	static Application app = new Application();
	public void example() {
		Bill bill = new Bill();
		bill.add(new Bottle("Milk 1.5%", 0.59, 4));
		bill.add(new Pastry("Pizza", 1.1, 266,2));
		bill.add(new Fruit("Apple", 0.59, 0.145));
		bill.add(new Goods("TEC-9", 15.99, 1, Category.School));
		Item beer = new Draft("Šariš 12%", 1.3 , false, 0.5);
		bill.add(beer);
		bill.remove(beer);
		bill.end();
		bill.print();
	}
	static Application getInstance(){
		return app;
	}
	private Application() {

	}
}