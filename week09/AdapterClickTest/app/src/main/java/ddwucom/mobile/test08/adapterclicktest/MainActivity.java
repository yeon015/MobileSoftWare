package ddwucom.mobile.test08.adapterclicktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SubjectManager subjectManager;
    ArrayList<String> subjectList;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subjectManager = new SubjectManager();
        subjectList = subjectManager.getSubjectList();

        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, subjectList
        );

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(itemClickListener);
        listView.setOnItemLongClickListener(itemLongClickListener);
    }

    public void onClick(View v) {
        editText = findViewById(R.id.etItem);
        String text = editText.getText().toString();

        if (v.getId() == R.id.btnInsert) {
            subjectManager.addData(text);
            adapter.notifyDataSetChanged();
        } else if (v.getId() == R.id.btnUpdate) {
            subjectManager.setData(index, text);
            adapter.notifyDataSetChanged();
        }
    }

    AdapterView.OnItemClickListener itemClickListener =
            new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    editText = findViewById(R.id.etItem);
                    String subject = subjectManager.getSubject(pos);
                    editText.setText(subject);
                    index = pos;
                }
            };

    AdapterView.OnItemLongClickListener itemLongClickListener =
            new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    subjectManager.removeData(pos);
                    adapter.notifyDataSetChanged();
                    return true;
                }
            };
}
