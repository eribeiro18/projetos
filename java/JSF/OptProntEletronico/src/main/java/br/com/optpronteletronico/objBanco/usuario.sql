--CRIAÇÃO DO USUARIO
CREATE USER "OPT_PRONT_ELETR" IDENTIFIED BY "1234";

--DESBLOQUEANDO USUARIO E SETANDO TABLESPACE
ALTER USER "OPT_PRONT_ELETR"
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP"
ACCOUNT UNLOCK ;

--PERMISSÃO DE DBA
GRANT "DBA" TO "OPT_PRONT_ELETR";

--SETANDO ROLE DE DBA
ALTER USER "OPT_PRONT_ELETR" DEFAULT ROLE "DBA";  

create sequence usuario_id_seq Minvalue 1 maxvalue 9999999999999999999999999999
start with 1 increment by 1 nocache cycle;

--CRIAR PESSOA E TIPOPESSOA ANTES DE EXECUTAR OS COMANDOS ABAIXO

CREATE TABLE "OPT_PRONT_ELETR"."USUARIO" 
   (	"ID" NUMBER NOT NULL ENABLE, 
	"IDPESSOA" NUMBER NOT NULL ENABLE, 
	"LOGIN" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"SENHA" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	 CONSTRAINT "IDUSUARIO" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "PESSOA_USUARIO_FK" FOREIGN KEY ("IDPESSOA")
	  REFERENCES "OPT_PRONT_ELETR"."PESSOA" ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "OPT_PRONT_ELETR"."USUARIO"."LOGIN" IS 'gerar automaticamente';
   COMMENT ON COLUMN "OPT_PRONT_ELETR"."USUARIO"."SENHA" IS 'gerar automaticamente';


insert into pessoa (id, IDTIPO, codigo, nomefantasia, razaosocial, datacadastro, apelido, telefone, telefone2, celular, natureza, email) 
values (pessoa_id_seq.nextval, (select id from TIPOPESSOA where DESCRICAO = 'ADMINISTRADOR'), 'COD-ADM', 'ADMINISTRADOR', 'ADMINISTRADOR', (SELECT TO_CHAR(SYSDATE, 'DD/MM/RRRR HH24:MI:SS') FROM DUAL), 'ADMIN',
'00000000000', '00000000000', '00000000000', 1, 'ADMIN@ADMIN.COM.BR')

insert into usuario (ID, IDPESSOA, LOGIN, SENHA)
values (usuario_id_seq.nextval, (select ID from pessoa where RAZAOSOCIAL = 'ADMINISTRADOR'), 'admin', '81dc9bdb52d04dc20036dbd8313ed055');
--senha = 1234