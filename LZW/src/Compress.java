import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Compress 
{
/************************************************
* 												*
* 				  COMPRESS METHOD 				*
* 												*
*************************************************/	
	public static void compress(String p) throws IOException 
	{
		String T ="";
		String data ="";
		String str="";
		Vector <String> S = new Vector <String>();
		int index=128;
		Scanner input;
		
		input = new Scanner (new File (p));
		data = input.nextLine();
		input.close();
		
		T += Character.toString(data.charAt(0));
		S.addElement(Character.toString(data.charAt(1)));
		str = T+S.elementAt(0);
		
		do 
		{
			
			
			
			//exist
			
			 if (S.size() == data.length()-1)
			{
				 //END OF DATA && LAST STR IS NOT EXIST IN DICTIONARY
				 if (search(str).equals("String not found!"))
				 {
					 	File asFile = new File("ASCII.txt");
						File tagFile = new File("TagOutput.txt");
						FileWriter fooWriter;
						
						
						
							//WRITE IN ASCII FILE
							fooWriter = new FileWriter(asFile, true);
							String write = str+" "+index ;
							fooWriter.write(System.lineSeparator());
							fooWriter.write(write);
							fooWriter.close();
						    index++;
						
			          
						
						    //TO WRITE THE TAGS IN COMPRESSED FILE
						
							fooWriter = new FileWriter(tagFile, true);
							String []buffer =search(str.substring(0,str.length()-1)).split(" ");
							fooWriter.write(buffer[1]);
							fooWriter.write(System.lineSeparator());
							buffer =search(str.substring(str.length()-1)).split(" ");
							fooWriter.write(buffer[1]);
							
				             fooWriter.close();
				             break;
				 }
				 
				 //END OF DATA && LAST STR EXIST IN DICTIONARY
				 else 
				 {
					    File tagFile = new File("TagOutput.txt");
						FileWriter fooWriter;
					    fooWriter = new FileWriter(tagFile, true);
						String []buffer =search(str).split(" ");
						fooWriter.write(buffer[1]);
						fooWriter.write(System.lineSeparator());
			             fooWriter.close();
			             break;
				 }
			         
			}
		/***************************************************************************/
			 else if (!search(str).equals("String not found!"))
				{
					T = T+S.lastElement();
					 S.addElement(Character.toString(data.charAt(S.size()+1)));
		             str = T+S.lastElement();
		            	
				}
		 /******************************************************************************/	
			//NOT EXIST
			else
			{
					File asFile = new File("ASCII.txt");
					File tagFile = new File("TagOutput.txt");
					FileWriter fooWriter;
					
					//WRITE IN ASCII FILE	
					fooWriter = new FileWriter(asFile, true);
					String write = str+" "+index ;
					fooWriter.write(System.lineSeparator());
					fooWriter.write(write);
					fooWriter.close();
					index++;
					
		          
					
					//TO WRITE THE TAGS IN COMPRESSED FILE
					
					fooWriter = new FileWriter(tagFile, true);
					String []buffer =search(str.substring(0,str.length()-1)).split(" ");
					fooWriter.write(buffer[1]);
					fooWriter.write(System.lineSeparator());
					fooWriter.close();
			             
			        T = S.lastElement();
			        S.addElement(Character.toString(data.charAt(S.size()+1)));
			        str = T+S.lastElement();
			 		
			}
				
				
		}
		while (S.size() <= data.length() );
}
		
	
/************************************************
* 												*
* 				  SEARCH METHOD 				*
* 												*
*************************************************/
public static String search (String s)
	{
		String buffer = "";
		try {
			
			Scanner scan = new Scanner (new File ("ASCII.txt"));
			while (scan.hasNext())
			{
				buffer = scan.nextLine();
				
				String [] splittedLine = buffer.split(" ");
				if (splittedLine[0].equals(s))
				{
					
					return buffer;
					
				}
				else
				{
					//return "String not found!";
					
					
				}
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occured while searching in ASCII file!");
		}
		return "String not found!";
	}

}
