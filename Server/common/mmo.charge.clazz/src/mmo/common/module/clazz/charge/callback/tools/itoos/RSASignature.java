package mmo.common.module.clazz.charge.callback.tools.itoos;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;



public class RSASignature
{
	public static final String RSA_PUBLIC ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2kcrRvxURhFijDoPpqZ/IgPlAgppkKrek6wSrua1zBiGTwHI2f+YCa5vC1JEiIi9uw4srS0OSCB6kY3bP2DGJagBoEgj/rYAGjtYJxJrEiTxVs5/GfPuQBYmU0XAtPXFzciZy446VPJLHMPnmTALmIOR5Dddd1Zklod9IQBMjjwIDAQAB";
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";

	/**
	* 寰楀埌鍏挜
	*/
	public static PublicKey getPublicKey() throws Exception 
	{
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decodeBase64(RSA_PUBLIC);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		return pubKey;
	}

	/**
	* RSA 鍏挜瑙ｅ瘑
	*/
	public static String decrypt(String content) throws Exception 
	{
        PublicKey pubKey = getPublicKey();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, pubKey);

        InputStream ins = new ByteArrayInputStream(Base64.decodeBase64(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa瑙ｅ瘑鐨勫瓧鑺傚ぇ灏忔渶澶氭槸128锛屽皢闇€瑕佽В瀵嗙殑鍐呭锛屾寜128浣嶆媶寮€瑙ｅ瘑
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), "utf-8");
    }

    /**
    * RSA 绛惧悕妫€鏌?
    */
    public static boolean verify(String content, String sign) throws Exception
    {
    	PublicKey pubKey = getPublicKey();

    	try {
    		java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);

			signature.initVerify(pubKey);
			signature.update(content.getBytes("utf-8"));

			boolean result = signature.verify(Base64.decodeBase64(sign));

			return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return false;
    }

}
