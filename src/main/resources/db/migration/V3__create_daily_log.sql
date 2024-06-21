DROP TABLE IF EXISTS daily_log;

CREATE TABLE daily_log (
    id VARCHAR(36) NOT NULL,
    version               INTEGER       DEFAULT NULL,
    created_date          TIMESTAMP(6)  DEFAULT NULL,
    last_modified_date    TIMESTAMP(6)  DEFAULT NULL,
    date TIMESTAMP(6) DEFAULT NULL,

    numeric_value BIGINT DEFAULT NULL,
    time_in_minutes_value BIGINT DEFAULT NULL,
    boolean_value BOOLEAN DEFAULT NULL,

    daily_discipline_id VARCHAR(36) DEFAULT NULL,

    PRIMARY KEY (id),
    CONSTRAINT FOREIGN KEY (daily_discipline_id) REFERENCES daily_discipline(id)
) ENGINE = InnoDB;