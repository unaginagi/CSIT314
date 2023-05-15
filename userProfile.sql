CREATE TABLE userProfile (
  id INT AUTO_INCREMENT,
  description VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO userProfile (id, description)
VALUES (1,'Student');
INSERT INTO userProfile (id, description)
VALUES (2,'Adult');
INSERT INTO userProfile (id, description)
VALUES (3,'Senior');
INSERT INTO userProfile (id, description)
VALUES (4,'Staff');
INSERT INTO userProfile (id, description)
VALUES (5,'Admin');
