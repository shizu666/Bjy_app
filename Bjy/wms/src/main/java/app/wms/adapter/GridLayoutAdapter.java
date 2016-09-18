package app.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

import app.wms.R;

/**
 * Created by zhou on 2016/9/18.
 */
public class GridLayoutAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public GridLayoutAdapter(List<String> list,Context context){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         Gl gl;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.adapter_gridlayout,null);
            gl = new Gl();
            convertView.setTag(gl);
        }else{
            gl = (Gl) convertView.getTag();
        }
        gl.butPda = (Button) convertView.findViewById(R.id.butPda);
        gl.butPda.setText((CharSequence) list.get(position));

        return convertView;
    }

    class Gl{
        private Button butPda;
    }
}
