package sg.edu.rp.c346.id20018354.ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> songList;
//    ArrayAdapter<Song> adapter;
    Button btn5Stars;

    ArrayList<String> years;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    CustomAdapter caadapter;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        songList.clear();
        songList.addAll(dbh.getAllSongs());
//        adapter.notifyDataSetChanged();
        caadapter.notifyDataSetChanged();

        years.clear();
        years.addAll(dbh.getYears());
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_show));

        lv = (ListView) this.findViewById(R.id.lv);
        btn5Stars = (Button) this.findViewById(R.id.btnShow5Stars);
        spinner = (Spinner) this.findViewById(R.id.spnYear);

        DBHelper dbh = new DBHelper(this);
        songList = dbh.getAllSongs();
        years = dbh.getYears();
        dbh.close();

//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songList);
//        lv.setAdapter(adapter);
        caadapter = new CustomAdapter(this, R.layout.row, songList);
        lv.setAdapter(caadapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent modi = new Intent(ShowActivity.this, ModifyActivity.class);
                modi.putExtra("song", songList.get(position));
                startActivity(modi);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ShowActivity.this);
                songList.clear();
                songList.addAll(dbh.getAllSongsByStars(5));
//                adapter.notifyDataSetChanged();
                caadapter.notifyDataSetChanged();
            }
        });

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(ShowActivity.this);
                songList.clear();
                songList.addAll(dbh.getAllSongsByYear(Integer.valueOf(years.get(position))));
//                adapter.notifyDataSetChanged();
                caadapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
