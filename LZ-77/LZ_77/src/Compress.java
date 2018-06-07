import java.util.*;
import java.io.*;
import java.nio.file.*;


public class Compress {
	
	//A scanner variable to read from the file
	private Scanner scanFile;
	private String Window;
	private String SearchBuffer = "" ;
	private String LookAheadBuffer =""; 
	int pos, len, counter =1;
	String next ="";
	Tag returnTag = new Tag ();
	
/****************************************************
*													*		
* 			A METHOD TO OPEN THE TEXT FILE			*			
*													*
*****************************************************/
public void Open_File (String path) throws FileNotFoundException
{
			
			scanFile = new Scanner ( new File (path));
			
}
/****************************************************
*													*		
* 			A METHOD TO COMPRESS THE TEXT FILE		*			
*													*
/****************************************************/	
public void Compress_File()   //to check if the compress done correctly or not
{
	try{
			Window = scanFile.next();
			LookAheadBuffer += (Window);
			

			while (SearchBuffer.length() < Window.length())
			{
				
					//If symbol NOT exist in search LookAheadBuffer
					if (SearchBuffer.lastIndexOf(LookAheadBuffer.charAt(0)) == -1)
					{
						
					
						returnTag.offset = 0;
						returnTag.length = 0;
						returnTag.nextSymbol = Character.toString(LookAheadBuffer.charAt(0));	
						Write_File(returnTag.getTag());
						
						SearchBuffer += Character.toString(LookAheadBuffer.charAt(0));
						LookAheadBuffer = LookAheadBuffer.substring(1);
					
					}
					
					
					
					//symbol EXIST in search LookAheadBuffer
					else 
					{
						counter = 1;
						int srch_size = SearchBuffer.length();
						String str = LookAheadBuffer.substring(0,1);
						
						if (counter < LookAheadBuffer.length())
							{str += Character.toString(LookAheadBuffer.charAt(counter));}
						//AT THE END OF FILE
						else
						{
							pos =  srch_size - SearchBuffer.lastIndexOf(str.substring(0, counter)); 
							SearchBuffer += str;
							srch_size = SearchBuffer.length();
							
							//NEXT SYMBOL
							if (counter < str.length())
								{
								next = Character.toString(str.charAt(counter));
								}
							else
								{
									next = "null";
								}
							
							len = counter;
							returnTag.offset = pos;
							returnTag.length = len;
							returnTag.nextSymbol = next;


							Write_File(returnTag.getTag());
							
						}
						
						INNER:					//LABEL
						while ( counter < LookAheadBuffer.length() && LookAheadBuffer.length() >= 0)
						{
							
							if (SearchBuffer.lastIndexOf(str) == -1)
							{
							
								pos =  srch_size - SearchBuffer.lastIndexOf(str.substring(0, counter)); 
								SearchBuffer += str;
								srch_size = SearchBuffer.length();
								
								
								if (counter < str.length())
									{
									next = Character.toString(str.charAt(counter));
									}
								else
									{
										next = "null";
									}
								
								len = counter;
								returnTag.offset = pos;
								returnTag.length = len;
								returnTag.nextSymbol = next;


								Write_File(returnTag.getTag());
								
									if (SearchBuffer.length() < Window.length()) // TO HANDLE EXCEPTION OF LOOK-AHEAD BUFFER = 0
									{
										LookAheadBuffer = Window.substring(SearchBuffer.length());
										str = LookAheadBuffer.substring(0, 1);
										counter = 0;
									}
									else
									{
										break;
									}
								
								
							
							}
							else // lsa str mwgood f search buffer
								{
									counter += 1;
									if (counter < LookAheadBuffer.length())
									{
										str += Character.toString(LookAheadBuffer.charAt(counter));
										continue INNER;
									}
									else
									{
										pos =  srch_size - SearchBuffer.lastIndexOf(str.substring(0, counter)); 
										SearchBuffer += str;
										srch_size = SearchBuffer.length();
										
										
										if (counter < str.length())
											{
											next = Character.toString(str.charAt(counter));
											}
										else
											{
												next = "null";
											}
										
										len = counter;
										returnTag.offset = pos;
										returnTag.length = len;
										returnTag.nextSymbol = next;

										
										Write_File(returnTag.getTag());
										break;
									}
								}
							}
							
						}
							
					}//OUTER while
			
	}
	catch (Exception e)
	{
		
	}
					
		
				
		
}
/****************************************************
*													*		
* 			A METHOD TO WRITE TO TEXT FILE			*			
*													*
/
 * @throws IOException ****************************************************/
public void Write_File (String x) throws IOException
{
		
			
			String newLine = System.getProperty("line.separator");

		    Files.write(Paths.get("TagOutput.txt"), x.getBytes(), StandardOpenOption.APPEND);
		    Files.write(Paths.get("TagOutput.txt"), newLine.getBytes(), StandardOpenOption.APPEND);
		    
		
		
}
/****************************************************
*													*		
* 		A METHOD TO CKECK REPEATITIVE SQUENCE 		*			
*													*
/****************************************************/
public boolean IsRepeated (String str,String look,String search)
		{
			int i=0;
			int search_lastIndex = search.charAt(search.length() - 1);
			if (str.charAt(0) == search.charAt(search_lastIndex))
			{
				while (str.matches("(.)\\1+"))
				{
					str += look.charAt(i);
					i++;
					
				}
				return true;
			}
			else 
			{
				return false;
			}
		}

/****************************************************
*													*		
* 			A METHOD TO CLOSE THE TEXT FILE			*			
*													*
/****************************************************/
public void Close_File ()
		{
				scanFile.close();
		}

	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	

