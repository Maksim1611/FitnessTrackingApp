# FitnessTrackingApp

A workout tracking backend inspired by Hevy — built with Java 21 and Spring Boot.
REST API designed to power a future Flutter mobile app.

## Features

- **Workout logging** — routines with target sets, live workout sessions, sets with weight/reps/RPE
- **Auto-progression** — per-exercise suggestions for the next session based on RPE and rep performance
- **Personal records** — automatic PR detection on every completed set
- **Stats** — per-exercise progress, weekly volume, muscle-group volume balance
- **Tiered badges** — Bronze → Mythical ranks per category, including per-muscle-group volume
- **Body measurements** — weight, body fat %, circumferences, progress photos
- **Social** — follow users, public profiles, user search, paginated activity feed
- **Auth** — JWT with rotating single-use refresh tokens, scheduled token cleanup

## Tech

- Java 21, Spring Boot, Spring Security (stateless JWT)
- MySQL with Flyway migrations
- JPA/Hibernate (schema validated against migrations)

## Running locally

1. MySQL running on `localhost:3306` (or set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`)
2. `./mvnw spring-boot:run`
3. API base: `http://localhost:8080/api/v1`

Flyway creates the schema automatically on the first run.