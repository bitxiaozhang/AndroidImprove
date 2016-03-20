package com.zsl.myapplication.find.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsl.myapplication.R;
import com.zsl.myapplication.find.ProgramCourse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zsl on 3/15/16.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<ProgramCourse> programCourseList = new ArrayList<>();
    private LayoutInflater mInflater;

    public GridViewAdapter(Context context, List<ProgramCourse> programCourseList) {
        this.context = context;
        if (programCourseList != null){
            this.programCourseList = programCourseList;
        }
        mInflater = LayoutInflater.from(context);
    }

    public void setProgramCourseList(List<ProgramCourse> programCourseList) {
        this.programCourseList = programCourseList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return programCourseList.size();
    }


    @Override
    public ProgramCourse getItem(int position) {
        return programCourseList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.fragment_find_gridview_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ProgramCourse item = programCourseList.get(position);
        viewHolder.icon_image.setImageResource(item.getIcon());
        viewHolder.name.setText(item.getName());
        return convertView;
    }


    static class ViewHolder{
        @Bind(R.id.program_course_icon)
        ImageView icon_image;
        @Bind(R.id.program_course_name)
        TextView name;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
