package hsealexglushko.battelship;

/**
 * A {@code Battleship} object represent a battle ship
 * and extends {@code Ship}.
 */
public class Battleship extends Ship{

    /**
     * Construct an object {@code BattleShip},
     * has no parameters.
     */
    public Battleship(){
        length = 4;
        for(int i = 0; i < length; i++){
            hit[i] = false;
        }
    }

    /**
     * Give ship type
     * @return name of type, "battleship"
     */
    @Override
    public String getShipType() {
        return "battleship";
    }
}
