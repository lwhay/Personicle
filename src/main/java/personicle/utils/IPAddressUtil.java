package personicle.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IPAddressUtil {
    public static String getEffectiveLocalIPAddress() throws SocketException {
        Enumeration<?> addresses = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
        while (addresses.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) addresses.nextElement();
            Enumeration<?> niAddresses = ni.getInetAddresses();
            while (niAddresses.hasMoreElements()) {
                InetAddress address = (InetAddress) niAddresses.nextElement();
                if (address.getHostAddress().startsWith("192")) {
                    return address.getHostAddress().trim();
                }
            }
        }
        return null;
    }
}
