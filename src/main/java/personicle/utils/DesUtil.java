package personicle.utils;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import static personicle.utils.Converter.byteArrayToHex;
import static personicle.utils.Converter.hexToByteArray;

public class DesUtil {

    private static Key codingkey;

    //private static String KEY_STR = "personicle";
    private static String CHARSETNAME = "UTF-8";
    private static String ALGORITHM = "DESede";

    public static Key generateKey(byte[] key) throws Exception {
        // 实例化Des密钥
        DESedeKeySpec dks = new DESedeKeySpec(key);
        // 实例化密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        // 生成密钥
        SecretKey secretKey = keyFactory.generateSecret(dks);
        return secretKey;
    }

    public static byte[] updateKey(long seed) {
        try {
            //生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            //运用SHA1安全策略
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            //设置上密钥种子
            secureRandom.setSeed(/*KEY_STR.getBytes()*/seed);
            //初始化基于SHA1的算法对象
            generator.init(secureRandom);
            //生成密钥对象
            codingkey = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return codingkey.getEncoded();
    }

    public static String getDecryptString(byte[] encodedKey, String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(str);
            DESedeKeySpec keySpec = new DESedeKeySpec(encodedKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey key = keyFactory.generateSecret(keySpec);

            Cipher decipher = Cipher.getInstance(ALGORITHM);

            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] doFinal = decipher.doFinal(bytes);
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEncryptString(byte[] encodedKey, String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            Key key = generateKey(encodedKey);

            Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = str.getBytes(CHARSETNAME);
            byte[] doFinal = cipher.doFinal(bytes);
            return encoder.encode(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 获取加密的信息
     * @param str
     * @return
     */
    public static String getEncryptString(String str) {
        //基于BASE64编码，接收byte[]并转换成String
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            //按utf8编码
            byte[] bytes = str.getBytes(CHARSETNAME);
            //获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化密码信息
            cipher.init(Cipher.ENCRYPT_MODE, codingkey);
            //加密
            byte[] doFinal = cipher.doFinal(bytes);
            //byte[]to encode好的String 并返回
            return encoder.encode(doFinal);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 获取解密之后的信息
     * @param str
     * @return
     */
    public static String getDecryptString(String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //将字符串decode成byte[]
            byte[] bytes = decoder.decodeBuffer(str);
            //获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化解密信息
            cipher.init(Cipher.DECRYPT_MODE, codingkey);
            //解密
            byte[] doFinal = cipher.doFinal(bytes);

            return new String(doFinal, CHARSETNAME);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        long seed = System.currentTimeMillis();
        byte[] sig = updateKey(seed);
        String oky = new String(sig);
        String key;
        String bky;
        String vky;
        for (int i = 0; i < 10; i++) {
            key = byteArrayToHex(oky.getBytes());
            bky = new String(hexToByteArray(key));
            vky = byteArrayToHex(bky.getBytes());
            System.out.println(oky + "<->" + key + "<->" + bky + "<->" + vky);
        }
        String origin = "I have long long way to go, still awaiting\n";
        System.out.print(origin);
        String secret = getEncryptString(origin);
        System.out.println(secret);
        String revert = getDecryptString(secret);
        System.out.print(revert);
        System.out.println("---------------------------------------------");

        updateKey(seed);
        System.out.print(origin);
        secret = getEncryptString(origin);
        System.out.println(secret);
        revert = getDecryptString(secret);
        System.out.print(revert);
        System.out.println("---------------------------------------------");

        seed = System.currentTimeMillis();
        updateKey(seed);
        System.out.print(origin);
        secret = getEncryptString(origin);
        System.out.println(secret);
        revert = getDecryptString(secret);
        System.out.print(revert);

        System.out.println("---------------------------------------------");
        secret = getEncryptString(codingkey.getEncoded(), origin);
        System.out.println(secret);
        revert = getDecryptString(codingkey.getEncoded(), secret);
        System.out.print(revert);
    }
}
