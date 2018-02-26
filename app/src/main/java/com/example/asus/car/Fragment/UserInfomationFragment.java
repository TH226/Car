package com.example.asus.car.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/9.
 */
public class UserInfomationFragment extends Fragment implements View.OnClickListener{

    private View view;
    private RelativeLayout RlTodayDoneJob;
    private RelativeLayout RlSexChoose;
    private RelativeLayout RlNameSign;

    private ImageView IvPersonPhoto;
    private ImageView IvFriendJudge;

    private TextView TvUserSex;
    private int Index = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_infomation_layout,container,false);
        initView();
        setListener();
        return view;
    }


    private void initView() {
        RlTodayDoneJob = (RelativeLayout) view.findViewById(R.id.rl_today_done_job);
        RlSexChoose = (RelativeLayout) view.findViewById(R.id.rl_sex_choose);
        RlNameSign = (RelativeLayout) view.findViewById(R.id.rl_name_sign);

        IvPersonPhoto = (ImageView) view.findViewById(R.id.iv_person_photo);
        IvFriendJudge = (ImageView) view.findViewById(R.id.iv_friend_judge);

        TvUserSex = (TextView) view.findViewById(R.id.user_sex);


    }
    private void setListener() {
        RlTodayDoneJob.setOnClickListener(this);
        RlSexChoose.setOnClickListener(this);
        RlNameSign.setOnClickListener(this);
        IvPersonPhoto.setOnClickListener(this);
        IvFriendJudge.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_today_done_job:

                break;
            case R.id.rl_sex_choose:
                showDialog();
                break;
            case R.id.rl_name_sign:
                showSignDialog();
                break;
            case R.id.iv_person_photo:
                break;
            case R.id.iv_friend_judge:
                judgeFriend();
                break;
        }
    }

    private boolean flag = false;
    private void judgeFriend() {
        if(!flag){
            IvFriendJudge.setImageResource(R.mipmap.cxz_chat_icon_son);
            flag = true;
        }else {
            IvFriendJudge.setImageResource(R.mipmap.cxz_chat_icon_soff);
            flag = false;
        }

    }

    private void showSignDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view1 = View.inflate(getActivity(),R.layout.dialog_signature,null);
        dialog.setView(view1,0,0,0,0);
        final EditText EtSignature = (EditText) view1.findViewById(R.id.et_signature);
        Button BtnSave = (Button) view1.findViewById(R.id.btn_save);
        Button BtnCancle = (Button) view1.findViewById(R.id.btn_cancle);
        final TextView TvSignature = (TextView) view.findViewById(R.id.tv_signature);

        BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String signature = EtSignature.getText().toString();
              //  Toast.makeText(getActivity(), signature, Toast.LENGTH_SHORT).show();
                TvSignature.setText(signature);
                dialog.dismiss();
            }
        });
        BtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showDialog() {
       AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择性别");
        final String[] sex = {"男","女"};

        builder.setSingleChoiceItems(sex,Index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TvUserSex.setText(sex[which]);
                Index = which;
                dialog.dismiss();
            }
        });
        builder.show();

    }
}
