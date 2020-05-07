# INF112 Maven template 
Simple skeleton with libgdx. 


## Known bugs
Currently throws "WARNING: An illegal reflective access operation has occurred", 
when the java version used is >8. This has no effect on function or performance, and is just a warning.


[![Build Status](https://travis-ci.com/inf112-v20/roborockers.svg?branch=master)](https://travis-ci.com/inf112-v20/roborockers)

## For kunne kjøre koden
For å kunne kjøre koden trenger du:
1. Java 11 (Kan tenkes at det fungerer med andre versjoner)
2. Et Java IDEA program for å åpne koden med Maven, for eksempel IntelliJ IDEA CE eller Eclipse IDE. 
3. Du må deretter importere mappen der prosjektet ligger, velg Maven og hvor du vil importere prosjektet til
4. Trykk Next på de påfølgende alternativene, trykk Finish og kjør Main.java 

## For å spille spillet
1. Med musepeker, velg brett og antall spillere.
OPTIONAL: Med musepekeren trykk 'Show selected map' for å se et bilde av det valgte mappet
2. Med musepekeren trykk på 'Play'-knappen.
3. Du styrer den gule roboten med et ettall, ved å velge kort med nummertastene 1 - 9.
4. Man kan angre valg av kort enten ved å taste nummertasten til kortet omigjen, eller ved å trykke 'Backspace'. Backspace legger tilbake alle valgte kort som ikke er låst.
5. Dersom man trykker 'P' vil man velge/avvelge å annonsere powerdown. Powerdown innebærer at roboten healer til 9 HP, men må stå over en hel runde og stå stille alle 5 faser. **PDS står for Powerdown Status** 
6. Når 5 kort er valgt kan man låse inn programkortet med 'Enter'-knappen. Da vil spillet kjøre alle 5 faser, og dele ut nye kort for neste runde.
7. Dersom roboten til din egen kontrollerte spiller dør vil den ikke lenger bli tildelt kort, man kan se ferdig avslutningen av spillet ved å trykke hvilken som helst knapp for å starte neste runde.
8. Når en vinner er blitt kåret vises en annen skjerm der vinneren annonseres.

## NB: Debugmode / Testmode
1. Når Playknappen er trykket kan man med 'D' knappen toggle debugmode av og på. Det vil vises på skjermen dersom man er i debugmode.
2. I debugmode vil følgende funksjonalitet være tilgjengelig
* Piltastene til forsøke å flytte spiller 1 i gjeldende retning, for så å oppdatere brettet.
* 'U' vil prompte brettet til å oppdatere seg og rotere, dytte spillere på belter, oppdatere flaggstatus, og skyte lasere
* 'G', 'H' og 'J' vil rotere spilleren 90, 180 og 270 grader.
* 'R' vil sette spiller 1 tilbake til sitt checkpoint.

Merk at den vanlige spillimplementasjonen fungerer samtidig som debugmode så, det kan medføre noen problemer å toggle fram og tilbake dersom spillerens HP endrer seg.

## Teamet
* Peter Kolbeinsen Klingenberg - Team leder 
* Johan Fredrik Svendsen - Kundekontakt
* Vegard Kjørberg - Møtereferat ansvarlig
* Øystein Ulvestad Karlsen - Coach
* Anna Halvorsen Nilsen - Kodestruktur ansvarlig
