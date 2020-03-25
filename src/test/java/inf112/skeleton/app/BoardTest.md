#BoardTest
Vi har lagt inn noen JUnit tester for å kunne se om ulike funksjoner med spillet fungerer. 
Ikke alle ting er like enkelt å få testet med denne metoden, 
som for eksempel LibGdx og derfor har vi valgt å bruke manuelle tester.
Dette går ut på at vi kjører programmet og klikker oss gjennom menyskjermen og deretter styrer spilleren manuelt
og observerer at forventet oppførsel forekommer avhengig av hvilke kommandoer vi gir.

Noen av de reglene vi har laget, som vi tester manuelt er: 

* 

* Vegger blokkerer spiller: Lagde flere forskjellige vegger på mappet, prøvde å styre spilleren gjennom vegger og 
observerte om spilleren gikk gjennom.

* Hull dreper spiller og resetter til checkpoint: Styrte spilleren til hul og observerte at spiller ble satt til 
checkpoint/startposition

* Gå av brettet dreper spiller: Styrte spilleren til kanten av brettet og observerte at spiller døde om den gikk 
utenfor 

* Kollisjon med player er noe vi tenker å teste manuelt, men det er ikke funksjonelt enda.
