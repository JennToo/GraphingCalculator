/* Original class by Dr. Mark Boshart, Tennessee Technological University, mboshart@tntech.edu
 * Used with permission.
 */
package gui;
import java.awt.Graphics;

public interface Drawable {
    public void draw(Graphics g, int width, int height);

    public void mouseMoved(int x, int y, int width, int height);
    public void mouseClicked(int x, int y, int button);
}