package sg.edu.rp.c346.id20018354.ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songsList;

    public CustomAdapter(Context context,int resource, ArrayList<Song> objects){
        super(context, resource, objects);
        parent_context=context;
        layout_id=resource;
        songsList=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvStitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvStars = rowView.findViewById(R.id.textViewStars);
        TextView tvSingers = rowView.findViewById(R.id.textViewSingers);

        // Obtain the Android Version information based on the position
        Song currentSongList = songsList.get(position);

        // Set values to the TextView to display the corresponding information
        tvStitle.setText(currentSongList.getTitle());
        tvYear.setText((currentSongList.getYearString()));
        tvStars.setText(currentSongList.getStarsString());
        tvSingers.setText(currentSongList.getSingers());

        return rowView;
    }

}
