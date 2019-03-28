package sk.itsovy.kaufland.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sk.itsovy.kaufland.Bill;
import sk.itsovy.kaufland.Globals;
import sk.itsovy.kaufland.Exceptions.BillNotClosedException;
import sk.itsovy.kaufland.Models.Item;
import sk.itsovy.kaufland.Models.Food.Fruit;
import sk.itsovy.kaufland.interfaces.DraftInterface;
import sk.itsovy.kaufland.interfaces.PcsInterface;

public class Database {
	public Database() {
		
	}
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("loaded");
			conn = DriverManager.getConnection(Globals.DB_URL, Globals.DB_USERNAME, Globals.DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  return conn;
	}
	public void insertNewBill(Bill b) throws BillNotClosedException {
	System.out.println("cyka start");
		if(!b.isEnd()) {
			System.out.println("pici");
			throw new BillNotClosedException("Bill not closed!");
			
		}
		Connection conn = getConnection();
		try {
		
			conn.setAutoCommit(false);
			System.out.println("connected");
			PreparedStatement statement = conn.prepareStatement("INSERT INTO Bill (date, time, totalPrice) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			statement.setDate(1, new java.sql.Date(b.getDate().getTime()));
			statement.setTime(2, new java.sql.Time(b.getDate().getTime()));
			statement.setDouble(3, b.getTotalPrice());
			statement.executeUpdate();
			System.out.println("Bill writed");
			long key = -1;
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				key = rs.getLong(1);
			}
			if(key == -1) {
				throw new SQLException("ID not found");
			}
			System.out.println(key);
			for(Item i:b.getBill()) {
				PreparedStatement stmnt = conn.prepareStatement("INSERT INTO Item (orderID, name,price, count, unit) VALUES(?,?,?,?,?)");
				stmnt.setLong(1, key);
				stmnt.setString(2, i.getName());
				stmnt.setDouble(3, i.getPrice());
				if(i instanceof PcsInterface) {
					stmnt.setDouble(4,((PcsInterface) i).getAmount());	
					stmnt.setString(5, "pcs");
				}else if(i instanceof Fruit) {
					stmnt.setDouble(4,((Fruit) i).getWeight());
					stmnt.setString(5, "kg");
				}else if(i instanceof DraftInterface) {
					stmnt.setDouble(4,((DraftInterface) i).getVolume());
					stmnt.setString(5, "l");
				}
				stmnt.executeUpdate();
				System.out.println("endof item");
			}
			conn.commit();
			System.out.println("end");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
