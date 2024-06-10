package com.wizardtdshooter.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wizardtdshooter.controller.SpriteSheet;

public class Wall2 extends GameObject {

	private BufferedImage wall2_image;
	public Wall2(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.wall2_image = ss.grabImage(6, 4, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(wall2_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
