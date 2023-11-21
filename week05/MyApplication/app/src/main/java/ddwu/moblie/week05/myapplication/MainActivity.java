package ddwu.moblie.week05.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //xml에 MyView 포함시켰을때.
        //setContentView(R.layout.activity_main);

//        //(3) invalidate - ex) 이런식으로 색 변경 호출!
//        MyView myView = new MyView(this);
//        myView.invalidate();
//        setContentView(myView);

        //(1)
        // xml에서 못쓰니 여기서 사용. 화면이 보여지는 순간 onDraw안의 코드 실행 됨.
//        MyInnerView myView = new MyInnerView(this);
//        setContentView(myView);

        //(2)
       //MyView 클래스 생성 후 xml에 MyView 선언 안했을 때 이렇게 하면 가능.
//        MyView myView = new MyView(this);
//        setContentView(myView);

        setContentView(R.layout.activity_main);
    }

    //버튼 눌렀을 때 색 변경. 그림을 다시는 그리는 개념이니 invalidate 사용!
    public void onClick(View v){
        MyView myView = findViewById(R.id.myView);
        myView.setColor(Color.RED);
        myView.invalidate();
    }


    //(1)
// 클래스 안에 내부 클래스. (public 클래스가 아님) 이렇게 하면 xml에서 MyView 접근 못함.따라서 클래스 별도로 만들어줘야함. 생성자는 원하는 만큼만 있어도 가능.
//    class MyInnerView extends View {
//        public MyInnerView(Context context) {
//            super(context);
//        }  //생성자 하나를 만들어도 얘는 필수.
//
    //(3)
    //ex) 버튼 클릭시 색 바뀌게 하려면 메인에 myView.onDraw(new Canvas(this)); 이런식으로 onDraw를 한번 더 호출하면 될거 같지만
    // onDraw는 안드로이드가 필요할 때 알아서 생성되는 것임!! 내가 직접 호출 불가능!!!!!! 따라서 invalidate 사용!!!
//        @Overide
//        protected void onDraw(Canvas canvas) { //상속 메소드. canvas는 view가 가지고 있는 그림이 그려지는 영역
//            super.onDraw(canvas);
//            canvas.drawColor(Color.LTGRAY);  //배경색
//            Paint paint = new Paint();  //그림 그리는 도구. 붓.
//            paint.setColor(Color.BLUE);  //파란 붓
//            canvas.drawCircle(100,100,80, paint);  //캔버스에 저장한 위치와 도구로 그림 그림.
//            canvas.drawCircle(200,150,160, paint); //이러면 원 하나 더 그려짐
//        }
//    }
}