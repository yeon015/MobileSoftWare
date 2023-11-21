package ddwu.moblie.week03.viewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TextView textView = findViewById(R.id.textView);
//        textView.setText("yeon");
    }

    public void onClick(View v){
        if(v.getId() == R.id.button_ok) {
            EditText editText1 = findViewById(R.id.editText);
            String text = editText1.getText().toString();

            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

        }
    }
}