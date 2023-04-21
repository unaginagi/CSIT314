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

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(30) NOT NULL,
  `reg_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE `route` (
  `route_id` int(11) NOT NULL PRIMARY KEY,
  `users_id` int(11) NOT NULL,
  `timestamp` datetime NOT NULL DEFAULT current_timestamp(),
  `startMarker` varchar(30) NOT NULL,
  `endMarker` varchar(50) NOT NULL,
  `distance` int(11) NOT NULL,
  `estTime` int(11) NOT NULL,
  CONSTRAINT FK_routeUsers FOREIGN KEY (users_id)
	REFERENCES users(id)
)

-- --------------------------------------------------------

--
-- Table structure for table `marker`
--

CREATE TABLE `marker` (
  `marker_id` int(11) NOT NULL PRIMARY KEY,
  `route_id` int(11) NOT NULL,
  `road_name` varchar(30) NOT NULL,
  `road_type` varchar(30) NOT NULL,
  `description` varchar(100) NOT NULL,
  CONSTRAINT FK_routeMrker FOREIGN KEY (route_id)
	REFERENCES route(route_id)
)
