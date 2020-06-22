package personicle.utils;

import java.security.MessageDigest;

import static personicle.utils.Converter.byteArrayToHex;

public class MD5Util {

    private static final String KEY_MD5 = "MD5";

    /***
     * MD5加密（生成唯一的MD5值）
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }

    public static String encryptMD5(byte[] data) throws Exception {
        return byteArrayToHex(encryMD5(data));
    }

    public static void main(String[] args) throws Exception {
        String origin = "hello world";
        String encytd = new String(encryMD5(origin.getBytes()));
        System.out.println(origin + "<->" + encytd);
        System.out.println(encytd + "<->" + encryptMD5(encytd.getBytes()));
    }
}
