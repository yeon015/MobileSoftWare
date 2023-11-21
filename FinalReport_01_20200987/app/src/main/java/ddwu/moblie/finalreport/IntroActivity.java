package ddwu.moblie.finalreport;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ImageView profile = findViewById(R.id.profile);
        profile.setImageResource(R.mipmap.profile);
        Intent intent = getIntent();
    }
}
