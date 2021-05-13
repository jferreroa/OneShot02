package com.example.oneshot001;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.oneshot001.databinding.FragmentProBinding;
import com.google.android.material.snackbar.Snackbar;

public class ProFragment extends Fragment {

    private FragmentProBinding binding;
    private TextView cambiarISO;
    private Integer listaISO[] = new Integer[]{20,50,80,100,160,200,250,320,400,800};
    private double listaAperture[] = new double[]{1,1.2,1.4,1.6,1.8,2,2.2,2.4,2.5,2.8,3.2,3.5,4,4.5,4.8,
                                                    5,5.6,6.3,6.7,7.1,8,9,9.5,10,11,13,14,16,18,19,20,22,
                                                    25,27,29,32,26};
    private Integer iteratorAperture = 0;
    private Integer iterator = 0;

    private String speedPro(double lux, int ISO, double fstop){
        String[] speeds = new String[] {"30X","60","125","250","500"};
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
        binding = FragmentProBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.Aperture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iteratorAperture==(listaAperture.length)){
                    iteratorAperture = 0;
                }
                binding.Aperture.setText(Double.toString(listaAperture[iterator]));
                iterator++;

                double x = Double.parseDouble(binding.Aperture.getText().toString());
                double y = MainActivity.light;
                Integer z = Integer.parseInt(binding.ISO.getText().toString());
                binding.Speed.setText(speedPro(y,z,x));
            }
        });

        binding.masISO.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(iterator==(listaISO.length)){
                    iterator = 0;
                }
                binding.ISO.setText(listaISO[iterator].toString());
                iterator++;

                double x = Double.parseDouble(binding.Aperture.getText().toString());
                double y = MainActivity.light;
                Integer z = Integer.parseInt(binding.ISO.getText().toString());
                binding.Speed.setText(speedPro(y,z,x));
            }
        });

        binding.menosISO.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(iterator==0){
                    iterator = (listaISO.length-1);
                }
                binding.ISO.setText(listaISO[iterator].toString());
                iterator--;


                double x = Double.parseDouble(binding.Aperture.getText().toString());
                double y = MainActivity.light;
                Integer z = Integer.parseInt(binding.ISO.getText().toString());
                binding.Speed.setText(speedPro(y,z,x));
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

                double x = Double.parseDouble(binding.Aperture.getText().toString());
                Integer z = Integer.parseInt(binding.ISO.getText().toString());
                binding.Speed.setText(speedPro(y,z,x));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}