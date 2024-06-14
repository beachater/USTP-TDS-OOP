package com.wizardtdshooter.model;

import com.wizardtdshooter.controller.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Block extends GameObject {

	private BufferedImage block_image;
	public Block(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(block_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
