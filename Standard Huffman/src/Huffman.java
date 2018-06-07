import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class Huffman {
	
	public int size;
	public String compressed,res2,res3;
	public Vector<Node>nodes,nodes2;
	public File inputFile ;
	
	public Huffman(){
		compressed = "";
		size = 0;
		res2 = "";
		res3 = "";
		nodes = new Vector<Node>();
		nodes2 = new Vector<Node>();
		inputFile = new File("text.txt");
	}
	
	
/*******************************************************************************/	
public void CalculateFreq(String text){
		
		int count = 0;
		
		Vector<Character>symbols = new Vector<Character>();
		
		for(int i=0; i<text.length(); i++)
		{	
			if(symbols.contains(text.charAt(i)))
			{
				continue;
			}
			symbols.add(text.charAt(i));
			
			for(int j=0; j<text.length(); j++){
				if(text.charAt(i) == text.charAt(j))
				{
					count++;
				}
			}
			
			Node temp = new Node();
			temp.freq = count;
			temp.symbol = Character.toString(text.charAt(i));	
			nodes.add(temp);
			count = 0;
		}
	}
/************************************************************************/
	public void sortNodes(Vector<Node> vec)
	{
		
		int j;
	    boolean sorting = true; 
	    while ( sorting )
	    {
	    	 
	       sorting = false; 
	       for( j=0;  j < vec.size()-1; j++ )
	       {
	       	
	          if ( vec.elementAt(j).freq > vec.elementAt(j+1).freq )
	          {
	       	   	Collections.swap(vec, j, j+1);
	       	    sorting = true;                
	          } 
	       } 
	    } 
	}
/*****************************************************************************/	
	public void buildTree(){
		
		sortNodes(nodes);
		
		while(nodes.size() != 2){

			Node n = new Node();
			n.freq = nodes.elementAt(0).freq + nodes.elementAt(1).freq;
			n.symbol = nodes.elementAt(0).symbol + nodes.elementAt(1).symbol;
			n.left = nodes.elementAt(0);
			n.right = nodes.elementAt(1);
			
			nodes.add(n);
			nodes.remove(0);
			nodes.remove(0);
			
			sortNodes(nodes);
			
		}
		
		nodes.elementAt(0).code = "0"; //left
		nodes.elementAt(1).code = "1"; //right
				
		for (int i=0; i<nodes.size(); i++){
			
			if(nodes.elementAt(i).left !=null && nodes.elementAt(i).right !=null){
				
				nodes.elementAt(i).left.code = nodes.elementAt(i).code + "0";
				nodes.elementAt(i).right.code = nodes.elementAt(i).code + "1";
				nodes.add(nodes.elementAt(i).right);
				nodes.add(nodes.elementAt(i).left);
				
			}
		}
		
		for(int i=0; i<nodes.size(); i++){
			if(nodes.elementAt(i).symbol.length() == 1){
				nodes2.add(nodes.elementAt(i));
			}
		}
		
		System.out.println(nodes2.size());
		for(int i=0; i<nodes2.size(); i++){
			System.out.println("<"+nodes2.elementAt(i).symbol+","+nodes2.elementAt(i).freq+","+nodes2.elementAt(i).code+">");
		}
		
}
/********************************************************************/
	public String compress(String text) throws IOException{
		
		CalculateFreq(text);
		buildTree();
		for(int i=0; i<text.length(); i++){
			for(int j=0; j<nodes2.size(); j++){
				if(nodes2.elementAt(j).symbol.equals(text.charAt(i)+"")){
					compressed += nodes2.elementAt(j).code;
				}
			}
		}
		System.out.println("Size of file before Comprssion = " + inputFile.length());
		
		size = compressed.length();
		byte data[] = new byte[(size + 7) / 8];
		for(int i = 0; i < size; ++i){
			if(compressed.charAt(i) == '1')data[i / 8] |= (1 << (7 - i % 8));
		}
		FileOutputStream fos = new FileOutputStream("text.txt");
		fos.write(data, 0, data.length);
		fos.close();
		
		System.out.println("Size of file after Comprssion = " + inputFile.length());
		
		return "Huffman Code = " + compressed;
	}
	
/*****************************************************************************************/	
	public String deCompress() throws IOException{

		byte[] data2 = new byte[(int) inputFile.length()];
		FileInputStream fis = new FileInputStream(inputFile);
		fis.read(data2, 0, data2.length);
		fis.close();
		for(int i = 0; i < size; ++i){
			res3 += (char)('0' + ((data2[i / 8] >> (7 - i % 8)) & 1));	
		}
		String sub="";
		for(int i=0;i<res3.length();i++){
			 sub += res3.charAt(i);
			 for(int j=0;j<nodes2.size();j++){
				 if (sub.equals(nodes2.elementAt(j).code)){
					res2 += nodes2.elementAt(j).symbol;
					sub="";
					break;
				 }
			 }
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile));
		bw.write(res2);
		bw.close();
		
		return "Original Text = " + res2;
	}
	
	
}