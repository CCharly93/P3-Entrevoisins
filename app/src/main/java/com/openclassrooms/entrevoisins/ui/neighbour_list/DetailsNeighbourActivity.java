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

    //public static final String EXTRA_ID = "com.openclassrooms.entrevoisins.EXTRA_TEXT";
    public static final String NEIGHBOUR = "NEIGHBOUR";

    //@BindView : va récupérer et diffuser la vue correspondante avec l'ID
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

    //Initialisation de la librairie ButterKnife
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);

        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        //Récupération du voisin sélectionné grâce à l'intent : passage d'argument entre 2 activités
        Intent intent = getIntent();
        Neighbour neighbour = (Neighbour) intent.getSerializableExtra(DetailsNeighbourActivity.NEIGHBOUR);
        Log.d("mydebug", "DetailsNeighbourActivity.onCreate() Neighbour = " + neighbour.getName());

        {
            //chargement des informations du voisin sélectionné dans les composants de l'activity details
            this.titre.setText(neighbour.getName());
            this.name.setText(neighbour.getName());
            this.address.setText(neighbour.getAddress());
            this.phoneNumber.setText(neighbour.getPhoneNumber());
            this.aboutMe.setText(neighbour.getAboutMe());
            this.web.setText("https://facebook.com/"+neighbour.getName());

            //Chargement de l'avatar du voisin avec un rognage de l'image : CenterCrop
            Glide.with(avatar.getContext())
                    .load(neighbour.getAvatarUrl())
                    .centerCrop()
                    .into(avatar);
            Log.i("mydebug", "DetailNeighbourActivity.onCreate() isFavorite = " + neighbour);
            //Lors d'un clic sur le bouton retour, j'appelle la méthode android finish qui permets de revenir à l'activity précédente
            mButtonPrevious.setOnClickListener(v -> finish());
            //Lors de la création de l'activity je vérifie si mon voisin est en favoris ou pas (Si oui, je change l'icône sur l'activity)
            checkNeighbourFavorite(neighbour);
            //Lors d'un clic sur le bouton favoris, j'appelle mon apiService pour demander la mise en favoris du voisin affiché
            mButtonFav.setOnClickListener(v -> {
                mApiService.setFavoriteNeighbour(neighbour, !neighbour.getFavorite());
                //Lorsque j'ai modifié si le voisin était en favoris ou pas, je remets à jour l'icone étoile (de favoris)
                checkNeighbourFavorite(neighbour);
            });
        }
    }
    public void checkNeighbourFavorite(Neighbour neighbour) {
        //Changement de l'étoile en fonction de si le voisin est en favoris ou pas
        mButtonFav.setImageResource(
                !neighbour.getFavorite() ?
                        R.drawable.ic_star_border_white_24dp :
                        R.drawable.ic_star_white_24dp
        );
    }

    //Permet d'atteindre la destination DetailsNeighbourActivity
    public static void navigate(FragmentActivity activity, Neighbour neighbour) {
        Intent anIntent = new Intent(activity, DetailsNeighbourActivity.class);
        anIntent.putExtra(DetailsNeighbourActivity.NEIGHBOUR, (Serializable) neighbour);
        ActivityCompat.startActivity(activity, anIntent, null);


    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
    }

}