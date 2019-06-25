package cn.edu.gdpt.wechatselection.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdpt.wechatselection.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn_search_back;
    private EditText edt_search_text;
    private Button btn;
    private TextView tv_search_move;
    private TextView tv_search_book;
    private TextView tv_search_service;
    private TextView tv_search_game;
    private TextView tv_search_music;
    private TextView tv_search_biao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }
    private void initView(){
        btn_search_back=(ImageButton)findViewById(R.id.btn_search_back);
        btn=(Button)findViewById(R.id.btn_search_send);
        edt_search_text=(EditText)findViewById(R.id.edt_search_text);

        btn_search_back.setOnClickListener(this);
        tv_search_move=(TextView)findViewById(R.id.tv_search_move);
        tv_search_move.setOnClickListener(this);
        tv_search_book = (TextView) findViewById(R.id.tv_search_book);
        tv_search_book.setOnClickListener(this);
        tv_search_service = (TextView) findViewById(R.id.tv_search_service);
        tv_search_service.setOnClickListener(this);
        tv_search_game = (TextView) findViewById(R.id.tv_search_game);
        tv_search_game.setOnClickListener(this);
        tv_search_music = (TextView) findViewById(R.id.tv_search_music);
        tv_search_music.setOnClickListener(this);
        tv_search_biao = (TextView) findViewById(R.id.tv_search_biao);
        tv_search_biao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search_back:
                SearchActivity.this.finish();
                break;
            case R.id.tv_search_biao:
                Toast.makeText(SearchActivity.this,"点击了表情包",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search_move:
                Toast.makeText(SearchActivity.this,"点击了动态",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search_music:
                Toast.makeText(SearchActivity.this,"点击了音乐",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search_game:
                Toast.makeText(SearchActivity.this,"点击了小游戏",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search_service:
                Toast.makeText(SearchActivity.this,"点击了服务号",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search_book:
                Toast.makeText(SearchActivity.this,"点击了文章",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    private void submit(){
        String text=edt_search_text.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this,"请输入要搜索的内容！",Toast.LENGTH_SHORT).show();
            return;
        }else {
            Toast.makeText(this,"抱歉，搜索不到您想要的内容！",Toast.LENGTH_SHORT).show();
        }
    }


}
