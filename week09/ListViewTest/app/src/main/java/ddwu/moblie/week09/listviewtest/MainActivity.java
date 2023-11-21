package ddwu.moblie.week09.listviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataManager dataManager;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //원본데이터 준비
//        ArrayList<String> subjectList = new ArrayList<String>();
//        subjectList.add("모바일소프트웨어");
//        subjectList.add("네트워크");
//        subjectList.add("웹서비스");
//        subjectList.add("운영체제");
//        subjectList.add("웹프로그래밍2");  //activity와 관련없는 코드는 분리하는게 좋음

        //Activity 와 Data의 분리 (activity는 화면 구성 역할이기에 data 생성 관련과는 연관이 떨어짐. 따라서 분리)
        dataManager = new DataManager();
        ArrayList<String> subjectList = dataManager.getSubjectList();

        //Adapter 객체 생성 (레이아웃과 원본데이터로!) 레이아웃은 이미 시스템에 만들어져잇음(simple_list_item1)
//        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subjectList);

        //AdapterView 준비
        ListView listView = findViewById(R.id.listView);

        //AdapterView(ListView)에 Adapter 연결(결합). 이 순간 AdapterView 사용 개념도(ppt6페이지) 과정 내부에서 이루어지는 것.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(itemClickListener);  //안에 new AdapterView.OnItemClickListner를 통째롤 넣어도 됨. 얘는 listView 통째를 클릭했을 경우임. 글씨 클릭 -> textView로 설정해야함
    }

    //항목 선택 이벤트 리스너
    AdapterView.OnItemClickListener itemClickListener =
            new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {  //현재 사용되고 있는 adpaterView, View는 내가 클릭한 view, 몇번째 view를 클릭했는지, DB연결되어 있을 경우 primary키
//                    Toast.makeText(MainActivity.this, "pos: " + pos,Toast.LENGTH_SHORT);
//                    Toast.makeText(MainActivity.this, dataManager.getSubject(pos),Toast.LENGTH_SHORT);

                    //원본 data 삭제(이거 안하면 원본데이터에 계속 남아있음)
                    dataManager.removeData(pos); //이거만 하면 원본데이터만 삭제되고 화면 삭제안됨. 다시 클릭하면 앱이 종료됨. 화면순서와 데이터 순서가 달라졌기 때문에!
                    adapter.notifyDataSetChanged(); //이걸 사용하면 화면도 삭제.

                }
            };
}