import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Desktop;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.util.Map;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextArea;


public class AboutFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblWikipedia;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutFrame frame = new AboutFrame();
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
	public AboutFrame() {
		setAlwaysOnTop(true);
		setForeground(Color.RED);
		setType(Type.POPUP);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AboutFrame.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-information@2x.png")));
		setTitle("About LZ-77");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblWikipedia = new JLabel("Wikipedia");
		lblWikipedia.addMouseMotionListener(new MouseMotionAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseMoved(MouseEvent arg0) {
				setCursor(HAND_CURSOR);
				
			
			}
		});
		lblWikipedia.setForeground(Color.BLUE);
		//lblWikipedia.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		
		Font font = lblWikipedia.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		lblWikipedia.setFont(font.deriveFont(attributes));
		
		lblWikipedia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
			        Desktop.getDesktop().browse(new URL("https://en.wikipedia.org/wiki/LZ77_and_LZ78#LZ77").toURI());
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		});
		lblWikipedia.setBounds(161, 211, 68, 20);
		contentPane.add(lblWikipedia);
		
		lblNewLabel = new JLabel("For more information visit ");
		lblNewLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 11));
		lblNewLabel.setBounds(20, 210, 239, 20);
		contentPane.add(lblNewLabel);
		
		JTextArea txtrLzAlgorithmsAchieve = new JTextArea();
		txtrLzAlgorithmsAchieve.setBackground(Color.WHITE);
		txtrLzAlgorithmsAchieve.setWrapStyleWord(true);
		txtrLzAlgorithmsAchieve.setEditable(false);
		txtrLzAlgorithmsAchieve.setText("LZ77 algorithms achieve compression by replacing repeated occurrences of data with references to a single copy of that data existing earlier in the uncompressed data stream. A match is encoded by a pair of numbers called a length-distance pair, which is equivalent to the statement \"each of the next length characters is equal to the characters exactly distance characters behind it in the uncompressed stream\". (The \"distance\" is sometimes called the \"offset\" instead.)");
		txtrLzAlgorithmsAchieve.setBounds(20, 34, 409, 163);
		txtrLzAlgorithmsAchieve.setLineWrap(true);
	
		contentPane.add(txtrLzAlgorithmsAchieve);
	}
}
