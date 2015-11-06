package City;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class City {

    double x;
    double y;
    int index;

    public City(double x, double y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public int getIndex() {
        return this.index;
    }

    public double distance(City city2) {
        double distance = 0;

        distance = pow((city2.getX()-this.x), 2) + pow((city2.getY()-this.y), 2);
        distance = sqrt(distance);
        return distance;
    }
}
