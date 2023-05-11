CREATE TABLE IF NOT EXISTS user(
  id bigint auto_increment,
  name varchar(60) NOT NULL,
  email varchar(60),
  password varchar(255) NOT NULL,
  CONSTRAINT user_pk1 PRIMARY KEY (id)
);
CREATE INDEX idx_user_np ON user (name, password);
CREATE INDEX idx_user_ep ON user (email, password);
CREATE INDEX idx_user_name ON user (name);
CREATE INDEX idx_user_email ON user (email);

CREATE TABLE IF NOT EXISTS user_follower(
  id bigint auto_increment,
  user_name varchar(60) NOT NULL,
  user_email varchar(60) NOT NULL,
  follower_name varchar(60) NOT NULL,
  follower_email varchar(60) NOT NULL,
  CONSTRAINT user_follower_pk PRIMARY KEY (id)
);
CREATE INDEX idx_user_follower ON user_follower (user_name);
CREATE INDEX idx_user_following ON user_follower (follower_name);
CREATE INDEX idx_user_uf ON user_follower (user_name, follower_name);

CREATE TABLE IF NOT EXISTS posts(
  id bigint auto_increment,
  poster_id bigint NOT NULL,
  poster_name varchar(60),
  message text,
  CONSTRAINT posts_pk PRIMARY KEY (id)
);
CREATE INDEX idx_posts_1 ON posts (poster_id);
CREATE INDEX idx_posts_2 ON posts (poster_name);