package application.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.model.Pizza;
import application.model.Pricing;
import data.OrderDBAccess;

/**
 * Servlet implementation class FrontController
 */

//annontation instead of front controller
@WebServlet("/orderpizza")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Declare an array list to hold pizza objects
	ArrayList<Pizza> list = new ArrayList<Pizza>();

	// Declare an arraylist to hold pricing objects
	ArrayList<Pricing> pricing = new ArrayList<Pricing>();

	// Declare an arraylist to hold all the double prices so that they can be added
	// up
	ArrayList<Double> totalPricesList = new ArrayList<Double>();

	// format prices so that they are two decimal places
	DecimalFormat df = new DecimalFormat("###.00");

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EE MMM dd yyyy HH:mm:ss");
	ZoneId myZone = ZoneId.systemDefault();
	LocalDateTime date = LocalDateTime.now();
	ZonedDateTime zdt = date.atZone(myZone);
	ZoneOffset offset = zdt.getOffset();
	Date sqlDate = Date.valueOf(LocalDate.now());
	Time sqlTime = new Time(LocalDateTime.now().atZone(myZone).toEpochSecond());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Declarations & Initalizations

		int page = 0;

		// See value of page from request
		// Parse string into int for switch

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			// redirection response to error page.
			response.sendRedirect("errors/404.html");
			return;
		} // End of parse

		switch (page) {

		case 1:

			HttpSession session = request.getSession();

			// get session and make sure it is new
			if (!session.isNew()) {
				session.invalidate();
				session = request.getSession();
			}

			// set session attributes for use later on
			session.setAttribute("name", request.getParameter("fullname"));
			session.setAttribute("email", request.getParameter("email"));
			session.setAttribute("phone", request.getParameter("phone"));
			session.setAttribute("address", request.getParameter("address"));
			session.setAttribute("delivery", request.getParameter("deliveryoption"));

			request.getRequestDispatcher("/views/OrderPage.jsp").forward(request, response);
			break;

		case 2:
			// get session and make sure it's NOT new
			session = request.getSession();
			if (session.isNew()) {
				session.invalidate();
				response.sendRedirect("errors/timeout.html");
				return;
			}

			// Get parameters from the order page and create a pizza object
			String _size = request.getParameter("size");
			int _numSauce = Integer.parseInt(request.getParameter("numSauce"));
			int _numCheese = Integer.parseInt(request.getParameter("numCheese"));
			int _numPepperoni = Integer.parseInt(request.getParameter("numPepperoni"));
			int _numBeef = Integer.parseInt(request.getParameter("numBeef"));
			int _numChicken = Integer.parseInt(request.getParameter("numChicken"));
			int _numSausage = Integer.parseInt(request.getParameter("numSausage"));
			int _numOnion = Integer.parseInt(request.getParameter("numOnion"));
			int _numMushroom = Integer.parseInt(request.getParameter("numMushroom"));
			int _numPineapple = Integer.parseInt(request.getParameter("numPineapple"));
			int _numGreenPepper = Integer.parseInt(request.getParameter("numGreenPepper"));
			int _numHotPepper = Integer.parseInt(request.getParameter("numHotPepper"));
			int _numOlive = Integer.parseInt(request.getParameter("numOlive"));

			Pricing two = new Pricing();
			two.setPrice(_size, _numSauce, _numCheese, _numPepperoni, _numBeef, _numChicken, _numSausage, _numOnion,
					_numMushroom, _numPineapple, _numGreenPepper, _numHotPepper, _numOlive);

			// Create a pizza object to represent one order
			Pizza one = new Pizza();
			one.setPizza(_size, _numSauce, _numCheese, _numPepperoni, _numBeef, _numChicken, _numSausage, _numOnion,
					_numMushroom, _numPineapple, _numGreenPepper, _numHotPepper, _numOlive);

			one.setPrice(two.getPrice());

			// Add it to the arraylist of pizza objects to print out each pizza order
			// set the arraylist as a session attribute so it can be retreived later
			list.add(one);
			session.setAttribute("cart", list);

			pricing.add(two);
			session.setAttribute("pricing", pricing);

			// get the total price before taxes for each pizza and add it to an array list
			// for calculation later
			totalPricesList.add(two.getPrice());
			session.setAttribute("subtotal", totalPricesList);

			ArrayList<Double> total = (ArrayList<Double>) session.getAttribute("subtotal");

			// set the total price through the get total method of the Pricing class
			// the method takes an arraylist of doubles and returns the total price
			Double totalPrice = two.getTotal(total);
			String totalP = df.format(totalPrice);

			// set the session attribute for total price to be retrieved later
			session.setAttribute("totalprice", totalP);

			// set the taxes to a double parameter
			// set the session attribute for total taxes that the user will pay
			Double taxSubtotal = two.getTaxSubtotal();
			String taxS = df.format(taxSubtotal);

			session.setAttribute("taxsubtotal", taxS);

			request.getRequestDispatcher("/views/AddPizza.jsp").forward(request, response);
			break;

		case 3:
			session = request.getSession();
			if (session.isNew()) {
				session.invalidate();
				response.sendRedirect("errors/timeout.html");
				return;
			}

			// create a new db connection to insert order
			OrderDBAccess db = new OrderDBAccess();

			String deliveryDateTime;
			String deliveryOption = (String) session.getAttribute("delivery");
			session.setAttribute("offset", offset);

			if (deliveryOption.equals("pick-up")) {

				// add 20 minutes for pickup
				deliveryDateTime = date.plusMinutes(20).format(dtf);
				session.setAttribute("deliveryDateTime", deliveryDateTime);

			} else if (deliveryOption.contentEquals("delivery")) {

				// add 40 minutes for delivery
				deliveryDateTime = date.plusMinutes(40).format(dtf);
				session.setAttribute("deliveryDateTime", deliveryDateTime);
			}

			request.getRequestDispatcher("/views/Receipt.jsp").forward(request, response);

			try {
				ArrayList<Pizza> pizzaCart = (ArrayList<Pizza>) session.getAttribute("cart");
				ArrayList<Pricing> pricingCart = (ArrayList<Pricing>) session.getAttribute("pricing");
				String name = (String) session.getAttribute("name");
				String email = (String) session.getAttribute("email");
				String street = (String) session.getAttribute("address");
				String phone = (String) session.getAttribute("phone");

				// if there is a connection insert into database
				if (db.connect()) {

					int delivery = 0;

					if (deliveryOption.equals("delivery")) {

						delivery = 1;

					}

					for (int i = 0; i < pizzaCart.size(); i++) {
						String size = pizzaCart.get(i).getPizzaSize();
						ArrayList<String> toppings = pizzaCart.get(i).getPizzaToppings();
						Double price = pricingCart.get(i).getPrice();
						String topping = new String();

						for (int j = 0; j < toppings.size(); j++) {
							if (j == (toppings.size() - 1)) {
								topping += toppings.get(j);
							} else {
								topping += (toppings.get(j) + ", ");

							}
						}

						// database statement
						db.insertOrder(name, phone, email, street, size, topping, delivery,
								Double.parseDouble(df.format(price)), sqlDate, sqlTime);
					}

				}
			} catch (NullPointerException e) {
				System.out.println("Cannot insert into the database! " + e.getMessage());
			} finally {
				db.closeConnection();
			}

			break;

		}// End of switch

	}// End of doPost

}
