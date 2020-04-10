package hsealexglushko.battelship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShipTest{

    @Test
    void getTypeTest()
    {
        Ship cruiser = new Cruiser();
        Ship battelship = new Battleship();
        Ship submarine = new Submarine();
        Ship destroyer = new Destroyer();

        assertEquals(submarine.getShipType(), "submarine");
        assertEquals(battelship.getShipType(), "battleship");
        assertEquals(destroyer.getShipType(), "destroyer");
        assertEquals(cruiser.getShipType(), "cruiser");
    }

    @Test
    void getLength(){
        Ship cruiser = new Cruiser();
        Ship battelship = new Battleship();
        Ship submarine = new Submarine();
        Ship destroyer = new Destroyer();

        assertEquals(cruiser.getLength(), 3);
        assertEquals(battelship.getLength(), 4);
        assertEquals(submarine.getLength(), 1);
        assertEquals(destroyer.getLength(), 2);
    }

    @Test
    void placeTest()
    {
        Ocean ocean = new Ocean();
        Ship ship = new Cruiser();
        ship.placeShipAt(1, 2,true, ocean);

        assertEquals(ship.getBowColumn(), 2);
        assertEquals(ship.getBowRow(), 1);
        assertTrue(ship.isHorizontal());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            ship.placeShipAt(23,9,false,ocean);
        });
    }

    @Test
    void isOkToPlaceTest()
    {
        Ocean ocean = new Ocean();
        Ship ship = new Cruiser();

        assertTrue(ship.okToPlaceShipAt(1, 2, true, ocean));
        assertFalse(ship.okToPlaceShipAt(9,9,false, ocean));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            ship.okToPlaceShipAt(23,9,false,ocean);
        });
    }

    @Test
    void shootTest(){
        Ocean ocean = new Ocean();
        Ship ship = new Cruiser();
        ship.placeShipAt(1, 2, true, ocean);

        assertFalse(ship.shootAt(1, 1));
        assertTrue(ship.shootAt(1,3));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            ship.shootAt(23,9);
        });
    }

    @Test
    void isSunkTest()
    {
        Ocean ocean = new Ocean();
        Ship ship = new Cruiser();
        ship.placeShipAt(1, 2, true, ocean);
        ship.shootAt(1, 2);
        ship.shootAt(1, 3);

        assertFalse(ship.isSunk());

        ship.shootAt(1, 4);

        assertTrue(ship.isSunk());
    }
}
