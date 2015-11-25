-- Table: sequence

-- DROP TABLE sequence;

CREATE TABLE sequence
(
  seq_name character varying(50) NOT NULL,
  seq_count integer,
  CONSTRAINT sequence_pkey PRIMARY KEY (seq_name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE sequence
  OWNER TO postgres;

  
  
  
  -- Table: customer

-- DROP TABLE customer;

CREATE TABLE customer
(
  id bigserial NOT NULL,
  firstname character varying(1000),
  lastname character varying(1000),
  CONSTRAINT customer_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE customer
  OWNER TO postgres;
  
  
  