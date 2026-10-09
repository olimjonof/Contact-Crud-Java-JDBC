# Contact-Crud-Java-JDBC

A console contact book in plain Java using JDBC and PostgreSQL. It supports adding, listing, searching, finding by phone and deleting contacts (name, surname, unique phone number).

## Structure

```
src/
  Main.java        console menu
  controller/      user input handling
  service/         validation and business rules
  repository/      JDBC queries
  dto/             Contact model
  util/            database connection and table creation
```

## Configuration

The database connection is read from environment variables:

| Variable | Default |
|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/db_lesson` |
| `DB_USERNAME` | `postgres` |
| `DB_PASSWORD` | *(empty)* |

The PostgreSQL driver is in `libs/`; the `contact` table is created automatically on start.
