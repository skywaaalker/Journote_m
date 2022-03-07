package com.example.journote.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.journote.bean.JournoteBean;
import com.example.journote.R;
import com.example.journote.event.StartUpdateJournoteEvent;
import com.example.journote.utils.GetDate;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class JournoteAdapter extends RecyclerView.Adapter<JournoteAdapter.JournoteViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<JournoteBean> mJournoteBeanList;
    private int mEditPosition = -1;

    public JournoteAdapter(Context context, List<JournoteBean> mJournoteBeanList){
        mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mJournoteBeanList = mJournoteBeanList;
    }
    @Override
    public JournoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new JournoteViewHolder(mLayoutInflater.inflate(R.layout.part_show_one_journote, parent, false));
    }
    @Override
    public void onBindViewHolder(final JournoteViewHolder holder, final int position){
        String dateSystem = GetDate.getDate().toString();
        holder.mTvTitle.setText(mJournoteBeanList.get(position).getTitle());
        holder.mTvContent.setText(mJournoteBeanList.get(position).getContent());
        holder.mTvDate.setText(mJournoteBeanList.get(position).getDate());
        if(mJournoteBeanList.get(position).getLabel()==0){
            holder.mTvLabel.setText("");
        }else{
            holder.mTvLabel.setText(mJournoteBeanList.get(position).getLabel()+"");
        }

        if(mJournoteBeanList.get(position).getHasnoti() == 0) {
            holder.mIvIfnoti.setVisibility(View.INVISIBLE);
        }
        else {
            holder.mIvIfnoti.setVisibility(View.VISIBLE);
        }
        holder.mLlItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mEditPosition != position){
                    notifyItemChanged(mEditPosition);
                }
                mEditPosition = position;
                EventBus.getDefault().post(new StartUpdateJournoteEvent(position));
            }
        });
    }

    public int getItemCount(){return mJournoteBeanList.size();}
    public static class JournoteViewHolder extends RecyclerView.ViewHolder{
        TextView mTvTitle;
        TextView mTvContent;
        TextView mTvDate;
        TextView mTvLabel;
        ImageView mIvIfnoti;
        View mVLine;
        LinearLayout mLlList;
        LinearLayout mLlItem;
        LinearLayout mLlTitle;

        JournoteViewHolder(View view){
            super(view);
            mTvTitle = (TextView) view.findViewById(R.id.main_tv_title);
            mTvContent = (TextView) view.findViewById(R.id.main_tv_content);
            mTvDate = (TextView) view.findViewById(R.id.main_tv_date);
            mTvLabel =(TextView) view.findViewById(R.id.main_tv_label);
            mIvIfnoti = (ImageView) view.findViewById(R.id.main_iv_if_noti);
            mVLine = (View) view.findViewById(R.id.main_v_line);
            mLlList = (LinearLayout) view.findViewById(R.id.item_ll_list);
            mLlItem = (LinearLayout) view.findViewById(R.id.item_ll);
            mLlTitle = (LinearLayout) view.findViewById(R.id.main_ll_title);
        }
    }
}
