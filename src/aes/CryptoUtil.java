package aes;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	/**
	 * for testing purpose only, please ignore this main method
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String asdKey = "0202020202020202020202020202020202020202020202020202020202020202";
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
	
	/**
	 * convert bytes array to plain string hexa
	 * @param bytes
	 * @return
	 */
	public static String byteToString(byte[] bytes) {
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
		    result.append(String.format("%02X ", b));
		    result.append(""); // delimiter
		}
		return result.toString();
	}
	
	/**
	 * generate random key for aes, with 128 bit length
	 * @return
	 * @throws Exception
	 */
	public static byte[] generateAESKey() throws Exception {
             KeyGenerator kgen = KeyGenerator.getInstance("AES");
             kgen.init(128);
//             Generate the secret key specs.
             SecretKey skey = kgen.generateKey();
             return skey.getEncoded();
     }

	/**
	 * encrypt using aes with default IV (all zero)
	 * @param key
	 * @param data
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static byte[] encryptAES(byte[] key, byte[] data) throws InvalidKeyException, Exception{
		return encryptAES(key, data, new byte[16]);
		
	}
	
	/**
	 * encrypt using aes with given IV 
	 * @param key
	 * @param data
	 * @param iv
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static byte[] encryptAES(byte[] key, byte[] data, byte[] iv) throws InvalidKeyException, Exception {
		key = setKey(key);
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        
        // Instantiate the cipher
        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
        
        return cipher.doFinal(addPadding(data));
     }

	/**
	 * decrypt using aes with default IV (all zero)
	 * @param key
	 * @param msg
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static byte[] decryptAES(byte[] key, byte[] msg) throws InvalidKeyException,Exception{
		return decryptAES(key, msg, new byte[16]);
	}
	
	/**
	 * decrypt using aes with given IV
	 * @param key
	 * @param msg
	 * @param iv
	 * @return
	 * @throws InvalidKeyException
	 * @throws Exception
	 */
	public static byte[] decryptAES(byte[] key, byte[] msg, byte[] iv) throws InvalidKeyException, Exception {
		key = setKey(key);
		SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

		// Instantiate the cipher
		Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));

		return removePadding(cipher.doFinal(msg));
     }
	
	/**
	 * convert hexa string into bytes array
	 * @param s
	 * @return
	 */
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	/**
	 * add padding using pcks#5 format
	 * @param data
	 * @return
	 */
	private static byte[] addPadding(byte[] data) {
		int length = data.length;
		int padLength = 0;
		if (length % 16 != 0 || length == 0) {
			padLength = 16 - (length % 16);
		}
		byte[] afterPadding = new byte[length + padLength];
		System.arraycopy(data, 0, afterPadding, 0, length);
		//add padding into the end of data
		for (int i = length; i < length+padLength; i++) {
			afterPadding[i] = (byte) padLength;
		}
		return afterPadding;
	}
	
	/**
	 * remove padding using pcks#5 format
	 * @param data
	 * @return
	 */
	private static byte[] removePadding(byte[] data) {
		
		int length = data.length;
		int padLength = (int) data[length - 1];
		if (padLength > 16) return data;
		byte[] afterRemoval = new byte[length - padLength];
		boolean isPadded = true;
		// check if n last data is padded bit
		for (int i = length-1; i > length-padLength; i--) {
			if (data[i] != padLength) {
				isPadded = false;
				break;
			}
		}
		if (isPadded) System.arraycopy(data, 0, afterRemoval, 0, length-padLength);
		
		return afterRemoval;
	}
	
	/**
	 * set suitable key for aes
	 * @param key
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] setKey(byte[] key) throws NoSuchAlgorithmException {
		int length = key.length;
		//if length is 128 or 192 or 256 then the key is suitable
		if (length == 16 || length == 24 || length == 32) {
			return key;
		} else {
			// else, hash the key using sha1 and take first 128 bit
			System.out.println("Preprocessing key...");
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] newKey = sha.digest(key);
			newKey = Arrays.copyOf(key, 16); // take first 128 bit
			return newKey;
		}
	}
}
