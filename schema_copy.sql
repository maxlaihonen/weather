CREATE TABLE IF NOT EXISTS observations (
    id int(8) NOT NULL AUTO_INCREMENT,
    city_id int(1) NOT NULL,
    temp double(5,2) NOT NULL,
    date TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
    );