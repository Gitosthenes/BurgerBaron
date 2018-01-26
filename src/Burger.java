/**
 * Burger class that creates a burger based on a customers order. 
 * 
 * @author Benjamin Yuen, Alex Bledsoe
 * @version Jan 16, 2018
 */
public class Burger {
	 
	/** The top half of the burger, usually a bun and veggies. */
 	private MyStack<String> myBurgerTop;
 	
 	/** The bottom half of the burger, usually a bun, patties and cheese. */
 	private MyStack<String> myBurgerBottom;
 	
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
		String patty;
		if ("Chicken".endsWith(thePattyType)) {
			patty = Ingredients.CHICKEN_PATTY;
		} else {
			patty = Ingredients.VEGGIE_PATTY;
		}
		MyStack<String> cheeseHolder = new MyStack<>();
		int currentPattyCount = myPattyCount;
		while (currentPattyCount > 0) {
			if (myBurgerBottom.peek() != Ingredients.BEEF_PATTY
					&& myBurgerBottom.peek() != Ingredients.CHICKEN_PATTY
					&& myBurgerBottom.peek() != Ingredients.VEGGIE_PATTY) {
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
			myBurgerBottom.push(patty);
			currentPattyCount++;
		}
	}
	
	/**
	 * Adds one patty to the Burger up to the maximum of 3. 
	 */
	public void addPatty() {
		if (myPattyCount < 3) {
			myBurgerBottom.push(Ingredients.BEEF_PATTY);
			myPattyCount++;
		}
	}

	/**
	 * Removes one patty from the Burger down to the minimum of 1. 
	 */
	public void removePatty() {
		if (myPattyCount > 1) {
			myBurgerBottom.pop();
			myPattyCount--;
		}
	}
	
	/**
	 * Adds all items of the type to the Burger in the proper locations. 
	 * 
	 * @param theType item that will be added to the Burger
	 */
	public void addCategory(String theType) {
		MyStack<String> catHolder = new MyStack<>();
		MyStack<String> addCatHolder = new MyStack<>();
		if ("Cheese".equals(theType)) {			
			addCatHolder.push(Ingredients.PEPPERJACK);
			addCatHolder.push(Ingredients.MOZZARELLA);
			addCatHolder.push(Ingredients.CHEDDAR);
			addCheese(addCatHolder);
			
		} else if ("Sauce".equals(theType)) {
			//Puts mayonnaise and baron sauce on top bun
			addCatHolder.push(Ingredients.BARON_SAUCE);
			addCatHolder.push(Ingredients.MAYONNAISE);
			addSauce(myBurgerTop, addCatHolder);
			
			//Puts ketchup and mustard on bottom bun
			addCatHolder.push(Ingredients.MUSTARD);
			addCatHolder.push(Ingredients.KETCHUP);
			addSauce(myBurgerBottom, addCatHolder);
			
		} else if ("Veggies".equals(theType)) {
			//Puts lettuce, tomato, and onions on top bun
			addCatHolder.push(Ingredients.ONIONS);
			addCatHolder.push(Ingredients.TOMATO);
			addCatHolder.push(Ingredients.LETTUCE);
			addCatHolder.push(Ingredients.PICKLE);			
			addVeggies(myBurgerTop, addCatHolder);
			
			//Puts mushrooms on bottom bun
			while (!(Ingredients.KETCHUP.equals(myBurgerBottom.peek()) 
					|| Ingredients.MUSTARD.equals(myBurgerBottom.peek())
					|| Ingredients.BUN.equals(myBurgerBottom.peek()))) {
				catHolder.push(myBurgerBottom.pop());				
			}
			myBurgerBottom.push(Ingredients.MUSHROOMS);
			while(!catHolder.isEmpty()) {
				myBurgerBottom.push(catHolder.pop());
			}
		}
	}
		
	/**
	 * Removes all items of the type from the Burger. 
	 * 
	 * @param theType item that will be remove to the Burger
	 */
	public void removeCategory(String theType) {
		MyStack<String> removeIngred = new MyStack<>();
		if ("Cheese".equals(theType)) {
			//Removes cheddar, mozzarella, and pepperjack from top bun
			removeIngred.push(Ingredients.CHEDDAR);
			removeIngred.push(Ingredients.MOZZARELLA);
			removeIngred.push(Ingredients.PEPPERJACK);
			removeCatHelp(myBurgerBottom, removeIngred);
			
		} else if ("Sauce".equals(theType)) {
			//Removes mayonnaise and baron sauce on top bun
			removeIngred.push(Ingredients.MAYONNAISE);
			removeIngred.push(Ingredients.BARON_SAUCE);
			removeCatHelp(myBurgerTop, removeIngred);
			
			//Removes ketchup and mustard on bottom bun
			removeIngred.push(Ingredients.KETCHUP);
			removeIngred.push(Ingredients.MUSTARD);
			removeCatHelp(myBurgerBottom, removeIngred);
			
		} else if ("Veggies".equals(theType)) {
			//Removes lettuce, tomato, pickles, and onions on top bun
			removeIngred.push(Ingredients.PICKLE);
			removeIngred.push(Ingredients.LETTUCE);
			removeIngred.push(Ingredients.TOMATO);
			removeIngred.push(Ingredients.ONIONS);
			removeCatHelp(myBurgerTop, removeIngred);
			
			//Removes mushrooms on bottom bun
			removeIngred.push(Ingredients.MUSHROOMS);
			removeCatHelp(myBurgerBottom, removeIngred);
		}
	}
	
	/**
	 * Adds the ingredient type to the Burger in the proper location.
	 * 
	 * @param theType ingredient that will be added to the Burger
	 */
	public void addIngredient(String theType) {
		MyStack<String> ingredHolder = new MyStack<>();
		MyStack<String> addIngredHolder = new MyStack<>();		
		addIngredHolder.push(theType);
		if (Ingredients.CHEDDAR.equals(theType) || Ingredients.MOZZARELLA.equals(theType)
				|| Ingredients.PEPPERJACK.equals(theType)) {
			addCheese(addIngredHolder);
			
		} else if (Ingredients.MAYONNAISE.equals(theType) || Ingredients.BARON_SAUCE.equals(theType)) {			
			addSauce(myBurgerTop, addIngredHolder);
		
		} else if (Ingredients.KETCHUP.equals(theType) || Ingredients.MUSTARD.equals(theType)) {
			addSauce(myBurgerBottom, addIngredHolder);
			
		} else if (Ingredients.LETTUCE.equals(theType) || Ingredients.TOMATO.equals(theType)
				|| Ingredients.ONIONS.equals(theType) || Ingredients.PICKLE.equals(theType)) {
			addVeggies(myBurgerTop, addIngredHolder);
			
		} else if (Ingredients.MUSHROOMS.equals(theType)) {
			while (!(Ingredients.KETCHUP.equals(myBurgerBottom.peek()) 
					|| Ingredients.MUSTARD.equals(myBurgerBottom.peek())
					|| Ingredients.BUN.equals(myBurgerBottom.peek()))) {
				ingredHolder.push(myBurgerBottom.pop());				
			}
			myBurgerBottom.push(Ingredients.MUSHROOMS);
			while(!ingredHolder.isEmpty()) {
				myBurgerBottom.push(ingredHolder.pop());
			}
		}
	}
	
	/**
	 * Removes the ingredient type from the Burger.
	 * 
	 * @param theType ingredient that will be remove to the Burger
	 */
	public void removeIngredient(String theType) {
		if (theType.equals(Ingredients.MAYONNAISE) || theType.equals(Ingredients.BARON_SAUCE)
				|| theType.equals(Ingredients.LETTUCE) || theType.equals(Ingredients.TOMATO)
				|| theType.equals(Ingredients.ONIONS) || theType.equals(Ingredients.PICKLE)) {			
			removeIngredHelp(myBurgerTop, theType);
		
		} else {
			removeIngredHelp(myBurgerBottom, theType);			
		} 
	}
	
	/**
	 * Helper method that puts cheese on bottom bun. 
	 * 
	 * @param holder holds ingredients
	 * @param cheeseBot cheese on bottom
	 * @param cheeseMid cheese on middle
	 * @param cheeseTop cheese on top
	 */
	private void addCheese(MyStack<String> cheeseHolder) {	
		MyStack<String> holder = new MyStack<>();
		// Only enter if there are more than 1 patty
		while (myPattyCount > 1) {
			holder.push(myBurgerBottom.pop());
			myPattyCount--;
		}
		while (!cheeseHolder.isEmpty()) {
			myBurgerBottom.push(cheeseHolder.pop());
		}
		while (!holder.isEmpty()) {
			myBurgerBottom.push(holder.pop());
			myPattyCount++;
		}		
	}
	
	/**
	 * Helper method that puts veggies on current bun. 
	 * 
	 * @param currentBun either the top or bottom bun
	 * @param holder holds ingredients
	 * @param sauce1 first sauce on the current bun
	 * @param sauce2 second sauce on the current bun
	 * @param veggieBott veggie on bottom
	 * @param veggieMid veggie in middle
	 * @param veggieTop veggie on top
	 */
	private void addVeggies(MyStack<String> currentBun, MyStack<String> addVegHolder) {
		MyStack<String> holder = new MyStack<>();
		while (!currentBun.isEmpty()) {
			holder.push(currentBun.pop());
		}	
		if (Ingredients.PICKLE.equals(addVegHolder.peek())) {
			currentBun.push(addVegHolder.pop());
		}		
		while (!holder.isEmpty()) {
			currentBun.push(holder.pop());
		}
		while (!addVegHolder.isEmpty()) {
			currentBun.push(addVegHolder.pop());
		}
		
	}
	
	/**
	 * Helper method that puts sauce on the current bun. 
	 * 
	 * @param currentBun either top or bottom bun
	 * @param holder hold ingredients
	 * @param bottomSauce sauce on bottom
	 * @param topSauce sauce on top
	 */
	private void addSauce(MyStack<String> currentBun, MyStack<String> addSauceholder) {
		MyStack<String> holder = new MyStack<>();
		while (currentBun.peek() != Ingredients.BUN) {
			holder.push(currentBun.pop());
		}	
		while (!addSauceholder.isEmpty()) {
			currentBun.push(addSauceholder.pop());
		}
		while (!holder.isEmpty()) {
			currentBun.push(holder.pop());
		}
	}
	
	/**
	 * Removes ingredients from the current bun.
	 * 
	 * @param currentBun either top or bottom bun 
	 * @param holder holds ingredients
	 * @param ingredBot ingredient on bottom
	 * @param ingredMid ingredient in middle
	 * @param ingredTop ingredient on top
	 */
	private void removeCatHelp(MyStack<String> currentBun, MyStack<String> removeIngred) {		
		MyStack<String> holder = new MyStack<>();
		while (!removeIngred.isEmpty()) {
//			System.out.println(currentBun.peek()+", "+removeIngred.peek());
			if (currentBun.peek().equals(removeIngred.peek())) {
				currentBun.pop();
				removeIngred.pop();
			} else {
				holder.push(currentBun.pop());
			}			
		}					
		while (!holder.isEmpty()) {
			currentBun.push(holder.pop());
		}
	}
	
	/**
	 * Helper method to remove an ingredient from the bun.
	 * 
	 * @param currentBun either top or bottom bun
	 * @param removeIngred ingredient to remove
	 */
	private void removeIngredHelp(MyStack<String> currentBun, String remove) {
		MyStack<String> holder = new MyStack<>();
		while (!currentBun.isEmpty()) {
			if (currentBun.peek().equals(remove)){
				currentBun.pop();
			} else {
				holder.push(currentBun.pop());
			}
		}
		while (!holder.isEmpty()) {
			currentBun.push(holder.pop());
		}
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
	
	/**
	 * Converts the Burger to a String for display.
	 * Empties both burger stack fields in process.
	 */
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		int bottom = myBurgerBottom.size();
		sb.append("[");
		//Builds full burger in correct order (top - bottom) in myBurgerBottom stack.
		while (!myBurgerTop.isEmpty()) {
			myBurgerBottom.push(myBurgerTop.pop());
		}
		//Appends ingredients in myBottomBurger (all ingredients for burger) to string.
		while (!myBurgerBottom.isEmpty()) {
			sb.append(myBurgerBottom.peek());
			sb.append(", ");
			myBurgerTop.push(myBurgerBottom.pop());
		}
		if (sb.length() > 1) {
			sb.delete(sb.length()-2, sb.length());
		}		
		sb.append("]");		
		while (bottom > 0) {
			myBurgerBottom.push(myBurgerTop.pop());
			bottom--;
		}		
		return sb.toString();		
	}	
}
