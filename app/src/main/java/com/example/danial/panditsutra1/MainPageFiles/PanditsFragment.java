package com.example.danial.panditsutra1.MainPageFiles;
import com.example.danial.panditsutra1.PanditsClasses.PanditsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danial.panditsutra1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PanditsFragment extends Fragment implements View.OnClickListener {


    public PanditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View InputFragmentView = inflater.inflate(R.layout.fragment_pandit, container, false);


        TextView textViewVastul = (TextView) InputFragmentView.findViewById(R.id.vastul);
        ImageView imageViewVastul = (ImageView) InputFragmentView.findViewById(R.id.vastulImage);

        TextView textViewaAtrologal = (TextView) InputFragmentView.findViewById(R.id.astrologal);
        ImageView imageViewAstrologal = (ImageView) InputFragmentView.findViewById(R.id.astrologalImage);

        TextView textViewSastri = (TextView) InputFragmentView.findViewById(R.id.sastri);
        ImageView imageViewSastri = (ImageView) InputFragmentView.findViewById(R.id.sastriImage);

        TextView textViewByias = (TextView) InputFragmentView.findViewById(R.id.byias);
        ImageView imageViewByias = (ImageView) InputFragmentView.findViewById(R.id.byiasImage);

        TextView textViewMohant = (TextView) InputFragmentView.findViewById(R.id.mohant);
        ImageView imageViewMohant = (ImageView) InputFragmentView.findViewById(R.id.mohantImage);

        TextView textViewJyotish = (TextView) InputFragmentView.findViewById(R.id.jyotish);
        ImageView imageViewJyotish = (ImageView) InputFragmentView.findViewById(R.id.jyotishImage);

        textViewVastul.setOnClickListener(this);
        imageViewVastul.setOnClickListener(this);
        textViewaAtrologal.setOnClickListener(this);
        imageViewAstrologal.setOnClickListener(this);
        textViewSastri.setOnClickListener(this);
        imageViewSastri.setOnClickListener(this);
        textViewByias.setOnClickListener(this);
        imageViewByias.setOnClickListener(this);
        textViewMohant.setOnClickListener(this);
        imageViewMohant.setOnClickListener(this);
        textViewJyotish.setOnClickListener(this);
        imageViewJyotish.setOnClickListener(this);


        return InputFragmentView;
    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), PanditsActivity.class);
        startActivity(intent);

        //in case they have different layouts ...
//        switch (view.getId()) {
//            case R.id.vastulImage:
//                startActivity(intent);
//                break;
//            case R.id.vastul:
//                startActivity(intent);
//                break;
//            case R.id.astrologalImage:
//                startActivity(intent);
//                break;
//            case R.id.astrologal:
//                startActivity(intent);
//                break;
//            case R.id.sastriImage:
//                startActivity(intent);
//                break;
//            case R.id.sastri:
//                startActivity(intent);
//                break;
//            case R.id.byiasImage:
//                startActivity(intent);
//                break;
//            case R.id.byias:
//                startActivity(intent);
//                break;
//            case R.id.mohantImage:
//                startActivity(intent);
//                break;
//            case R.id.mohant:
//                startActivity(intent);
//                break;
//            case R.id.jyotishImage:
//                startActivity(intent);
//                break;
//            case R.id.jyotish:
//                startActivity(intent);
//                break;
//        }


    }
}
