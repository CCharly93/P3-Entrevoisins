package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class DetailsNeighbourActivity extends AppCompatActivity {

    //public static final String EXTRA_ID = "com.openclassrooms.entrevoisins.EXTRA_TEXT";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Neighbour neighbour = (Neighbour) intent.getSerializableExtra(DetailsNeighbourActivity.NEIGHBOUR);
        Log.d("mydebug", "DetailsNeighbourActivity.onCreate() Neighbour = " + neighbour.getName());

        //DI.getNeighbourApiService().findNeighbourById(id);
        //if (neighbour != null)
        {
            //this.avatar = neighbour.getAvatarUrl();
            this.titre.setText(neighbour.getName());
            this.name.setText(neighbour.getName());
            this.address.setText(neighbour.getAddress());
            this.phoneNumber.setText(neighbour.getPhoneNumber());
            this.aboutMe.setText(neighbour.getAboutMe());
            this.web.setText("");

            Glide.with(avatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .into(avatar);
            Log.i("mydebug", "DetailNeighbourActivity.onCreate() isFavorite = " + neighbour);

        }
    }

    /*  public static void navigate(Context context, Neighbour neighbour) {
          Long id = neighbour.getId();
          Intent intent = new Intent(context, DetailsNeighbourActivity.class);
          intent.putExtra(DetailsNeighbourActivity.EXTRA_ID, id);
          ActivityCompat.startActivity(context, intent, null);
      }
  */
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
    }
}