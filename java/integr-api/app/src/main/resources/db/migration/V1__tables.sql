CREATE SEQUENCE IF NOT EXISTS pix_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE pix_id_seq
    OWNER TO postgres;

-- DROP TABLE IF EXISTS pix;

CREATE TABLE IF NOT EXISTS pix
(
    id bigint NOT NULL DEFAULT nextval('pix_id_seq'::regclass),
    payment_date date NOT NULL,
    payment_amount numeric(15,3) NOT NULL,
    description character varying(1000) COLLATE pg_catalog."default" NOT NULL, 
    situation character varying(100) COLLATE pg_catalog."default" NOT NULL,
    create_at date NOT NULL,
    create_by character varying(100) NOT NULL,
    update_at date NULL,
    update_by character varying(100)
    CONSTRAINT pix_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS pix
    OWNER to postgres;


-- Tabela pix_remetente
CREATE TABLE IF NOT EXISTS pix_remetente
(
    id bigint NOT NULL DEFAULT nextval('pix_id_seq'::regclass),
    pix_id bigint NOT NULL,
    nome character varying(150) COLLATE pg_catalog."default" NOT NULL,
    documento character varying(150) COLLATE pg_catalog."default" NOT NULL,
    banco character varying(150) COLLATE pg_catalog."default" NOT NULL,
    create_at date NOT NULL,
    create_by character varying(100) NOT NULL,
    update_at date NULL,
    update_by character varying(100),
    CONSTRAINT pix_remetente_pkey PRIMARY KEY (id),
    CONSTRAINT pix_remetente_pix_fk FOREIGN KEY (pix_id)
        REFERENCES pix (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS pix_remetente
    OWNER to postgres;

-- Tabela pix_destinatario
CREATE TABLE IF NOT EXISTS pix_destinatario
(
    id bigint NOT NULL DEFAULT nextval('pix_id_seq'::regclass),
    pix_id bigint NOT NULL,
    nome character varying(150) COLLATE pg_catalog."default" NOT NULL,
    documento character varying(150) COLLATE pg_catalog."default" NOT NULL,
    banco character varying(150) COLLATE pg_catalog."default" NOT NULL,
    create_at date NOT NULL,
    create_by character varying(100) NOT NULL,
    update_at date NULL,
    update_by character varying(100),
    CONSTRAINT pix_destinatario_pkey PRIMARY KEY (id),
    CONSTRAINT pix_destinatario_pix_fk FOREIGN KEY (pix_id)
        REFERENCES pix (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS pix_destinatario
    OWNER to postgres;
