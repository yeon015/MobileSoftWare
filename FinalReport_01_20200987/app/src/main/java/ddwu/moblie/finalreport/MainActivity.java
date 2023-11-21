/*
과제명: 영화 리뷰 앱
분반: 01분반
학번: 20200987 성명: 유승연
제출일:
 */
package ddwu.moblie.finalreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    final int REQ_CODE = 100;
    final int UPDATE_CODE = 200;

    ArrayList<Movie> movieList = null;
    MyAdapter myAdapter;
    ListView listView;
//    MovieDBHelper dbHelper;
    MovieDBManager movieDBManager;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        movieList = new ArrayList<>();

//        dbHelper = new MovieDBHelper(this);
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery("SELECT * FROM " + MovieDBHelper.TABLE_NAME, null);
//
//        while(cursor.moveToNext()) {
//            long id = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ID));
////            int image = cursor.getInt(Integer.valueOf(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_IMAGE)));
//            int image = cursor.getInt(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_IMAGE));
//            String title = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_TITLE));
//            String mainActor = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_ACTOR));
//            String director = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_DIRECTOR));
//            String grade = cursor.getString(cursor.getColumnIndexOrThrow(MovieDBHelper.COL_GRADE));
//            movieList.add ( new Movie (id, image, title, mainActor, director, grade) );
//        }
//
//        cursor.close();
//        dbHelper.close();

        myAdapter = new MyAdapter(this, R.layout.custom_adapter_view, movieList);
        listView.setAdapter(myAdapter);

        movieDBManager = new MovieDBManager(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Movie movie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("movie", movie);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final int pos = position;
                //dialog 작성
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                String movieName = movieList.get(position).getTitle();
                builder.setTitle(R.string.dialog_title)
                        .setMessage(movieName + "를(을) 삭제하시겠습니까?")
                        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                boolean result = movieDBManager.removeMovie(movieList.get(pos).get_id());
                                if(result) {
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    movieList.clear();
                                    movieList.addAll(movieDBManager.getAllMovie());
                                    myAdapter.notifyDataSetChanged();
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

    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_main_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.query_hint));
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            movieList.clear();
            movieList.addAll(movieDBManager.getMovieByTitle(newText));
            myAdapter.notifyDataSetChanged();
            return true;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        movieList.clear();

        movieList.addAll(movieDBManager.getAllMovie());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case  R.id.add:
                intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, REQ_CODE);
                break;
            case R.id.intro:
                intent = new Intent(this, IntroActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                exitOptionsDialog();
                break;
        }
        return true;
    }

    private void exitOptionsDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.exit_name)
                .setMessage(R.string.exit_message)
                .setNegativeButton(R.string.dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setPositiveButton(R.string.exit_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {  // AddActivity 호출 후 결과 확인
            switch (resultCode) {
                case RESULT_OK:
                    String movie = data.getStringExtra("movie");
                    Toast.makeText(this, movie + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } else if (requestCode == UPDATE_CODE) {    // UpdateActivity 호출 후 결과 확인 (수정할 때 사용)
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(this, "영화 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "영화 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}