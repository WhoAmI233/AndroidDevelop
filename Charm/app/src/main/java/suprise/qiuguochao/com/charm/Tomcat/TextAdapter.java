package suprise.qiuguochao.com.charm.Tomcat;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import suprise.qiuguochao.com.charm.R;

/**
 * Created by qiuguochao on 2017/2/24.
 */

public class TextAdapter extends BaseAdapter {

    private List<ListItemData> mLists;
    private Context mContext;
    private RelativeLayout layout;

    public TextAdapter(List<ListItemData> lists, Context mContext) {
        this.mLists = lists;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取或创建viewholder
        ViewHolder holder=null;
        if (convertView==null){//第一次肯定为RECEIVER
            holder=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.leftitem,null);
            holder.content= (TextView) convertView.findViewById(R.id.tv);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
            if (mLists.get(position).getFlag() == ListItemData.SEND){
                convertView=View.inflate(mContext,R.layout.rightitem,null);
                holder.content= (TextView) convertView.findViewById(R.id.tv);

                convertView.setTag(holder);
            }else {
                convertView=View.inflate(mContext,R.layout.leftitem,null);
                holder.content= (TextView) convertView.findViewById(R.id.tv);

                convertView.setTag(holder);
            }
        }

        //获取当前item数据
        ListItemData listData=mLists.get(position);

        //显示数据
        if (listData.getFlag()==ListItemData.RECEIVER){
            holder.content.setText(listData.getContent());
        }else {
            holder.content.setText("\""+mLists.get(position).getContent()+"\"");//如果是发送的信息，加上双引号
        }
        holder.content.setTextColor(Color.BLUE);

        //返回view
        return convertView;
    }

    private class ViewHolder{
        private TextView content;
    }


}
