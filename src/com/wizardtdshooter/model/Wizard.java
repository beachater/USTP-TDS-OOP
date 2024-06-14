package com.wizardtdshooter.model;

import com.wizardtdshooter.controller.Handler;
import com.wizardtdshooter.controller.SpriteSheet;
import com.wizardtdshooter.view.Game;
import com.wizardtdshooter.view.Window;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class Wizard extends GameObject {

	private Handler handler;
	private BufferedImage wizard_image;
	private int speed = 3;
	private int width = 32;
	private int height = 48;
	public static boolean canPasss = false;

	public Wizard(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.wizard_image = ss.grabImage(1, 1, 32, 48);
	}

	@Override
	public void tick() {
		this.x += velX;
		this.y += velY;

		collision();
		movement();
		
		if(Window.hp <= 0) {
			JOptionPane.showMessageDialog(null, "Game Over");
			System.exit(0);
		}
	}

	private void collision() {

		

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Block) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}
			if (tempObject.getId() == ID.DarkerR) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}
			if (tempObject.getId() == ID.DarkR) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}
			if (tempObject.getId() == ID.WhiteWall) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}
			if (tempObject.getId() == ID.Dow) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}
			if (tempObject.getId() == ID.Roof) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}
		
			if (tempObject.getId() == ID.Water) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}
			if (tempObject.getId() == ID.Wood) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					this.x += this.velX * -1;
					this.y += this.velY * -1;
				}
			}


			if (tempObject.getId() == ID.Crate) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					Window.ammo += 30;
					handler.removeObject(tempObject);
				}
			}
			if (tempObject.getId() == ID.Enemy) {
				if (this.getBounds().intersects(tempObject.getBounds())) {
					Window.hp--;
				}
			}
			if (tempObject.getId() == ID.Door) {
                if (this.getBounds().intersects(tempObject.getBounds())) {
					
					if (canPasss == true){
						((Game) handler.getGame()).loadNextLevel();
					}
                    // Call the loadNextLevel method in the Game class
                }
            }
		}
	}

	private void movement() {
		if (handler.isUp()) {
			this.velY = -speed;
		} else if (!handler.isDown()) {
			this.velY = 0;
		}
		if (handler.isDown()) {
			this.velY = speed;
		} else if (!handler.isUp()) {
			this.velY = 0;
		}
		if (handler.isLeft()) {
			this.velX = -speed;
		} else if (!handler.isRight()) {
			this.velX = 0;
		}
		if (handler.isRight()) {
			this.velX = speed;
		} else if (!handler.isLeft()) {
			this.velX = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(this.wizard_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

    public static void setCanPasss(boolean gamecanPasss) {
        canPasss = gamecanPasss;
    }

}