package ddwu.moblie.week07.menutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   //checked 위함
    boolean[] checkedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ContextView 등록 작업. customView가 만들어져있을 때는 이렇게 사용x
        TextView textView = findViewById(R.id.textView);
        registerForContextMenu(textView);  //onCreateContextMenu 실행 될 것.

        //checked 위함
        checkedItem = new boolean[2];
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_main, menu); //textView를 롱탭할 경우 만들어놓은 xml메뉴가 뜬다.
//        //view 여러개일 경우
//        switch(v.getId()){
//            case R.id.textView:
//                getMenuInflater().inflate(R.menu.menu_main, menu);
//                break;
//        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item01:
                Toast.makeText(this, "Context!!!", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //메뉴 만들어져서 화면에 뜸.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case  R.id.item01:
                Toast.makeText(this, "첫번째 항목!", Toast.LENGTH_SHORT).show();  //만약 코드 길어지면 별도의 메소드 사용해도 o
                break;
        }
        return true;
    }

//    public void onMenuClick(MenuItem item){  //item으로 구분. switch 사용.
//        Toast.makeText(this, "하위 메뉴 항목!!", Toast.LENGTH_SHORT).show();
//    }

    public void onMenuClick(MenuItem item){  //체크 항목
        switch(item.getItemId()){
            case R.id.radio01:
                item.setChecked(true);
                break;
            case R.id.radio02:
                item.setChecked(true);
                break;
        }
    }
    
    public void onCheckClick(MenuItem item){
        switch(item.getItemId()){ //check되어 있으면 풀고 아님 하고. 현 상태를 알아야함. 사실 안해도 수동으로 하면 됨.
            case R.id.check01:
                if(item.isChecked()) {  //중요한건 체크를 했는지 안했는지 기록해놔야함. 나중에 메뉴항목에서 뭐를 체크했고 안했는지 이 배열을 보고 알 수 있음!! 이를 위해 기록해놓는것.
                    checkedItem[0] = false;
                    item.setChecked(false);
                }else{
                    checkedItem[0] = true;
                    item.setChecked(true);
                }
                break;
            case R.id.check02:
                if(item.isChecked()) {  //중요한건 체크를 했는지 안했는지 기록해놔야함.
                    checkedItem[1] = false;
                    item.setChecked(false);
                }else {
                    checkedItem[1] = true;
                    item.setChecked(true);
                }
                break;
        }
    }
}