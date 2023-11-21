package ddwucom.mobile.test13.exam01;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    FoodDBHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        myDbHelper = new FoodDBHelper(this);
    }

    public void onClick(View v){
        SQLiteDatabase db = null;
        switch (v.getId()){
            case R.id.btnAddFood:
                db = myDbHelper.getWritableDatabase();

                //집어넣을 값
                ContentValues row = new ContentValues();
                row.put(FoodDBHelper.COL_FOOD, "갈비탕");
                row.put(FoodDBHelper.COL_NATION, "한국");
                db.insert(FoodDBHelper.TABLE_NAME, null, row);
                myDbHelper.close();

                break;
            case R.id.btnAddCancel:

                break;
        }
    }

}
