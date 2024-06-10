package com.wizardtdshooter.model;

import com.wizardtdshooter.controller.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Water extends GameObject {

	private BufferedImage water_image;
	public Water(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.water_image = ss.grabImage(5, 2, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(water_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
