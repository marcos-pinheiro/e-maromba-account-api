
CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE permission (
  id bigint(20) NOT NULL,
  name varchar(255) DEFAULT NULL,
  KEY FK2vcomkfuoi6g318twtvra8yp3 (id),
  CONSTRAINT FK2vcomkfuoi6g318twtvra8yp3 FOREIGN KEY (id) REFERENCES role (id)
);

CREATE TABLE account (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  inactive bit(1) NOT NULL,
  roles_id bigint(20) DEFAULT NULL,
  user_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKgtxjm5hk5wfqdutyj9nv4uxn6 (roles_id),
  KEY FK7m8ru44m93ukyb61dfxw0apf6 (user_id),
  CONSTRAINT FK7m8ru44m93ukyb61dfxw0apf6 FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT FKgtxjm5hk5wfqdutyj9nv4uxn6 FOREIGN KEY (roles_id) REFERENCES role (id)
);


INSERT INTO role (name) VALUES('USER');

INSERT INTO permission (id, name) VALUES(1, 'NAGIVATE_IN_SITE');
INSERT INTO permission (id, name) VALUES(1, 'SHOPPING_CART');