
public class Pair {
	public int Q;
	public int Inverse;
	
	Pair()
	{
		
	}
	Pair (int q, int inv)
	{
		Q = q;
		Inverse = inv;
	}
	
	public int getInverse(int q)
	{
		return this.Inverse;
	}

}
