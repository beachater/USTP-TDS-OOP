package com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wizardtdshooter.controller.SpriteSheet;

public class Wall extends GameObject {

	private BufferedImage wall_image;
	public Wall(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.wall_image = ss.grabImage(4, 4, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(wall_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
