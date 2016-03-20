package com.zsl.myapplication.find.adapter;

import android.content.Context;
import android.util.Log;
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
 * Created by zsl on 3/16/16.
 */
public class ListCourseTodayAdapter extends BaseAdapter {

    private Context context;
    private List<ProgramCourse> programCourseList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public ListCourseTodayAdapter(List<ProgramCourse> programCourseList, Context context) {

        if (programCourseList != null){
            this.programCourseList = programCourseList;
        }
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setProgramCourseList(List<ProgramCourse> programCourseList) {
        this.programCourseList = programCourseList;
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
        Log.e("getView","text");
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.fragment_program_course_item_list,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProgramCourse programCourse = programCourseList.get(position);
        viewHolder.iconImage.setImageResource(programCourse.getIcon());
        viewHolder.textView.setText(programCourse.getName());
        return convertView;
    }
    static class ViewHolder{
        @Bind(R.id.today_course_icon) ImageView iconImage;
        @Bind(R.id.today_course_name) TextView textView;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
