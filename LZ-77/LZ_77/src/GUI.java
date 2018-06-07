import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import java.awt.Font;

public class GUI extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private JTextField statusBar = new JTextField();

  private JButton browse = new JButton("Browse");
  private JTextField pathname;
  private JMenuItem mntmTagsOutput;

  public GUI() {
  	setResizable(false);
  	setAlwaysOnTop(true);
  	setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Paste@2x.png")));
  	setTitle("LZ-77 Compressor");
    
    getContentPane().setLayout(null);
    browse.setBounds(478, 155, 80, 20);
    getContentPane().add(browse);
    browse.setToolTipText("Select File");
   
    statusBar.setBounds(0, 351, 594, 20);
    getContentPane().add(statusBar);
    statusBar.setEditable(false);
    
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBounds(0, 0, 594, 21);
    getContentPane().add(menuBar);
    
    JMenu mnFile = new JMenu("File");
    mnFile.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    menuBar.add(mnFile);
    
    JMenuItem mntmNewMenuItem = new JMenuItem("Compress");
    mntmNewMenuItem.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    /****************************************************
     * 													*
     * 					COMPRESS BUTTON					*
     * 													*
     ****************************************************/
    mntmNewMenuItem.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
    		Compress file = new Compress ();
    		
    		String p = pathname.getText();
    		 
    		try 
    		{
    			if (p.isEmpty())
    				{
    					statusBar.setText("Nothing to compress!");
    				}
    			else
    				{
    					file.Open_File(p);
    					file.Compress_File();
    					file.Close_File();
    					

            	JOptionPane.showMessageDialog(null, "Compression process has been completed succesfully.", "Done", JOptionPane.INFORMATION_MESSAGE);
            	try {
    				Runtime.getRuntime().exec("notepad.exe TagOutput.txt");
    			} catch (IOException e1) {
    				JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
    			}
            	
    				
    				}
        	}
    		
    		catch (Exception ex1) 
    		{
    			JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);}
    		
    		}
    });
    mnFile.add(mntmNewMenuItem);
    
    JMenuItem mntmNewMenuItem_1 = new JMenuItem("Decompress");
    /****************************************************
     * 													*
     * 					DECOMPRESS BUTTON				*
     * 													*
     ****************************************************/
    mntmNewMenuItem_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		Decompress file = new Decompress ();
    		String p = pathname.getText();
   		 
    		try {
    			if (p.isEmpty())
    			{
    				statusBar.setText("Nothing to decompress!");
    			}
    			else {
    			file.OpenFile(p);
    			file.DecompressFile(p);
        		file.CloseFile();
        		JOptionPane.showMessageDialog(null, "Decompression process has been completed succesfully.", "Done", JOptionPane.INFORMATION_MESSAGE);
        		try {
    				Runtime.getRuntime().exec("notepad.exe TextOutput.txt");
    			} catch (IOException e1) {
    				JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
    			}
        		}
    		}
    		catch (Exception ex1) {
    			JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
    			}
    	}
    });
    mntmNewMenuItem_1.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnFile.add(mntmNewMenuItem_1);
    
    JSeparator separator = new JSeparator();
    mnFile.add(separator);
    
    JMenuItem mntmExit = new JMenuItem("Exit");
    /****************************************************
     * 													*
     * 					EXIT BUTTON						*
     * 													*
     ****************************************************/
    mntmExit.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		dispose();
    	}
    });
    /*********************************************************************/
    /*********************************************************************/
    /*********************************************************************/
    /*********************************************************************/
    
    mntmExit.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnFile.add(mntmExit);
    
    JMenu mnOpen = new JMenu("Open");
    mnOpen.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    menuBar.add(mnOpen);
    
    JMenuItem mntmTextInput = new JMenuItem("Text Input");
    /****************************************************
     * 													*
     * 				OPEN TEXT INPUT BUTTON				*
     * 													*
     ****************************************************/
    mntmTextInput.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		
    		try {
				Runtime.getRuntime().exec("notepad.exe TextInput.txt");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
    		
    	}
    });
    mntmTextInput.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnOpen.add(mntmTextInput);
    /****************************************************
     * 													*
     * 				OPEN TAGS INPUT BUTTON				*
     * 													*
     ****************************************************/
    JMenuItem mntmTextOutput = new JMenuItem("Text Output");
    mntmTextOutput.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
				Runtime.getRuntime().exec("notepad.exe TextOutput.txt");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
    	}
    });
    mntmTextOutput.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnOpen.add(mntmTextOutput);
    /****************************************************
     * 													*
     * 				OPEN TAGS OUTPUT BUTTON				*
     * 													*
     ****************************************************/
    mntmTagsOutput = new JMenuItem("Tags Output");
    mntmTagsOutput.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
				Runtime.getRuntime().exec("notepad.exe TagOutput.txt");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
    	}
    });
    
    JSeparator separator_1 = new JSeparator();
    mnOpen.add(separator_1);
    
    JMenuItem mntmTagsInput = new JMenuItem("Tags Input");
    
    mntmTagsInput.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
				Runtime.getRuntime().exec("notepad.exe TagInput.txt");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
    	}
    });
    mntmTagsInput.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnOpen.add(mntmTagsInput);
    mntmTagsOutput.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnOpen.add(mntmTagsOutput);
    /****************************************************
     * 													*
     * 				OPEN TEXT OUTPUT BUTTON				*
     * 													*
     ****************************************************/
    
    JMenuItem mntmFilePath = new JMenuItem("Open File Path");
    mntmFilePath.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    /****************************************************
     * 													*
     * 					OPEN FILE PATH					*
     * 													*
     ****************************************************/
    mntmFilePath.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
    		
    	        try {
    	        	String path1 = pathname.getText();
    	        	if (path1.isEmpty())
    	        	{
    	        		statusBar.setText("Please, select a text file to open!");
    	        	}
    	        	else
    	        	{
    	    		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", " start notepad.exe "+path1 );
    	    	      builder.redirectErrorStream(true);
					builder.start();
				} 
    	        }catch (IOException e) {
					JOptionPane.showMessageDialog(null, "An error occuered while opening the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
    	       
    	}
    });
    
    JSeparator separator_3 = new JSeparator();
    mnOpen.add(separator_3);
    
    
    mnOpen.add(mntmFilePath);
    
    JMenu mnClear = new JMenu("Clear");
    mnClear.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    menuBar.add(mnClear);
    /****************************************************
     * 													*
     * 				CLEAR TEXT INPUT BUTTON				*
     * 													*
     ****************************************************/
    JMenuItem mntmClearTextInput = new JMenuItem("Clear Text Input");
    mntmClearTextInput.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try
    		{
    			Clear file = new Clear ();
    			String p = "TextInput.txt";
    			
    			int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "After clearing file data saved in it cannot be recovered, proceed?","Warning",dialogButton);
                 if(dialogResult == JOptionPane.YES_OPTION)
                 {
                 	file.ClearFile(p);
                 	JOptionPane.showMessageDialog(null, "Clearing process has been completed succesfully.", "Done", JOptionPane.INFORMATION_MESSAGE);

                 }
                 if(dialogResult == JOptionPane.NO_OPTION)
                 {
                 	JOptionPane.showMessageDialog(null, "Clearing process has been cancelled.", "Cancelled", JOptionPane.ERROR_MESSAGE);
                 }
    		
    		}
    		catch (Exception ex3)
    		{
    			JOptionPane.showMessageDialog(null, "An error occuered while clearing the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    });
    mntmClearTextInput.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnClear.add(mntmClearTextInput);
    /****************************************************
     * 													*
     * 		    	CLEAR TEXT OUTPUT BUTTON			*
     * 													*
     ****************************************************/
    JMenuItem mntmClearTextOutput = new JMenuItem("Clear Text Output");
    mntmClearTextOutput.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		try
    		{
    			Clear file = new Clear ();
    			String p = "TextOutput.txt";
    			int dialogButton = JOptionPane.YES_NO_OPTION;
    			int dialogResult = JOptionPane.showConfirmDialog (null, "After clearing file data saved in it cannot be recovered, proceed?","Warning",dialogButton);
                 if(dialogResult == JOptionPane.YES_OPTION)
                 {
                 	file.ClearFile(p);
                 	JOptionPane.showMessageDialog(null, "Clearing process has been completed succesfully.", "Done", JOptionPane.INFORMATION_MESSAGE);

                 }
                 if(dialogResult == JOptionPane.NO_OPTION)
                 {
                 	JOptionPane.showMessageDialog(null, "Clearing process has been cancelled.", "Cancelled", JOptionPane.ERROR_MESSAGE);
                 }
    		
    		}
    		catch (Exception ex3)
    		{
    			JOptionPane.showMessageDialog(null, "An error occuered while clearing the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    });
    mntmClearTextOutput.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnClear.add(mntmClearTextOutput);
    
    JSeparator separator_2 = new JSeparator();
    mnClear.add(separator_2);
    /****************************************************
     * 													*
     * 				CLEAR TAGS INPUT BUTTON				*
     * 													*
     ****************************************************/
    JMenuItem mntmClearTagsInput = new JMenuItem("Clear Tags Input");
    mntmClearTagsInput.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try
    		{
    			Clear file = new Clear ();
    			String p = "TagInput.txt";
    			
    			
    		   int dialogButton = JOptionPane.YES_NO_OPTION;
    		   int dialogResult = JOptionPane.showConfirmDialog (null, "After clearing file data saved in it cannot be recovered, proceed?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION)
                {
                	file.ClearFile(p);
                	JOptionPane.showMessageDialog(null, "Clearing process has been completed succesfully.", "Done", JOptionPane.INFORMATION_MESSAGE);

                }
                if(dialogResult == JOptionPane.NO_OPTION)
                {
                	JOptionPane.showMessageDialog(null, "Clearing process has been cancelled.", "Cancelled", JOptionPane.ERROR_MESSAGE);
                }
    			
                    	
    		
    		}
    		catch (Exception ex3)
    		{
    			JOptionPane.showMessageDialog(null, "An error occuered while clearing the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    });
    mntmClearTagsInput.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnClear.add(mntmClearTagsInput);
    /****************************************************
     * 													*
     * 				CLEAR TAG OUTPUT BUTTON				*
     * 													*
     ****************************************************/
    JMenuItem mntmClearTagsOutput = new JMenuItem("Clear Tags Output");
    mntmClearTagsOutput.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try
    		{
    			Clear file = new Clear ();
    			String p = "TagOutput.txt";
    			int dialogButton = JOptionPane.YES_NO_OPTION;
    			int dialogResult = JOptionPane.showConfirmDialog (null, "After clearing file data saved in it cannot be recovered, proceed?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION)
                {
                	file.ClearFile(p);
                	JOptionPane.showMessageDialog(null, "Clearing process has been completed succesfully.", "Done", JOptionPane.INFORMATION_MESSAGE);

                }
                if(dialogResult == JOptionPane.NO_OPTION)
                {
                	JOptionPane.showMessageDialog(null, "Clearing process has been cancelled.", "Cancelled", JOptionPane.ERROR_MESSAGE);
                }
    		
    		}
    		catch (Exception ex3)
    		{
    			JOptionPane.showMessageDialog(null, "An error occuered while clearing the file.", "Error Message", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    });
    mntmClearTagsOutput.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnClear.add(mntmClearTagsOutput);
    
    JMenu mnHelp = new JMenu("Help");
    mnHelp.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    menuBar.add(mnHelp);
    
    JMenuItem mntmHelpContent = new JMenuItem("Help Content");
    /****************************************************
     * 													*
     * 					HELP BUTTON						*
     * 													*
     ****************************************************/
    mntmHelpContent.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		
    		
    		HelpForm obj = new HelpForm();
    		obj.setVisible(true);
    		obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    		
    		
    	}
    });
    mntmHelpContent.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnHelp.add(mntmHelpContent);
    /****************************************************
     * 													*
     * 					ABOUT BUTTON					*
     * 													*
     ****************************************************/
    JMenuItem mntmAbout = new JMenuItem("About LZ-77");
    mntmAbout.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		AboutFrame obj = new AboutFrame();
    		obj.setVisible(true);
    		obj.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    		
    		
    		
    	}
    });
    mntmAbout.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 12));
    mnHelp.add(mntmAbout);
    
    pathname = new JTextField();
    pathname.setBounds(103, 156, 338, 20);
    getContentPane().add(pathname);
    pathname.setColumns(10);
    
    JLabel lblFilePath = new JLabel("File Path");
    lblFilePath.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));
    lblFilePath.setBounds(40, 159, 74, 14);
    getContentPane().add(lblFilePath);
    browse.addActionListener(new OpenL());
  }

  class OpenL implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      JFileChooser c = new JFileChooser();
      // Demonstrate "Open" dialog:
      int rVal = c.showOpenDialog(GUI.this);
      if (rVal == JFileChooser.APPROVE_OPTION) {
        pathname.setText(c.getCurrentDirectory().toString()+"\\"+c.getSelectedFile().getName());
        
      }
      if (rVal == JFileChooser.CANCEL_OPTION) {
        statusBar.setText("You pressed cancel");
       
      }
    }
  }

  public static void main(String[] args) {
    run(new GUI(), 600, 400);
  }

  public static void run(JFrame frame, int width, int height) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(width, height);
    frame.setVisible(true);
  }
	public JMenuItem getMntmTagsOutput() {
		return mntmTagsOutput;
	}
} ///:~