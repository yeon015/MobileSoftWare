package ddwu.moblie.week13.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class FoodDBHelper extends SQLiteOpenHelper {  // SQLiteOpenHelper를 상속
    final static String TAG = "FoodDBHelper";
    final static String DB_NAME = "foods.db";
    public final static String TABLE_NAME = "food_table";  //테이블 이름 여러번 쓰기에 상수 처리
    public final static String COL_ID = "_id";
    public final static String COL_FOOD = "food";
    public final static String COL_NATION = "nation";

 //3개의 메소드 반드시 재정의!
    //생성자. 데이터베이스 테이블을 만드는 역할 (new MyDBHelper(this) 하는 순간 생성자 만들어짐)
    public FoodDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);  //context, DB 파일명, 커서 팩토리, 개발하는 DB 테이블의 버전
    }// 내부에 DB파일이 만들어지는 것. foods.db가 만들어짐. 한번 만들어지면 그 만들어지는 것을 계속 사용해야함 (더 이상 만들어지지 않음)


    //맨처음 한번만 실행됨. getWritableDatabase() 또는 getReadableDatabase()를 최초로 실행하는 순간에 딱 한 번만 onCreate가 실행!!
    @Override  //한번만 생성되면 되기 때문!(SQL문장)
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +   //ID값 자동으로 부여됨.
                COL_FOOD + " TEXT, " + COL_NATION + " TEXT)";
        //위의 string 안에 인자 더 추가 하고 싶은데 여기에 그냥 추가하면 안바뀜 따라서
        // 1. 앱 삭제 후 다시 깔기. 2. version을 높여주면 onUpgrade호출 후에 onCreate가 호출됨.
        // (onUpgrage는 만들었던 테이블이 있으면 지워버리고 다시 onCreate로 새 테이블 생성)
        //위의 문장은 String임. 실행하는 것은 sqListeDatabase.execSQL에서!
        Log.d(TAG, sql);
        sqLiteDatabase.execSQL(sql); //해당 sql 실행 되므로써 테이블 생성됨(sql 직접 실행하고 싶을 떄 사용)
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) { //db, oldVersion, newVersion   ex) 1->2
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);  //테이블 삭제
        onCreate(sqLiteDatabase); //다시 테이블 생성!
    }
    //DB와 테이블은 한번 만들어지면 계속 유지됨.
}
