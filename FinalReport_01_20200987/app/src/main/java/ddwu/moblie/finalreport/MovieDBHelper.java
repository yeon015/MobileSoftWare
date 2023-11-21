package ddwu.moblie.finalreport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MovieDBHelper extends SQLiteOpenHelper {
    final static String TAG = "MovieDBHelper";
    final static String DB_NAME = "movies.db";
    public final static String TABLE_NAME = "movie_table";
    public final static String COL_ID = "_id";
    public final static String COL_IMAGE = "image";
    public final static String COL_TITLE = "title";
    public final static String COL_ACTOR = "mainActor";
    public final static String COL_DIRECTOR = "director";
    public final static String COL_GRADE = "grade";

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, 41);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " +
                COL_IMAGE + " integer, " + COL_TITLE + " TEXT, " + COL_ACTOR + " TEXT, " + COL_DIRECTOR + " TEXT, " + COL_GRADE + " TEXT)";
        db.execSQL(sql);

//        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, " + String.valueOf(R.mipmap.inside) + ", '인사이드아웃', '배우', '감독', '3');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, " + R.mipmap.inside + ", '인사이드아웃', '라일리, 라일리의 감정들', '피트 닥터', '9.05');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, " + R.mipmap.zoo + ", '주토피아', '주디, 닉', '바이론 하워드', '9.33');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, " + R.mipmap.roundup + ", '범죄도시2', '마동석, 손석구', '이상용', '9.05');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, " + R.mipmap.spiderman + ", '스파이더맨: 노 웨이 홈', '톰 홀랜드', '존 왓츠', '9.11');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, " + R.mipmap.aladdin + ", '알라딘', '메나 마수드, 나오미 스콧', '가이 리치', '9.42');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
