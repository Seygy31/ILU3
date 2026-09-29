package cartes;

import java.util.Iterator;

public class JeuDeCartes {
    private Configuration[] typesDeCartes = new Configuration[] {
        new Configuration(new Borne(25), 10),
        new Configuration(new Borne(50), 10),
        new Configuration(new Borne(75), 10),
        new Configuration(new Borne(100), 12),
        new Configuration(new Borne(200), 4),
        new Configuration(new Parade(Type.FEU), 14),
        new Configuration(new FinLimite(), 6),
        new Configuration(new Parade(Type.ESSENCE), 6),
        new Configuration(new Parade(Type.CREVAISON), 6),
        new Configuration(new Parade(Type.ACCIDENT), 6),
        new Configuration(new Attaque(Type.FEU), 5),
        new Configuration(new DebutLimite(), 4),
        new Configuration(new Attaque(Type.ESSENCE), 3),
        new Configuration(new Attaque(Type.CREVAISON), 3),
        new Configuration(new Attaque(Type.ACCIDENT), 3),
        new Configuration(new Botte(Type.FEU), 1),
        new Configuration(new Botte(Type.ESSENCE), 1),
        new Configuration(new Botte(Type.CREVAISON), 1),
        new Configuration(new Botte(Type.ACCIDENT), 1)
    };

    public String affichageJeuDeCartes() {
        StringBuilder sb = new StringBuilder();
        for (Configuration config : typesDeCartes) {
            sb.append(config.getNbExemplaires())
              .append(" ")
              .append(config.getCarte())
              .append("\n");
        }
        return sb.toString();
    }

    public boolean checkCount() {
        Carte[] toutesLesCartes = donnerCartes();
        
        // 1. On vérifie le nombre total de cartes (106)
        if (toutesLesCartes.length != 106) {
            return false;
        }

        // 2. On vérifie que le nombre réel de chaque type correspond à la configuration
        for (Configuration config : typesDeCartes) {
            int count = 0;
            for (Carte c : toutesLesCartes) {
                if (c.equals(config.getCarte())) {
                    count++;
                }
            }
            if (count != config.getNbExemplaires()) {
                return false;
            }
        }
        return true;
    }

    public Carte[] donnerCartes() {
        Carte[] cartes = new Carte[106];
        int index = 0;
        for (Configuration config : typesDeCartes) {
            for (int i = 0; i < config.getNbExemplaires(); i++) {
                cartes[index++] = config.getCarte();
            }
        }
        return cartes;
    }

    private static class Configuration {
        private Carte carte;
        private int nbExemplaires;

        public Configuration(Carte carte, int nbExemplaires) {
            this.carte = carte;
            this.nbExemplaires = nbExemplaires;
        }

        public Carte getCarte() {
            return carte;
        }

        public int getNbExemplaires() {
            return nbExemplaires;
        }
    }
}