package Src;

import java.util.ArrayList;

public class EducationPlan {
    //учебный план
    private String name;
    private String startDate;
    private String edPlanCreateDate;
    //стандарт
    private int standartId;
    private String standartCreateDate;
    private int standartOrder;
    private String pathToStandart = null;
    //направление
    private String dirEncr;
    private String dirName;
    //профиль
    private String profName;


    public EducationPlan(ArrayList<ArrayList<String>> rawData) {
        name = rawData.get(0).get(1).replace(".xls", "")
                .split("Название плана")[1].trim();
        startDate = rawData.get(0).get(0).split("Год начала обучения")[1].trim();

        standartId = Integer.parseInt(
                rawData.get(3).get(1).replace("Номер стандарта", "").trim());

        String [] orderRaw = rawData.get(3).get(0).split("от");
        standartOrder = Integer.parseInt(orderRaw[0].replace("Приказ", "").trim());
        edPlanCreateDate = orderRaw[1].trim();

        standartCreateDate = rawData.get(3).get(2).replace("Дата", "").trim();

        dirName = rawData.get(2).get(1).replaceAll("\"", "")
                .replace("Направление:", "").trim();

        dirEncr = rawData.get(2).get(0);

        profName = rawData.get(1).get(0).replaceAll("\"", "").trim();

    }

}
