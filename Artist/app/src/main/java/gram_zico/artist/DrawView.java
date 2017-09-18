package gram_zico.artist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by dsm2017 on 2017-09-15.
 */

public class DrawView extends View {

    private Paint paint = new Paint();//그림 그리기 버튼
    private Path path = new Path();

    private int color = Color.BLACK;
    private int width = 10;

    float y ;//그림 그리는 좌표 설정
    float x ;

    private Paint.Style style;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void changeColor(int color){
        this.color = color;
    }

    public void changeWidth(int size){
        this.width = size;
    }




    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(width);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        x = event.getX();
        y = event.getY();

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

