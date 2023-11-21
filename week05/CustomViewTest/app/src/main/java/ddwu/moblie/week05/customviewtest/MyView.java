package ddwu.moblie.week05.customviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyView extends View {
    Random random = new Random();

    boolean flag = false;

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

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        if(flag == true) {
            int x = random.nextInt(500);
            int y = random.nextInt(500);
            int r = (random.nextInt(3)+1) * 100;

            Paint paint = new Paint();
            paint.setColor(Color.CYAN);
            canvas.drawCircle(x, y, r, paint);
        }
    }
}
