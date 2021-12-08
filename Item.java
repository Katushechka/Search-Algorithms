package TheMultipleKnapsackProblem;

public class Item {
	private int value;
	private int weight;
	private int benefitPerWeight;
	
	public Item (int value, int weight) {
		this.value=value;
		this.weight=weight;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void calculateBenefitPerWeight() {
		benefitPerWeight = value/weight;
	}
	
	public int getBenefitPerWeight() {
		return benefitPerWeight;
	}

}
