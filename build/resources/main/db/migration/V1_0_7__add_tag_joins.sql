CREATE TABLE IF NOT EXISTS exercises_tags_join
(
    id            uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    exercise_id   uuid NOT NULL REFERENCES exercises(id),
    tag_id        uuid NOT NULL REFERENCES tags(id),
    created_at    timestamp NOT NULL default current_timestamp
);

CREATE TABLE IF NOT EXISTS sets_tags_join
(
    id            uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    set_id        uuid NOT NULL REFERENCES sets(id),
    tag_id        uuid NOT NULL REFERENCES tags(id),
    created_at    timestamp NOT NULL default current_timestamp
);

CREATE TABLE IF NOT EXISTS super_sets_tags_join
(
    id            uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    super_set_id  uuid NOT NULL REFERENCES super_sets(id),
    tag_id        uuid NOT NULL REFERENCES tags(id),
    created_at    timestamp NOT NULL default current_timestamp
);

CREATE TABLE IF NOT EXISTS workouts_tags_join
(
    id          uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    workout_id  uuid NOT NULL REFERENCES workouts(id),
    tag_id      uuid NOT NULL REFERENCES tags(id),
    created_at  timestamp NOT NULL default current_timestamp
);