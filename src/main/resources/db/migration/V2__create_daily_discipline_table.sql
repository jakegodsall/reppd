DROP TABLE IF EXISTS daily_discipline;

CREATE TABLE daily_discipline
(
    id                    VARCHAR(36)  NOT NULL,
    version               INTEGER       DEFAULT NULL,
    created_date          TIMESTAMP(6)  DEFAULT NULL,
    last_modified_date    TIMESTAMP(6)  DEFAULT NULL,
    title                 VARCHAR(255) NOT NULL,
    description           VARCHAR(1000) DEFAULT NULL,
    numeric_value         BIGINT        DEFAULT NULL,
    time_in_minutes_value BIGINT        DEFAULT NULL,
    boolean_value         BOOLEAN       DEFAULT NULL,

    competency_id         VARCHAR(36)  NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (competency_id) REFERENCES competency (id)
) ENGINE = InnoDB;