package ddwu.moblie.week11.dialogtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //선택 항목의 index
    int selectedIndex = 0;
    //음식의 개수만큼 체크 여부의 배열 (체크->t 체크x->f)
    boolean[] selectedItems = new boolean[] {false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        //레이아웃. 커스텀 대화상자
        final ConstraintLayout orderLayout = (ConstraintLayout) View.inflate(this, R.layout.order_layout, null);  //만든 화면이 객체로 정의됨.
        // 2) 문자배열
//        String[] array = new String[] {"짜장면", "짬뽕"};  //이걸 setItems 첫번째 인자에 넣어줄 수도 o
        switch (v.getId()){
            case R.id.button:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("대화상자 제목");
//                builder.setMessage("대화상자 메시지");
//                builder.setIcon(R.mipmap.ic_launcher);
//                builder.setPositiveButton("확인버튼", null);
//                builder.setNeutralButton("대기버튼", null);
//                builder.setNegativeButton("취소버튼", null);

                builder.setTitle("대화상자 제목")
//                    .setMessage("대화상자 메시지")
                  //setMessage 대신 setItems가 들어감!
        // 1) xml 배열
//                    .setItems(R.array.foods, null)
                  //null 대신 클식시 동작
//                    .setItems(R.array.foods, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {  //i는 몇번째
//                            String[] foods = getResources().getStringArray(R.array.foods);  //xml 배열 가져옴
//                            Toast.makeText(MainActivity.this, "선택!!" + foods[i], Toast.LENGTH_SHORT).show();  //클릭 후 dialog가 닫힘 후에 i번째 출력 toast가 나옴. (listener가 구현되어 있으면 롱클릭 후 자동으로 창 닫힘. null일때는 창 안닫힘)
//                        }
//                    })

                    //라디오 버튼
//                    .setSingleChoiceItems(R.array.foods, selectedIndex, new DialogInterface.OnClickListener() {  //selectedIndex가 더 넣어짐. 라디오 버튼의 index를 담는 역할. 이전 선택 항목 index가 저장됨 (처음 초기값은 0(0으로 멤버변수 위에 설정) 이라 첫번째 값이 선택됨)
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {  //i는 몇번째
//                            selectedIndex = i;  //라디오 버튼 중 선택된 해당 index가 selectedIndex에 저장됨. dialog다시 뜰때 그 선택한 값이 유지됨.
//                        }
//                    })

                    //체크박스
//                    .setMultiChoiceItems(R.array.foods, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {  //선택한 항목의 boolean값이 들어옴
//                            selectedItems[i] = b;  //보관. 다시 dialog 띄웠을때 그대로 남아있음
//                        }
//                    })

                    //커스텀 대화상자. 레이아웃
                    .setView(orderLayout)

                    .setIcon(R.mipmap.ic_launcher)
//                    .setCancelable(false)   //back버튼 눌러도 dialog 종료 안됨.
                    .setPositiveButton("확인버튼", new DialogInterface.OnClickListener(){ //확인버튼 클릭시 실행됨!
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(MainActivity.this, "확인!!!", Toast.LENGTH_SHORT).show();  //그냥 this하면 DialogInterface가 들어감. 따라서 에러
//                            finish();  //여기 (modeless)

                            //커스텀 대화상자. 레이아웃.
                            EditText etProduct = orderLayout.findViewById(R.id.etProduct);
                            String product = etProduct.getText().toString();
                            Toast.makeText(MainActivity.this, product, Toast.LENGTH_SHORT).show();  //입력한 값을 알아내서 toast를 띄우는 것
                        }
                    })
                    .setNeutralButton("대기버튼", null)
                    .setNegativeButton("취소버튼", null);

//                Dialog dlg = builder.create();
//                dlg.setCanceledOnTouchOutSide(false);   //외부 클릭시 닫히게 하지 않기 위해. 반드시 dialog 상태에서 해야함!
//                dlg.show();
                builder.show();

                //modal vs modeless
//                finish();  //대화상자 켜짐 동시에 닫힘. 따라서 finish는 확인 취소 버튼쪽에 있어야 정상 실행.

                Toast.makeText(this, "대화상자와와 상관없음", Toast.LENGTH_SHORT).show(); //대화상자 생성시 바로 Toast 뜸. 얘는 Modeless처럼 동작함. 따라서 버튼을 눌렀을때 동작할 수 있게 구현해야함.

               break;
        }
    }
}