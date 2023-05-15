SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `csit314`
--

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

COMMIT;
-- --------------------------------------------------------