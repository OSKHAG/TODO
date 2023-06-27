# Projektrapport: Oskar Hagman TODO

### Beskrivning
Projektet är en enkel todo applikation, där en användare kan registrera uppgifter samt tilldela dessa uppgifter till en önskad användare.
För att det ska fungera så kan användaren också registrera användare för att sedan kunna tilldela arbetsuppgifter till.
Både användare och uppgifter sparas i en mongodb databas.

Lösenord : user1   

    
#### Beroenden
- Java 17
- Maven

### Plan
Jag använde mig av en enkel repository struktur. Valde att använda mig av en mongodb databas som jag skapat vid tidigare tillfälle.  
Valde också att använda mig av tidigare strukturer på exempelvis utility funktionalitet.


## Arbetet och dess genomförande
Valde att för första gången någonsin genomföra ett projekt helt utan statiska klasser, inget revolutionärt men en lite kul utmaning.  
Det som har varit svårt är i generella drag att hålla ett projektet rent från onödiga kodstycken, syftar framförallt på min fasad, som dessvärre är lite av en röra.


### Vad som varit svårt
Hade stora problem med databas mocken. Det ursprungliga problemet var lustigt nog en felaktig variabeltyp som returnerades.

### Beskriv lite olika lösningar du gjort
Försökt använda mig mer av dependency injections för att kontrollera olika klassers beteende lite bättre. 
Även låta instansiering sköta sin beskärda del av funktionalitet.

### Beskriv något som var besvärligt att få till
Framförallt databasmocken, men som jag beskrev ovan. Det var ett enkelt problem där jag missförstått vad för variabeltyp som returnerades.
Utöver det tycker jag att det mesta gick bra.

### Beskriv om du fått byta lösning och varför i sådana fall
Inget som jag i nuläget kan komma på. 

## Slutsatser

### Vad gick bra
Givetvis sådant som jag redan hade koll på. Skapa koppling till mongo, samt hur den kopplingen upprätthålls. Vanlig crud funktionalitet.

### Vad gick dåligt
Som tidigare nämnts, mockning av databasen. 

### Vad har du lärt dig
Jag har lärt mig att tidigare undersöka vad för variabeltyp som returneras från mongodb i olika situationer.

### Vad hade ni gjort annorlunda om ni gjort om projektet
Jag skulle planerat min arkitektur bättre. Jag ogillar starkt hur hela interaktionen mellan funktioner, genom fasaden, fungerar i nuläget.

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
Är lite osäker på vad kunskaperna har för värde i nuläget. Men i generella drag så känner jag mig mer bekväm i rollen som utvecklare även om jag ibland bryter principer som jag helst vill följa.