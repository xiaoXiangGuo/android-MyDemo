package yishitongda.www.demo.utils;

import android.os.Environment;

import java.io.File;

public class FileUtil {
    public static File getStorageFile(){
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
    }
}
