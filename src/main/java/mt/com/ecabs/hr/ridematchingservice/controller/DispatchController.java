package mt.com.ecabs.hr.ridematchingservice.controller;

import mt.com.ecabs.hr.ridematchingservice.model.*;
import mt.com.ecabs.hr.ridematchingservice.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

/**
 * For a simple project as this, we collect all the endpoints in one place.
 */
@RestController
@RequestMapping("/api/")
public class DispatchController {
    
    @Autowired
    private DispatchService dispatchService;

    /**
     * Req. 1: Register new driver.
     * I assume here for simplicity drivers are honest about their 
     */
    @PostMapping("/driver/register")
    public ResponseEntity<String> registerDriver(@RequestParam String driverId, 
                                                @RequestParam double x, @RequestParam double y) {
        dispatchService.registerDriver(new Driver(driverId, new Location(x, y), true));
        return ResponseEntity.ok("Driver registered/updated");
    }
    /**
     * req. 2:
     */
    @PostMapping("ride/request")
    public ResponseEntity<Ride> requestRide(@RequestBody Location pickupLocation) {
        return ResponseEntity.ok(dispatchService.requestRide(pickupLocation));
    }

    /**
     * req. 3:
     * 
     */
    @PostMapping("ride/complete/{rideId}")
    public ResponseEntity<String> completeRide(@PathVariable String rideId) {
        dispatchService.completeRide(rideId);
        return ResponseEntity.ok("OK");
    }

    /**
     * req 4: TODO! NOT Implemented.
     */
    @GetMapping("driver/nearest")
    public ResponseEntity<List<Driver>> getNearestDrivers(@RequestParam double x,
                                                          @RequestParam double y,
                                                          @RequestParam int count) {
        return ResponseEntity.ok(dispatchService.getNearestDrivers(new Location(x, y), count));
    }
}

