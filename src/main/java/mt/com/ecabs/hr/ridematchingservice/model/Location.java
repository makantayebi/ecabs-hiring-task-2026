package mt.com.ecabs.hr.ridematchingservice.model;

import lombok.Data;

@Data
public class Location {
    private double x, y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Location other) {
        if(other == null) {
            // TODO Log error
            System.err.println(" distance calculation got null object.");
        }
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
}