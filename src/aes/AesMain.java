package aes;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;

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
					
					frame.getBtnEncrypt().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							System.out.println("Encrypting....");
							try {
								byte[] key = readKeyFromFile(frame.getTxtKeyPlainTxt().getText());
								byte[] message = readFile(frame.getTxtPlainText().getText());
								byte[] chiper = CryptoUtil.encryptAES(key, message);
								frame.getTxtpnChiperText().setText(writeToFile(chiper, "encrypted.aes"));
//								System.out.println(CryptoUtil.byteToString(chiper));
							}catch (InvalidKeyException a) {
								System.out.println("Java Security Key error : please follow this instruction http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters");
								a.printStackTrace();
							}
							
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
					
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
					
					frame.getBtnDecrypt().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							System.out.println("Decrypting....");
							// TODO Auto-generated method stub
							try {
								byte[] key = readKeyFromFile(frame.getTxtKeyChiperTxt().getText());
								byte[] chiper = readFile(frame.getTxtChiperText().getText());
								byte[] message = CryptoUtil.decryptAES(key, chiper);
								frame.getTxtpnPlainText().setText(writeToFile(message, "decrypted.aes"));
							} catch (InvalidKeyException a) {
								System.out.println("Java Security Key error : please follow this instruction http://stackoverflow.com/questions/6481627/java-security-illegal-key-size-or-default-parameters");
								a.printStackTrace();
							}catch (IOException e) {
								e.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
					});
					
				} 
				catch (Exception e) {
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

	public static byte[] readFile(String strPath) throws IOException{
		Path path = Paths.get(strPath);
		byte[] data = Files.readAllBytes(path);
	
		return data;
	}
	
	public static byte[] readKeyFromFile(String strPath) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(strPath));
		try {
			return CryptoUtil.hexStringToByteArray(new String(encoded, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String writeToFile(byte[] data, String name) throws IOException {
		FileOutputStream fos = new FileOutputStream(name);
		fos.write(data);
		fos.close();
		return System.getProperty("user.dir")+"/"+name;
	}
}
