--- CREATE EVENT PUBLICATION TABLE FOR INTER_MODULE EVENTING
CREATE TABLE IF NOT EXISTS event_publication
(
    id                     UUID NOT NULL,
    listener_id            TEXT NOT NULL,
    event_type             TEXT NOT NULL,
    serialized_event       TEXT NOT NULL,
    publication_date       TIMESTAMP WITH TIME ZONE NOT NULL,
    completion_date        TIMESTAMP WITH TIME ZONE,
    status                 TEXT,
    completion_attempts    INT,
    last_resubmission_date TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (id)
    );
CREATE INDEX IF NOT EXISTS event_publication_serialized_event_hash_idx ON event_publication USING hash(serialized_event);
CREATE INDEX IF NOT EXISTS event_publication_by_completion_date_idx ON event_publication (completion_date);

--- DISCS MODULE TABLE CREATION
CREATE SCHEMA discs;

CREATE TABLE discs.manufacturer
(
    id         UUID PRIMARY KEY DEFAULT uuidv7(),
    key        VARCHAR(250) NOT NULL,
    name       VARCHAR(250) NOT NULL,
    createdDT  TIMESTAMP    NOT NULL,
    modifiedDT TIMESTAMP,
    UNIQUE (key)
);

CREATE TABLE discs.plastic
(
    id         UUID PRIMARY KEY DEFAULT uuidv7(),
    key        VARCHAR(250) NOT NULL,
    name       VARCHAR(250) NOT NULL,
    createdDT  TIMESTAMP    NOT NULL,
    modifiedDT TIMESTAMP,
    UNIQUE (key)
);

CREATE TABLE discs.mold
(
    id         UUID PRIMARY KEY DEFAULT uuidv7(),
    key        VARCHAR(250) NOT NULL,
    name       VARCHAR(250) NOT NULL,
    speed      INT,
    glide      INT,
    turn       INT,
    fade       INT,
    diameter   DECIMAL,
    height     DECIMAL,
    rimDepth   DECIMAL,
    rimWidth   DECIMAL,
    createdDT  timestamp    NOT NULL,
    modifiedDT timestamp,
    UNIQUE (key)
);

CREATE TABLE discs.disc
(
    id              UUID PRIMARY KEY DEFAULT uuidv7(),
    key             VARCHAR(250) NOT NULL,
    weight          DECIMAL,
    manufacturer_id UUID         NOT NULL,
    plastic_id      UUID         NOT NULL,
    mold_id         UUID         NOT NULL,
    createdDT       timestamp    NOT NULL,
    modifiedDT      timestamp,
    UNIQUE (key),
    CONSTRAINT fk_manufacturer FOREIGN KEY (manufacturer_id) REFERENCES discs.manufacturer (id),
    CONSTRAINT fk_plastic FOREIGN KEY (plastic_id) REFERENCES discs.plastic (id),
    CONSTRAINT fk_mold FOREIGN KEY (mold_id) REFERENCES discs.mold (id)
);

--- LOADER MODULE TABLE CREATION
CREATE SCHEMA loader;

CREATE TABLE loader.page
(
    id            UUID PRIMARY KEY DEFAULT uuidv7(),
    path          VARCHAR(250) NOT NULL,
    isActive      BOOLEAN,
    lastVisitedDT TIMESTAMP,
    source_id     UUID         NOT NULL,
    createdDT     TIMESTAMP    NOT NULL,
    modifiedDT    TIMESTAMP,
    UNIQUE (path, source_id)
);

CREATE TABLE loader.source
(
    id         UUID PRIMARY KEY DEFAULT uuidv7(),
    key        VARCHAR(250) NOT NULL,
    name       VARCHAR(250) NOT NULL,
    baseURL    VARCHAR(250) NOT NULL,
    priority   INT          NOT NULL,
    createdDT  TIMESTAMP    NOT NULL,
    modifiedDT TIMESTAMP,
    UNIQUE (key)
);
