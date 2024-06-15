CREATE TYPE public.role_name AS ENUM (
    'ROLE_USER',
    'ROLE_DRIVER'
);

CREATE TABLE public.roles_project (
    user_id integer NOT NULL,
    name character varying(255)
);

CREATE TABLE public.trips (
    id integer NOT NULL,
    start_time timestamp with time zone NOT NULL,
    end_time timestamp with time zone,
    created_time timestamp with time zone NOT NULL,
    pickup_location text NOT NULL,
    dropoff_location text NOT NULL,
    tariff character varying(15),
    comment text,
    trip_cost numeric(7,2),
    trip_status character varying(10) NOT NULL,
    client_id integer NOT NULL,
    driver_id integer,
    payment_type boolean,
    PRIMARY KEY (id)
);

CREATE SEQUENCE public.trips_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.users (
    id integer NOT NULL,
    f_name character varying(255),
    m_name character varying(255),
    l_name character varying(255),
    email character varying(255) NOT NULL,
    phone character varying(255),
    password text NOT NULL,
    vehicle_id integer,
    PRIMARY KEY (id)
);

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.vehicles (
    id integer NOT NULL,
    reg_no character varying(10) NOT NULL,
    brand character varying(20) NOT NULL,
    color character varying(20) NOT NULL,
    length numeric(5,2) NOT NULL,
    width numeric(5,2) NOT NULL,
    height numeric(5,2) NOT NULL,
    max_weight numeric(5,2) NOT NULL,
    PRIMARY KEY (id)
);

CREATE SEQUENCE public.vehicles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ONLY public.trips
    ADD CONSTRAINT client_fk FOREIGN KEY (client_id) REFERENCES public.users(id) NOT VALID;

ALTER TABLE ONLY public.trips
    ADD CONSTRAINT driver_fk FOREIGN KEY (driver_id) REFERENCES public.users(id) NOT VALID;

ALTER TABLE ONLY public.roles_project
    ADD CONSTRAINT fk97mxvrajhkq19dmvboprimeg1 FOREIGN KEY (user_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT vehicle_fk FOREIGN KEY (vehicle_id) REFERENCES public.vehicles(id) NOT VALID;

