package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

public class OrderDBAccess {
	/**
	 * The connection object *
	 */
	private Connection _connection = null;
	/**
	 * Prepared Statements *
	 */
	private PreparedStatement _insertOrder;

	/**
	 * Default constructor links in the JDBC driver class.
	 */
	public OrderDBAccess() {
		try {
			//Attempt to load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//show a message if it fails
		} catch (ClassNotFoundException e) {
			System.err.println("ERROR! mysql.com.jdbc.Driver not found.");
		}
	}// end of constructor

	public boolean connect() {
		try {
			// TODO: Change the password before running the application
			//connect url
			_connection = DriverManager.getConnection("jdbc:mysql://localhost/pizzashop", "root", "password");
			
			//initalize the connection object
			try {

				// Create the statement objects
				_insertOrder = _connection.prepareStatement(
						"INSERT orderinfo (Name, Phone, Email, Street, Size, Toppings, Delivery, Price, Date, Time) VALUES (?,?,?,?,?,?,?,?,?,?);");

			} catch (SQLException e) {
				System.err.println("ERROR! While preparing statements: " + e.getMessage());
				try {
					_connection.close();
				} catch (SQLException e2) {
					System.err.println("ERROR! Couldn't close DB connection: " + e.getMessage());
				}
				return false;
			}

		} catch (SQLException e) {
			System.err.println("ERROR! Couldn't connect to db: " + e.getMessage());
			return false;
		}
		return true;
	}// end of connect method

	//discount from db
	public boolean closeConnection() {
		boolean success = true;
		try {
			//close the PS objects
			_insertOrder.close();
		} catch (SQLException e) {
			System.err.println("ERROR! Couldn't close statement: " + e.getMessage());
			success = false;
		}

		return success;
	}

	public boolean insertOrder(String name, String phone, String email, String street, String size, String toppings,
			int delivery, double price, Date date, Time time) {
		try {
			_insertOrder.setString(1, name);
			_insertOrder.setString(2, phone);
			_insertOrder.setString(3, email);
			_insertOrder.setString(4, street);
			_insertOrder.setString(5, size);
			_insertOrder.setString(6, toppings);
			_insertOrder.setInt(7, delivery);
			_insertOrder.setDouble(8, price);
			_insertOrder.setDate(9, date);
			_insertOrder.setTime(10, time);
			_insertOrder.executeUpdate();
			System.out.println(" \n CONGRATS! Order has been added!");
		} catch (SQLException e) {
			System.err.println("ERROR! Couldn't execute prepared statement: " + e.getMessage());
			return false;
		}

		return true;
	}

}
