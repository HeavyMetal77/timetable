CREATE DATABASE IF NOT EXISTS `timetable_directory`;
USE `timetable_directory`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `first_name`  varchar(45) DEFAULT NULL,
    `middle_name` varchar(45) DEFAULT NULL,
    `last_name`   varchar(45) DEFAULT NULL,
    `email`       varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `name_subject`    varchar(45) DEFAULT NULL,
    `school_class_id` int(45)     DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`school_class_id`) REFERENCES `school_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `school_class`;

CREATE TABLE `school_class`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `name_class` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `class_room`;

CREATE TABLE `class_room`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `name_classroom` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;

DROP TABLE IF EXISTS `lesson`;

CREATE TABLE `lesson`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT,
    `number_lesson`   int(11)     DEFAULT NULL,
    `date_time`       varchar(45) DEFAULT NULL,
    `subject_id`      int(11)     DEFAULT NULL,
    `school_class_id` int(11)     DEFAULT NULL,
    `teacher_id`      int(11)     DEFAULT NULL,
    `classroom_id`    int(11)     DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`school_class_id`) REFERENCES `school_class` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (`classroom_id`) REFERENCES `class_room` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `teacher`
VALUES (1, 'Ivan', 'Olexandrovich', 'Ivanov', 'ivanov@ukr.net'),
       (2, 'Petro', 'Mikolayovich', 'Petrov', 'petrov@ukr.net'),
       (3, 'Maria', 'Vitalivna', 'Gula', 'gula@ukr.net'),
       (4, 'Galina', 'Petrivna', 'Bec', 'bec@ukr.net'),
       (5, 'Andriy', 'Ivanovich', 'Ghuk', 'ghuk@ukr.net');

INSERT INTO `school_class`
VALUES (1, '8a'),
       (2, '8b'),
       (3, '8c'),
       (4, '8d'),
       (5, '8e');

INSERT INTO `subject`
VALUES (1, 'mathematical', 1),
       (2, 'history', 2),
       (3, 'chemical', 1),
       (4, 'mathematical', 2),
       (5, 'history', 1);

INSERT INTO `class_room`
VALUES (1, 'mathematical_classroom'),
       (2, 'history_classroom'),
       (3, 'chemical_classroom'),
       (4, 'mathematical_classroom'),
       (5, 'biology_classroom');

INSERT INTO `lesson`
VALUES (1, 1, '15-02-2020', 1, 1, 1, 1),
       (2, 2, '16-02-2020', 2, 2, 2, 1),
       (3, 4, '15-02-2020', 3, 3, 3, 1),
       (4, 3, '16-02-2020', 4, 4, 4, 1),
       (5, 5, '16-02-2020', 5, 5, 5, 1);



