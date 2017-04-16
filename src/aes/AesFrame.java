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
	private JTextField txtKeyPlainTxt;
	private JTextField txtKeyChiperTxt;
	private JTextField txtPlainText;
	private JTextField txtChiperText;
	private JButton btnSelectFilePlainTxt;
	private JButton btnSelectKeyPlainTxt;
	private JButton btnSelectFileChiperTxt;
	private JButton btnSelectKeyChiperTxt;
	private JTextPane txtpnPlainText;
	private JTextPane txtpnChiperText;
	

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
        
        btnSelectFilePlainTxt = new JButton("Select File");
        panel_1.add(btnSelectFilePlainTxt);
        
        JLabel lblKey = new JLabel("Key");
        panel.add(lblKey);
        
        Panel panel_2 = new Panel();
        panel.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2));
        
        txtKeyPlainTxt = new JTextField();
        txtKeyPlainTxt.setEditable(false);
        panel_2.add(txtKeyPlainTxt);
        txtKeyPlainTxt.setColumns(10);
        
        btnSelectKeyPlainTxt = new JButton("Select File");
        panel_2.add(btnSelectKeyPlainTxt);
        
        Panel panel_3 = new Panel();
        panel.add(panel_3);
        
        JButton btnNewButton = new JButton("ENCRYPT");
        btnNewButton.setBackground(new Color(255, 69, 0));
        panel.add(btnNewButton);
        
        JLabel lblChiperText = new JLabel("Result - Chiper Text");
        panel.add(lblChiperText);
        
        txtpnChiperText = new JTextPane();
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
        
        txtChiperText = new JTextField();
        txtChiperText.setEditable(false);
        panel_1.add(txtChiperText);
        txtChiperText.setColumns(10);
        
        btnSelectFileChiperTxt = new JButton("Select File");
        panel_1.add(btnSelectFileChiperTxt);
        
        JLabel lblKey = new JLabel("Key");
        panel.add(lblKey);
        
        Panel panel_2 = new Panel();
        panel.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2));
        
        txtKeyChiperTxt = new JTextField();
        txtKeyChiperTxt.setEditable(false);
        panel_2.add(txtKeyChiperTxt);
        txtKeyChiperTxt.setColumns(10);
        
        btnSelectKeyChiperTxt = new JButton("Select File");
        panel_2.add(btnSelectKeyChiperTxt);
        
        Panel panel_3 = new Panel();
        panel.add(panel_3);
        
        JButton btnNewButton = new JButton("DECRYPT");
        btnNewButton.setBackground(new Color(255, 69, 0));
        panel.add(btnNewButton);
        
        JLabel lblPlainText = new JLabel("Result - Plain Text");
        panel.add(lblPlainText);
        
        txtpnPlainText = new JTextPane();
        txtpnPlainText.setEditable(false);
        txtpnPlainText.setText("Plain Text File Path");
        panel.add(txtpnPlainText);
        return panel;
    }

	public JTextField getTxtKeyPlainTxt() {
		return txtKeyPlainTxt;
	}

	public void setTxtKeyPlainTxt(JTextField txtKeyPlainTxt) {
		this.txtKeyPlainTxt = txtKeyPlainTxt;
	}

	public JTextField getTxtKeyChiperTxt() {
		return txtKeyChiperTxt;
	}

	public void setTxtKeyChiperTxt(JTextField txtKeyChiperTxt) {
		this.txtKeyChiperTxt = txtKeyChiperTxt;
	}

	public JTextField getTxtPlainText() {
		return txtPlainText;
	}

	public void setTxtPlainText(JTextField txtPlainText) {
		this.txtPlainText = txtPlainText;
	}

	public JTextField getTxtChiperText() {
		return txtChiperText;
	}

	public void setTxtChiperText(JTextField txtChiperText) {
		this.txtChiperText = txtChiperText;
	}

	public JButton getBtnSelectFilePlainTxt() {
		return btnSelectFilePlainTxt;
	}

	public void setBtnSelectFilePlainTxt(JButton btnSelectFilePlainTxt) {
		this.btnSelectFilePlainTxt = btnSelectFilePlainTxt;
	}

	public JButton getBtnSelectKeyPlainTxt() {
		return btnSelectKeyPlainTxt;
	}

	public void setBtnSelectKeyPlainTxt(JButton btnSelectKeyPlainTxt) {
		this.btnSelectKeyPlainTxt = btnSelectKeyPlainTxt;
	}

	public JButton getBtnSelectFileChiperTxt() {
		return btnSelectFileChiperTxt;
	}

	public void setBtnSelectFileChiperTxt(JButton btnSelectFileChiperTxt) {
		this.btnSelectFileChiperTxt = btnSelectFileChiperTxt;
	}

	public JButton getBtnSelectKeyChiperTxt() {
		return btnSelectKeyChiperTxt;
	}

	public void setBtnSelectKeyChiperTxt(JButton btnSelectKeyChiperTxt) {
		this.btnSelectKeyChiperTxt = btnSelectKeyChiperTxt;
	}

	public JTextPane getTxtpnPlainText() {
		return txtpnPlainText;
	}

	public void setTxtpnPlainText(JTextPane txtpnPlainText) {
		this.txtpnPlainText = txtpnPlainText;
	}

	public JTextPane getTxtpnChiperText() {
		return txtpnChiperText;
	}

	public void setTxtpnChiperText(JTextPane txtpnChiperText) {
		this.txtpnChiperText = txtpnChiperText;
	}
	
	

}
