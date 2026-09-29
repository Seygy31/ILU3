package jeu;

import cartes.Carte;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Sabot implements Iterable<Carte> {
    private final Carte[] cartes;
    private int nbCartes;
    private int nombreOperations = 0;
    
    private Iterator<Carte> iterateurPioche;

    public Sabot(Carte[] cartes) {
        this.cartes = cartes;
        this.nbCartes = cartes.length;
        this.iterateurPioche = iterator(); 
    }

    public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(Carte carte) {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Capacité maximale du sabot atteinte.");
        }
        cartes[nbCartes++] = carte;
        nombreOperations++;
    }

    public Carte piocher() {
        if (!iterateurPioche.hasNext()) {
            throw new NoSuchElementException("Le sabot est vide.");
        }
        Carte carte = iterateurPioche.next();
        iterateurPioche.remove();
        return carte;
    }

    @Override
    public Iterator<Carte> iterator() {
        return new IterateurSabot();
    }

    private class IterateurSabot implements Iterator<Carte> {
        private int indiceCourant = 0;
        private boolean nextEffectue = false;
        private int nombreOperationsReference = nombreOperations;

        private void verifierConcurrence() {
            if (nombreOperations != nombreOperationsReference) {
                throw new ConcurrentModificationException("Modification concurrente du sabot détectée.");
            }
        }

        @Override
        public boolean hasNext() {
            return indiceCourant < nbCartes;
        }

        @Override
        public Carte next() {
            verifierConcurrence();
            if (!hasNext()) {
                throw new NoSuchElementException("Aucune carte suivante dans le sabot.");
            }
            Carte c = cartes[indiceCourant++];
            nextEffectue = true;
            return c;
        }

        @Override
        public void remove() {
            verifierConcurrence();
            if (!nextEffectue) {
                throw new IllegalStateException("Appel à remove() impossible sans appel préalable à next().");
            }
            for (int i = indiceCourant - 1; i < nbCartes - 1; i++) {
                cartes[i] = cartes[i + 1];
            }
            cartes[--nbCartes] = null;
            indiceCourant--;
            nextEffectue = false;

            nombreOperations++;
            nombreOperationsReference++;
        }
    }
}