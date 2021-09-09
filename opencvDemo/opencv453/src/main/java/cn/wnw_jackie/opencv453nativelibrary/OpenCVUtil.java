package cn.wnw_jackie.opencv453nativelibrary;

import android.graphics.Bitmap;
import android.util.Log;

public class OpenCVUtil {

    static {

        System.loadLibrary("opencv453");

    }

    private static native int[]grey(int[] pixels_, int width, int height);



    public static Bitmap bmpGrey(Bitmap bm) {

        int width = bm.getWidth();

        int height = bm.getHeight();

        int[] ps =new int[width * height];

        bm.getPixels(ps, 0, width, 0, 0, width, height);

        long time = System.currentTimeMillis();

        int[] newPs =grey(ps, width, height);

        Log.e("debug","excute:" + (System.currentTimeMillis() - time));

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        bitmap.setPixels(newPs, 0, width, 0, 0, width, height);

        return bitmap;

    }

}
