package newPackage;

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Buildings extends JPanel {

	// Fields to hold relevant values
	private int x;
	private int y;
	private int w;
	private int h;
	Random r = new Random();
	private boolean[] status = new boolean[1000];

	public Buildings(int x, int y, int w, int h) {

		// Assigns values to the fields
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		// Fills a 2000 element array with booleans
		for (int i = 0; i < 1000; i++) {
			status[i] = false;
			status[i] = r.nextBoolean();
		}
	}

	// Method paints the actual building onto the frame
	public void paint(Graphics g) {
		Graphics2D pic = (Graphics2D) g;

		// Paints the background rectangle of the building
		pic.setColor(Color.DARK_GRAY);
		pic.fillRect(x, y, w, h);

		// Paints the windows of the building
		paintWindows(pic, x, y, w, h);

	}

	// Method to paint the windows of the building
	public void paintWindows(Graphics g, int x, int y, int width, int height) {

		// Sets the position of the windows
		int winX = x + 10;
		int winY = y + 10;

		Graphics2D pic = (Graphics2D) g;

		// Calculates the maximum number of rows and columns of windows possible
		// given the building's height and width
		int maxRow = (width - 10) / 30;
		int maxCol = (height - 10) / 30;

		// Generates the windows
		for (int i = maxCol; i > 0; i--) {
			for (int j = 0; j < maxRow; j++) {

				// Toggles whether the window light is on or off
				if (status[i * 23 + j]) {
					pic.setColor(Color.YELLOW);
				} else {
					pic.setColor(Color.LIGHT_GRAY);
				}

				// Paints a 17 pixel by 117 pixel window
				pic.fillRect(winX, winY, 17, 17);
				winX += 30;
			}

			// Adjusts coordinates for the next window
			winX = x + 10;
			winY += 30;

		}

	}

}
