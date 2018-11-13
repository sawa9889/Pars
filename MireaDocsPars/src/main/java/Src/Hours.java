package Src;

public class Hours {
    private int practice;
    private int hoursId;
    private int semestr;
    private int lectures;
    private int lab;
    private int ksr;
    private int srs;
    private int control;

    public int getPractice() {
        return practice;
    }

    public int getHoursId() {
        return hoursId;
    }

    public int getSemestr() {
        return semestr;
    }

    public int getLectures() {
        return lectures;
    }

    public int getLab() {
        return lab;
    }

    public int getKsr() {
        return ksr;
    }

    public int getSrs() {
        return srs;
    }

    public int getControl() {
        return control;
    }

    public int isEcz() {
        if (isEcz) {
            return 1;
        } else {
            return 0;
        }
    }

    public int isZachet() {
        if (isZachet) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean isEcz;
    private boolean isZachet;

    public Hours(int semestr, int lectures, int lab, int practice,
                 int ksr, int srs, int control, boolean isEcz, boolean isZachet) {
        this.semestr = semestr;
        this.lectures = lectures;
        this.lab = lab;
        this.practice = practice;
        this.ksr = ksr;
        this.srs = srs;
        this.control = control;
        this.isEcz = isEcz;
        this.isZachet = isZachet;
    }
}
