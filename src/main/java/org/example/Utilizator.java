package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/*exceptie modelata de mine in cazul in care un tip de cerere nu se preteaza
 unui anumit tip de utilizator*/
class ExceptieSolicitare extends Exception {
    public ExceptieSolicitare(String message) {
        super(message);
    }
}

public abstract class Utilizator {
    /*functionalitatile unui utilizator*/

    /*scrierea textului unei cereri implementata pentru fiecare tip de utilizator*/
    public abstract String textCerere(Cerere.TipCerere tipCerere);

    /*crearea unei cereri*/
    public Cerere creareCerere(String tipCerere, int prioritate, String data, Utilizator utilizator) {
        Cerere.TipCerere tip = Cerere.TipCerere.valueOf(tipCerere.toUpperCase().replace(" ", "_"));
        Date date = Cerere.parsareText(data);
        return new Cerere(tip, date, prioritate, utilizator);
    }

    /*functie pentru scrierea mesajului de exceptie in fisierul de output*/
    public void afisareMesajExceptie(String mesaj) {
        try (FileWriter fw = new FileWriter(ManagementPrimarie.antetOutput + ManagementPrimarie.file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(mesaj);
            out.flush();
        } catch (IOException e) {
            System.out.println("Nu s-a putut scrie in fisierul de output");
        }
    }

    /*adaugare cerere*/
    public abstract void adaugareCerere(Cerere cerere);

    /*retragerea unei cereri*/
    public abstract void retragereCerere(Date data);

    /*afisare cereri solutionate*/
    public abstract void cereriSolutionate(Date data);

    /*afisare cereri finalizate*/
    public abstract void afisareCereriFinalizate();

    /*afisare cereri in asteptare*/
    public abstract void cereriInAsteptare();
}
