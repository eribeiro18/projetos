CREATE SEQUENCE IF NOT EXISTS account_payment_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE account_payment_id_seq
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
