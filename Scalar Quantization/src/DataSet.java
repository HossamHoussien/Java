import java.util.Vector;

public class DataSet {
	int average;
	float name;//MADE IT FLOAT ON PURPOSE
	Vector <Integer> Data;
	DataSet leftDataSet;
	DataSet rightDataSet;
	
	DataSet()
	{
		average = 0;
		Data = new Vector<Integer>();
		leftDataSet = new DataSet();
		rightDataSet = new DataSet();
	}
	public void setAverage(int avg)
	{
		this.average = avg;
	}
	public  void UpdateAverage()
    {
    	int count = 0;
    	int sum = 0;
    	for (int i=0; i<(this.Data).size();i++)
    	{
    		sum += (this.Data).get(i);
    		count +=1;
    	}
    	average =  sum / count;
    }
	public int getAverage()
	{
		UpdateAverage();
		return (int)average;
	}
	public void setName(float n)
	{
		name = n;
	}
	public float getName()
	{
		return name;
	}
	public void setData(Vector <Integer> img)
	{
		Data = img;
	}
	public void addData(int data)
	{
		Data.add(data);
	}
	public void printData()
	{
		for (int i = 0; i < Data.size(); i++) {
			System.out.print(Data.get(i)+" : ");
			
			
		}
	}
}
