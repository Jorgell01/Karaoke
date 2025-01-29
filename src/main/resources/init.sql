CREATE DATABASE IF NOT EXISTS karaoke_db;

USE karaoke_db;

CREATE TABLE IF NOT EXISTS Cancion (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
    artist VARCHAR(255) NOT NULL,
    count INT DEFAULT 0
    );

CREATE TABLE IF NOT EXISTS User (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS CancionReproducida (
                                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                  song_id BIGINT,
                                                  user_id BIGINT,
                                                  date TIMESTAMP,
                                                  FOREIGN KEY (song_id) REFERENCES Cancion(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
    );