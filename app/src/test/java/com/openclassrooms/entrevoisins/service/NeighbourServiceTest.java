package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }
    @Test
    //Test qui vérifie que lorsqu'un voisin est mis en favoris, ma liste de voisins favoris n'est plus vide
    public void getFavoriteNeighboursListNotEmpty(){
        //Je récupère le premier voisin de ma liste depuis mon ApiService
        Neighbour neighbour = service.getNeighbours().get(0);
        //J'appelle mon service en lui demandant de passer un voisin particulier en favoris
        service.setFavoriteNeighbour(neighbour,true);
        //On s'attends à ce que la liste de voisin favoris récupérée depuis le service ne soit pas vide et par conséquent que la méthode isEmpty retourne false
        //On a bien false qui est rénvoyé par le isEmpty et par conséquent l'assertion fausse est vraie
        assertFalse(service.getFavoriteNeighbours().isEmpty());
    }

    @Test
    // test qui vérifie la bonne modification d'un voisin en favori, en simulant un clonage (ce qui se passe avec l'intent et le serializable)
    public void enableFavoriteNeighbourWithSuccess() {
        //Je récupère le premier voisin de ma liste depuis mon ApiService
        Neighbour originalNeighbour = service.getNeighbours().get(0);
        // simuler un clonage de l'objet, qui se produit lorsque l'on utilise l'intent serializable
        Neighbour clonedNeighbour = new Neighbour(
                originalNeighbour.getId(), // seul l'ID est réellement important car testé dans la méthode equals()
                originalNeighbour.getName(), originalNeighbour.getAvatarUrl(), originalNeighbour.getAddress(),
                originalNeighbour.getPhoneNumber(), originalNeighbour.getAboutMe()
        );
        //On demande la mise en favoris via le voisin clôné
        service.setFavoriteNeighbour(clonedNeighbour, true);
        //Le voisin original a bien sa propriété favoris à vrai
        assertTrue(originalNeighbour.getFavorite());
        // vérifier également que l'objet correspond à celui retourné par la liste de favoris
        assertTrue(originalNeighbour.equals(service.getFavoriteNeighbours().get(0)));
    }

    @Test
    //Test qui vérifie la désactivation d'un voisin qui est en favoris
    public void disableFavoriteNeighbourWithSuccess(){
        //Je récupère le voisin n°3 de ma liste depuis mon ApiService
        Neighbour aNeighbour = service.getNeighbours().get(2);
        //Mise en favoris du voisin
        service.setFavoriteNeighbour(aNeighbour, true);
        //La liste de voisins en favoris n'est plus vide
        assertFalse(service.getFavoriteNeighbours().isEmpty());
        //On désactive la mise en favoris de ce même voisin
        service.setFavoriteNeighbour(aNeighbour, false);
        //On vérifie que la liste de favoris de contient plus le voisin
        assertFalse(service.getFavoriteNeighbours().contains(aNeighbour));
        //On vérifie qu'il n'y a plus de voisins en favoris
        assertTrue(service.getFavoriteNeighbours().isEmpty());

    }
}
