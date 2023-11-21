package ddwucom.mobile.test14.exam02;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    Food food;

    EditText etFood;
    EditText etNation;

    FoodDBManager foodDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        food = (Food) getIntent().getSerializableExtra("food");

        etFood = findViewById(R.id.et_food_name);
        etNation = findViewById(R.id.et_nation);

        //food객체에 있는 음식명과 나라명을 화면에 나타냄
        etFood.setText(food.getFood());
        etNation.setText(food.getNation());

        foodDBManager = new FoodDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_update:
                String foodName = etFood.getText().toString();
                String nation = etNation.getText().toString();  //바뀌었는지 안바뀌었는지는 체크 안함(간편하게 하기 위함)

// DBManager이용
//               FoodDBHelper foodDBHelper = new FoodDBHelper(this);
//                SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();
//
//                ContentValues row = new ContentValues();
//                row.put(FoodDBHelper.COL_FOOD, foodName);
//                row.put(FoodDBHelper.COL_NATION, nation);
//
//                String whereClause = FoodDBHelper.COL_ID + "=?";
//                String[] whereArgs = new String[] {String.valueOf(food.get_id())};
//
//                int result = sqLiteDatabase.update(foodDBHelper.TABLE_NAME, row, whereClause, whereArgs);

                food.setFood(foodName);
                food.setNation(nation);

                if(foodDBManager.modifyFood(food)){
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("foodName", foodName);  //또는 "food", food 도 가능
                    setResult(RESULT_OK, resultIntent);
                }else {
                    setResult(RESULT_CANCELED);
                }

                break;
            case R.id.btn_cancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
