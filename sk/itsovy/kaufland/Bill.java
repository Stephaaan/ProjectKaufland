package sk.itsovy.kaufland;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.ClosedSelectorException;
import java.util.Date;
import java.util.LinkedList;

import sk.itsovy.kaufland.Models.Item;
import sk.itsovy.kaufland.Models.Food.Fruit;
import sk.itsovy.kaufland.interfaces.DraftInterface;
import sk.itsovy.kaufland.interfaces.PcsInterface;

public class Bill {
	boolean end = false;
	Date endDate = null;
	private LinkedList<Item> list ;
	public Bill() {
		list = new LinkedList<Item>();
	}
	public void add(Item i) {
		if(i == null) {
			return;
		}
		if(list.size() == 7) {
			throw new ArrayIndexOutOfBoundsException("Limit of bill exceed");
		}
		if(end) {
		//	throw new ClosedChannelException("Bill is closed");
		}
		list.add(i);
	}
	public void remove(Item i) {
		list.remove(i);
	}
	public int getCount() {
		return list.size();
	}
	public void end() {
		if(end == false) {
			endDate = new Date();
		}
		end = true;
	}
	public void print() {
		if(getCount() == 0) {
			System.out.println("Bill is empty");
			return;
		}
		for(Item i:list) {
			if(i instanceof PcsInterface) {
				System.out.println(i.getName() + " " +((PcsInterface) i).getAmount());
				System.out.println(i.getPrice() + " " + i.getTotalPrice());
			}else if(i instanceof Fruit) {
				System.out.println(i.getName() + " " + ((Fruit) i).getWeight());
				System.out.println(i.getPrice() + " " + i.getTotalPrice());
			}else if(i instanceof DraftInterface) {
				System.out.println(i.getName() + " " + ((DraftInterface) i).getVolume());
				System.out.println(i.getPrice() + " " + i.getTotalPrice());
			}
		}
		if(end) {
			System.out.println("end:"+endDate);
		}
	}
}