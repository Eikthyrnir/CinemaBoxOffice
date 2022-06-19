--DROP TABLE IF EXISTS "cinema_box_office".ticket;
--DROP TABLE IF EXISTS "cinema_box_office".viewing_room_seat;
--DROP TABLE IF EXISTS "cinema_box_office".movie_session;
--DROP TABLE IF EXISTS "cinema_box_office".viewing_room;
--DROP TABLE IF EXISTS "cinema_box_office".movie;
--
--DROP SCHEMA IF EXISTS "cinema_box_office";

CREATE SCHEMA "cinema_box_office" AUTHORIZATION postgres;

CREATE TABLE "cinema_box_office".movie(
    id SERIAL,
    title VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    duration_mins INT NOT NULL,
    CONSTRAINT movie_pk PRIMARY KEY (id)
);

CREATE TABLE "cinema_box_office".viewing_room(
    id SERIAL,
    name VARCHAR NOT NULL,
--    description VARCHAR not null,
    CONSTRAINT viewing_room_pk PRIMARY KEY (id)
);

CREATE TABLE cinema_box_office.seat_type(
    id   SERIAL NOT NULL,
    name VARCHAR NOT NULL,
    CONSTRAINT seat_type_pk PRIMARY KEY (id)
);

CREATE TABLE "cinema_box_office".viewing_room_seat(
    id SERIAL,
    viewing_room_id integer NOT NULL,
    type_id integer NOT NULL,
    "row" int4 NOT NULL,
    "column" int4 NOT NULL,
    CONSTRAINT viewing_room_seat_pk PRIMARY KEY (id),
    CONSTRAINT seat_viewing_room_fk FOREIGN KEY (viewing_room_id) REFERENCES "cinema_box_office".viewing_room(id) 
    	ON UPDATE NO ACTION
    	ON DELETE NO ACTION,
    CONSTRAINT viewing_room_seat_seat_type_fk FOREIGN KEY (type_id) REFERENCES "cinema_box_office".seat_type(id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE "cinema_box_office".movie_session(
    id SERIAL,
    movie_id integer NOT NULL,
    viewing_room_id integer NOT NULL,
    start_time TIMESTAMP NOT NULL,
    CONSTRAINT movie_session_pk PRIMARY KEY (id),
    CONSTRAINT movie_session_movie_fk FOREIGN KEY (movie_id) REFERENCES "cinema_box_office".movie(id) 
    	ON UPDATE NO ACTION
    	ON DELETE NO ACTION,
    CONSTRAINT movie_session_viewing_room_fk FOREIGN KEY (viewing_room_id) REFERENCES "cinema_box_office".viewing_room(id) 
    	ON UPDATE NO ACTION
    	ON DELETE NO ACTION
);

CREATE TABLE "cinema_box_office".ticket(
    id SERIAL,
    movie_session_id integer NOT NULL,
    viewing_room_seat_id integer NOT NULL,
    price varchar NOT NULL,
    state integer NOT NULL DEFAULT 0,
    CONSTRAINT ticket_pk PRIMARY KEY (id),
    CONSTRAINT ticket_movie_session_fk FOREIGN KEY (movie_session_id) REFERENCES "cinema_box_office".movie_session(id) 
    	ON UPDATE NO ACTION
    	ON DELETE NO ACTION,
    CONSTRAINT ticket_viewing_room_seat_fk FOREIGN KEY (viewing_room_seat_id) REFERENCES "cinema_box_office".viewing_room_seat(id) 
    	ON UPDATE NO ACTION
    	ON DELETE NO ACTION
);

