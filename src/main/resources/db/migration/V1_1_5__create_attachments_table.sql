CREATE TABLE IF NOT EXISTS attachments
(
    id                  BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    download_link        TEXT         NOT NULL,
    task_id             BIGINT       NOT NULL,
    FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS "attachments_task_id_idx" ON attachments USING btree (task_id);