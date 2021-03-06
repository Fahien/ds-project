
  <h1>Strutture Dati</h1>

  <p>Vademecum ristretto.</p>

  <h2 id="sigil_toc_id_1">Tipi di dati astratti (ADT)</h2>

  <p>I tipi di dati astratti sono il risultato del paradigma dell'astrazione applicato al design di strutture dati. È altresì un modello matematico di una struttura dati che specifica il tipo di dati contenuti, le operazioni possibili su questi e i parametri delle operazioni. Un ADT specifica cosa fa ogni operazione, ma non come. In Java possono essere espressi attraverso le <strong>interfacce</strong>.</p>

  <h2 id="sigil_toc_id_2">Stack</h2>

  <p>Uno stack è una collezione di oggetti che sono inseriti e rimossi seguendo il principio <strong>last-in, first-out</strong> (LIFO) e supporta le seguenti operazioni:</p>

  <ul>
    <li><strong>push(x)</strong>: inserisce l'elemento x;</li>
    <li><strong>pop()</strong>: cancella e restituisce l'ultimo elemento inserito;</li>
    <li><strong>top()</strong>: restituisce l'ultimo elemento inserito;</li>
    <li><strong>size()</strong>: restituisce il numero di elementi;</li>
    <li><strong>isEmpty()</strong>: restituisce true o false a seconda che sia vuoto o meno.</li>
  </ul>

  <h2 id="sigil_toc_id_3">Queue</h2>

  <p>Una coda è una collezione di oggetti che sono inseriti e rimossi seguendo il principio <strong>first-in, first-out</strong> (FIFO) e supporta le seguenti operazioni</p>

  <ul>
    <li><strong>enqueue(x)</strong>: inserisce l'elemento x alla fine della coda;</li>
    <li><strong>dequeue()</strong>: cancella e restituisce l'elemento all'inizio della coda;</li>
    <li><strong>front()</strong>: restituisce l'elemento all'inizio della coda;</li>
    <li><strong>size()</strong>: restituisce il numero di elementi;</li>
    <li><strong>isEmpty()</strong>: restituisce true o false a seconda che sia vuota o meno.</li>
  </ul>

  <h2 id="sigil_toc_id_4">Deque</h2>

  <p>La coda <i>double-ended</i>, o deque, è più generale dello stack e della queue, permettendo operazioni all'inizio e alla fine della coda e supporta le seguenti operazioni:</p>

  <ul>
    <li><strong>addFirst(x)</strong>: inserisce l'elemento x all'inizio;</li>
    <li><strong>addLast(x)</strong>: inserisce l'elemento x alla fine;</li>
    <li><strong>removeFirst()</strong>: cancella e restituisce il primo elemento;</li>
    <li><strong>removeLast()</strong>: cancella e restituisce l'ultimo elemento;</li>
    <li><strong>getFirst()</strong>: restituisce il primo elemento;</li>
    <li><strong>getLast()</strong>: restituisce l'ultimo elemento;</li>
    <li><strong>size()</strong>: restituisce il numero di elementi;</li>
    <li><strong>isEmpty()</strong>: restituisce true o false a seconda che sia vuota o meno.</li>
  </ul>

  <h2 id="sigil_toc_id_5">Notazione postfissa</h2>

  <p>La notazione postfissa è un metodo non ambiguo per la scrittura di espressioni aritmetiche senza l'utilizzo delle parentesi. Sia (exp<sub>1</sub> <strong>op</strong> exp<sub>2</sub>) una normale espressione in <i>notazione infissa</i>, la versione postfissa è pexp<sub>1</sub> pexp<sub>2</sub> <strong>op</strong>.</p>

  <p>Per esempio ((5 + 2) * (8 - 3)) / 4 corrisponde a 5 2 + 8 3 - * 4 /</p>

  <h2 id="sigil_toc_id_6">List</h2>

  <p>Una lista è una rappresentazione di una sequenza lineare di elementi e ogni locazione è definita da un <strong>index</strong> che va da zero al numero di elementi nella lista meno uno.</p>

  <p>Un <i>Array List</i> utilizza un semplice <strong>array</strong> per l'implementazione, e supporta le seguenti operazioni:</p>

  <ul>
    <li><strong>get(i)</strong>: restituisce l'elemento di indice i;</li>
    <li><strong>set(i, x)</strong>: restituisce e rimpiazza l'elemento di indice i con l'elemento x;</li>
    <li><strong>remove(i)</strong>: cancella e restituisce l'elemento di indice i;</li>
    <li><strong>size()</strong>: restituisce il numero di elementi;</li>
    <li><strong>isEmpty()</strong>: restituisce true o false a seconda che sia vuoto o meno.</li>
  </ul>

  <p>Una <i>Node List</i> utilizza il tipo di dato astratto <strong>Position</strong> con dei puntatori alla posizione precedente e successiva e supporta le seguenti operazioni:</p>

  <ul>
    <li><strong>first()</strong>: restituisce la posizione del primo elemento;</li>
    <li><strong>last()</strong>: restituisce la posizione dell'ultimo elemento;</li>
    <li><strong>prev(p)</strong>: restituisce la posizione dell'elemento che precede la posizione p;</li>
    <li><strong>next(p)</strong>: restituisce la posizione dell'elemento che segue la posizione p;</li>
    <li><strong>addBefore(p, x)</strong>: inserisce l'elemento x nella posizione che precede p e ne restituisce la posizione;</li>
    <li><strong>addAfter(p, x)</strong>: inserisce l'elemento x nella posizione che segue p e ne restituisce la posizione;</li>
    <li><strong>addLast(x)</strong>: inserisce l'elemento x alla fine;</li>
    <li><strong>addFirst(x)</strong>: inserisce l'elemento x all'inizio;</li>
    <li><strong>addLast(x)</strong>: inserisce l'elemento x alla fine;</li>
    <li><strong>remove(p)</strong>: cancella e restituisce l'elemento in posizione p;</li>
    <li><strong>set(p, x)</strong>: sostituisce con x l'elemento in posizione p, restituendolo in output;</li>
    <li><strong>size()</strong>: restituisce il numero di elementi;</li>
    <li><strong>isEmpty()</strong>: restituisce true o false a seconda che sia vuoto o meno.</li>
  </ul>

  <p><i>Sequence</i> fornisce le funzionalità di <strong>ambedue</strong> queste implementazioni, oltre alle seguenti operazioni, attraverso l'uso di due metodi ponte, atIndex(<i>i</i>) e indexOf(<i>p</i>):</p>

  <ul>
    <li><strong>getFirst()</strong>: restituisce il primo elemento;</li>
    <li><strong>getLast()</strong>: restituisce l'ultimo elemento;</li>
    <li><strong>removeFirst()</strong>: cancella e restituisce il primo elemento;</li>
    <li><strong>removeLast()</strong>: cancella e restituisce l'ultimo elemento.</li>
  </ul>

  <h2 id="sigil_toc_id_7">Iterator</h2>

  <p>Iterator è un <strong>design pattern</strong> software che astrae il processo di scansione attraverso una sequenza di elementi, uno alla volta, supportando i seguenti metodi:</p>

  <ul>
    <li><strong>next()</strong>: restituisce il prossimo elemento;</li>
    <li><strong>hasNext()</strong>: testa se ci sono altri elementi.</li>
  </ul>

  <h2 id="sigil_toc_id_8">Priority Queue</h2>

  <p>Una priority queue è una collezione di elementi con priorità che permette l'inserimento di elementi in modo arbitrario e la rimozione dell'elemento che ha priorità maggiore. Quando un elemento viene aggiunto alla coda a priorità, l'utente designa la sua priorità fornendo una <strong>chiave</strong> associata. I metodi principali sono:</p>

  <ul>
    <li><strong>insert(k, v)</strong>: inserisce e restituisce l'entry (k, v);</li>
    <li><strong>removeMin()</strong>: restituisce e rimuove l'entry con chiave più piccola;</li>
  </ul>

  <h2 id="sigil_toc_id_9">Tree</h2>

  <p>Un albero è una struttura dati astratta non lineare tra le più importanti nella computazione che conserva gli elementi in maniera gerarchica. Ad eccezione del top element, detto <strong>root</strong>, ogni elemento in un albero ha un genitore e zero o più figli.</p>

  <p>Formalmente un albero T è un insieme di <strong>nodi</strong> contenenti gli elementi e tali nodi hanno una relazione <strong>padre-figlio</strong> che soddisfa le seguenti proprietà:</p>

  <ul>
    <li>Se T non è vuoto, ha un nodo speciale, chiamato radice di T, ha non ha genitori.</li>
    <li>Ogni nodo v di T diverso dalla radice ha un unico nodo genitore w; ogni nodo che ha w come genitore è detto figlio di w.</li>
  </ul>

  <p>Due nodi figli dello stesso genitore sono detti <strong>sibling</strong> (fratelli). Un nodo v è <strong>esterno</strong> se non ha figli. Un nodo v è <strong>interno</strong> se ha uno o più figli. I nodi esterni sono anche chiamati <strong>foglie</strong>.</p>

  <p>Un nodo u è <strong>antenato</strong> di un nodo v se u = v oppure u è antenato del genitore di v. Un nodo v è <strong>discendente</strong> di un nodo u se u è un antenato di v. La <strong>profondità</strong> di un nodo è data dal numero di antenati del nodo meno il nodo stesso.</p>

  <p>L'<strong>altezza</strong> di un nodo è zero se è una foglia; altrimenti uno più l'altezza massima tra i figli del nodo. Il <strong>sottoalbero</strong> di T con radice al nodo v è l'albero che consiste di tutti i discendenti di v in T (incluso v), si scrive T<sub>w</sub>.</p>

  <p>Un <strong>arco</strong> di un albero T è una coppia di nodi (u,v) tale che u è genitore di v, o viceversa. Un <strong>cammino</strong> di T è una sequenza di nodi tali che ogni due nodi consecutivi nella sequenza formano un arco.</p>

  <p>Un albero è <strong>ordinato</strong> se c'è un ordine lineare tra i figli di ogni nodo.</p>

  <p>I metodi del TDA Tree sono:</p>

  <ul>
    <li><strong>size()</strong>;</li>
    <li><strong>isEmpty()</strong>;</li>
    <li><strong>iterator()</strong>: restituisce un iteratore di tutti gli elementi contenuti nei nodi dell'albero;</li>
    <li><strong>positions()</strong>: restituisce una collezione iterabile delle posizioni di tutti gli elementi dell'albero;</li>
    <li><strong>root()</strong>: restituisce la posizione della radice;</li>
    <li><strong>parent(p)</strong>: restituisce la posizione del genitore del nodo in posizione p;</li>
    <li><strong>children(p)</strong>: restituisce una collezione iterabile delle posizioni dei figli del nodo in posizione p;</li>
    <li><strong>isInternal(p)</strong>: testa se il nodo in p è interno;</li>
    <li><strong>isExternal(p)</strong>: testa se il nodo in p è esterno;</li>
    <li><strong>isRoot(p)</strong>: testa se il nodo in p è la radice;</li>
    <li><strong>replace(p, e)</strong>: rimpiazza l'elemento del nodo in p con e, restituendo il vecchio elemento.</li>
  </ul>

  <h2 id="sigil_toc_id_10">Binary Tree</h2>

  <p>Un albero binario è un albero ordinato con le seguenti proprietà:</p>

  <ul>
    <li>Ogni nodo ha al più due figli.</li>
    <li>Ogni nodo figlio è segnato come figlio sinistro o figlio destro.</li>
    <li>Il figlio sinistro precede il figlio destro nell'ordine dei figli del nodo.</li>
  </ul>

  <p>Un sottoalbero con radice che è figlia sinistra o destra di un nodo v è chiamato rispettivamente <strong>sottoalbero sinistro</strong> o <strong>sottoalbero destro</strong> di v. Un albero binario è <strong>proprio</strong>, ovvero <strong>completo</strong>, se ogni nodo ha o zero o due figli.</p>

  <p>Oltre ai metodi di Tree, vi sono operazioni addizionali:</p>

  <ul>
    <li><strong>left(p)</strong>: restituisce la posizione del figlio sinistro del nodo in p;</li>
    <li><strong>right(p)</strong>: restituisce la posizione del figlio destro del nodo in p;</li>
    <li><strong>hasLeft(p)</strong>: restituisce true se il nodo in p ha figlio sinistro;</li>
    <li><strong>hasRight(p)</strong>: restituisce true se il nodo in p ha figlio destro;</li>
  </ul>

  <h2 id="sigil_toc_id_11">Heap</h2>

  <p>Una migliore implementazione della priority queue è data usando una struttura dati chiamata <strong>binary heap</strong>. Questa permette l'inserimento e la rimozione in tempo logaritmico, attraverso l'utilizzo di un albero binario.</p>

  <p>Un heap è quindi un albero binario T che soddisfa due proprietà:</p>

  <ul>
    <li><strong>Heap-Order</strong>: in un heap T, per ogni posizione p diversa dalla radice, la chiave conservata in p è maggiore o uguale della chiave conservata nel genitore di p.</li>
    <li><strong>Albero binario completo</strong>: un heap T con altezza h è un albero binario completo se tutti i livelli 0, 1, 2, ..., h-1 di T hanno il massimo numero di nodi possibile.</li>
  </ul>

  <p>Oltre ai metodi del TDA Binary Tree, supporta i seguenti:</p>

  <ul>
    <li><strong>add(e)</strong>: inserisce una foglia, al primo nodo che ha meno di due figli, che contiene l'elemento e, restituendone la posizione;</li>
    <li><strong>remove()</strong>: rimuove l'ultimo nodo dell'albero restituendone l'elemento;</li>
  </ul>

  <h2 id="sigil_toc_id_12">Adaptable Priority Queue</h2>

  <p>Una coda a priorità adattabile, estende la struttura dati astratta della coda a priorità, fornendo funzionalità aggiuntive, quali la rimozione, il rimpiazzo delle chiavi e dei valori in maniera efficiente. Per far ciò utilizza un meccanismo per accedere direttamente ad un'entrata della coda: <strong>Locator</strong>, estende Entry e aggiunge un campo che tiene traccia del posto dove si trova tale entrata nella struttura dati usata per implementare la coda. Questo TDA estende il TDA Priority Queue con le seguenti operazioni:</p>

  <ul>
    <li><strong>remove(e)</strong>: rimuove e restituisce l'entry e;</li>
    <li><strong>replaceValue(e, v)</strong>: rimpiazza con v il valore dell'entry e, restituendo il vecchio valore;</li>
    <li><strong>replaceKey(e, k)</strong>: rimpiazza con k la chiave dell'entry e, restituendo la vecchia chiave;</li>
  </ul>

  <h2 id="sigil_toc_id_13">Map</h2>

  <p>Una mappa è una struttura dati astratta progettata per archiviare e recuperare in modo efficiente dei valori in base ad un'unica <strong>chiave</strong> che li identifica. Praticamente, una mappa archivia coppie di key-value (k, v), chiamate entries, dove k è la chiave e v il valore corrispondente. Le chiavi devono essere uniche.</p>

  <p>La mappe sono anche conosciute come array associativi, perché la chiave ha funzione simile all'index, tuttavia tale chiave può anche non essere numerica.</p>

  <p>Questo TDA supporta le seguenti operazioni:</p>

  <ul>
    <li><strong>size()</strong>;</li>
    <li><strong>isEmpty()</strong>;</li>
    <li><strong>get(k)</strong>: restituisce l'entry con chiave k, se esiste;</li>
    <li><strong>put(k, v)</strong>: Se non esiste entry con chiave uguale a k, aggiunge l'entry (k, v) non restituendo nulla, altrimenti rimpiazza con v il valore della entry esistente, restituendo il vecchio valore;</li>
    <li><strong>remove(k)</strong>: rimuove e restituisce l'entry con chiave k, se esiste;</li>
    <li><strong>keys()</strong>: restituisce una collezione iterabile delle chiavi;</li>
    <li><strong>values()</strong>: restituisce una collezione iterabile dei valori associati alle chiavi;</li>
    <li><strong>entries()</strong>: restituisce una collezione iterabile delle entries.</li>
  </ul>

  <h3 id="sigil_toc_id_14">Dictionary</h3>

  <p>Il tipo di dato astratto dictionary è molto simile all'ADT Map, tuttavia sono permessi più elementi con la stessa chiave.</p>

  <p>Annovera tra le sue operazioni:</p>

  <ul>
    <li><strong>size()</strong>;</li>
    <li><strong>isEmpty()</strong>;</li>
    <li><strong>find(k)</strong>: restituisce l'entry con chiave k, se esiste;</li>
    <li><strong>findAll(k)</strong>: restituisce una collezione iterabile di tutte le entry con chiave k;</li>
    <li><strong>entries()</strong>: restituisce una collezione iterabile delle entries.</li>
    <li><strong>insert(k, v)</strong>: inserisce e restituisce l'entrata (k, v).</li>
    <li><strong>remove(e)</strong>: rimuove e restituisce l'entrata e, se appartiene al dizionario.</li>
  </ul>

 <h2>Hash Table</h2>

  <p>Una <i>tabella hash</i> è una struttura dati usata per mettere in corrispondenza una <i>chiave</i> con un <i>valore</i>. Attraverso una tabella hash è possibile implementare TDA associativi come Map, Dictionary e Set, mediante un array.</p>

  <p>Nelle tabelle hash, invece di scorrere le entrate e confrontare le chiavi, si cerca di accedere agli elementi nella tabella in modo diretto trasformando le chiavi in indirizzi della tabella.</p>

  <p>Una tabella hash, per un dato tipo di chiavi, consiste di una <b>funzione hash</b> e un array chiamato <b>bucket</b>. Una funziona hash mappa un insieme di chiavi in un intervallo perfissato di interi e h(<i>x</i>) è chiamato <b>valore hash</b> di x. Comunque, tale computazione avviene in due fasi: mappatura di della chiave k in un intero chiamato <b>hash code</b>, mappatura dell'hash code in un intero nel range della capacità del bucket mediante una <i>funzione di compressione</i>.</p>

  <h3>Metodi per la computazione dell Hash Code</h3>

  <p>Tale computazione deve evitare quanto più possibile le collisioni.</p>

  <ul>
    <li><b>Cast ad intero</b>;</li>
    <li><b>Somma delle parti</b>;</li>
    <li><b>Hash Code Polinomiale</b>: sia <i>a</i> ≠ 0 e <i>a</i> ≠ 1, p(<i>a</i>) = k<sub>0</sub>a<sup>m-1</sup> + k<sub>1</sub>a<sup>m-2</sup> + ... + k<sub>m-2</sub>a + k<sub>m-1</sub>. Ogni componente della m-upla dà un contributo che dipende sia dal suo valore che dalla sua posizione.</li>
  </ul>

  <h3>Funzione di compressione</h3>

  <p>Siano <i>i</i> l'hash code di <i>k</i> ed <i>N</i> la capacità del bucket.</p>

  <ul>
    <li><b>Metodo della divisione</b>: |<i>i</i>| mod <i>N</i>;</li>
    <li><b>Metodo MAD</b> (multiply, add and divide): |a<i>i</i> + b| mod <i>N</i>, con a e b costanti intere scelte a caso tali che a &gt; 0, b ≥ 0, a mod <i>N</i> ≠ 0 ed <i>N</i> è primo.</li>
  </ul>

  <h3>Schemi di risoluzione di una collisione</h3>

  <p>Quali siano i metodi utilizzati per la computazione dell'hash code e per la compressione, c'è comunque il rischio che si verifichi una collisione, ovvero due chiavi che generano lo stesso valore hash. Per gestire questa situazione si può utilizzare una di queste due strategie:</p>

  <ul>
    <li><b>Separate Chaining</b>: le entrate che hanno generato la collisione vengono memorizzate in una sequenza;</li>
    <li><b>Open addressing</b>: una delle entrate che hanno generato la collisione viene sistemata in un'altra cella tella tabella.</li>
  </ul>
  
  <h2 id="sigil_toc_id_15">Binary Search Tree</h2>

  <p>Un albero di ricerca binario è un albero binario che memorizza in ciascun nodo una chiave in modo tale che se <i>u</i>, <i>v</i> e <i>w</i> sono tre nodi interni tale che <i>u</i> si trova nel sottoalbero sinistro di <i>v</i> e <i>w</i> si trova nel sotto albero destro di <i>v</i>, allora: key(u) ≤ key(v) ≤ key(w), ovvero è definita una relazione di ordine totale sulle chiavi.</p>

  <p>Una <strong>visita preorder</strong> di un albero T, visita prima la radice e poi i sottoalberi radicati nei suoi figli, seguendo l'ordine dei figli, se c'è un ordine. Una <strong>visita inorder</strong>, di un albero di ricerca binario visita le chiavi in ordine non decrescente. Una <strong>visita postorder</strong> in un certo senso è l'opposto della visita preorder, perché visita prima i sottoalberi radicati nei figli della radice, seguendone l'ordine, e poi la radice stessa.</p>

  <h2 id="sigil_toc_id_16">Set</h2>

  <p>Un insieme è una collezione di elementi non ordinata, senza duplicati. Un <strong>multiset</strong>, oppure <strong>bag</strong> è simile al set, ma consente la presenza di duplicati. Una <strong>multimap</strong> è simile ad una map, perché associa delle chiavi ai valori, tuttavia, la stessa chiave può avere più valori. Supporta, oltre ai metodi generici size() e isEmpty(), le seguenti operazioni:</p>

  <ul>
    <li><strong>union(B)</strong>: invocato su A, rimpiazza A con l'unione di A e B;</li>
    <li><strong>intersect(B)</strong>: invocato su A, rimpiazza A con l'intersezione di A e B;</li>
    <li><strong>subtract(B)</strong>: invocato su A, rimpiazza A con la differenza di A e B;</li>
  </ul>

  <h3 id="sigil_toc_id_17">Template method</h3>

  <p>Per rendere più efficienti le operazioni insiemistiche è utile definire una relazione d'ordine totale sugli elementi dell'insieme, così da poter utilizzare un algoritmo generico di merge.</p>

  <p>Nello specifico, l'algoritmo generico di merge, utilizza i metodi ausiliari <i>bothAreEqual</i>, <i>aIsLess</i> e <i>bIsLess</i>, e le operazioni di <i>intersection</i>, <i>union</i> e <i>subtract</i> riscrivono alcuni di questi:</p>

  <ul>
    <li><strong>Intersection</strong>: riscrive il metodo <i>bothAreEqual</i> poiché l'intersezione ha bisogno di sapere quali elementi appaiono in entrambi gli insiemi;</li>
    <li><strong>Union</strong>: copia ogni elemento, ignorando eventuali duplicati, quindi riscrive tutti e tre i metodi ausiliari;</li>
    <li><strong>Subtract</strong> riscrive il metodo <i>aIsLess</i>, poiché ha bisogno solo degli elementi che appaiono in A e non in B.</li>
  </ul>

  <h2 id="sigil_toc_id_18">Partition</h2>

  <p>Una partizione è una collezione di insiemi S<sub>1</sub>, S<sub>2</sub>, ..., S<sub>k</sub> a due a due disgiunti, ovvero ∀ i ≠ j S<sub>i</sub> ∩ S<sub>j</sub> = ∅</p>

  <p>Per un implementazione efficiente, ad ogni elemento possiamo associare l'insieme a cui appartiene. Quindi avremo una <i>mappa</i> che ha come voci le coppie elemento-insieme.</p>

  <p>Quando si esegue un'unione, spostiamo sempre gli elementi dall'insieme più piccolo a quello più grande, in modo da avere l'euristica dell'<strong>unione pesata</strong>.</p>

  <p>Per la rimozione di un insieme da rimuovere, invece di cercare la posizione di tale insieme, è utile inserire nella classe che implementa <i>Set</i> una variabile che tenga traccia di questa posizione.</p>

  <p>Il TDA Partition supporta i seguenti metodi:</p>

  <ul>
    <li><strong>makeSet(x)</strong>: crea l'insieme contenente il singolo elemento x e lo aggiunge alla partizione;</li>
    <li><strong>union(A, B)</strong>: aggiunge alla partizione l'insieme unione di A e B, distruggendoli;</li>
    <li><strong>find(x)</strong>: restituisce l'insieme che contiene l'elemento x;</li>
  </ul>

  <h2 id="sigil_toc_id_19">Graph</h2>

  <p>Un grafo è un modo di rappresentare relazioni che esistono tra coppie di oggetti. È composto da un insieme di oggetti, chiamati vertici, e da una collezione di collegamenti tra tali vertici, chiamati archi.</p>

  <p>Gli archi di un grafo possono essere direzionati e non, quindi se in un grafo tutti gli archi sono direzionati, tale grafo è <strong>direzionato</strong>, se invece tutti gli archi sono non direzionati, tale grafo è <strong>non direzionato</strong>. I grafi <strong>misti</strong> contengono sia archi direzionati che non direzionati.</p>

  <ul>
    <li><strong>Arco direzionato</strong>: (u, v), coppia ordinata di vertici, u è l'origine, v è la destinazione.</li>
    <li><strong>Arco non direzionato</strong>: (u, v), coppia non ordinata di vertici.</li>
  </ul>

  <p>I vertici collegati da un arco sono chiamati <strong>estremità</strong> dell'arco. Se un arco è diretto, la sua prima estremità è l'<strong>origine</strong> e l'altra è la <strong>destinazione</strong>. Due vertici <i>u</i> e <i>v</i> sono <strong>adiacenti</strong> se esiste un arco (<i>u</i>, <i>v</i>). Un arco <strong>incide</strong> un vertice se tale vertice è sua estremità. Gli archi <strong>uscenti</strong> di un vertice sono quegli archi diretti la cui origine è il vertice stesso. Gli archi <strong>entranti</strong> di un vertice sono quegli archi diretti la cui destinazione è il vertice stesso. Il <strong>grado</strong> di un vertice è il numero di archi che incidono sul vertice stesso. Un <strong>autociclo</strong> è un arco che ha entrambe le estremità uguali ad uno stesso vertice.</p>

  <p>Un <strong>percorso</strong> è una sequenza che comincia e finisce con un vertice, in cui si alternano vertici e archi e ciascun arco è situato tra le sue estremità. Un <strong>percorso semplice</strong> è un percorso in cui tutti i vertici e tutti gli archi sono distinti. Un <strong>ciclo</strong> è un percorso che inizia e finisce nello stesso vertice. Un <strong>ciclo semplice</strong> è un ciclo in cui tutti i vertici sono distinti ad eccezione del primo e dell'ultimo. Un <strong>percorso diretto</strong> è un percorso in cui ogni arco è diretto ed è preceduto dalla sua origine e seguito dalla sua destinazione. Un <strong>ciclo diretto</strong> è un percorso diretto che comincia e termina nello stesso vertice.</p>

  <h3 id="sigil_toc_id_20">Metodi supportati</h3>

  <ul>
    <li><strong>numVertices()</strong>: restituisce il numero di vertici;</li>
    <li><strong>numEdges()</strong>: restituisce il numero di archi;</li>
    <li><strong>endVertices(e)</strong>: restituisce un array con le estremità di e;</li>
    <li><strong>opposite(v, e)</strong>: restituisce il vertice incidente su e opposto a v;</li>
    <li><strong>areAdjacent(v, w)</strong>: restituisce true se i vertici v e w sono adiacenti;</li>
    <li><strong>incidentEdges(v)</strong>: restituisce una collezione iterabile degli archi incidenti su v;</li>
    <li><strong>vertices()</strong>: restituisce una collezione iterabile dei vertici;</li>
    <li><strong>edges()</strong>: restituisce una collezione iterabile degli archi;</li>
    <li><strong>replace(v, x)</strong>: sostituisce l'elemento nel vertice v con x e restituisce il vecchio elemento;</li>
    <li><strong>replace(e, x)</strong>: sostituisce l'elemento nell'arco e con x e restituisce il vecchio elemento;</li>
    <li><strong>insertVertex(x)</strong>: inserisce e restituisce un vertice che memorizza x;</li>
    <li><strong>insertEdge(v, w, x)</strong>: inserisce e restituisce un arco (v, w) che memorizza x;</li>
    <li><strong>removeVertex(v)</strong>: cancella il vertice v e i suoi archi incidenti restituendone l'elemento;</li>
    <li><strong>removeEdge(e)</strong>: cancella l'arco e restituendone l'elemento;</li>
  </ul>

  <h3 id="sigil_toc_id_21">Traversal</h3>

  <p>Formalmente, un traversal è una procedura sistematica per l'esplorazione di un grafo esaminando tutti i vertici e gli archi. È efficiente se visita tutti i vertici e gli archi in tempo proporzionale al loro numero, quindi lineare.</p>

  <h3 id="sigil_toc_id_22">Depth-First Search</h3>

  <p>DFS è un algoritmo di traversal. Comincia in un vertice <i>s</i> di G, quindi si considera un arco incidente nel vertice corrente e se tale arco di porta in un vertice che è già visitato, lo si ignora; altrimenti, il nuovo vertice diventa quello corrente e si marca come visitato. Se si raggiunge un vicolo cieco, si torna indietro e si provano gli archi non ancora visitati. Il processo termina quano il <i>backtracking</i> ci riporta al vertice iniziale <i>s</i>.</p>

  <h3 id="sigil_toc_id_23">Dijkstra's Algorithm</h3>

  <p>Si tratta di un algoritmo che accresce iterativamente una <i>nuvola</i> di vertici intorno ad <i>s</i>, vertice iniziale, in ordine della distanza da <i>s</i>. Quindi, ad ogni iterazione, il prossimo vertice scelto è il vertice esterno alla nuvola che è più vicino a <i>s</i>. L'algoritmo termina quando non ci sono più vertici esterni alla nuvola e a questo punto avremo il percorso più breve da <i>s</i> a tutti i vertici di G raggiungibili da <i>s</i>.</p>

  <h3 id="sigil_toc_id_24">Kruskal's Algorithm</h3>

  <p>Si tratta di un algoritmo per la costruzione del minimum spanning tree. Inizialmente ogni vertice ha il proprio cluster. L'algoritmo quindi considera un arco alla volta, ordinati per peso crescente. Se un arco <i>e</i> collega vertici in due cluster differenti, allora <i>e</i> viene aggiunto all'insieme degli archi del minimum spanning tree, e i due alberi vengono uniti con l'aggiunta di <i>e</i>. Se, invece, <i>e</i> collega due vertici nello stesso cluster, allora viene scartato. Appena l'algoritmo ha aggiungo abbastanza archi per formare lo spanning tree, termina e restituisce quest'albero che sarà il minimo albero ricoprente.</p>
