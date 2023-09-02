package org.example;

import jdk.jshell.execution.Util;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class ManagementPrimarie {
    static String antetOutput = "src/main/resources/output/";
    static String antetInput = "src/main/resources/input/";
    static String file;
    private ArrayList<Utilizator> utilizatori;

    /*cate un birou pentru fiecare tip de utilizator*/
    private Birou<Angajat> birouAngajati;
    private Birou<Elev> birouElevi;
    private Birou<Pensionar>  birouPensionari;
    private Birou<EntitateJuridica> birouEntitatiJuridice;
    private Birou<Persoana> birouPersoane;

    public ManagementPrimarie() {
        this.utilizatori = new ArrayList<>();
        this.birouAngajati = new Birou<>();
        this.birouElevi = new Birou<>();
        this.birouPensionari = new Birou<>();
        this.birouEntitatiJuridice = new Birou<>();
        this.birouPersoane = new Birou<>();
    }

    /*functie care imi gaseste utilizatorul dupa nume*/
    public Utilizator gasescUtilizator(String nume) {
        for (Utilizator utilizator : utilizatori) {
            if (utilizator instanceof Angajat) {
                if (((Angajat)utilizator).getNume().equals(nume)) {
                    return utilizator;
                }
            } else if (utilizator instanceof Elev) {
                if (((Elev)utilizator).getNume().equals(nume)) {
                    return utilizator;
                }
            } else if (utilizator instanceof Pensionar) {
                if (((Pensionar)utilizator).getNume().equals(nume)) {
                    return utilizator;
                }
            } else if (utilizator instanceof EntitateJuridica) {
                if (((EntitateJuridica)utilizator).getNume().equals(nume)) {
                    return utilizator;
                }
            } else if (utilizator instanceof Persoana) {
                if (((Persoana)utilizator).getNume().equals(nume)) {
                    return utilizator;
                }
            }
        }
        return null;
    }

    /*functie care adauga in lista un utilizator*/
    public void adaugaUtilizator(Utilizator utilizator) {
        utilizatori.add(utilizator);
    }

    /*functie care adauga cererea la colectia de cereri in asteptare a unui utilizator si in coada de cereri a biroului
    * corespunzator tipului de utilizator*/
    public void adaugaCererePrimarie(Utilizator utilizator, Cerere cerere) {
        utilizator.adaugareCerere(cerere);
        if (utilizator instanceof Angajat) {
            birouAngajati.primesteCerere(cerere);
        } else if (utilizator instanceof Elev) {
            birouElevi.primesteCerere(cerere);
        } else if (utilizator instanceof Pensionar) {
            birouPensionari.primesteCerere(cerere);
        } else if (utilizator instanceof EntitateJuridica) {
            birouEntitatiJuridice.primesteCerere(cerere);
        } else if (utilizator instanceof Persoana) {
            birouPersoane.primesteCerere(cerere);
        }
    }

    /*functie care afiseaza cererile care trebuie solutionate din birou*/
    /*dupa ce e parcursa coada de cereri si devine goala, este recreata la final*/
    public void afisareCereriPrimarie(String tipUtilizator) {
        if (tipUtilizator.equals("angajat")) {
            PriorityQueue<Cerere> cereriDeSolutionat = new PriorityQueue<>(birouAngajati.getCereriDeSolutionat());
            while (!birouAngajati.getCereriDeSolutionat().isEmpty()) {
                Cerere cerere = birouAngajati.getCereriDeSolutionat().element();
                Utilizator utilizator = cerere.getUtilizator();
                birouAngajati.afiseazaCereri(cerere, (Angajat) utilizator);
                birouAngajati.getCereriDeSolutionat().poll();
            }
            birouAngajati.setCereriDeSolutionat(cereriDeSolutionat);
        } else if (tipUtilizator.equals("elev")) {
            PriorityQueue<Cerere> cereriDeSolutionat = new PriorityQueue<>(birouElevi.getCereriDeSolutionat());
            while (!birouElevi.getCereriDeSolutionat().isEmpty()) {
                Cerere cerere = birouElevi.getCereriDeSolutionat().element();
                Utilizator utilizator = cerere.getUtilizator();
                birouElevi.afiseazaCereri(cerere, (Elev) utilizator);
                birouElevi.getCereriDeSolutionat().poll();
            }
            birouElevi.setCereriDeSolutionat(cereriDeSolutionat);
        } else if (tipUtilizator.equals("pensionar")) {
            PriorityQueue<Cerere> cereriDeSolutionat = new PriorityQueue<>(birouPensionari.getCereriDeSolutionat());
            while (!birouPensionari.getCereriDeSolutionat().isEmpty()) {
                Cerere cerere = birouPensionari.getCereriDeSolutionat().element();
                Utilizator utilizator = cerere.getUtilizator();
                birouPensionari.afiseazaCereri(cerere, (Pensionar) utilizator);
                birouPensionari.getCereriDeSolutionat().poll();
            }
            birouPensionari.setCereriDeSolutionat(cereriDeSolutionat);
        } else if (tipUtilizator.equals("entitate juridica")) {
            PriorityQueue<Cerere> cereriDeSolutionat = new PriorityQueue<>(birouEntitatiJuridice.getCereriDeSolutionat());
            while (!birouEntitatiJuridice.getCereriDeSolutionat().isEmpty()) {
                Cerere cerere = birouEntitatiJuridice.getCereriDeSolutionat().element();
                Utilizator utilizator = cerere.getUtilizator();
                birouEntitatiJuridice.afiseazaCereri(cerere, (EntitateJuridica) utilizator);
                birouEntitatiJuridice.getCereriDeSolutionat().poll();
            }
            birouEntitatiJuridice.setCereriDeSolutionat(cereriDeSolutionat);
        } else if (tipUtilizator.equals("persoana")) {
            PriorityQueue<Cerere> cereriDeSolutionat = new PriorityQueue<>(birouPersoane.getCereriDeSolutionat());
            while (!birouPersoane.getCereriDeSolutionat().isEmpty()) {
                Cerere cerere = birouPersoane.getCereriDeSolutionat().element();
                Utilizator utilizator = cerere.getUtilizator();
                birouPersoane.afiseazaCereri(cerere, (Persoana) utilizator);
                birouPersoane.getCereriDeSolutionat().poll();
            }
            birouPersoane.setCereriDeSolutionat(cereriDeSolutionat);
        }
    }

    /*functie care afiseaza cererile care trebuie solutionate din birou pentru un anumit tip de utilizator*/
    public void afiseazaCereri(String tipUtilizator) {
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
        try (FileWriter fw = new FileWriter(ManagementPrimarie.antetOutput + ManagementPrimarie.file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(tipUtilizator + " - cereri in birou:");
            out.flush();
        } catch (IOException e) {
            System.out.println("Nu s-a putut scrie in fisierul de output");
        }
    }

    /*functie care adauga un functionar in birou, care poate solutiona doar cereri facute de tipul de utilizator pe care
    * biroul s-a specializat*/
    public void adaugaFunctionar(String tipUtilizator, String numeFunctionar) {
        switch (tipUtilizator) {
            case "angajat": birouAngajati.adaugaFunctionar(new FunctionarPublic<>(numeFunctionar));
            case "elev": birouElevi.adaugaFunctionar(new FunctionarPublic<>(numeFunctionar));
            case "pensionar": birouPensionari.adaugaFunctionar(new FunctionarPublic<>(numeFunctionar));
            case "entitate juridica": birouEntitatiJuridice.adaugaFunctionar(new FunctionarPublic<>(numeFunctionar));
            case "persoana": birouPersoane.adaugaFunctionar(new FunctionarPublic<>(numeFunctionar));
        }
    }

    public void afisareFunctionari() {
        birouAngajati.afiseazaFunctionari();
        birouElevi.afiseazaFunctionari();
        birouPensionari.afiseazaFunctionari();
        birouEntitatiJuridice.afiseazaFunctionari();
        birouPersoane.afiseazaFunctionari();
    }

    /*daca un functionar rezolva o cerere, atunci aceasta este scoasa din coada de cereri a biroului si adaugata in colectia
    * de cereri solutionate a utilizatorului*/
    public void rezolvaCerere(String tipUtilizator, String numeFunctionar) {
        if (tipUtilizator.equals("angajat")) {
            Cerere cerere = birouAngajati.rezolvareCerere();
            FunctionarPublic<? extends Utilizator> functionar = gasesteFunctionar(tipUtilizator, numeFunctionar);
            functionar.scriereInFisier(cerere.parsareData(), ((Angajat) cerere.getUtilizator()).getNume());
            Utilizator utilizator = cerere.getUtilizator();
            utilizator.cereriSolutionate(cerere.getData());
        } else if (tipUtilizator.equals("elev")) {
            Cerere cerere = birouElevi.rezolvareCerere();
            FunctionarPublic<? extends Utilizator> functionar = gasesteFunctionar(tipUtilizator, numeFunctionar);
            functionar.scriereInFisier(cerere.parsareData(), ((Elev) cerere.getUtilizator()).getNume());
            Utilizator utilizator = cerere.getUtilizator();
            utilizator.cereriSolutionate(cerere.getData());
        } else if (tipUtilizator.equals("pensionar")) {
            Cerere cerere = birouPensionari.rezolvareCerere();
            FunctionarPublic<? extends Utilizator> functionar = gasesteFunctionar(tipUtilizator, numeFunctionar);
            functionar.scriereInFisier(cerere.parsareData(), ((Pensionar) cerere.getUtilizator()).getNume());
            Utilizator utilizator = cerere.getUtilizator();
            utilizator.cereriSolutionate(cerere.getData());
        } else if (tipUtilizator.equals("entitate juridica")) {
            Cerere cerere = birouEntitatiJuridice.rezolvareCerere();
            FunctionarPublic<? extends Utilizator> functionar = gasesteFunctionar(tipUtilizator, numeFunctionar);
            functionar.scriereInFisier(cerere.parsareData(), ((EntitateJuridica) cerere.getUtilizator()).getNume());
            Utilizator utilizator = cerere.getUtilizator();
            utilizator.cereriSolutionate(cerere.getData());
        } else if (tipUtilizator.equals("persoana")) {
            Cerere cerere = birouPersoane.rezolvareCerere();
            FunctionarPublic<? extends Utilizator> functionar = gasesteFunctionar(tipUtilizator, numeFunctionar);
            functionar.scriereInFisier(cerere.parsareData(), ((Persoana) cerere.getUtilizator()).getNume());
            Utilizator utilizator = cerere.getUtilizator();
            utilizator.cereriSolutionate(cerere.getData());
        }
    }

    /*functie care gaseste un functionar dupa numele lui si tipul de utilizator*/
    public FunctionarPublic<? extends Utilizator> gasesteFunctionar(String tipUtilizator, String numeFunctionar) {
        switch (tipUtilizator) {
            case "angajat":
                for (FunctionarPublic<Angajat> functionar : birouAngajati.getFunctionari()) {
                    if (functionar.getNume().equals(numeFunctionar)) {
                        return functionar;
                    }
                }
            case "elev":
                for (FunctionarPublic<Elev> functionar : birouElevi.getFunctionari()) {
                    if (functionar.getNume().equals(numeFunctionar)) {
                        return functionar;
                    }
                }
            case "pensionar":
                for (FunctionarPublic<Pensionar> functionar : birouPensionari.getFunctionari()) {
                    if (functionar.getNume().equals(numeFunctionar)) {
                        return functionar;
                    }
                }
            case "entitate juridica":
                for (FunctionarPublic<EntitateJuridica> functionar : birouEntitatiJuridice.getFunctionari()) {
                    if (functionar.getNume().equals(numeFunctionar)) {
                        return functionar;
                    }
                }
            case "persoana":
                for (FunctionarPublic<Persoana> functionar : birouPersoane.getFunctionari()) {
                    if (functionar.getNume().equals(numeFunctionar)) {
                        return functionar;
                    }
                }
            default:
                return null;
        }
    }

    /*cand o cerere este retrasa, ea este eliminata din colectia de cereri in asteptare a utilizatorului
    * si din coada de cereri a biroului*/
    public void retragereCerere(String data, Utilizator utilizator) {
        utilizator.retragereCerere(Cerere.parsareText(data));
        if (utilizator instanceof Angajat) {
            birouAngajati.retrageCerere(Cerere.parsareText(data));
        } else if (utilizator instanceof Elev) {
            birouElevi.retrageCerere(Cerere.parsareText(data));
        } else if (utilizator instanceof Pensionar) {
            birouPensionari.retrageCerere(Cerere.parsareText(data));
        } else if (utilizator instanceof EntitateJuridica) {
            birouEntitatiJuridice.retrageCerere(Cerere.parsareText(data));
        } else if (utilizator instanceof Persoana) {
            birouPersoane.retrageCerere(Cerere.parsareText(data));
        }
    }

    /*sterg fisierele de output create*/
    static void emptyFiles() {
        File filesList[] = new File(antetOutput).listFiles();
        for(File file : filesList) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                file.delete();
            }
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        file = args[0];
        emptyFiles();
        ManagementPrimarie managementPrimarie = new ManagementPrimarie();
        try (BufferedReader br = new BufferedReader(new FileReader(antetInput + file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("; ");
                if (words[0].equals("adauga_utilizator")) {
                    if (words[1].equals("angajat"))
                        managementPrimarie.adaugaUtilizator(new Angajat(words[2], words[3]));
                    else if (words[1].equals("elev"))
                        managementPrimarie.adaugaUtilizator(new Elev(words[2], words[3]));
                    else if (words[1].equals("pensionar"))
                        managementPrimarie.adaugaUtilizator(new Pensionar(words[2]));
                    else if (words[1].equals("entitate juridica"))
                        managementPrimarie.adaugaUtilizator(new EntitateJuridica(words[2], words[3]));
                    else if (words[1].equals("persoana"))
                        managementPrimarie.adaugaUtilizator(new Persoana(words[2]));
                } else if (words[0].equals("cerere_noua")) {
                    String numeUtilizator = words[1];
                    Utilizator utilizator = managementPrimarie.gasescUtilizator(numeUtilizator);
                    Cerere cerere = utilizator.creareCerere(words[2], Integer.parseInt(words[4]), words[3], utilizator);
                    if (utilizator.textCerere(cerere.getTip()) != null) {
                        /*adaug cererea la primarie si la utilizatorul corespunzator doar daca cererea este valida*/
                        managementPrimarie.adaugaCererePrimarie(utilizator, cerere);
                    }
                } else if (words[0].equals("afiseaza_cereri_in_asteptare")) {
                    String numeUtilizator = words[1];
                    Utilizator utilizator = managementPrimarie.gasescUtilizator(numeUtilizator);
                    utilizator.cereriInAsteptare();
                } else if (words[0].equals("retrage_cerere")) {
                    String numeUtilizator = words[1];
                    Utilizator utilizator = managementPrimarie.gasescUtilizator(numeUtilizator);
                    String data = words[2];
                    managementPrimarie.retragereCerere(data, utilizator);
                } else if (words[0].equals("afiseaza_cereri")) {
                    String tipUtilizator = words[1];
                    managementPrimarie.afiseazaCereri(tipUtilizator);
                    managementPrimarie.afisareCereriPrimarie(tipUtilizator);
                } else if (words[0].equals("adauga_functionar")) {
                    String tipUtilizator = words[1];
                    String numeFunctionar = words[2];
                    managementPrimarie.adaugaFunctionar(tipUtilizator, numeFunctionar);
                    managementPrimarie.afisareFunctionari();
                } else if (words[0].equals("rezolva_cerere")) {
                    String tipUtilizator = words[1];
                    String numeFunctionar = words[2];
                    managementPrimarie.rezolvaCerere(tipUtilizator, numeFunctionar);
                } else if (words[0].equals("afiseaza_cereri_finalizate")) {
                    String numeUtilizator = words[1];
                    Utilizator utilizator = managementPrimarie.gasescUtilizator(numeUtilizator);
                    utilizator.afisareCereriFinalizate();
                }
            }
        } catch (IOException e) {
            System.out.println("Nu s-a putut citi din fisierul de intrare");
        }
    }
}