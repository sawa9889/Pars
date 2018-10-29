package src;

public class Hours {
    private int hoursId;
    private int semestr;
    private int lab;
    private int kr;
    private int sr;
    private boolean isEcz;
    private boolean isZachet;

    public Hours(int hoursId, int semestr, int lab,
                 int kr, int sr, boolean isEcz, boolean isZachet) {
        this.hoursId = hoursId;
        this.semestr = semestr;
        this.lab = lab;
        this.kr = kr;
        this.sr = sr;
        this.isEcz = isEcz;
        this.isZachet = isZachet;
    }

    public int getHoursId() {

        return hoursId;
    }

    public void setHoursId(int hoursId) {
        this.hoursId = hoursId;
    }

    public int getSemestr() {
        return semestr;
    }

    public void setSemestr(int semestr) {
        this.semestr = semestr;
    }

    public int getLab() {
        return lab;
    }

    public void setLab(int lab) {
        this.lab = lab;
    }

    public int getKr() {
        return kr;
    }

    public void setKr(int kr) {
        this.kr = kr;
    }

    public int getSr() {
        return sr;
    }

    public void setSr(int sr) {
        this.sr = sr;
    }

    public boolean isEcz() {
        return isEcz;
    }

    public void setEcz(boolean ecz) {
        isEcz = ecz;
    }

    public boolean isZachet() {
        return isZachet;
    }

    public void setZachet(boolean zachet) {
        isZachet = zachet;
    }
}
