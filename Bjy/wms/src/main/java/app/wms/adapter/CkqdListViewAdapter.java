package app.wms.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.wms.R;
import app.wms.empty.MyQingDan;
import app.wms.empty.ViewHolder;
import app.wms.inter.MyCallback;


/**
 * Created by zhou on 2016/9/21.
 */
public class CkqdListViewAdapter extends BaseAdapter {
    private Context context;
    private List<MyQingDan> list;
    private MyCallback callback;

    public CkqdListViewAdapter(Context context,List<MyQingDan> list,MyCallback callback){
        this.callback = callback;
        this.context = context;
        this.list = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = View.inflate(this.context, R.layout.adapter_chulist,null);
            holder = new ViewHolder();
            holder.tv_qd_bianhao = (TextView) convertView.findViewById(R.id.tv_qd_ck1);
            holder.tv_qd_dingdanhao = (TextView) convertView.findViewById(R.id.tv_qd_ck2);
            holder.tv_qd_caozuo = (TextView) convertView.findViewById(R.id.tv_qd_ck3);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        if(position==0){
            holder.tv_qd_caozuo.setTextColor(Color.parseColor("#000000"));
        }else{
            holder.tv_qd_caozuo.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
            holder.tv_qd_caozuo.getPaint().setAntiAlias(true);
        }
        holder.tv_qd_bianhao.setText(list.get(position).getTv_qd_bianhao());
        holder.tv_qd_dingdanhao.setText(list.get(position).getTv_qd_dingdanhao());
        holder.tv_qd_caozuo.setText(list.get(position).getTv_qd_caozuo());
        holder.tv_qd_caozuo.setTag(position);
        holder.tv_qd_caozuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.itemClick(v);
            }
        });

        return convertView;
    }
}
