CREATE TABLE IF NOT EXISTS workouts
(
    id          uuid      NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    name        varchar   NOT NULL,
    description varchar,
    user_id     varchar,
    admin_user  boolean   NOT NULL             default false,
    created_at  timestamp NOT NULL             default current_timestamp,
    updated_at  timestamp NOT NULL             default current_timestamp
);

CREATE FUNCTION update_updated_on_workouts()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = now();
    RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_workouts_updated_on
    BEFORE UPDATE
    ON workouts
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_on_workouts();