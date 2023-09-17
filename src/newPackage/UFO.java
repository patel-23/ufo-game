package newPackage;

import java.awt.*;
import javax.swing.*;

public class UFO extends JPanel {

	// Fields to hold relevant values
	public int x, y, xa, ya;
	private Cityscape c;
	private int diameter = 50;

	public UFO(Cityscape c, int x, int y, int xa, int ya) {

		// Initializes the fields
		this.c = c;
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
	}

	// Method moves the UFO object
	public void move() {

		if (x + xa < 0) // If the UFO exceeds the left
			xa = 1; // go right

		if (x + xa > c.getWidth() - 60) // If the UFO exceeds the right
			xa = -1; // go left

		if (y + ya < 10) // If the UFO exceeds the top
			ya = 1; // go down

		if (y + ya > c.getHeight() - 415) // If the UFO exceeds the bottom
			ya = -1; // go up

		x = x + xa; // Adjust the x and y coordinates
		y = y + ya; // by the appropriate speeds

	}

	// Method paints the UFO object
	public void paint(Graphics2D g) {

		// Smoothes out the graphics
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Creates a grey UFO body and a red UFO capsule
		g.setColor(Color.RED);
		g.fillOval(this.x + 15, this.y - 10, 30, 20);
		g.setColor(Color.DARK_GRAY);
		g.fillOval(this.x, this.y, 60, 20);

		// Creates 4 different colored lights on the UFO
		g.setColor(Color.CYAN);
		g.fillOval(this.x + 5, this.y + 6, 7, 7);
		g.setColor(Color.ORANGE);
		g.fillOval(this.x + 20, this.y + 6, 8, 8);
		g.setColor(Color.MAGENTA);
		g.fillOval(this.x + 35, this.y + 6, 8, 8);
		g.setColor(Color.GREEN);
		g.fillOval(this.x + 50, this.y + 6, 7, 7);

	}

	// Method checks if the UFO objects are colliding. If they are, they bounce off
	// each other
	public void collision(UFO u) {

		int dx = (x - u.x) + (xa - u.xa);
		int dy = (y - u.y) + (ya - u.ya);

		if (Math.sqrt(dx * dx + dy * dy) <= diameter) {

			// Switches velocities
			int tempX = xa;
			int tempY = ya;

			xa = u.xa;
			ya = u.ya;

			u.xa = tempX;
			u.ya = tempY;
		}
	}

}