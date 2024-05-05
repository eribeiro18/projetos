-- Database: quarkus-social

-- DROP DATABASE IF EXISTS "quarkus-social";

CREATE DATABASE "quarkus-social"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;


-- SEQUENCE: public.user_id_seq

-- DROP SEQUENCE IF EXISTS public.user_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.user_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY users.id;

ALTER SEQUENCE public.user_id_seq
    OWNER TO postgres;

-- Table: public.user

-- DROP TABLE IF EXISTS public."user";

CREATE TABLE IF NOT EXISTS public."users"
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    name character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    age integer NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)
    )
    WITH (
        OIDS = FALSE
        )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."user"
    OWNER to postgres;

-- SEQUENCE: public.post_id_seq

-- DROP SEQUENCE IF EXISTS public.post_id_seq;

CREATE SEQUENCE IF NOT EXISTS public.post_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1
    OWNED BY users.id;

ALTER SEQUENCE public.post_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS public."posts"
(
    id bigint NOT NULL DEFAULT nextval('post_id_seq'::regclass),
    post_text character varying(1000) COLLATE pg_catalog."default" NOT NULL,
    datetime timestamp NOT NULL,
    user_id bigint not null references users(id),
    CONSTRAINT post_pkey PRIMARY KEY (id)
    )
    WITH (
        OIDS = FALSE
        )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."posts"
    OWNER to postgres;

-- DROP SEQUENCE IF EXISTS public.user_id_seq;


-- Table: public.followers

-- DROP TABLE IF EXISTS public.followers;

CREATE TABLE IF NOT EXISTS public.followers
(
    id bigint NOT NULL DEFAULT nextval('followers_id_seq'::regclass),
    user_id bigint NOT NULL,
    follower_id bigint NOT NULL,
    CONSTRAINT followers_pkey PRIMARY KEY (user_id),
    CONSTRAINT fk_followers_follower FOREIGN KEY (follower_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fk_followers_user FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )
    WITH (
        OIDS = FALSE
        )
    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.followers
    OWNER to postgres;
