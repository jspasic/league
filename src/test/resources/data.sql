INSERT INTO `leagues` (`id`, `league_name`) VALUES
(1001, 'Champions league 2020/21');

INSERT INTO `teams` (`id`, `team_name`) VALUES
(1001, 'Arsenal'),
(1002, 'PSG');

INSERT INTO `groups` (id, group_name, league_id) VALUES
(1001, 'A', 1001);

INSERT INTO `games` (id, matchday, kickoff_at, home_team_goals, away_team_goals, home_team_id, away_team_id, group_id, league_id) VALUES
(1001, 1, '2016-10-01 20:45:00', 1, 1, 1002, 1001, 1001, 1001);

INSERT INTO `teams_leagues` (`team_id`, `league_id`) VALUES
(1001, 1001),
(1002, 1001);

INSERT INTO `teams_groups` (`team_id`, `group_id`) VALUES
(1001, 1001),
(1002, 1001);