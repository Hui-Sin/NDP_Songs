package sg.edu.rp.c346.id20018354.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button btnzGet5StarSong;
    Spinner spnYear;
    ArrayList<Song> al;
    ListView lv;
    ArrayAdapter<Song> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        btnzGet5StarSong=findViewById(R.id.btnShow5Stars);
        lv=findViewById(R.id.lv);
        spnYear=findViewById(R.id.spnYear);
        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent modi = new Intent(ShowActivity.this,
                        ModifyActivity.class);
                modi.putExtra("data", data);
                startActivity(modi);
            }
        });
        btnzGet5StarSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongs());
                aa.notifyDataSetChanged();
            }
        });
    }
}