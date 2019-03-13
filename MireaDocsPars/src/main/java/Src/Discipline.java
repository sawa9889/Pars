package Src;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Discipline {
    private Integer disciplineId;
    private String disciplineName;
    private Integer depNumber;
    private String depName;
    private ArrayList<Hours> hours = new ArrayList<>();

    private Integer SR, control, withZET;
    private Integer hoursInZE, hoursExpert, hoursContact;

    public Discipline(ArrayList<ArrayList<String>> rawData) {
        disciplineName = rawData.get(0).get(1).trim();
        depNumber = (int) Double.parseDouble(rawData.get(rawData.size()-1).get(0).replace("Номер кафедры", "").trim());
        depName = rawData.get(rawData.size()-1).get(1);
        SR = (int) Double.parseDouble(rawData.get(2).get(0).trim().split(" ")[1]);
        control = (int) Double.parseDouble(getValueForHour(rawData.get(2),"Контроль "));
        withZET = (int) Double.parseDouble(getValueForHour(rawData.get(2),"По ЗЕТ "));
        hoursInZE = (int) Double.parseDouble(getValueForHour(rawData.get(2),"Часов в з.е. "));
        hoursExpert = (int) Double.parseDouble(getValueForHour(rawData.get(2),"Итог: экспертное "));
        hoursContact = (int) Double.parseDouble(getValueForHour(rawData.get(2),"Контакт часы "));
        for (int i = 3; i < rawData.size()-1; i++) {
            boolean isExam = rawData.get(i).get(0).toLowerCase().contains("экзамен");
            hours.add(new Hours(Integer.parseInt(rawData.get(i).get(0).substring(0,1)),
                    (int) Double.parseDouble(rawData.get(i).get(2)),(int) Double.parseDouble(rawData.get(i).get(3)),
                    (int) Double.parseDouble(rawData.get(i).get(4)),(int) Double.parseDouble(rawData.get(i).get(5)),
                    (int) Double.parseDouble(rawData.get(i).get(6)),(int) Double.parseDouble(rawData.get(i).get(7)),
                    //или экзамен, или зачет
                    isExam, !isExam));
        }
        System.out.println("Закончил "+disciplineName);
    }

    public void Display(){
        System.out.println(disciplineName);
        System.out.println(depNumber +' '+ depName);
        for (Hours h:hours){
            System.out.println(h.getControl() + ' ' +
                    h.getKsr() + " , " +
                    h.getLab() + " , " +
                    h.getLectures() + " , " +
                    h.getPractice() + " , "+
                    h.getSemestr() + " , " +
                    h.getSrs() + " , " +
                    h.isEcz() + " , " +
                    h.isZachet());
        }
    }

    private String getValueForHour(ArrayList<String> rawData,String toFind){
        for (String s: rawData) {
            if (s.contains(toFind)){
                return s.trim().replace(toFind, "");
            }
        }
        return "0.0";
    }


    public Integer getSR() {
        return SR;
    }

    public Integer getControl() {
        return control;
    }

    public Integer getWithZET() {
        return withZET;
    }

    public Integer getHoursInZE() {
        return hoursInZE;
    }

    public Integer getHoursExpert() {
        return hoursExpert;
    }

    public Integer getHoursContact() {
        return hoursContact;
    }

    public Integer getDepNumber() {

        return depNumber;
    }

    public String getDepName() {
        return depName;
    }

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() { return disciplineName; }


    public ArrayList<Hours> getHours() {
        return hours;
    }
}
