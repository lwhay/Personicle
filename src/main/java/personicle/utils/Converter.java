package personicle.utils;

public class Converter {
    private static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static int[] map = null;

    public static String byteArrayToHex(byte[] byteArray) {
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

    public static byte[] hexToByteArray(String hex) {
        if (map == null) {
            map = new int[256];
            for (int i = 0; i < hexDigits.length; i++) {
                map[hexDigits[i]] = i;
            }
        }
        byte[] array = new byte[hex.length() / 2];
        for (int i = 0; i < array.length; i++) {
            array[i] = (byte) ((map[hex.charAt(2 * i)] << 4 & 0xf0) + (map[hex.charAt(2 * i + 1)] & 0xf));
        }
        return array;
    }
}
