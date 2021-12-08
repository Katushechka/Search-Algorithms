package TheMultipleKnapsackProblem;

import java.util.ArrayList;
import java.util.List;
/*
 * Class Knapsack represents a bag
 */
public class Knapsack {
	private int maxCapacity;
	private int availableCapacity;
	private List <Item> listItemsInKnapsack = new ArrayList<Item>();;

	public Knapsack(int maxCapacity, int availableCapacity) {
		this.maxCapacity=maxCapacity;	
		this.availableCapacity=availableCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	
	

	public void setAvailableCapacity(int availableCapacity) {
		this.availableCapacity = availableCapacity;
	}
	
	public int getAvailableCapacity() {
		return availableCapacity;
	}
	
	public void putItemInKnapsack(Item newItem) {
		listItemsInKnapsack.add(newItem);
	}
	
	public List <Item> getlistItemsInKnapsack(){
		return listItemsInKnapsack;
	}
	
	public int getTotalValueOfOneBag(){
		int totalValue =0;
		for (int i = 0; i < listItemsInKnapsack.size(); i++) {			
			totalValue += listItemsInKnapsack.get(i).getValue();
		}
		return totalValue;
	}

}
