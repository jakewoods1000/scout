create table users
(
    id             varchar     NOT NULL PRIMARY KEY,
    email          varchar(50) not null,
    email_verified boolean     not null,
    created_at     timestamp   not null default current_timestamp,
    updated_at     timestamp   not null default current_timestamp,
    deactivated_at timestamp,
    test           boolean     not null default false
);

CREATE FUNCTION update_updated_on_users()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at
= now();
RETURN NEW;
END;
$$
language 'plpgsql';

CREATE TRIGGER update_users_updated_on
    BEFORE UPDATE
    ON users
    FOR EACH ROW
    EXECUTE PROCEDURE update_updated_on_users();