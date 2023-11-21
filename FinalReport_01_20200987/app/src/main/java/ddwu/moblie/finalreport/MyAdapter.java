package ddwu.moblie.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Movie> myDataList;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, int layout, ArrayList<Movie> myDataList) {
        this.context = context;
        this.layout = layout;
        this.myDataList = myDataList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return myDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return myDataList.get(i).get_id();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        final int position = pos;

        ViewHolder holder;

        if(convertView == null){
            convertView = layoutInflater.inflate(layout, viewGroup, false);

            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.title = convertView.findViewById(R.id.title);
            holder.director = convertView.findViewById(R.id.director);
            holder.grade = convertView.findViewById(R.id.grade);

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setImageResource(myDataList.get(position).getImage());
        holder.title.setText(myDataList.get(position).getTitle());
        holder.director.setText(myDataList.get(position).getDirector());
        holder.grade.setText(myDataList.get(position).getGrade());

        return convertView;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView title;
        TextView director;
        TextView grade;
    }
}
