# Ohjelmistotekniikka, harjoitustyö

Ohjelmasta on tehty 
[release](https://github.com/acidmole/last-chance/releases/tag/Viikko5).



Ohjelmaa voi myös testata lähdekoodin avulla hakemistossa lastchance/ 
komennolla
```
mvn compile exec:java -Dexec.mainClass=lastchance.ui.LastChanceUi
```

Ohjelman lähdekoodia voi testata ja testikattavuutta seurata komennolla
```
mvn test jacoco:report
```
Testiraportti löytyy hakemistosta target/site/jacoco/index.html


Koodin puhtautta voi tarkistaa käskyllä
```
mvn jxr:jxr checkstyle:checkstyle
```
Raportti tästä löytyy osoitteesta target/site/checkstyle.html


## Dokumentaatio

* [Vaatimusmäärittely](https://github.com/acidmole/last-chance/blob/master/documents/vaatimusmaarittely.md)
* 
[Luokka- ja sekvenssikaavio](https://github.com/acidmole/last-chance/blob/master/documents/arkkitehtuuri.md)
* [Työaikakirjanpito](https://docs.google.com/spreadsheets/d/163KEEXksQ6WTQZjbq344-tCZUor9Yi9uiTqxldqkszk/edit?usp=sharing)


## Tekijänoikeudet
Ohjelman tekijänoikeudet ovat tekijällä ja edelleen hyödynnettävissä Creative Commons BY-SA -lisenssin mukaisesti.

Musiikki:
"Stakula Nights" säv. ja sov. Heikki Venhola. Äänitys ja miksaus Eero Kaukomies / Content Union Studios huhti-toukokuussa 2021. Edelleen hyödynnettävissä CC-BY-SA -rajoituksin.

Fontti:
"Erbos Draco Monospaced NBP Font", total FontGeek DTF, Ltd. Käytetään CC-BY-SA -lisenssillä.
