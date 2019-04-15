CREATE TABLE public."User"
(
    "ID" integer NOT NULL,
    "Name" character varying(100)[] NOT NULL,
    PRIMARY KEY ("ID")
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public."User"
    OWNER to postgres;