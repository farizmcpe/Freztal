package com.freztal;

import java.util.ArrayList;
import java.util.Random;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class MyWallpaper extends WallpaperService {
	Bitmap bg;
	int height;
	int width;
	private final Handler mHandler = new Handler();
	private boolean mVisible;
	BitmapFactory.Options options;
	Random randomGenerator;
	public float scale;
	float scaleSpeed;

	public void onCreate() {
		super.onCreate();
		this.scale = getResources().getDisplayMetrics().density;
		this.randomGenerator = new Random();
		this.options = new BitmapFactory.Options();
		this.options.inPreferredConfig = Bitmap.Config.ARGB_4444;
		this.options.inPurgeable = true;
		this.options.inInputShareable = true;
	}

	@Override
	public WallpaperService.Engine onCreateEngine() {
		return new CubeEngine();
	}

	public void onDestroy() {
		super.onDestroy();
	}

	class CubeEngine extends WallpaperService.Engine {
		Canvas c;
		ArrayList<Elements> elements = new ArrayList<Elements>();
		public float eventX;
		public float eventY;
		SurfaceHolder holder;
		Matrix m;
		private final Runnable mDrawer;
		private final Paint mPaint = new Paint();
		private final Paint mPaintBG = new Paint();
		private float mTouchX = -1.0F;
		private float mTouchY = -1.0F;
		boolean tapFlag = false;

		CubeEngine() {
			this.mDrawer = new Runnable() {
				public void run() {
					MyWallpaper.CubeEngine.this.drawFrame();
				}
			};
			this.m = new Matrix();
			this.mPaint.setAntiAlias(true);
			this.mPaint.setFilterBitmap(true);
			this.mPaint.setDither(true);
			this.mPaintBG.setAntiAlias(true);
			this.mPaintBG.setFilterBitmap(true);
			this.mPaintBG.setDither(true);
			MyWallpaper.this.scaleSpeed = 0.02f;
			MyWallpaper.this.bg = BitmapFactory.decodeResource(
				MyWallpaper.this.getResources(), R.drawable.background);

			// Initialize all elements
			int[] drawables = {R.drawable.e1, R.drawable.e2, R.drawable.e3, R.drawable.e4, R.drawable.e5, R.drawable.e6, R.drawable.e7, R.drawable.e8, R.drawable.e9, R.drawable.e1, R.drawable.e2, R.drawable.e8, R.drawable.e9, R.drawable.e3, R.drawable.e4};
			for (int i = 0; i < drawables.length; ++i) {
				Elements e = new Elements(
					BitmapFactory.decodeResource(
						MyWallpaper.this.getResources(), drawables[i],
						MyWallpaper.this.options),
					(int) (randomGenerator.nextInt(400) * MyWallpaper.this.scale),
					(int) (randomGenerator.nextInt(600) * MyWallpaper.this.scale),
					randomGenerator.nextInt(100) / 100.0f, randomGenerator.nextInt(360), randomGenerator.nextInt(255));
				this.elements.add(e);
			}

			drawFrame();
		}

		void drawAll(Canvas canvas) {
			canvas.save();
			canvas.translate(0, 0);
			canvas.drawBitmap(MyWallpaper.this.bg, new Rect(0, 0,
															MyWallpaper.this.bg.getWidth(),
															MyWallpaper.this.bg.getHeight()), 
							  new Rect(0, 0, canvas.getWidth(), canvas.getHeight()),
							  this.mPaintBG);
			for (int i = 0; i < this.elements.size(); i++) {
				Elements e = this.elements.get(i);
				Matrix matrix = new Matrix();
				matrix.postScale(e.scale, e.scale);
				matrix.postRotate(e.rotate);
				matrix.postTranslate(e.x, e.y);
				this.mPaint.setAlpha(e.alpha);
				canvas.drawBitmap(e.b, matrix, this.mPaint);
			}

			canvas.restore();
		}

		void drawFrame() {
			final SurfaceHolder holder = getSurfaceHolder();

			try {
				c = holder.lockCanvas();
				if (c != null) {
					drawAll(c);
					drawTouchPoint(c);
				}

				mHandler.removeCallbacks(mDrawer);
				if (mVisible) {
					update(c);
					mHandler.postDelayed(mDrawer, 40L);
				}
			} finally {
				// Release the canvas
				if (c != null) {
					holder.unlockCanvasAndPost(c);
				}
			}
		}

		void drawTouchPoint(Canvas canvas) {
			if ((this.mTouchX >= 0.0F) && (this.mTouchY >= 0.0F) && (!this.tapFlag)) {
				this.tapFlag = true;
				int i = MyWallpaper.this.randomGenerator.nextInt(-1
																 + this.elements.size());
				if (i < this.elements.size()) {
					Elements e = this.elements.get(i);
					e.x = (int) this.mTouchX;
					e.y = (int) this.mTouchY;
					e.alpha = 255;
					e.scale = 1.0F;
					e.rotate = 0;
					e.alphaTag = false;
					e.scaleTag = false;
				}
			}
		}

		public void onCreate(SurfaceHolder holder) {
			super.onCreate(holder);
			setTouchEventsEnabled(true);
		}

		public void onDestroy() {
			super.onDestroy();
			MyWallpaper.this.mHandler.removeCallbacks(this.mDrawer);
			for (int i = 0; i < this.elements.size(); i++) {
				Elements e = this.elements.get(i);
				if (e.b != null) {
					e.b.recycle();
					e.b = null;
				}
			}
		}

		public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep, float yOffsetStep, int xPixelOffset, int yPixelOffset) {
			if (!MyWallpaper.this.mVisible) {
				MyWallpaper.this.mVisible = true;
				drawFrame();
			}
		}

		public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			super.onSurfaceChanged(holder, format, width, height);
			MyWallpaper.this.height = height;
			MyWallpaper.this.width = width;
			getSurfaceHolder();
			this.c = null;
		}

		public void onSurfaceCreated(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
		}

		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			MyWallpaper.this.mVisible = false;
			MyWallpaper.this.mHandler.removeCallbacks(this.mDrawer);
		}

		public void onTouchEvent(MotionEvent motionevent) {
			if (motionevent.getAction() == 0) {
				mTouchX = motionevent.getX();
				mTouchY = motionevent.getY();
				if (!mVisible) {
					mVisible = true;
					drawFrame();
				}
			} else {
				tapFlag = false;
				mTouchX = -1F;
				mTouchY = -1F;
			}
			super.onTouchEvent(motionevent);
		}

		public void onVisibilityChanged(boolean visible) {
			MyWallpaper.this.mVisible = visible;
			if (visible) {
				drawFrame();
			} else {
				MyWallpaper.this.mHandler.removeCallbacks(this.mDrawer);
			}
		}

		public void update(Canvas canvas) {
			for (int i = 0; i < elements.size(); ++i) {
				Elements e = elements.get(i);

				if (!e.scaleTag) {
					e.scale = e.scale + scaleSpeed;
				} else {
					e.scale = e.scale - scaleSpeed;
				}

				if (e.scale >= 0.99) {
					e.scaleTag = true;
				} else if (e.scale <= 0.01) {
					e.rotate = randomGenerator.nextInt(360);
					e.scaleTag = false;
					e.x = randomGenerator.nextInt(width);
					e.y = randomGenerator.nextInt(height);
				}

				if (!e.alphaTag) {
					e.alpha = e.alpha + 1;
				} else {
					e.alpha = e.alpha - 1;
				}

				if (e.alpha >= 250) {
					e.alphaTag = true;
				} else if (e.alpha <= 50) {
					e.alphaTag = false;
				}
			} 
		}

	}

}
