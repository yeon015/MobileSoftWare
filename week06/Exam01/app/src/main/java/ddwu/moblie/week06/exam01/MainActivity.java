package ddwu.moblie.week06.exam01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyClick myClick = new MyClick();

        Button btnDisplay = findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(myClick);
    }

    class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText editText = findViewById(R.id.editText);
            String result = editText.getText().toString();
            TextView tvDisplay = findViewById(R.id.tvDisplay);
            tvDisplay.setText(result);
        }
    }

}