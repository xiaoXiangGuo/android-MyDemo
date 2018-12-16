package yishitongda.www.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import yishitongda.www.demo.utils.MyLog;


/**
 * author  aellen
 * date    2017/05/08
 */

class DefaultWebChromeClient extends WebChromeClient {

    private static final String TAG = "DefaultWebChromeClient";

    DefaultWebChromeClient(Context context) {
        MyLog.d(TAG, "DefaultWebChromeClient");
    }

    //是否拦截alert
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        MyLog.d(TAG, "DefaultWebChromeClient onJsAlert");
        return super.onJsAlert(view, url, message, result);
    }

    //是否拦截confirm
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        MyLog.d(TAG, "DefaultWebChromeClient onJsConfirm");
        return super.onJsConfirm(view, url, message, result);
    }

    //是否拦截prompt
    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        MyLog.d(TAG, "DefaultWebChromeClient onJsPrompt");
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }

    //网址图片
    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        MyLog.d(TAG, "DefaultWebChromeClient onReceivedIcon");
        super.onReceivedIcon(view, icon);
    }

    //网址标题
    @Override
    public void onReceivedTitle(WebView view, String title) {
        MyLog.d(TAG, "DefaultWebChromeClient onReceivedTitle");
        super.onReceivedTitle(view, title);
    }

    //网页的加载进度
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        MyLog.d(TAG, "DefaultWebChromeClient onProgressChanged " + newProgress);
        super.onProgressChanged(view, newProgress);
    }
}
