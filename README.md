# Online algoritmusok gyakorlat – Algoritmus-fejlesztési feladat #

### Feladatkiírás ###

A feladat egy online ütemezési probléma megoldására szolgáló algoritmus fejlesztése és implementációja.

### Probléma definíció ###

A feladat keretein belül megoldandó egy visszautasításos és konfliktusos ütemezési probléma. Ebben minden
feladathoz meg van adva (a végrehajtási ideje mellett) a visszautasítás költsége, melyet akkor kell "kifizetnie"
az algoritmusnak, ha úgy dönt, hogy az adott feladatot nem hajtja végre, valamint egy konfliktus-lista, amely
azokat a feladatokat tartalmazza, amelyeket nem lehet az aktuális feladattal azonos gépen végrehajtani. A
cél a visszautasítási költségeknek és a legkésőbbi befejezési időnek az összegének a minimalizálása.

### Implementációs elvárások ###

Az algoritmust JAVA nyelven, a közzétett keretrendszer részeként a *JobScheduler* abstract osztályból való
örököltetéssel és a származtatott osztályban a *scheduleJob()* metódus megvalósításával kell implementálni.
Ez a metódus egy feladatot (*Job*-ot) kap paraméterül, és egy gépet (*Machine*-t) kell visszaadnia, amelyre
ütemezi a kapott feladatot, illetve *null* értékkel tér vissza, ha visszautasítja a munkát.
Beküldendő az abstract metódus megvalósítását tartalmazó *JobSchedulerImpl* nevű osztály forráskódja.
Fontos, hogy az osztály neve pontosan ez legyen!

### Input ###

Az input egy JSON fájlból kerül beolvasásra a keretrendszer által, tehát ezzel az implementált algoritmusnak
nem kell foglalkoznia.
Az inputban adott a gépek listája, amelyben minden géphez csak egy *ID (String)* tartozik, illetve a
munkák listája. A munkák a következő paraméterekkel rendelkeznek:

* *ID (String)*
* *duration (long)*: A végrehajtási idő
* *rejectionPrice (long)*: A visszautasítás költsége
* *conflictedJobIds (Set<String>)*: Azon munkák ID-jainak halmaza, amelyekkel nem hajtható végre
azonos gépen az adott munka. Csak olyan munkák ID-ját tartalmazhatja, amelyek korábban szerepel-
nek a listában, mint az aktuális munka. (A keretrendszer az input betöltésekor ez alapján beállít az
adott munkához egy *Set<Job>* típusú *conflictedJobs* attributúmot, ezt érdemes használni.)

A közzétett keretrendszer tartalmaz egy egyszerű példa inputot, de az algoritmusok értékelése nem ezen
fog történni. Az implementáció mellé lehetőség van input fájlok csatolására, és (részben) az így beküldött
inputokon lesznek értékelve az algoritmusok.

### Megjegyzések ###

A feladat megoldása során figyelembe kell venni a következőket:
* A keretrendszer 1.8-as verziójú JAVA-t használva készült.
* A JSON fájlok betöltéséhez a Gson könyvtár került felhasználásra, amelyet a közzétett Eclipse projekt
tartalmaz.
* A keretrendszer futtató része a későbbiekben változhat, amely változások nem feltétlenül lesznek nyil-
vánosak. Éppen ezért a beküldött megoldás ne tartalmazza a keretrendszerhez kapcsolódó fájlokat,
tehát csak az általatok módosított JobSchedulerImpl osztályt tartalmazó fájlt kell beküldeni.
* A keretrendszer azért készült, hogy biztosított legyen az algoritmusok online tulajdonsága. A be-
adott megvalósításban minden olyan próbálkozás, ami ennek kijátszására szolgál büntetést von maga
után, amely a gyakorlat nem-teljesítését jelenti. Ugyanakkor a keretrendszerben talált esetleges hibák
jelentéséért a hiba jellegétől függően plusz pontok kaphatóak.