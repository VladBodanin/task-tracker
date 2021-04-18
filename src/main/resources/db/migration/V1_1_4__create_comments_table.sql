CREATE TABLE IF NOT EXISTS comments
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    value               TEXT         NOT NULL,
    author_id           BIGINT       NOT NULL,
    task_id             BIGINT       NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS "comments_author_id_idx" ON comments USING btree (author_id);
CREATE INDEX IF NOT EXISTS "comments_task_id_idx" ON comments USING btree (task_id);