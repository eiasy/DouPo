/****************************************************************************
Copyright (c) 2010-2013 cocos2d-x.org

http://www.cocos2d-x.org

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 ****************************************************************************/
package org.cocos2dx.lib;

import java.io.IOException;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

import org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chukong.cocosplay.client.CocosPlayClient;

public abstract class Cocos2dxActivity extends Activity implements Cocos2dxHelperListener {
    // ===========================================================
    // Constants
    // ===========================================================

    private final static String TAG = Cocos2dxActivity.class.getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================
    
    protected Cocos2dxGLSurfaceView mGLSurfaceView = null;
    private int[] mGLContextAttrs = null;
    private Cocos2dxHandler mHandler = null;   
    private static Cocos2dxActivity sContext = null;
    private Cocos2dxVideoHelper mVideoHelper = null;
    private Cocos2dxWebViewHelper mWebViewHelper = null;
    
		private WakeLock wakeLock = null;
		
		//37Íæ ÒôÁ¿
    public void requestFocus()
    {
    	this.mGLSurfaceView.requestFocus();
    }
    public class Cocos2dxEGLConfigChooser implements GLSurfaceView.EGLConfigChooser
    {
        protected int[] configAttribs;
        public Cocos2dxEGLConfigChooser(int redSize, int greenSize, int blueSize, int alphaSize, int depthSize, int stencilSize)
        {
            configAttribs = new int[] {redSize, greenSize, blueSize, alphaSize, depthSize, stencilSize};
        }
        public Cocos2dxEGLConfigChooser(int[] attribs)
        {
            configAttribs = attribs;
        }
        
        public EGLConfig selectConfig(EGL10 egl, EGLDisplay display, EGLConfig[] configs, int[] attribs)
        {
            for (EGLConfig config : configs) {
                int d = findConfigAttrib(egl, display, config,
                        EGL10.EGL_DEPTH_SIZE, 0);
                int s = findConfigAttrib(egl, display, config,
                        EGL10.EGL_STENCIL_SIZE, 0);
                if ((d >= attribs[4]) && (s >= attribs[5])) {
                    int r = findConfigAttrib(egl, display, config,
                            EGL10.EGL_RED_SIZE, 0);
                    int g = findConfigAttrib(egl, display, config,
                             EGL10.EGL_GREEN_SIZE, 0);
                    int b = findConfigAttrib(egl, display, config,
                              EGL10.EGL_BLUE_SIZE, 0);
                    int a = findConfigAttrib(egl, display, config,
                            EGL10.EGL_ALPHA_SIZE, 0);
                    if ((r >= attribs[0]) && (g >= attribs[1])
                            && (b >= attribs[2]) && (a >= attribs[3])) {
                        return config;
                    }
                }
            }
            return null;
        }

        private int findConfigAttrib(EGL10 egl, EGLDisplay display,
                EGLConfig config, int attribute, int defaultValue) {
            int[] value = new int[1];
            if (egl.eglGetConfigAttrib(display, config, attribute, value)) {
                return value[0];
            }
            return defaultValue;
        }
        
        @Override
        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) 
        {
            int[] numConfigs = new int[1];
            if(egl.eglGetConfigs(display, null, 0, numConfigs))
            {
                EGLConfig[] configs = new EGLConfig[numConfigs[0]];
                int[] EGLattribs = {
                        EGL10.EGL_RED_SIZE, configAttribs[0], 
                        EGL10.EGL_GREEN_SIZE, configAttribs[1],
                        EGL10.EGL_BLUE_SIZE, configAttribs[2],
                        EGL10.EGL_ALPHA_SIZE, configAttribs[3],
                        EGL10.EGL_DEPTH_SIZE, configAttribs[4],
                        EGL10.EGL_STENCIL_SIZE,configAttribs[5],
                        EGL10.EGL_RENDERABLE_TYPE, 4, //EGL_OPENGL_ES2_BIT
                        EGL10.EGL_NONE
                                    };
                int[] choosedConfigNum = new int[1];
                
                egl.eglChooseConfig(display, EGLattribs, configs, numConfigs[0], choosedConfigNum);
                if(choosedConfigNum[0]>0)
                {
                    return selectConfig(egl, display, configs, configAttribs);
                }
                else
                {
                    int[] defaultEGLattribs = {
                            EGL10.EGL_RED_SIZE, 5, 
                            EGL10.EGL_GREEN_SIZE, 6,
                            EGL10.EGL_BLUE_SIZE, 5,
                            EGL10.EGL_ALPHA_SIZE, 0,
                            EGL10.EGL_DEPTH_SIZE, 0,
                            EGL10.EGL_STENCIL_SIZE,0,
                            EGL10.EGL_RENDERABLE_TYPE, 4, //EGL_OPENGL_ES2_BIT
                            EGL10.EGL_NONE
                                        };
                    int[] defaultEGLattribsAlpha = {
                            EGL10.EGL_RED_SIZE, 4, 
                            EGL10.EGL_GREEN_SIZE, 4,
                            EGL10.EGL_BLUE_SIZE, 4,
                            EGL10.EGL_ALPHA_SIZE, 4,
                            EGL10.EGL_DEPTH_SIZE, 0,
                            EGL10.EGL_STENCIL_SIZE,0,
                            EGL10.EGL_RENDERABLE_TYPE, 4, //EGL_OPENGL_ES2_BIT
                            EGL10.EGL_NONE
                                        };
                    int[] attribs = null;
                    //choose one can use
                    if(this.configAttribs[3] == 0)
                    {
                        egl.eglChooseConfig(display, defaultEGLattribs, configs, numConfigs[0], choosedConfigNum);
                        attribs = new int[]{5,6,5,0,0,0};
                    }
                    else
                    {
                        egl.eglChooseConfig(display, defaultEGLattribsAlpha, configs, numConfigs[0], choosedConfigNum);
                        attribs = new int[]{4,4,4,4,0,0};
                    }
                    if(choosedConfigNum[0] > 0)
                    {
                        return selectConfig(egl, display, configs, attribs);
                    }
                    else
                    {
                        Log.e(DEVICE_POLICY_SERVICE, "Can not select an EGLConfig for rendering.");
                        return null;
                    }
                }
            }
            Log.e(DEVICE_POLICY_SERVICE, "Can not select an EGLConfig for rendering.");
            return null;
        }

    }
    
    public static Context getContext() {
        return sContext;
    }
    
    public void setKeepScreenOn(boolean value) {
        final boolean newValue = value;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mGLSurfaceView.setKeepScreenOn(newValue);
            }
        });
    }
    
    protected void onLoadNativeLibraries() {
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            String libName = bundle.getString("android.app.lib_name");
            System.loadLibrary(libName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // ===========================================================
    // Constructors
    // ===========================================================
    
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CocosPlayClient.init(this, false);

        onLoadNativeLibraries();

        sContext = this;
        this.mHandler = new Cocos2dxHandler(this);
        
        Cocos2dxHelper.init(this);
        
        this.mGLContextAttrs = getGLContextAttrs();
        this.init();

        if (mVideoHelper == null) {
            mVideoHelper = new Cocos2dxVideoHelper(this, mFrameLayout);
        }
        
        if(mWebViewHelper == null){
            mWebViewHelper = new Cocos2dxWebViewHelper(mFrameLayout);
        }
    }

    //native method,call GLViewImpl::getGLContextAttrs() to get the OpenGL ES context attributions
    private static native int[] getGLContextAttrs();
    
    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    
    private int videoDuration = -1;

    @Override
    protected void onResume() {
        super.onResume();
        wakeLock = ((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.FULL_WAKE_LOCK, "DouPo");
        wakeLock.acquire();
        Cocos2dxHelper.onResume();
//        this.mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wakeLock != null) {        	
        	wakeLock.release();
        }
        Cocos2dxHelper.onPause();
//        this.mGLSurfaceView.onPause();
    }
    
    protected Cocos2dxVideoView getCurVideoView() {
        for (int i = 0; i < mFrameLayout.getChildCount(); ++i) {
        	View view = mFrameLayout.getChildAt(i);
        	if (view instanceof Cocos2dxVideoView) {
        		return (Cocos2dxVideoView) view;
        	}
        }
        
        return null;
    }
    
	public void showEixtGameDialog() {
		Message msg = new Message();
		msg.what = Cocos2dxHandler.HANDLER_EXIT_GAME_DIALOG;
		this.mHandler.sendMessage(msg);
	}
	
	public void showWebView(String url) {
		Message msg = new Message();
		msg.what = Cocos2dxHandler.HANDLER_SHOW_WEBVIEW;
		msg.obj = url;
		this.mHandler.sendMessage(msg);
	}
	
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showDialog(final String pTitle, final String pMessage) {
        Message msg = new Message();
        msg.what = Cocos2dxHandler.HANDLER_SHOW_DIALOG;
        msg.obj = new Cocos2dxHandler.DialogMessage(pTitle, pMessage);
        this.mHandler.sendMessage(msg);
    }

    @Override
    public void showEditTextDialog(final String pTitle, final String pContent, final int pInputMode, final int pInputFlag, final int pReturnType, final int pMaxLength) { 
        Message msg = new Message();
        msg.what = Cocos2dxHandler.HANDLER_SHOW_EDITBOX_DIALOG;
        msg.obj = new Cocos2dxHandler.EditBoxMessage(pTitle, pContent, pInputMode, pInputFlag, pReturnType, pMaxLength);
        this.mHandler.sendMessage(msg);
    }
    
    @Override
    public void runOnGLThread(final Runnable pRunnable) {
        this.mGLSurfaceView.queueEvent(pRunnable);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        for (OnActivityResultListener listener : Cocos2dxHelper.getOnActivityResultListeners()) {
            listener.onActivityResult(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    protected FrameLayout mFrameLayout = null;
    // ===========================================================
    // Methods
    // ===========================================================
    public void init() {
        
        // FrameLayout
        ViewGroup.LayoutParams framelayout_params =
            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                       ViewGroup.LayoutParams.MATCH_PARENT);
        mFrameLayout = new FrameLayout(this);
        mFrameLayout.setLayoutParams(framelayout_params);

        // Cocos2dxEditText layout
        ViewGroup.LayoutParams edittext_layout_params =
            new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                       ViewGroup.LayoutParams.WRAP_CONTENT);
        Cocos2dxEditText edittext = new Cocos2dxEditText(this);
        edittext.setLayoutParams(edittext_layout_params);

        // ...add to FrameLayout
        mFrameLayout.addView(edittext);

        // Cocos2dxGLSurfaceView
        this.mGLSurfaceView = this.onCreateView();

        // ...add to FrameLayout
        mFrameLayout.addView(this.mGLSurfaceView);

        showFitImageView();
        
        // Switch to supported OpenGL (ARGB888) mode on emulator
        if (isAndroidEmulator())
           this.mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);

        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        this.mGLSurfaceView.setCocos2dxEditText(edittext);

        // Set framelayout as the content view
        setContentView(mFrameLayout);
    }
    
    public Cocos2dxGLSurfaceView onCreateView() {
        Cocos2dxGLSurfaceView glSurfaceView = new Cocos2dxGLSurfaceView(this);
        //this line is need on some device if we specify an alpha bits
        if(this.mGLContextAttrs[3] > 0) glSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        Cocos2dxEGLConfigChooser chooser = new Cocos2dxEGLConfigChooser(this.mGLContextAttrs);
        glSurfaceView.setEGLConfigChooser(chooser);

        return glSurfaceView;
    }

   private final static boolean isAndroidEmulator() {
      String model = Build.MODEL;
      Log.d(TAG, "model=" + model);
      String product = Build.PRODUCT;
      Log.d(TAG, "product=" + product);
      boolean isEmulator = false;
      if (product != null) {
         isEmulator = product.equals("sdk") || product.contains("_sdk") || product.contains("sdk_");
      }
      Log.d(TAG, "isEmulator=" + isEmulator);
      return isEmulator;
   }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
   
	public void doShowWebView(String url) {
		SystemNotice.getInstance(this).initWebView(this);
		SystemNotice.getInstance(this).doShowWebView(mFrameLayout, url);
	}
	
	private void showFitImageView() {
		SystemNotice notice = SystemNotice.getInstance(this);
		
		float scaleX = notice.getScreenWidth() / (float) notice.getDesignResolutionWidth();
		float scaleY = notice.getScreenHeight() / (float) notice.getDesignResolutionHeight();
		
		if (scaleX < scaleY) {
			int fitImgHeight = (int) ((notice.getScreenHeight() - scaleX * notice
					.getDesignResolutionHeight()) / 2 + 0.5f);
			if (fitImgHeight > 0) {
				try {
					LayoutInflater layoutInflater = LayoutInflater.from(this);
					RelativeLayout relativeLayout = (RelativeLayout) layoutInflater
							.inflate(R.layout.screen_fit, null);

					ImageView topImageView = (ImageView) relativeLayout
							.findViewById(R.id.topImageView);
					topImageView.getLayoutParams().height = fitImgHeight;
					topImageView.setImageDrawable(Drawable
							.createFromStream(
									this.getAssets().open(
											"image/screen_fit.png"), null));

					ImageView bottomImageView = (ImageView) relativeLayout
							.findViewById(R.id.bottomImageView);
					bottomImageView.getLayoutParams().height = fitImgHeight;

					Bitmap img = BitmapFactory.decodeStream(this.getAssets()
							.open("image/screen_fit.png"));
					Matrix matrix = new Matrix();
					matrix.postScale(1, -1);
					int width = img.getWidth();
					int height = img.getHeight();
					Bitmap img_a = Bitmap.createBitmap(img, 0, 0, width,
							height, matrix, true);

					bottomImageView.setImageBitmap(img_a);

					mFrameLayout.addView(relativeLayout);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (scaleX > scaleY) {
			int fitImgWidth = (int) ((notice.getScreenWidth() - scaleY * notice
					.getDesignResolutionWidth()) / 2 + 0.5f);
			if (fitImgWidth > 0) {
				try {
					LayoutInflater layoutInflater = LayoutInflater.from(this);
					RelativeLayout relativeLayout = (RelativeLayout) layoutInflater
							.inflate(R.layout.screen_fit_h, null);

					ImageView topImageView = (ImageView) relativeLayout
							.findViewById(R.id.leftImageView);
					topImageView.getLayoutParams().width = fitImgWidth;
					topImageView.setImageDrawable(Drawable.createFromStream(
							this.getAssets().open("image/screen_fit_h.png"),
							null));

					ImageView bottomImageView = (ImageView) relativeLayout
							.findViewById(R.id.rightImageView);
					bottomImageView.getLayoutParams().width = fitImgWidth;

					Bitmap img = BitmapFactory.decodeStream(this.getAssets()
							.open("image/screen_fit_h.png"));
					Matrix matrix = new Matrix();
					matrix.postScale(-1, 1);
					int width = img.getWidth();
					int height = img.getHeight();
					Bitmap img_a = Bitmap.createBitmap(img, 0, 0, width,
							height, matrix, true);

					bottomImageView.setImageBitmap(img_a);

					mFrameLayout.addView(relativeLayout);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
