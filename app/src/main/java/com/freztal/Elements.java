package com.freztal;

import android.graphics.Bitmap;

public class Elements {
	int alpha;
	boolean alphaTag;
	Bitmap b;
	int rotate;
	boolean rotateTag;
	float scale;
	boolean scaleTag;
	int x;
	int y;

	public Elements(Bitmap bitmap, int x, int y, float scale, int rotate, int alpha) {
		this.b = bitmap;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.rotate = rotate;
		this.alpha = alpha;
		this.alphaTag = false;
		this.scaleTag = false;
	}
}
