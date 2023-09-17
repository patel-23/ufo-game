package newPackage;

import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.awt.*;

public class Car {

	// Fields to hold relevant values
	public int x;
	public int y;
	public int xa = 1;
	public int width = 100;
	public int height = 75;
	public BufferedImage img = null;
	public boolean isVisible = true;

	public Car(int x, int y) {

		this.x = x;
		this.y = y;

		// Imports the image of the car
		try {
			img = ImageIO.read(new File("download.png"));
		} catch (IOException e) {
			System.err.println("Error whilst importing image");
		}

	}

	// Method moves the car object
	public void move() {

		// If the car reaches the right end of the frame, the car image is flip
		// and the car proceeds in the opposite direction
		if (x + width > 1020) {
			xa = -1;
			width = -100;
			x = 1010;

			// Same thing happens if the car reaches the left end of the frame
		} else if (x + width < 0) {
			xa = 1;
			width = 100;
			x = -20;
		}
		x += xa;

	}

	public void setInvisible() {
		isVisible = false;
	}

	// Method paints the car to the frame
	public void paint(Graphics2D g) {

		if (isVisible) {
			g.drawImage(img, x, y, width, height, null);
		}
	}

	public int[] getPosition() {
		int carPos[] = { x, x + width, y, y + height };
		return carPos;
	}

}
