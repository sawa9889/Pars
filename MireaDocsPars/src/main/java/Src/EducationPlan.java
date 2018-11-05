package Src;

public class EducationPlan {
    //учебный план
    private String name;
    private String startDate;
    //стандарт
    private int standartId;
    private String standartCreateDate;
    private String standartOrder;
    private String pathToStandart;
    //направление
    private int dirEncr;
    private String dirName;
    //профиль
    private String profName;

    public EducationPlan(String name, String startDate,
                         int standartId, String standartCreateDate,
                         String standartOrder, String pathToStandart,
                         int dirEncr, String dirName, String profName) {
        this.name = name;
        this.startDate = startDate;
        this.standartId = standartId;
        this.standartCreateDate = standartCreateDate;
        this.standartOrder = standartOrder;
        this.pathToStandart = pathToStandart;
        this.dirEncr = dirEncr;
        this.dirName = dirName;
        this.profName = profName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getStandartId() {
        return standartId;
    }

    public void setStandartId(int standartId) {
        this.standartId = standartId;
    }

    public String getStandartCreateDate() {
        return standartCreateDate;
    }

    public void setStandartCreateDate(String standartCreateDate) {
        this.standartCreateDate = standartCreateDate;
    }

    public String getStandartOrder() {
        return standartOrder;
    }

    public void setStandartOrder(String standartOrder) {
        this.standartOrder = standartOrder;
    }

    public String getPathToStandart() {
        return pathToStandart;
    }

    public void setPathToStandart(String pathToStandart) {
        this.pathToStandart = pathToStandart;
    }

    public int getDirEncr() {
        return dirEncr;
    }

    public void setDirEncr(int dirEncr) {
        this.dirEncr = dirEncr;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }
}
