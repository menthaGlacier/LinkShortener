CREATE TABLE link
(
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    short_code   VARCHAR(8)   NOT NULL UNIQUE,
    original_url VARCHAR(2048) NOT NULL,
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT now(),
    is_active    BOOLEAN       NOT NULL DEFAULT TRUE
);

COMMENT ON TABLE link IS 'Shortened links';
COMMENT ON COLUMN link.id is 'ID of a link';
COMMENT ON COLUMN link.short_code is 'Short code of a link';
COMMENT ON COLUMN link.original_url is 'Original long URL';
COMMENT ON COLUMN link.created_at is 'Creation time of a link';
COMMENT ON COLUMN link.is_active is 'Is link active';
