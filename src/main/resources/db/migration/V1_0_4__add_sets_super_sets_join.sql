CREATE TABLE IF NOT EXISTS sets_super_sets_join
(
    id            uuid NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    set_id        uuid NOT NULL REFERENCES sets(id),
    super_set_id   uuid NOT NULL REFERENCES super_sets(id),
    created_at    timestamp NOT NULL default current_timestamp
);
