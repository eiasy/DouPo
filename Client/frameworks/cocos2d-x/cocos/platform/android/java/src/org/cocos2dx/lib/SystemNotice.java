package org.cocos2dx.lib;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class SystemNotice {
	
	private static final int DesignResolutionWidth = 640;
	private static final int DesignResolutionHeight = 1136;
	private float screenWidth, screenHeight;
	private float scaleX, scaleY;
	
	private WebView webView = null;
	private ImageView webViewBg = null;
	private ImageButton webViewBtn = null;
	private ViewGroup webViewGroup = null;
	
	private Bitmap bmWebViewBg = null;
	private Bitmap bmWebViewBtn = null;
	
	private FrameLayout frameLayout = null;
	
	private static SystemNotice instance = null;
	
	public static SystemNotice getInstance(Activity activity) {
		if(instance == null) {
			instance = new SystemNotice(activity);
		}
		return instance;
	}
	
	private SystemNotice(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		if (dm.heightPixels < dm.widthPixels) {
			screenWidth = dm.heightPixels;
			screenHeight = dm.widthPixels;
		} else {
			screenWidth = dm.widthPixels;
			screenHeight = dm.heightPixels;
		}
		scaleX = screenWidth / DesignResolutionWidth;
		scaleY = screenHeight / DesignResolutionHeight;
		dm = null;
	}
	
	public int getScreenWidth() {
		return (int) screenWidth;
	}
	
	public int getScreenHeight() {
		return (int) screenHeight;
	}
	
	public int getDesignResolutionWidth() {
		return DesignResolutionWidth;
	}
	
	public int getDesignResolutionHeight() {
		return DesignResolutionHeight;
	}
	
	public float getScaleX() {
		return scaleX;
	}
	
	public float getScaleY() {
		return scaleY;
	}
	
	public void initWebView(Context context) {
		webView = new WebView(context);
		WebSettings settings = webView.getSettings();
		settings.setSupportZoom(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setJavaScriptEnabled(true);
		
		webView.setWebChromeClient(new WebChromeClient());
		webView.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});// .希望点击链接继续在当前browser中响应，必须覆盖 WebViewClient对象�?

		webView.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
						webView.goBack();
						return true;
					}
				}
				return true;
			}
		});
		
		try {
			webViewBg = new ImageView(context);
			bmWebViewBg = BitmapFactory.decodeStream(context.getAssets().open("image/game_notice_bg.png"));
			webViewBg.setImageBitmap(bmWebViewBg);
			webViewBg.setScaleType(ScaleType.FIT_XY);
			webViewBg.setLayoutParams(new LayoutParams((int) (bmWebViewBg.getWidth() * scaleX), (int) (bmWebViewBg.getHeight() * scaleY)));
			
			webViewBtn = new ImageButton(context);
			bmWebViewBtn = BitmapFactory.decodeStream(context.getAssets().open("image/game_notice_btn.png"));
			webViewBtn.setImageBitmap(bmWebViewBtn);
			webViewBtn.getBackground().setAlpha(0);
			webViewBtn.setScaleType(ScaleType.FIT_XY);
			webViewBtn.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			webViewBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					doHideWebView();
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		webViewGroup = new ViewGroup(context) {

			@Override
			protected void onLayout(boolean changed, int l, int t, int r, int b) {
				// TODO Auto-generated method stub
				int count = this.getChildCount();
				for (int i = 0; i < count; i++) {
					View view = this.getChildAt(i);
					if(view.equals(webView)) {
						int webView_W = bmWebViewBg.getWidth() - 40;
						int webView_H = bmWebViewBg.getHeight() - bmWebViewBtn.getHeight() * 2 - 10;
						int w = (int) (webView_W * scaleX);
						int h = (int) (webView_H * scaleY);
						int left = (int) ((DesignResolutionWidth - webView_W) / 2 * scaleX);
						int webViewY = (DesignResolutionHeight - bmWebViewBg.getHeight()) / 2 + bmWebViewBg.getHeight() - bmWebViewBtn.getHeight() - 15 - webView_H;
						int top = (int) (webViewY * scaleY);
						view.layout(left, top, left + w, top + h);
					} else if (view.equals(webViewBtn)) {
						int w = (int) (bmWebViewBtn.getWidth() * scaleX);
						int h = (int) (bmWebViewBtn.getHeight() * scaleY);
						int left = (int) ((DesignResolutionWidth - bmWebViewBtn.getWidth()) / 2 * scaleX);
						int webViewBtnY = (DesignResolutionHeight - bmWebViewBg.getHeight()) / 2 + bmWebViewBg.getHeight() - bmWebViewBtn.getHeight() - 10;
						int top = (int) (webViewBtnY * scaleY);
						view.layout(left, top, left + w, top + h);
					} else if (view.equals(webViewBg)) {
						int w = (int) (bmWebViewBg.getWidth() * scaleX);
						int h = (int) (bmWebViewBg.getHeight() * scaleY);
						int left = (int) ((DesignResolutionWidth - bmWebViewBg.getWidth()) / 2 * scaleX);
						int top = (int) ((DesignResolutionHeight - bmWebViewBg.getHeight()) / 2 * scaleY);
						view.layout(left, top, left + w, top + h);
					}
				}
			}
			
		};
		
		webViewGroup.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		webViewGroup.addView(webViewBg);
		webViewGroup.addView(webView);
		webViewGroup.addView(webViewBtn);
	}
	
	public void doShowWebView(FrameLayout layout, String url) {
		frameLayout = layout;
		if(webViewGroup != null && webView != null) {
			webView.loadUrl(url);
			frameLayout.addView(webViewGroup);
		}
	}
	
	private void doHideWebView() {
		if(frameLayout != null && webViewGroup != null && webView != null) {
			webView.clearView();
			frameLayout.removeView(webViewGroup);
			webView = null;
			webViewGroup = null;
			frameLayout = null;
		}
	}

}
