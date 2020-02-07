## Deloppgave 1 - Organisér teamet
Fire av medlemmene på gruppen vår går bachelorprogram i informasjon og kommunikasjonsteknologi og det siste medlemmet går bachelorprogram i datasikkerhet. Vi har alle jevnt over hatt de samme fagene innenfor Informatikk, men forskjellen er at en av oss er lengre i studieløpet enn de andre. I disse fagene har vi alle fått erfaring med programmering og skrive kode. Noen av gruppemedlemmene har fått en bedre forståelse for programmering enn andre, men det er ingen store forskjeller som skiller oss. Ingen på teamet er kjent med hverken Maven eller Libgdx.      

Fordeling av roller skjedde etter vi fikk en oversikt over de ulike kompetansenivåene i gruppen. Vi valgte Peter som teamleder da alle var enige om at han er organisert og ansvarsfull. Vegard fikk ansvar for å skrive møtereferat etter hvert møte for å holde en oversikt over hva vi oppnådd i løpet av ett møte. Johan fikk rolle som kundekontakt, sånn ble det bare. Øystein fikk rolle som coach og samarbeids koordinator, da det er viktig å ha en i teamet som har ansvar for å holde stemningen oppe til enhver tid. Anna fikk rollen som ansvarlig for strukturen på oppgaven. 

Vi har som mål å i stor grad programmere dette spillet ved å bruke arbeidsmetoden parprogrammering. Dette skal organiseres ved at teamleder Peter, skal ta initiativ til å samle gruppen gjennom dette semesteret for å arbeide med prosjektet. Grunnen til at vi ønsker å benytte oss av arbeidsmetoden parprogrammering, er fordi vi ser at programmeringskunnskapene våres er litt forskjellige. Med dette vil vi bidra til å hjelpe hverandre, slik at alle skal få best mulighet til å delta og hjelpe hverandre til å få en best mulig oppgave. 


## Deloppgave 2 - Få oversikt over forventet produkt

### Beskrivelse av det overordnede målet for applikasjonen:
Det overordnede målet for applikasjonen er at vi kan programmere en robot på spillebrettet og kunne bruke programkort til å bevege den. Robo Rally består av runder og faser som skal gjennomføres til en spiller vinner. Roboten skal klare å komme seg igjennom alle flagg i stigende rekkefølge. Om roboten dør 3 ganger er spilleren ute av spillet. Den første roboten til å komme seg igjennom alle flaggene på en gyldig måte, vinner.

Vi ønsker lage en digital versjon av brettspillet Robo Rally der alle funksjoner og utseende kan gi den samme opplevelsen som å spille brettspillet. Vi vil lage det som en digital versjon slik at det eventuelt kan spilles over nett. Den digitale versjonen vil også gjøre det mulig å legge til nye funksjoner og effekter som kan gjøre spillet enda mer underholdene. Spillet skal være mulig å spille med flere spillere og inneholde fungerende funksjoner som sett i brettspillet / regelboken.  

Funksjonene som er viktige er: et spillbrett, en bevegelig spillkarakter, ruter på spillebrettet med forskjellige funksjoner, programmeringskort og andre ting som  Aller helst vil vi implementere alle spillets små funksjoner og nyanser slik at det endelige produktet i utseende og spillfølelse gjenspeiler originalen.

#### En liste over krav til systemet basert på høynivåkravene gjennomgått i forelesning. 
* Ha et spillbrett 
* Vise og flytte en brikke
* Spille på ulike type maskiner
* Spille/ gjennomføre en runde
* Spille/ gjennomføre en fase
* Kunne utføre lovlige trekk
* Kunne dele ut kort 
* Ta skade
* Reparere skade
* Ta backup
* Fyre av laser
* Robot kan dø utenfor brettet/i et sort hull
* Plassere flagg
* Prioritering som avgjør rekkefølge på roboter
* Vegger som stopper roboter og som stopper lasere
* Lasere som stopper i første trufne laser
* Dytte andre roboter
* Være i powerdown
* Aktivere robot fra powerdown
* Samlebånd
* Gjennomføre en fase
* Tannhjul
* Vinne spillet
* Avslutte spillet

### En prioritert liste over hvilke krav dere vil ha med i første iterasjon (altså frem til levering av denne oppgaven). 

##### Det vi ønsker å ha med i første iterasjon er:
* Starte prosjektet og vise et spillbrett
* Få en spillkarakter på brettet  

* Inndeling av teamet, og hvordan vi skal jobbe fremover.
* Lage brukerhistorier som vi ønsker å realisere og jobbe videre med.

## Deloppgave 3 - Velg og tilpass en prosess for laget

(Møtereferat fra hvert møte ligger i Wiki)

Vi har valgt å bruke en blanding av Kanban og parprogrammering fordi vi mener at Kanban blir den mest oversiktlige metoden å gå frem med. Grunnen til at vi har valgt Kanban som prosjektmetodikk er fordi det er en enkelt representasjon av hva som skal bli gjort, hvordan man ligger ann og hva som er ferdig. Her går man ut fra hvordan man ligger ann i prosjektet, og gjør litt og litt om gangen. Parprogrammering kommer nok til å skje på en eller annen måte uansett, og vi ser på dette som viktig fordi dette bidrar til best mulig samarbeid hvor alle lærer av hverandre. 

Vi har som plan å ha minst ett møte hver uke i tillegg til gruppetimene. Når vi ser at det er mer behov avtaler vi dette nærmere. Vi bruker Slack for å kommunikasjon dersom det er noe vi lurer på, slik at vi er best mulig forberedt fremfor møtene, og dersom vi har planlagt å gjøre ting individuelt eller i mindre grupper mellom møtene. Arbeidsfordelingen har Peter hovedansvaret å tildele medlemmene i teamet, men alle har også et ansvar for å sette seg inn i hva de andre gjør og dersom man har kommentarer utover dette. Oppfølging av arbeid har Anna hovedansvar for, men dette er også et felt alle har et ansvar for å oppdatere seg på, slik at man får en felles forståelse av hva som skal gjøres og hvordan vi ligger ann i prosjektet. Deling og oppbevaring av felles dokument, diagram og kodebase vil pushes til git, slik at alle har tilgang til dette til enhver tid. 


## Deloppgave 4 - kode

### Brukerhistorier

**Som spiller trenger jeg å se spillbrettet for å kunne spille.**

##### Arbeidsoppgaver for å realisere brukerhistorie:
* Implementere en brett-klasse
* Designe basisutseende
* Modellere og designe struktur i koden

 **Som spiller vil kunne se et element på brettet for** å spille

##### Arbeidsoppgaver for å realisere brukerhistorie:
* Implementere en klasse for et elemnet
* Designe utseede til element

# Oppsumering
I denne oppgaven har vi jobbet jevnt med oppgavene for å bli ferdig til fristen. Vi gikk først sammen på oppgaveteksten for å få en felles oversikt over hva som måtte gjøres. Deretter jobbet vi sammen med første deloppgave der vi gikk igjennom team organisering. Resten av deloppgavene delte vi utover i teamet. Da vi begynte å nærme oss slutten på å få skrevet ferdig deloppgave 1 til 3, så begynte vi felles å se på deloppgave 4. Det var litt usikkerhet rundt hvordan vi skulle gjøre denne, og 2 medlemmer på teamet begynte å se på Travis og Codacy, og resten tok seg fatt på Tiled, Libgdx og brukerhistorier. Måten vi jobbet på fungerte veldig bra i begynnelsen, men da vi kom til siste deloppgave ble det litt mye styr. Vi fikk ikke til å importere knappen fra Codacy. Det var fordi knappen vi skulle trykke på ikke eksisterte. Vi parprogrammerte og prøvde å få til oppgaven, hvor vi gjorde noen feil og brukte tid for å komme tilbake der vi slapp.
