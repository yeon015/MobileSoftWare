package ddwu.moblie.week09.adapterviewtest01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> catList = new ArrayList<String>();
//        catList.add("노르웨이숲");
//        catList.add("러시안블루");
//        catList.add("코리안숏헤어");
//        catList.add("먼치킨");
//        catList.add("터키시앙고라");
//        catList.add("페르시안");
//        catList.add("스코티시폴드");

        DataManager dataManager = new DataManager();
        ArrayList<String> catList = dataManager.getCatList();

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, catList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}