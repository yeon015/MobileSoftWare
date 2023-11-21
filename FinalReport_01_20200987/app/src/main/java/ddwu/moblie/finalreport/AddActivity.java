package ddwu.moblie.finalreport;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    int addImage;
    EditText addTitle;
    EditText addActor;
    EditText addDirector;
    EditText addGrade;

//    MovieDBHelper dbHelper;
    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addImage = R.mipmap.ic_launcher;
        addActor = findViewById(R.id.addActor);
        addTitle = findViewById(R.id.addTitle);
        addDirector = findViewById(R.id.addDirector);
        addGrade = findViewById(R.id.addGrade);

//        dbHelper = new MovieDBHelper(this);
        movieDBManager = new MovieDBManager(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton:
                if(addTitle.getText().toString().equals("") || addActor.getText().toString().equals("")
                        || addDirector.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Input Error!", Toast.LENGTH_SHORT).show();
                }else{
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues value = new ContentValues();
//                value.put(MovieDBHelper.COL_IMAGE, addImage);
//                value.put(MovieDBHelper.COL_TITLE, addTitle.getText().toString());
//                value.put(MovieDBHelper.COL_ACTOR, addActor.getText().toString());
//                value.put(MovieDBHelper.COL_DIRECTOR, addDirector.getText().toString());
//                value.put(MovieDBHelper.COL_GRADE, addGrade.getText().toString());

//                insert 메소드를 사용할 경우 데이터 삽입이 정상적으로 이루어질 경우 1 이상, 이상이 있을 경우 0 반환 확인 가능
                    boolean result = movieDBManager.addNewMovie(
                            new Movie(addImage, addTitle.getText().toString(), addActor.getText().toString(), addDirector.getText().toString(), addGrade.getText().toString()));

//                if (count > 0) {    // 정상수행에 따른 처리
//                    Intent resultIntent = new Intent();
//                    resultIntent.putExtra("movie", addTitle.getText().toString() );
//                    setResult(RESULT_OK, resultIntent);
//                    dbHelper.close();
//                    finish();
//                } else {        // 이상에 따른 처리
//                    Toast.makeText(this, "새로운 영화 추가 실패!", Toast.LENGTH_SHORT).show();
//                    dbHelper.close();
//                }
                    if (result) {    // 정상수행에 따른 처리
                        Intent resultIntent = new Intent();  //결과를 담는 intent. main에 가져가기 위함
                        resultIntent.putExtra("movie", addTitle.getText().toString() );
                        setResult(RESULT_OK, resultIntent);
                        finish();
                        } else {        // 이상에 따른 처리
                        Toast.makeText(this, "새로운 영화 추가 실패!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.cancelButton:   // 취소에 따른 처리
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
