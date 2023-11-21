package ddwu.moblie.week06.exam02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyView extends View {   //implements View.OnTouchListener
    float x,y;
    int r = 80;
    boolean flag = false;
    int paintColor = Color.RED;

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        MyView myView = findViewById(R.id.myView);
//
//        myView.flag = true;
//        myView.x = event.getX();
//        myView.y = event.getY();
//        return super.onTouchEvent(event);
//    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        if(flag == true){
            Paint paint = new Paint();
            paint.setColor(paintColor);
            canvas.drawCircle(x, y, r, paint);
        }
    }

//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        this.flag = true;
//        x = motionEvent.getX();
//        y = motionEvent.getY();
//        this.invalidate();
//        return false;
//    }
}

