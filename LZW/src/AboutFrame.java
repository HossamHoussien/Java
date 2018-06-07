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
		setTitle("About LZW");
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
			        Desktop.getDesktop().browse(new URL("https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch").toURI());
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
		txtrLzAlgorithmsAchieve.setText("Lempel\u2013Ziv\u2013Welch (LZW) is a universal lossless data compression algorithm created by Abraham Lempel, Jacob Ziv, and Terry Welch. It was published by Welch in 1984 as an improved implementation of the LZ78 algorithm published by Lempel and Ziv in 1978. The algorithm is simple to implement, and has the potential for very high throughput in hardware implementations. It is the algorithm of the widely used Unix file compression utility compress, and is used in the GIF image format.");
		txtrLzAlgorithmsAchieve.setBounds(20, 34, 409, 176);
		txtrLzAlgorithmsAchieve.setLineWrap(true);
	
		contentPane.add(txtrLzAlgorithmsAchieve);
	}
}
