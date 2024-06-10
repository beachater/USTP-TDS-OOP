package com.wizardtdshooter.model;

import com.wizardtdshooter.controller.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ground2 extends GameObject {

	private BufferedImage ground2_image;
	public Ground2(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.ground2_image = ss.grabImage(4, 2, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(ground2_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
