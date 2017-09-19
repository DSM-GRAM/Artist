package gram_zico.artist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;


/**
 * Created by dsm2017 on 2017-09-15.
 */


public class DrawView extends View {

    private LinkedList<DrawClass> linkedList = new LinkedList<>();

    private Paint paint = new Paint();
    private Path path = new Path();

    private Bitmap bitmap;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void clear(){
        linkedList.clear();
        path = new Path();
        invalidate();
    }

    public void changeColor(int color){
        linkedList.add(new DrawClass(path, getPaint(paint.getColor(), paint.getStrokeWidth())));
        paint.setColor(color);
        path = new Path();
    }

    public void changeWidth(int size){
        linkedList.add(new DrawClass(path, getPaint(paint.getColor(), paint.getStrokeWidth())));
        paint.setStrokeWidth(size);
        path = new Path();
    }

    private Paint getPaint(int color, float width){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        return paint;
    }

    protected void onDraw(Canvas canvas) {
        if (bitmap == null){
            paint.setStrokeWidth(10);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        }

        //canvas.setBitmap(bitmap);
        for(DrawClass drawClass : linkedList){
            canvas.drawPath(drawClass.path, drawClass.paint);
        }

        Log.d("xxx", ""+Color.WHITE);
        Log.d("xxx", ""+paint.getColor());


        canvas.drawPath(path, paint);
    }

    class DrawClass{
        private Path path;
        private Paint paint;

        public DrawClass(Path path, Paint paint) {
            this.path = path;
            this.paint = paint;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();

        return true;
    }

}

