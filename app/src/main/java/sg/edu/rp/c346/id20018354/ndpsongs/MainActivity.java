package sg.edu.rp.c346.id20018354.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert,btnShowList;
    EditText songTitle,singers,year;
    RadioGroup rdgrpstars;

    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songTitle=findViewById(R.id.etSongTitle);
        singers=findViewById(R.id.etSingers);
        year=findViewById(R.id.etYear);
        btnInsert=findViewById(R.id.btnInsert);
        btnShowList=findViewById(R.id.btnShowList);
        rdgrpstars=findViewById(R.id.rdgrpStarsModi);
        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sstar="";
                String songTitles = songTitle.getText().toString();
                String singer = singers.getText().toString();
                String syear= year.getText().toString();
                int checkedRadioId = rdgrpstars.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.rdbtn1star){
                    sstar= "*";
                }
                else if(checkedRadioId == R.id.rdbtn2star){
                    sstar= "**";
                }
                else if(checkedRadioId == R.id.rdbtn3star){
                    sstar= "***";
                }
                else if(checkedRadioId == R.id.rdbtn4star){
                    sstar= "****";
                }
                else{
                    sstar= "*****";
                }
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSongs(songTitles,singer,Integer.parseInt(syear),sstar);
                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(MainActivity.this,
                        ShowActivity.class);
                startActivity(edit);
            }
        });
    }
}