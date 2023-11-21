package ddwu.moblie.week10.customadaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<MyData> myDataList;
    private MyAdapter myAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //원본 데이터 준비
       myDataList = new ArrayList<MyData>();

        myDataList.add(new MyData(1,"홍길동", "012345"));
        myDataList.add(new MyData(2,"전우치", "123456"));
        myDataList.add(new MyData(3,"일지매", "234567"));

        //우리가 만든 adapter
        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, myDataList);

        //listView 찾아서
        listView = findViewById(R.id.listView);

        //listView에 adapter 넣음. 결합
        listView.setAdapter(myAdapter);
    }
}