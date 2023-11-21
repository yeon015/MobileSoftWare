package ddwu.moblie.week04.layouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        //inflation. 위의 한줄과 이 세줄이 같은 것!
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linear = (LinearLayout) inflater.inflate(R.layout.activity_main, null);  //이 두줄이 코드만으로 작성할 경우에서의 내용부분과 같은 것!!
        setContentView(linear);
    }

    public void onClick(View v){
        LinearLayout layout = findViewById(R.id.layout);

        switch(layout.getOrientation()){
            case LinearLayout.HORIZONTAL:
                layout.setOrientation(LinearLayout.VERTICAL);
                break;
            case LinearLayout.VERTICAL:
                layout.setOrientation(LinearLayout.HORIZONTAL);
                break;
        }
    }
}