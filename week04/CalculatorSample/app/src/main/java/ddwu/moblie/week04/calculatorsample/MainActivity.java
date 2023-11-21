package ddwu.moblie.week04.calculatorsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    int num1 = 0, num2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        EditText editText = findViewById(R.id.etDisplay);
        String num = editText.getText().toString();

        switch(v.getId()){
            case R.id.btn_1:
                num += "1";
                editText.setText(num);
                break;
            case R.id.btn_2:
                num += "2";
                editText.setText(num);
                break;
            case R.id.btn_plus:
                num1 = Integer.parseInt(num);
                num = "";
                editText.setText(num);
                break;
            case R.id.btn_equal:
                num2 = Integer.parseInt(num);
                int result = num1 + num2;
                num = Integer.toString(result);
                editText.setText(num);
                break;
        }

    }
}