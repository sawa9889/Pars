package Src;

import java.util.ArrayList;

public class Discipline {
    private String disciplineName;
    private ArrayList<Hours> hours = new ArrayList<>();

    public Discipline(ArrayList<ArrayList<String>> rawData) {
        disciplineName = rawData.get(0).get(0);
        for (int i = 3; i < rawData.size(); i++) {
            boolean isExam = rawData.get(i).get(0).toLowerCase().contains("экзамен");
            hours.add(new Hours(i-2,
                    (int) Double.parseDouble(rawData.get(i).get(2)),(int) Double.parseDouble(rawData.get(i).get(3)),
                    (int) Double.parseDouble(rawData.get(i).get(4)),(int) Double.parseDouble(rawData.get(i).get(5)),
                    (int) Double.parseDouble(rawData.get(i).get(6)),(int) Double.parseDouble(rawData.get(i).get(7)),
                    //или экзамен, или зачет
                    isExam, !isExam));
        }
    }


    public String getDisciplineName() { return disciplineName; }


    public ArrayList<Hours> getHours() {
        return hours;
    }
}
