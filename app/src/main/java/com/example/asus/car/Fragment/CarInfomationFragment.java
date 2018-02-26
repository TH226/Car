package com.example.asus.car.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asus.car.Activity.AddCarActivity;
import com.example.asus.car.R;

/**
 * Created by asus on 2016/10/9.
 */
public class CarInfomationFragment extends Fragment implements View.OnClickListener{

    private View view;
    private Button BtnAddCar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.car_infomation_layout,container,false);
        BtnAddCar = (Button) view.findViewById(R.id.btn_add_car);
        BtnAddCar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_car:
                startActivity(new Intent(getActivity(), AddCarActivity.class));
                break;
        }
    }
}
