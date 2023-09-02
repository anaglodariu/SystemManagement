package org.example;

import java.io.*;

public class FunctionarPublic<T extends Utilizator> {
    private String nume;

    public FunctionarPublic(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    /*scrierea in fisierul de output a cererilor solutionate de un functionar*/
    public void scriereInFisier(String data, String numeUtilizator) {
        File outputFile = new File(ManagementPrimarie.antetOutput + "functionar_" + nume + ".txt");
        try {
            if (outputFile.createNewFile()) {
                System.out.println("Fisier de output creat: " + outputFile.getName());
            } else {
                System.out.println("Fisierul de output exista deja.");
            }
        } catch (IOException e) {
            System.out.println("Eroare la crearea fisierului de output.");
        }
        try (FileWriter fw = new FileWriter(ManagementPrimarie.antetOutput + "functionar_" + nume + ".txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(data + " - " + numeUtilizator);
            out.flush();
        } catch (IOException e) {
            System.out.println("Nu s-a putut scrie in fisierul de output");
        }
    }
}
