package mt.com.ecabs.hr.ridematchingservice.service;
import mt.com.ecabs.hr.ridematchingservice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DispatchServiceTest {

    private DispatchService dispatchService;

    @BeforeEach
    void setUp() {
        dispatchService = new DispatchService();
    }
    @Test
    void testRegisterDriver() {
        dispatchService.registerDriver(new Driver ("driver1", new Location(0, 0), true));
        Driver driver = dispatchService.getClosestDriver(new Location(0, 0));
        assertTrue(driver.isAvailable());
        assertEquals("driver1", driver.getId());
    }

    @Test
    void testRequestRide() {
        dispatchService.registerDriver(new Driver ("driver1", new Location(0, 0), true));
        dispatchService.registerDriver(new Driver ("driver2", new Location(100, 0), true));

        Ride ride = dispatchService.requestRide(new Location(1, 1));

        assertNotNull(ride.getRideId());
        assertEquals("driver1", ride.getDriver().getId()); // Nearest
        assertFalse(ride.getDriver().isAvailable());

    }

    // TODO The rest of tests here.
}