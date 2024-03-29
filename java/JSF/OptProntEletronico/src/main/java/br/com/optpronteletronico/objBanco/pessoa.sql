/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  evandro
 * Created: 19/11/2017
 */
create sequence pessoa_id_seq minvalue 1 maxvalue 9999999999999999999999999999
start with 1 increment by 1 nocache cycle;


CREATE TABLE "OPT_PRONT_ELETR"."PESSOA" 
   (	"ID" NUMBER NOT NULL ENABLE, 
	"IDTIPO" NUMBER NOT NULL ENABLE, 
	"CODIGO" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"NOMEFANTASIA" VARCHAR2(90 BYTE) NOT NULL ENABLE, 
	"RAZAOSOCIAL" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"DATACADASTRO" TIMESTAMP (6) NOT NULL ENABLE, 
	"APELIDO" VARCHAR2(30 BYTE), 
	"TELEFONE" VARCHAR2(11 BYTE) NOT NULL ENABLE, 
	"TELEFONE2" VARCHAR2(11 BYTE), 
	"CELULAR" VARCHAR2(12 BYTE), 
	"NATUREZA" NUMBER(1,0) NOT NULL ENABLE, 
	"EMAIL" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"PROFISSAO" VARCHAR2(50 BYTE) DEFAULT '-NÃO INFORMADO-', 
	 CONSTRAINT "IDPESSOA" PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 CONSTRAINT "TIPOPESSOA_PESSOA_FK" FOREIGN KEY ("IDTIPO")
	  REFERENCES "OPT_PRONT_ELETR"."TIPOPESSOA" ("ID") ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "FASTFOOD"."PESSOA"."TELEFONE2" IS '-NÃO INFORMADO-';
   COMMENT ON COLUMN "FASTFOOD"."PESSOA"."CELULAR" IS '-NÃO INFORMADO-';
   COMMENT ON COLUMN "FASTFOOD"."PESSOA"."NATUREZA" IS '1-fisica, 2-juridica';
   COMMENT ON COLUMN "FASTFOOD"."PESSOA"."EMAIL" IS '-NÃO INFORMADO-';