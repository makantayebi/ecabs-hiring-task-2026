package mt.com.ecabs.hr.ridematchingservice.model;

import lombok.Data;

import java.util.UUID;

/**
 * There was no reference to keeping track of passengers. I avoid that because I have 45 minutes left here..
 */
@Data
public class Ride {
    private String rideId = UUID.randomUUID().toString();
    private Driver driver;
    private Location pickupLocation;
}