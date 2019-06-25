package cn.edu.gdpt.wechatselection.activity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.wechatselection.Item;
import cn.edu.gdpt.wechatselection.R;
import cn.edu.gdpt.wechatselection.fragment.AddressListFragment;
import cn.edu.gdpt.wechatselection.fragment.ChatFragment;
import cn.edu.gdpt.wechatselection.fragment.FindFragment;
import cn.edu.gdpt.wechatselection.fragment.MeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton ib_add,ib_search;
    private RadioButton rb_chat,rb_address,rb_find,rb_me;
    private ViewPager vp_body;
    private RadioGroup rg_btn;
    private TextView tv_title;
    private List<Fragment> fragments=new ArrayList<>();
    private LinearLayout add_see,add_search,add_chat,add_friend,add_help;
    private DrawerLayout drawerLayout;
    private ListView listView_draw;
    private ArrayList<Item> menuList;
    private Myadapter myAdapter=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnPhotos();
        initListener();
        initViewPager();

        drawerLayout=(DrawerLayout)findViewById(R.id.dl_layout);
        listView_draw=(ListView)findViewById(R.id.lv_draweMenu);
        menuList=new ArrayList<Item>();
        menuList.add(new Item(R.drawable.iv_menu_realtime,"实时信息"));
        menuList.add(new Item(R.drawable.iv_menu_alert,"提醒通知"));
        menuList.add(new Item(R.drawable.iv_menu_trace,"搜一搜"));
        menuList.add(new Item(R.drawable.iv_menu_settings,"相关设置"));
        menuList.add(new Item(R.drawable.back,"返回"));
        myAdapter=new Myadapter();
        listView_draw.setAdapter(myAdapter);
        listView_draw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Toast.makeText(MainActivity.this,"点击了实时信息",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this,"点击了提醒通知",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Toast.makeText(MainActivity.this,"点击了相关设置",Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        MainActivity.this.finish();
                        break;
                        default:
                            break;
                }
                drawerLayout.closeDrawer(listView_draw);
            }
        });

    }



    private class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return menuList.size();
        }

        @Override
        public Object getItem(int i) {
            return menuList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1= LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item_drawerlayout,null);
            ImageView imageView=(ImageView)view1.findViewById(R.id.iv_draw);
            TextView textView=(TextView)view1.findViewById(R.id.tv_draw);
            imageView.setImageResource(menuList.get(i).getIconId());
            textView.setText(menuList.get(i).getIcName());
            return view1;
        }
    }
    private void initViewPager() {
        fragments.add(new ChatFragment());
        fragments.add(new AddressListFragment());
        fragments.add(new FindFragment());
        fragments.add(new MeFragment());
        vp_body.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }
    private void initListener() {
        vp_body.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        rg_btn.check(R.id.rb_chat);
                        tv_title.setText("聊天");
                        break;
                    case 1:
                        rg_btn.check(R.id.rb_address);
                        tv_title.setText("通讯录");
                        break;
                    case 2:
                        rg_btn.check(R.id.rb_find);
                        tv_title.setText("发现");
                        break;
                    case 3:
                        rg_btn.check(R.id.rb_me);
                        tv_title.setText("我");
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        rg_btn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_chat:
                        vp_body.setCurrentItem(0,false);
                        break;
                    case R.id.rb_address:
                        vp_body.setCurrentItem(1,false);
                        break;
                    case R.id.rb_find:
                        vp_body.setCurrentItem(2,false);
                        break;
                    case R.id.rb_me:
                        vp_body.setCurrentItem(3,false);
                        break;
                }
            }
        });
    }
    private void initView(){
        ib_add = (ImageButton) findViewById(R.id.ib_add);
        ib_search = (ImageButton) findViewById(R.id.ib_search);
        vp_body = (ViewPager) findViewById(R.id.vp_body);
        rb_chat = (RadioButton) findViewById(R.id.rb_chat);
        rb_address = (RadioButton) findViewById(R.id.rb_address);
        rb_find = (RadioButton) findViewById(R.id.rb_find);
        rb_me = (RadioButton) findViewById(R.id.rb_me);
        rg_btn = (RadioGroup) findViewById(R.id.rg_btn);
        tv_title=(TextView) findViewById(R.id.tv_main_title);

        ib_add.setOnClickListener(this);
        ib_search.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_add:
                View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.addwindow,null);
                final PopupWindow popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(v);
                add_chat=(LinearLayout)view.findViewById(R.id.ll_add_chat);
                add_see=(LinearLayout)view.findViewById(R.id.ll_add_see);
                add_search=(LinearLayout)view.findViewById(R.id.ll_add_search);
                add_friend=(LinearLayout)view.findViewById(R.id.ll_add_friend);
                add_help=(LinearLayout)view.findViewById(R.id.ll_add_help);
                add_see.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"点击了扫一扫",Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                add_chat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"点击了发起群聊",Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                add_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(intent);

                    }
                });
                add_help.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"点击了帮助与反馈",Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                add_friend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"点击了添加朋友",Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });
                break;
            case R.id.ib_search:
                Intent intent=new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void btnPhotos(){
        RadioButton[] rb = new RadioButton[4];
        rb[0] =rb_chat;
        rb[1]=rb_address;
        rb[2]=rb_find;
        rb[3]=rb_me;
        for (RadioButton r:rb){
            Drawable[] drawables=r.getCompoundDrawables();
            Rect rect=new Rect(0,0,drawables[1].getMinimumWidth()/15,drawables[1].getMinimumHeight()/15);
            drawables[1].setBounds(rect);
            r.setCompoundDrawables(null,drawables[1],null,null);
        }
    }
}
