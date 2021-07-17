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

import java.sql.Array;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ShowActivity extends AppCompatActivity {

    Button btnzGet5StarSong;
    Spinner spnyear;
    ArrayList<Song> al;
    ListView lv;
    ArrayAdapter<Song> aa;
//    Song data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        btnzGet5StarSong=findViewById(R.id.btnShow5Stars);
        lv=findViewById(R.id.lv);
        spnyear=findViewById(R.id.spnYear);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        DBHelper dbh = new DBHelper(ShowActivity.this);
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent modi = new Intent(ShowActivity.this,
                        ModifyActivity.class);
                modi.putExtra("songdata", data);
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
        spnyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String filterText = valueOf(spnyear).trim();
                if(filterText == valueOf(R.id.etYear)) {
                    DBHelper dbh = new DBHelper(ShowActivity.this);
                    al = dbh.getAllSongs(filterText);
                }
                else{
                    DBHelper dbh = new DBHelper(ShowActivity.this);
                    al=dbh.getAllSongs();
                    }
                aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}