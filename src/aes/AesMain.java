package aes;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
public class AesMain {

	static File selectedFilePlainTxt;
	static File selectedFileKeyPlainTxt;
	static File selectedFileChiperTxt;
	static File selectedFileKeyChiperTxt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AesFrame frame = new AesFrame();
					frame.setVisible(true);
					
					/**
					 * This code is for encryption tab
					 */
					
					//Get File Plain Txt
					frame.getBtnSelectFilePlainTxt().addActionListener(new ActionListener() {		
						@Override
						public void actionPerformed(ActionEvent e) {
							 selectedFilePlainTxt = getFileChoosen();
							 frame.getTxtPlainText().setText(selectedFilePlainTxt.getAbsolutePath());
						}
					});
					
					//Get File Key Plain Txt
					frame.getBtnSelectKeyPlainTxt().addActionListener(new ActionListener() {		
						@Override
						public void actionPerformed(ActionEvent e) {
							 selectedFileKeyPlainTxt = getFileChoosen();
							 frame.getTxtKeyPlainTxt().setText(selectedFileKeyPlainTxt.getAbsolutePath());
						}
					});
					
					/**
					 * This code is for decryption tab
					 */
					
					//Get File Chiper Txt
					frame.getBtnSelectFileChiperTxt().addActionListener(new ActionListener() {		
						@Override
						public void actionPerformed(ActionEvent e) {
							 selectedFileChiperTxt = getFileChoosen();
							 frame.getTxtChiperText().setText(selectedFileChiperTxt.getAbsolutePath());
						}
					});
					
					//Get File Key Chiper Txt
					frame.getBtnSelectKeyChiperTxt().addActionListener(new ActionListener() {		
						@Override
						public void actionPerformed(ActionEvent e) {
							 selectedFileKeyChiperTxt = getFileChoosen();
							 frame.getTxtKeyChiperTxt().setText(selectedFileKeyChiperTxt.getAbsolutePath());
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static File getFileChoosen(){
		File selectedFile = null;
		JFileChooser fileChooser = new JFileChooser();
	    int returnValue = fileChooser.showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	      selectedFile = fileChooser.getSelectedFile();
	    }
		return selectedFile;
	}

}
