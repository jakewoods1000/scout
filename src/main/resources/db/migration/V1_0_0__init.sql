CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS exercises
(
    id            uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    name          varchar NOT NULL,
    description   varchar,
    created_at    timestamp NOT NULL default current_timestamp,
    updated_at    timestamp NOT NULL default current_timestamp
);

CREATE  FUNCTION update_updated_on_exercises()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_exercises_updated_on
    BEFORE UPDATE
    ON exercises
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_on_exercises();