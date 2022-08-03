package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsNeighbourActivity extends AppCompatActivity {

    public static final String NEIGHBOUR = "NEIGHBOUR";

    @BindView(R.id.details_avatar)
    ImageView avatar;
    @BindView(R.id.details_titre)
    TextView titre;
    @BindView(R.id.details_name)
    TextView name;
    @BindView(R.id.details_adresse)
    TextView address;
    @BindView(R.id.details_telephone)
    TextView phoneNumber;
    @BindView(R.id.details_web)
    TextView web;
    @BindView(R.id.details_about)
    TextView aboutMe;
    @BindView(R.id.fav_button)
    public FloatingActionButton mButtonFav;
    @BindView(R.id.previous_button)
    public ImageButton mButtonPrevious;

    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);

        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        Intent intent = getIntent();
        Neighbour neighbour = (Neighbour) intent.getSerializableExtra(DetailsNeighbourActivity.NEIGHBOUR);
        Log.d("mydebug", "DetailsNeighbourActivity.onCreate() Neighbour = " + neighbour.getName());

        {
            this.titre.setText(neighbour.getName());
            this.name.setText(neighbour.getName());
            this.address.setText(neighbour.getAddress());
            this.phoneNumber.setText(neighbour.getPhoneNumber());
            this.aboutMe.setText(neighbour.getAboutMe());
            this.web.setText("https://facebook.com/"+neighbour.getName());

            Glide.with(avatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .centerCrop()
                    .into(avatar);
            Log.i("mydebug", "DetailNeighbourActivity.onCreate() isFavorite = " + neighbour);
            mButtonPrevious.setOnClickListener(v -> finish());
            checkNeighbourFavorite(neighbour);
            mButtonFav.setOnClickListener(v -> {
                mApiService.setFavoriteNeighbour(neighbour, !neighbour.getFavorite());
                checkNeighbourFavorite(neighbour);
            });
        }
    }
    public void checkNeighbourFavorite(Neighbour neighbour) {
        mButtonFav.setImageResource(
                !neighbour.getFavorite() ?
                        R.drawable.ic_star_border_white_24dp :
                        R.drawable.ic_star_white_24dp
        );
    }

    public static void navigate(FragmentActivity activity, Neighbour neighbour) {
        Intent anIntent = new Intent(activity, DetailsNeighbourActivity.class);
        anIntent.putExtra(DetailsNeighbourActivity.NEIGHBOUR, (Serializable) neighbour);
        ActivityCompat.startActivity(activity, anIntent, null);


    }

    @Override
    public void onResume(){
        super.onResume();
    }

}