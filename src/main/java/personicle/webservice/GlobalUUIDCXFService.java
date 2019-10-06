package personicle.webservice;

import java.net.SocketException;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.log4j.Logger;
import personicle.utils.IPAddressUtil;

public class GlobalUUIDCXFService {
    private static Logger LOGGER = Logger.getLogger(GlobalUUIDCXFService.class);

    TimeBasedGenerator nbg = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    public String getUUID() {
        return nbg.generate().toString().replace("-", "").trim();
    }

    public static void main(String[] args) throws SocketException {
        ServerFactoryBean serverFactoryBean = new ServerFactoryBean();
        String ipAddress = IPAddressUtil.getEffectiveLocalIPAddress();
        if (ipAddress != null) {
            System.out.println(ipAddress);
            String address = "http://" + ipAddress + ":10901/GlobalUUIDCXFService";
            serverFactoryBean.setAddress(address);
            serverFactoryBean.setServiceClass(GlobalUUIDService.class);
            serverFactoryBean.create();
        } else {
            LOGGER.error("Ip address not found!");
        }
    }
}
