package yishitongda.www.demo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import java.security.Permission;

import yishitongda.www.demo.R;

public class MainActivity extends BaseActivity implements OnClickListener {
    private static final int REQUEST_CODE_PERMISSION = 12;

    private View btWeb;
    private View bt_video_call;
    private View bt_screen_cap;

    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        btWeb = findViewById(R.id.bt_web);
        bt_video_call = findViewById(R.id.bt_video_call);
        bt_screen_cap = findViewById(R.id.bt_screen_cap);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE_PERMISSION);
    }

    @Override
    protected void initEvent() {
        btWeb.setOnClickListener(this);
        bt_video_call.setOnClickListener(this);
        bt_screen_cap.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view == btWeb) {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        } else if (view == bt_video_call) {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        } else if (view == bt_screen_cap) {
            Intent intent = new Intent(MainActivity.this, ScreenCapActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION){
        }
    }
}
