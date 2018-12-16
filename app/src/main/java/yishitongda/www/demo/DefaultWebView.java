package yishitongda.www.demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import yishitongda.www.demo.utils.MyLog;

/**
 * Author:  kuencheung
 * Date:    2018/7/6
 * Des:     自定义webview
 */
public class DefaultWebView extends WebView {

    private static final String TAG = "DefaultWebView";
    private OnScrollChangedCallback mOnScrollChangedCallback;

    public DefaultWebView(Context context) {
        super(context);
        initView(context);
    }

    public DefaultWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DefaultWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);   //禁止硬件加速
        initView(context);
    }

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
    private void initView(Context context) {
        //默认设置,如果其他，请重新特定方法，覆盖原有设置
        requestFocusFromTouch();                        //默认支持获取手势焦点
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);                            //默认允许JS
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);    //提高渲染的优先级
        settings.setUseWideViewPort(true);                              //将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true);                         //缩放至屏幕的大小
        settings.setSupportZoom(false);                                 //默认禁止缩放
        settings.setBuiltInZoomControls(false);                         //设置内置的缩放控件
        settings.setDisplayZoomControls(false);                         //隐藏原生的缩放控件
        settings.setDefaultTextEncodingName("utf-8");                   //设置编码格式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//默认运行加载http与https混合内容
        }

        settings.setDomStorageEnabled(true);
        settings.setAppCacheMaxSize(1024 * 1024 * 8);   //设置缓冲大小，这里设的是8M
        String appCacheDir = context.getCacheDir().getAbsolutePath();
        settings.setAppCachePath(appCacheDir);          //设置APP缓存路径
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);              //默认开启缓存

        settings.setDomStorageEnabled(true);            // 开启浏览器缓存机制
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式，（不使用缓存、仅使用缓存等等）
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);//不使用网络，只加载缓存
        }
        //设置默认的WebViewClient
        setWebViewClient(new DefaultWebViewClient(context));
        //设置默认的WebChromeClient
        setWebChromeClient(new DefaultWebChromeClient(context));
        //设置默认的JS Object
//        addJavascriptInterface(new DefaultJsObject(context), StaticConfig.JS_OBJECT);
    }

    //激活WebView为活跃状态，能正常执行网页的响应
    @Override
    public void onResume() {
        MyLog.d(TAG, "DefaultWebView onResume");
        super.onResume();
    }

    //当页面被失去焦点被切换到后台不可见状态，需要执行onPause动过,onPause动作通知内核暂停所有的动作，
    //比如DOM的解析、plugin的执行、JavaScript执行
    @Override
    public void onPause() {
        MyLog.d(TAG, "DefaultWebView onPause ");
        super.onPause();
    }

    //销毁，关闭了Activity时，音乐或视频，还在播放。就必须销毁
    @Override
    public void destroy() {
        MyLog.d(TAG, "DefaultWebView destroy");
        super.destroy();
    }

    //当应用程序被切换到后台我们使用了webview，这个方法不仅仅针对当前的webview而是全局的全应用程序的webview，
    //它会暂停所有webview的layout，parsing，javascripttimer。降低CPU功耗
    @Override
    public void pauseTimers() {
        MyLog.d(TAG, "DefaultWebView pauseTimers");
        super.pauseTimers();
    }

    //恢复pauseTimers时的动作
    @Override
    public void resumeTimers() {
        MyLog.d(TAG, "DefaultWebView resumeTimers");
        super.resumeTimers();
    }

    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl, final int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    public interface OnScrollChangedCallback {
        void onScroll(final int l, final int t, final int oldl, final int oldt);
    }
}
