package sk.itsovy.kaufland;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.w3c.dom.DOMException;

import sk.itsovy.kaufland.Database.Database;
import sk.itsovy.kaufland.Exceptions.BillClosedException;
import sk.itsovy.kaufland.Exceptions.BillNotClosedException;
import sk.itsovy.kaufland.Exceptions.BillOverLoadedException;
import sk.itsovy.kaufland.Export.ExportToXML;
import sk.itsovy.kaufland.Models.Drink.Draft;
import sk.itsovy.kaufland.Models.Food.Food;
import sk.itsovy.kaufland.Models.Food.Fruit;
import sk.itsovy.kaufland.Models.Food.Pastry;
import sk.itsovy.kaufland.Models.Other.Goods;
import sk.itsovy.kaufland.enums.Category;

public class Application {
	static Application app = new Application();
	public void example() {
		Bill b = new Bill();
		try {
			b.add(new Fruit("Jablko", 0.59, 1.25));
			b.add(new Draft("Coca Cola", 1.25, false, 0.5));
			b.add(new Goods("Prievidzka žárovečka", 15.99,  2,Category.School));
			b.add(new Goods("prievidzka žárovečka", 15.99, 1, Category.School));
		} catch (BillOverLoadedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BillClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.end();
		try {
			ExportToXML.export(b);
		} catch (DOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("begin of write");
		Database db = new Database();
		try {
			System.out.println("writing");
			db.insertNewBill(b);
			System.out.println("writed");
		} catch (BillNotClosedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.print();
	}
	static Application getInstance(){
		return app;
	}
	private Application() {
			
	}
}