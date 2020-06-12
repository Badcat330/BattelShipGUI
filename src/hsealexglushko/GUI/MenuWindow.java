package hsealexglushko.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

/**
 * Menu window
 */
public class MenuWindow extends JFrame{

    public MenuWindow(){
        super();

        setResizable(false);

        // Make window full screen
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize();
        this.setSize(sSize);

        // Put background image
        try
        {
            BufferedImage myImage = ImageIO.read(ClassLoader.getSystemResource("menuBackground.jpg"));
            this.setContentPane(new ImagePanel(myImage));
        }
        catch(IOException e)
        {
            final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("iconMessage.png"));
            JOptionPane.showMessageDialog(null,
                    "Something wrong with background image",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    icon);
        }

        // Declare elements which we will need
        JButton gameIIButton = new JButton("Game with computer");
        JButton gameMultiplayerButton = new JButton("Multiplayer Game");
        JLabel titleLabel = new JLabel("Menu");

        // Put them into container
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3, 1, 2, 2));
        container.add(titleLabel);
        container.add(gameIIButton);
        container.add(gameMultiplayerButton);

        // Add Fonts
        try{
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font labelFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResource("/ComicNeue-Regular.ttf").openStream());
            Font buttonFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResource("/Oswald-VariableFont_wght.ttf").openStream());
            genv.registerFont(buttonFont);
            genv.registerFont(labelFont);
            labelFont = labelFont.deriveFont(100f);
            buttonFont = buttonFont.deriveFont(50f);

            gameIIButton.setFont(buttonFont);
            gameMultiplayerButton.setFont(buttonFont);
            titleLabel.setFont(labelFont);
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

        // title customization
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);

        // buttons customization
        gameIIButton.setBorder(new RoundedBorder(30));
        gameMultiplayerButton.setBorder(new RoundedBorder(30));
        gameIIButton.setForeground(Color.white);
        gameMultiplayerButton.setForeground(Color.white);

        // Add listeners for buttons
        gameMultiplayerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("iconMessage.png"));
                JOptionPane.showMessageDialog(null,
                                                     "Multiplayer game is not ready yet",
                                                         "Information",
                                                              INFORMATION_MESSAGE,
                                                              icon);
            }

        });

        gameIIButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow gameWindow = new GameWindow();
                gameWindow.setVisible(true);
                dispose();
                final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("gameStart.jpg"));
                JOptionPane.showMessageDialog(null,
                        "Welcome to the sea battle.\n Click on the cells of the field or enter" +
                                " the number of the row and column separated by a space manually and win!",
                        "Information",
                        INFORMATION_MESSAGE,
                        icon);
            }

        });
    }
}
