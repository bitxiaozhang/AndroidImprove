package com.zsl.myapplication.find;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Toast;

import com.zsl.myapplication.R;
import com.zsl.myapplication.common.utils.InnerScrollViewMeasureUtils;
import com.zsl.myapplication.common.widget.ScrollViewBottomTop;
import com.zsl.myapplication.common.widget.ScrollViewBottomTop.OnScrollBottomTopListener;
import com.zsl.myapplication.find.adapter.GridViewAdapter;
import com.zsl.myapplication.find.adapter.ListCourseTodayAdapter;
import com.zsl.myapplication.find.presenter.FindPresenterComl;
import com.zsl.myapplication.find.view.IFindView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
@TargetApi(Build.VERSION_CODES.M)
public class FindFragment extends Fragment implements IFindView {
    @Bind(R.id.find_item_grid_view) GridView gridView;
    @Bind(R.id.find_item_list_view) ListView listView;
    @Bind(R.id.root_view) ScrollViewBottomTop rootScrollView;
    @Bind(R.id.top_view) View topView;
    @Bind(R.id.tab_bar)LinearLayout mTabTop;

    private GridViewAdapter mProgramCourseAdapter;
    private ListCourseTodayAdapter mListCourseTodayAdapter;
    private FindPresenterComl findPresenterComl;

    public FindFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find,container,false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        initParam();
    }

    private void initParam(){
        mProgramCourseAdapter = new GridViewAdapter(getActivity().getBaseContext(),null);
        mListCourseTodayAdapter = new ListCourseTodayAdapter(null,getActivity().getBaseContext());
        gridView.setAdapter(mProgramCourseAdapter);
        listView.setAdapter(mListCourseTodayAdapter);
        findPresenterComl = new FindPresenterComl(this);
        rootScrollView.setOnScrollBottomTopListener(new OnScrollBottomTopListener() {
            @Override
            public void onScrollTop() {
                Toast.makeText(getActivity(),"Top",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScrollBottom() {
                Log.e("Scroll","Bottom");
            }

            @Override
            public void onScroll(int scrollY) {
                Log.e("Scroll", "scrollY:" + scrollY);
                if (scrollY>topView.getHeight()){
                    mTabTop.setVisibility(View.VISIBLE);
                }else{
                    mTabTop.setVisibility(View.INVISIBLE);
                }
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }
    @Override
    public void showCourse(List list) {
        mProgramCourseAdapter.setProgramCourseList((List<ProgramCourse>) list);
        mProgramCourseAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCourseToday(List list) {
        mListCourseTodayAdapter.setProgramCourseList((List<ProgramCourse>) list);
        mListCourseTodayAdapter.notifyDataSetChanged();
    }

}
