package hsealexglushko.GUI;

import hsealexglushko.battelship.Ocean;
import hsealexglushko.battelship.Ship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

/**
 * Game window
 */
public class GameWindow extends JFrame{

    // Consts for correct writing
    final int logStringNumber = 6;
    final String titleLog = "Logs about shoots: \n";
    final String titleInformation = "Information about game: \n";

    // log row collection
    Vector<String> logCollection = new Vector<>();

    // Field
    private OceanButton[][] oceanButtons = new OceanButton[10][10];
    private Ocean ocean = new Ocean();

    // Icons for message dialog
    final ImageIcon missShoot = new ImageIcon(ClassLoader.getSystemResource("waterShoot.png"));
    final ImageIcon shipShoot = new ImageIcon(ClassLoader.getSystemResource("shipShoot.png"));
    final ImageIcon sunkShoot = new ImageIcon(ClassLoader.getSystemResource("sunkShip.png"));
    final ImageIcon congratulationIcon = new ImageIcon(ClassLoader.getSystemResource("congratulationIcon.png"));

    // Elements in Frame
    private JTextArea log = new JTextArea();
    private JTextArea information = new JTextArea();
    private EditPanel editPanel = new EditPanel();

    //Counters for logger
    private int fireCount = 0;
    private int shipCount = 10;

    public GameWindow(){
        super();

        ocean.placeAllShipsRandomly();

        // Make window full screen
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize();
        this.setSize(sSize);
        setResizable(false);

        // Set background image
        try
        {
            BufferedImage myImage = ImageIO.read(ClassLoader.getSystemResource("gameWindowBackground.png"));
            this.setContentPane(new ImagePanel(myImage));
        }
        catch(IOException e)
        {
            final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("iconMessage.png"));
            JOptionPane.showMessageDialog(null,
                    "Something wrong with background image!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE,
                    icon);
        }

        // Create Layout
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT, 25, 3));

        // Creat field
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                OceanButton button = new OceanButton(i, j);
                oceanButtons[j][i] = button;
                ActionListener listener = new OceanButtonListener();
                button.addActionListener(listener);
                add(button);
            }
        }

        // Creat element
        GameBoardPanel gameBoardPanel = new GameBoardPanel(oceanButtons);

        //Add listeners
        ActionListener listener = new OceanButtonListener();
        editPanel.getEnter().addActionListener(listener);

        // settings for size
        gameBoardPanel.setPreferredSize(new Dimension( sSize.width * 3 / 4, sSize.height * 3 / 4));
        information.setPreferredSize(new Dimension(sSize.width / 5, sSize.height / 5));
        editPanel.setPreferredSize(new Dimension(sSize.width / 2 - 40, sSize.height / 4 - 40));
        log.setPreferredSize(new Dimension(sSize.width * 3 / 7, sSize.height / 4 - 20));

        // Add elements
        add(gameBoardPanel);
        add(information);
        add(editPanel);
        add(log);

        gameBoardPanel.setOpaque(false);
        information.setOpaque(false);
        editPanel.setOpaque(false);
        log.setOpaque(false);

        // Font settings
        try{
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font infoFont = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("ComicNeue-Bold.ttf")));
            genv.registerFont(infoFont);
            infoFont = infoFont.deriveFont(20f);
            information.setFont(infoFont);
            log.setFont(infoFont);

            // Make titles
            log.setText(log.getText() + titleLog);
            information.setText(information.getText() + titleInformation);

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

        log.setForeground(Color.WHITE);
        information.setForeground(Color.WHITE);
        log.setBorder(new RoundedBorder(5));
        information.setBorder(new RoundedBorder(5));

        // Add edit panel listens
        editPanel.getExitButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MenuWindow menu = new MenuWindow();
                menu.setVisible(true);
                dispose();
            }

        });

        editPanel.getEditText().addKeyListener(new KeyAdapter(){

            @Override
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode() == 10)
                {
                    listener.actionPerformed(null);
                }
            }
        });
    }

    public class OceanButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            if(!ocean.isGameOver()){

                OceanButton firedButton;

                if(e != null && e.getSource().getClass() == OceanButton.class){
                    firedButton = (OceanButton) e.getSource();
                }
                else
                {

                    String text = editPanel.getEditText().getText();
                    String[] symbols = text.split(" ");
                    int row;
                    int column;
                    try
                    {
                        row = Integer.parseInt(symbols[0]);
                        column = Integer.parseInt(symbols[1]);
                        firedButton = oceanButtons[row][column];
                        editPanel.getEditText().setText("");
                    }
                    catch(IndexOutOfBoundsException | NumberFormatException n){
                        if(!text.equals("")){
                            final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("iconMessage.png"));
                            JOptionPane.showMessageDialog(null,
                                    "You input incorrect data! \n You should input numbers for row and column!",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE,
                                    icon);
                            editPanel.getEditText().setText("");
                        }
                        return;
                    }
                }
                Ship ship = ocean.getShips()[firedButton.getRow()][firedButton.getColumn()];

                if(firedButton.isFired){
                    final ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("iconMessage.png"));
                    JOptionPane.showMessageDialog(null,
                            "You shoot here already!",
                            "Information",
                            JOptionPane.ERROR_MESSAGE,
                            icon);
                }
                else{
                    if(ocean.shootAt(firedButton.getRow(), firedButton.getColumn())){
                        if(ship.isSunk()){
                            int row = ship.getBowRow();
                            int column = ship.getBowColumn();

                            if(fireCount > 0)
                                fireCount -= ship.getLength() - 1;
                            shipCount--;

                            for(int i = 0; i < ship.getLength(); i++)
                            {
                                if(ship.isHorizontal())
                                    oceanButtons[row][column + i].setIcon(sunkShoot);
                                else
                                    oceanButtons[row + i][column].setIcon(sunkShoot);
                            }

                            if(logCollection.size() >= logStringNumber)
                            {
                                logCollection.remove(0);

                            }
                            logCollection.add("row = " +
                                    firedButton.getRow() +
                                    " column = " +
                                    firedButton.getColumn() +
                                    " : sunk " +
                                    ship.getShipType() + "\n");

                        }
                        else
                        {
                            fireCount++;
                            firedButton.setIcon(shipShoot);
                            if(logCollection.size() >= logStringNumber)
                            {
                                logCollection.remove(0);

                            }
                            logCollection.add("row = " +
                                    firedButton.getRow() +
                                    " column = " +
                                    firedButton.getColumn() +
                                    " : shoot a ship\n");
                        }
                    }
                    else{
                        firedButton.setIcon(missShoot);

                        if(logCollection.size() >= logStringNumber)
                        {
                            logCollection.remove(0);

                        }
                        logCollection.add("row = " +
                                firedButton.getRow() +
                                " column = " +
                                firedButton.getColumn() +
                                " : miss\n");
                    }

                    firedButton.isFired = true;
                    reDrawLog();
                    reDrawInformation();
                }

                if(ocean.isGameOver()){
                    JOptionPane.showMessageDialog(null,
                            "I win! Congratulation!",
                            "Information",
                            JOptionPane.ERROR_MESSAGE,
                            congratulationIcon);
                }
            }
        }
    }

    private void reDrawLog()
    {
        StringBuilder buf = new StringBuilder();

        buf.append(titleLog);

        for(String s : logCollection)
        {
            buf.append(s);
        }

        log.setText(buf.toString());
    }

    private void reDrawInformation()
    {
        StringBuilder buf = new StringBuilder();

        buf.append(titleInformation);
        buf.append("\n");
        buf.append("Shoots count - ");
        buf.append(ocean.getShotsFired());
        buf.append("\n");
        buf.append("Ships left - ");
        buf.append(shipCount);
        buf.append("\n");
        buf.append("Ships crashed - ");
        buf.append(fireCount);
        buf.append("\n");
        buf.append("Ships sunk - ");
        buf.append(ocean.getShipsSunk());

        information.setText(buf.toString());
    }
}
