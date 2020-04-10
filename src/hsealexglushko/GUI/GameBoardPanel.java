package hsealexglushko.GUI;


import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 * JPanel for board
 * board made from buttons
 */
public class GameBoardPanel extends JPanel{

    public GameBoardPanel(OceanButton[][] buttons){
        super();
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize();
        this.setSize(sSize);

        this.setLayout(new GridLayout(11, 11, 2, 2));

        // Font settings
        try{
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font labelFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResource("/ComicNeue-Regular.ttf").openStream());
            genv.registerFont(labelFont);
            labelFont = labelFont.deriveFont(30f);

            // Board creation
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            add(label);

            for(int i = 0; i < 10; i++)
            {
                JLabel labelNumber = new JLabel(String.valueOf(i));
                labelNumber.setHorizontalAlignment(JLabel.CENTER);
                labelNumber.setVerticalAlignment(JLabel.CENTER);
                labelNumber.setForeground(Color.WHITE);
                labelNumber.setFont(labelFont);
                add(labelNumber);
            }

            for(int i = 0; i < 10; i++)
            {
                JLabel labelNumber = new JLabel(String.valueOf(i));
                labelNumber.setHorizontalAlignment(JLabel.CENTER);
                labelNumber.setVerticalAlignment(JLabel.CENTER);
                labelNumber.setForeground(Color.WHITE);
                labelNumber.setFont(labelFont);
                add(labelNumber);

                for(int j = 0; j < 10; j++)
                {
                    add(buttons[i][j]);
                }
            }

        }
        catch(FontFormatException | IOException e)
        {
            final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("iconMessage.png"));
            JOptionPane.showMessageDialog(null,
                    "Something wrong with fonts!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    icon);
        }
    }
}
