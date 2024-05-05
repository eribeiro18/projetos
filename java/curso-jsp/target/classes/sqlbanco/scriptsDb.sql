/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  evandro
 * Created: 19 de set. de 2023
 */

-- Database: curso-jsp

-- DROP DATABASE IF EXISTS "curso-jsp";

CREATE DATABASE "curso-jsp"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Table: public.model_login

-- DROP TABLE IF EXISTS public.model_login;

CREATE TABLE IF NOT EXISTS public.model_login
(
    login character varying(200) COLLATE pg_catalog."default",
    senha character varying(200) COLLATE pg_catalog."default",
    id integer NOT NULL DEFAULT nextval('model_login_id_seq'::regclass),
    nome character varying(300) COLLATE pg_catalog."default",
    email character varying(300) COLLATE pg_catalog."default",
    descricao character varying(2000) COLLATE pg_catalog."default",
    CONSTRAINT model_login_pkey PRIMARY KEY (id),
    CONSTRAINT uk_login UNIQUE (login)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.model_login
    OWNER to postgres;