package personicle.webservice;

import org.junit.Test;

public class GlobalUUIDCXFServiceTest {
    @Test
    public void testUUIDGeneration() {
        GlobalUUIDCXFService service = new GlobalUUIDCXFService();
        for (int i = 0; i < 256; i++) {
            System.out.println(service.getUUID());
        }
    }
}
