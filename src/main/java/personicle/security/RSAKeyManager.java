package personicle.security;

import org.apache.log4j.Logger;
import personicle.utils.Converter;
import personicle.utils.RSAUtil;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class RSAKeyManager {
    private static Logger LOGGER = Logger.getLogger(RSAKeyManager.class);
    private Map<String, Key> nodeUKs = new HashMap<>();
    private Map<String, Key> nodeVKs = new HashMap<>();

    public void reflushKey(String nid) {
        try {
            RSAUtil.generateKeyToFile("RSA", "./resources/" + nid + ".uk", "./resources/" + nid + ".vk", 0);
            nodeUKs.put(nid, RSAUtil.loadPublicKeyFromFile("RSA", "./resources/" + nid + ".uk"));
            nodeVKs.put(nid, RSAUtil.loadPrivateKeyFromFile("RSA", "./resources/" + nid + ".vk"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPublicKey(String nid) {
        LOGGER.info("nid: " + nid + " pk: " + new String(nodeUKs.get(nid).getEncoded()));
        byte[] origin = nodeUKs.get(nid).getEncoded();
        System.out.println("nid: " + nid + " pk: " + origin.length + "\n" + new String(origin));
        String hexkey = Converter.byteArrayToHex(origin);
        System.out.println("nid: " + nid + " pk: " + hexkey.length() + "\n" + hexkey);
        byte[] revkey = Converter.hexToByteArray(hexkey);
        System.out.println("nid: " + nid + " pk: " + revkey.length + "\n" + new String(revkey));
        String rebkey = Converter.byteArrayToHex(revkey);
        System.out.println("nid: " + nid + " pk: " + rebkey.length() + "\n" + rebkey);
        //return new String(nodeUKs.get(nid).getEncoded());
        return hexkey;
    }
}
