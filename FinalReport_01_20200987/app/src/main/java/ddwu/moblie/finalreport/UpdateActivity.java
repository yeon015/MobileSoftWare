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

public class UpdateActivity extends AppCompatActivity {

    Movie movie;

    ImageView updateImage;
    EditText updateTitle;
    EditText updateActor;
    EditText updateDirector;
    EditText updateGrade;

    MovieDBManager movieDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        movie = (Movie) getIntent().getSerializableExtra("movie");

        updateImage = findViewById(R.id.updateImage);
        updateTitle = findViewById(R.id.updateTitle);
        updateActor = findViewById(R.id.updateActor);
        updateDirector = findViewById(R.id.updateDirector);
        updateGrade = findViewById(R.id.updateGrade);

        updateImage.setImageResource(movie.getImage());
        updateTitle.setText(movie.getTitle());
        updateActor.setText(movie.getMainActor());
        updateDirector.setText(movie.getDirector());
        updateGrade.setText(movie.getGrade());

        movieDBManager = new MovieDBManager(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.updateButton:
                if(updateTitle.getText().toString().equals("") || updateActor.getText().toString().equals("")
                        || updateDirector.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Input Error!", Toast.LENGTH_SHORT).show();
                }else {
                    String title = updateTitle.getText().toString();
                    String actor = updateActor.getText().toString();
                    String director = updateDirector.getText().toString();
                    String grade = updateGrade.getText().toString();

//                MovieDBHelper movieDBHelper = new MovieDBHelper(this);
//                SQLiteDatabase sqLiteDatabase = movieDBHelper.getWritableDatabase();
//
//                ContentValues row = new ContentValues();
//                row.put(MovieDBHelper.COL_IMAGE, image);
//                row.put(MovieDBHelper.COL_TITLE, title);
//                row.put(MovieDBHelper.COL_ACTOR, actor);
//                row.put(MovieDBHelper.COL_DIRECTOR, director);
//                row.put(MovieDBHelper.COL_GRADE, grade);
//
//
//                String whereClause = MovieDBHelper.COL_ID + "=?";
//                String[] whereArgs = new String[] {String.valueOf(movie.get_id())};
//
//                int result = sqLiteDatabase.update(movieDBHelper.TABLE_NAME, row, whereClause, whereArgs);

                    movie.setTitle(title);
                    movie.setMainActor(actor);
                    movie.setDirector(director);
                    movie.setGrade(grade);

                    if (movieDBManager.modifyMovie(movie)) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("movieName", title);
                        setResult(RESULT_OK, resultIntent);
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                    finish();
                }
                break;
            case R.id.cancelButton2:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
}
