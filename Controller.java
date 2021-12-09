package TheMultipleKnapsackProblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Controller {
	private Item item;
	private Knapsack knapsack;
	private List <Item> listItems;
	private List <Knapsack> listKnapsacks;
	
	public Controller(Knapsack knapsack, Item item,  List <Item> listItems, List <Knapsack> listKnapsacks) {
		this.knapsack=knapsack;
		this.item=item;
		this.listItems=listItems;
		this.listKnapsacks=listKnapsacks;
		Collections.sort(listItems, new SortByBenefitPerWeight());
		printAllItems();
		putItemsInKnapsack();
		printResultGreedyAlgorithm();
		printListUnusedItems();
		NeighborhoodSearch();
		printResultNeighboorSearch();
	}

	//sort items, with highest benefit first
	class SortByBenefitPerWeight implements Comparator<Item> {  
	    public int compare(Item a, Item b) { 
	        return  (int) (b.getBenefitPerWeight() - a.getBenefitPerWeight()); 
	    } 
	} 
	
	public void putItemsInKnapsack() {				
		for (int i = 0; i < listItems.size(); i++) {
			int minAvailableCapacity = 1000;
			int availableCapacityAfterAddingItem =0;
			Knapsack moreSuitableKnapsack = null;
			//check if bag's capacity greater than item's weight
			for(int numKnapsak = 0; numKnapsak < listKnapsacks.size(); numKnapsak ++) {
				if(listKnapsacks.get(numKnapsak).getAvailableCapacity() >= listItems.get(i).getWeight()) {   
					//calculate bag’s available capacity after adding an item 
					availableCapacityAfterAddingItem = listKnapsacks.get(numKnapsak).getAvailableCapacity() - listItems.get(i).getWeight();    	
					//choose the bag where the item will take less space in the bag 
					if(availableCapacityAfterAddingItem < minAvailableCapacity) {
        				minAvailableCapacity = availableCapacityAfterAddingItem;
        				moreSuitableKnapsack = listKnapsacks.get(numKnapsak);
        			}
    			}   			
    		}
			//if moreSuitableKnapsack is chosen
			if(moreSuitableKnapsack != null) {
				moreSuitableKnapsack.putItemInKnapsack(listItems.get(i));		//put the item i the chosen bag
				listItems.remove(i);											//remove this item from the list of items
				moreSuitableKnapsack.setAvailableCapacity(moreSuitableKnapsack.getAvailableCapacity() -listItems.get(i).getWeight() ); //update bag's available capacity
			}
    	}
	}
	
	public void NeighborhoodSearch() {
		//for each bag
		for (int bag = 0; bag < listKnapsacks.size(); bag++) {			
			//for each item in the bag
			for (int oneItem = 0; oneItem < listKnapsacks.get(bag).getlistItemsInKnapsack().size(); oneItem++) {							
				//for each unused item:
				for (int unusedItem = 0; unusedItem < listItems.size(); unusedItem ++) {
					List <Item> copyItems = new ArrayList<Item>();
					//copy the the list with items in the current bag				
					copyItems.addAll(listKnapsacks.get(bag).getlistItemsInKnapsack());				
					
					//remove the first item from the list
					Item deletedITem = new Item(copyItems.get(oneItem).getValue(), copyItems.get(oneItem).getWeight());
					deletedITem.calculateBenefitPerWeight();
					copyItems.remove(oneItem); //delete an item that will replaced with an unused item
					
					//get weight of the "copyItems" list without one item
					int weightCopyListWithoutOneItem = 0;
					for (int y = 0; y < copyItems.size(); y++) {
						weightCopyListWithoutOneItem += copyItems.get(y).getWeight();
					}
					//if bag's capacity less than copyList's weight + one unused item's weight
					if(listKnapsacks.get(bag).getMaxCapacity() > weightCopyListWithoutOneItem + listItems.get(unusedItem).getWeight()) {
						
						// get total value of "copyItems" list with one unused item as "totalValueCopyList"
						int valueOfUnusedItem = listItems.get(unusedItem).getValue();
						int valueOfcopyItemsList = 0;
						for(int x = 0; x < copyItems.size(); x ++) {
							valueOfcopyItemsList += copyItems.get(x).getValue();
						}
						int totalValueCopyList = valueOfUnusedItem + valueOfcopyItemsList;
						
						//check if total value of bag with a new unused item will be greater than before
						if(listKnapsacks.get(bag).getTotalValueOfOneBag() < totalValueCopyList) {
							listKnapsacks.get(bag).getlistItemsInKnapsack().remove(oneItem); //delete an item that will replaced with an unused item
							listKnapsacks.get(bag).getlistItemsInKnapsack().add(oneItem, listItems.get(unusedItem)); //add unused item
							
							
							listItems.remove(unusedItem);
							listItems.add(unusedItem, deletedITem);
						
						}
						
					}									
				}				
			}
		}
	}
	
	public void printAllItems() {		
		System.out.println("\nAll items");
		for(Item a : listItems) {
    			System.out.println("w: " + a.getWeight() + "  " + "v: " + a.getValue() + "  benefit: " + a.getBenefitPerWeight());
    		}
	}
	
	public void printListUnusedItems() {		
		System.out.println("\nNon used items");
		for(Item a : listItems) {
    			System.out.println("w: " + a.getWeight() + "  " + "v: " + a.getValue() + "  benefit: " + a.getBenefitPerWeight());
    		}
	}

	public void printResultGreedyAlgorithm() {	
		System.out.println("-------------------------------------- ");
		System.out.println("GREEDY ALGORITHM: ");
		System.out.println("ITEMS IN THE BAG: ");
		for (int a = 0; a < listKnapsacks.size(); a++) {
			System.out.println("Bag " + a);
			int totalValue = 0;
			for(Item b : listKnapsacks.get(a).getlistItemsInKnapsack()) {
	    			System.out.println("w: " + b.getWeight() + "  v: " + b.getValue() + "  benefit: " + b.getBenefitPerWeight());
	    			totalValue += b.getValue();
	    		}
			System.out.println("Bag " + a + ": Total value with Greedy algorithm: " + totalValue);
		}
		
	}
	
	public void printResultNeighboorSearch() {
		System.out.println("-------------------------------------- ");
		System.out.println("NEIGHBORHOOD SEARCH: ");
		System.out.println("ITEMS IN THE BAG: ");
		for (int a = 0; a < listKnapsacks.size(); a++) {
			System.out.println("Bag " + a);
			int totalValue = 0;
			for(Item b : listKnapsacks.get(a).getlistItemsInKnapsack()) {
	    			System.out.println("w: " + b.getWeight() + "  v: " + b.getValue() + " benefit: " + b.getBenefitPerWeight());
	    			totalValue += b.getValue();
	    		
			System.out.println("Bag " + a + ": Total value with NeighboorSearch algorithm: " + totalValue);
		}		
	}	
}
