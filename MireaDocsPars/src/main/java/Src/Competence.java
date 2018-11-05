package Src;

import java.util.ArrayList;

public class Competence {
    private String competenceName;
    private String competenceDescr;
    private ArrayList <CompetenceDictionary> competenceDictArr = new ArrayList<>();

    public Competence(ArrayList<String> rawData) {
        competenceName = rawData.get(0).trim();
        competenceDescr = rawData.get(1).trim();
        for (int i = 2; i < rawData.size(); i++) {
            competenceDictArr.add(new CompetenceDictionary(rawData.get(i).trim(),
                    rawData.get(++i).trim()));
        }
    }

    public ArrayList<CompetenceDictionary> getCompetenceDictArr() {
        return competenceDictArr;
    }

    public void setCompetenceDictArr(ArrayList<CompetenceDictionary> competenceDictArr) {
        this.competenceDictArr = competenceDictArr;
    }

    public void setCompetenceDict(CompetenceDictionary competenceDict) {
        this.competenceDictArr.add(competenceDict);
    }

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

    public void setCompetence(ArrayList<ArrayList<String>> parse) {
    }
}
