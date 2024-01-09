-- Database: BdReclamo

-- DROP DATABASE IF EXISTS "BdReclamo";

CREATE DATABASE "BdReclamo"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE IF NOT EXISTS documents_type (
  id_documents_type SERIAL PRIMARY KEY,
  cod_type VARCHAR(45) NOT NULL,
  desc_type VARCHAR(45) NOT NULL,
  status INT NOT NULL,
  user_create VARCHAR(45),
  date_create TIMESTAMP,
  user_modif VARCHAR(45),
  date_modif TIMESTAMP
);


CREATE TABLE IF NOT EXISTS enterprises_type (
  id_enterprises_type SERIAL PRIMARY KEY,
  cod_type VARCHAR(45) NOT NULL,
  desc_type VARCHAR(45) NOT NULL,
  status INT NOT NULL,
  user_create VARCHAR(45),
  date_create TIMESTAMP,
  user_modif VARCHAR(45),
  date_modif TIMESTAMP
);

-- Table enterprises
CREATE TABLE IF NOT EXISTS enterprises (
  id_enterprises SERIAL PRIMARY KEY,
  num_document VARCHAR(15) NOT NULL,
  business_name VARCHAR(150) NOT NULL,
  tradename VARCHAR(150) NOT NULL,
  status INT NOT NULL,
  user_create VARCHAR(45),
  date_create TIMESTAMP,
  user_modif VARCHAR(45),
  date_modif TIMESTAMP,
  documents_type_id_documents_type INT NOT NULL,
  enterprises_type_id_enterprises_type INT NOT NULL,
  FOREIGN KEY (documents_type_id_documents_type) REFERENCES documents_type (id_documents_type) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (enterprises_type_id_enterprises_type) REFERENCES enterprises_type (id_enterprises_type) ON DELETE NO ACTION ON UPDATE NO ACTION
);



CREATE TABLE IF NOT EXISTS persons (
  id_persons SERIAL PRIMARY KEY,
  num_document VARCHAR(15) NOT NULL,
  name VARCHAR(100) NOT NULL,
  lastname VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  telephone VARCHAR(15) NOT NULL,
  status INT NOT NULL,
  user_create VARCHAR(45),
  date_create TIMESTAMP,
  user_modif VARCHAR(45),
  date_modif TIMESTAMP,
  documents_type_id_documents_type INT NOT NULL,
  enterprises_id_enterprises INT NOT NULL,
  FOREIGN KEY (documents_type_id_documents_type) REFERENCES documents_type (id_documents_type) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (enterprises_id_enterprises) REFERENCES enterprises (id_enterprises) ON DELETE NO ACTION ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS users (
  id_users INT PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(250) NOT NULL,
  status INT NOT NULL,
  pass_expiration TIMESTAMP NOT NULL,
  attempts INT NOT NULL,
  last_login_attempt TIMESTAMP,
  role VARCHAR(45) NOT NULL,
  locked INT NOT NULL,
  user_create VARCHAR(45),
  date_create TIMESTAMP,
  user_modif VARCHAR(45),
  date_modif TIMESTAMP,
  persons_id_persons INT NOT NULL,
  FOREIGN KEY (persons_id_persons) REFERENCES persons (id_persons) ON DELETE NO ACTION ON UPDATE NO ACTION
);