package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter<position> extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    //Cette méthode est appellée par Android pour créer le fragment correspondant à l'onglet n° position
    public Fragment getItem(int position) {
        //Premier appel (onglet liste de tous voisins, position = 0) : NeighbourFragment.newInstance(false)
        //Deuxième appel (onglet liste de favoris, position = 1): NeighbourFragment.newInstance(True)
        return NeighbourFragment.newInstance(position == 1);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}