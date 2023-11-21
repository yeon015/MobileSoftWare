package ddwu.moblie.week07.menutest01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.china1:
                Toast.makeText(this, "달다!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.china2:
                Toast.makeText(this, "맵다!!!", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void onMenuClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.korea1:
                Toast.makeText(this, "김치가 맛있다!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.korea2:
                Toast.makeText(this, "두부가 부드럽다!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}