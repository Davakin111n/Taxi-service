USE order_board;

CREATE TABLE discount (
  `id`   BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` TEXT       NOT NULL,
  `note` TEXT       NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;