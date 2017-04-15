package aes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class AesFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtKey;
	private JTextField txtPlainText;

	/**
	 * Create the frame.
	 */
	
	public AesFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.NORTH);
		
		JComponent panel1 = makeTextPanel("Isi Encryption Here");
        tabbedPane.addTab("Encryption", null, panel1,
                "Encryption");
		
        JComponent panel2 = makeTextPanel2("Isi Decryption Here");
        tabbedPane.addTab("Decryption", null, panel2,
                "Decryption");
		
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(9, 1));
        
        JLabel lblPlainText = new JLabel("Plain Text");
        panel.add(lblPlainText);
        
        Panel panel_1 = new Panel();
        panel.add(panel_1);
        panel_1.setLayout(new GridLayout(0, 2));
        
        txtPlainText = new JTextField();
        txtPlainText.setEditable(false);
        panel_1.add(txtPlainText);
        txtPlainText.setColumns(10);
        
        JButton btnSelectFile = new JButton("Select File");
        panel_1.add(btnSelectFile);
        
        JLabel lblKey = new JLabel("Key");
        panel.add(lblKey);
        
        Panel panel_2 = new Panel();
        panel.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2));
        
        txtKey = new JTextField();
        txtKey.setEditable(false);
        panel_2.add(txtKey);
        txtKey.setColumns(10);
        
        JButton button = new JButton("Select File");
        panel_2.add(button);
        
        Panel panel_3 = new Panel();
        panel.add(panel_3);
        
        JButton btnNewButton = new JButton("ENCRYPT");
        btnNewButton.setBackground(new Color(255, 69, 0));
        panel.add(btnNewButton);
        
        JLabel lblChiperText = new JLabel("Result - Chiper Text");
        panel.add(lblChiperText);
        
        JTextPane txtpnChiperText = new JTextPane();
        txtpnChiperText.setEditable(false);
        txtpnChiperText.setText("Chiper Text File Path");
        panel.add(txtpnChiperText);
        return panel;
    }
	
	protected JComponent makeTextPanel2(String text) {
		JPanel panel = new JPanel(false);
        panel.setLayout(new GridLayout(9, 1));
        
        JLabel lblChiperTxt = new JLabel("Chiper Text");
        panel.add(lblChiperTxt);
        
        Panel panel_1 = new Panel();
        panel.add(panel_1);
        panel_1.setLayout(new GridLayout(0, 2));
        
        txtPlainText = new JTextField();
        txtPlainText.setEditable(false);
        panel_1.add(txtPlainText);
        txtPlainText.setColumns(10);
        
        JButton btnSelectFile = new JButton("Select File");
        panel_1.add(btnSelectFile);
        
        JLabel lblKey = new JLabel("Key");
        panel.add(lblKey);
        
        Panel panel_2 = new Panel();
        panel.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2));
        
        txtKey = new JTextField();
        txtKey.setEditable(false);
        panel_2.add(txtKey);
        txtKey.setColumns(10);
        
        JButton button = new JButton("Select File");
        panel_2.add(button);
        
        Panel panel_3 = new Panel();
        panel.add(panel_3);
        
        JButton btnNewButton = new JButton("DECRYPT");
        btnNewButton.setBackground(new Color(255, 69, 0));
        panel.add(btnNewButton);
        
        JLabel lblPlainText = new JLabel("Result - Plain Text");
        panel.add(lblPlainText);
        
        JTextPane txtpnChiperText = new JTextPane();
        txtpnChiperText.setEditable(false);
        txtpnChiperText.setText("Plain Text File Path");
        panel.add(txtpnChiperText);
        return panel;
    }

}
