package com.example.danial.panditsutra1.MainPageFiles;
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
        ArrayList<ImageView> img = new ArrayList<>();



//        panditTypeSelected = "vstul";
//       textViewVastul.setOnClickListener(this);
//        imageViewVastul.setOnClickListener(this);
//
//        panditTypeSelected = "astrologal";
//        textViewaAtrologal.setOnClickListener(this);
//        imageViewAstrologal.setOnClickListener(this);
//        textViewSastri.setOnClickListener(this);
//        imageViewSastri.setOnClickListener(this);
//        textViewByias.setOnClickListener(this);
//        imageViewByias.setOnClickListener(this);
//        textViewMohant.setOnClickListener(this);
//        imageViewMohant.setOnClickListener(this);
//        textViewJyotish.setOnClickListener(this);
//        imageViewJyotish.setOnClickListener(this);


        return InputFragmentView;
    }




    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), UserViewPanditsActivity.class);
        startActivity(intent);

//        in case they have different layouts ...
        switch (view.getId()) {
            case R.id.vastulImage:
                panditTypeSelected = "vastul";
//                startActivity(new Intent(PanditsFragment.this, UserViewPanditsActivity.class));
                break;
//            case R.id.vastul:
//                startActivity(intent);
//                panditTypeSelected = "vastul";
//                break;
//            case R.id.astrologalImage:
//                panditTypeSelected = "astrologal";
//                startActivity(intent);
//                break;
//            case R.id.astrologal:
//                    panditTypeSelected = "astrologal";
//                startActivity(intent);
//                break;
//            case R.id.sastriImage:
//                panditTypeSelected = "sastri";
//                startActivity(intent);
//                break;
//            case R.id.sastri:
//                panditTypeSelected = "sastri";
//                startActivity(intent);
//                break;
//            case R.id.byiasImage:
//                panditTypeSelected = "byias";
//                startActivity(intent);
//                break;
//            case R.id.byias:
//                panditTypeSelected = "byias";
//                startActivity(intent);
//                break;
//            case R.id.mohantImage:
//                panditTypeSelected = "mohant";
//                startActivity(intent);
//                break;
//            case R.id.mohant:
//                panditTypeSelected = "mohant";
//                startActivity(intent);
//                break;
//            case R.id.jyotishImage:
//                panditTypeSelected = "jyotish";
//                startActivity(intent);
//                break;
//            case R.id.jyotish:
//                panditTypeSelected = "jyotish";
//                startActivity(intent);
//                break;
        }


    }

}
