package ddwu.moblie.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MovieDBManager {
    MovieDBHelper movieDBHelper = null;
    Cursor cursor = null;

    public MovieDBManager(Context context) {
        movieDBHelper = new MovieDBHelper(context);
    }

    // 필요한 메소드 추가
    public ArrayList<Movie> getAllMovie() {
        ArrayList movieList = new ArrayList();   //새 리스트에 데이터들을 다 담아서 반환
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ID));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_IMAGE));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_TITLE));
            String mainActor = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ACTOR));
            String director = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_DIRECTOR));
            String grade = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_GRADE));
            movieList.add ( new Movie (id, image, title, mainActor, director, grade) );
        }

        cursor.close();
        movieDBHelper.close();

        return movieList;
    }

    //    DB 에 새로운 movie 추가
    public boolean addNewMovie(Movie newMovie) {
        SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(MovieDBHelper.COL_IMAGE, newMovie.getImage());
        value.put(MovieDBHelper.COL_TITLE, newMovie.getTitle());
        value.put(MovieDBHelper.COL_ACTOR, newMovie.getMainActor());
        value.put(MovieDBHelper.COL_DIRECTOR, newMovie.getDirector());
        value.put(MovieDBHelper.COL_GRADE, newMovie.getGrade());

//                insert 메소드를 사용할 경우 데이터 삽입이 정상적으로 이루어질 경우 1 이상, 이상이 있을 경우 0 반환 확인 가능
        long count = db.insert(MovieDBHelper.TABLE_NAME, null, value);

        if(count > 0) return true;
        return false;
    }

    //    _id 를 기준으로 movie 의 이름과 nation 변경 (내용 수정)
    public boolean modifyMovie(Movie movie) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();

        ContentValues row = new ContentValues();
        row.put(MovieDBHelper.COL_IMAGE, movie.getImage());
        row.put(MovieDBHelper.COL_TITLE, movie.getTitle());
        row.put(MovieDBHelper.COL_IMAGE, movie.getImage());
        row.put(MovieDBHelper.COL_ACTOR, movie.getMainActor());
        row.put(MovieDBHelper.COL_DIRECTOR, movie.getDirector());
        row.put(MovieDBHelper.COL_GRADE, movie.getGrade());

        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(movie.get_id())};

        int result = sqLiteDatabase.update(movieDBHelper.TABLE_NAME, row, whereClause, whereArgs);

        movieDBHelper.close();

        if (result > 0) return true;
        return false;
    }

    //    _id 를 기준으로 DB에서 movie 삭제
    public boolean removeMovie(long id) {
        SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
        String whereClause = MovieDBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] {String.valueOf(id)};
        int result = sqLiteDatabase.delete(MovieDBHelper.TABLE_NAME, whereClause, whereArgs);

        movieDBHelper.close();

        if(result > 0) return true;
        return false;
    }

    public ArrayList<Movie> setUpList(){
        ArrayList movieLists = new ArrayList();   //새 리스트에 데이터들을 다 담아서 반환
        SQLiteDatabase db = movieDBHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);

        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ID));
            int image = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_IMAGE));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_TITLE));
            String mainActor = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ACTOR));
            String director = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_DIRECTOR));
            String grade = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_GRADE));
            movieLists.add ( new Movie (id, image, title, mainActor, director, grade) );
        }

        cursor.close();
        movieDBHelper.close();

        return movieLists;
    }

    public ArrayList<Movie> getMovieByTitle(String title) {
        ArrayList<Movie> moviesList = setUpList();
        ArrayList<Movie> filterMovie = new ArrayList<>();
        for(int i = 0; i < moviesList.size(); i++){
            Movie movie = moviesList.get(i);
            if(movie.getTitle().toLowerCase().contains(title.toLowerCase())){
                filterMovie.add(movie);
            }
        }
        return filterMovie;
    }


    //    close 수행
    public void close() {
        if (movieDBHelper != null) movieDBHelper.close();
        if (cursor != null) cursor.close();
    };
}
