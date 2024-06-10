package com.wizardtdshooter.model;

import com.wizardtdshooter.controller.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Grass extends GameObject {

	private BufferedImage grass_image;
	public Grass(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.grass_image = ss.grabImage(6, 3, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(grass_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
