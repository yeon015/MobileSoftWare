package ddwucom.mobile.test14.exam02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FoodDBManager {

    FoodDBHelper foodDBHelper = null;
    Cursor cursor = null;

    public FoodDBManager(Context context) {
        foodDBHelper = new FoodDBHelper(context);
    }

// 필요한 메소드 추가
//    DB의 모든 food를 반환
    public ArrayList<Food> getAllFood() {
        ArrayList foodList = new ArrayList();   //새 리스트에 데이터들을 다 담아서 반환
        SQLiteDatabase db = foodDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + FoodDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(FoodDBHelper.COL_ID));
            String food = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_FOOD));
            String nation = cursor.getString(cursor.getColumnIndex(FoodDBHelper.COL_NATION));
            foodList.add ( new Food (id, food, nation) );
        }

        cursor.close();
        foodDBHelper.close();
        return foodList;
    }

//    DB 에 새로운 food 추가
    public boolean addNewFood(Food newFood) {
        SQLiteDatabase db = foodDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(FoodDBHelper.COL_FOOD, newFood.getFood());
        value.put(FoodDBHelper.COL_NATION, newFood.getNation());

//                insert 메소드를 사용할 경우 데이터 삽입이 정상적으로 이루어질 경우 1 이상, 이상이 있을 경우 0 반환 확인 가능
        long count = db.insert(FoodDBHelper.TABLE_NAME, null, value);

        if(count > 0) return true;
        return false;
    }

//    _id 를 기준으로 food 의 이름과 nation 변경 (내용 수정)
    public boolean modifyFood(Food food) {
//        FoodDBHelper foodDBHelper = new FoodDBHelper(this); (위에 helper가 생성되어있기에 필요 x)
        SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(FoodDBHelper.COL_FOOD, food.getFood());
        row.put(FoodDBHelper.COL_NATION, food.getNation());

        String whereClause = FoodDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(food.get_id())};

        int result = sqLiteDatabase.update(foodDBHelper.TABLE_NAME, row, whereClause, whereArgs);

        foodDBHelper.close(); //빼먹지 않기!

        if (result > 0) return true;
        return false;
    }

//    _id 를 기준으로 DB에서 food 삭제
    public boolean removeFood(long id) {
        SQLiteDatabase sqLiteDatabase = foodDBHelper.getWritableDatabase();
        String whereClause = FoodDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(id)};
        int result = sqLiteDatabase.delete(FoodDBHelper.TABLE_NAME, whereClause, whereArgs);

        foodDBHelper.close();   //빼먹지 않기

        if(result > 0) return true;
        return false;
    }

//    나라 이름으로 DB 검색
    public ArrayList<Food> getFoodsByNation(String nation) {

        return null;
    }

//    음식 이름으로 DB 검색
    public ArrayList<Food> getFoodByName(String foodName) {
        return null;
    }

//    id 로 DB 검색
    public Food getFoodById(long id) {

        return  null;
    }

//    close 수행
    public void close() {
        if (foodDBHelper != null) foodDBHelper.close();
        if (cursor != null) cursor.close();
    };
}
