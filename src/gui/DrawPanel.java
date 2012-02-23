package gui;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class DrawPanel extends JPanel implements MouseMotionListener,
        ActionListener, MouseListener {
    private static final long serialVersionUID = 1789204553405396351L;
    private Drawable pict;
    private boolean visible;

    public DrawPanel() {
        pict = null;
        addMouseMotionListener(this);
        addMouseListener(this);
        visible = true;
    }

    public void scheduleRepaint() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                repaint();
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        repaint();
    }

    public void add(Drawable drawable) {
        pict = drawable;
    }

    /** Handles the complicated painting for the panel. <br> */
    public void paint(Graphics g) {
        if (!visible) {
            return;
        }

        int width = getSize().width;
        int height = getSize().height;

        // use double buffering
        Image offScreenBuffer = createImage(width, height);
        Graphics gOff = offScreenBuffer.getGraphics();

        gOff.setColor(Color.white);
        gOff.fillRect(0, 0, width, height);

        if (pict != null)
            pict.draw(gOff, width, height);

        g.drawImage(offScreenBuffer, 0, 0, null); // copy the offScreenImage to
                                                  // the panel
    }

    public void mouseMoved(MouseEvent event) {
        if (!visible) {
            return;
        }

        if (pict != null)
            pict.mouseMoved(event.getX(), event.getY(), getSize().width,
                    getSize().height);
        repaint();
    }

    public void mouseDragged(MouseEvent event) {
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        if (!visible) {
            return;
        }

        if (pict != null) {
            pict.mouseClicked(arg0.getX(), arg0.getY(), arg0.getButton());
        }
        repaint();
    }

    public void mouseEntered(MouseEvent arg0) {

    }

    public void mouseExited(MouseEvent arg0) {

    }

    public void mousePressed(MouseEvent arg0) {

    }

    public void mouseReleased(MouseEvent arg0) {

    }
}
