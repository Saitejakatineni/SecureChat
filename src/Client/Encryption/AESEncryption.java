
package Client.Encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class AESEncryption {
    private String message;

    public AESEncryption(String message, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        byte[] plainText = message.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        // encrypt using the key and the plaintext
        System.out.println("\nStart encryption using AES:");

        final long startTime = System.nanoTime();
        //  Initializes the Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Calculates the ciphertext with a plaintext string.
        byte[] cipherText = cipher.doFinal(plainText);
        String str2="";

        for (byte b:cipherText) {
            str2 +=(char)b;
        }
        this.message = str2;
        final long duration = System.nanoTime() - startTime;
        System.out.println("Finish encryption using AES: ");
        System.out.println("It took " + duration + " nanosecond to encrypt the message \"" + message +"\" using AES");
        System.out.println("Message length is " + message.length());
    }

    public String getMessage() {
        return message;
    }
}
