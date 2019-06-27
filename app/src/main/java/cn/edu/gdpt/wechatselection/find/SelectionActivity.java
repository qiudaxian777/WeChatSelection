package cn.edu.gdpt.wechatselection.find;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.security.cert.CertPathBuilderSpi;
import java.util.List;

import cn.edu.gdpt.wechatselection.R;
import cn.edu.gdpt.wechatselection.activity.WebActivity;
import cn.edu.gdpt.wechatselection.bean.JinxuanBean;
import cn.edu.gdpt.wechatselection.utils.NetworkListining;
import cn.edu.gdpt.wechatselection.utils.OkhttpUntil;

public class SelectionActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton ib_selection_back;
    private ListView lvs;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activity=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        initView();
        Getinfo();

    }
    public class Mydadapter extends BaseAdapter{
        List<JinxuanBean.ResultBean.ListBean> listBeans;

        public Mydadapter(List<JinxuanBean.ResultBean.ListBean> listBeans) {
            this.listBeans = listBeans;
        }

        @Override
        public int getCount() {
            return listBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final View inflate = LayoutInflater.from(activity).inflate(R.layout.selection_list_item, parent, false);
          TextView textView= inflate.findViewById(R.id.tv_sel_chat_title);
          textView.setText(listBeans.get(position).getTitle());
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(SelectionActivity.this, WebActivity.class);
                    intent.putExtra("url",listBeans.get(position).getUrl());
                    startActivity(intent);
                }
            });
            return inflate;
        }
    }

    private void initView(){
        ib_selection_back = (ImageButton)findViewById(R.id.ib_selection_back);
        ib_selection_back.setOnClickListener(this);
        lvs=findViewById(R.id.lvs);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_selection_back:
                SelectionActivity.this.finish();
                break;
        }

    }
    public void Getinfo(){
        OkhttpUntil.enqueueGetrequest("http://v.juhe.cn/weixin/query?pno=1&ps=20&dtype=&key=3160b829e14a1e17950ba08fe81d4729", JinxuanBean.class, new NetworkListining<JinxuanBean>() {
            @Override
            public void BackResultSuccess(JinxuanBean bean, int code) {
                if(bean!=null){
                    List<JinxuanBean.ResultBean.ListBean> list = bean.getResult().getList();
                    String title = list.get(0).getTitle();
                    String url = list.get(0).getUrl();
                    Mydadapter mydadapter=new Mydadapter(list);
                    lvs.setAdapter(mydadapter);

                }
            }

            @Override
            public void BackResultFail(Exception errow) {

            }

            @Override
            public void tostring(String responseString) {

            }
        });
    }
}
