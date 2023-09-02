package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;



public class Birou<T extends Utilizator>{
    private PriorityQueue<Cerere> cereriDeSolutionat;
    private ArrayList<FunctionarPublic<T>> functionari;
    public Birou() {
        this.cereriDeSolutionat = new PriorityQueue<>();
        this.functionari = new ArrayList<>();
    }

    public ArrayList<FunctionarPublic<T>> getFunctionari() {
        return functionari;
    }

    public PriorityQueue<Cerere> getCereriDeSolutionat() {
        return cereriDeSolutionat;
    }

    public void setCereriDeSolutionat(PriorityQueue<Cerere> cereriDeSolutionat) {
        this.cereriDeSolutionat = cereriDeSolutionat;
    }

    public void adaugaFunctionar(FunctionarPublic<T> functionar) {
        this.functionari.add(functionar);
    }

    public void afiseazaFunctionari() {
        for (FunctionarPublic<T> functionar : this.functionari) {
            System.out.println(functionar.getNume());
        }
    }

    public void primesteCerere(Cerere cerere) {
        cereriDeSolutionat.offer(cerere);
    }

    /*daca o cerere e retrasa trebuie eliminata si din coada de cereri din birou*/
    public void retrageCerere(Date data) {
        for(Cerere cerere : cereriDeSolutionat) {
            if (cerere.getData().equals(data)) {
                cereriDeSolutionat.remove(cerere);
                break;
            }
        }
    }

    public Cerere rezolvareCerere() {
        return cereriDeSolutionat.poll();
    }

    public void afiseazaCereri(Cerere cerere, T utilizator) {
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
        /*scriere cereri din birou in fisierul de output*/
        try (FileWriter fw = new FileWriter(ManagementPrimarie.antetOutput + ManagementPrimarie.file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.print(cerere.getPrioritate() + " - " + cerere.parsareData());
            out.println(" - " +  utilizator.textCerere(cerere.getTip()));
            out.flush();
        } catch (IOException e) {
            System.out.println("Nu s-a putut scrie in fisierul de output");
        }
    }
}





