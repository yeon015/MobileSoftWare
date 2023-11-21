package ddwu.moblie.week06.exam02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MyView myView;
    float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = findViewById(R.id.myView);
//        myView.setOnTouchListener(myView);
        myView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                myView.flag = true;
                x = myView.getX();
                y = myView.getY();

                myView.x = motionEvent.getX();
                myView.y = motionEvent.getY();
                myView.invalidate();
                return false;
            }
        });

        myView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                System.out.println("before : " + x + ", " + y);
                System.out.println("after : " + myView.getX() + ", " + myView.getY());
                if(x != myView.getX() || y != myView.getY()) {
                    myView.flag = true;
                    myView.paintColor = Color.BLUE;
                }
                return false;
            }

        });
    }
}

//    View.OnLongClickListener longClickListener = new View.OnLongClickListener(){
//
//        @Override
//        public boolean onLongClick(View view) {
//
//            return false;
//        }
//
//    };


//    View.OnTouchListener touchListener = new View.OnTouchListener() {
//
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            myView = findViewById(R.id.myView);
//
//            myView.flag = true;
//            myView.x = motionEvent.getX();
//            myView.y = motionEvent.getY();
//            myView.invalidate();
//            return false;
//        }
//    };
