DROP TABLE IF EXISTS daily_discipline;

CREATE TABLE daily_discipline
(
    id                 VARCHAR(36)  NOT NULL,
    version            INTEGER       DEFAULT NULL,
    created_date       TIMESTAMP(6)  DEFAULT NULL,
    last_modified_date TIMESTAMP(6)  DEFAULT NULL,
    title              VARCHAR(255) NOT NULL,
    description        VARCHAR(1000) DEFAULT NULL,
    status             SMALLINT     NOT NULL,
    minimum_value      BIGINT        DEFAULT NULL,

    competency_id      VARCHAR(36)   DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (competency_id) REFERENCES competency (id)
) ENGINE = InnoDB;