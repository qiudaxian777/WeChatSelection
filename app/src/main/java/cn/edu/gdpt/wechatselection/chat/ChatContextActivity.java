package cn.edu.gdpt.wechatselection.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdpt.wechatselection.R;

public class ChatContextActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton ib_context_back;
    private ListView lv_context_chat;
    private EditText edt_context_input;
    private Button btn_context_send;
    private List<ChatMessage> messages;
    private ChatListAdapter chatListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_context);
        initView();
        messages=new ArrayList<ChatMessage>();
        ChatMessage m1=new ChatMessage("我通过了你的朋友验证，现在我们可以开始聊天了！",ChatMessage.TYPE_RECEIVE);
        messages.add(m1);
        ChatMessage m2=new ChatMessage("在？吃饭吗？",ChatMessage.TYPE_SEND);
        messages.add(m2);
        ChatMessage m3=new ChatMessage("不在，吃过了！",ChatMessage.TYPE_RECEIVE);
        messages.add(m3);

        chatListAdapter=new ChatListAdapter();
        lv_context_chat.setAdapter(chatListAdapter);
        btn_context_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content=edt_context_input.getText().toString().trim();
                if (!content.isEmpty()){
                    ChatMessage msg=new ChatMessage(content,ChatMessage.TYPE_SEND);
                    messages.add(msg);
                    chatListAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    private void initView(){
        ib_context_back = (ImageButton)findViewById(R.id.ib_context_back);
        lv_context_chat = (ListView) findViewById(R.id.lv_context_chat);
        edt_context_input = (EditText) findViewById(R.id.edt_context_input);
        btn_context_send = (Button) findViewById(R.id.btn_context_send);

        ib_context_back.setOnClickListener(this);
        btn_context_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_context_back:
                ChatContextActivity.this.finish();
                break;
            case R.id.btn_context_send:

                break;
        }
    }

    private void submit() {
        String input = edt_context_input.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "input不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private class ChatListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatMessage msg=messages.get(position);
            View view;
            RViewHolder viewHolder;
            if (convertView==null){
                view= LayoutInflater.from(ChatContextActivity.this).inflate(R.layout.chating_list_item,null);
                viewHolder=new RViewHolder();
                viewHolder.leftL=(LinearLayout)view.findViewById(R.id.ll_left_layout);
                viewHolder.rightL=(LinearLayout)view.findViewById(R.id.ll_right_layout);
                viewHolder.leftM=(TextView)view.findViewById(R.id.tv_left_msg);
                viewHolder.rightM=(TextView)view.findViewById(R.id.tv_right_msg);
                viewHolder.hl=(ImageView)view.findViewById(R.id.iv_head_left);
                viewHolder.hr=(ImageView)view.findViewById(R.id.iv_right_head);
                view.setTag(viewHolder);

            }else {
                view=convertView;
                viewHolder=(RViewHolder)view.getTag();
            }
            if (msg.getType()==ChatMessage.TYPE_RECEIVE){
                viewHolder.leftL.setVisibility(View.VISIBLE);
                viewHolder.hl.setVisibility(View.VISIBLE);
                viewHolder.rightL.setVisibility(View.GONE);
                viewHolder.hr.setVisibility(View.GONE);
                viewHolder.leftM.setVisibility(View.VISIBLE);
                viewHolder.leftM.setText(msg.getContent());
            }else if (msg.getType()==ChatMessage.TYPE_SEND){
                viewHolder.rightL.setVisibility(View.VISIBLE);
                viewHolder.hr.setVisibility(View.VISIBLE);
                viewHolder.leftL.setVisibility(View.GONE);
                viewHolder.hl.setVisibility(View.GONE);
                viewHolder.rightM.setText(msg.getContent());
            }
            return view;
        }
    }
    private class RViewHolder {
        LinearLayout leftL,rightL;
        TextView leftM,rightM;
        ImageView hl,hr;
    }
}
