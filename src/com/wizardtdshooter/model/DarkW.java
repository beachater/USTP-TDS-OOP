package com.wizardtdshooter.model;

import com.wizardtdshooter.controller.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DarkW extends GameObject {

	private BufferedImage darkW_image;
	public DarkW(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.darkW_image = ss.grabImage(1, 3, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(darkW_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
