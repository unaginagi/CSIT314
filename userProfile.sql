DROP TABLE userProfile;

CREATE TABLE userProfile (
    profileID INT(11) PRIMARY KEY,
    description VARCHAR(50) NOT NULL
);

ALTER TABLE userProfile
  MODIFY profileID int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
COMMIT;

INSERT INTO userProfile (description)
VALUES
    ('User Admin'),
    ('Cinema Owner'),
    ('Cinema Manager'),
	('Customer');

select * from userProfile;