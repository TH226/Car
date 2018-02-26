package com.example.asus.car.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/8.
 */
public class MyUserFragment extends Fragment implements View.OnClickListener{
    private RelativeLayout RlUserInfomation;
    private RelativeLayout RlCarInfomation;

    private TextView TvCarInfomation;
    private TextView TvCarInfomationLine;
    private TextView TvUserInfomation;
    private TextView TvUserInfomationLine;

    private Fragment UserInformation;
    private Fragment CarInformation;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment_layout,container,false);

        initView();

        initEvent();

        setSelect(0);

        return view;
    }



    private void initView() {
        RlUserInfomation = (RelativeLayout) view.findViewById(R.id.rl_user_infomation);
        RlCarInfomation = (RelativeLayout) view.findViewById(R.id.rl_car_infomation);

        TvCarInfomation = (TextView) view.findViewById(R.id.tv_car_infomation);
        TvCarInfomationLine = (TextView) view.findViewById(R.id.tv_car_infomation_line);
        TvUserInfomation = (TextView) view.findViewById(R.id.tv_user_infomation);
        TvUserInfomationLine = (TextView) view.findViewById(R.id.tv_user_infomation_line);
    }


    private void initEvent() {

        RlUserInfomation.setOnClickListener(this);
        RlCarInfomation.setOnClickListener(this);
    }


    private void setSelect(int i){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);

        switch (i) {
            case 0:
                if(UserInformation==null) {
                    UserInformation = new UserInfomationFragment();
                    transaction.add(R.id.frame,UserInformation);
                } else {
                    transaction.show(UserInformation);
                }
                TvUserInfomation.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvUserInfomationLine.setBackgroundColor(this.getResources().getColor(R.color.colorbottomlight));

                break;
            case 1:
                if(CarInformation==null) {
                    CarInformation = new CarInfomationFragment();
                    transaction.add(R.id.frame,CarInformation);
                } else {
                    transaction.show(CarInformation);
                }
                TvCarInfomation.setTextColor(this.getResources().getColor(R.color.colorbottomlight));
                TvCarInfomationLine.setBackgroundColor(this.getResources().getColor(R.color.colorbottomlight));
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction ) {
        if(UserInformation!=null) {
            transaction.hide(UserInformation);
        }
        if(CarInformation!=null) {
            transaction.hide(CarInformation);
        }
    }

    @Override
    public void onClick(View v) {
        resetImgs();
        switch (v.getId()) {
            case R.id.rl_user_infomation:
                setSelect(0);
                break;
            case R.id.rl_car_infomation:
                setSelect(1);
                break;

        }
    }

    /**
     * 置暗
     */
    private void resetImgs() {
        TvUserInfomation.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorbottomgray));
        TvUserInfomationLine.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomgray));
        TvCarInfomation.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorbottomgray));
        TvCarInfomationLine.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.colorbottomgray));
    }
}
