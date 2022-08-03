package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {

        List<Neighbour> favoriteNeighbours = new ArrayList<>();
        for (Neighbour currentNeighbour : neighbours) {
            if (currentNeighbour.getFavorite().equals(Boolean.TRUE)) {
                favoriteNeighbours.add(currentNeighbour);
            }
        }
        return favoriteNeighbours;
    }

    @Override
    public void setFavoriteNeighbour(Neighbour neighbour, boolean favorite) {
        for (Neighbour currentNeighbour : neighbours) {
            if(currentNeighbour.equals(neighbour)){
                currentNeighbour.setFavorite(favorite);
                neighbour.setFavorite(favorite);
            }
        }
    }

}
