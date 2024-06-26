package com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wizardtdshooter.controller.SpriteSheet;

public class DarkerR extends GameObject {

	private BufferedImage darkerR_image;
	public DarkerR(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.darkerR_image = ss.grabImage(2, 3, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(darkerR_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
