package yishitongda.www.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import java.io.File;

import yishitongda.www.demo.R;
import yishitongda.www.demo.task.MediaRecordService;
import yishitongda.www.demo.utils.FileUtil;

public class ScreenCapActivity extends BaseActivity {
    private static final String TAG = "ScreenCapActivity";
    private static final int LOCAL_REQUEST_CODE = 10;
    private MediaProjectionManager mProjectionManager;
    private Button bt_stop;
    private MediaRecordService mediaRecord;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_screen_cap;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        bt_stop = findViewById(R.id.bt_stop);
    }

    @Override
    protected void initEvent() {
        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaRecord.release();
            }
        });
    }

    @Override
    protected void initData() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            assert mProjectionManager != null;
            Intent captureIntent = mProjectionManager.createScreenCaptureIntent();
            startActivityForResult(captureIntent, LOCAL_REQUEST_CODE);
        } else {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MediaProjection mediaProjection = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mediaProjection = mProjectionManager.getMediaProjection(resultCode, data);
        }
        if (mediaProjection == null) {
            Log.e(TAG, "media projection is null");
            return;
        }

        File file = new File(FileUtil.getStorageFile(),"screen.mp4");  //录屏生成文件
        Log.i(TAG, "onActivityResult: 地址："+file.getAbsolutePath());
        Display display = getWindowManager().getDefaultDisplay();
        mediaRecord = new MediaRecordService(display.getWidth(), display.getHeight(), 6000000, 1,
                mediaProjection, file.getAbsolutePath());
        mediaRecord.start();
    }
}
