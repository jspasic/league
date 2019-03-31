create table `leagues` (
  id INT NOT NULL AUTO_INCREMENT,
  league_name VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
);

create table `teams` (
  id INT NOT NULL AUTO_INCREMENT,
  team_name VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
);

create table `groups` (
  id INT NOT NULL AUTO_INCREMENT,
  group_name VARCHAR (100) NOT NULL,
  league_id INT,
  PRIMARY KEY(id),
  FOREIGN KEY (league_id) REFERENCES `leagues`
);

create table `games` (
  id INT NOT NULL AUTO_INCREMENT,
  matchday INT(3) NOT NULL,
  kickoff_at timestamp NOT NULL,
  home_team_goals INT(3) NOT NULL,
  away_team_goals INT(3) NOT NULL,
  home_team_id INT NOT NULL,
  away_team_id INT NOT NULL,
  group_id INT NOT NULL,
  league_id INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (home_team_id) REFERENCES `teams`,
  FOREIGN KEY (away_team_id) REFERENCES `teams`,
  FOREIGN KEY (group_id) REFERENCES `groups`,
  FOREIGN KEY (league_id) REFERENCES `leagues`
);

create table `teams_leagues` (
  team_id INT NOT NULL,
  league_id INT NOT NULL,
  PRIMARY KEY (team_id, league_id),
  FOREIGN KEY (team_id) REFERENCES `teams`,
  FOREIGN KEY (league_id) REFERENCES `leagues`
);

create table `teams_groups` (
  team_id INT NOT NULL,
  group_id INT NOT NULL,
  PRIMARY KEY (team_id, group_id),
  FOREIGN KEY (team_id) REFERENCES `teams`,
  FOREIGN KEY (group_id) REFERENCES `groups`
);