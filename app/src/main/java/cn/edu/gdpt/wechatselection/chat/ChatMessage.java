package cn.edu.gdpt.wechatselection.chat;

public class ChatMessage {
    public static final int TYPE_RECEIVE=0;
    public static final int TYPE_SEND=1;
    private String content;
    private int type;

    public ChatMessage(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public static int getTypeReceive() {
        return TYPE_RECEIVE;
    }

    public static int getTypeSend() {
        return TYPE_SEND;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
