package ddwu.moblie.week05.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
//xml에서 MyView 사용하기 위함.
public class MyView extends View {

    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public MyView(Context context) {
        super(context);
        setColor(Color.BLUE); //기본 색
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setColor(Color.BLUE);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setColor(Color.BLUE);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setColor(Color.BLUE);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(100,100,80, paint);
    }
}
