package dictionnaire;

public class dict {
    private int nb_mots;
    private Mot_dict[] Dict;
    private String Nom;
    public dict(String nom, int taille) {
        this.Nom = nom;
        this.Dict = new Mot_dict[taille];
        this.nb_mots = 0;
    }
    public dict(String nom) {
        this(nom, 100);
    }
    public int getNb_mots() {
        return nb_mots;
    }
    public String getNom() {
        return Nom;
    }
    public void Ajouter_Mot(Mot_dict m) {
        if (nb_mots == Dict.length) {
            System.out.println("Dictionnaire plein, impossible d'ajouter : " + m.getMot());
            return;
        }
        int i = nb_mots - 1;
        while (i >= 0 && Dict[i].getMot().compareTo(m.getMot()) > 0) {
            Dict[i + 1] = Dict[i];
            i--;
        }
        Dict[i + 1] = m;
        nb_mots++;
    }
    public void Trier() {
        for (int i = 1; i < nb_mots; i++) {
            Mot_dict cle = Dict[i];
            int j = i - 1;
            while (j >= 0 && Dict[j].getMot().compareTo(cle.getMot()) > 0) {
                Dict[j + 1] = Dict[j];
                j--;
            }
            Dict[j + 1] = cle;
        }
    }
    public void Supprimer_Mot(Mot_dict m) {
        int pos = -1;
        for (int i = 0; i < nb_mots; i++) {
            if (Dict[i].getMot().compareTo(m.getMot()) == 0) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("Mot introuvable : " + m.getMot());
            return;
        }
        for (int i = pos; i < nb_mots - 1; i++) {
            Dict[i] = Dict[i + 1];
        }
        Dict[nb_mots - 1] = null;
        nb_mots--;
    }
    public String Recherche_dicho(String mot) {
        int debut = 0;
        int fin = nb_mots - 1;
        while (debut <= fin) {
            int milieu = (debut + fin) / 2;
            int cmp = Dict[milieu].getMot().compareTo(mot);
            if (cmp == 0) {
                return Dict[milieu].getDefinition();
            } else if (cmp < 0) {
                debut = milieu + 1;
            } else {
                fin = milieu - 1;
            }
        }
        return null;
    }
    public void Lister_dictionnaire() {
        System.out.println("Dictionnaire : " + Nom + " (" + nb_mots + " mots)");
        for (int i = 0; i < nb_mots; i++) {
            System.out.println(Dict[i].getMot() + " : " + Dict[i].getDefinition());
        }
    }
    public int Nombre_synonyme(Mot_dict m) {
        int compteur = 0;
        for (int i = 0; i < nb_mots; i++) {
            if (Dict[i].getMot().compareTo(m.getMot()) != 0
                    && Dict[i].synonyme(m.getDefinition())) {
                compteur++;
            }
        }
        return compteur;
    }
    public static void main(String[] args) {
        dict d = new dict("Informatique", 10);
        Mot_dict m1 = new Mot_dict("pc", "Machine qui traite des informations");
        Mot_dict m2 = new Mot_dict("ordinateur", "Machine qui traite des informations");
        Mot_dict m3 = new Mot_dict("clavier", "Périphérique d'entrée");
        Mot_dict m4 = new Mot_dict("souris", "Périphérique de pointage");
        d.Ajouter_Mot(m1);
        d.Ajouter_Mot(m2);
        d.Ajouter_Mot(m3);
        d.Ajouter_Mot(m4);
        d.Lister_dictionnaire();
        System.out.println("Définition de clavier : " + d.Recherche_dicho("clavier"));
        System.out.println("Synonymes de pc : " + d.Nombre_synonyme(m1));
        d.Supprimer_Mot(m4);
        d.Lister_dictionnaire();
    }
}