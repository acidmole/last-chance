Ohjelman sovelluslogiikkaa on testattu JUnit-testeillä ja raportti on luettavissa, kun lähdekoodin testaa Maven-ympäristön automaattisella arvioijalla _mvn test jacoco:report_. Testiraportti tulee _target/site/jacoco_-kansioon.

Ohjelman testien kattavuus on 67% ja niissä ei ole huomoitu graafisen käyttöliittymän koodia.

Ohjelma on testattu toimivaksi Linux-ympäristössä.

Tunnettuja testauksen ulkopuolelle jääneitä ongelmia:
* musiikkia ei pystytä toistamaan soittokohdasta, kun ohjelma keskeytyy esim. ESC-näppäimestä.
* virheilmoituksia ei esitetä, vaikka joitain tiedostoja jäisi puuttumaan
