CREATE TABLE IF NOT EXISTS tags
(
    id            uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    name          varchar NOT NULL,
    description   varchar,
    type          varchar NOT NULL,
    created_at    timestamp NOT NULL default current_timestamp,
    updated_at    timestamp NOT NULL default current_timestamp
);

CREATE  FUNCTION update_updated_on_tags()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_tags_updated_on
    BEFORE UPDATE
    ON tags
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_on_tags();