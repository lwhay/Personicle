package personicle.webservice;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.apache.cxf.frontend.ServerFactoryBean;

import javax.xml.ws.Endpoint;

public class GlobalUUIDCXFService {

    TimeBasedGenerator nbg = Generators.timeBasedGenerator(EthernetAddress.fromInterface());

    public String getUUID() {
        return nbg.generate().toString().replace("-", "").trim();
    }

    public static void main(String[] args) {
        ServerFactoryBean serverFactoryBean = new ServerFactoryBean();
        String address = "http://192.168.56.133:10901/GlobalUUIDSerivce";
        serverFactoryBean.setAddress(address);
        serverFactoryBean.setServiceClass(GlobalUUIDService.class);
        serverFactoryBean.create();
    }
}
