DROP TABLE userProfile;

CREATE TABLE userProfile (
    profileID INT(11) PRIMARY KEY,
    description VARCHAR(50) NOT NULL
);

INSERT INTO userProfile (profileID, description)
VALUES
    (1, 'User Admin'),
    (2, 'Cinema Owner'),
    (3, 'Cinema Manager'),
	(4, 'Customer');

select * from userProfile;