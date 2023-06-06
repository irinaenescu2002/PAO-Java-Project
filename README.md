# PAO-Java-Project
Proiect pentru Programare Avansata pe Obiecte (anul II) implementat utilizand limbajul de programare Java. Proiectul urmareste dezvoltarea unei aplicatii de gestiune a unui lant de Centre de Echitatie. 

## Definirea sistemului
<img align="right" src="https://camo.envatousercontent.com/8499761c58ab7fc60c378096ddfafc46c5dbd886/687474703a2f2f64696c65747461636174612e636f6d2f77702d636f6e74656e742f75706c6f6164732f323031372f30312f6f75747075745f556b376a58372e676966"> </img>
Vom avea mai multe centre de echitatie, fiecare situat intr-o locatie bine stabilita. La fiecare centru de echitatie se vor afla mai multi cai (acestia sunt salvati intr-o lista ordonata alfabetic). Fiecare cal este antrenat pentru o categorie (dresaj, sarituri peste obstacole etc). De asemenea, fiecare centru de echitatie va avea proprii angajati (instructor sau ingrijitori). Clientii centrului de echitatie pot merge la orice centru de echitatie pentru o programare. Programarea se face in functie de cal, client si antrenor. 

✅ Obiectele definite in cadrul sistemului:
1. Centru de echitatie 
2. Locatie
3. Cal
4. Client
5. Angajat (instructor sau ingrijitor)
6. Categorie 
7. Manej
8. Programare 

✅ Interogarile definite in cadrul sistemului:
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
12. Afisarea tuturor cailor de o anumita culoare
13. Calcularea bugetului lunar necesar centrelor de echitatie pentru a plati toti angajatii 
14. Afisarea tuturor cailor in functie de o anumita categorie 
15. Afisarea datelor de contact (angajati si clienti)
16. Afisarea varstei (clienti si angajati), respectiv a vechimii (angajati)
17. Stergerea unui client
18. Stergerea unui centru de echitatie aflat la o anumita locatie 
19. Marirea salariului unui angajat cu X RON
20. Adaugarea unor noi grajduri la un centru de echitatie 
21. Mutarea unui cal de la un centru de echitatie dintr-un grajd in altul 

## Implementarea sistemului 
✅ Clase simple cu atribute private / protected si metode de acces

✅ Cel putin doua colectii diferite capabile sa gestioneze obiectele definite anterior dintre care una sa fie sortata
1. Fiecare centru de echitatie va avea o lista cu angajati si cu cai sortate alfabetic. 
2. Lista de programari se va salva sub forma unui dictionar indexat dupa fiecare client. 
3. Fiecare centru de echitatie va avea un set cu informatii despre fiecare manej. 

✅ Cel putin o clasa serviciu care sa expuna operatiile sistemului - Singleton 

✅ Utilizarea mostenirii pentru crearea de clase aditionale si utilizarea in cadrul colectiilor 
1. Clasele Ingrijitor si Antrenor mostenesc clasa Angajat 
2. Lista angajatilor unui centru de echitatie cuprinde atat obiecte de tipul Ingrijitor, cat si obiecte de tipul Antrenor 

✅ O clasa Main din care sunt facute apeluri catre servicii 

✅ Utilizarea Interface si Enum 
1. Clasele Client si Angajat implementeaza interfata Persoana 
2. Enum este utilizat pentru a descrie categoriile de antrenament ale cailor centrelor de echitatie 

✅ Definirea si tratarea exceptiilor
1. Tratarea exceptiilor standard (FileNotFoundException, NoSuchElementException etc.)
2. Definirea unor exceptii noi (plasarea unui cal intr-un grajd ce nu exista, plasarea unui cal intr-un grajd ocupat, email sau numar de telefon introdus gresit) 

## JDBC. Baza de date relationala 

✅ A fost stabilita conexiunea la o baza de date relationala. Au fost utilizate servicii singleton generice. 


![image](https://github.com/irinaenescu2002/PAO-Java-Project/assets/93870739/df39e7da-d213-4ade-b297-41627c1cd32e)


✅ Expunerea operatiilor de tip CRUD pe cel putin 4 dintre clasele definite: 

1. CLIENTS
- CREATE un nou client in baza de date 
- READ detalii despre clientii din baza de date 
- UPDATE numarul de telefon si email-ul unui client din baza de date 
- DELETE un client din baza de date 

2. RIDING CENTERS 
- CREATE un nou centru de echitatie in baza de date 
- READ detalii despre toate centrele de echitatie din baza de date 
- UPDATE numarul de grajduri dintr-un centru de echitatie din baza de date 
- DELETE un centru de echitatie din baza de date in functie de locatia in care se afla acesta

3. EMPLOYEES
- CREATE un nou angajat in baza de date (atribuit la un centru de echitatie din baza de date)
- READ detalii despre datele de contact ale unui angajat din baza de date 
- UPDATE salariul unui angajat din baza de date (crestere cu x RON)
- DELETE angajatii unui centru de echitatie atunci cand este sters centrul de echitatie din baza de date 

4. HORSES 
- CREATE un nou cal in baza de date (atribuit la un centru de echitatie din baza de date)
- READ detalii despre caii de o anumita culoare de la centrele de echitatie din baza de date 
- UPDATE grajdul in care sta un cal la un anumit cetru de echitatie 
- DELETE caii unui centru de echitatie atunci cand este sters centrul de echitatie din baza de date 

5. LOCATIONS
- CREATE o noua locatie in baza de date
- READ toate locatiile din baza de date 
- DELETE o locatie din baza de date atunci cand se sterge centrul de echitatie respectiv 

## Audit 

✅ A fost realizat un serviciu de scriere intr-un fisier CSV d fiecare data cand este executata o actiune in cadrul programului. 
Structura fisierului:
(TIMESTAMP, actiune)


![image](https://github.com/irinaenescu2002/PAO-Java-Project/assets/93870739/926cde68-d33a-400f-bf16-62e59285e960)

