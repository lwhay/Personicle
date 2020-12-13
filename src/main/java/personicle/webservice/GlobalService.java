package personicle.webservice;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.apache.asterix.external.generator.DataGenerator.Point;
import personicle.security.RSAKeyManager;
import personicle.utils.Converter;
import personicle.utils.IPAddressUtil;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.net.SocketException;

import org.apache.log4j.Logger;
import personicle.webservice.directoryService.DirectoryPublisher.GlobalDirectory;

@WebService
public class GlobalService {
    private static Logger LOGGER = Logger.getLogger(GlobalService.class);

    TimeBasedGenerator nbg = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    private final GlobalDirectory globalDirectory = new GlobalDirectory();

    public String getUUID() {
        return nbg.generate().toString().replace("-", "").trim();
    }

    public String reflushRSAPublicKey() {
        RSAKeyManager rm = new RSAKeyManager();
        rm.reflushKey("127.0.0.1");
        return rm.getPublicKey("127.0.0.1");
    }

    private String getGeoCode(Point point) {
        return String.valueOf(point.getLongitude() * 180 + point.getLatitude());
    }

    public String getGlobalDirectory() {
        return globalDirectory.getInformation();
    }

    public String getGeoCode(float x, float y) {
        return getGeoCode(new Point(x % 180, y));
    }

    public String getGeoCodeDouble(double x, double y) {
        System.out.println(x + "," + y);
        return getGeoCode((float) x % 180, (float) y);
    }

    public String feedRecord(String record) {
        System.out.println(record + "->" + ((JSONObject) JSONObject.parse(record)).toJSONString());
        return null;
    }

    public static void main(String[] args) throws SocketException {
        String ipAddress = IPAddressUtil.getEffectiveLocalIPAddress();
        if (ipAddress != null) {
            System.out.println(ipAddress);
            String address = "http://" + ipAddress + ":10901/GlobalService";
            Endpoint.publish(address, new GlobalService());

            /*
            http://localhost:10901/GlobalService/getGeoCodeDouble/arg0/10.0/arg1/1.0
            http://localhost:10901/GlobalService/feedRecord/arg0/{id:123,userName:"Michael Jordan"}
            */
        } else {
            LOGGER.error("IP not found!");
        }
    }
}
