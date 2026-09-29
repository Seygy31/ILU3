package testsFonctionnels;

import java.util.Iterator;

import cartes.Botte;
import cartes.Carte;
import cartes.JeuDeCartes;
import jeu.Sabot;

public class TestSabot {
    JeuDeCartes jeu = new JeuDeCartes();
    Sabot sabot = new Sabot(jeu.donnerCartes());

    // 4.2.a 
    public void questionA() {
        while (!sabot.estVide()) {
            Carte carte = sabot.piocher();
            System.out.println("Je pioche " + carte);
        }
    }

    // 4.2.b 
    public void questionB() {
        for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
            System.out.println("Je pioche " + iterator.next());
            iterator.remove();
        }
    }

    // 4.2.c 
    public void questionC() {
        System.out.println("--- Test 1 : piocher() dans la boucle ---");
        try {
            for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
                Carte carte = iterator.next();
                System.out.println("Je pioche " + carte);
                iterator.remove();
                sabot.piocher(); 
            }
        } catch (Exception e) {
            System.out.println("Exception capturée : " + e);
        }

        System.out.println("\n--- Test 2 : ajouterCarte() dans la boucle ---");

        Carte cartePiochee = sabot.piocher();
        System.out.println("Je pioche hors boucle : " + cartePiochee);

        try {
            for (Iterator<Carte> iterator = sabot.iterator(); iterator.hasNext();) {
                Carte carte = iterator.next();
                System.out.println("Je pioche " + carte);
                sabot.ajouterCarte(new Botte(cartes.Type.ACCIDENT)); 
            }
        } catch (Exception e) {
            System.out.println("Exception capturée : " + e);
        }

        Iterator<Carte> iterator = sabot.iterator();
        System.out.println("\nLa pioche contient encore des cartes ? " + iterator.hasNext());
    }

    public static void main(String[] args) {
        TestSabot testPioche = new TestSabot();

        System.out.println("=== QUESTION 4.2.a ===");
        testPioche.questionA();

        testPioche = new TestSabot();
        System.out.println("\n=== QUESTION 4.2.b ===");
        testPioche.questionB();

        testPioche = new TestSabot();
        System.out.println("\n=== QUESTION 4.2.c ===");
        testPioche.questionC();
    }
}