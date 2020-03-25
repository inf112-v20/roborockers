
## Deloppgave 1: Team og prosjekt
**Referat fra møter siden forrige leveranse skal legges ved.** 
Du finner møtereferatene våres på Wiki siden på prosjektet. -> https://github.com/inf112-v20/roborockers/wiki

**Hvordan fungerer rollene i teamet? Trenger dere å oppdatere hvem som er teamlead eller kundekontakt?** 
Rollene fungerer fortsatt bra, selv om Peter er teamlead så bidrar alle til at jobben blir gjort. Da Johan er kundekontakt er han flink til å stille spørsmål til gruppeleder da han har sjansen, til tross for at vi ikke har hatt et kundemøte enda. Nå som omstendigheten er endret og vi vil kun kunne jobbe hjemmefra, så er det best at rollene forblir som de er. Men gruppen er åpen for forslag om noen ønsker å endre roller i løpet av denne iterasjonen.  


**Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre måten teamet fungerer på?** 
Vi er fornøyd med Kanban metoden, men det var først forrige gang vi lærte oss og bruke det skikkelig. Nå føler vi at vi får brukt det på en fornuftig måte. Vi har ikke vært veldig flinke til å holde project board oppdatert, vi glemte å flytte ferdige oppgaver frå in progress til done. Dette har vi rettet på og vi er bedre til å oppdatere project board fortløpende.

**Gjør et kort retrospektiv hvor dere vurderer hva dere har klart til nå, og hva som kan forbedres. Dette skal handle om prosjektstruktur, ikke kode. Dere kan selvsagt diskutere kode, men dette handler ikke om feilretting, men om hvordan man jobber og kommuniserer.** 
På grunn av koronaviruset har prosjektstrukturen blitt litt endret i forhold til at folk er på forskjellige steder i landet og nå må vi sitte på discord og arbeide med prosjektet. Det fungerer egentlig ganske bra å jobbe sammen via discord. Skjermdeling funksjonen gjør at vi kan samarbeide om koden på en god måte. Vi klarer å avtale når vi skal møtes på discord for å jobbe og det har ikke hemmet evnen vår til å jobbe i lag veldig mye.


**Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint.** 

Få til enda flere møter enn vi tidligere har hatt, på grunn av at vi er mer fleksible nå og har bedre tid til å jobbe med prosjektet. 
Mer effektivitet
Rette opp i skjev fordeling av commits. Noe av det vi har gjort hittil har blitt gjort på noen få personers pc, selv om andre gruppemedlemmer har vært med på å gjøre selve deloppgaven. 

**Forklar kort hvordan dere har prioritert oppgavene fremover. Legg ved skjermdump av project board ved innlevering** 
Vi har i denne iterasjonen prioritert å jobbe med mulig med koding. Vi tok en titt på project boardet og valgte ut hvilke oppgaver vi ville begynne med. Vi prøvde å ikke velge så mange oppgaver i starten, og heller velge ut flere om vi rekker å bli ferdig med de vi allerede har valgt.  
[![Project board](https://i.postimg.cc/d02g2KCR/Skjermbilde-2020-03-25-kl-14-42-03.png)



**Hvordan fungerer gruppedynamikken og kommunikasjonen?**
Kommunikasjon vil fremover nå foregå via discord. Her kommer vi til å ha møtene våres fremover og oppdatere hverandre på hvordan vi ligger an, eller endringer som skjer. Vi har planer om å ha mellom 3-4 møter i uken, på grunn av at vi ikke møtes i person, er det nå viktigere å ha lengre og mer hyppige møter. Grunnen til dette er at det ofte er lettere å ha møter nå, siden vi ikke må fysisk møtes et sted, hvor vi nå tar alt over discord. En annen grunn er også at vi nå har bedre tid, siden vi ikke har noe særlig obligatorisk oppmøte eller forelesninger på skolen, som vi må møte på til et spesifikt tidspunkt. 

## Deloppgave 2: Krav
**Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang.** 
Til denne iterasjonen har vi prioritert disse kravene:
* Legge til vegger, og at de har en funksjon 	 [#Issue 5](https://github.com/inf112-v20/roborockers/issues/5)
* Dytte andre roboter [#Issue 11](https://github.com/inf112-v20/roborockers/issues/11)
* Implementere fungerende converybelt	 [#Issue 8](https://github.com/inf112-v20/roborockers/issues/8)
* Gå ut av brettet eller gå i et hull og “reboote” til start location 	[#Issue 12](https://github.com/inf112-v20/roborockers/issues/12) og [#Issue 13](https://github.com/inf112-v20/roborockers/issues/13) 
* En spiller kan dø  [#Issue 14](https://github.com/inf112-v20/roborockers/issues/14)

Brukerhistorie, akseptansekriterier og arbeidsoppgaver finner du på hvert issue (gitt ovenfor) på project boardet vårt. 
 

**Forklar kort hvilke hovedkrav dere anser som en del av MVP(Minimum Viable Product) og hvorfor. Hvis det er gjort endringer i rekkefølge utfra hva som er gitt fra kunde, hvorfor er dette gjort?**   

Kravene for MVP er å kunne bevege roboten og at roboten kan interagere med brettet. Roboten må  ikke kunne gå gjennom vegger og den må “dø” og bli rebootet når den faller ned i et hull eller går ut av brettet. Dette er viktig fordi det er grunnlaget for at spillet kan spilles. 



## Tester 
Vi har lagt inn noen JUnit tester for å kunne se om ulike funksjoner med spillet fungerer. Ikke alle ting er like enkelt å få testet med denne metoden, som for eksempel LibGdx og derfor har vi valgt å bruke manuelle tester. Dette går ut på at vi sjekker at dette fungerer med å gjøre dette i spillet. 

De manuelle testete finner dere i "test" mappen på prosjeket. 


