package cn.edu.gdpt.wechatselection.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.gdpt.wechatselection.R;
import cn.edu.gdpt.wechatselection.chat.ChatContextActivity;
import cn.edu.gdpt.wechatselection.find.SelectionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends Fragment {
    private SimpleAdapter FsimpleAdapter;
    private List<Map<String,Object>> findlist;
    private ListView findlv;


    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_find, container, false);
        initlistV();
        FsimpleAdapter=new SimpleAdapter(getContext(),findlist,R.layout.find_list_item,new String[]{"logo","title"},
                new int[]{R.id.iv_find_list,R.id.tv_list_find_title});
        findlv=(ListView)view.findViewById(R.id.lv_find_body);
        findlv.setAdapter(FsimpleAdapter);
        registerForContextMenu(findlv);
        findlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), SelectionActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initlistV(){
        findlist=new ArrayList<Map<String,Object>>();
        Map<String,Object> c1=new HashMap<String, Object>();
        c1.put("logo",R.drawable.pengyouquan);
        c1.put("title","朋友圈");
        findlist.add(c1);

        Map<String,Object> c2=new HashMap<String, Object>();
        c2.put("logo",R.drawable.saomiao);
        c2.put("title","扫描");
        findlist.add(c2);

        Map<String,Object> c3=new HashMap<String, Object>();
        c3.put("logo",R.drawable.yaoyiyao);
        c3.put("title","摇一摇");
        findlist.add(c3);

        Map<String,Object> c4=new HashMap<String, Object>();
        c4.put("logo",R.drawable.kanyikan);
        c4.put("title","看一看");
        findlist.add(c4);

        Map<String,Object> c5=new HashMap<String, Object>();
        c5.put("logo",R.drawable.souyisou);
        c5.put("title","搜一搜");
        findlist.add(c5);

        Map<String,Object> c6=new HashMap<String, Object>();
        c6.put("logo",R.drawable.youxi);
        c6.put("title","游戏");
        findlist.add(c6);

        Map<String,Object> c7=new HashMap<String, Object>();
        c7.put("logo",R.drawable.xiaochengxu);
        c7.put("title","小程序");
        findlist.add(c7);
    }

}
