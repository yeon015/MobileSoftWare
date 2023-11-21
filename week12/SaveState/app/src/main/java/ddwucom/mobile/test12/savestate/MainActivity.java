package ddwucom.mobile.test12.savestate;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class MainActivity extends Activity {

    final static String TAG = "MainActivity";

    private MyView vw;
    int x;
    int y;
    int radius;
    int color;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		x, y 초기 좌표
        x = 300;
        y = 300;

        //보관하고 있는 x,y좌표 가져옴
        if(savedInstanceState != null){
            x = savedInstanceState.getInt("x");
            y = savedInstanceState.getInt("y");
        }

        radius = 100;
        color = Color.GREEN;
        Log.d(TAG, "변수 x의 현재값: " + x);
        vw = new MyView(this);
        vw.setFocusable(true);
        vw.setFocusableInTouchMode(true);

        setContentView(vw);
    }

    //임시 저장. 액티비티가 사라지기 직전에 호출됨. 강제종료 후에도 값을 유지하고 싶으면 이 보관한 값을 onCreate에 savedInstanceState를 통해 이용
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState); //outState에 보관
        outState.putInt("x", x); //x좌표를 x이름으로 저장
        outState.putInt("y", y); //y좌표를 y이름으로 저장
    }


    protected class MyView extends View {
        public MyView(Context context) {
            super(context);
        }

        public void onDraw(Canvas canvas) {
            Paint p = new Paint();
            p.setColor(color);
            canvas.drawCircle(x, y, radius, p);
        }

        public boolean onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                x = (int) event.getX();
                y = (int) event.getY();
                invalidate();
                return true;
            }
            return false;
        }
    }
}
