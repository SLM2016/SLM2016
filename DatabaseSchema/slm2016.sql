
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `slm2016`
--

-- --------------------------------------------------------

--
-- 資料表結構 `student_info`
--

CREATE TABLE `student_info` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email.` varchar(200) NOT NULL,
  `nickname` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `company` varchar(200) NOT NULL,
  `apartment` varchar(200) NOT NULL,
  `title` varchar(200) NOT NULL,
  `ticket_type` varchar(200) NOT NULL,
  `vege_meat` varchar(200) NOT NULL,
  `receipt_type` varchar(200) NOT NULL,
  `company_name_and_EIN` varchar(200) NOT NULL,
  `class_info` varchar(200) NOT NULL,
  `has_scrum` varchar(200) NOT NULL,
  `flow_ok` varchar(200) NOT NULL,
  `team_members` text NOT NULL,
  `comment` text NOT NULL,
  `timestamp` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `student_info`
--
ALTER TABLE `student_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `student_info`
--
ALTER TABLE `student_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
