package org.example;

import java.io.*;
import java.util.*;

public class Angajat extends Utilizator {
    private String nume;
    private String compania;

    /*mentinerea unor colectii cu cererile solutionate sau in asteptare*/
    private Set<Cerere> cereriInAsteptare;
    private Set<Cerere> cereriSolutionate;

    public Angajat(String nume, String compania) {
        this.nume = nume;
        this.compania = compania;
        this.cereriInAsteptare = new TreeSet<>(Comparator.comparing(Cerere::getData));
        this.cereriSolutionate = new TreeSet<>(Comparator.comparing(Cerere::getData));
    }

    public String getNume() {
        return nume;
    }

    public String getCompania() {
        return compania;
    }

    /*creare text cerere tinand cont de tipul de cerere cerut si de tipul utilizatorului*/
    @Override
    public String textCerere(Cerere.TipCerere tipCerere) {
        StringBuffer mesaj = new StringBuffer("Utilizatorul de tip angajat nu poate inainta o cerere de tip ");
        try {
            switch (tipCerere) {
                case INREGISTRARE_CUPOANE_DE_PENSIE:
                    mesaj.append("inregistrare cupoane de pensie");
                    throw new ExceptieSolicitare(mesaj.toString());
                case REINNOIRE_AUTORIZATIE:
                    mesaj.append("reinnoire autorizatie");
                    throw new ExceptieSolicitare(mesaj.toString());
                case CREARE_ACT_CONSTITUTIV:
                    mesaj.append("creare act constitutiv");
                    throw new ExceptieSolicitare(mesaj.toString());
                case INLOCUIRE_CARNET_DE_ELEV:
                    mesaj.append("inlocuire carnet de elev");
                    throw new ExceptieSolicitare(mesaj.toString());
                default:
                    return "Subsemnatul " + nume + ", angajat la compania " + compania + ", va rog sa-mi aprobati urmatoarea solicitare: "
                            + tipCerere.toString().replace("_", " ").toLowerCase();
            }
        } catch (ExceptieSolicitare e) {
                afisareMesajExceptie(e.getMessage());
        }
        return null;
    }

    /*daca o cerere este adaugata o introducem in colectia de cereri in asteptare*/
    @Override
    public void adaugareCerere(Cerere cerere) {
        cereriInAsteptare.add(cerere);
    }

    /*daca o cerere e retrasa o eliminam din colectia de cereri in asteptare*/
    @Override
    public void retragereCerere(Date data) {
        for (Cerere cerere : cereriInAsteptare) {
            if (cerere.getData().equals(data)) {
                cereriInAsteptare.remove(cerere);
                return;
            }
        }
    }

    /*daca o cerere este solutionata este eliminata din colectia de cereri in asteptare
     * si adaugata in cea de cereri solutionate*/
    @Override
    public void cereriSolutionate(Date data) {
        for (Cerere cerere : cereriInAsteptare) {
            if (cerere.getData().equals(data)) {
                cereriInAsteptare.remove(cerere);
                cereriSolutionate.add(cerere);
                return;
            }
        }
    }

    /*scriere cereri finalizate in fisierul de output*/
    @Override
    public void afisareCereriFinalizate() {
        /*creare fisier de output daca nu exista*/
        File outputFile = new File(ManagementPrimarie.antetOutput + ManagementPrimarie.file);
        try {
            if (outputFile.createNewFile()) {
                System.out.println("Fisier de output creat: " + outputFile.getName());
            } else {
                System.out.println("Fisierul de output exista deja.");
            }
        } catch (IOException e) {
            System.out.println("Eroare la crearea fisierului de output.");
        }
        /*scriere cereri finalizate in fisierul de output*/
        try (FileWriter fw = new FileWriter(ManagementPrimarie.antetOutput + ManagementPrimarie.file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(nume + " - cereri in finalizate:");
            for (Cerere cerere : cereriSolutionate)
                out.println(cerere.parsareData() + " - " + textCerere(cerere.getTip()));
            out.flush();
        } catch (IOException e) {
            System.out.println("Nu s-a putut scrie in fisierul de output");
        }
    }

    /*scriere cereri in asteptare in fisierul de output*/
    @Override
    public void cereriInAsteptare() {
        /*creare fisier de output daca nu exista*/
        File outputFile = new File(ManagementPrimarie.antetOutput + ManagementPrimarie.file);
        try {
            if (outputFile.createNewFile()) {
                System.out.println("Fisier de output creat: " + outputFile.getName());
            } else {
                System.out.println("Fisierul de output exista deja.");
            }
        } catch (IOException e) {
            System.out.println("Eroare la crearea fisierului de output.");
        }
        /*scriere cereri in asteptare in fisierul de output*/
        try (FileWriter fw = new FileWriter(ManagementPrimarie.antetOutput + ManagementPrimarie.file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(nume + " - cereri in asteptare:");
            for (Cerere cerere : cereriInAsteptare)
                out.println(cerere.parsareData() + " - " + textCerere(cerere.getTip()));
            out.flush();
        } catch (IOException e) {
            System.out.println("Nu s-a putut scrie in fisierul de output");
        }
    }
}
