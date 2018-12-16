package yishitongda.www.demo.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import yishitongda.www.demo.DefaultWebView;
import yishitongda.www.demo.R;

/**
 * Author:  kuencheung
 * Date:    2018/7/6
 * Des:     加载网页
 */
public class WebViewActivity extends BaseActivity {
    DefaultWebView defaultWeb;
    RelativeLayout rlTopBarWeb;
    View statusBarFix;
    ImageView ivLeftTopbar;
    ImageView ivRightTopbar;
    private String url;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_webview_common;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
            type = intent.getStringExtra("type");
        }

        setDefaultWebView();

        if ("yesTopBar".equals(type)) {
            //有导航栏
//            ivLeftTopbar.setImageResource(R.drawable.ic_back);
//            ivRightTopbar.setImageResource(R.drawable.icon_news_home_gray);
//            //沉浸式状态栏和导航栏
//            new HomeAuxiliary().initWebTopBarListenter(defaultWeb, rlTopBarWeb, this);
        } else {
            //无导航栏
            statusBarFix.setVisibility(View.GONE);
            rlTopBarWeb.setVisibility(View.GONE);
        }
    }


    private void setDefaultWebView() {
        defaultWeb.setVerticalScrollBarEnabled(false);//让滚动条不显示出来
        //让webview先不加载图片，等页面加载完后再加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            defaultWeb.getSettings().setLoadsImagesAutomatically(true);
        } else {
            defaultWeb.getSettings().setLoadsImagesAutomatically(false);
        }

        //过渡期前将WebView的硬件加速临时关闭，过渡期后再开启
        defaultWeb.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        if (!TextUtils.isEmpty(url)) {
            defaultWeb.loadUrl(url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && defaultWeb.canGoBack()) {
            defaultWeb.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    @OnClick({R.id.iv_left_topbar, R.id.iv_right_topbar})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.iv_left_topbar:
//                if (defaultWeb != null && defaultWeb.canGoBack()) {
//                    defaultWeb.goBack();// 返回前一个页面
//                } else {
//                    finish();
//                }
//                break;
//            case R.id.iv_right_topbar:
//                MyToast.showShort(BaseApplication.mContext, "分享");
//                break;
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (defaultWeb != null) {
            defaultWeb.clearHistory();
            ((ViewGroup) defaultWeb.getParent()).removeView(defaultWeb);
            defaultWeb.destroy();
            defaultWeb = null;
        }
    }
}
