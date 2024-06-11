DROP TABLE IF EXISTS goal;

CREATE TABLE goal (
    id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
    version BIGINT DEFAULT 0,
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    title VARCHAR(255) NOT NULL,
    status VARCHAR(100) NOT NULL,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    smart_detail VARCHAR(1000),
    measurable_detail VARCHAR(1000),
    achievable_detail VARCHAR(1000),
    relevant_detail VARCHAR(1000),
    timebound_detail VARCHAR(1000)
);