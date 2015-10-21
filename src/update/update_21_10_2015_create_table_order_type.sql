CREATE TABLE jean_taxi_service.order_type (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type_name` TEXT       NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO jean_taxi_service.order_type (id, type_name)
VALUES (1, 'Все'), (2, 'Неактивные'), (3, 'Активные'), (4, 'Выполненные');

CREATE TABLE jean_taxi_service.date_option (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `option_name` TEXT       NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO jean_taxi_service.date_option (id, option_name)
VALUES (1, 'Без ограничений'), (2, 'За сегодня'), (3, 'За неделю'), (4, 'За месяц');