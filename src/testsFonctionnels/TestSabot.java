package testsFonctionnels;

import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
import cartes.Type;
import jeu.Sabot;

import java.util.Iterator;

public class TestSabot {

    public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();

        Sabot sabot1 = new Sabot(jeu.donnerCartes());
        while (!sabot1.estVide()) {
            System.out.println("je pioche " + sabot1.piocher());
        }

        Sabot sabot2 = new Sabot(jeu.donnerCartes());
        for (Iterator<Carte> it = sabot2.iterator(); it.hasNext(); ) {
            Carte carte = it.next();
            System.out.println("je pioche " + carte);
            it.remove();
        }

        Sabot sabot3 = new Sabot(jeu.donnerCartes());
        try {
            for (Iterator<Carte> it = sabot3.iterator(); it.hasNext(); ) {
                Carte carte = it.next();
                System.out.println("je pioche " + carte);
                sabot3.piocher();
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }

        Sabot sabot4 = new Sabot(jeu.donnerCartes());
        sabot4.piocher();

        Carte asDuVolant = new Botte(Type.ACCIDENT);
        try {
            for (Iterator<Carte> it = sabot4.iterator(); it.hasNext(); ) {
                Carte carte = it.next();
                System.out.println("je pioche " + carte);
                sabot4.ajouterCarte(asDuVolant);
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }
}