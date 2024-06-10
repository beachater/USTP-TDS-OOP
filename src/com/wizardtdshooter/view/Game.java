package com.wizardtdshooter.view;

import com.wizardtdshooter.controller.BufferedImageLoader;
import com.wizardtdshooter.controller.Handler;
import com.wizardtdshooter.controller.KeyInput;
import com.wizardtdshooter.controller.MouseInput;
import com.wizardtdshooter.controller.SpriteSheet;
import com.wizardtdshooter.model.Block;
import com.wizardtdshooter.model.Crate;
import com.wizardtdshooter.model.DarkR;
import com.wizardtdshooter.model.DarkerR;
import com.wizardtdshooter.model.Door;
import com.wizardtdshooter.model.Dow;
import com.wizardtdshooter.model.Enemy;
import com.wizardtdshooter.model.GameObject;
import com.wizardtdshooter.model.Grass;
import com.wizardtdshooter.model.Ground2;
import com.wizardtdshooter.model.ID;
import com.wizardtdshooter.model.Roof;
import com.wizardtdshooter.model.Wall;
import com.wizardtdshooter.model.Wall2;
import com.wizardtdshooter.model.Water;
import com.wizardtdshooter.model.Wizard;
import com.wizardtdshooter.model.Wood;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Timer timer;
	private Handler handler;
	private SpriteSheet ss;
	private BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	private BufferedImage floor = null;
	private Camera camera;
	public static int currentLevel = 0;
	private String[] levels = {"/bottom-left.png", "/top_left.png", "/top_right.png", "/bottom-right.png"};


	public Game() {
		////////////////////////

		handler = new Handler(this);
		camera = new Camera();

		BufferedImageLoader loader = new BufferedImageLoader();
		this.sprite_sheet = loader.loadImage("/wizard_images_2.png");
		this.ss = new SpriteSheet(sprite_sheet);
		this.floor = ss.grabImage(4, 2, 32, 32);
		loadLevel(levels[currentLevel]);
		/////////////////////////
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler, camera, ss));
		this.start();
	}	
	public void loadNextLevel() {
        if (currentLevel < levels.length - 1) {
            currentLevel++;
            loadLevel(levels[currentLevel]);
        }
    }	

	private void start() {
		isRunning = true;
		timer = new Timer(10, this);
		timer.start();
	}

	private void stop() {
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isRunning) {
			tick();
			repaint();
		} else {
			stop();
		}
	}

	public void tick() {
    int enemy = 0;
    for (int i = 0; i < handler.object.size(); i++) {
        GameObject obj = handler.object.get(i);
        if (obj.getId() == ID.Player) {
            camera.tick(obj);
            System.out.println("Updating camera for player at position: " + obj.getX() + ", " + obj.getY());
        }
        if (obj.getId() == ID.Enemy) {
            enemy++;
            System.out.println("Enemy found at position: " + obj.getX() + ", " + obj.getY());
        }
    }
    handler.tick();
    if (enemy <= 0) {
        this.isRunning = false;
        System.out.println("No enemies left, stopping game.");
    }
}

	public void paint(Graphics g) {
		osSupport();
		Graphics2D g2 = (Graphics2D) g;
	
		g2.translate(-camera.getX(), -camera.getY());
	
		// Draw the floor
		for (int xx = 0; xx < 30 * 72; xx += 32) {
			for (int yy = 0; yy < 30 * 72; yy += 32) {
				g.drawImage(this.floor, xx, yy, null);
			}
		}
	
		// Draw blocks first
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() != ID.Player || handler.object.get(i).getId() != ID.Enemy || handler.object.get(i).getId() != ID.Crate|| handler.object.get(i).getId() != ID.Door) {
				handler.object.get(i).render(g);
			}
		}
	
		// Draw other objects (player, enemies, crates, etc.)
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player || handler.object.get(i).getId() == ID.Enemy || handler.object.get(i).getId() == ID.Crate || handler.object.get(i).getId() == ID.Door ){
				handler.object.get(i).render(g);
			}
		}
	
		g2.translate(camera.getX(), camera.getY());
	
		// HUD rendering
		g.setColor(Color.red.darker());
		g.fillRect(20, 20, 200, 25);
		g.setColor(Color.green.darker());
		g.fillRect(20, 20, Window.hp * 4, 25);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", 0, 14));
	
		int ammo = Window.ammo % 16;
		int onHand = Window.ammo / 16;
		g.drawString("" + onHand, 105, 60);
		g.setFont(new Font("Arial", 0, 16));
		g.drawString("Ammo:  " + ammo, 20, 60);
	
		g.dispose();
	}
	

	// Loading the level
	private void loadLevel(String path) {
		BufferedImageLoader loader = new BufferedImageLoader();
		BufferedImage image = loader.loadImage(path);
		
		int w = image.getWidth();
		int h = image.getHeight();
	
		handler.clearObjects();  // Clear existing objects before loading new level
	
		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
	
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 255 && blue == 0 && green == 0) {
					handler.addObject(new Block(xx * 32, yy * 32, ID.Block, ss));
				}
				if (green == 255 && blue != 255 && red != 255) {
					handler.addObject(new Enemy(xx * 32, yy * 32, ID.Enemy, handler, ss));
				}
				if (blue == 255 && green != 255 && red != 255) {
					handler.addObject(new Wizard(xx * 32, yy * 32, ID.Player, handler, ss));
				}
				if (blue == 255 && green == 255 && red != 255) {
					handler.addObject(new Crate(xx * 32, yy * 32, ID.Crate, ss));
				}
				if (blue == 100 && green == 0 && red == 100) {
					handler.addObject(new Door(xx * 32, yy * 32, ID.Door, ss));
				}
				if (red == 255 && green == 255 && blue == 0) {
					handler.addObject(new Grass(xx * 32, yy * 32, ID.Grass, ss));
				}
				if (red == 50 && green == 50 && blue == 50) {
					handler.addObject(new Ground2(xx * 32, yy * 32, ID.Ground2, ss));
				}
				if (red == 255 && green == 0 && blue == 255) {
					handler.addObject(new Water(xx * 32, yy * 32, ID.Water, ss));
				}
				if (red == 0 && green == 255 && blue == 0) {
					handler.addObject(new Ground2(xx * 32, yy * 32, ID.Ground2, ss));
				}
				if (red == 0 && green == 0 && blue == 0) {
					handler.addObject(new Wall2(xx * 32, yy * 32, ID.Wall2, ss));
				}
				if (red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Wall(xx * 32, yy * 32, ID.Wall, ss));
				}
				if (red == 255 && green == 100 && blue == 100) {
					handler.addObject(new Wood(xx * 32, yy * 32, ID.Wood, ss));
				}
				if (red == 0 && green == 255 && blue == 255) {
					handler.addObject(new Dow(xx * 32, yy * 32, ID.Dow, ss));
				}
				if (red == 0 && green == 150 && blue == 250) {
					handler.addObject(new Roof(xx * 32, yy * 32, ID.Roof, ss));
				}
				if (red == 50 && green == 50 && blue == 250) {
					handler.addObject(new DarkR(xx * 32, yy * 32, ID.DarkR, ss));
				}
				if (red == 40 && green == 40 && blue == 120) {
					handler.addObject(new DarkerR(xx * 32, yy * 32, ID.DarkerR, ss));
				}
				
			}
		}
	}

	private void osSupport() {
		if (Window.operatingSystem.equals("Linux")) {
			Toolkit.getDefaultToolkit().sync();
		}
	}
}
