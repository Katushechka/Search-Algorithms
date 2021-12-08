package TheMultipleKnapsackProblem;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
	private Knapsack knapsack;
	private Item item;
	private Controller controller;
	private List <Item> listItems = new ArrayList<Item>();
	private List <Knapsack> listKnapsacks = new ArrayList<Knapsack>();
	
	private void start() {    
		//Create knapsacks
		knapsack = new Knapsack(5, 5); 			//maxCapacity, availableCapacity
		listKnapsacks.add(knapsack);
		knapsack = new Knapsack(10, 10);
		listKnapsacks.add(knapsack);
		
		//Create items and put them in a list
		item = new Item (3, 3);					//value, weight
		item.calculateBenefitPerWeight();
		listItems.add(item);
		item = new Item (4, 2);
		item.calculateBenefitPerWeight();
		listItems.add(item);
		item = new Item (5, 11);
		item.calculateBenefitPerWeight();
		listItems.add(item);
		item = new Item (16, 2);			
		item.calculateBenefitPerWeight();
		listItems.add(item);
		item = new Item (4, 4);				
		item.calculateBenefitPerWeight();
		listItems.add(item);
		item = new Item (20, 2);			
		item.calculateBenefitPerWeight();
		listItems.add(item);
		item = new Item (10, 5);
		item.calculateBenefitPerWeight();
		listItems.add(item);
		item = new Item (40, 4);
		item.calculateBenefitPerWeight();
		listItems.add(item);
		item = new Item (27, 3);
		item.calculateBenefitPerWeight();
		listItems.add(item);
		controller = new Controller(knapsack, item, listItems, listKnapsacks);
		
	}
	 public static void main(String[] args) {
	    	MainClass main = new MainClass();
	    	main.start();
	  }   
	

}
