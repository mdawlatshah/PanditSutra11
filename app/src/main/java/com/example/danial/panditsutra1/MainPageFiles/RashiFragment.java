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
import com.example.danial.panditsutra1.MainActivity;
import com.example.danial.panditsutra1.R;
import com.example.danial.panditsutra1.RashifalFiles.RashifalActivity;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class RashiFragment extends Fragment implements View.OnClickListener {

    public static String horoSelected;
    public RashiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View InputFragmentView = inflater.inflate(R.layout.fragment_rashi, container, false);

        //loads images from firebase and makes app work faster
        String ariesUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_aries.png?alt=media&token=5493d153-8ef2-4157-8c5d-eaecedc8a942";
        ImageView ariesImg = (ImageView) InputFragmentView.findViewById(R.id.aries);
        Picasso.with(getActivity()).load(ariesUri).fit().centerInside().into(ariesImg);

        String taurusUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_taurus.png?alt=media&token=2ddaf989-9b20-4f89-8d00-a539688d48c3";
        ImageView taurusImg = (ImageView) InputFragmentView.findViewById(R.id.taurus);
        Picasso.with(getActivity()).load(taurusUri).fit().centerInside().into(taurusImg);

        String geminiUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_gemini.png?alt=media&token=a6db6487-01e9-46ce-9472-fc65a31fe54a";
        ImageView geminiImg = (ImageView) InputFragmentView.findViewById(R.id.gemini);
        Picasso.with(getActivity()).load(geminiUri).fit().centerInside().into(geminiImg);

        String cancerUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_cancer.png?alt=media&token=7f2e4dff-48a4-4960-bba9-c106ba684691";
        ImageView cancerImg = (ImageView) InputFragmentView.findViewById(R.id.cancer);
        Picasso.with(getActivity()).load(cancerUri).fit().centerInside().into(cancerImg);

        String leoUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_leo.png?alt=media&token=aa33b296-3b59-40c2-be0f-391b78a82cc7";
        ImageView leoImg = (ImageView) InputFragmentView.findViewById(R.id.leo);
        Picasso.with(getActivity()).load(leoUri).fit().centerInside().into(leoImg);

        String virgoUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_virgo.png?alt=media&token=0791f4e4-302b-476b-8771-a7db5c27b6f9";
        ImageView virgoImg = (ImageView) InputFragmentView.findViewById(R.id.virgo);
        Picasso.with(getActivity()).load(virgoUri).fit().centerInside().into(virgoImg);

        String libraUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_libra.png?alt=media&token=b6137f52-e80a-48e9-9497-0b1b32e2d5d1";
        ImageView libraImg = (ImageView) InputFragmentView.findViewById(R.id.libra);
        Picasso.with(getActivity()).load(libraUri).fit().centerInside().into(libraImg);

        String scorpioUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_scorpio.png?alt=media&token=d456028b-bade-45e9-b9e8-40982f66dd3b";
        ImageView scorpioImg = (ImageView) InputFragmentView.findViewById(R.id.scorpio);
        Picasso.with(getActivity()).load(scorpioUri).fit().centerInside().into(scorpioImg);

        String sagitUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_sagittarius.png?alt=media&token=d3de21d8-5b06-4490-9b53-d9028c1793e2";
        ImageView sagitImg = (ImageView) InputFragmentView.findViewById(R.id.sagittarius);
        Picasso.with(getActivity()).load(sagitUri).fit().centerInside().into(sagitImg);

        String capricornUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_capricorn.png?alt=media&token=015f2d7d-bda5-4b3d-b0d8-219b11ffc4cb";
        ImageView capricornImg = (ImageView) InputFragmentView.findViewById(R.id.capricorn);
        Picasso.with(getActivity()).load(capricornUri).fit().centerInside().into(capricornImg);

        String aquarUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_aquarius.png?alt=media&token=0e268fdb-87f0-44ef-9dd3-05b479c48626";
        ImageView aquarImg = (ImageView) InputFragmentView.findViewById(R.id.aquarius);
        Picasso.with(getActivity()).load(aquarUri).fit().centerInside().into(aquarImg);

        String piscesUri = "https://firebasestorage.googleapis.com/v0/b/panditsutra1.appspot.com/o/AppImages%2Fz_pisces.png?alt=media&token=384f3b18-49ac-43f0-8eab-ac089de445f4";
        ImageView piscesImg = (ImageView) InputFragmentView.findViewById(R.id.pisces);
        Picasso.with(getActivity()).load(piscesUri).fit().centerInside().into(piscesImg);


        ImageView buttonViewAries = (ImageView) InputFragmentView.findViewById(R.id.aries);
        ImageView buttonViewTaurus = (ImageView) InputFragmentView.findViewById(R.id.taurus);
        ImageView buttonViewGemini = (ImageView) InputFragmentView.findViewById(R.id.gemini);
        ImageView buttonViewCancer = (ImageView) InputFragmentView.findViewById(R.id.cancer);
        ImageView buttonViewLeo = (ImageView) InputFragmentView.findViewById(R.id.leo);
        ImageView buttonViewVirgo = (ImageView) InputFragmentView.findViewById(R.id.virgo);
        ImageView buttonViewLibra = (ImageView) InputFragmentView.findViewById(R.id.libra);
        ImageView buttonViewScorpio = (ImageView) InputFragmentView.findViewById(R.id.scorpio);
        ImageView buttonViewSagittarius = (ImageView) InputFragmentView.findViewById(R.id.sagittarius);
        ImageView buttonViewCapricorn = (ImageView) InputFragmentView.findViewById(R.id.capricorn);
        ImageView buttonViewAquarius = (ImageView) InputFragmentView.findViewById(R.id.aquarius);
        ImageView buttonViewPisces = (ImageView) InputFragmentView.findViewById(R.id.pisces);

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
