package personicle.webservice;

import java.net.SocketException;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.apache.asterix.external.generator.DataGenerator.Point;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.log4j.Logger;
import personicle.utils.IPAddressUtil;

public class GlobalCXFService {
    private static Logger LOGGER = Logger.getLogger(GlobalCXFService.class);

    TimeBasedGenerator nbg = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    public String getUUID() {
        return nbg.generate().toString().replace("-", "").trim();
    }

    private String getGeoCode(Point point) {
        return null;
    }

    public String getGeoCode(float x, float y) {
        return getGeoCode(new Point(x, y));
    }

    public static void main(String[] args) throws SocketException {
        ServerFactoryBean serverFactoryBean = new ServerFactoryBean();
        String ipAddress = IPAddressUtil.getEffectiveLocalIPAddress();
        if (ipAddress != null) {
            System.out.println(ipAddress);
            String address = "http://" + ipAddress + ":10901/GlobalCXFService";
            serverFactoryBean.setAddress(address);
            serverFactoryBean.setServiceClass(GlobalService.class);
            serverFactoryBean.create();
        } else {
            LOGGER.error("Ip address not found!");
        }
    }
}
