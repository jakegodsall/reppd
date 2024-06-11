DROP DATABASE IF EXISTS reppd_db;
DROP USER IF EXISTS `reppd_admin`@`localhost`;

CREATE DATABASE reppd_db;
CREATE USER `reppd_admin`@`localhost` IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON reppd_db.* TO `reppd_admin`@`localhost`;
FLUSH PRIVILEGES;