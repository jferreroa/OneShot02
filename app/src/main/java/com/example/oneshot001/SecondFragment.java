package com.example.oneshot001;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.oneshot001.databinding.FragmentSecondBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Vector;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private TextView cambiarISO;
    private Integer listaISO[] = new Integer[]{20,50,80,100,160,200,250,320,400,800};
    private Integer iterator = 0;

    private String speedPortrait(double lux, int ISO){
        String[] speeds = new String[] {"30X","60","125","250","500"};
        double fstop = 3.5;
        double Constant = 12.5;
        double speed;

        speed = (fstop*fstop*Constant)/(lux*ISO);
        System.out.println(speed);

        if(speed>=0.03) {
            return speeds[0];
        }
        else if(speed>=0.016) {
            return speeds[1];
        }
        else if(speed>=0.008) {
            return speeds[2];
        }
        else if(speed>=0.004) {
            return speeds[3];
        }
        else if(speed>=0.002) {
            return speeds[4];
        }
        else {
            return speeds[4];
        }

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.masISO.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(iterator==(listaISO.length)){
                    iterator = 0;
                }
                binding.ISO.setText(listaISO[iterator].toString());
                iterator++;

                double y = MainActivity.light;
                Integer z = Integer.parseInt(binding.ISO.getText().toString());
                binding.Speed.setText(speedPortrait(y,z));
            }
        });

        binding.menosISO.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(iterator==0){
                    iterator = (listaISO.length-1);
                }
                binding.ISO.setText(listaISO[iterator].toString());
                iterator--;

                double y = MainActivity.light;
                Integer z = Integer.parseInt(binding.ISO.getText().toString());
                binding.Speed.setText(speedPortrait(y,z));
            }
        });

        binding.switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double y = MainActivity.light;

                if(y < 10){
                    Snackbar.make(view, "Men it's dark here\nYou better take that 800 ISO film", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if(y > 400){
                    Snackbar.make(view, "Wow baby that's a sunny day\nLet's try some low ISO 70s overexposure", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                Integer z = Integer.parseInt(binding.ISO.getText().toString());
                binding.Speed.setText(speedPortrait(y,z));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}