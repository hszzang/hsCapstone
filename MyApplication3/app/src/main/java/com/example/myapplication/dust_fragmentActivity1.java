package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class dust_fragmentActivity1 extends Fragment {

    public static Fragment newInstance() {
        dust_fragmentActivity1 fragment1 = new dust_fragmentActivity1();

        return fragment1;
    }
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.dust_fragment1,container,false);

            return view;
        }
}
