package sk.itsovy.kaufland;

import java.util.Date;
import java.util.LinkedList;

import sk.itsovy.kaufland.Exceptions.BillClosedException;
import sk.itsovy.kaufland.Exceptions.BillOverLoadedException;
import sk.itsovy.kaufland.Models.Item;
import sk.itsovy.kaufland.Models.Food.Fruit;
import sk.itsovy.kaufland.interfaces.DraftInterface;
import sk.itsovy.kaufland.interfaces.PcsInterface;

public class Bill {
	private boolean end = false;
	Date endDate = null;
	private double totalPrice = 0;
	private LinkedList<Item> list ;
	public Bill() {
		list = new LinkedList<Item>();
	}
	public void add(Item i) throws BillOverLoadedException, BillClosedException {
		if(i == null) {
			return;
		}
		if(list.size() == 7) {
			throw new BillOverLoadedException("Maximum bill items count exceed");
		}
		if(end) {
			throw new BillClosedException("Bill is closed");
		}
		Item toUpdate = list.stream().filter(item -> i.getName().toLowerCase().equals(item.getName().toLowerCase()) && i.getClass().getName().equals(item.getClass().getName())).findAny().orElse(null);
		if(toUpdate == null) {
			list.add(i);
			return;
		}
		update(toUpdate, i);
	}
	public LinkedList<Item> getBill(){
		return list;
	}
	public void update(Item toUpdate, Item item) {
		if(toUpdate instanceof PcsInterface) {
			int newAmount = ((PcsInterface) toUpdate).getAmount() + ((PcsInterface) item).getAmount();
			((PcsInterface) toUpdate).setAmount(newAmount);
		}else if(toUpdate instanceof Fruit) {
			double newWeight = ((Fruit) toUpdate).getWeight() + ((Fruit) item).getWeight();
			((Fruit) toUpdate).setWeight(newWeight);
		}else if(toUpdate instanceof DraftInterface) {
			double newVolume = ((DraftInterface) toUpdate).getVolume() + ((DraftInterface) item).getVolume();
			((DraftInterface) toUpdate).setVolume(newVolume);
		}
	}
	public void remove(Item i) {
		list.remove(i);
	}
	public int getCount() {
		return list.size();
	}
	public boolean isEnd() {
		return end;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void end() {
		if(end == false) {
			endDate = new Date();
		}
		end = true;
		for(Item i:list) {
			totalPrice+=i.getTotalPrice();
		}
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
			System.out.println("end: "+endDate);
		}
	}
	public Date getDate() {
		return endDate;
	}
}