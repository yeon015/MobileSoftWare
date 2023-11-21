package ddwu.com.mobile.exam.mycircletest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyCircle myCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Custom View 객체*/
        myCircle = findViewById(R.id.myCircle);
        registerForContextMenu(myCircle);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.bigger:
               myCircle.setR(myCircle.getR() + 10);
               myCircle.invalidate();
               break;
            case R.id.smaller:
                myCircle.setR(myCircle.getR() - 10);
                myCircle.invalidate();
                break;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch(v.getId()){
            case R.id.myCircle:
                getMenuInflater().inflate(R.menu.menu_file, menu);
                break;
        }
    }

//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()){
//            case R.id.RED:
//                myCircle.setColor(Color.RED);
//                item.setChecked(true);  //menu.findItem(R.id.radio_red).setCheckable(true);
//                myCircle.invalidate();
//                break;
//            case R.id.GREEN:
//                myCircle.setColor(Color.GREEN);
//                item.setChecked(true);
//                myCircle.invalidate();
//
//                break;
//            case R.id.BLUE:
//                myCircle.setColor(Color.BLUE);
//                item.setChecked(true);
//                myCircle.invalidate();
//                break;
//        }
//        return true;
//    }

    public void onMenuClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.RED:
                myCircle.setColor(Color.RED);
                myCircle.invalidate();
                item.setChecked(true);
                break;
            case R.id.GREEN:
                myCircle.setColor(Color.GREEN);
                myCircle.invalidate();
                item.setChecked(true);
                break;
            case R.id.BLUE:
                myCircle.setColor(Color.BLUE);
                myCircle.invalidate();
                item.setChecked(true);
                break;
        }
    }
}
