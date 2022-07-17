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
        //neighbours = la liste de tous les voisins
        //pour chaque voisin présent dans la liste de tous les voisins
        for (Neighbour currentNeighbour : neighbours) {
            //Si le voisin courant est favoris
            if (currentNeighbour.getFavorite().equals(Boolean.TRUE)) {
                //Alors j'ajoute le voisin à la liste des voisins favoris à retourner
                favoriteNeighbours.add(currentNeighbour);
            }
        }
        //On retourne la liste des voisins favoris
        return favoriteNeighbours;
    }

    @Override
    public void setFavoriteNeighbour(Neighbour neighbour, boolean favorite) {
        //pour chaque voisin présent dans la liste de tous les voisins
        for (Neighbour currentNeighbour : neighbours) {
            // Subtilité : du fait que l'objet a été dupliqué lors du passage via l'intent / et la sérialisation
            //On retrouve le voisin original à partir de la copie, pour mettre à jour la source
            if(currentNeighbour.equals(neighbour)){
                //Je mets en favoris le voisin original
                currentNeighbour.setFavorite(favorite);
                //Je mets en favoris la copie du voisin
                neighbour.setFavorite(favorite);
            }
        }
    }

}
