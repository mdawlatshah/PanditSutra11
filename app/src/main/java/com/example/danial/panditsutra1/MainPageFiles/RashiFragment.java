package com.example.danial.panditsutra1.MainPageFiles;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.danial.panditsutra1.R;
import com.example.danial.panditsutra1.RashifalFiles.RashifalActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class RashiFragment extends Fragment implements View.OnClickListener {


    public RashiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View InputFragmentView = inflater.inflate(R.layout.fragment_rashi, container, false);


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
        startActivity(intent);
    }
}
