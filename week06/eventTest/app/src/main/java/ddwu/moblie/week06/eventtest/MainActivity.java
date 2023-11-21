package ddwu.moblie.week06.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {   //2번 유형  implements View.OnLongClickListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MyClick과 버튼을 연결해주는 작업 (1번유형)
        Button button = findViewById(R.id.buttonId);  //버튼 찾아옴
        MyClick myClick = new MyClick(); //클릭 객체 만듦.
        button.setOnClickListener(myClick); //위에 두개 연결. myClick 객체를 버튼에 다가 연결시키는 것(끼워넣는것)!!!

        TextView textView = findViewById(R.id.textViewId);

//        MyTouch myTouch = new MyTouch(); //인터페이스 구현 클래스의 객체 생성
//        textView.setOnTouchListener(myTouch); //MyTouch와 TextView를 연결.(1번 유형)

//        MyLongClick myLongClick = new MyLongClick();
//        textView.setOnLongClickListener(myLongClick);  //1번 유형

//        textView.setOnLongClickListener(this);  //2번 유형  MainActivity가 직접 구현한것! this는 main

        //4번 유형
//        textView.setOnLongClickListener(longClickListener);
        //or
//        textView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Toast.makeText(MainActivity.this, "Touch!!!", Toast.LENGTH_SHORT).show();
//                return false;  //이거 하나만 처리하면 되기에 true 해줘도 o
//            }
//        });  //위에 new ~(){} 는 이 안에서만 쓸 수 있으므로 임시 객체

        //ex) if, longclick 시에 textview를 Long Click!!!으로 바꾸고 싶을때
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this, "Touch!!!", Toast.LENGTH_SHORT).show();
                textView.setText("Long Click!!!");
                return true;
            }
        });

    }

    class MyClick implements View.OnClickListener {  //1번 유형
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, "Click!!", Toast.LENGTH_SHORT).show();  //여기서 this객체는 이 클래스 즉 MyClick을 가리킴. 따라서 정확성을 위해.
        }
    }


    //해당 이벤트 interface 구현. 인터페이스 구현 클래스 작성(1번 유형)  인터페이스에서 객체 직접 구현 못하니 어쩔 수 없이 이걸 구현하는 클래스 만든 것!
//    class MyTouch implements View.OnTouchListener {
//        @Override
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            Toast.makeText(MainActivity.this, "Touch!!!", Toast.LENGTH_SHORT).show();  //여기 this는 MyTouch가 됨. but, 여기 this는 context역할을 하는 Activiy객체가 들어가야함. 따라서 MainActiviy.this 로 명시적 지정(내부클래스이기에 이렇게 가능)
//            return false;
//        }
//    }

//    class MyLongClick implements View.OnLongClickListener {
//
//        @Override
//        public boolean onLongClick(View view) {
//            Toast.makeText(MainActivity.this, "Touch!!!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    }

//    //2번 유형
//    @Override
//    public boolean onLongClick(View view) {
//        Toast.makeText(MainActivity.this, "Touch!!!", Toast.LENGTH_SHORT).show();
//        return false;
//     }

    //4번 유형. 원래 인터페이스에서는 메소드 선언만 있기에 객체 만들 수 없음. but, 메소드의 내용물을 채워가면서 만들면 문법적으로 허용되어 객체 생성 가능하게 됨. 아래 변수는 클래스의 멤버 변수
//    View.OnLongClickListener longClickListener = new View.OnLongClickListener() { //원래 인터페이스는 new 메소드() 즉, 객체 생성 불가! 메소드의 선언만 가지고 있고 내용을 가지고 있지 않기 떄문!
//    // new View.OnLongClickListener()로 디폴트 생성자 호출하면서 그 만들어지는 순간에 내용물(아래)을 채워 넣으면 메소드에 내용물이 생기는 것이므로 객체 생성 가능! new 해서 만드는 시점에 내용물을 채워넣으면 객체 생성가능!
//        @Override
//        public boolean onLongClick(View view) {
//            Toast.makeText(MainActivity.this, "Touch!!!", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    }; //여기에 ;붙음!! 일반적인 자바 클래스는 {}부분이 없는 것.
    //longClickListener 없이 new View.OnLongClickListener() {...}를 통째로 textView.setOnLongClickListener(이 안에 넣어줘도 O)  but, 코드 짧을 때 사용하는게 좋음


    //3번 유형 -> MyView 클래스 안에서 하는것! 여기가 아님. 그냥 내가 여기에 쓴것.
//    class MyView extends View implements View.OnClickListner{
//        public MyView(Context context){
//            super(context);
//        }
//    }

//    public void onClick(View view){
//
//    }
}