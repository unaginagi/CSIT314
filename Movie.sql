SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `csit314`
--

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE movie (
  id int(5) PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  genre VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO movie (name, description, genre)
VALUES ("James Bond", "abcde", "Action");

INSERT INTO movie (name, description, genre)
VALUES ("Mission Impossible", "123", "Action");

INSERT INTO movie (name, description, genre)
VALUES ("Fight Club", "abc", "Thriller");

INSERT INTO movie (name, description, genre)
VALUES ("John Wick", "456", "Action");

INSERT INTO movie (name, description, genre)
VALUES ("Conjuring", "000", "Horror");

COMMIT;
-- --------------------------------------------------------