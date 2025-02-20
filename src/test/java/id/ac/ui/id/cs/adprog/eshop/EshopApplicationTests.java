package id.ac.ui.id.cs.adprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EshopApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void mainMethodStartsApplication() {
        try {
            EshopApplication.main(new String[]{});
            assertTrue(true, "Application should start without throwing exceptions");
        } catch (Exception e) {
            fail("Application should not throw any exceptions on startup: " + e.getMessage());
        }
    }
}
