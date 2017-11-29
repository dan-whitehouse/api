package org.ricone.api.util;


import org.ricone.error.exception.ConfigException;
import org.ricone.init.ConfigProperties;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Singleton;
import java.security.Key;

@Component
@Singleton
public class AES 
{
    private static AES instance = null;
	private Cipher cipher;
    private static String key = null;
    
    public static AES getInstance()
	{
		if(instance == null)
		{
			synchronized(AES.class)
			{
				if(instance==null)
				{
					instance = new AES();
					try
					{		
						key = ConfigProperties.getInstance().getProperty("component.config.provider");
					}
					catch (ConfigException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return instance;
	}
        
    
    public String encrypt(String plainText) throws Exception 
    {
        cipher = Cipher.getInstance("AES");
        byte[] plainTextByte = plainText.getBytes();
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        return Base64.encode(encryptedByte).toString();
    }
    public String decrypt(String encryptedText) throws Exception 
    {
        cipher = Cipher.getInstance("AES");
        byte[] encryptedTextByte = Base64.decode(encryptedText.getBytes());
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        return new String(decryptedByte);
    }

    public String encrypt(String plainText, String secretKey)
    {
    	try
    	{
    		cipher = Cipher.getInstance("AES");
            byte[] plainTextByte = plainText.getBytes();
            Key aesKey = new SecretKeySpec(secretKey.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encryptedByte = cipher.doFinal(plainTextByte);
            return new String(Base64.encode(encryptedByte));
    	}
        catch(Exception e){return null;}    
    }
    public String decrypt(String encryptedText, String secretKey)
    {
    	try
    	{
	        cipher = Cipher.getInstance("AES");
	        byte[] encryptedTextByte = Base64.decode(encryptedText.getBytes());
	        Key aesKey = new SecretKeySpec(secretKey.getBytes(), "AES");
	        cipher.init(Cipher.DECRYPT_MODE, aesKey);
	        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
	        return new String(decryptedByte);
    	}
    	catch(Exception e){return null;}  
    }
   
    
    public String getRefToKey(String refId)
    {
    	String out = "";
    	out = refId.substring(0, 16);// + refId.substring(refId.length()-12, refId.length());	
    	return out;
    }
}