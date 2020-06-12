package hsealexglushko.GUI;

import javax.swing.border.Border;
import java.awt.*;

/**
 * Rounding boarders
 */
public class RoundedBorder implements Border{
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
