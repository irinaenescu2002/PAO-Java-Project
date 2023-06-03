# PAO-Java-Project
Proiect pentru Programare Avansata pe Obiecte (anul II) implementat utilizand limbajul de programare Java. Proiectul urmareste dezvoltarea unei aplicatii de gestiune a unui lant de Centre de Echitatie. 

## Definirea sistemului

Vom avea mai multe centre de echitatie, fiecare situat intr-o locatie bine stabilita. La fiecare centru de echitatie se vor afla mai multi cai (acestia sunt salvati intr-o lista ordonata alfabetic). Fiecare cal este antrenat pentru o categorie (dresaj, sarituri peste obstacole etc). De asemenea, fiecare centru de echitatie va avea proprii angajati (instructor sau ingrijitori). Clientii centrului de echitatie pot merge la orice centru de echitatie pentru o programare. Programarea se face in functie de cal, client si antrenor. 

✅ Obiectele definite in cadrul sistemului sunt urmatoarele:
1. Centru de echitatie 
2. Locatie
3. Cal
4. Client
5. Angajat (instructor sau ingrijitor)
6. Categorie 
7. Manej
8. Programare 

✅ Interogarile definite in cadrul sistemului 
1. Adaugarea unui nou centru de echitatie intr-o anumita locatie
2. Adaugarea unui client 
3. Adaugarea unui cal, unui angajat, unei arene intr-un centru de echitatie 
4. Inregistrarea unei programari 
5. Vizualizarea detaliilor centrelor de echitatie (cai, angajati etc.)
6. Vizualizarea clientilor centrelor de echitatie 
7. Modificarea datelor de contact ale unui client 
8. Afisarea programarilor unui angajat, unui cal sau unui client 
9. Afisarea clientilor fideli
10. Afisarea celor mai activi angajati 
11. Calcularea sumei totale incasate pe programari 
12. Anularea unei programari 
13. Calcularea bugetului lunar necesar centrelor de echitatie pentru a plati toti angajatii 
14. Afisarea tuturor cailor in functie de o anumita categorie 

## Implementarea sistemului 
✅ Clase simple cu atribute private / protected si metode de acces

✅ Cel putin doua colectii diferite capabile sa gestioneze obiectele definite anterior dintre care una sa fie sortata
1. Fiecare centru de echitatie va avea o lista cu angajati si cu cai sortate alfabetic. 
2. Lista de programari se va salva sub forma unui dictionar indexat dupa fiecare client. 
3. Fiecare centru de echitatie va avea un set cu informatii despre fiecare manej. 

✅ Cel putin o clasa serviciu care sa expuna operatiile sistemului 

✅ Utilizarea mostenirii pentru crearea de clase aditionale si utilizarea in cadrul colectiilor 
1. Clasele Ingrijitor si Antrenor mostenesc clasa Angajat 
2. Lista angajatilor unui centru de echitatie cuprinde atat obiecte de tipul Ingrijitor, cat si obiecte de tipul Antrenor 

✅ O clasa Main din care sunt facute apeluri catre servicii 

✅ Utilizarea Interface si Enum 
1. Clasele Client si Angajat implementeaza interfata Persoana 
2. Enum este utilizat pentru a descrie categoriile de antrenament ale cailor centrelor de echitatie 

✅ Definirea si tratarea exceptiilor
1. Tratarea exceptiilor standard (FileNotFoundException, NoSuchElementException etc.)
2. Definirea unor exceptii noi (plasarea unui cal intr-un grajd ce nu exista, plasarea unui cal intr-un grajd ocupat) 
