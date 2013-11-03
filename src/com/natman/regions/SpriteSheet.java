package com.natman.regions;

import java.util.HashMap;
import java.util.Map;

/**
 * A SpriteSheet storing the path of its texture and a map of texture regions.
 * @author Natman64
 * @created  Nov 2, 2013
 */
public class SpriteSheet {
	
	public String texturePath;
	public Map<String, Rectangle> regions = new HashMap<String, Rectangle>();
	
	/**
	 * Constructor.
	 * @param texturePath File path of the sprite sheet texture
	 * (relative to the game's assets directory).
	 */
	public SpriteSheet(String texturePath) {
		this.texturePath = texturePath;
	}
	
	/**
	 * Adds a texture region to the sprite sheet.
	 * @param key The key of the texture region.
	 * @param region Rectangle representing the texture region.
	 */
	public void addRegion(String key, Rectangle region) {
		regions.put(key, region);
	}
	
}
