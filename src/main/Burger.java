package main;

/**
 * 
 * @author Benjamin Yuen
 * @version Jan 16, 2018
 */
public class Burger {
	 
	/** The top half of the burger, usually a bun and veggies. */
 	private MyStack<Ingredients> myBurgerTop;
 	
 	/** The bottom half of the burger, usually a bun, patties and cheese. */
 	private MyStack<Ingredients> myBurgerBottom;
 	
 	/** Keeps track of the number of patties on the burger. */
 	private int myPattyCount;

	/**
	 * A constructor that initializes a Burger with only a bun and
	 * patty if theWorks is false and a Baron Burger if theWorks is true.
	 * 
	 * @param theWorks determines if the Burger is a Baron Burger or not.
	 */
	public Burger (boolean theWorks) {
		myBurgerTop = new MyStack<>();
		myBurgerBottom = new MyStack<>();
			
		if (theWorks) {
			makeBaron();
		} else {
			makeBasic();
		}
		
		myPattyCount++;
	}

	/**
	 * Changes all patties in the Burger to the pattyType.
	 * 
	 * @param thePattyType the patty type that will change all patties 
	 */
	public void changePatties(String thePattyType) {
		Ingredients patty;
		if ("Chicken".endsWith(thePattyType)) {
			patty = Ingredients.CHICKEN_PATTY;
		} else {
			patty = Ingredients.VEGGIE_PATTY;
		}
		MyStack<Ingredients> cheeseHolder = new MyStack<>();
		int currentPattyCount = myPattyCount;
		while (currentPattyCount > 0) {
			if (myBurgerBottom.peek() != Ingredients.BEEF_PATTY) {
				cheeseHolder.push(myBurgerBottom.pop());
			} else {
				myBurgerBottom.pop();
				currentPattyCount--;
			}
		}
		myBurgerBottom.push(patty);
		currentPattyCount++;
		while (!cheeseHolder.isEmpty()) {
			myBurgerBottom.push(cheeseHolder.pop());
		}
		while (currentPattyCount < myPattyCount) {
			myBurgerBottom.push(cheeseHolder.pop());
			currentPattyCount++;
		}
	}
	
	/**
	 * Adds one patty to the Burger up to the maximum of 3. 
	 */
	public void addPatty() {
		myBurgerBottom.push(Ingredients.BEEF_PATTY);
		myPattyCount++;
	}

	/**
	 * Removes one patty from the Burger down to the minimum of 1. 
	 */
	public void removePatty() {
		
	}
	
	/**
	 * Adds all items of the type to the Burger in the proper locations. 
	 * 
	 * @param theType item that will be added to the Burger
	 */
	public void addCategory(String theType) {
		
	}
	
	/**
	 * Removes all items of the type from the Burger. 
	 * 
	 * @param theType item that will be remove to the Burger
	 */
	public void removeCategory(String theType) {
		
	}
	
	/**
	 * Adds the ingredient type to the Burger in the proper location.
	 * 
	 * @param theType ingredient that will be added to the Burger
	 */
	public void addIngredient(String theType) {
		
	}
	
	/**
	 * Removes the ingredient type from the Burger.
	 * 
	 * @param theType ingredient that will be remove to the Burger
	 */
	public void removeIngredient(String theType) {
		
	}
	
	/**
	 * Converts the Burger to a String for display.
	 */
	@Override
	public String toString() {
		
		return null;		
	}
	
	/**
	 * Builds a standard Baron Burger.
	 * Constructed in two halves to make adding and changing patties easier.
	 */
	private void makeBaron() {
		myBurgerBottom.push(Ingredients.BUN);
		myBurgerBottom.push(Ingredients.KETCHUP);
		myBurgerBottom.push(Ingredients.MUSTARD);
		myBurgerBottom.push(Ingredients.MUSHROOMS);
		myBurgerBottom.push(Ingredients.BEEF_PATTY);
		myBurgerBottom.push(Ingredients.CHEDDAR);
		myBurgerBottom.push(Ingredients.MOZZARELLA);
		myBurgerBottom.push(Ingredients.PEPPERJACK);
		
		myBurgerTop.push(Ingredients.PICKLE);
		myBurgerTop.push(Ingredients.BUN);
		myBurgerTop.push(Ingredients.MAYONNAISE);
		myBurgerTop.push(Ingredients.BARON_SAUCE);
		myBurgerTop.push(Ingredients.LETTUCE);
		myBurgerTop.push(Ingredients.TOMATO);
		myBurgerTop.push(Ingredients.ONIONS);
	}
	
	/**
	 * Builds a basic burger (buns and patty).
	 * Constructed in two halves to make adding and changing patties easier.
	 */
	private void makeBasic() {
		myBurgerBottom.push(Ingredients.BUN);
		myBurgerBottom.push(Ingredients.BEEF_PATTY);
		
		myBurgerTop.push(Ingredients.BUN);
	}
}
