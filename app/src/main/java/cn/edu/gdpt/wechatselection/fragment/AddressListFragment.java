package cn.edu.gdpt.wechatselection.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.gdpt.wechatselection.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddressListFragment extends Fragment {


    public AddressListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address_list, container, false);
    }

}