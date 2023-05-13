SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `csit314`
--

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

COMMIT;
-- --------------------------------------------------------