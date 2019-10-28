package personicle.webservice;

import org.junit.Test;

public class GlobalServiceTest {
    @Test
    public void testUUIDGeneration() {
        GlobalService localService = new GlobalService();
        for (int i = 0; i < 256; i++) {
            System.out.println(localService.getUUID());
        }
    }
}
