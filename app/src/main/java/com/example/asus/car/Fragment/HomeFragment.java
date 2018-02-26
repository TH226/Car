package com.example.asus.car.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.asus.car.Activity.CarServiceActivity;
import com.example.asus.car.Activity.CarShopActivity;
import com.example.asus.car.Activity.HomeWindow;
import com.example.asus.car.Activity.NewsRemindActivity;
import com.example.asus.car.Activity.NowMarkActivity;
import com.example.asus.car.Activity.TodayJobActivity;
import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/8.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private ViewGroup LLPoint;//小圆点
    private ViewPager VpHeader;//图片
    private ImageView[] tips;//装小圆点的ImageView数组
    private ImageView[] mImageViews;//装ImageView数组
    private int[] imgIdArray; //图片资源id
    private View view;

    private LinearLayout LLHomeCarStation;
    private LinearLayout LLHomeCarCansaleMoney;
    private LinearLayout LLHomeCarDrive;
    private LinearLayout LlCarSevice;
    private LinearLayout LlCarFriend;
    private LinearLayout LlNews;

    private RelativeLayout RlCarshop;
    private RelativeLayout RlTodayJob;
    private RelativeLayout RlNowJifen;

    private ImageView IvYao;
    private ImageView IvErwei;



    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment_layout, container, false);
      // getActivity().startService(new Intent(getActivity(), RocketService.class));
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.home_table);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),HomeWindow.class));
            }
        });
        initView();
        listener();
        initData();


        if (mHandler == null) {
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    int currentItem = VpHeader.getCurrentItem();
                    if (currentItem < (mImageViews.length - 1)) {
                        currentItem++;
                    } else {
                        currentItem = 0;
                    }
                    VpHeader.setCurrentItem(currentItem);
                    setImageBackground(currentItem % mImageViews.length);
                    mHandler.sendEmptyMessageDelayed(0, 2000);
                }
            };
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }

        return view;
    }


    private void initData() {
        LoadPicPoint();
        VpHeader.setAdapter(new MyAdapter());
        VpHeader.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setImageBackground(position % mImageViews.length);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initView() {
        LLPoint = (ViewGroup) view.findViewById(R.id.ll_point);
        VpHeader = (ViewPager) view.findViewById(R.id.vp_header);

        LLHomeCarStation = (LinearLayout) view.findViewById(R.id.ll_home_car_station);
        LLHomeCarCansaleMoney = (LinearLayout) view.findViewById(R.id.ll_home_car_money);
        LLHomeCarDrive = (LinearLayout) view.findViewById(R.id.ll_home_car_drive);
        LlCarSevice = (LinearLayout) view.findViewById(R.id.ll_home_car_service);
        LlCarFriend = (LinearLayout) view.findViewById(R.id.ll_car_friend);
        LlNews = (LinearLayout) view.findViewById(R.id.ll_news);

        RlCarshop = (RelativeLayout) view.findViewById(R.id.rl_car_shop);
        RlTodayJob = (RelativeLayout) view.findViewById(R.id.rl_today_job);
        RlNowJifen = (RelativeLayout) view.findViewById(R.id.rl_now_jifen);

        IvYao = (ImageView) view.findViewById(R.id.img_pho);
        IvErwei = (ImageView) view.findViewById(R.id.img_setting);


        imgIdArray = new int[]{R.mipmap.fourmerchant_image_default, R.mipmap.weixiu_merchant_image_default,
                R.mipmap.zuling_merchant_image_default, R.mipmap.peijian_merchant_image_default};
        tips = new ImageView[imgIdArray.length];

    }

    private void listener() {
        LLHomeCarStation.setOnClickListener(this);
        LLHomeCarCansaleMoney.setOnClickListener(this);
        LLHomeCarDrive.setOnClickListener(this);

        RlCarshop.setOnClickListener(this);
        RlTodayJob.setOnClickListener(this);
        RlNowJifen.setOnClickListener(this);
        LlCarSevice.setOnClickListener(this);
        LlCarFriend.setOnClickListener(this);
        LlNews.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home_car_station:
              // getActivity().stopService(new Intent(getActivity(), RocketService.class));
                showNoCardialog();
                break;
            case R.id.ll_home_car_money:
               // getActivity().stopService(new Intent(getActivity(), RocketService.class));
                showNoCardialog();
                break;
            case R.id.ll_home_car_drive:
               // getActivity().stopService(new Intent(getActivity(), RocketService.class));
                showNoCardialog();
                break;
            case R.id.rl_car_shop:
                startActivity(new Intent(getActivity(),CarShopActivity.class));
            //    getActivity().stopService(new Intent(getActivity(), RocketService.class));
                break;
            case R.id.rl_today_job:
                startActivity(new Intent(getActivity(), TodayJobActivity.class));
                break;
            case R.id.rl_now_jifen:
                startActivity(new Intent(getActivity(), NowMarkActivity.class));
                break;
            case R.id.ll_home_car_service:
                startActivity(new Intent(getActivity(), CarServiceActivity.class));
                break;
            case R.id.ll_car_friend:
                startActivity(new Intent(getActivity(), CarFriendMainFragment.class));
                break;
            case R.id.ll_news:
                startActivity(new Intent(getActivity(), NewsRemindActivity.class));
                break;

        }
    }

    private void showNoCardialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示！");
        builder.setMessage("还未绑定车辆!");
        builder.show();
    }

    private void LoadPicPoint() {
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.mipmap.mvx);
            } else {
                tips[i].setBackgroundResource(R.mipmap.mvy);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                    (new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 8;
            layoutParams.rightMargin = 8;
            LLPoint.addView(imageView, layoutParams);
        }
        //将图片装入数组
        mImageViews = new ImageView[imgIdArray.length];
        for (int i = 0; i < mImageViews.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            mImageViews[i] = imageView;
            imageView.setBackgroundResource(imgIdArray[i]);
        }
    }


    /**
     * viewpager的适配器
     */
    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViews.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViews[position]);
            return mImageViews[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViews[position]);
        }
    }


    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.mipmap.mvx);
            } else {
                tips[i].setBackgroundResource(R.mipmap.mvy);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      //  getActivity().stopService(new Intent(getActivity(), RocketService.class));
    }
}
