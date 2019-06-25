package cn.edu.gdpt.wechatselection;

public class Item {
    private int iconId;
    private String icName;

    public Item(){
        super();
    }

    public Item(int iconId, String icName) {
        this.iconId = iconId;
        this.icName = icName;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getIcName() {
        return icName;
    }

    public void setIcName(String icName) {
        this.icName = icName;
    }
}
