package app.wms.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import app.wms.R;
import app.wms.empty.XfQingDan;

/**
 * Created by zhou on 2016/9/20.
 */
public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<XfQingDan> list;

    public ListViewAdapter(Context context,List<XfQingDan> list){
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
        QingDan qingDan;
        if(convertView==null){
            convertView = View.inflate(this.context, R.layout.adapter_list,null);
            qingDan = new QingDan();
            convertView.setTag(qingDan);
        }else{
            qingDan = (QingDan) convertView.getTag();
        }
        qingDan.tv_la_bianhao = (TextView) convertView.findViewById(R.id.tv_la_bianhao);
        qingDan.tv_la_name = (TextView) convertView.findViewById(R.id.tv_la_name);
        qingDan.tv_la_shuliang = (TextView) convertView.findViewById(R.id.tv_la_shuliang);
        qingDan.tv_la_dsj = (TextView) convertView.findViewById(R.id.tv_la_dsj);
        qingDan.tv_la_zhiliang = (TextView) convertView.findViewById(R.id.tv_la_zhiliang);

        qingDan.tv_la_bianhao.setText(list.get(position).getTv_la_bianhao());
        qingDan.tv_la_name.setText(list.get(position).getTv_la_name());
        qingDan.tv_la_shuliang.setText(list.get(position).getTv_la_shuliang());
        qingDan.tv_la_dsj.setText(list.get(position).getTv_la_dsj());
        qingDan.tv_la_zhiliang.setText(list.get(position).getTv_la_zhiliang());

        return convertView;
    }

    class QingDan{
        public TextView tv_la_bianhao,tv_la_name,tv_la_shuliang,tv_la_dsj,tv_la_zhiliang;
    }


}
