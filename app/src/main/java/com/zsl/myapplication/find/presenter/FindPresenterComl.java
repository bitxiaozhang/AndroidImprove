package com.zsl.myapplication.find.presenter;

import android.os.Handler;
import android.os.Looper;

import com.zsl.myapplication.R;
import com.zsl.myapplication.find.ProgramCourse;
import com.zsl.myapplication.find.view.IFindView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsl on 3/15/16.
 */
public class FindPresenterComl implements IFindViewPresenter {

    private IFindView findView;
    private Handler mhandler;

    public FindPresenterComl(IFindView findView) {
        this.findView = findView;
        mhandler = new Handler(Looper.getMainLooper());
        initData();
    }

    @Override
    public void initData() {
        List<ProgramCourse> mProgramCourList = new ArrayList<>();
        mProgramCourList.add(new ProgramCourse("C++", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Python", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Java", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Object-C", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Swift", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Swift", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Swift", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Swift", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Swift", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Swift", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Swift", R.drawable.ic_launcher));
        mProgramCourList.add(new ProgramCourse("Swift", R.drawable.ic_launcher));

        List<ProgramCourse> mProgramFindToday = new ArrayList<>();
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));
        mProgramFindToday.add(new ProgramCourse("beijing",R.drawable.ic_launcher));

        findView.showCourse(mProgramCourList);
        findView.showCourseToday(mProgramFindToday);
    }
}
