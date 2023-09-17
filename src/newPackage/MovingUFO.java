package newPackage;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class MovingUFO extends JPanel {

	private int x, y, xa, ya;
	private Cityscape c;

	private int diameter = 50;
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;
	private boolean space = false;
	private Car car;

	public MovingUFO(Cityscape c, int x, int y, int xa, int ya, Car car) {

		// Initializes the fields
		this.c = c;
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
		this.car = car;

	}

	public boolean getSpace() {
		return space;
	}

	// Method paints the UFO object
	public void paint(Graphics2D g) {
		int temp = 0;

		if (space) {
			int[] beamX = { x - 60, x + 10, x + 50, x + 120 };
			int[] beamY = { y + 500, y + 17, y + 17, y + 500 };

			if (car.x > beamX[0] && car.x < beamX[3] && car.y-car.height<beamY[0]) {
				car.xa = 0;
				car.y -= 2;
				temp = car.y;
				if (temp < this.y) {

					car.setInvisible();
				}

			}

			Color tractorBeam = new Color(255, 251, 0, 50);
			g.setColor(tractorBeam);
			g.fillPolygon(beamX, beamY, 4);
		}

		// Smoothes out the graphics
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Creates a grey UFO body and a red UFO capsule
		g.setColor(Color.ORANGE);
		g.fillOval(this.x + 15, this.y - 10, 30, 20);
		g.setColor(Color.MAGENTA);
		g.fillOval(this.x, this.y, 60, 20);

		// Creates 4 different colored lights on the UFO
		g.setColor(Color.WHITE);
		g.fillOval(this.x + 5, this.y + 6, 7, 7);
		g.fillOval(this.x + 20, this.y + 6, 8, 8);
		g.fillOval(this.x + 35, this.y + 6, 8, 8);
		g.fillOval(this.x + 50, this.y + 6, 7, 7);

	}

	// Method checks if the UFO objects are colliding. If they are, they bounce off
	// each other
	public void collision(UFO u) {

		int dx = (x - u.x) + (xa - u.xa);
		int dy = (y - u.y) + (ya - u.ya);

		if (Math.sqrt(dx * dx + dy * dy) <= diameter) {

			u.xa = u.xa * -1;
			u.ya = u.ya * -1;

		}
	}

	// Method checks if an arrow key was pressed and changes the according boolean
	// values
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			space = true;
		}

	}

	// Method checks if an arrow key was pressed, changes the according boolean
	// values, and sets the UFO acceleration to 0
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
			xa = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
			xa = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
			ya = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
			ya = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			space = false;
		}
	}

	// Method allows the user to move the UFo manually with arrow keys
	public void move() {

		// Keeps UFO within borders of the frame
		if (x + xa < 0)
			left = false;
		xa = 0;

		if (x + xa > c.getWidth() - 60)
			right = false;
		xa = 0;

		if (y + ya < 10)
			up = false;
		ya = 0;

		// Adjusts the acceleration depending on the keyboard input

		if (right) {
			xa = 1;
		}
		if (left) {
			xa = -1;
		}
		if (down) {
			ya = 1;
		}
		if (up) {
			ya = -1;
		}

		// Actually adjusts the acceleration values
		x += xa;
		y += ya;

	}

}
