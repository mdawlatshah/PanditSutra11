package com.example.danial.panditsutra1.MainPageFiles;
import com.example.danial.panditsutra1.AfterLogIn;
import com.example.danial.panditsutra1.PanditsClasses.UserViewPanditsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danial.panditsutra1.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PanditsFragment extends Fragment implements View.OnClickListener {

    TextView textViewVastul;
    ImageView imageViewVastul;
    TextView textViewaAtrologal;
    ImageView imageViewAstrologal;
    TextView textViewSastri;
    ImageView imageViewSastri;
    TextView textViewByias;
    ImageView imageViewByias;
    TextView textViewMohant;
    ImageView imageViewMohant;
    TextView textViewJyotish;
    ImageView imageViewJyotish;

    public  static  String panditTypeSelected;
    Intent intent = new Intent();
    public PanditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View InputFragmentView = inflater.inflate(R.layout.fragment_pandit, container, false);


         textViewVastul = (TextView) InputFragmentView.findViewById(R.id.vastul);
         imageViewVastul = (ImageView) InputFragmentView.findViewById(R.id.vastulImage);

         textViewaAtrologal = (TextView) InputFragmentView.findViewById(R.id.astrologal);
         imageViewAstrologal = (ImageView) InputFragmentView.findViewById(R.id.astrologalImage);

         textViewSastri = (TextView) InputFragmentView.findViewById(R.id.sastri);
         imageViewSastri = (ImageView) InputFragmentView.findViewById(R.id.sastriImage);

         textViewByias = (TextView) InputFragmentView.findViewById(R.id.byias);
         imageViewByias = (ImageView) InputFragmentView.findViewById(R.id.byiasImage);

         textViewMohant = (TextView) InputFragmentView.findViewById(R.id.mohant);
         imageViewMohant = (ImageView) InputFragmentView.findViewById(R.id.mohantImage);

         textViewJyotish = (TextView) InputFragmentView.findViewById(R.id.jyotish);
         imageViewJyotish = (ImageView) InputFragmentView.findViewById(R.id.jyotishImage);




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

        Intent intent = new Intent(getActivity(), UserViewPanditsActivity.class);




//        in case they have different layouts ...
        switch (view.getId()) {
            case R.id.vastulImage:
                panditTypeSelected = "vastu";
                startActivity(intent);
                break;
            case R.id.vastul:
                startActivity(intent);
                panditTypeSelected = "vastu";
                break;
            case R.id.astrologalImage:
                panditTypeSelected = "astrologal";
                startActivity(intent);
                break;
            case R.id.astrologal:
                    panditTypeSelected = "astrologal";
                startActivity(intent);
                break;
            case R.id.sastriImage:
                panditTypeSelected = "sastri";
                startActivity(intent);
                break;
            case R.id.sastri:
                panditTypeSelected = "sastri";
                startActivity(intent);
                break;
            case R.id.byiasImage:
                panditTypeSelected = "byias";
                startActivity(intent);
                break;
            case R.id.byias:
                panditTypeSelected = "byias";
                startActivity(intent);
                break;
            case R.id.mohantImage:
                panditTypeSelected = "mohant";
                startActivity(intent);
                break;
            case R.id.mohant:
                panditTypeSelected = "mohant";
                startActivity(intent);
                break;
            case R.id.jyotishImage:
                panditTypeSelected = "jyotish";
                startActivity(intent);
                break;
            case R.id.jyotish:
                panditTypeSelected = "jyotish";
                startActivity(intent);
                break;
        }


    }

}
