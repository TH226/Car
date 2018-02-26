package com.example.asus.car.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.car.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/10/8.
 */
public class DataFragment extends Fragment implements View.OnClickListener{

    private RelativeLayout DayData;
    private RelativeLayout WeekData;
    private RelativeLayout MonthData;

    private TextView IvDayData;
    private TextView IvWeekData;
    private TextView IvMonthData;

    private TextView TvDayData;
    private TextView TvWeekData;
    private TextView TvMonthData;
    private View view;

    private ViewPager viewPager;
    private PagerAdapter mAdapter;
    private List<View> mViews = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.data_fragment_layout,container,false);

        initView();

        initData();



        return view;
    }



    private void initView() {
        DayData = (RelativeLayout) view.findViewById(R.id.rl_day_data);
        WeekData = (RelativeLayout) view.findViewById(R.id.rl_week_data);
        MonthData = (RelativeLayout) view.findViewById(R.id.rl_month_data);

        IvDayData = (TextView) view.findViewById(R.id.iv_day_data);
        IvWeekData = (TextView) view.findViewById(R.id.iv_week_data);
        IvMonthData = (TextView) view.findViewById(R.id.iv_month_data);

        TvDayData = (TextView) view.findViewById(R.id.tv_day_data);
        TvWeekData = (TextView) view.findViewById(R.id.tv_week_data);
        TvMonthData = (TextView) view.findViewById(R.id.tv_month_data);

        viewPager = (ViewPager) view.findViewById(R.id.data_viewPager);

        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View Daydata = mInflater.inflate(R.layout.day_data,null);//实例化布局文件，将布局文件转化为View
        View Weekdata = mInflater.inflate(R.layout.week_data,null);
        View Monthdata = mInflater.inflate(R.layout.month_data,null);

        mViews.add(Daydata);//添加入数据源
        mViews.add(Weekdata);
        mViews.add(Monthdata);

        mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                return view;
            }
        };

        viewPager.setAdapter(mAdapter);

    }

    private void initData() {
         setListener();


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = viewPager.getCurrentItem();//得到当前页面
                 resetImg();
                switch (currentItem){
                    case 0:
                        IvDayData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));//将图标点亮
                        TvDayData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));
                        break;
                    case 1:
                        IvWeekData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));//将图标点亮
                        TvWeekData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));
                        break;
                    case 2:
                        IvMonthData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));//将图标点亮
                        TvMonthData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setListener() {
        DayData.setOnClickListener(this);
        WeekData.setOnClickListener(this);
        MonthData.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()){
            case R.id.rl_day_data:
                viewPager.setCurrentItem(0);//将ViewPager置到第一个界面
               IvDayData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));//将图标点亮
                 TvDayData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));
                break;
            case R.id.rl_week_data:
                viewPager.setCurrentItem(1);
               IvWeekData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));//将图标点亮
              TvWeekData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));
                break;
            case R.id.rl_month_data:
                viewPager.setCurrentItem(2);
                IvMonthData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));//将图标点亮
                TvMonthData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorbottomlight));
                break;
        }
    }

    private void resetImg(){
        IvDayData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomgray));
        TvDayData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorgray));
        IvWeekData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomgray));
        TvWeekData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorgray));
        IvMonthData.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomgray));
        TvMonthData.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorgray));

    }
}
