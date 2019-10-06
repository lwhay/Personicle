package personicle.webservice;

import org.junit.Test;

public class GlobalUUIDServiceTest {
    @Test
    public void testUUIDGeneration() {
        GlobalUUIDService localService = new GlobalUUIDService();
        for (int i = 0; i < 256; i++) {
            System.out.println(localService.getUUID());
        }
    }
}
