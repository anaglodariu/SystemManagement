package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Cerere implements Comparable<Cerere> {

    enum TipCerere {
        INLOCUIRE_BULETIN,
        INREGISTRARE_VENIT_SALARIAL,
        INLOCUIRE_CARNET_DE_SOFER,
        INLOCUIRE_CARNET_DE_ELEV,
        CREARE_ACT_CONSTITUTIV,
        REINNOIRE_AUTORIZATIE,
        INREGISTRARE_CUPOANE_DE_PENSIE
    };

    TipCerere tip;
    Date data;
    int prioritate;
    Utilizator utilizator;

    public Cerere(TipCerere tip, Date data, int prioritate, Utilizator utilizator) {
        this.tip = tip;
        this.data = data;
        this.prioritate = prioritate;
        this.utilizator = utilizator;
    }

    public Date getData() {
        return data;
    }

    public TipCerere getTip() {
        return tip;
    }

    public int getPrioritate() {
        return prioritate;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    /*functie statica care primeste data depunerii cererii ca string si mi-o intoarce de tipul Date*/
    public static Date parsareText(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            return format.parse(date);
        } catch (NullPointerException | IllegalArgumentException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*functie care ia campul data al cererii si mi-l returneaza ca string*/
    public String parsareData() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            return format.format(data);
        } catch (NullPointerException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*am suprascris functia compareTo din interfata Comparable pentru a pune in ordinea dorita cererile in birou*/
    /*deci, clasa Cerere implementeaza interfata Comparable*/
    @Override
    public int compareTo(Cerere o) {
        if (this.prioritate > o.prioritate) {
            return -1;
        } else if (this.prioritate < o.prioritate) {
            return 1;
        } else {
            if (this.data.after(o.data)) {
                return 1;
            } else if (this.data.before(o.data)) {
                return -1;
            } else {
                return 0;
            }
        }
    }








}
