package hsealexglushko.battelship;

/**
 * A {@code Ship} abstract object represent a ship and
 * implement all methods it need for game
 */
public abstract class Ship {

    private int bowRow;
    private int bowColumn;
    int length;
    private boolean horizontal;
    boolean[] hit = new boolean[4];
    private int countToStringRequests = 0;

    /**
     * Getter
     * @return Bow length
     */
    public int getLength(){
        return length;
    }

    /**
     * Getter
     * @return Bow Row
     */
    public int getBowRow() {
        return bowRow;
    }

    /**
     * Getter
     * @return bow column
     */
    public int getBowColumn() {
        return bowColumn;
    }

    /**
     * Getter
     * @return if ship placed horizontal or not
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * Getter
     * @return nothing because we will override it in children classes
     */
    public String getShipType(){
        return "";
    }

    /**
     * Check if it is okay to put it here
     * @param row number of row
     * @param column number of column
     * @param horizontal boolean value for checking if it is horizontal
     * @param ocean where we check if it okay to put it here
     * @return boolean value if we can put ship in this cell
     * @throws IndexOutOfBoundsException
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) throws IndexOutOfBoundsException{
        if(row < 0 || row > 9 || column < 0 || column > 9)
            throw new IndexOutOfBoundsException();
        int startI = 0;
        int startJ = 0;
        int endI = 3;
        int endJ = length + 2;

        if((column + length - 1 > 9 && horizontal) || (row + length - 1 > 9 && !horizontal))
            return false;

        if(horizontal){
            if(row == 9)
                endI = 2;

            if(row == 0)
                startI = 1;

            if(column == 0)
                startJ = 1;

            if(column + length - 1 == 9)
                endJ --;
        }
        else {
            endJ = 3;
            endI = length + 2;

            if(column == 9)
                endJ = 2;

            if(column == 0)
                startJ = 1;

            if(row == 0)
                startI = 1;

            if(row + length - 1 == 9)
                endI--;
        }

        for(int i = startI; i < endI; i++){
            for(int j = startJ; j < endJ; j++){
                if(ocean.isOccupied(i + row - 1, j + column - 1))
                    return false;
            }
        }

        return true;
    }

    /**
     * Method put ship where we ask
     * @param row number of row
     * @param column number of column
     * @param horizontal boolean value if ship is placed horizontal
     * @param ocean where we need to put our ship
     * @throws IndexOutOfBoundsException
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) throws IndexOutOfBoundsException{
        if(row < 0 || row > 9 || column < 0 || column > 9)
            throw new IndexOutOfBoundsException();
        bowRow = row;
        bowColumn = column;
        this.horizontal = horizontal;
        if(horizontal){
            for(int i = 0; i< length; i++){
                ocean.getShips()[row][column + i] = this;
            }
        }
        else {
            for(int i = 0; i< length; i++){
                ocean.getShips()[row + i][column] = this;
            }
        }
    }

    /**
     * Method shoot in cell we asked
     * @param row number of row
     * @param column number of column
     * @return if shoot was successful
     * @throws IndexOutOfBoundsException
     */
    boolean shootAt(int row, int column) throws IndexOutOfBoundsException{
        if(row < 0 || row > 9 || column < 0 || column > 9)
            throw new IndexOutOfBoundsException();
        if(horizontal && row == bowRow && column >= bowColumn
           && column <= bowColumn + length - 1 && !hit[column - bowColumn]){
            hit[column - bowColumn] = true;
            return true;
        }
        if(column == bowColumn && row >= bowRow && row <= bowRow + length - 1 && !hit[row - bowRow]){
            hit[row - bowRow] = true;
            return true;
        }
        return false;
    }

    /**
     * Check if ship is sunk
     * @return if ship is sunk
     */
    public boolean isSunk(){
        boolean hitFlag = true;
        for (int i = 0; i < length; i++){
            hitFlag &= hit[i];
        }
        return hitFlag;
    }
}
