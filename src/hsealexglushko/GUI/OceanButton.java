package hsealexglushko.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Special JButton for game board
 */
public class OceanButton extends JButton{
    private int column;
    private int row;
    boolean isFired = false;

    public int getColumn(){
        return column;
    }

    public int getRow(){
        return row;
    }

    public OceanButton(int column, int row){
        super();
        this.column = column;
        this.row =row;
        setBorder(new RoundedBorder(10));
        setForeground(Color.WHITE);
    }


}
