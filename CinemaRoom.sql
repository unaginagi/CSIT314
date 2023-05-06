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
  Capacity int(3) NOT NULL,
  State VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO CinemaRoom (ID, Capacity, State)
VALUES (1, 150, "Available");

INSERT INTO CinemaRoom (ID, Capacity, State)
VALUES (2, 200, "Not Available");

INSERT INTO CinemaRoom (ID, Capacity, State)
VALUES (3, 125, "Available");

INSERT INTO CinemaRoom (ID, Capacity, State)
VALUES (4, 175, "Available");

INSERT INTO CinemaRoom (ID, Capacity, State)
VALUES (5, 100, "Available");

COMMIT;
-- --------------------------------------------------------