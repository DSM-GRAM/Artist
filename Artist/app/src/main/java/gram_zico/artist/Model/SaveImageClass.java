package gram_zico.artist.Model;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Created by root1 on 2017. 9. 21..
 */

public class SaveImageClass {
    private Bitmap bitmap;
    private static SaveImageClass instance = new SaveImageClass();

    private SaveImageClass(){}

    public static SaveImageClass getInstance(){
        return instance;
    }

    public void setBitmap(Bitmap image){
        Log.d("xxx", image.toString());
        bitmap = image;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

}
