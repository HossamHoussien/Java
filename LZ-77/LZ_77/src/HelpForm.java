import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;

public class HelpForm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpForm frame = new HelpForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HelpForm() {
		setAlwaysOnTop(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(HelpForm.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-confirm@2x.png")));
		setResizable(false);
		setTitle("Help");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 401);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 474, 350);
		getContentPane().add(scrollPane);
		
		JTextArea textpanel = new JTextArea();
		textpanel.setText("File: Contains two operations \"Compress\" and/or \"Decompress\".\r\nOpen: Enables you to open text files in text editor \"Notepad\".\r\nClear: A easy tool helps you to clear contents of text files.\r\nHelp: For additional information regards \"LZ-77 Compressor\" and/or \"Program Commands\".\r\n------------------------------------------------------------------------------------------------\r\nHow to use?\r\n*************\r\n1. Select a text file that contains the data you want to compress and/or decompress.\r\n2. From \"File\" menu choose either to Compress or Decompress that file.\r\n3. In either case, the output is saved in seperate file.\r\n4. Check out the results through using \"Open\" menu.");
		scrollPane.setViewportView(textpanel);
	}
}
