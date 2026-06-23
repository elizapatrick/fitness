# Fitness Tracker (Full-Stack)

Projekt mit Spring Boot 3 (MongoDB) Backend und React (Vite) Frontend.

Voraussetzungen:

- Java 17
- Maven
- Node.js
- MongoDB

Backend starten:

```bash
cd backend
mvn spring-boot:run
```

Frontend starten:

```bash
cd frontend
npm install
npm run dev
```

Wichtig: Starte nicht direkt `src/main.jsx` mit Node. Die Datei wird von Vite gebundelt, daher muss das Projekt über `npm run dev` oder `npm start` laufen.

MongoDB Beispiel-Daten importieren (von Projekt-Root):

```bash
mongoimport --db fitnessdb --collection exercises --file data/exercises.json --jsonArray
mongoimport --db fitnessdb --collection trainings --file data/trainings.json --jsonArray
mongoimport --db fitnessdb --collection plans --file data/plans.json --jsonArray
mongoimport --db fitnessdb --collection progress --file data/progress.json --jsonArray
mongoimport --db fitnessdb --collection users --file data/users.json --jsonArray
```

Weitere Details siehe `docs/DOCUMENTATION.md`.
