create table users
(
    id               uuid         NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    username         varchar(50)  not null,
    password         varchar(500) not null,
    version          int          not null,
    token_validation timestamp    not null,
    created_at       timestamp    not null             default current_timestamp,
    test             boolean      not null
);

create table user_validations (
    id                   uuid        NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id                 uuid        not null REFERENCES users (id),
    token                varchar(50) not null,
    token_issue          timestamp   not null,
    password_reset_token varchar(50) not null,
    password_reset_issue timestamp   not null,
    version              int         not null,
    created_at           timestamp   not null             default current_timestamp
);
