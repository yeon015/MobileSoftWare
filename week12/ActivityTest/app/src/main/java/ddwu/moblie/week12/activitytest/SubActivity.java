package ddwu.moblie.week12.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {  //상속 받아야함

    TextView textView;

    //onCreate() 반드시 구현! ctrl+o(알파벳)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //이거 집어넣어야함
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();  //실행시 운영체제에게 전달 받은 intent를 확보. 이걸 꺼내는 것!
        String data = intent.getStringExtra("subject");

        textView = findViewById(R.id.textView);
        textView.setText(Log.v("SubActivity", "subject: " + data));  //화면에 data값이 뜸

        //숫자 전달 받음
        int time = intent.getIntExtra("time", 2);  //변수, default값. (값이 있으면 변수에 있는 값이 반환되는 거고 없으면 default값이 반환 되는 것)

        //객체 전달 받음
        DataClass dataClass = (DataClass) intent.getSerializableExtra("dataClass");

    }

    //인텐트를 이용한 정보 전달3 - 서브에서 ok를 눌렀는지 cancel을 눌렀는지 전달. (ok던 cancel이던 결과 주면서 창이 닫힘. 즉 메인 화면으로 돌아감. 창이 싹 사라지면서 결과값을 돌려줌)
    public void onClick(View view){
        switch(view.getId()){
            case R.id.sub_button_ok:
                Intent resultIntent = new Intent();  //빈 intent. mainActivity는 뒤에 있고 우린 결과만 전달하면 되기 때문에. 결과를 담기 위함임!(결과를 담을 때는 항상 비움)
                // 주의!! new Intent(this, SubActivity.class); 이렇게 하면 main 앞에 또 main을 생성하는 게 되어버려서 에러!!
                resultIntent.putExtra("result_data", "SubActivity returns data");  //결과를 담음
                setResult(RESULT_OK, resultIntent);  //결과를 돌려줌 (sub -> main). 결과를 잘 만든 상황 -> RESULT_OK
                break;
            case R.id.sub_button_cancel:
                setResult(RESULT_CANCELED);  //잘 만들어지지 않은 경우 -> RESULT_CANCELED (규칙임!!)
                break;
        }
        finish();   //activity 종료 메소드. activity가 사라지는 순간 자동으로 결과가 전해짐. subActivity를 호출했던 main한테!!
    }
}
