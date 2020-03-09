CREATE DATABASE IF NOT EXISTS `timetable_directory`;
USE `timetable_directory`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher`
(
    `id`                    int(11) NOT NULL AUTO_INCREMENT,
    `first_name`            varchar(45) DEFAULT NULL,
    `middle_name`           varchar(45) DEFAULT NULL,
    `last_name`             varchar(45) DEFAULT NULL,
    `email`                 varchar(45) DEFAULT NULL,
    `total_hours_allocated` int(11)     DEFAULT 0,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8;

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `name_subject`    varchar(45) DEFAULT NULL,
    `school_class_id` int(11)     DEFAULT NULL,
    `value_hours`     int(11)     DEFAULT 0,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`school_class_id`) REFERENCES `school_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8;

DROP TABLE IF EXISTS `school_class`;

CREATE TABLE `school_class`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `name_class` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8;

DROP TABLE IF EXISTS `class_room`;

CREATE TABLE `class_room`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `name_classroom` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8;

DROP TABLE IF EXISTS `day_of_week`;

CREATE TABLE `day_of_week`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `day_week` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8;

DROP TABLE IF EXISTS `subject_int_map`;

CREATE TABLE `subject_int_map`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `subject_id` int(11) DEFAULT NULL,
    `teacher_id` int(11) DEFAULT NULL,
    `value`      int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8;

DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `number_lesson`   int(11) DEFAULT NULL,
    `day_week_id`     int(11) DEFAULT NULL,
    `subject_id`      int(11) DEFAULT NULL,
    `school_class_id` int(11) DEFAULT NULL,
    `teacher_id`      int(11) DEFAULT NULL,
    `classroom_id`    int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`day_week_id`) REFERENCES `day_of_week` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`school_class_id`) REFERENCES `school_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`classroom_id`) REFERENCES `class_room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `day_of_week`
VALUES (1, 'Понеділок'),
       (2, 'Вівторок'),
       (3, 'Середа'),
       (4, 'Четвер'),
       (5, 'П\'ятниця'),
       (6, 'Субота'),
       (7, 'Неділя');

INSERT INTO `teacher`
VALUES (1, 'Іван', 'Олександрович', 'Іванов', 'ivanov@ukr.net', 12),
       (2, 'Петро', 'Миколайович', 'Петров', 'petrov@ukr.net', 18),
       (3, 'Марія', 'Віталівна', 'Гула', 'gula@ukr.net', 20),
       (4, 'Галина', 'Петрівна', 'Сокирко', 'sokirko@ukr.net', 25),
       (5, 'Андрій', 'Іванович', 'Жук', 'ghuk@ukr.net', 22);

INSERT INTO `school_class`
VALUES (1, '8a'),
       (2, '8b'),
       (3, '8c'),
       (4, '8d'),
       (5, '8e');

INSERT INTO `subject`
VALUES (1, 'математика', 1, 4),
       (2, 'історія', 2, 1),
       (3, 'хімія', 1, 1),
       (4, 'фізика', 2, 3),
       (5, 'малювання', 1, 2);

INSERT INTO `subject_int_map`
VALUES (1, 1, 1, 2),
       (2, 2, 2, 3),
       (3, 3, 2, 4),
       (4, 4, 4, 1),
       (5, 5, 5, 5);

INSERT INTO `class_room`
VALUES (1, 'клас математики'),
       (2, 'клас історії'),
       (3, 'клас хімії'),
       (4, 'клас біології'),
       (5, 'клас фізики');

# INSERT INTO `lesson`
# VALUES (5, 1, 1, 1, 1, 1, 1),
#        (3, 2, 2, 2, 2, 2, 1),
#        (4, 4, 3, 3, 3, 3, 1),
#        (2, 3, 4, 4, 2, 2, 1),
#        (1, 5, 5, 5, 5, 5, 1);




