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
	public static void main(final String[] theArgs) throws IOException {
		FileReader inputStream = null;
		
//		try {
//			for (final String arg : theArgs) {
//				inputStream = new FileReader(arg);
//				BufferedReader bufferedStream = new BufferedReader(inputStream);
//				String line;
//				int count = 0;
//				while ((line = bufferedStream.readLine()) != null) {
//					System.out.print("Processing Order "+ count++ + ": ");
//					System.out.println(line); // useful for debugging
//					parseLine(line);
//					System.out.println();
//				}
//					bufferedStream.close();
//			}
//		} finally {}
		
		testParse();
//		testMyStack();
	}
	
	/**
	 * 
	 */
	public static void testBurger() {
		
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
//		parseLine("Baron Burger");
		parseLine("Double Chicken Burger with Cheese Ketchup");
		parseLine("Double Baron Burger with no Cheese but Mozzarella");
		parseLine("Single Burger with Veggies but no Lettuce");
		parseLine("Baron Burger with no Veggies Sauce but Baron-Sauce");
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

