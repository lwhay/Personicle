package personicle.webservice;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import personicle.utils.IPAddressUtil;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.net.SocketException;

import org.apache.log4j.Logger;

@WebService
public class GlobalUUIDService {
    private static Logger LOGGER = Logger.getLogger(GlobalUUIDService.class);

    TimeBasedGenerator nbg = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    public String getUUID() {
        return nbg.generate().toString().replace("-", "").trim();
    }

    public String getReturn() {
        return nbg.generate().toString().replace("-", "").trim();
    }

    public String setReturn() {
        return nbg.generate().toString().replace("-", "").trim();
    }

    public static void main(String[] args) throws SocketException {
        String ipAddress = IPAddressUtil.getEffectiveLocalIPAddress();
        if (ipAddress != null) {
            System.out.println(ipAddress);
            String address = "http://" + ipAddress + ":10901/GlobalUUIDSerivce";
            Endpoint.publish(address, new GlobalUUIDService());
        } else {
            LOGGER.error("IP not found!");
        }
    }
}
