package Src;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EducationPlan {
    //учебный план
    private String name;
    private Date startDate;
    private Date edPlanCreateDate;
    //стандарт
    private int standartId;
    private Date standartCreateDate;
    private int standartOrder;
    private String pathToStandart = null;
    //направление
    private String dirEncr;
    private String dirName;
    //профиль
    private String profName;
    //Какие-то новые строки
    private String Kafedra;
    private String KafedraName;
    private String Kvalification;
    private String ProdTraining;
    private String TypeOfEducation;
    private String Years;

    public String getKafedra() {
        return Kafedra;
    }

    public String getKafedraName() {
        return KafedraName;
    }

    public String getKvalification() {
        return Kvalification;
    }

    public String getProdTraining() {
        return ProdTraining;
    }

    public String getTypeOfEducation() {
        return TypeOfEducation;
    }

    public String getYears() {
        return Years;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEdPlanCreateDate() {
        return edPlanCreateDate;
    }

    public int getStandartId() {
        return standartId;
    }

    public Date getStandartCreateDate() {
        return standartCreateDate;
    }

    public int getStandartOrder() {
        return standartOrder;
    }

    public String getPathToStandart() {
        return pathToStandart;
    }

    public String getDirEncr() {
        return dirEncr;
    }

    public String getDirName() {
        return dirName;
    }

    public String getProfName() {
        return profName;
    }

    public EducationPlan(ArrayList<ArrayList<String>> rawData) {
        name = rawData.get(0).get(1).replace(".xls", "")
                .split("Название плана")[1].trim();
        try {
            startDate = new Date( new SimpleDateFormat("yyyy").parse(rawData.get(0).get(0).split("Год начала обучения")[1].trim()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        standartId = Integer.parseInt(
                rawData.get(3).get(1).replace("Номер стандарта", "").trim());

        String [] orderRaw = rawData.get(3).get(0).split("от");
        standartOrder = Integer.parseInt(orderRaw[0].replace("Приказ", "").trim());
        try {
            edPlanCreateDate = new Date( new SimpleDateFormat("dd.mm.yyyy").parse(orderRaw[1].trim()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            standartCreateDate = new Date( new SimpleDateFormat("dd.mm.yyyy").parse(rawData.get(3).get(2).replace("Дата", "").trim()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dirName = rawData.get(2).get(1).replaceAll("\"", "")
                .replace("Направление:", "").trim();

        dirEncr = rawData.get(2).get(0);

        profName = rawData.get(1).get(0).replaceAll("\"", "").trim();


        Kafedra = rawData.get(4).get(0);
        KafedraName = rawData.get(4).get(1);
        Kvalification = rawData.get(4).get(2);
        ProdTraining = rawData.get(4).get(3);
        TypeOfEducation = rawData.get(4).get(4);
        Years = rawData.get(4).get(5);
    }

}
