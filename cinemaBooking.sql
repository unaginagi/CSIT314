SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `csit314`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

--
-- Table structure for table `users`
--

CREATE TABLE `userAccount` (
  `UID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `DOB` Date NOT NULL,
  `user` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phoneNo` int (10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `address` varchar (50) NOT NULL,
  `suspended` bit(1) Not Null,
  `reg_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `userAccount`
  ADD PRIMARY KEY (`UID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `userAccount`
  MODIFY `UID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

-- --------------------------------------------------------

--
-- Table structure for table `ticketType`
--

CREATE TABLE `tickettype` (
  `ID` int(3) NOT NULL PRIMARY KEY,
  `typeName` int(11) NOT NULL,
  `price` double(3,2) NOT NULL
)

-- --------------------------------------------------------

--
-- Table structure for table `CinemaRoom`
--

CREATE TABLE CinemaRoom (
  ID int(3) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
  Name VARCHAR(25) NOT NULL,
  Capacity int(3) NOT NULL,
  State VARCHAR(50) NOT NULL
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
  ID int(5) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
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

CREATE TABLE `booking` (
  `bookingID` int(6) NOT NULL PRIMARY KEY,
  `roomID` int(3) NOT NULL,
  `SessionTiming` DATETIME NOT NULL,
  `UID` int(11) NOT NULL,
  `ticketID` int(3) NOT NULL,
  `quantity` int(3) NOT NULL,
  `price` double(4,2) NOT NULL,
  `book_date` datetime NOT NULL DEFAULT current_timestamp(),
  CONSTRAINT FK_bookMS FOREIGN KEY (roomID,SessionTiming)
	REFERENCES MovieSession(roomID,SessionTiming),
  CONSTRAINT FK_bookUserAcc FOREIGN KEY (UID)
	REFERENCES userAccount(UID),
  CONSTRAINT FK_bookTicType FOREIGN KEY (ticketID)
	REFERENCES tickettype(ID)
)

