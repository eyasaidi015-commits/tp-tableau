package dictionnaire;

public class Mot_dict {
    private String mot;
    private String definition;
    public Mot_dict(String mot, String definition) {
        this.mot = mot;
        this.definition = definition;
    }
    public String getMot() {
        return mot;
    }
    public String getDefinition() {
        return definition;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }
    public void setMot(String mot) {
        this.mot = mot;
    }
    public boolean synonyme(String autreDefinition) {
        return this.definition.compareTo(autreDefinition) == 0;
    }
    public static void main(String[] args) {
        Mot_dict mot1 = new Mot_dict("pc", "Machine");
        System.out.println("Mot : " + mot1.getMot());
        System.out.println("Definition : " + mot1.getDefinition());
        mot1.setDefinition("Machine qui traite des informations");
        System.out.println("Nouvelle definition : " + mot1.getDefinition());
        System.out.println(mot1.synonyme("Machine qui traite des informations"));
    }
}