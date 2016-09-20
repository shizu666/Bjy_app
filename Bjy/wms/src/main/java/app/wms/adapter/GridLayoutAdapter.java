package app.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import app.wms.R;

/**
 * Created by zhou on 2016/9/18.
 */
public class GridLayoutAdapter extends BaseAdapter {
    private List<Map<String,Object>> list;
    private Context context;

    public GridLayoutAdapter(List<Map<String,Object>> list,Context context){
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
        gl.iv = (ImageView) convertView.findViewById(R.id.iv_gv);
        gl.tv = (TextView) convertView.findViewById(R.id.tv_gv);
        gl.iv.setImageResource((Integer) list.get(position).get("img"));
        gl.tv.setText(list.get(position).get("name").toString());
        return convertView;
    }

    class Gl{
        private ImageView iv;
        private TextView tv;
    }
}
