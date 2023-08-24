

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class MessageEncryption {
    private String messageString;

    public MessageEncryption(String message, Key key,String type) throws Exception{
        byte[] plainText = message.getBytes(StandardCharsets.UTF_8);
        Cipher cipher = Cipher.getInstance(type+"/ECB/PKCS5Padding");
        // encrypt using the key and the plaintext
        System.out.println("\nStart encryption:");

        final long startTime = System.nanoTime();
        //  Initializes the Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Calculates the ciphertext with a plaintext string.
        byte[] cipherText = cipher.doFinal(plainText);
        String str2="";

        for (byte b:cipherText) {
            str2 +=(char)b;
        }
        this.messageString = str2;
        final long duration = System.nanoTime() - startTime;
        System.out.println("Finish encryption: ");
        System.out.println("It took " + duration + " nanosecond to encrypt the message \"" + message +"\" using "+type);
        Launcher.takenTimeE=duration;
        System.out.println("Message length is " + message.length());
        if(type.equals("AES"))
        {
            Graph.length_Aes.add((double)message.length());
            Graph.time_Aes.add((double)duration/100000);
        }
        else
        {
             Des_Graph.length_Des.add((double)message.length());
            Des_Graph.time_Des.add((double)duration/100000);
        }
    }

    public String getMessageString() {
        return messageString;
    }
}