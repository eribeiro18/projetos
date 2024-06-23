CREATE SEQUENCE IF NOT EXISTS account_payment_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE account_payment_id_seq
    OWNER TO postgres;

CREATE SEQUENCE IF NOT EXISTS user_system_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE user_system_id_seq
    OWNER TO postgres;

-- DROP TABLE IF EXISTS account_payment;

CREATE TABLE IF NOT EXISTS account_payment
(
    id bigint NOT NULL DEFAULT nextval('account_payment_id_seq'::regclass),
    expiration_date date NOT NULL,
    payment_date date NOT NULL,
    payment_amount numeric(15,3) NOT NULL,
    description character varying(1000) COLLATE pg_catalog."default" NOT NULL, 
    situation character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT account_payment_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS account_payment
    OWNER to postgres;
    
CREATE TABLE IF NOT EXISTS user_system
(
    id bigint NOT NULL DEFAULT nextval('user_system_id_seq'::regclass),
    firstName character varying(1000) COLLATE pg_catalog."default" NOT NULL, 
    username character varying(1000) COLLATE pg_catalog."default" NOT NULL, 
    password character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_system_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS user_system
    OWNER to postgres;
    
--user = validacao
--pss =  validar@123
INSERT INTO user_system(firstname, username, password) VALUES ('Validação', 'validacao', '$2a$10$Ecx.eDRJMnmIrNEUAfTH0.ocE1GMYym8Fs0QzYH6bTXR4i.QHbaiC');


