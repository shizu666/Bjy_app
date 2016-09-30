package app.wms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import app.wms.adapter.GridLayoutAdapter;

public class PdaIndex extends AppCompatActivity {

    private GridView gl;
    private static Boolean isExit = false;
    private Map<String,Object> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pda_index);
        //初始化
        initView();
    }

    private void initView() {
        gl = (GridView) findViewById(R.id.gl);
        final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.cai);
        map.put("name","采购上架");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.diao);
        map.put("name","调拨上架");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.fan);
        map.put("name","返架上架");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.xiaotui);
        map.put("name","销退上架");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.lian);
        map.put("name","拣货下架");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.chu);
        map.put("name","出库确认");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.bu);
        map.put("name","补货作业");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.xiaotui);
        map.put("name","销退验收");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.tong);
        map.put("name","同区移库");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.kua);
        map.put("name","跨区移库");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.pan);
        map.put("name","盘点作业");
        list.add(map);
        map = new HashMap<String,Object>();
        map.put("img",R.mipmap.ku);
        map.put("name","库存查询");
        list.add(map);

        GridLayoutAdapter adapter = new GridLayoutAdapter(list, getApplicationContext());
        gl.setAdapter(adapter);

        gl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==(list.size()-1)){//库存查询
                    Intent intent = new Intent(PdaIndex.this,InventoryQuery.class);
                    startActivity(intent);
                }else if(position==(list.size()-7)){//出库确认
                    Intent intent = new Intent(PdaIndex.this,StockRemoval.class);
                    startActivity(intent);
                }else if(position==8){//同区移库
                    Intent intent = new Intent(PdaIndex.this,SameLibraryMove.class);
                    startActivity(intent);
                }else if(position==9){//跨区移库
                    Intent intent = new Intent(PdaIndex.this,NoSamelibraryMove.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(PdaIndex.this,Order.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("index",position);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            Timer tExit = null;
            if (isExit == false) {
                isExit = true; // 准备退出
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                tExit = new Timer();
                tExit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false; // 取消退出
                        }
                    }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

                } else {
                finish();
                System.exit(0);
                }
            return false;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }


}
