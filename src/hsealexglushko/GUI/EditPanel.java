package hsealexglushko.GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Panel with exit buttons and edit element
 */
public class EditPanel extends JPanel{
    private JTextField editText = new JTextField(25);
    private JButton enter = new JButton("Enter");
    private JButton exitButton = new JButton("Back to menu");

    public JTextField getEditText(){
        return editText;
    }

    public JButton getEnter(){
        return enter;
    }

    public JButton getExitButton(){
        return exitButton;
    }

    public EditPanel()
    {
        this.setLayout(new GridLayout(2, 1, 2, 2));

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel flowExit = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel editLabel = new JLabel("Hand edit:");

        // Settings
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize();
        exitButton.setPreferredSize(new Dimension(sSize.width / 4 + 20, sSize.height / 20));

        // Add to flow
        flow.add(editLabel);
        flow.add(editText);
        flow.add(enter);
        flowExit.add(exitButton);

        // Customization flow
        flow.setOpaque(false);
        flowExit.setOpaque(false);

        // Add to grid
        this.add(flow);
        this.add(flowExit);

        // Customization
        editText.setForeground(Color.WHITE);
        editLabel.setForeground(Color.WHITE);
        enter.setForeground(Color.WHITE);
        exitButton.setForeground(Color.WHITE);

        enter.setBorder(new RoundedBorder(10));
        editText.setBorder(new RoundedBorder(10));
        exitButton.setBorder(new RoundedBorder(10));

        editText.setOpaque(false);

        // Font Setting
        try{
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font labelFont = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("ComicNeue-Bold.ttf")));
            genv.registerFont(labelFont);
            labelFont = labelFont.deriveFont(18f);
            editLabel.setFont(labelFont);
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
