package aes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil {
	
	public static void main(String[] args) {
		try {
			String asdKey = "key";
			byte[] key = generateAESKey();
			System.out.println(byteToString(key));
			String asd = "vsdasdasdasdasdasdasdasdasdasd adasd ad asd as asd as das as dasd ";
			byte[] data = asd.getBytes();
			System.out.println(byteToString(data));
			byte[] enc = encryptAES(key, data);
			System.out.println(byteToString(enc));
			System.out.println(byteToString(decryptAES(key, enc)));
			
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


	public static byte[] encryptAES(byte key[], byte data[]) throws Exception {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            // Instantiate the cipher
            Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));

            return cipher.doFinal(data);
     }

	public static byte[] decryptAES(byte key[], byte msg[]) throws Exception {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            // Instantiate the cipher
            Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));

            return cipher.doFinal(msg);
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
}
