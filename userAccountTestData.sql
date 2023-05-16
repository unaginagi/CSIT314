DROP TABLE userAccount; 

CREATE TABLE userAccount (
    `UID` INT(11) PRIMARY KEY,
	`name` VARCHAR(50) NOT NULL,
    `DOB` Date NOT NULL,
	`user` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
	`phoneNo` VARCHAR(10) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
	`address` varchar (50) NOT NULL,
	`suspended` BIT(1) NOT NULL,
	`reg_date` Date NOT NULL DEFAULT current_timestamp(),
    `profileID` INT(2) NOT NULL
);

ALTER TABLE userAccount
  MODIFY `UID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;
 
INSERT INTO userAccount (`name`, `DOB`, `user`, `password`, `phoneNo`, `email`, `address`, `suspended`,`reg_date` ,`profileID`)
VALUES
    ('John Smith', '1990-05-12', 'johnsmith', 'password123', '1234567890', 'johnsmith@example.com', '123 Main St', 0,'2023-04-25', 1),
    ('Jane Doe', '2009-09-20', 'janedoe', 'doe456', '9876543210', 'janedoe@example.com', '456 Elm St', 0,'2022-11-10', 2),
    ('Alice Johnson', '1976-03-08', 'alicej', 'pass321', '5554443333', 'alicej@example.com', '789 Oak St', 0,'2022-07-03', 3),
    ('Bob Williams', '1992-07-18', 'bobw', 'bobpass', '1112223333', 'bobw@example.com', '246 Pine St', 0,'2022-03-19', 1),
    ('Sarah Davis', '2012-01-25', 'sarahd', 'sarahpass', '9998887777', 'sarahd@example.com', '678 Maple St', 0,'2021-12-01', 2),
    ('Michael Johnson', '1994-11-05', 'michaelj', 'mikepass', '5556667777', 'michaelj@example.com', '987 Oak St', 0,'2021-08-16', 1),
    ('Emily Smith', '1967-06-30', 'emilys', 'emilypass', '8887776666', 'emilys@example.com', '543 Elm St', 0,'2021-04-29', 3),
    ('David Brown', '1992-02-15', 'davidb', 'davidpass', '4445556666', 'davidb@example.com', '321 Pine St', 0,'2020-11-14', 1),
    ('Jessica Wilson', '2010-09-10', 'jessicaw', 'jessicapass', '2223334444', 'jessicaw@example.com', '876 Maple St', 0,'2020-07-27', 2),
    ('Andrew Miller', '1967-04-20', 'andrewm', 'andrewpass', '7778889999', 'andrewm@example.com', '543 Oak St', 0,'2020-04-10', 3),
    ('Olivia Johnson', '1991-08-07', 'oliviaj', 'oliviapass', '3334445555', 'oliviaj@example.com', '234 Elm St', 0,'2019-11-24', 1),
    ('William Davis', '2011-03-16', 'williamd', 'williampass', '6667778888', 'williamd@example.com', '789 Pine St', 0,'2019-08-08', 2),
    ('Sophia Anderson', '1967-12-02', 'sophiaa', 'sophiapass', '4445556666', 'sophiaa@example.com', '432 Maple St', 0,'2019-04-21', 3),
    ('Daniel Johnson', '1994-06-22', 'danielj', 'danielpass', '8889990000', 'danielj@example.com', '345 Oak St', 0,'2018-11-05', 1),
    ('Mike Smith', '2013-07-18', 'mikes', 'mikepass', '1231231231', 'mikes@example.com', '987 Pine St', 0,'2021-08-17', 2);

SELECT * FROM userAccount LIMIT 100;
