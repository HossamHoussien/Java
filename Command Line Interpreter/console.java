/**************************************
*	Hossam Houssien  20140098
*	Alaa Ehab Ali    20140174
*	Menna El-gohary  20140084
*************************************/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;






public class console {
	
	public static void main (String [] args)
	{
		new console();
		
	}

	public JFrame frmConsole;
	public JTextField input;
	public JTextField defaultText;
	public JTextPane text;
	public JScrollPane scrollpane;
	public StyledDocument document;
	
	
	//
	ArrayList <String> recent_used = new ArrayList <String> ();
	int recent_used_id = 0;
	int recent_used_max = 10;

	
	public console() 
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {}
		
		
		frmConsole = new JFrame();
		frmConsole.setTitle("Console");
		frmConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		text = new JTextPane ();
		text.setEditable(false);
		text.setFont(new Font ("Courier New" , Font.PLAIN, 12));
		text.setOpaque(false);
			
		
		input = new JTextField();
		input.setName("");
		input.setEditable(true);
		input.setFont(new Font ("Courier New" , Font.PLAIN, 12));
		input.setForeground(Color.white);
		input.setCaretColor(Color.green);	//CURSOR COLOR
		input.setOpaque(false);
		
		
		
		scrollpane = new JScrollPane (text);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setBorder(null);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);
		
		
		frmConsole.getContentPane().add(input, BorderLayout.SOUTH);
		frmConsole.getContentPane().add(scrollpane, BorderLayout.CENTER);
		
		frmConsole.getContentPane().setBackground(new Color (50,50,50));
		
		frmConsole.setSize(660, 350);
		frmConsole.setLocationRelativeTo(null); // LOCATE THE WINDOW TO CENTER OF SCREEN
		frmConsole.setResizable(false);
		frmConsole.setVisible(true);
		
		
		
		document = text.getStyledDocument();
		

		
		input.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String text = input.getText();
				
				if (text.length() > 0)
				{
					recent_used.add(text);
					recent_used_id = 0;
					
					doCommand (text);
					
					scrollBottom();		//TO SCROLL DOWN AFTER TYPING 
					input.selectAll(); //TO HIGHLIGHT TEXT IN INPUT
				}
			}
		});
		
/*****************************************************************/	
		input.addKeyListener(new KeyListener() {
			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if (e.getKeyCode() == KeyEvent.VK_UP && !recent_used.isEmpty())
				{
					if (recent_used_id < (recent_used_max - 1) && recent_used_id < (recent_used.size() - 1))
					{
						recent_used_id ++;
					}
					input.setText(recent_used.get(recent_used.size() - 1 - recent_used_id));
					
					
					
						
					
					
				}
				
				else if (e.getKeyCode() == KeyEvent.VK_DOWN && !recent_used.isEmpty())
				{
					if (recent_used_id > 0)
					{
						recent_used_id --;
					}
					input.setText(recent_used.get(recent_used.size() - 1 - recent_used_id));
					
				}
			}
/*******************************************************************/			
			@Override
			public void keyTyped(KeyEvent e) 
			{
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
		
		});
		
		
		
	}
/************************************************/
	public void scrollTop()
	{
		text.setCaretPosition(0);
	}
/********************************************************/
	public void scrollBottom()
	{
		text.setCaretPosition(text.getDocument().getLength());

	}
	
/********************************************************/
	public void print(String s)
	{
		Color c = new Color (255,255,255); //WHITE
		Style style = text.addStyle("Style",null);
		StyleConstants.setForeground(style, c);
	
		try 
		{
			document.insertString(document.getLength(), s, style);
		} 
		catch (Exception e) {println("An error occuered while printing to the text!");}
		
	}

/********************************************************/	
	public void println (String s)
	{
		print (s + "\n");
	}
	
/********************************************************/
	public void clear ()
	{
		try {
		
			
			
		
			for (int i=1; i <= 21 ; i++)
			{
				print("\n");
				
			}
			
			 
		} catch (Exception e) {println("An error occuered while clearing the text!");}
	}
/*********************************************************/
	public void exit ()
	{
		System.exit(0);
	}
/*************************************************************/
	public void help ()
	{
		 println("clear : clear the current terminal screen.");
         println("cd : changes the current directory to another one.");
         println("ls : These programs list each given file or directory name. Directory contents are sorted alphabetically. For ls, files are by default listed in columns, sorted vertically, if the standard output is a terminal; otherwise, they are listed one per line.");
         println("Pwd : Display current user directory.");
         println("cat : Concatenate files and print on the standard output.");
         println("args : List all command arguments");
         println("date :  Current date/time");
         println("exit : Stop all");
         println("Cp : Copy File.");
         println("Mv : move file.");
         println("Rm : Delete file ");
         println("more : Let us display and scroll down the output in one direction only. You can scroll page by page or line by line.\n"
         +"rmdir : removes each given empty directory \n"
         +" If the file is not exist, it will be created."
         + ">> : Redirect the output to be written to a file using the redirect >> create/append to file operator.\n" 
         + "If the file is not exist, it will be created.\n" 
         +"mkdir : creates a directory with each given name \n"
         + "> : Redirect the output to be written to a file using the redirect > create/replace file operator.\n");
		
	}

/**********************************************************/
	public void makedir(String []command,boolean fullPath)
	{
		String filename = command [2];
		if (fullPath)
		{
			String path = command[1];
			File file1 = new File(path+"\\"+filename);
			
			 
				if (!file1.exists()) 
				{
					if (file1.mkdirs()) 
					{
						println("Directory is created!");
					}
					else 
					{
						println("Failed to create directory!");
						
					}
				}
		}
		else 
		{
		
        
			File file = new File(filename);
			if (!file.exists()) 
			{
				if (file.mkdir()) 
				{
					println("Directory is created!");
				}
				else 
				{
					println("Failed to create directory!");
				}
			}
		}
	}
/**********************************************************/
	public String pwd ()
	{
		String current = System.getProperty("user.dir");
			return(current);	
	}
/*************************************************************/
	public void cd(String[] s)
	{
		String dir = s[1]; 
		System.setProperty("user.dir",dir);
	}
/************************************************************/
	public String showDate ()
	{
		DateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String returnString = dateFormat.format(date);
		println(dateFormat.format(date));
		return returnString;
	}
/***********************************************************/
	public void cat(String []commandpath)
	{
		try{
		String path1,path2;
		if (commandpath.length == 2)
		{ 
			path1 = commandpath[1];
			BufferedReader get = new BufferedReader(new FileReader(path1));
        	String line;
        	while((line = get.readLine()) != null)
        	{
                  println(line);
            }
        	get.close();
			
		}
		else if (commandpath.length == 3) 
		{
			    path1 = commandpath[1];
			    path2 = commandpath[2];
			    
			    //DISPLAY FIRST FILE
			    
			    BufferedReader get1 = new BufferedReader(new FileReader(path1));
	        	String line1;
	        	while((line1 = get1.readLine()) != null)
	        	{
	                  println(line1);
	            }
	        	get1.close();
	        	
	        	//DISPLAY SECOND FILE
	        	
				BufferedReader get2 = new BufferedReader(new FileReader(path2));
	        	String line2;
	        	while((line2 = get2.readLine()) != null)
	        	{
	                  println(line2);
	            }
	        	get2.close();          
		}
		
        }
        catch (Exception e)
        {
        	println("An error occured while concatenating the files!");
        }
         
	}
/********************************************************/
	public void commandHelp(String [] s)
	{
		
		 if (s[0].contains("clear"))
                println("This command can be called to clear the current terminal screen and it can be redirected to clear the screen of some other terminal.");
         else if (s[0].contains("cd"))
                println("This command changes the current directory to another one.");
         else if (s[0].contains("Is"))
                println("These programs list each given file or directory name. Directory contents are sorted alphabetically. For ls, files are by default listed in columns, sorted vertically, if the standard output is a terminal; otherwise, they are listed one per line.");
         else if (s[0].contains("pwd"))
                println("Display current user directory");
         else if (s[0].contains("Cp"))
                println("If the last argument names an existing directory, cp copies each other given file into a file with the same name in that directory. Otherwise, if only two files are given, it copies the first onto the second. It is an error if the last argument is not a directory and more than two files are given. By default, it does not copy directories.");
         else if (s[0].contains("Mv"))
                println("If the last argument names an existing directory, mv moves each other given file into a file with the same name in that directory. Otherwise, if only two files are given, it moves the first onto the second. It is an error if the last argument is not a directory and more than two files are given. It can move only regular files across file systems. If a destination file is unwritable, the standard input is a tty, and the –f or --force option is not given, mv prompts the user for whether to overwrite the file. If the response does not begin with y or Y, the file is skipped.");
         else if (s[0].contains("Rm"))
                println("rm removes each specified file. By default, it does not remove directories. If a file is unwritable, the standard input is a tty, and the -f or --force option is not given, rm prompts the user for whether to remove the file. If the response does not begin with y or Y, the file is skipped.\n" +
                			"rm can be used to remove directories and its subdirectories and files recursively suing option -r");
         else if (s[0].contains("mkdir"))
                println("mkdir creates a directory with each given name. By default, the mode of created directories is 0777 minus the bits set in the umask.");
         else if (s[0].contains("rmdir"))
                println("rmdir removes each given empty directory. If any nonoption argument does not refer to an existing empty directory, it is an error.");
   
         else if (s[0].contains("date"))
                println("To display or to set the date and time of the system. The format for setting date is [MMDDhhmm[[CC]YY][.ss]]");
         else if (s[0].contains("echo"))
                println("Prints its arguments to the screen.");
         else if (s[0].contains("cat"))
                println("Concatenate files and print on the standard output.");
         else if (s[0].contains("uname"))
                println("Gets name and information about the current kernel.");
         else if (s[0].contains("users"))
                println("Displays the current users that are logged on the system.");
         else if (s[0].contains("who"))
                println("Output who is logged in on local machines. Similar to users.");
         else if (s[0].contains("more"))
                println("Let us display and scroll down the output in one direction only. You can scroll page by page or line by line.");
         else if (s[0].contains("less"))
                println("Like more but more enhanced. It support scroll forward and backward (by arrows).");
         else if (s[0].contains(">"))
                println("Redirect the output to be written to a file using the redirect > create/replace file operator.\n" +"If the file is not exist, it will be created.");
         else if (s[0].contains(">>"))
                println("Redirect the output to be written to a file using the redirect >> create/append to file operator.\n" +"If the file is not exist, it will be created.");
         else if (s[0].contains("<"))
                println("Redirect the input to be taken from a file.");
         else if (s[0].contains("args"))
                println("Use args to display the arguments of each command.");
         else 
         {
        	 println("No such command exist!");
         }
			
		
	}

	
/*******************************************************/
	public void redirect1 (String []x)//OVERWRITE
	{
		String []splitted = x[0].split((">"));
		
		if (splitted[0].equalsIgnoreCase("pwd"))
		{
			String output =  pwd();
			stringToFile(output,splitted[1]);
		}
		
		/*else if (splitted[0].equalsIgnoreCase("ls"))
		{
			String output = list(splitted[1]);
			stringToFile(output,splitted[1]);
		}
		else if (splitted[0].equalsIgnoreCase("cat"))
		{
			String output = cat();
			stringToFile(output,splitted[1]);
		}
		else if (splitted[0].equalsIgnoreCase("args"))
		{
			String output = args();
			stringToFile(output,splitted[1]);
		}
		else if (splitted[0].equalsIgnoreCase("help"))
		{
			String output = commandHelp(s);
			stringToFile(output,splitted[1]);
		}
		
	*/	

	}
	/*************************************************/
	public void stringToFile( String text, String fileName )
	 {
	 try
	 {
	    File file = new File( fileName );

	    // if file doesnt exists, then create it 
	    if ( ! file.exists( ) )
	    {
	        file.createNewFile( );
	    }

	    FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
	    BufferedWriter bw = new BufferedWriter( fw );
	    bw.write( text );
	    bw.close( );
	    //System.out.println("Done writing to " + fileName); //For testing 
	 }
	 catch( IOException e )
	 {
	 System.out.println("Error: " + e);
	 e.printStackTrace( );
	 }
	} 
/*************************************************************/
	public void list(String p)
	{
		String[] paths;
		File f = new File (p);
	      
	      try{      
	         // returns pathnames for files and directory
	         paths = f.list();
	         
	         // for each pathname in pathname array
	         for(String path:paths)
	         {
	            // prints files and directories paths
	            println(path);
	         }
	      }
	      
	      catch (Exception ex){}
	}
/********************************************************/
	@SuppressWarnings("resource")
	public void move (String []s)
	{
		InputStream inStream = null;
		OutputStream outStream = null;

		
			try {
				
				if (s.length > 4 || s.length < 3)//TO HANDLE NO. OF ARGUMENTS
				{
					println("mv must take two arguments! \"mv Source-Path Destination-Path\"");
				}
				/********************************/
				else if (s.length == 3)
				{
					File file1 = new File(s[1]);
					File file2 = new File(s[2]);
					Path source = Paths.get(s[1]);
					Path dest  =Paths.get(s[(s.length)-1]);
					
					if (source.isAbsolute() && dest.isAbsolute())//IS_ABSOLUTE RETURN TRUE IF PATH REFERS TO FILE (OR)FOLDER 
					{
						if (file1.isFile() && file1.exists()&&dest.isAbsolute())//IN CASE TWO FILES ARE GIVEN
						{
							
							file2.setWritable(true);
							inStream = new FileInputStream(file1);
							outStream = new FileOutputStream(file2,true);
				    	   
				    	    byte[] buffer= new byte[1024];
				    	    int length;
				    	   
				    	    //copy the file1 content in bytes
				    	    
				    	    	if (file2.exists())
				    	    	{
				    	    		while ((length = inStream.read(buffer)) > 0)
						    	    {
				    	    			outStream.write(buffer, 0, length);
						    	    }
				    	    		outStream.close();
				    	    		 inStream.close();
				    	    	}
				    	    	else 
				    	    	{
				    	    		file2.createNewFile();
				    	    		outStream = new FileOutputStream(file2,true);
				    	    		
				    	    		
				    	    		
				    	    		while ((length = inStream.read(buffer)) > 0)
						    	    {
				    	    			outStream.write(buffer, 0, length);
						    	    }
				    	    		
				    	    		
				    	    		outStream.close();
				    	    		 inStream.close();
						    	    
				    	    	}

				    	    	outStream.close();
			    	    		 inStream.close();
				    	    //delete the original file
				    	    file1.delete();

				    	   println("File is moved successfully!");
							
						}
						
						
					}
				}
				/********************************/
				else if (s.length == 4)
					{
						
						Path source_1 = Paths.get(s[1]);
						Path source_2 = Paths.get(s[2]);
						Path dest_1  =Paths.get(s[3]);
		
						Files.move(source_1, dest_1.resolve(source_1.getFileName()), StandardCopyOption.REPLACE_EXISTING);
						Files.move(source_2, dest_1.resolve(source_2.getFileName()), StandardCopyOption.REPLACE_EXISTING);
						
					}
					
					
					else 
					{
						println("You entered wrong directory!");
					}
				
				
			} 
			catch (DirectoryNotEmptyException n)
			{
				
			}
			catch (IOException e) {
				println("Error occured while moving the file!");
			}
		

	}
/*******************************************************/
	public void copy (String [] s)
	{
		

		
			try {
				
				if (s.length > 4 || s.length < 3)//TO HANDLE NO. OF ARGUMENTS
				{
					println("cp takes two or three arguments! \"cp Source-Path Destination-Path\"");
				}
				/********************************/
				else if (s.length == 3)
				{
				
					Path source = Paths.get(s[1]);
					Path dest = Paths.get(s[2]);
					if (dest.toFile().isDirectory())//ONE FILE - DIR
					{
						Files.copy(source, dest.resolve(source.getFileName()));
						
					}
					
					else if (source.toFile().isFile() && dest.toFile().isFile())//TWO FILES
					{
						  
						  Path path2 = Paths.get(s[2]);
						    
						  //READ FROM FIRST FILE TO BUFFER STRING THEN WRITE THAT BUFFER INTO THE SECOND FILE
						  Scanner scanner = new Scanner(new File(s[1]));
						  scanner.useDelimiter("\n");
						  
						  String content = scanner.next();
						  Files.write(path2, content.getBytes(), StandardOpenOption.APPEND);
						  scanner.close();
				        
					}
					
					else 
					{
						println(dest+" is not a directory!");
					}
					
						
					
				}
				
				/********************************/
				else if (s.length == 4)
					{
						
						Path source_1 = Paths.get(s[1]);
						Path source_2 = Paths.get(s[2]);
						Path dest  =Paths.get(s[3]);
						if (dest.toFile().isDirectory())
						{
						
							Files.copy(source_1, dest.resolve(source_1.getFileName()),StandardCopyOption.REPLACE_EXISTING);
							Files.copy(source_2, dest.resolve(source_2.getFileName()),StandardCopyOption.REPLACE_EXISTING);
						}
						else 
						{
							println(dest+" is not a directory!");
						}
					}
				
				
			} 
			catch (DirectoryNotEmptyException n)
			{
				
			}
			catch (IOException e) {
				println("Error occured while moving the file!");
			}
	    	
	}
/**************************************************************/
	public void args ()
	{
		 println("clear : takes no args.");
         println("cd :takes the name of two folders.");
         println("ls : takes the name of folder.");
         println("Pwd :takes no args.");
         println("cat :takes the path of one or more files.");
         println("args :takes no args");
         println("date :takes no args");
         println("exit :takes no args");
         println("Cp : takes the name of two files.");
         println("Mv : takes name of file , place ,rename if you want .");
         println("Rm :takes name of file ");
         println("mkdir : takes name of directory. \n"+"more :takes no args"+
                 "rmdir : take name of directory \n"+
                 "help:takes no args \n");
	}
	/***********************************/
	public void rmdir (String []k)
	{
		String [] split = k[0].split((" "));
		
		
		
		if (split.length == 3){
			if (split[1].equalsIgnoreCase ("-i") ||split[1].equalsIgnoreCase ("-I")||split[1].equalsIgnoreCase ("--interactive"))
			{
				File file1 = new File(split[2]);
					if (file1.isFile())
					{
						file1.delete();
					}
			}
			else if (split[1].equalsIgnoreCase ("-f") ||split[1].equalsIgnoreCase ("--force"))
			{
			
			}
			else if (split[1].equalsIgnoreCase ("-d") ||split[1].equalsIgnoreCase ("--directory"))
			{
				File f = new File (split[2]);
				f.delete();
			
			}
			else if(split[1].equalsIgnoreCase ("-r") ||split[1].equalsIgnoreCase ("-R")||split[1].equalsIgnoreCase ("--recursive"))
				{
					File f = new File (split[2]);
					
				if (f.isDirectory()) {
				    for (File c : f.listFiles())
				      c.delete();
				  }
				f.delete();
			
				}
			}
		
		else if (split.length ==2){
			if (split[1].equalsIgnoreCase ("--help") )
					
				{
						println("rm OPTION FILE/DIRECTORY.\n"
								+"rm -i -I --interactive: remove file.\n"
								+"rm -r -R --recursive: remove non-empty folder.\n"
								+"rm -d --directory: remove an empty folder.\n");
				}
		}
		
		
		
	}
/*********************************************************/
	public void rm (String []s)
	{
		String []sp = s[0].split(" ");
		
		File f = new File (sp[1]);
		if (sp.length == 2)
		{
		if (f.isDirectory()) {
			    for (File c : f.listFiles())
			      c.delete();
				
				f.delete();
		}
		else if (f.isFile())
		{
			f.delete();
		}
	}
		else 
		{
			println("rm takes only two arguments!");
		}
}
/********************************************************/
	public void doCommand (String s)
	{
		final String [] commands = s.split((";"));
		
		
			for (int k=0; k < commands.length ; k++)
			{
				String [] splitCommand = commands[k].split(" ");
				if (splitCommand[0].equalsIgnoreCase("clear") || splitCommand[0].equalsIgnoreCase("exit") || splitCommand[0].equalsIgnoreCase("help") 
						||splitCommand[0].equalsIgnoreCase("mkdir") 
						||splitCommand[0].equalsIgnoreCase("pwd") ||splitCommand[0].equalsIgnoreCase("cd") || splitCommand[0].equalsIgnoreCase("date")
						||splitCommand[0].equalsIgnoreCase("cat") || splitCommand[0].endsWith("?")||splitCommand[0].contains(">")||splitCommand[0].contains(">>")
						||splitCommand[0].equalsIgnoreCase("ls") ||splitCommand[0].equalsIgnoreCase("mv") ||splitCommand[0].equalsIgnoreCase("cp") 
						||splitCommand[0].equalsIgnoreCase("args")||splitCommand[0].equalsIgnoreCase("rmdir")||splitCommand[0].equalsIgnoreCase("rm"))
				{
				
						//PERFORM EACH COMMAND
						for (int i=0; i<splitCommand.length;i++)
							{
								String command = splitCommand[i].toString().toLowerCase();
								
								if (splitCommand[0].endsWith("?"))
								{
									if (splitCommand[0].substring(0, (splitCommand[0].length())-1).contains("?"))
									{
										println("No such command!");
									}
									else
									{
										commandHelp(splitCommand);
									}
								
								}
								if (splitCommand[0].contains(">"))
								{
									redirect1(splitCommand);
								}
								switch (command)
								{
										case "clear":
														clear();
														break;
										case "exit":
														exit();
														break;
										
										case "help":
														help();
														break;
										case "mkdir":
														makedir(splitCommand,true);
														break;
														
										case "pwd":
														println(pwd());
														break;
										case "cd":
														cd(splitCommand);
														break;
										case "date":
														showDate();
														break;
										case "cat":
														cat(splitCommand);
														break;
										case "ls":
														list(splitCommand[1]);
														break;
										case "mv":
														move (commands);
														break;
										case "cp":
														copy(splitCommand);
														break;
														
										case "args":
														args();
														break;
										case "rmdir":
														rmdir(commands);
														break;
														
										case "rm":
														rm(commands);
														break;
					
								}
				
					}
			}
			else 
			{
					println("The command is not exist");
			}
				
		}
			
		
		
		
	}
	
		

	
	
	
	
	
	
	
	
	
	
	
} //PUBLIC CLASS
