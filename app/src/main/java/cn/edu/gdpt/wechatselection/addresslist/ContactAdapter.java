package cn.edu.gdpt.wechatselection.addresslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.edu.gdpt.wechatselection.R;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private String[] mContactNames;
    private List<String> mContactList;
    private List<Contact> resultList;
    private List<String> characterList;

    public ContactAdapter(Context context, String[] contactNames) {
        mContext =context;
        mLayoutInflater=LayoutInflater.from(context);
        mContactNames=contactNames;
        handleConact();
    }

    public enum ITEM_TYPE{
        ITEM_TYPE_CHARACTER,
        ITEM_TYPE_CONTACT
    }

    private void handleConact() {
        mContactList =new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        for (int i=0;i<mContactNames.length;i++){
            String pinyin =Utils.getPinYin(mContactNames[i]);
            map.put(pinyin,mContactNames[i]);
            mContactList.add(pinyin);
        }
        Collections.sort(mContactList,new ContactComparator());
        resultList=new ArrayList<>();
        characterList=new ArrayList<>();
        for (int i=0;i<mContactList.size();i++){
            String name=mContactList.get(i);
            String character=(name.charAt(0)+"").toUpperCase(Locale.CANADA.ENGLISH);
            if (!characterList.contains(character)){
                if (character.hashCode()>="A".hashCode() && character.hashCode()<="Z".hashCode()){

                    characterList.add(character);
                    resultList.add(new Contact(character,ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                }else {
                    if (!characterList.contains("#")){
                        characterList.add("#");
                        resultList.add(new Contact("#",ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()));
                    }
                }
            }
            resultList.add(new Contact(map.get(name),ITEM_TYPE.ITEM_TYPE_CONTACT.ordinal()));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i==ITEM_TYPE.ITEM_TYPE_CHARACTER.ordinal()){
            return new CharacterHolder(mLayoutInflater.inflate(R.layout.item_character,viewGroup,false));
        }else {
            return new ContactHolder(mLayoutInflater.inflate(R.layout.item_contact,viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof CharacterHolder) {
            ((CharacterHolder)viewHolder).mTextView.setText(resultList.get(i).getmName());
        }else if (viewHolder instanceof ContactHolder) {
            ((ContactHolder)viewHolder).mTextView.setText(resultList.get(i).getmName());
        }

    }

    @Override
    public int getItemViewType(int position) {
        return resultList.get(position).getmType();
    }

    @Override
    public int getItemCount() {
        return resultList==null ? 0 : resultList.size();
    }

    public class CharacterHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        CharacterHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.character);
        }
    }

    public class ContactHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ContactHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.contact_name);
        }
    }

    public int getScrollPosition(String character){
        if (characterList.contains(character)){
            for (int i=0;i<resultList.size();i++){
                if (resultList.get(i).getmName().equals(character)){
                    return i;
                }
            }
        }
        return -1;
    }
}
