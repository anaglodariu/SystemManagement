
Tema2 POO:

Cum una dintre functionalitatile unui utilizator este mentinerea unor colectii cu cererile solutionate sau in asteptare, 
pentru fiecare tip de utilizator retin aceste cereri prin intermediul lui TreeSet, care imi sorteaza cererile dupa 
ordinea datei de creare cu ajutorul unui comparator.

In clasa generica Birou am un camp in care imi tin cererile care trebuie solutionate intr-o colectie de tipul PriorityQueue.
Astfel, cererile sunt puse in ordinea ceruta in enunt deoarece am suprascris functia compareTo, iar clasa Cerere implementeaza
interfata Comparable. De asemenea, functionarii din cadrul biroului ii tin intr-un ArrayList. (sunt pusi in lista in ordinea
in care sunt adaugati in birou)

In clasa ManagementPrimarie am cate un camp pentru fiecare tip de birou (birouri specializate pe un anumit tip de cetatean,
neputand primi cererile altor tipuri de utilizatori) si o colectie de tipul ArrayList in care retin toti utilizatorii.
