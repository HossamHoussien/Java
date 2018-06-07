
public class Range {

	float lower;
	float upper;
	
	Range(float lower, float upper)
	{
		this.lower = lower;
		this.upper = upper;
	}
	
	
	
	public float getLower() {
		return lower;
	}
	public void setLower(float lower) {
		this.lower = lower;
	}
	public float getUpper() {
		return upper;
	}
	public void setUpper(float upper) {
		this.upper = upper;
	}
	public void printRange()
	{
		System.out.println("Lower range is: " + lower);
		System.out.println("Upper range is: " + upper +"\n\n");
	}
	
	
	
	
}


