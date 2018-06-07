import java.util.Vector;

public class Node {
	int average;
	Vector <Integer> Data;
	
	Node()
	{
		
	}

	public int getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public Vector<Integer> getData() {
		return Data;
	}

	public void setData(Vector<Integer> data) {
		Data = data;
	}
	public void addData(int d)
	{
		Data.add(d);
	}

}
