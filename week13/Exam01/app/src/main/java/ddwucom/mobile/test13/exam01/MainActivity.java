package ddwucom.mobile.test13.exam01;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    FoodDBHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        //helper 객체 생성
        myDbHelper = new FoodDBHelper(this);
    }


    public void onClick(View v) {
        SQLiteDatabase db = null;

        switch(v.getId()) {
            case R.id.btnSelect:
                //DB 불러옴(select이기 때문에 읽기 전용으로 얻어옴)
                db = myDbHelper.getReadableDatabase();
            //DB로 작업
                String[] columns = null; //null은 몽땅 다 가져올때. new String[] {FoodDBHelper.COL_ID, FoodDBHelper.COL_FOOD, FoodDBHelper.COL_NATION}
                String selection = null;  //select 조건 없으니 null
                String[] selectArgs = null;

                //모든 레코드 다 가져옴
                Cursor cursor = db.query(FoodDBHelper.TABLE_NAME, columns, selection, selectArgs, null, null, null, null);

                ArrayList<Food> foodList = new ArrayList<Food>();

                while(cursor.moveToNext()){
                    long _id = cursor.getLong(cursor.getColumnIndex(FoodDBHelper.COL_ID));  // == cursor.getLong(0); 0은 table에서의 컬럼 순서. 순서 기억하기 어려우니 해당 ID의 index순서 알려줌
                    String foodName = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
                    String nation = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_NATION));
                    //food 객체 생성
                    Food food = new Food(_id, foodName, nation);
                    foodList.add(food);
                }

                //닫음
                cursor.close();
                myDbHelper.close();  //helper 닫음

                //결과 확인 위함. foodList에서 다 꺼냄
                String result = "";
                for (Food newFood : foodList) {
                    result += newFood.toString() + "\n";
                }

                tvDisplay.setText(result);
                break;
            case R.id.btnAdd:
//                db = myDbHelper.getWritableDatabase();
//                //집어넣을 값
//                ContentValues row = new ContentValues();
//                row.put(FoodDBHelper.COL_FOOD, "갈비탕");
//                row.put(FoodDBHelper.COL_NATION, "한국");
//                db.insert(FoodDBHelper.TABLE_NAME, null, row);
//                myDbHelper.close();
                break;
            case R.id.btnUpdate:

                break;
            case R.id.btnRemove:

                break;
        }

    }

}
