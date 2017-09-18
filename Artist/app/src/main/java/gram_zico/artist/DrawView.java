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
    View paper;
    public DrawView(Context context) {
        super(context);
        paper=findViewById(R.id.paper);
    }

    class Paper extends View {

        Paint paint = new Paint();//그림 그리기 버튼
        Path path = new Path();

        float y ;//그림 그리는 좌표 설정
        float x ;
        private float strokeWidth;
        private Paint.Style style;

        public Paper(Context context) {
            super(context);
        }

        public Paper(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        protected void onDraw(Canvas canvas) {
            paint.setStrokeWidth(10);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint);
            paint.setDither(true);
            paint.setStrokeWidth(60);
            paint.setAlpha(100);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
        }


        @Override
        public boolean onTouchEvent(MotionEvent event) {

            x = event.getX();
            y = event.getY();

            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.d("xxx", event.toString());
                    path.moveTo(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.d("xxx", "move");
                    x = event.getX();
                    y = event.getY();
                    path.lineTo(x, y);
                    break;
                case MotionEvent.ACTION_UP:
                    Log.d("xxx", "up");
                    break;
            }

            invalidate();

            return true;
        }




    }

}

