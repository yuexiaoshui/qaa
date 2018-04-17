package com.example.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * AES解密工具类
 * @author Administrator
 *
 */
public class AESUtil {
	 static {  
	        //BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/  
	        Security.addProvider(new BouncyCastleProvider());  
	    }  
	  
	    /** 
	     * AES解密 
	     * 
	     * @param data           //密文，被加密的数据 
	     * @param key            //秘钥 
	     * @param iv             //偏移量 
	     * @param encodingFormat //解密后的结果需要进行的编码 
	     * @return 
	     * @throws Exception 
	     */  
	    public static String decrypt(String data, String key, String iv, String encodingFormat) throws Exception {  
//	        initialize();  
	  
	        //被加密的数据  
	        byte[] dataByte = Base64.decodeBase64(data.getBytes());  
	        //加密秘钥  
	        byte[] keyByte = Base64.decodeBase64(key.getBytes());  
	        //偏移量  
	        byte[] ivByte = Base64.decodeBase64(iv.getBytes());  
	  
	  
	        try {  
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");  
	  
	            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");  
	  
	            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");  
	            parameters.init(new IvParameterSpec(ivByte));  
	  
	            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化  
	            /**ceshi**/
	            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivByte);
//	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
	            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
	            byte[] resultByte = cipher.doFinal(dataByte);  
	            if (null != resultByte && resultByte.length > 0) {  
	                String result = new String(resultByte, encodingFormat);  
	                
	                System.out.println("****************************");
	                System.out.println(result);
	                return result;  
	            }  
	            return null;  
	        } catch (NoSuchAlgorithmException e) {  
	            e.printStackTrace();  
	        } catch (NoSuchPaddingException e) {  
	            e.printStackTrace();  
	        } catch (InvalidParameterSpecException e) {  
	            e.printStackTrace();  
	        } catch (InvalidKeyException e) {  
	            e.printStackTrace();  
	        } catch (InvalidAlgorithmParameterException e) {  
	            e.printStackTrace();  
	        } catch (IllegalBlockSizeException e) {  
	            e.printStackTrace();  
	        } catch (BadPaddingException e) {  
	            e.printStackTrace();  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	  
	        return null;  
	    }
}
