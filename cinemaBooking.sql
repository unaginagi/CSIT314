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

CREATE TABLE `ticketType` (
  `id` int(11) NOT NULL PRIMARY KEY,
  `typeName` varchar(30) NOT NULL,
  `price` double(3,2) NOT NULL,
);


-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `ticketType`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;
COMMIT;


