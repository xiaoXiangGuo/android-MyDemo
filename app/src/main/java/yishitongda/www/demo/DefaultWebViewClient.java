package yishitongda.www.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.util.ConcurrentModificationException;

import yishitongda.www.demo.utils.MyLog;

/**
 * author  aellen
 * date    2017/05/08
 */

class DefaultWebViewClient extends WebViewClient {

    private static final String TAG = "DefaultWebViewClient";
    //TODO 加载网页的进度框
//    private MyLoadingDialog myLoadingDialog;

    DefaultWebViewClient(Context context) {
        MyLog.d(TAG, "DefaultWebViewClient");
//        myLoadingDialog = new MyLoadingDialog((Activity) context, null);
//        myLoadingDialog.setCanceledOnTouchOutside(false);
    }

    //是否拦截请求
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        MyLog.d(TAG, "DefaultWebViewClient shouldInterceptRequest d");
        return super.shouldInterceptRequest(view, url);
    }

    //是否拦截请求
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        MyLog.d(TAG, "DefaultWebViewClient shouldInterceptRequest");
        return super.shouldInterceptRequest(view, request);
    }

    //页面开始加载
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        MyLog.d(TAG, "DefaultWebViewClient onPageStarted");
        super.onPageStarted(view, url, favicon);
//        if (myLoadingDialog != null) {
//            myLoadingDialog.show();
//        }
    }

    //页面加载完成
    @Override
    public void onPageFinished(WebView view, String url) {
        MyLog.d(TAG, "DefaultWebViewClient onPageFinished");
        super.onPageFinished(view, url);
//        if (myLoadingDialog != null && myLoadingDialog.isShowing()) {
//            myLoadingDialog.cancel();
//        }
    }

    //报告错误信息
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        MyLog.d(TAG, "DefaultWebViewClient onReceivedError d");
        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    //报告错误信息
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        MyLog.d(TAG, "DefaultWebViewClient onReceivedError");
        super.onReceivedError(view, request, error);
    }

    //加载资源调用
    @Override
    public void onLoadResource(WebView view, String url) {
        MyLog.d(TAG, "DefaultWebViewClient onLoadResource");
        super.onLoadResource(view, url);
    }

    //请求重新获取网页数据
    @Override
    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        MyLog.d(TAG, "DefaultWebViewClient onFormResubmission");
        super.onFormResubmission(view, dontResend, resend);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }
}