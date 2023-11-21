package ddwu.moblie.homework02.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnHello:
                EditText editText1 = findViewById(R.id.etName);
                EditText editText2 = findViewById(R.id.etPhone);

                String name = editText1.getText().toString();
                String phone = editText2.getText().toString();

                Toast.makeText(this, "안녕하세요, 저는 " + name + " 입니다.\n전화번호는 " + phone + " 입니다.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnExit:
                finish();
                break;
        }
    }
}