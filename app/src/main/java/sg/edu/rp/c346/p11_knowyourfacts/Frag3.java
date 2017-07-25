package sg.edu.rp.c346.p11_knowyourfacts;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag3 extends Fragment {
    Button btnImage;
    ImageView imageView;


    public Frag3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_frag3, container, false);
        btnImage = (Button) view.findViewById(R.id.btnChangeImage);
        imageView = (ImageView) view.findViewById(R.id.iv);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imageUrl = "http://square.github.io/picasso/static/sample.png";
                Picasso.with(getActivity()).load(imageUrl).into(imageView);





            }
        });

        return view;
    }
}