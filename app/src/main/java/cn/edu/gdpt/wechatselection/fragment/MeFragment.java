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
import cn.edu.gdpt.wechatselection.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {
    private SimpleAdapter MsimpleAdapter;
    private List<Map<String,Object>> melist;
    private ListView melv;


    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_me, container, false);
        initList();
        MsimpleAdapter=new SimpleAdapter(getContext(),melist,R.layout.me_list_item,new String[]{"logo","title","context"},
                new int[]{R.id.iv_me_list,R.id.tv_list_me_title,R.id.tv_list_me_context});
        melv=(ListView)view.findViewById(R.id.lv_me_body);
        melv.setAdapter(MsimpleAdapter);
        registerForContextMenu(melv);
        melv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initList() {
        melist=new ArrayList<Map<String,Object>>();
        Map<String,Object> c1=new HashMap<String, Object>();
        c1.put("logo",R.drawable.default_icon);
        c1.put("title","皮卡丘");
        c1.put("context","WeChat ID:14785236");
        melist.add(c1);

        Map<String,Object> c2=new HashMap<String, Object>();
        c2.put("logo",R.drawable.wechatpay);
        c2.put("title","微信支付");
        melist.add(c2);

        Map<String,Object> c3=new HashMap<String, Object>();
        c3.put("logo",R.drawable.shoucang);
        c3.put("title","收藏");
        melist.add(c3);

        Map<String,Object> c4=new HashMap<String, Object>();
        c4.put("logo",R.drawable.xiangce);
        c4.put("title","相册");
        melist.add(c4);

        Map<String,Object> c5=new HashMap<String, Object>();
        c5.put("logo",R.drawable.piaozheng);
        c5.put("title","票证&卡券");
        melist.add(c5);

        Map<String,Object> c6=new HashMap<String, Object>();
        c6.put("logo",R.drawable.tietu);
        c6.put("title","贴图市集");
        melist.add(c6);

        Map<String,Object> c7=new HashMap<String, Object>();
        c7.put("logo",R.drawable.sheding);
        c7.put("title","设置");
        melist.add(c7);

    }

}
