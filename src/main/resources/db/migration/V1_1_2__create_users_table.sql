CREATE TABLE IF NOT EXISTS users
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name                VARCHAR(255) NOT NULL UNIQUE,
    unit_id             BIGINT       NOT NULL,
    FOREIGN KEY (unit_id) REFERENCES units (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS "users_unit_id_idx" ON users USING btree (unit_id);