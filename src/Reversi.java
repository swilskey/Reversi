/**
 * Created by source41 on 4/19/2015.
 */
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Reversi extends JPanel implements MouseListener {
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    
    private Board b;

    final static Color COLOURPLAYER =  Color.WHITE;
    final static Color COLOUREMPTY =  Color.ORANGE;
    final static Color COLOURCOMP =  Color.BLACK;

    private Graphics2D g2d;

    FontMetrics metrics;

    Hexagon[] hexagons = new Hexagon[122];
    int counter = 0;

    public Reversi(Board b) {
         
        this.b = b;
        b.initialize();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseListener(this);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        Point origin = new Point(WIDTH / 2, HEIGHT / 2);

        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        metrics = g.getFontMetrics();

        drawCircle(g2d, origin, 380, true, true, 0x4488FF, 0);
        drawHexGridLoop(g2d, origin, 9, 40, 8);
    }

    private void drawHexGridLoop(Graphics g, Point origin, int size, int radius, int padding) {
        double ang30 = Math.toRadians(30);
        double xOff = Math.cos(ang30) * (radius + padding);
        double yOff = Math.sin(ang30) * (radius + padding);
        int half = size / 2;

        for (int row = 0; row < size; row++) {
            int cols = size - java.lang.Math.abs(row - half);

            for (int col = 0; col < cols; col++) {
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);

                drawHex(g, row, col, x, y, radius);

                counter++;
            }
        }
        counter = 0;
    }

    private void drawHex(Graphics g, int posX, int posY, int x, int y, int r) {
        g2d = (Graphics2D) g;

        hexagons[counter] = new Hexagon(x, y, r, posX, posY);
        String text = String.format("%s : %s", coord(posX), coord(posY));
        int w = metrics.stringWidth(text);
        int h = metrics.getHeight();

        if(b.isBlack(posX, posY)) {
            hexagons[counter].draw(g2d, x, y, 0, 0x000000, true);
            hexagons[counter].draw(g2d, x, y, 4, 0xFFDD88, false);
        } else if(b.isWhite(posX, posY)) {
            hexagons[counter].draw(g2d, x, y, 0, 0xffffff, true);
            hexagons[counter].draw(g2d, x, y, 4, 0xFFDD88, false);
        } else if(b.isEmpty(posX, posY)) {
            hexagons[counter].draw(g2d, x, y, 0, 0x777777, true);
            hexagons[counter].draw(g2d, x, y, 4, 0xFFDD88, false);
        }
    }

    private String coord(int value) {
        return (value > 0 ? "+" : "") + Integer.toString(value);
    }

    public void drawCircle(Graphics2D g, Point origin, int radius,
                           boolean centered, boolean filled, int colorValue, int lineThickness) {
        // Store before changing.
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));

        int diameter = radius * 2;
        int x2 = centered ? origin.x - radius : origin.x;
        int y2 = centered ? origin.y - radius : origin.y;

        if (filled)
            g.fillOval(x2, y2, diameter, diameter);
        else
            g.drawOval(x2, y2, diameter, diameter);

        // Set values to previous when done.
        g.setColor(tmpC);
        g.setStroke(tmpS);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        for(int i = 0; i < hexagons.length / 2; i++) {
            if(x - hexagons[i].getCenter().getX() <= 40 && x - hexagons[i].getCenter().getX() >= -40 && y - hexagons[i].getCenter().getY() <= 40 && y - hexagons[i].getCenter().getY() >= -40 ) {

                GameController.getInstance().playerMove(hexagons[i].getCordX(), hexagons[i].getCordY());
                this.repaint();
                break;
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
