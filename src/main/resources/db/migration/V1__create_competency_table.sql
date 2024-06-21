DROP TABLE IF EXISTS competency;

CREATE TABLE competency
(
    id                 VARCHAR(36)  NOT NULL,
    version            INTEGER       DEFAULT NULL,
    created_date       TIMESTAMP(6)  DEFAULT NULL,
    last_modified_date TIMESTAMP(6)  DEFAULT NULL,
    title              VARCHAR(255) NOT NULL,
    description        VARCHAR(1000) DEFAULT NULL,
    status             TINYINT,
    start_date         TIMESTAMP     DEFAULT NULL,
    end_date           TIMESTAMP     DEFAULT NULL,

    PRIMARY KEY (id)
) ENGINE = InnoDB;