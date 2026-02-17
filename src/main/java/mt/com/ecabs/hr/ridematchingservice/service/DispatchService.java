package mt.com.ecabs.hr.ridematchingservice.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import mt.com.ecabs.hr.ridematchingservice.model.*;
import java.util.List;
import java.util.Map;

/**
 * All the services on one place, but Ideally I'd split them into classes for DriverService, etc.
 */
@Service
public class DispatchService {
    private final Map<String, Driver> drivers = new ConcurrentHashMap<>();
    private final Map<String, Ride> activeRides = new ConcurrentHashMap<>();

    /**
     * req 1
     */
    public synchronized void registerDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    /**
     * req 2
     */
    public synchronized Ride requestRide(Location pickupLocation) {
        
        Driver driver = getClosestDriver(pickupLocation);
        if(driver == null) {
            throw new RuntimeException("No drivers");
        }
        driver.setAvailable(false);
        Ride ride = new Ride();
        ride.setDriver(driver);
        ride.setPickupLocation(pickupLocation);
        activeRides.put(ride.getRideId(), ride);
        return ride;
    }

    /**
     * req 3
     */
    public synchronized void completeRide(String rideId) {
        Ride ride = activeRides.remove(rideId);
        if (ride != null) {
            ride.getDriver().setAvailable(true);
        } else {
            throw new RuntimeException("Ride not found");
        }
    }

    public Driver getClosestDriver(Location location) {
        Driver closestDriver = null;
        double min = Double.MAX_VALUE;
        for (Driver driver : drivers.values()) {
            if (!driver.isAvailable()) {
                continue;
            }
            double distance = driver.getLocation().distanceTo(location);
            if (distance < min) {
                min = distance;
                closestDriver = driver;
            }
        }
        return closestDriver;
    }

    /**
     * req 4 TODO: get the list of x closest drivers: time permitting...
     */
    public List<Driver> getNearestDrivers(Location location, int size) {
        throw new UnsupportedOperationException("Nearest k drivers not implemented yet.");
    }
}

