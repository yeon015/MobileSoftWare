package ddwucom.mobile.week11.exam01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // DataManager 적용해 볼 것
        foodList = new ArrayList<Food>();
        foodList.add(new Food("김치찌개", "한국"));
        foodList.add(new Food("된장찌개", "한국"));
        foodList.add(new Food("훠궈", "중국"));
        foodList.add(new Food("딤섬", "중국"));
        foodList.add(new Food("초밥", "일본"));
        foodList.add(new Food("오코노미야키", "일본"));

        // Food 객체의 toString() 메소드가 호출되어 하나의 문자열로 처리됨
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foodList);

        listView.setAdapter(adapter);

        // listView 롱클릭 이벤트 추가
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                final int position = pos;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                String foodName = foodList.get(pos).getFood();  //음식 이름을 꺼내려면 getFood() 사용
                builder.setTitle("음식 삭제")
                        .setMessage(foodName + " 을(를) 삭제하시겠습니까?")
                        .setIcon(R.mipmap.ic_launcher)
//                    .setCancelable(false)   //back버튼 눌러도 dialog 종료 안됨.
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                foodList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", null);

                builder.show();
                return false;
            }
        });
    }


    public void onClick(View v) {
        final ConstraintLayout orderLayout = (ConstraintLayout) View.inflate(this, R.layout.order_layout, null);
        switch (v.getId()) {
            case R.id.button:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("음식 추가")
                        .setView(orderLayout)
//                        .setIcon(R.mipmap.ic_launcher)
//                        .setCancelable(false)   //back버튼 눌러도 dialog 종료 안됨.
                        .setPositiveButton("추가", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(MainActivity.this, "확인!!!", Toast.LENGTH_SHORT).show();  //그냥 this하면 DialogInterface가 들어감. 따라서 에러
                                EditText foodname = orderLayout.findViewById(R.id.foodname);
                                EditText countryname = orderLayout.findViewById(R.id.countryname);
//
                                foodList.add(new Food(foodname.getText().toString(), countryname.getText().toString()));
                                adapter.notifyDataSetChanged();
                            }
                        })
//                        .setNeutralButton("대기버튼", null)
                        .setNegativeButton("취소", null);

//                Dialog dlg = builder.create();
//                dlg.setCanceledOnTouchOutSide(false);   //외부 클릭시 닫히게 하지 않기 위해
//                dlg.show();
                builder.show();
        }
    }

}
