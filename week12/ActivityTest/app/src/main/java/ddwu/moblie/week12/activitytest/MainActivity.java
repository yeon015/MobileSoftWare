package ddwu.moblie.week12.activitytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static int SUB_ACTIVITY_CODE = 100;  //intent 구분 위함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.button:
                //객체 넣기 위해 생성
//                DataClass dataClass = new DataClass();
//                //SubActivity 띄우는 코드
//                Intent intent = new Intent(this, SubActivity.class);
//
//                intent.putExtra("subject", "mobile software"); //변수이름, 변수값
//
//                intent.putExtra("time", 3);
//
//                intent.putExtra("dataClass", dataClass); // 이를 위해 DataClass에서 implements Serializable 해야함.
//
//                //startActivity(intent); //이렇게만 하면 앱이 꺼짐(죽음) log에서 보면 ActivityNotFoundException 즉, activity찾을 수 없다. 이유는? 등록을 안했기 때문!!! -> AndroidManifest.xml에서!!
//
//            //인텐트를 이용한 정보 전달3. main->sub 후 sub에서 자료를 만들어서 ->main으로 뭔가를 보냄. (위에는 main->sub이 끝) SubActivity에서 나온 결과를 받겠다. 결과 -> ok인지 cancel인지
//               startActivityForResult(intent, SUB_ACTIVITY_CODE); //여러 activity일 경우 구분 위함.

                //묵시적 인텐트(외부의 다른 앱의 액티비티를 호출)
                  //1
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//                startActivity(intent); //클릭하는 순간 sub액티비티가 아닌 구글 웹브라우저가 나옴
                  //2
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:012-3456-7890"));
                startActivity(intent); //전화관련된 것 뜸. 전화번호와 함께
                break;
        }
    }

    //인텐트를 이용한 정보 전달3 - Sub에서 보낸 결과를 받게 되면 아래 메소드가 자동으로 호출됨!
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //SUB_ACTIVITY_CODE이 값에 해당하는 것이 들어옴(내가 요청했던 코드에 해당하는 결과인지 구분), RESULT_OK인지 RESULT_CANCLED인지 구분, ok 일 경우 resultIntent(즉, 없을 수도 있음)
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case SUB_ACTIVITY_CODE:
                if(resultCode == RESULT_OK){
                    String resultData = data.getStringExtra("result_data");
                    Toast.makeText(this, "Result: " + resultData, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}