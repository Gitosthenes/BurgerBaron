import java.io.*;

/**
 * Controller and tester for the Baron Burger program.
 *
 * @author Ka Yee Yeung, Alex Bledsoe, Benjamin Yuen
 * @version Jan 20, 2018
 */
public class Main {

	/**
	 * Main method takes the name of the text files passed through the cmd line and
	 * passes each line of the file to the parseLine method to be interpreted.
	 *
	 * @param theArgs command line arguments in the form of text file names.
	 * @throws IOException when file passed in cannot be found or is not a text file.
	 */
	public static void main(String[] theArgs) throws IOException {
		FileReader inputStream = null;		
//		theArgs = new String[] {"test.txt"};
		try {
			for (final String arg : theArgs) {
				inputStream = new FileReader(arg);
				BufferedReader bufferedStream = new BufferedReader(inputStream);
				String line;
				int count = 0;
				while ((line = bufferedStream.readLine()) != null) {
					System.out.print("Processing Order "+ count++ + ": ");
					System.out.println(line); // useful for debugging
					parseLine(line);
					System.out.println();
				}
					bufferedStream.close();
			}
		} finally {}
	
//		testParse();
		
		//Uncomment methods below to view test results of the Burger and MyStack class.
//		testToString();
		testBurger();
//		testMyStack();
	}
	
	/**
	 * Tests Burger for proper functionality.
	 */
	public static void testBurger() {
		//Tests constructor by initializing an instance of Burger.
		Burger testBaron = new Burger(true);		
		Burger testBasic = new Burger(false);
		System.out.println("Testing Baron Burger constructor: " + testBaron.toString());
		System.out.println("Testing Basic Burger constructor: " + testBasic.toString());		
		
		//Tests addPatty method. addPatty should only allow a maximum of 3 patties on the burger.
		testBasic = new Burger(false);
		for (int i = 0; i < 10; i++) {
			testBasic.addPatty();
		}
		System.out.println("Testing addPatty method: ");
		System.out.println("\tMethod will add one patty to the Basic Burger up to a maximum of 3. ");
		System.out.println("\tResult: " + testBasic.toString());
		
		//Tests changePatties method. changePatties should change all the patties on the burger to the
    	//patty type specified in the parameter.
		testBasic = new Burger(false);
		for (int i = 0; i < 10; i++) {
			testBasic.addPatty();
		}
		testBasic.changePatties(Ingredients.CHICKEN_PATTY);
		System.out.println("Testing changePatties method: ");
		System.out.println("\tMethod will changes all previous patties from previous Burger to Chicken patties.");
		System.out.println("\tResult: " + testBasic.toString());
		
		//Tests removePatty method. removePatty should keep 1 patty on the burger.
		testBasic = new Burger(false);
		for (int i = 0; i < 10; i++) {
			testBasic.removePatty();
		}		
		testBasic.changePatties(Ingredients.CHICKEN_PATTY);
		System.out.println("Testing removePatty method: ");
		System.out.println("\tMethod will removes two patties from previos Burger down to a minimum of 1.");
		System.out.println("\tResult: " + testBasic.toString());
		
		//Tests addCategory method. After method call, the burger should contain all 
		//ingredients of the specified category.
		testBasic = new Burger(false);
		System.out.println("Testing addCategory method: \n\tBefore method call: " + testBasic.toString());
		testBasic = new Burger(false);
		testBasic.addCategory("Cheese");
		// String literals that are not a category for the burger will not be affect burger.
		testBasic.addCategory("NOT CATEGORY");
		System.out.println("\tMethod will add all cheese to burger");
		System.out.println("\tAfter method call:  " + testBasic.toString());
		
		//Tests removeCategory method. After method call, the burger should remove 
		//all ingredients of the specified category.
		testBaron = new Burger(false);
		testBaron.addCategory("Cheese");
		System.out.println("Testing removeCategory method: \n\tBefore method call: " + testBaron.toString());
		testBaron = new Burger(false);
		testBaron.addCategory("Cheese");
		testBaron.removeCategory("Cheese");
		// String literals that are not a category for the burger will not be affect burger.
		testBasic.addCategory("NOT CATEGORY");
		System.out.println("\tMethod will remove all cheese from burger");
		System.out.println("\tAfter method call:  " + testBaron.toString());	
		
		// Tests addIngredients method. This method will add the ingredient in the correct spot on the burger. 
		testBasic = new Burger(false);
		System.out.println("Testing addIngredient method: \n\tBefore method call: " + testBasic.toString());
		testBasic = new Burger(false);
		testBasic.addIngredient(Ingredients.MOZZARELLA);
		testBasic.addIngredient(Ingredients.MUSHROOMS);
		testBasic.addIngredient(Ingredients.LETTUCE);
		testBasic.addIngredient(Ingredients.PICKLE);
		// String literals that are not an ingredient for the burger will not be added.
		testBaron.addIngredient("NOT INGREDIENT");
		System.out.println("\tMethod will add Mozzarella, Mushrooms, Pickle, and Lettuce");
		System.out.println("\tAfter method call: " + testBasic.toString());
		
		// Tests removeIngredients method. This method will remove the specified ingredient.
		testBaron = new Burger(true);
		System.out.println("Testing removeIngredients method: \n\tBefore method call: " + testBaron.toString());
		testBaron = new Burger(true);
		// String literals that are not an ingredient for the burger will not be removed.
		testBaron.removeIngredient("NOT INGREDIENT");
		testBaron.removeIngredient(Ingredients.BARON_SAUCE);
		testBaron.removeIngredient(Ingredients.PEPPERJACK);
		testBaron.removeIngredient(Ingredients.PICKLE);
		System.out.println("\tMethod will remove Baron-Sauce, Pepperjack, and Pickle");
		System.out.println("\tAfter method call: " + testBaron.toString());
	}
	
	/**
	 * Tests MyStack (and underlying implementation, MyLinkedList) for proper functionality.
	 * This method achieves 100% code coverage in those two classes according to EclEmma.
	 */
	public static void testMyStack() {
		//Tests constructor by initializing an instance of MyStack.
		MyStack<Integer> test = new MyStack<>();
		
		//Tests size method returns correct value.
		System.out.println("Testing size every 10 pushes (up to 50)...");
		for (int i = 1; i <= 50; i++) {
			test.push(new Integer(i));
			if (i % 10 == 0) {
				System.out.println("Expected Size: " + i + "\nActual Size: "
								   + test.size() + "\n-------------------");
			}
		}
		
		/* Tests that push, pop and peek methods function correctly 
		 * by verifying various elements in the stack. */
		System.out.println("\nTesting various elements on the stack...");
		for (int i = 0; i < 25; i++) {
			if (test.peek() % 5 == 0) {
				System.out.println("Expected value: " + (50 - i) + "\n"
						         + "Actual value: " + test.pop() + "\n------------------");
			} else {
				test.pop();
			}
		}
		
		//Tests isEmpty method by emptying the stack and verifying that it's empty.
		System.out.println("\nTesting empty status of stack...\nCurrently Empty?(Expecting false): "
						 + test.isEmpty() + "\n");
		System.out.println("Emptying stack down to 10 elements...");
		for (int i = 0; i < 15; i++) {
			test.pop();
		}
		System.out.println("Number of elements in stack: " + test.size());
		System.out.println("Currently Empty?(Expecting false): " + test.isEmpty() + "\n");
		System.out.println("Fully emptying stack...");
		while (!test.isEmpty()) {
			test.pop();
		}
		System.out.println("Number of elements in stack: " + test.size());
		System.out.println("Currently Empty?(Expecting true): " + test.isEmpty());
	}
	
	//TODO Incorporate testToString method into testBurger method.
	/** Tests toString method in Burger class for correct output. */
	public static void testToString() {
		Burger burger = new Burger(true);
		System.out.println(burger.toString());
	}
	
	/**
	 * 
	 */
	public static void testParse() {
//		parseLine("Burger");
//		parseLine("Double Baron Burger");
//		parseLine("Double Chicken Burger with Cheese Ketchup");		
		parseLine("Single Burger with Veggies but no Lettuce");
		parseLine("Baron Burger with no Veggies Sauce but Baron-Sauce");
		parseLine("Triple Chicken Burger with Onions Cheese but Cheddar");
		parseLine("Single Veggie Baron Burger");
		parseLine("Double Baron Burger with no Cheese but Mozzarella");
		parseLine("Single Burger with Veggies but no Lettuce");
		parseLine("Double Chicken Burger with Ketchup Cheddar Onions and Mushrooms");
		parseLine("Triple Baron Burger");	
		
	}
	
	/**
	 * 
	 *
	 * @param line
	 */
	public static void parseLine(String line) {
		String[] words = line.split(" ");
		Burger burger;
		
//		for(int i = 0; i < words.length; i++) {
//			System.out.println("|"+ words[i]+"|");
//		}
		
		boolean baronBurger = words[0].equals("Baron") || words[Math.min(1, words.length-1)].equals("Baron") || words[Math.min(2, words.length-1)].equals("Baron");
		burger = new Burger(baronBurger);
//		System.out.println(baronBurger);
		if(words[0].equals("Double"))
			burger.addPatty();
		if(words[0].equals("Triple")){
			burger.addPatty();
			burger.addPatty();
		}
		
		boolean chicken = words[0].equals("Chicken")|words[Math.min(1, words.length-1)].equals("Chicken");
		if(chicken) burger.changePatties("Chicken");
		boolean veggie = words[0].equals("Veggie")||words[Math.min(1, words.length-1)].equals("Veggie");
		if(veggie) burger.changePatties("Veggie");
		
		int with = 0;
		int but = 0;
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals("with")) with = i;
			if(words[i].equals("but")) but = i;
		}
		
		if(with > 0) {
			int end = words.length;
			if(but > 0) end = but;
			for(int i = with; i < end; i++) {
//				System.out.println(words[i]);
				boolean cat = isCategory(words[i]);
				if(cat){
					if (baronBurger) 
						burger.removeCategory(words[i]); 
					else 
						burger.addCategory(words[i]);
				} else {
					if (baronBurger) 
						burger.removeIngredient(words[i]); 
					else 
						burger.addIngredient(words[i]);
				}
			}
		}
		if(but > 0) {
			int end = words.length;
			for(int i = but; i < end; i++) {
//				System.out.println(words[i]);
				if (baronBurger) 
					burger.addIngredient(words[i]); 
				else 
					burger.removeIngredient(words[i]);
			}
		}

		System.out.println(burger.toString());
	}
	
	/**
	 * 
	 *
	 * @param str
	 * @return
	 */
	public static boolean isCategory(String str) {
		return str.equals("Cheese")||str.equals("Veggies")||str.equals("Sauce");
	}
}

