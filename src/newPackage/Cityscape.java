package newPackage;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Cityscape extends JPanel {

	// Creates arrays to hold building and UFO objects
	Buildings[] city = new Buildings[7];
	UFO[] army = new UFO[4];

	// Creates car object
	Car c = new Car(0, 550);

	MovingUFO m = new MovingUFO(this, 20, 20, 0, 0, c);

	public Cityscape() {

		// Creates building and UFO objects
		city[0] = new Buildings(10, 400, 130, 210);
		city[1] = new Buildings(260, 460, 160, 150);
		city[2] = new Buildings(150, 340, 100, 270);
		city[3] = new Buildings(435, 220, 220, 390);
		city[4] = new Buildings(665, 310, 130, 300);
		city[5] = new Buildings(810, 400, 70, 210);
		city[6] = new Buildings(895, 280, 100, 330);

		army[0] = new UFO(this, 240, 100, 1, 1);
		army[1] = new UFO(this, 480, 20, 1, 1);
		army[2] = new UFO(this, 720, 100, 1, 1);
		army[3] = new UFO(this, 960, 20, 1, 1);

		// Adds KeyListener to obtain keyboard input
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// Passes the KeyEvent e to the UFO instance
				m.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// Passes the KeyEvent e to UFO ball instance
				m.keyPressed(e);
			}

		});

		setFocusable(true);
	}

	// Method paints the background as well as the buildings, UFO, and car objects
	public void paint(Graphics g) {

		Graphics2D pic = (Graphics2D) g;

		Color background = new Color(16, 20, 74);

		pic.setColor(background);

		pic.fillRect(0, 0, 1020, 640);

		for (Buildings b : city) {
			b.paint(pic);
		}

		for (UFO u : army) {
			u.paint(pic);
		}

		c.paint(pic);

		m.paint(pic);
	}

	// Method allows the UFO and car object to move and detect collisions among
	// objects
	public void move() throws InterruptedException {

		for (int i = 0; i < army.length; i++) {
			for (int j = 0; j < army.length; j++) {
				army[i].collision(army[j]);
				m.collision(army[j]);
			}
		}

		m.move();

		for (int i = 0; i < army.length; i++) {
			army[i].move();

		}

		c.move();

	}

	// Driver method
	public static void main(String[] args) throws InterruptedException {

		// Creates new frame and cityscape
		JFrame frame = new JFrame("Cityscape");

		Cityscape c = new Cityscape();

		// Adds the cityscape to the frame
		frame.add(c);

		// Sets frame attributes
		frame.setSize(1020, 640);

		frame.setVisible(true);

		frame.setResizable(false);

		// Allows us to close the frame once we're done with it
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Moves and repaints the objects with a very small delay in between each move
		// to give the human eye a sense of animation
		while (true) {

			c.move();
			c.repaint();
			Thread.sleep(10);
		}

	}

}
