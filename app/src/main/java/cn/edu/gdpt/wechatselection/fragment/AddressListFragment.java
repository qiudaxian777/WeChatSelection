package cn.edu.gdpt.wechatselection.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.edu.gdpt.wechatselection.R;
import cn.edu.gdpt.wechatselection.addresslist.ContactAdapter;
import cn.edu.gdpt.wechatselection.addresslist.LetterView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressListFragment extends Fragment {
    private RecyclerView contactList;
    private String[] contactNames;
    private LinearLayoutManager layoutManager;
    private LetterView letterView;
    private ContactAdapter adapter;


    public AddressListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_address_list, container, false);
       contactNames=new String[]{"明明","詠丽","王二小","刘能","张三丰","李世民","赵家屯","孙悟空","秦始皇","魏忠贤","饿肚子","热热热","腾讯","杨过","裴东来","阿玛尼","三哥","大哥大","方世玉",
               "高大威猛","核平","姐姐","坤坤，鸡你太美","绿谷","战国无双","村长","爸爸","！256d","sbs","#$%","*&fff"};
       contactList=(RecyclerView)view.findViewById(R.id.content_list);
       letterView=(LetterView) view.findViewById(R.id.letter_view);
       layoutManager=new LinearLayoutManager(getContext());

       adapter=new ContactAdapter(getContext(),contactNames);
        contactList.setLayoutManager(layoutManager);
        contactList.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        contactList.setAdapter(adapter);

        letterView.setCharacterClickListener(new LetterView.CharacterClickListener() {
            @Override
            public void clickCharacter(String character) {
                layoutManager.scrollToPositionWithOffset(adapter.getScrollPosition(character),0);
                Toast.makeText(getContext(),character,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clickArrow() {
                layoutManager.scrollToPositionWithOffset(0,0);
            }
        });

        return view;
    }

}
