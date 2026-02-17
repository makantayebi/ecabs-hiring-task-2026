package mt.com.ecabs.hr.ridematchingservice.model;

import lombok.Data;
@Data
public class Driver {
    private String id;
    private Location location;
    private boolean available;


    public Driver(String id, Location location) {
        this.id = id;
        this.location = location;
        // TODO: available?
    }

}