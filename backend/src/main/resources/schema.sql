CREATE SCHEMA IF NOT EXISTS myschema;
DROP TABLE IF EXISTS myschema.PERSON;
CREATE TABLE IF NOT EXISTS myschema.PERSON (ID bigint not null, EMAIL varchar(255), FIRST_NAME varchar(255), JOINED_DATE date, LAST_NAME varchar(255), primary key (id));