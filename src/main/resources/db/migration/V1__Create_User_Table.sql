CREATE TABLE public."User"
(
   "ID" SERIAL PRIMARY KEY,
   "Name" character varying(100) NOT NULL
)
WITH (
   OIDS = FALSE
);

ALTER TABLE public."User"
   OWNER to postgres;