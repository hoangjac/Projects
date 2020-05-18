package application.model;

import java.util.ArrayList;

//Class to calculate pricing
public class Pricing {
	
	//initalize the parameters
	private double _price = 0.0;
	private double _toppingPrice = 0.0;
	private double _totalPrice = 0.0;
	private double _taxSubtotal = 0.0;
	private double _tax = 1.13;

	//takes an arraylist of doubles that includes the price of each pizza and calculates the total price before taxes
	//this method will be called upon once the getTotal method is called upon
	private void calculateTotal(ArrayList<Double> subtotals) {

		//loops through the list and adds all the prices of each pizza
		for (int i = 0; i < subtotals.size(); i++) {
			_totalPrice += subtotals.get(i);
		}

	}

	//calculate the tax - takes the total price and multiply by .13 (13%)
	private void calculateTax() {

		this._taxSubtotal = this._totalPrice * .13;
	}
	
	//Set the price of each pizza once a pizza has been added to the order
	//takes in all the parameters to calculate the price
	public void setPrice(String size, int numSauce, int numCheese, int numPepperoni, int numBeef, int numChicken,
			int numSausage, int numOnion, int numMushroom, int numPineapple, int numGreenPepper, int numHotPepper,
			int numOlive) {

		//set the price based on the size
		if (size.equals("S")) {
			this._price = 9.99;
		} else if (size.equals("M")) {
			this._price = 12.99;
		} else if (size.contentEquals("L")) {
			this._price = 14.99;
		} else if (size.contentEquals("XL")) {
			this._price = 19.99;
		}

		//counts all of the toppings to add to the price of each pizza
		double totalToppings = numSauce + numCheese + numPepperoni + numBeef + numChicken + numSausage + numOnion
				+ numMushroom + numPineapple + numGreenPepper + numHotPepper + numOlive;
		
		//count how many toppings there are and for each 3 toppings, it is free 
		for (int count=0; count <= totalToppings; count++) {
			if((count%4)!=0 && totalToppings !=0) {
				//set the topping price
				this._toppingPrice += 1.00;
			}
			
		}
		
		//adds the price of each pizza with their topping price
		this._price += this._toppingPrice;

	}

	//get the price of each piiza
	public double getPrice() {
		return this._price;
	}

	//get the tax
	public double getTaxSubtotal() {
		calculateTax();
		return this._taxSubtotal;
	}

	//get the total price including taxes
	public double getTotal(ArrayList<Double> subtotals) {
		calculateTotal(subtotals);
		return _totalPrice * this._tax;
	}

}
