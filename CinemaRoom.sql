SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `csit314`
--

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

COMMIT;
-- --------------------------------------------------------