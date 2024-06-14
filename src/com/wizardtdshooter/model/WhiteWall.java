package com.wizardtdshooter.model;

import com.wizardtdshooter.controller.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class WhiteWall extends GameObject {

	private BufferedImage whitew_image;
	public WhiteWall(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
		this.whitew_image = ss.grabImage(2, 4, 32, 32);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(whitew_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
