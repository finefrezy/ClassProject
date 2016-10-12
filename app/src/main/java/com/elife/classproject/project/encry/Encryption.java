package com.elife.classproject.project.encry;

import com.elife.classproject.project.model.Sign;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import it.sauronsoftware.base64.Base64;

public class Encryption {

    // 32位小写MD5加密
    private static String test(String str) throws Exception {
        String mdStr = new String();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        byte b[] = md.digest();

        int i;

        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }

        mdStr = buf.toString();

        return mdStr;
    }

    private static byte[] getIV() {
        String iv = "1234567812345678";
        return iv.getBytes();
    }

    /**
     * 提供返回的对象，需要转换成json
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static Sign encryption(String str)  {
        Sign sigh = null;
        try {
            String mdStr = test(str);
            String key = mdStr.substring(0, 5) + mdStr.substring(9, 14) + mdStr.substring(19, 25);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            Key keySpec = new SecretKeySpec(key.getBytes(), "AES");

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(getIV()));

            byte[] encrypt = cipher.doFinal(str.getBytes());
            sigh = new Sign();
            sigh.sign = mdStr + new String(Base64.encode(encrypt));
            return sigh;
        } catch (Exception e) {

        }
        return sigh;
    }


}


