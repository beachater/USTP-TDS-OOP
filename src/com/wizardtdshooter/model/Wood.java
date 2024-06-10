package com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wizardtdshooter.controller.SpriteSheet;

public class Wood extends GameObject {

	private BufferedImage wood_image;
	public Wood(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.wood_image = ss.grabImage(5, 4, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(wood_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
