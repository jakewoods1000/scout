CREATE TABLE IF NOT EXISTS super_sets_workouts_join
(
    id                  uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    super_set_id        uuid NOT NULL REFERENCES super_sets(id),
    super_set_order     integer NOT NULL,
    workout_id          uuid NOT NULL REFERENCES workouts(id),
    created_at          timestamp NOT NULL default current_timestamp
);