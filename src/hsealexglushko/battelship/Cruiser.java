package hsealexglushko.battelship;

/**
 * A {@code Cruiser} object represent a battle ship
 * and extends {@code Ship}.
 */
public class Cruiser extends Ship {

    /**
     * Construct an object {@code Cruiser},
     * has no parameters.
     */
    public Cruiser(){
        length = 3;
        for(int i = 0; i < length; i++){
            hit[i] = false;
        }
    }

    /**
     * Give ship type
     * @return name of type, "cruiser"
     */
    @Override
    public String getShipType() {
        return "cruiser";
    }
}
