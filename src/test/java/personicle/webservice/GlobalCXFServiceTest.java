package personicle.webservice;

import org.junit.Test;

public class GlobalCXFServiceTest {
    @Test
    public void testUUIDGeneration() {
        GlobalCXFService service = new GlobalCXFService();
        for (int i = 0; i < 256; i++) {
            System.out.println(service.getUUID());
        }
    }
}
