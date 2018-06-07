public class Tag {
	
	public int offset,length;
	public String nextSymbol;
	
	
	public String getTag()
	{
		return ("< " + this.offset +" , " + this.length + " , " + this.nextSymbol + " >" );	
		
	}
	
	
	
}
