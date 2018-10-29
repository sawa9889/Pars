package src;

public class Competence {
    private String competenceName;
    private String competenceDescr;

    public Competence(String competenceName, String competenceDescr) {
        this.competenceName = competenceName;
        this.competenceDescr = competenceDescr;
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    public String getCompetenceDescr() {
        return competenceDescr;
    }

    public void setCompetenceDescr(String competenceDescr) {
        this.competenceDescr = competenceDescr;
    }
}
