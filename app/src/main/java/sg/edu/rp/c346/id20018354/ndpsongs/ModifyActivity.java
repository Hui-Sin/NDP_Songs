package sg.edu.rp.c346.id20018354.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {

    Button btnUpdate,btnDelete,btnCancel;
    TextView ModiID;
    EditText songTitleModi,singersModi,yearModi;
    RadioGroup rdgrpstarsModi;
    Song data;

    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ModiID=findViewById(R.id.tvModiID);
        songTitleModi=findViewById(R.id.etModiSongTitle);
        singersModi=findViewById(R.id.etModiSinger);
        yearModi=findViewById(R.id.etModiYear);
        rdgrpstarsModi=findViewById(R.id.rdgrpStarsModi);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnCancel=findViewById(R.id.btnCancel);
        Intent modi = getIntent();
        data = (Song) modi.getSerializableExtra("data");

        ModiID.setText("ID: " + data.getId());
        songTitleModi.setText(data.getSongTitle());
        singersModi.setText(data.getSingers());
        yearModi.setText(data.getYear());
        rdgrpstarsModi.check(Integer.parseInt(data.getStars()));
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setSongContent(songTitleModi.toString(),singersModi.toString(),Integer.parseInt(yearModi.toString()),rdgrpstarsModi.toString());
                dbh.updateSong(data);
                dbh.close();
                Intent back = new Intent(ModifyActivity.this,
                        ShowActivity.class);
                startActivity(back);
                Toast.makeText(ModifyActivity.this, "Update successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteSong(data.getId());
                Intent back = new Intent(ModifyActivity.this,
                        ShowActivity.class);
                startActivity(back);
                Toast.makeText(ModifyActivity.this, "Delete successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}