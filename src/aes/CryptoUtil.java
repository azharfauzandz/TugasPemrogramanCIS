package aes;

import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	public static void main(String[] args) {
		try {
			String asdKey = "2327DC44B69B60062CBBE9674007AE5F";
			byte[] key = hexStringToByteArray(asdKey);
			System.out.println(byteToString(key));
			String asd = "asdaasdaasdaasa";
			byte[] data = asd.getBytes();
			System.out.println(byteToString(data));
			byte[] enc = encryptAES(key, data);
			System.out.println(byteToString(enc));
			System.out.println(byteToString(decryptAES(key, enc)));
			System.out.println(byteToString(addPadding(data)));
			System.out.println(byteToString(removePadding(addPadding(data))));
		}
		catch (Exception a){
			a.printStackTrace();
		}
		
	}
	
	public static String byteToString(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
		    result.append(String.format("%02X ", b));
		    result.append(""); // delimiter
		}
		return result.toString();
	}
	
	public static byte[] generateAESKey() throws Exception {
             KeyGenerator kgen = KeyGenerator.getInstance("AES");
             kgen.init(128);
//             Generate the secret key specs.
             SecretKey skey = kgen.generateKey();
             return skey.getEncoded();
     }


	public static byte[] encryptAES(byte[] key, byte[] data) throws InvalidKeyException, Exception{
		return encryptAES(key, data, new byte[16]);
		
	}
	
	public static byte[] encryptAES(byte[] key, byte[] data, byte[] iv) throws InvalidKeyException, Exception {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            // Instantiate the cipher
            Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
            
            return cipher.doFinal(addPadding(data));
     }

	public static byte[] decryptAES(byte key[], byte msg[]) throws InvalidKeyException,Exception{
		return decryptAES(key, msg, new byte[16]);
	}
	
	public static byte[] decryptAES(byte key[], byte msg[], byte[] iv) throws InvalidKeyException, Exception {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            // Instantiate the cipher
            Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));

            return removePadding(cipher.doFinal(msg));
     }
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	private static byte[] addPadding(byte[] data) {
		int length = data.length;
		int padLength = 0;
		if (length % 16 != 0 || length == 0) {
			padLength = 16 - (length % 16);
		}
		byte[] afterPadding = new byte[length + padLength];
		System.arraycopy(data, 0, afterPadding, 0, length);
		for (int i = length; i < length+padLength; i++) {
			afterPadding[i] = (byte) padLength;
		}
		return afterPadding;
	}
	
	private static byte[] removePadding(byte[] data) {
		
		int length = data.length;
		int padLength = (int) data[length - 1];
		if (padLength > 16) return data;
		byte[] afterRemoval = new byte[length - padLength];
		boolean isPadded = true;
		for (int i = length-1; i > length-padLength; i--) {
			if (data[i] != padLength) {
				isPadded = false;
				break;
			}
		}
		if (isPadded) System.arraycopy(data, 0, afterRemoval, 0, length-padLength);
		
		return afterRemoval;
	}
	
}
