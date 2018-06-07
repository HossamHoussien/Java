import java.util.*;
import java.io.*;
import java.nio.file.*;


public class Decompress 
{
	
	//A scanner variable to read from the file
	private Scanner file;
	
/****************************************************
*													*		
* 			A METHOD TO OPEN THE TEXT FILE			*			
*													*
/
 * @throws FileNotFoundException ****************************************************/
public void OpenFile (String path) throws FileNotFoundException
{
			
			file = new Scanner ( new File (path));
		
					
}
	
/****************************************************
*													*		
* 		A METHOD TO DECOMPRESS THE TEXT FILE		*			
*													*
/****************************************************/	
public  void DecompressFile(String p)   
{
	
	try
	{
			
			int pos,len, pointer=0;
			String next ="";
			String Buffer, string ="";
			BufferedReader br = new BufferedReader(new FileReader(p));
	
		
        while((Buffer = br.readLine()) != null) 
        {
        	
        	String [] str  = PrepareTag(Buffer).split(",");
        	pos = Integer.parseInt(str[0]);
            len = Integer.parseInt(str[1]);
        	next = str[2];
        	
      
        	
        	if (len == 0 && pos == 0)
        	{
        		string += next;
        		//pointer +=1;
        	}
        	else
        	{
        		pointer = string.length();
        		string += string.substring(pointer-pos,(pointer-pos)+len);
        		if (Buffer.length() == 13)
        		{string += next;}
        		/*else if (Buffer.length() > 13)
        		{
        			//DO NOTHING "END OF TAGS"
        		}
        		else 
        		{
        			// TAG LENGTH < 13, WHICH MEANS THE TAG IS WRITTEN WRONGLY
        			
        		}*/
        		
        	}
        	
        	 
        }
        
        WriteFile(string);
       
        br.close();
	}
	catch (Exception e)
	{
		
	}
		
		
}

/****************************************************
*													*		
* 			A METHOD TO WRITE THE TEXT FILE			*			
*													*
/****************************************************/	
public void WriteFile (String data)
{
		try {
			

		    Files.write(Paths.get("TextOutput.txt"), data.getBytes(), StandardOpenOption.APPEND);
		    
		    
		}catch (IOException e) {
			
		}
		
}	
/****************************************************
*													*		
* 			A METHOD TO CLOSE THE TEXT FILE			*			
*													*
/****************************************************/	
public void CloseFile ()
		{
				file.close();
		}
	
/****************************************************
*													*		
* 	A METHOD TO TRIM "< | > | SPACE" THE TEXT FILE	*			
*													*
/****************************************************/	
	public String PrepareTag (String t)
		{
				String str = "";
				for (int i=0; i<t.length();)
				{
					if (t.charAt(i) == '<' ||t.charAt(i) == ' ' || t.charAt(i) == '>')
					{
						i +=1;
					}
					else
					{
						str += Character.toString(t.charAt(i));
						
						i++;
					}
				}
				
				return str;
				
		}	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
