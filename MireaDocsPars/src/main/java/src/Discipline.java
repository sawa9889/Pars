package src;

public class Discipline {
    private String disciplineName;
    private String disciplineDescr;

    public Discipline(String disciplineName, String disciplineDescr) {
        this.disciplineName = disciplineName;
        this.disciplineDescr = disciplineDescr;
    }

    public String getDisciplineName() {

        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getDisciplineDescr() {
        return disciplineDescr;
    }

    public void setDisciplineDescr(String disciplineDescr) {
        this.disciplineDescr = disciplineDescr;
    }
}
