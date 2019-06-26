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
                Intent intent=new Intent(getActivity(), ChatContextActivity.class);
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
        c2.put("logo",R.drawable.kanyikan);
        c2.put("title","看一看");
        findlist.add(c2);
    }

}
