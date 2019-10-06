package personicle.webservice;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class GlobalUUIDService {

    TimeBasedGenerator nbg = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    public String getUUID() {
        return nbg.generate().toString().replace("-", "").trim();
    }

    public static void main(String[] args) {
        String address = "http://192.168.56.133:10901/GlobalUUIDSerivce";
        Endpoint.publish(address, new GlobalUUIDService());
    }
}
