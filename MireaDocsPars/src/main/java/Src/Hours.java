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
