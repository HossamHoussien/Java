import java.io.File;
import java.io.IOException;

public class Clear {
	
	public void ClearFile(String path) throws IOException
	{
	
		
			File file = new File (path);

			if(file.delete())
			{
				file.createNewFile();
				
			}
			else 
			{
				//HANDELED WITH TRY-CATCH BLOCK IN GUI FORM
			}
					
		}
			
			
	}
	


