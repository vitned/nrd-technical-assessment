INSERT INTO myschema.PERSON (ID, EMAIL, FIRST_NAME, JOINED_DATE, LAST_NAME)
VALUES (1, 'test1@test.com', 'FirstName_1', '2020-11-30', 'LastName_1');

INSERT INTO myschema.PERSON (ID, EMAIL, FIRST_NAME, JOINED_DATE, LAST_NAME)
VALUES (2, 'test2@test.com', 'FirstName_2', '2020-10-01', 'LastName_2');

INSERT INTO myschema.PERSON (ID, EMAIL, FIRST_NAME, JOINED_DATE, LAST_NAME)
VALUES (3, 'test3@test.com', 'FirstName_3', '2020-10-02', 'LastName_3');

create sequence hibernate_sequence start with 10 increment by 1;