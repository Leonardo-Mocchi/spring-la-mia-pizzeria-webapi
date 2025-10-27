# Consegna

> Repo: spring-la-mia-pizzeria-crud

Questo esercizio è diviso in vari# step, che vanno seguiti in maniera incrementale, essendo propedeutici.

## FASE 1

### Step 1

Dobbiamo realizzare un’applicazione web che ci aiuti a gestire la nostra pizzeria.

[x] Abbiamo bisogno di creare la prima pagina (index) dove mostriamo tutte le pizze che prepariamo. Nei prossimi giorni implementeremo il resto dei metodi per le CRUD.

[x] Una pizza avrà le seguenti informazioni :

- [x] un id
- [x] un nome
- [x] una descrizione
- [x] una foto (url)
- [x] un prezzo

[x] Creiamo:

- [x] il database,
- [x] la repository
- [x] e l'entity per gestire le CRUD delle pizze.

[x] Implementiamo quindi il controller con il metodo index che restituisce una view per mostrare l’elenco delle pizze caricate dal database (possiamo creare una tabella con bootstrap o una qualche grafica a nostro piacimento che mostri questo elenco... con un po’ di creatività se vogliamo!)

[x] L’elenco potrebbe essere vuoto: in quel caso dobbiamo mostrare un messaggio che indichi all’utente che non ci sono pizze presenti nella nostra applicazione.

[x] Gestiamo i componenti riutilizzabili con i fragments.

### Step 2

[x] Mostriamo una singola pizza.

[x] Ogni pizza dell’elenco avrà quindi un pulsante che se cliccato ci porterà a una pagina che mostrerà i dettagli della pizza scelta. Dobbiamo quindi inviare l’id come parametro dell’URL, recuperarlo nel metodo del controller, caricare i dati della pizza ricercata e passarli come model.

[x] La view a quel punto li mostrerà all’utente con la grafica che preferiamo.

### Step 3 - Bonus

[x] Nella pagina con l’elenco delle pizze aggiungiamo un campo di testo che se compilato filtrerà le pizze (lato server) aventi come titolo quello inserito dall’utente.

---

## FASE 2

### Step 4

Abbiamo la lista delle pizze, abbiamo i dettagli delle pizze...perchè non realizzare la pagina per la creazione di una nuova pizza?

[x] Aggiungiamo quindi tutto il codice necessario per mostrare il form per la creazione di una nuova pizza e per il salvataggio dei dati in tabella.

[x] Nella index creiamo ovviamente il bottone “Crea nuova pizza” che ci porta a questa nuova pagina creata.

Ricordiamoci che l’utente potrebbe sbagliare inserendo dei dati: gestiamo quindi la validazione!
[x] Ad esempio verifichiamo che :

- [x] i dati della pizza siano tutti presenti
- [x] il campi di testo non superino una certa lunghezza
- [x] il prezzo abbia un valore valido (ha senso una pizza con prezzo minore o uguale a zero?)

### Step 5

Completiamo le pagine di gestione delle nostre pizze!

Abbiamo la pagina con la lista di tutte le pizze, quella con i dettagli della singola pizza, quella per crearla...cosa manca?

[x] Dobbiamo realizzare :

- [x] pagina di modifica di una pizza
- [x] cancellazione di una pizza, cliccando un pulsante presente nella grafica di ogni singolo prodotto mostrato (nella lista in index)

---

## FASE 3

> **Repo**: spring-la-mia-pizzeria-relazioni

**IMPORTANTE**:
Ricordatevi di sganciare la vostra vecchia repository e di crearne una nuova per questo esercizio, che prosegue il lavoro della pizzeria, dove lo avevate lasciato.

Nuova importante funzionalità : le offerte speciali!
In alcuni momenti potremmo voler vendere le nostre pizze a un prezzo scontato.

[x] Dobbiamo quindi predisporre tutto il codice necessario per poter collegare un’offerta speciale a una pizza (in una relazione 1 a molti, cioè un’offerta speciale può essere collegata a una sola pizza, e una pizza può essere collegata a più offerte speciali).

[x] L’offerta speciale avrà :

- [x] una data di inizio
- [x] una data di fine
- [x] un titolo

[x] La pagina di dettaglio della singola pizza mostrerà l’elenco delle offerte collegate e avrà un bottone “Crea nuova offerta speciale” per aggiungerne una nuova.

[x] Accanto ad ogni offerta speciale è previsto un bottone che mi porterà a una pagina per modificarla.

Extra:

- [x] show
- [x] delete
- [x] create con la select dell'offerta (separated)
- [x] index

---

## FASE 4

Continuiamo l'esercizio precedente. Stessa repo.

Aggiungiamo una nuova fuzionalità per la nostra pizzeria: gli ingredienti!

[x] Ogni pizza può avere più ingredienti, e ogni ingrediente può essere collegato a più pizze.

[x] Prevediamo quindi una pagina per mostrare l'elenco di tutti gli ingredienti che utilizziamo nella nostra pizzeria
[x] che permetta anche di crearne di nuovi
[x] e di cancellarli.

[x] Nella pagina di creazione (e modifica) della singola pizza dobbiamo dare la possibilità di collegare uno o più ingredienti.

### Unrequested Extra

[x] show for ingredients
[x] rebuilt the sample db with all ingredients mapped to the correct pizzas, each ingedient has:
    - id
    - name
    - description
