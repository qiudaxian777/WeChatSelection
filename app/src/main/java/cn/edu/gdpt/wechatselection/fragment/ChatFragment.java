package cn.edu.gdpt.wechatselection.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.gdpt.wechatselection.R;
import cn.edu.gdpt.wechatselection.chat.ChatContextActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> lists;
    private ListView lv;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_chat, container, false);
        initlist();
        simpleAdapter =new SimpleAdapter(getContext(),lists,R.layout.chat_list_item,new String[]{"logo","title","context"},
                new int[]{R.id.iv_chat_list,R.id.tv_list_chat_title,R.id.tv_list_chat_context});
        lv=(ListView)view.findViewById(R.id.lv_chat_body);
        lv.setAdapter(simpleAdapter);
        registerForContextMenu(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), ChatContextActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v==lv){
            int i=((AdapterView.AdapterContextMenuInfo)menuInfo).position;
            menu.setHeaderTitle(lists.get(i).get("title").toString());
            menu.setHeaderIcon(R.drawable.miliao);
            menu.add(0,0,0,"详情");
            menu.add(0,1,1,"删除");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int i=menuInfo.position;
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(getActivity(),"信息："+lists.get(i).get("title").toString(),Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(getActivity(),"已删除："+lists.get(i).get("title").toString(),Toast.LENGTH_LONG).show();
                lists.remove(lists.get(i));
                simpleAdapter.notifyDataSetChanged();
                break;
                default:
                    break;
        }
        return true;
    }

    private void initlist(){
        lists=new ArrayList<Map<String,Object>>();
        Map<String,Object> c1=new HashMap<String, Object>();
        c1.put("logo",R.drawable.qiyu);
        c1.put("title","明明");
        c1.put("context","在吗");
        lists.add(c1);

        Map<String,Object> c2=new HashMap<String, Object>();
        c2.put("logo",R.drawable.longjuan);
        c2.put("title","詠丽");
        c2.put("context","在的，打怪人吗？");
        lists.add(c2);

        Map<String,Object> c3=new HashMap<String, Object>();
        c3.put("logo",R.drawable.weixin);
        c3.put("title","微信服务");
        c3.put("context","登陆操作通知");
        lists.add(c3);

        Map<String,Object> c4=new HashMap<String, Object>();
        c4.put("logo",R.drawable.yixin);
        c4.put("title","易信服务公众号");
        c4.put("context","欢迎关注易信，易信将竭诚为您服务！");
        lists.add(c4);

        Map<String,Object> c5=new HashMap<String, Object>();
        c5.put("logo",R.drawable.miliao);
        c5.put("title","小米科技");
        c5.put("context","小米科技将为您提供关于小米的最新信息！");
        lists.add(c5);

        Map<String,Object> c6=new HashMap<String, Object>();
        c6.put("logo",R.color.white);
        c6.put("title","阿凡达");
        c6.put("context","我现在要夺回潘多拉，保卫家园！");
        lists.add(c6);

        Map<String,Object> c7=new HashMap<String, Object>();
        c7.put("logo",R.color.red);
        c7.put("title","大嫂");
        c7.put("context","回家吃饭啦！");
        lists.add(c7);

        Map<String,Object> c8=new HashMap<String, Object>();
        c8.put("logo",R.color.gray);
        c8.put("title","二狗子");
        c8.put("context","好的，等我放完牛就回去！");
        lists.add(c8);

        Map<String,Object> c9=new HashMap<String, Object>();
        c9.put("logo",R.color.green);
        c9.put("title","大哥");
        c9.put("context","臭小子，快点！就等你开饭啦！");
        lists.add(c9);

        Map<String,Object> c10=new HashMap<String, Object>();
        c10.put("logo",R.color.pink);
        c10.put("title","妹妹");
        c10.put("context","还没回来吗？好饿，QAQ！");
        lists.add(c10);

        Map<String,Object> c11=new HashMap<String, Object>();
        c11.put("logo",R.color.black);
        c11.put("title","村长");
        c11.put("context","二狗子，又把牛放到我家草地来，偷草吃！");
        lists.add(c11);

        Map<String,Object> c12=new HashMap<String, Object>();
        c12.put("logo",R.color.blue);
        c12.put("title","何水");
        c12.put("context","我通过了你的朋友验证请求，现在我们可以开始聊天了");
        lists.add(c12);

        Map<String,Object> c13=new HashMap<String, Object>();
        c13.put("logo",R.drawable.lvgu);
        c13.put("title","绿谷");
        c13.put("context","吃我一拳！");
        lists.add(c13);

        Map<String,Object> c14=new HashMap<String, Object>();
        c14.put("logo",R.color.yellow);
        c14.put("title","坤坤");
        c14.put("context","你好，我喜欢唱，跳，rap和篮球，你呢？");
        lists.add(c14);

        Map<String,Object> c15=new HashMap<String, Object>();
        c15.put("logo",R.drawable.lvmao);
        c15.put("title","李四");
        c15.put("context","要坚强，当然是选择原谅她啦！");
        lists.add(c15);
    }

}
