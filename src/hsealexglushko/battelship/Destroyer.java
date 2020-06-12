package hsealexglushko.battelship;

/**
 * A {@code Destroyer} object represent a battle ship
 * and extends {@code Ship}.
 */
public class Destroyer extends Ship {

    /**
     * Construct an object {@code Destroyer},
     * has no parameters.
     */
    public Destroyer() {
        length = 2;
        for(int i = 0; i < length; i++){
            hit[i] = false;
        }
    }

    /**
     * Give ship type
     * @return name of type, "destroyer"
     */
    @Override
    public String getShipType() {
        return "destroyer";
    }
}
