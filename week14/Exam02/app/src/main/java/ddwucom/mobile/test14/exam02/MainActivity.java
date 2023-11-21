package ddwucom.mobile.test14.exam02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<Food> foodList = null;
//    FoodDBHelper dbHelper;   //DB매니저로 인해 helper 직접 생성 x
    //DB매니저 생성
    FoodDBManager foodDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        foodList = new ArrayList<Food>();

// onResume으로 인해 필요 x
//        dbHelper = new FoodDBHelper(this);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME, null);
//
//        while(cursor.moveToNext()) {
//            long id = cursor.getInt(cursor.getColumnIndex(FoodDBHelper.COL_ID));
//            String food = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
//            String nation = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_NATION));
//            foodList.add ( new Food (id, food, nation) );
//        }
//
//        cursor.close();
//        dbHelper.close();

        adapter = new ArrayAdapter<Food>(this, android.R.layout.simple_list_item_1, foodList);  //이 ArrayAdapter는 foodList가 가르키는 저장공간(onCreate에서 생성된 new ArrayList)을 같이 가리킴
        listView.setAdapter(adapter);

        foodDBManager = new FoodDBManager(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Food food = foodList.get(position);  // 현재 클릭한 위치에 표시되고 있는 항목
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("food", food);   //위에 만든 food 객체(serializable). UpdateActivty로 전달되는 것!
                startActivityForResult(intent, UPDATE_CODE);  // 수정했는지 안했는지를 구별하기 위함.
                // 아래 onActivityResult 쓰기 위해서 쓰는 것(그냥 startActivity해도 됨)
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final int pos = position;
                //dialog 작성
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.dialog_title)    //직접 값을 넣어도 되지만 편리성을 위해 values안에 strings.xml에 값들을 넣고 이를 이용
                        .setMessage(R.string.dialog_message)
                        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                deleteRecord(pos);  //코드 길어지는 것을 방지하기 위해 메소드 사용
                                //DBManager 사용
                                boolean result = foodDBManager.removeFood(foodList.get(pos).get_id());
                                if(result) {
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    //이 상태로 실행 시키면 삭제는 실행되나, 화면 갱신 안됨
                                    //화면 갱신(바뀐 DB 다시 가져옴)
                                    foodList.clear();
                                    foodList.addAll(foodDBManager.getAllFood());
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

// DBManager 사용으로 인해 필요 없어짐
//    private void deleteRecord(int pos) {  //해당 위치의 데이터를 삭제
//        //db삭제를 위해서 helper를 사용 (but, DBManager 사용하는게 더 좋은 프로그램이기에 아래 부분을 DBManager로 이동!)
//        FoodDBHelper foodDBHelper = new FoodDBHelper(this);
//        SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();
//        String whereClause = FoodDBHelper.COL_ID + "=?";
//        String[] whereArgs = new String[] {String.valueOf(foodList.get(pos).get_id())};  //listView의 pos위치에 해당하는 food객체 안에서 id값 가져옴. 해당 id값이 long타입이기 때문에 String으로 형변환.
//        int result = sqLiteDatabase.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs);
//
//        if(result > 0) {
//            Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show();
//            //이 상태로 실행 시키면 삭제는 실행되나, 화면 갱신 안됨
//            //화면 갱신(바뀐 DB 다시 가져옴)
//            foodList.clear();
//            foodList.addAll(foodDBManager.getAllFood());
//            adapter.notifyDataSetChanged();
//        } else {
//            Toast.makeText(this, "삭제 실패", Toast.LENGTH_SHORT).show();
//        }
//        foodDBHelper.close();
//    }

    //onResume() 사용
    @Override  // UpdateActivity갔다가 main오면 바로 onResume부터 실행되면서 알아서 갱신됨!!
    protected void onResume() {
        super.onResume();
//        readAllFoods();   //원본 데이터 내용 바뀜

        foodList.clear();
// DBManager 사용! (중요!!!!!!!!!!!!!!!!!!!!!!!!!!!)
    //foodList = foodDBManager.getAllFood();   //foodList는 래퍼런스 변수! 이 변수는 new ArrayList를 가리키는 공간!! 실제 저장공간이 아님.
        //(위 어뎁터 참고)  foodDBManger.getAllFood()에서 새로운 ArrayList를 생성한 후 그걸 return하는 방식이기 때문에
        //위에서 foodDBManager.getAllFood();를 하는 순간 저장공간이 하나가 더 생기고 여기에 음식 내용들이 저장되고,
        //foodList = 에서 foodList는 main에서 만든 new ArrayList로 인해 만들어진 저장공간이 아닌 foodDBManager가 새로 만든 저장공간(바로 윗줄)을 가리키게 됨.
        //이렇게 되면서 foodList가 가리키는 공간과 adapter가 가리키는 공간이 달라지게 됨. 따라서 화면상에 변화가 일어나지 않는 것!(아무것도 나타나지 않음)
        //따라서 foodList = foodDBManager.getAllFood(); 이 코드가 아니라 foodList에 DBManager에서 가져온 arrayList를 추가하는 메소드를 사용해야함!! -> addAll()메소드
        foodList.addAll(foodDBManager.getAllFood());  //즉, onCreate에서 생성했던 new ArrayList에 foodDBManager에서 생성한 arrayList를 넣어줌으로써(가리키는 것 x)
        //adpater와 foodList가 같은 저장공간을 가리키게 됨!! (둘 다 new ArrayList!!). 이로써 화면에 정상적으로 나옴.
        //but, 이렇게만 하면 중복돼서 내용이 출력되기에 앞에 foodList.clear();를 넣어줘야함!!
        adapter.notifyDataSetChanged();  //갱신
    }

//데이터베이스 관리 클래스로 인해 필요없어짐!
//    private void readAllFoods() {
//        //꼭 foodlist를 비워야 중복된 데이터들이 출력되지 않음. 기존 내용 지우고 전체 내용 추가!
//        foodList.clear();
//
////        dbHelper = new FoodDBHelper(this);
////        SQLiteDatabase db = dbHelper.getReadableDatabase();
////
////        Cursor cursor = db.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME, null);
////
////        while(cursor.moveToNext()) {
////            long id = cursor.getInt(cursor.getColumnIndex(FoodDBHelper.COL_ID));
////            String food = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
////            String nation = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_NATION));
////            foodList.add ( new Food (id, food, nation) );
////        }
////
////        cursor.close();
////        dbHelper.close();
//    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                break;
        }
    }

    //subactivity에서 받아온 결과 이용.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    String food = data.getStringExtra("food");
                    Toast.makeText(this, food + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "음식 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {    // UpdateActivity 호출 후 결과 확인 (수정할 때 사용)
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "음식 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "음식 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
