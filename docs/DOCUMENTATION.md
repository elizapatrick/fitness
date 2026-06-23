# Fitness Tracker - Projektübersicht

Kurzbeschreibung: Full-Stack Fitness-Tracker mit Java Spring Boot (MongoDB) Backend und React (Vite) Frontend. Enthält CRUD-APIs, Statistikendpunkte und Beispiel-Daten für MongoDB Compass / mongoimport.

**Architekturdiagramm**

![Architektur-Platzhalter](screenshots/architecture.png)

**Datenmodell**

- `users` (id, username, email, gewicht)
- `exercises` (id, name, muskelgruppe, beschreibung)
- `trainings` (id, titel, datum, dauer, trainingsart, exerciseIds)
- `plans` (id, name, beschreibung, trainingIds)
- `progress` (id, exerciseId, gewicht, wiederholungen, datum)

**MongoDB Collections**

- users
- exercises
- trainings
- plans
- progress

**API Endpunkte**

Training:

- POST /api/trainings
- GET /api/trainings
- GET /api/trainings/{id}
- PUT /api/trainings/{id}
- DELETE /api/trainings/{id}
- GET /api/trainings/filter?type=Krafttraining

Exercise:

- POST /api/exercises
- GET /api/exercises
- PUT /api/exercises/{id}
- DELETE /api/exercises/{id}

TrainingPlan:

- POST /api/plans
- GET /api/plans
- PUT /api/plans/{id}
- DELETE /api/plans/{id}

Progress:

- POST /api/progress
- GET /api/progress
- PUT /api/progress/{id}
- DELETE /api/progress/{id}

Statistiken / Diagramme:

- GET /api/statistics
- GET /api/progress/chart

**CRUD Beispiele (curl)**

Erstelle Exercise:

```bash
curl -X POST http://localhost:8080/api/exercises -H "Content-Type: application/json" -d '{"name":"Neue Übung","muskelgruppe":"Core","beschreibung":"..."}'
```

Hole Trainings:

```bash
curl http://localhost:8080/api/trainings
```

Filter Trainings nach Typ:

```bash
curl "http://localhost:8080/api/trainings/filter?type=Krafttraining"
```

**MongoDB Benutzer und Rechte**

Erstelle `readWrite` Benutzer:

```js
db.createUser({
  user: "fitness_rw",
  pwd: "fitness123",
  roles: [{ role: "readWrite", db: "fitnessdb" }],
});
```

Erstelle `read` Benutzer:

```js
db.createUser({
  user: "fitness_read",
  pwd: "fitness123",
  roles: [{ role: "read", db: "fitnessdb" }],
});
```

Beispiele:

- `fitness_read` kann GET-Anfragen ausführen, aber keine Inserts/Deletes
- `fitness_rw` kann vollständige CRUD-Operationen ausführen

**Backup & Restore**

Backup:

```bash
mongodump --db fitnessdb --out backup
```

Restore:

```bash
mongorestore --db fitnessdb backup/fitnessdb
```

**Beispiel-Daten importieren (mongoimport)**

```bash
mongoimport --db fitnessdb --collection exercises --file data/exercises.json --jsonArray
mongoimport --db fitnessdb --collection trainings --file data/trainings.json --jsonArray
mongoimport --db fitnessdb --collection plans --file data/plans.json --jsonArray
mongoimport --db fitnessdb --collection progress --file data/progress.json --jsonArray
mongoimport --db fitnessdb --collection users --file data/users.json --jsonArray
```

**Frontend**

- Frontend mit Vite + React + Material UI
- Seiten: Dashboard, Trainingsverwaltung, Übungsverwaltung, Trainingspläne, Fortschritt, Statistik
- Diagramme mit Recharts

**Screenshots**

- Platzhalter: `screenshots/dashboard.png`, `screenshots/trainings.png`, `screenshots/statistics.png`

**Fazit**

Dieses Projekt liefert eine solide Grundlage für einen Fitness-Tracker. Erweiterungen: Authentifizierung, Nutzerprofile, Datei-Uploads, erweiterte Auswertungen.
