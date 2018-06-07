import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Decompress
{
	
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
				if (splittedLine[1].equals(s))
				{
					
					return splittedLine[0];
					
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
	
	
/************************************************
* 												*
* 			  DECOMPRESS METHOD 				*
* 												*
*************************************************/
	public static void decompress(String p) throws IOException
	{
	try {
			Scanner scan = new Scanner (new File (p));
			Vector <String> tags = new Vector <String>();
			String previous="";
			String read = "";
			int index = 128;
			int count = 1;
			
			

			//READ TAGS IN VECTOR
			while (scan.hasNext())
			{
				String buffer = scan.nextLine();
				tags.addElement(buffer);
				
			}
			scan.close();
			/*******************************************/
			
			//READ FIRST CHARACTER
			read = tags.elementAt(0);
			previous = (search(read));
			
			
			//WRITE IN TEXTOUTPUT FILE
			File textFile = new File("TextOutput.txt");
			FileWriter writer;
			writer = new FileWriter(textFile, true);
			writer.write(search(read));
			writer.close();
			
		
			/*******************************************/
			//LOOP OVER TAGS
			do
			{
				read = search(tags.elementAt(count));
				
				
				//CHARACTER EXIST
				if (!read.equals("String not found!"))
				{
					
					//WRITE IN TEXTOUTPUT FILE
					File f = new File("TextOutput.txt");
					FileWriter write;
					write = new FileWriter(f, true);
					write.write(read);
					write.close();
					
					
					
					//WRITE IN TEXTOUTPUT FILE
					File as = new File("ASCII.txt");
					FileWriter wr;
					wr = new FileWriter(as, true);
					wr.write(System.lineSeparator());
					wr.write(previous+read.substring(0, 1)+ " "+index);
					
					previous = read;
					index++;
					wr.close();
					
					
				}
				else
				{
					
					//WRITE IN TEXTOUTPUT FILE
					File as = new File("ASCII.txt");
					FileWriter wr;
					wr = new FileWriter(as, true);
					wr.write(System.lineSeparator());
					wr.write(previous+previous.substring(0, 1)+ " "+index);
					
					read = previous+previous.substring(0,1);
					previous = read;
					index++;
					wr.close();
					
					
					//WRITE IN TEXTOUTPUT FILE
					File f = new File("TextOutput.txt");
					FileWriter write;
					write = new FileWriter(f, true);
					write.write(read);
					write.close();
					
				}
				
				count++;
				
			}while (count < tags.size());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
			
}