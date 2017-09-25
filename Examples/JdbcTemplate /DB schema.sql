-- Table: public.jiraissue

-- DROP TABLE public.jiraissue;

CREATE TABLE IF NOT EXISTS public.jiraissue
(
    issuetype text COLLATE pg_catalog."default",
    workflow_id text COLLATE pg_catalog."default",
    timespent bigint,
    timeoriginalestimate bigint,
    project bigint,
    description text COLLATE pg_catalog."default",
    resolution text COLLATE pg_catalog."default",
    security text COLLATE pg_catalog."default",
    resolutiondate text COLLATE pg_catalog."default",
    fixfor text COLLATE pg_catalog."default",
    id bigint,
    pkey text COLLATE pg_catalog."default",
    issuestatus text COLLATE pg_catalog."default",
    summary text COLLATE pg_catalog."default",
    watches bigint,
    created timestamp without time zone,
    reporter text COLLATE pg_catalog."default",
    priority text COLLATE pg_catalog."default",
    environment text COLLATE pg_catalog."default",
    component text COLLATE pg_catalog."default",
    timeestimate bigint,
    duedate timestamp without time zone,
    votes bigint,
    assignee text COLLATE pg_catalog."default",
    updated timestamp without time zone,
    CONSTRAINT "jiraissue_pkey" PRIMARY KEY (pkey)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.jiraissue
    OWNER to "postgres";

-- Table: public.user

-- DROP TABLE public.user;

CREATE TABLE IF NOT EXISTS public.user
(
    email_address text COLLATE pg_catalog."default",
    name text COLLATE pg_catalog."default",
    display_name text COLLATE pg_catalog."default",
    CONSTRAINT "user_pkey" PRIMARY KEY (name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.user
    OWNER to "postgres";

-- Table: public.project

-- DROP TABLE public.project;

CREATE TABLE IF NOT EXISTS public.project
(
    name text COLLATE pg_catalog."default",
    id bigint,
    pkey text COLLATE pg_catalog."default",
    CONSTRAINT "project_pkey" PRIMARY KEY (pkey)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.project
    OWNER to "postgres";

-- Table: public.issuelink

-- DROP TABLE public.issuelink;

CREATE TABLE IF NOT EXISTS public.issuelink
(
    issuekey text COLLATE pg_catalog."default",
    linktype text COLLATE pg_catalog."default",
    targetkey text COLLATE pg_catalog."default",
    CONSTRAINT "issuelink_pkey" PRIMARY KEY (issuekey,targetkey,linktype)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.issuelink
    OWNER to "postgres";

-- Table: public.changeitem

-- DROP TABLE public.changeitem;

CREATE TABLE IF NOT EXISTS public.changeitem
(
    newvalue text COLLATE pg_catalog."default",
    issuekey text COLLATE pg_catalog."default",
    field text COLLATE pg_catalog."default",
    author text COLLATE pg_catalog."default",
    created timestamp without time zone,
    oldstring text COLLATE pg_catalog."default",
    newstring text COLLATE pg_catalog."default",
    fieldtype text COLLATE pg_catalog."default",
    oldvalue text COLLATE pg_catalog."default",
    id    serial primary key
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.changeitem
    OWNER to "postgres";


DELETE FROM public.project;
DELETE FROM public.jiraissue;
DELETE FROM public.changeitem;
DELETE FROM public.issuelink;