package application.model;

import java.util.ArrayList;

public class Pizza {
	
	//initalize pizza parameters
	private String _size;
	private int _numSauce = 0;
	private int _numCheese = 0;
	private int _numPepperoni = 0;
	private int _numBeef = 0;
	private int _numChicken = 0;
	private int _numSausage = 0;
	private int _numOnion = 0;
	private int _numMushroom = 0;
	private int _numPineapple = 0;
	private int _numGreenPepper = 0;
	private int _numHotPepper = 0;
	private int _numOlive = 0;
	private double _price = 0;
	
	//takes in parameters from the servlet and sets the parameters
	public void setPizza(String size, int numSauce, int numCheese, int numPepperoni, int numBeef, int numChicken,
			int numSausage, int numOnion, int numMushroom, int numPineapple, int numGreenPepper, int numHotPepper,
			int numOlive) {

		this._size = size;
		this._numSauce = numSauce;
		this._numCheese = numCheese;
		this._numPepperoni = numPepperoni;
		this._numBeef = numBeef;
		this._numChicken = numChicken;
		this._numSausage = numSausage;
		this._numOnion = numOnion;
		this._numMushroom = numMushroom;
		this._numPineapple = numPineapple;
		this._numGreenPepper = numGreenPepper;
		this._numHotPepper = numHotPepper;
		this._numOlive = numOlive;

	}

	public String getPizzaSize() {
		return _size;
	}
	
	//loops through each topping and checks if the value is greater than zero
	//if user has chosen the toppings, it will be added to the arraylist and will be returned once the method is called
	public ArrayList<String> getPizzaToppings() {

		ArrayList<String> toppings = new ArrayList<String>();
		String topping = new String();

		if (this._numSauce > 0) {
			topping = Integer.toString(_numSauce) + " x Extra Sauce";
			toppings.add(topping);
		}

		if (this._numCheese > 0) {
			topping = Integer.toString(_numCheese) + " x Extra Cheese";
			toppings.add(topping);
		}

		if (this._numPepperoni > 0) {
			topping = Integer.toString(_numPepperoni) + " x Pepperoni";
			toppings.add(topping);
		}

		if (this._numBeef > 0) {
			topping = Integer.toString(_numBeef) + " x Beef";
			toppings.add(topping);
		}

		if (this._numChicken > 0) {
			topping = Integer.toString(_numChicken) + " x Chicken";
			toppings.add(topping);
		}

		if (this._numSausage > 0) {
			topping = Integer.toString(_numSausage) + " x Sausage";
			toppings.add(topping);
		}

		if (this._numOnion > 0) {
			topping = Integer.toString(_numOnion) + " x Onion";
			toppings.add(topping);
		}

		if (this._numMushroom > 0) {
			topping = Integer.toString(_numMushroom) + " x Mushroom";
			toppings.add(topping);
		}

		if (this._numPineapple > 0) {
			topping = Integer.toString(_numPineapple) + " x Pineapple";
			toppings.add(topping);
		}

		if (this._numGreenPepper > 0) {
			topping = Integer.toString(_numGreenPepper) + " x Green Pepper";
			toppings.add(topping);
		}

		if (this._numHotPepper > 0) {
			topping = Integer.toString(_numHotPepper) + " x Hot Pepper";
			toppings.add(topping);
		}

		if (this._numOlive > 0) {
			topping = Integer.toString(_numOlive) + " x Olive";
			toppings.add(topping);
		}

		return toppings;

	}
	
	public void setPrice(double price) {
		this._price = price;
	}
	
	public double getPrice() {
		return this._price;
	}

}
