package com.wizardtdshooter.model;

import com.wizardtdshooter.controller.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Door extends GameObject {

	private BufferedImage door_image;
	public Door(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.door_image = ss.grabImage(3, 4, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(door_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
