package yishitongda.www.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        initView(savedInstanceState);
        initEvent();
        initData();
    }


    protected abstract int getContentViewId();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initEvent();
    protected abstract void initData();

}
