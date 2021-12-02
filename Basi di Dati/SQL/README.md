## Istruzioni per la creazione di un utente, e per la gestione dei privilegi
Per creare un nuovo utente si usa il comando:
```SQL
CREATE USER Professore PASSWORD 'LaPasswordSegretaPerIlProfessore'
```

Per garantire/revocare dei privilegi si usa il comando:
```SQL
GRANT privileges ON table TO user -- privilegies can be ALL
```

