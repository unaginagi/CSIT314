-- --------------------------------------------------------
--
-- Table structure for table `userProfile`
--

-- DROP TABLE userProfile;

CREATE TABLE userProfile (
    profileID INT(11) PRIMARY KEY,
    description VARCHAR(50) NOT NULL
);

ALTER TABLE userProfile
  MODIFY profileID int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
-- COMMIT;

INSERT INTO userProfile (description)
VALUES
    ('User Admin'),
    ('Cinema Owner'),
    ('Cinema Manager'),
	('Customer');

select * from userProfile;

--
-- Table structure for table `userAccount`
--


-- DROP TABLE userAccount; 

CREATE TABLE userAccount (
    `UID` INT(11) PRIMARY KEY,
	`name` VARCHAR(50) NOT NULL,
    `DOB` Date NOT NULL,
	`user` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
	`phoneNo` VARCHAR(10) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
	`address` varchar (50) NOT NULL,
	`suspended` BIT(1) NOT NULL DEFAULT 0,
    `profileID` INT(2) NOT NULL,
	CONSTRAINT FK_profile FOREIGN KEY (profileID) REFERENCES userProfile(profileID)
);

ALTER TABLE userAccount
  MODIFY `UID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
  
 
INSERT INTO userAccount (`name`, `DOB`, `user`, `password`, `phoneNo`, `email`, `address`, `suspended`,`profileID`)
VALUES
	('Jerome', '1990-05-12', 'chin', 'yano', '1234567890', 'chin@example.com', '321 Main St', 0, 1),
    ('John Smith', '1990-05-12', 'useradmin', 'asdf', '1234567890', 'johnsmith@example.com', '123 Main St', 0, 1),
    ('Jane Doe', '2009-09-20', 'cinemaowner', 'asdf', '9876543210', 'janedoe@example.com', '456 Elm St', 0, 2),
    ('Alice Johnson', '1976-03-08', 'cinemamanager', 'asdf', '5554443333', 'alicej@example.com', '789 Oak St', 0, 3),
    ('Bob Williams', '1992-07-18', 'bobw', 'bobpass', '1112223333', 'bobw@example.com', '246 Pine St', 0, 4),
    ('Sarah Davis', '2012-01-25', 'sarahd', 'sarahpass', '9998887777', 'sarahd@example.com', '678 Maple St', 0, 4),
    ('Michael Johnson', '1994-11-05', 'michaelj', 'mikepass', '5556667777', 'michaelj@example.com', '987 Oak St', 0, 4),
    ('Emily Smith', '1967-06-30', 'emilys', 'emilypass', '8887776666', 'emilys@example.com', '543 Elm St', 0, 4),
    ('David Brown', '1992-02-15', 'davidb', 'davidpass', '4445556666', 'davidb@example.com', '321 Pine St', 0, 4),
    ('Jessica Wilson', '2010-09-10', 'jessicaw', 'jessicapass', '2223334444', 'jessicaw@example.com', '876 Maple St', 0, 4),
    ('Andrew Miller', '1967-04-20', 'andrewm', 'andrewpass', '7778889999', 'andrewm@example.com', '543 Oak St', 0, 4),
    ('Olivia Johnson', '1991-08-07', 'oliviaj', 'oliviapass', '3334445555', 'oliviaj@example.com', '234 Elm St', 0, 4),
    ('William Davis', '2011-03-16', 'williamd', 'williampass', '6667778888', 'williamd@example.com', '789 Pine St', 0, 4),
    ('Sophia Anderson', '1967-12-02', 'sophiaa', 'sophiapass', '4445556666', 'sophiaa@example.com', '432 Maple St', 0, 4),
    ('Daniel Johnson', '1994-06-22', 'danielj', 'danielpass', '8889990000', 'danielj@example.com', '345 Oak St', 0, 4),
    ('Mike Smith', '2013-07-18', 'mikes', 'mikepass', '1231231231', 'mikes@example.com', '987 Pine St', 0, 4);





-- --------------------------------------------------------

--
-- Table structure for table `ticketType`
--

CREATE TABLE `tickettype` (
  `ID` int(3) NOT NULL PRIMARY KEY,
  `typeName` varchar(255) NOT NULL,
  `price` double(4,2) NOT NULL,
  `ageLimit` int(3) NOT NULL
);

ALTER TABLE tickettype
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

INSERT INTO tickettype (typeName, price, ageLimit)
VALUES ("adult", 15, -1);
INSERT INTO tickettype (typeName, price, ageLimit)
VALUES ("senior", 6, 60);
INSERT INTO tickettype (typeName, price, ageLimit)
VALUES ("student", 8, 18);
-- --------------------------------------------------------

--
-- Table structure for table `CinemaRoom`
--

CREATE TABLE `CinemaRoom` (
  `ID` int(3) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(25) NOT NULL,
  `Capacity` int(3) NOT NULL,
  `State` VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--

INSERT INTO CinemaRoom (Name, Capacity, State)
VALUES ("Snowdrop", 150, "Available");

INSERT INTO CinemaRoom (Name, Capacity, State)
VALUES ("Amaryllis", 200, "Not Available (Renovation)");

INSERT INTO CinemaRoom (Name, Capacity, State)
VALUES ("Alchemilla", 125, "Available");

INSERT INTO CinemaRoom (Name, Capacity, State)
VALUES ("Iris", 175, "Available");

INSERT INTO CinemaRoom (Name, Capacity, State)
VALUES ("Daisy", 100, "Available");

-- --------------------------------------------------------

--
-- Table structure for table `Movie`
--

CREATE TABLE Movie (
  ID int(5) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  Name VARCHAR(255) NOT NULL,
  Description VARCHAR(255) NOT NULL,
  Genre VARCHAR(50) NOT NULL,
  Duration int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 

INSERT INTO Movie (Name, Description, Genre, Duration)
VALUES ("James Bond", "abcde", "Action", 150);

INSERT INTO Movie (Name, Description, Genre, Duration)
VALUES ("Mission Impossible", "123", "Action", 150);

INSERT INTO Movie (Name, Description, Genre, Duration)
VALUES ("Fight Club", "abc", "Thriller", 120);

INSERT INTO Movie (Name, Description, Genre, Duration)
VALUES ("John Wick", "456", "Action", 135);

INSERT INTO Movie (Name, Description, Genre, Duration)
VALUES ("Conjuring", "000", "Horror", 100);

-- --------------------------------------------------------

--
-- Table structure for table `MovieSession`
--

CREATE TABLE MovieSession (
  RoomID int(3) NOT NULL,
  SessionTiming DATETIME NOT NULL,
  MovieID int(5) NOT NULL,
  PRIMARY KEY(RoomID, SessionTiming),
  CONSTRAINT FK_MOVIE_ID FOREIGN KEY (MovieID) REFERENCES Movie(ID),
  CONSTRAINT FK_ROOM_ID FOREIGN KEY (RoomID) REFERENCES CinemaRoom(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 

INSERT INTO MovieSession (RoomID, SessionTiming, MovieID)
VALUES (1, "2023-03-10 12:00:00", 1);

INSERT INTO MovieSession (RoomID, SessionTiming, MovieID)
VALUES (2, "2023-03-10 15:00:00", 1);

INSERT INTO MovieSession (RoomID, SessionTiming, MovieID)
VALUES (1, "2023-03-12 11:30:00", 2);

INSERT INTO MovieSession (RoomID, SessionTiming, MovieID)
VALUES (3, "2023-04-15 16:20:00", 3);

INSERT INTO MovieSession (RoomID, SessionTiming, MovieID)
VALUES (5, "2023-03-11 14:30:00", 4);


-- --------------------------------------------------------
--
-- Table structure for table `booking`
--


CREATE TABLE food_items (
  `id` int(4) PRIMARY KEY NOT NULL,
  `name` varchar(50),
  `description` VARCHAR(100),
  `price` DECIMAL(10, 2) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO food_items (id,name, description, price)
VALUES (0001,"Pop Corn", "Caramel", "12");

INSERT INTO food_items (id,name, description, price)
VALUES (0002,"Nuggets", "Fried", "9.5");

INSERT INTO food_items (id,name, description, price)
VALUES (0003,"Chips", "BBQ", "5.5");

INSERT INTO food_items (id,name, description, price)
VALUES (0004,"Hot Dog", "Cheese", "7.8");

INSERT INTO food_items (id,name, description, price)
VALUES (0005,"Ice Lemon Tea", "Heaven&Earth", "3.6");

--DROP TABLE food_items;
-- --------------------------------------------------------
--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingID` int(6) NOT NULL PRIMARY KEY,
  `roomID` int(3) NOT NULL,
  `SessionTiming` DATETIME NOT NULL,
  `UID` int(11) NOT NULL,
  `ticketID` int(3) NOT NULL,
  `quantity` int(3) NOT NULL,
  `price` double(5,2) NOT NULL,
  `book_date` datetime NOT NULL DEFAULT current_timestamp(),
  CONSTRAINT FK_bookMS FOREIGN KEY (roomID,SessionTiming)
	REFERENCES MovieSession(roomID,SessionTiming),
  CONSTRAINT FK_bookUserAcc FOREIGN KEY (UID)
	REFERENCES userAccount(UID),
  CONSTRAINT FK_bookTicType FOREIGN KEY (ticketID)
	REFERENCES tickettype(ID)
);

ALTER TABLE booking
  MODIFY `bookingID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100000;
  
  -- --------------------------------------------------------
--
-- Table structure for table `food_sales`
--

CREATE TABLE `food_sales` (
  `fsalesID` int(5) NOT NULL PRIMARY KEY,
  `bookingID` int(6) NOT NULL,
  `foodID` int(3) NOT NULL,
  `quantity` int(3) NOT NULL,
  `price` double(5,2)  NOT NULL,
  `book_date` datetime NOT NULL DEFAULT current_timestamp(),
  CONSTRAINT FK_fsalesBooking FOREIGN KEY (bookingID)
	REFERENCES booking(bookingID),
  CONSTRAINT FK_fsales_fItems FOREIGN KEY (foodID)
	REFERENCES food_items(id)
);

ALTER TABLE food_sales
  MODIFY `fsalesID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10000;