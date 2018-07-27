package com.example.danial.panditsutra1.MainPageFiles;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.danial.panditsutra1.PanditsClasses.UserViewPanditsActivity;
import com.example.danial.panditsutra1.R;
import com.example.danial.panditsutra1.RashifalFiles.RashifalActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class RashiFragment extends Fragment implements View.OnClickListener {

    ImageView buttonViewAries;
    ImageView buttonViewTaurus;
    ImageView buttonViewGemini;
    ImageView buttonViewCancer;
    ImageView buttonViewLeo;
    ImageView buttonViewVirgo;
    ImageView buttonViewLibra;
    ImageView buttonViewScorpio;
    ImageView buttonViewSagittarius;
    ImageView buttonViewCapricorn;
    ImageView buttonViewAquarius;
    ImageView buttonViewPisces;

    public static String horoSelected;

    public RashiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View InputFragmentView = inflater.inflate(R.layout.fragment_rashi, container, false);


        buttonViewAries = (ImageView) InputFragmentView.findViewById(R.id.aries);
         buttonViewTaurus = (ImageView) InputFragmentView.findViewById(R.id.taurus);
         buttonViewGemini = (ImageView) InputFragmentView.findViewById(R.id.gemini);
         buttonViewCancer = (ImageView) InputFragmentView.findViewById(R.id.cancer);
         buttonViewLeo = (ImageView) InputFragmentView.findViewById(R.id.leo);
         buttonViewVirgo = (ImageView) InputFragmentView.findViewById(R.id.virgo);
         buttonViewLibra = (ImageView) InputFragmentView.findViewById(R.id.libra);
         buttonViewScorpio = (ImageView) InputFragmentView.findViewById(R.id.scorpio);
         buttonViewSagittarius = (ImageView) InputFragmentView.findViewById(R.id.sagittarius);
         buttonViewCapricorn = (ImageView) InputFragmentView.findViewById(R.id.capricorn);
         buttonViewAquarius = (ImageView) InputFragmentView.findViewById(R.id.aquarius);
         buttonViewPisces = (ImageView) InputFragmentView.findViewById(R.id.pisces);

        buttonViewAries.setOnClickListener(this);
        buttonViewTaurus.setOnClickListener(this);
        buttonViewGemini.setOnClickListener(this);
        buttonViewCancer.setOnClickListener(this);
        buttonViewLeo.setOnClickListener(this);
        buttonViewVirgo.setOnClickListener(this);
        buttonViewLibra.setOnClickListener(this);
        buttonViewScorpio.setOnClickListener(this);
        buttonViewSagittarius.setOnClickListener(this);
        buttonViewCapricorn.setOnClickListener(this);
        buttonViewAquarius.setOnClickListener(this);
        buttonViewPisces.setOnClickListener(this);

        return InputFragmentView;
    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), RashifalActivity.class);

        switch (v.getId()) {
            case R.id.aries:
                horoSelected = "Aries";
                startActivity(intent);
                break;
            case R.id.taurus:
                startActivity(intent);
                horoSelected = "Taurus";
                break;
            case R.id.gemini:
                horoSelected = "Gemini";
                startActivity(intent);
                break;
            case R.id.cancer:
                horoSelected = "Cancer";
                startActivity(intent);
                break;
            case R.id.leo:
                horoSelected = "Leo";
                startActivity(intent);
                break;
            case R.id.virgo:
                horoSelected = "Virgo";
                startActivity(intent);
                break;
            case R.id.libra:
                horoSelected = "Libra";
                startActivity(intent);
                break;
            case R.id.scorpio:
                horoSelected = "Scorpion";
                startActivity(intent);
                break;
            case R.id.sagittarius:
                horoSelected = "Sagittarius";
                startActivity(intent);
                break;
            case R.id.capricorn:
                horoSelected = "Capricon";
                startActivity(intent);
                break;
            case R.id.aquarius:
                horoSelected = "Aquarius";
                startActivity(intent);
                break;
            case R.id.pisces:
                horoSelected = "Pisces";
                startActivity(intent);
                break;





        }
    }
}
