package personicle.webservice;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.apache.asterix.external.generator.DataGenerator.Point;
import personicle.utils.IPAddressUtil;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.net.SocketException;

import org.apache.log4j.Logger;

@WebService
public class GlobalService {
    private static Logger LOGGER = Logger.getLogger(GlobalService.class);

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
        String ipAddress = IPAddressUtil.getEffectiveLocalIPAddress();
        if (ipAddress != null) {
            System.out.println(ipAddress);
            String address = "http://" + ipAddress + ":10901/GlobalSerivce";
            Endpoint.publish(address, new GlobalService());
        } else {
            LOGGER.error("IP not found!");
        }
    }
}