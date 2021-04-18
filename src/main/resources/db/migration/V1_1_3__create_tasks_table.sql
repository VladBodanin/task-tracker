CREATE TABLE IF NOT EXISTS tasks
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    created_at          TIMESTAMP    NOT NULL,
    topic               VARCHAR(300) NOT NULL,
    description         TEXT,
    author_id           BIGINT       NOT NULL,
    performer_id        BIGINT       NOT NULL,
    state               VARCHAR(50)  NOT NULL,
    unit_id             BIGINT       NOT NULL,
    FOREIGN KEY (unit_id) REFERENCES units (id) ON DELETE CASCADE,
    FOREIGN KEY (performer_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS "tasks_unit_id_idx" ON tasks USING btree (unit_id);
CREATE INDEX IF NOT EXISTS "tasks_performer_id_idx" ON tasks USING btree (performer_id);
CREATE INDEX IF NOT EXISTS "tasks_author_id_idx" ON tasks USING btree (author_id);