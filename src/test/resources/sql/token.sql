DELETE from token;
INSERT INTO token (token,application, date_created)
VALUES ('token','app',CURRENT_TIMESTAMP);