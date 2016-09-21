package app.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.wms.R;
import app.wms.empty.MyQingDan;
import app.wms.empty.ViewHolder;

/**
 * Created by zhou on 2016/9/21.
 */
public class KykqdListViewAdapter extends BaseAdapter {
    private Context context;
    private List<MyQingDan> list;

    public KykqdListViewAdapter(Context context, List<MyQingDan> list){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView = View.inflate(this.context, R.layout.adapter_list,null);
            holder = new ViewHolder();
            holder.tv_qd_bianhao = (TextView) convertView.findViewById(R.id.tv_qd_c1);
            holder.tv_qd_name = (TextView) convertView.findViewById(R.id.tv_qd_c2);
            holder.tv_qd_shuliang = (TextView) convertView.findViewById(R.id.tv_qd_c3);
            holder.tv_qd_yishangjia = (TextView) convertView.findViewById(R.id.tv_qd_c4);
            holder.tv_qd_daishangjia = (TextView) convertView.findViewById(R.id.tv_qd_c5);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_qd_bianhao.setText(list.get(position).getTv_qd_bianhao());
        holder.tv_qd_name.setText(list.get(position).getTv_qd_name());
        holder.tv_qd_shuliang.setText(list.get(position).getTv_qd_shuliang());
        holder.tv_qd_daishangjia.setText(list.get(position).getTv_qd_dsj());
        holder.tv_qd_yishangjia.setText(list.get(position).getTv_qd_yishangjia());

        return convertView;
    }

}
