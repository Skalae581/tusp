-- Vorbedingung: Es muss einen tuspadmin geben, der alles darf

-- Diese Datei wird laufend fortgeschrieben und bestehendes **NIEMALS** verändert!!!
-- Wenn neue Statements an diese Datei angehängt werden sollen, muss zuvor ein Hinweis auf die neue Version gegeben werden:
-- [Verison N (struct|data) (require sync)? (try)?]
-- N muss dabei eine Ganzzahl sein, die um einen größer ist als die vorige.
-- (struct|data) gibt an, ob in dem SQL die Datenbank Struktur oder Daten gepflegt werden. (Daten ohne Abhängigkeiten dürften auch in struct sein, umgekehrt darf dies nie der Fall sein.)
-- (require sync) ist optional und nur bei data, es gibt an, ob zwischen dem Verarbeiten der Struktur-Versionen und dem Verarbeiten der Daten-Versionen ein Sync laufen soll.
-- (try) ist optional und gibt an, dass bei einem Fehlschlag des Versionssprungs dieser übersprungen werden darf (Achtung das darf nur bei Daten-Versionen gemacht werden!!!)
-- Es darf eine Version N zweimal verwendet werden, wenn die eine struct und die andere data ist
-- Wenn sich jemand nicht exact an diese Vorgaben hält können die verschiedenen Datenbanken auseinanderlaufen!!!





-- [Version 1]

--
-- Name: courseofstudyservice_courseofstudy; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE courseofstudyservice_courseofstudy (
    id integer NOT NULL,
    lid integer NOT NULL,
    defaulttext_de character varying(255),
    defaulttext_en character varying(255) NOT NULL,
    shorttext_de character varying(255),
    shorttext_en character varying(255),
    longtext_de character varying(255),
    longtext_en character varying(255),
    degreeid integer,
    valid boolean NOT NULL
);


ALTER TABLE courseofstudyservice_courseofstudy OWNER TO tuspadmin;

--
-- Name: courseofstudyservice_degree; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE courseofstudyservice_degree (
    id integer NOT NULL,
    longtext_de character varying(255),
    longtext_en character varying(255),
    shorttext_de character varying(255),
    shorttext_en character varying(255),
    defaulttext_de character varying(255) NOT NULL,
    defaulttext_en character varying(255) NOT NULL,
    sortorder character varying,
    valid boolean NOT NULL
);


ALTER TABLE courseofstudyservice_degree OWNER TO tuspadmin;

--
-- Name: fame_input; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_input (
    id integer NOT NULL,
    input_type_id integer NOT NULL,
    workflow_id integer NOT NULL,
    name character varying(255) NOT NULL,
    beschreibung character varying(4096) NOT NULL,
    options character varying(4096) NOT NULL,
    "position" integer NOT NULL
);


ALTER TABLE fame_input OWNER TO tuspadmin;

--
-- Name: fame_input_berechtigung; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_input_berechtigung (
    id integer NOT NULL,
    input_id integer NOT NULL,
    rolle_id integer NOT NULL,
    status_id integer NOT NULL,
    recht character varying(1) NOT NULL
);


ALTER TABLE fame_input_berechtigung OWNER TO tuspadmin;

--
-- Name: fame_input_berechtigung_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_input_berechtigung_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_input_berechtigung_id_seq OWNER TO tuspadmin;

--
-- Name: fame_input_berechtigung_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_input_berechtigung_id_seq OWNED BY fame_input_berechtigung.id;


--
-- Name: fame_input_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_input_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_input_id_seq OWNER TO tuspadmin;

--
-- Name: fame_input_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_input_id_seq OWNED BY fame_input.id;


--
-- Name: fame_input_type; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_input_type (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE fame_input_type OWNER TO tuspadmin;

--
-- Name: fame_input_type_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_input_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_input_type_id_seq OWNER TO tuspadmin;

--
-- Name: fame_input_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_input_type_id_seq OWNED BY fame_input_type.id;


--
-- Name: fame_status; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_status (
    id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE fame_status OWNER TO tuspadmin;

--
-- Name: fame_status_berechtigung; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_status_berechtigung (
    id integer NOT NULL,
    workflow_id integer NOT NULL,
    status_from_id integer NOT NULL,
    rolle_id integer NOT NULL,
    status_to_id integer NOT NULL,
    bedingungen character varying(4096) NOT NULL
);


ALTER TABLE fame_status_berechtigung OWNER TO tuspadmin;

--
-- Name: fame_status_berechtigung_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_status_berechtigung_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_status_berechtigung_id_seq OWNER TO tuspadmin;

--
-- Name: fame_status_berechtigung_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_status_berechtigung_id_seq OWNED BY fame_status_berechtigung.id;


--
-- Name: fame_status_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_status_id_seq OWNER TO tuspadmin;

--
-- Name: fame_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_status_id_seq OWNED BY fame_status.id;


--
-- Name: fame_status_workflow; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_status_workflow (
    id integer NOT NULL,
    workflow_id integer NOT NULL,
    status_id integer NOT NULL,
    "position" integer NOT NULL
);


ALTER TABLE fame_status_workflow OWNER TO tuspadmin;

--
-- Name: fame_status_workflow_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_status_workflow_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_status_workflow_id_seq OWNER TO tuspadmin;

--
-- Name: fame_status_workflow_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_status_workflow_id_seq OWNED BY fame_status_workflow.id;


--
-- Name: fame_value; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_value (
    id integer NOT NULL,
    input_id integer NOT NULL,
    eingabe character varying(8192) NOT NULL,
    vorgang_id integer NOT NULL
);


ALTER TABLE fame_value OWNER TO tuspadmin;

--
-- Name: fame_value_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_value_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_value_id_seq OWNER TO tuspadmin;

--
-- Name: fame_value_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_value_id_seq OWNED BY fame_value.id;


--
-- Name: fame_vorgang; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_vorgang (
    id integer NOT NULL,
    workflow_id integer NOT NULL,
    status_id integer NOT NULL,
    connector_key character varying(255) NOT NULL
);


ALTER TABLE fame_vorgang OWNER TO tuspadmin;

--
-- Name: fame_vorgang_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_vorgang_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_vorgang_id_seq OWNER TO tuspadmin;

--
-- Name: fame_vorgang_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_vorgang_id_seq OWNED BY fame_vorgang.id;


--
-- Name: fame_workflow; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE fame_workflow (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    connector character varying(255) NOT NULL,
    bulkmail_intervall integer NOT NULL
);


ALTER TABLE fame_workflow OWNER TO tuspadmin;

--
-- Name: fame_workflow_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE fame_workflow_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE fame_workflow_id_seq OWNER TO tuspadmin;

--
-- Name: fame_workflow_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE fame_workflow_id_seq OWNED BY fame_workflow.id;


--
-- Name: global_rolle; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE global_rolle (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    beschreibung character varying(2048) NOT NULL
);


ALTER TABLE global_rolle OWNER TO tuspadmin;

--
-- Name: global_rolle_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE global_rolle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE global_rolle_id_seq OWNER TO tuspadmin;

--
-- Name: global_rolle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE global_rolle_id_seq OWNED BY global_rolle.id;


--
-- Name: global_rolle_user; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE global_rolle_user (
    id integer NOT NULL,
    user_id integer NOT NULL,
    rolle_id integer NOT NULL
);


ALTER TABLE global_rolle_user OWNER TO tuspadmin;

--
-- Name: global_rolle_user_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE global_rolle_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE global_rolle_user_id_seq OWNER TO tuspadmin;

--
-- Name: global_rolle_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE global_rolle_user_id_seq OWNED BY global_rolle_user.id;


--
-- Name: global_user; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE global_user (
    id integer NOT NULL,
    benutzername character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    mailadresse character varying(255) NOT NULL,
    passwort character varying(512),
    enabled boolean
);


ALTER TABLE global_user OWNER TO tuspadmin;

--
-- Name: global_user_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE global_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE global_user_id_seq OWNER TO tuspadmin;

--
-- Name: global_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE global_user_id_seq OWNED BY global_user.id;


--
-- Name: orgunitservice; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE orgunitservice (
    id integer NOT NULL,
    lid integer NOT NULL
);


ALTER TABLE orgunitservice OWNER TO tuspadmin;

--
-- Name: orgunitservice_orgunits; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE orgunitservice_orgunits (
    id integer NOT NULL,
    shorttext_de character varying,
    displaytext_de character varying,
    displaytext_en character varying,
    longtext_de character varying,
    longtext_en character varying,
    parent_id integer,
    type integer,
    valid boolean NOT NULL,
    lid integer
);


ALTER TABLE orgunitservice_orgunits OWNER TO tuspadmin;

--
-- Name: periodservice_period; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE periodservice_period (
    id integer NOT NULL,
    uniquename character varying(255),
    startdate date,
    enddate date,
    valid boolean NOT NULL
);


ALTER TABLE periodservice_period OWNER TO tuspadmin;

--
-- Name: personservice_person; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE personservice_person (
    id integer NOT NULL,
    firstname character varying(511) NOT NULL,
    surname character varying(511) NOT NULL,
    updated_at date
);


ALTER TABLE personservice_person OWNER TO tuspadmin;

--
-- Name: precheck_berechtigung; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE precheck_berechtigung (
    id integer NOT NULL,
    rolle_id integer NOT NULL
);


ALTER TABLE precheck_berechtigung OWNER TO tuspadmin;

--
-- Name: precheck_berechtigung_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE precheck_berechtigung_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE precheck_berechtigung_id_seq OWNER TO tuspadmin;

--
-- Name: precheck_berechtigung_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE precheck_berechtigung_id_seq OWNED BY precheck_berechtigung.id;


--
-- Name: precheck_interessent; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE precheck_interessent (
    id integer NOT NULL,
    vorname character varying(255) NOT NULL,
    nachname character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    stadt character varying(255) NOT NULL,
    geburtsland integer NOT NULL,
    status integer NOT NULL,
    bearbeitungsdatum date,
    bewerbungsdatum date NOT NULL,
    abgeschlossenesstd character varying(255) NOT NULL,
    uni character varying(255) NOT NULL,
    abschluss character varying(255) NOT NULL,
    studienland integer NOT NULL,
    stg_tuhh integer NOT NULL,
    geschlecht integer NOT NULL,
    geburtsdatum date NOT NULL,
    std_dauer integer NOT NULL,
    abschluss_jahr integer NOT NULL,
    min_note real NOT NULL,
    max_note real NOT NULL,
    erreichte_note real NOT NULL,
    kommentar text
);


ALTER TABLE precheck_interessent OWNER TO tuspadmin;

--
-- Name: precheck_interessent_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE precheck_interessent_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE precheck_interessent_id_seq OWNER TO tuspadmin;

--
-- Name: precheck_interessent_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE precheck_interessent_id_seq OWNED BY precheck_interessent.id;


--
-- Name: precheck_note; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE precheck_note (
    id integer NOT NULL,
    note real NOT NULL,
    countries integer NOT NULL
);


ALTER TABLE precheck_note OWNER TO tuspadmin;

--
-- Name: precheck_note_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE precheck_note_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE precheck_note_id_seq OWNER TO tuspadmin;

--
-- Name: precheck_note_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE precheck_note_id_seq OWNED BY precheck_note.id;


--
-- Name: precheck_studiengang; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE precheck_studiengang (
    id integer NOT NULL,
    courseofstudy_id integer NOT NULL,
    aktiv integer NOT NULL
);


ALTER TABLE precheck_studiengang OWNER TO tuspadmin;

--
-- Name: precheck_studiengang_id_seq; Type: SEQUENCE; Schema: public; Owner: tuspadmin
--

CREATE SEQUENCE precheck_studiengang_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE precheck_studiengang_id_seq OWNER TO tuspadmin;

--
-- Name: precheck_studiengang_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: tuspadmin
--

ALTER SEQUENCE precheck_studiengang_id_seq OWNED BY precheck_studiengang.id;


--
-- Name: rightsandroleservice_roles; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE rightsandroleservice_roles (
    role_id integer NOT NULL,
    shorttext character varying(255) NOT NULL,
    defaulttext character varying(255) NOT NULL,
    longtext character varying(255) NOT NULL,
    uniquename character varying(255)
);


ALTER TABLE rightsandroleservice_roles OWNER TO tuspadmin;

--
-- Name: rightsandrolesservice_rolesforperson; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE rightsandrolesservice_rolesforperson (
    id integer NOT NULL,
    orgunit_lid integer NOT NULL,
    person_id integer NOT NULL,
    role_id integer NOT NULL,
    valid_from date NOT NULL,
    valid_to date NOT NULL
);


ALTER TABLE rightsandrolesservice_rolesforperson OWNER TO tuspadmin;

--
-- Name: valueservice_countries; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE valueservice_countries (
    id integer NOT NULL,
    lid integer NOT NULL,
    uniquename character varying(255),
    shorttext_de character varying(255),
    shorttext_en character varying(255),
    defaulttext_de character varying(255),
    defaulttext_en character varying(255),
    longtext_de character varying(255),
    longtext_en character varying(255),
    iso3166_1_alpha character varying(255),
    sortorder character varying(255),
    valid boolean NOT NULL
);


ALTER TABLE valueservice_countries OWNER TO tuspadmin;

--
-- Name: valueservice_gender; Type: TABLE; Schema: public; Owner: tuspadmin
--

CREATE TABLE valueservice_gender (
    id integer NOT NULL,
    uniquename character varying(255),
    defaulttext_de character varying(255),
    defaulttext_en character varying(255) NOT NULL,
    shorttext_de character varying(255),
    shorttext_en character varying(255),
    longtext_de character varying(255),
    longtext_en character varying(255),
    sortorder character varying(255),
    valid boolean NOT NULL
);


ALTER TABLE valueservice_gender OWNER TO tuspadmin;

--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input ALTER COLUMN id SET DEFAULT nextval('fame_input_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input_berechtigung ALTER COLUMN id SET DEFAULT nextval('fame_input_berechtigung_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input_type ALTER COLUMN id SET DEFAULT nextval('fame_input_type_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status ALTER COLUMN id SET DEFAULT nextval('fame_status_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_berechtigung ALTER COLUMN id SET DEFAULT nextval('fame_status_berechtigung_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_workflow ALTER COLUMN id SET DEFAULT nextval('fame_status_workflow_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_value ALTER COLUMN id SET DEFAULT nextval('fame_value_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_vorgang ALTER COLUMN id SET DEFAULT nextval('fame_vorgang_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_workflow ALTER COLUMN id SET DEFAULT nextval('fame_workflow_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY global_rolle ALTER COLUMN id SET DEFAULT nextval('global_rolle_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY global_rolle_user ALTER COLUMN id SET DEFAULT nextval('global_rolle_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY global_user ALTER COLUMN id SET DEFAULT nextval('global_user_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_berechtigung ALTER COLUMN id SET DEFAULT nextval('precheck_berechtigung_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_interessent ALTER COLUMN id SET DEFAULT nextval('precheck_interessent_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_note ALTER COLUMN id SET DEFAULT nextval('precheck_note_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_studiengang ALTER COLUMN id SET DEFAULT nextval('precheck_studiengang_id_seq'::regclass);


--
-- Name: courseofstudyservice_courseofstudy_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY courseofstudyservice_courseofstudy
    ADD CONSTRAINT courseofstudyservice_courseofstudy_pk PRIMARY KEY (id);


--
-- Name: courseofstudyservice_degree_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY courseofstudyservice_degree
    ADD CONSTRAINT courseofstudyservice_degree_pk PRIMARY KEY (id);


--
-- Name: fame_input_berechtigung_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input_berechtigung
    ADD CONSTRAINT fame_input_berechtigung_pk PRIMARY KEY (id);


--
-- Name: fame_input_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input
    ADD CONSTRAINT fame_input_pk PRIMARY KEY (id);


--
-- Name: fame_input_type_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input_type
    ADD CONSTRAINT fame_input_type_pk PRIMARY KEY (id);


--
-- Name: fame_status_berechtigung_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_berechtigung
    ADD CONSTRAINT fame_status_berechtigung_pk PRIMARY KEY (id);


--
-- Name: fame_status_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status
    ADD CONSTRAINT fame_status_pk PRIMARY KEY (id);


--
-- Name: fame_status_workflow_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_workflow
    ADD CONSTRAINT fame_status_workflow_pk PRIMARY KEY (id);


--
-- Name: fame_value_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_value
    ADD CONSTRAINT fame_value_pk PRIMARY KEY (id);


--
-- Name: fame_vorgang_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_vorgang
    ADD CONSTRAINT fame_vorgang_pk PRIMARY KEY (id);


--
-- Name: fame_workflow_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_workflow
    ADD CONSTRAINT fame_workflow_pk PRIMARY KEY (id);


--
-- Name: global_rolle_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY global_rolle
    ADD CONSTRAINT global_rolle_pk PRIMARY KEY (id);


--
-- Name: global_rolle_user_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY global_rolle_user
    ADD CONSTRAINT global_rolle_user_pk PRIMARY KEY (id);


--
-- Name: global_user_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY global_user
    ADD CONSTRAINT global_user_pk PRIMARY KEY (id);


--
-- Name: orgunitservice_orgunits_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY orgunitservice_orgunits
    ADD CONSTRAINT orgunitservice_orgunits_pk PRIMARY KEY (id);


--
-- Name: orgunitservice_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY orgunitservice
    ADD CONSTRAINT orgunitservice_pk PRIMARY KEY (id);


--
-- Name: periodservice_period_pkey; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY periodservice_period
    ADD CONSTRAINT periodservice_period_pkey PRIMARY KEY (id);


--
-- Name: personservice_person_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY personservice_person
    ADD CONSTRAINT personservice_person_pk PRIMARY KEY (id);


--
-- Name: precheck_berechtigung_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_berechtigung
    ADD CONSTRAINT precheck_berechtigung_pk PRIMARY KEY (id);


--
-- Name: precheck_interessent_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_interessent
    ADD CONSTRAINT precheck_interessent_pk PRIMARY KEY (id);


--
-- Name: precheck_note_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_note
    ADD CONSTRAINT precheck_note_pk PRIMARY KEY (id);


--
-- Name: precheck_studiengang_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_studiengang
    ADD CONSTRAINT precheck_studiengang_pk PRIMARY KEY (id);


--
-- Name: rightsandroleservice_roles_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY rightsandroleservice_roles
    ADD CONSTRAINT rightsandroleservice_roles_pk PRIMARY KEY (role_id);


--
-- Name: rightsandrolesservice_rolesforperson_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY rightsandrolesservice_rolesforperson
    ADD CONSTRAINT rightsandrolesservice_rolesforperson_pk PRIMARY KEY (id);


--
-- Name: valueservice_countries_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY valueservice_countries
    ADD CONSTRAINT valueservice_countries_pk PRIMARY KEY (id);


--
-- Name: valueservice_gender_pk; Type: CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY valueservice_gender
    ADD CONSTRAINT valueservice_gender_pk PRIMARY KEY (id);


--
-- Name: fki_degreeid_fk; Type: INDEX; Schema: public; Owner: tuspadmin
--

CREATE INDEX fki_degreeid_fk ON courseofstudyservice_courseofstudy USING btree (degreeid);


--
-- Name: fki_precheck_interessent_fk0; Type: INDEX; Schema: public; Owner: tuspadmin
--

CREATE INDEX fki_precheck_interessent_fk0 ON precheck_interessent USING btree (geburtsland);


--
-- Name: fki_precheck_interessent_fk1; Type: INDEX; Schema: public; Owner: tuspadmin
--

CREATE INDEX fki_precheck_interessent_fk1 ON precheck_interessent USING btree (studienland);


--
-- Name: degreeid_fk; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY courseofstudyservice_courseofstudy
    ADD CONSTRAINT degreeid_fk FOREIGN KEY (degreeid) REFERENCES courseofstudyservice_degree(id);


--
-- Name: fame_input_berechtigung_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input_berechtigung
    ADD CONSTRAINT fame_input_berechtigung_fk0 FOREIGN KEY (input_id) REFERENCES fame_input(id);


--
-- Name: fame_input_berechtigung_fk1; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input_berechtigung
    ADD CONSTRAINT fame_input_berechtigung_fk1 FOREIGN KEY (rolle_id) REFERENCES global_rolle(id);


--
-- Name: fame_input_berechtigung_fk2; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input_berechtigung
    ADD CONSTRAINT fame_input_berechtigung_fk2 FOREIGN KEY (status_id) REFERENCES fame_status(id);


--
-- Name: fame_input_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input
    ADD CONSTRAINT fame_input_fk0 FOREIGN KEY (input_type_id) REFERENCES fame_input_type(id);


--
-- Name: fame_input_fk1; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_input
    ADD CONSTRAINT fame_input_fk1 FOREIGN KEY (workflow_id) REFERENCES fame_workflow(id);


--
-- Name: fame_status_berechtigung_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_berechtigung
    ADD CONSTRAINT fame_status_berechtigung_fk0 FOREIGN KEY (workflow_id) REFERENCES fame_workflow(id);


--
-- Name: fame_status_berechtigung_fk1; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_berechtigung
    ADD CONSTRAINT fame_status_berechtigung_fk1 FOREIGN KEY (status_from_id) REFERENCES fame_status(id);


--
-- Name: fame_status_berechtigung_fk2; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_berechtigung
    ADD CONSTRAINT fame_status_berechtigung_fk2 FOREIGN KEY (rolle_id) REFERENCES global_rolle(id);


--
-- Name: fame_status_berechtigung_fk3; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_berechtigung
    ADD CONSTRAINT fame_status_berechtigung_fk3 FOREIGN KEY (status_to_id) REFERENCES fame_status(id);


--
-- Name: fame_status_workflow_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_workflow
    ADD CONSTRAINT fame_status_workflow_fk0 FOREIGN KEY (workflow_id) REFERENCES fame_workflow(id);


--
-- Name: fame_status_workflow_fk1; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_status_workflow
    ADD CONSTRAINT fame_status_workflow_fk1 FOREIGN KEY (status_id) REFERENCES fame_status(id);


--
-- Name: fame_value_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_value
    ADD CONSTRAINT fame_value_fk0 FOREIGN KEY (input_id) REFERENCES fame_input(id);


--
-- Name: fame_value_fk1; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_value
    ADD CONSTRAINT fame_value_fk1 FOREIGN KEY (vorgang_id) REFERENCES fame_vorgang(id);


--
-- Name: fame_vorgang_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_vorgang
    ADD CONSTRAINT fame_vorgang_fk0 FOREIGN KEY (workflow_id) REFERENCES fame_workflow(id);


--
-- Name: fame_vorgang_fk1; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY fame_vorgang
    ADD CONSTRAINT fame_vorgang_fk1 FOREIGN KEY (status_id) REFERENCES fame_status(id);


--
-- Name: global_rolle_user_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY global_rolle_user
    ADD CONSTRAINT global_rolle_user_fk0 FOREIGN KEY (user_id) REFERENCES global_user(id);


--
-- Name: global_rolle_user_fk1; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY global_rolle_user
    ADD CONSTRAINT global_rolle_user_fk1 FOREIGN KEY (rolle_id) REFERENCES global_rolle(id);


--
-- Name: precheck_berechtigung_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_berechtigung
    ADD CONSTRAINT precheck_berechtigung_fk0 FOREIGN KEY (rolle_id) REFERENCES global_rolle(id);


--
-- Name: precheck_interessent_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_interessent
    ADD CONSTRAINT precheck_interessent_fk0 FOREIGN KEY (geburtsland) REFERENCES valueservice_countries(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: precheck_interessent_fk1; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_interessent
    ADD CONSTRAINT precheck_interessent_fk1 FOREIGN KEY (studienland) REFERENCES valueservice_countries(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: precheck_interessent_fk2; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_interessent
    ADD CONSTRAINT precheck_interessent_fk2 FOREIGN KEY (stg_tuhh) REFERENCES precheck_studiengang(id);


--
-- Name: precheck_note_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_note
    ADD CONSTRAINT precheck_note_fk0 FOREIGN KEY (countries) REFERENCES valueservice_countries(id);


--
-- Name: precheck_studiengang_fk0; Type: FK CONSTRAINT; Schema: public; Owner: tuspadmin
--

ALTER TABLE ONLY precheck_studiengang
    ADD CONSTRAINT precheck_studiengang_fk0 FOREIGN KEY (courseofstudy_id) REFERENCES courseofstudyservice_courseofstudy(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
-- REVOKE ALL ON SCHEMA public FROM postgres;
-- GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON SEQUENCES  FROM PUBLIC;
-- ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON SEQUENCES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON SEQUENCES  TO "tusp-user";


--
-- Name: DEFAULT PRIVILEGES FOR FUNCTIONS; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON FUNCTIONS  FROM PUBLIC;
-- ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON FUNCTIONS  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON FUNCTIONS  TO "tusp-user";


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON TABLES  FROM PUBLIC;
-- ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public REVOKE ALL ON TABLES  FROM postgres;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public GRANT ALL ON TABLES  TO "tusp-user";



-- Versionierungstabelle
CREATE TABLE IF NOT EXISTS global_config (
    id integer NOT NULL,
    key character varying(255),
    value character varying(255) NOT NULL
);

ALTER TABLE global_config OWNER TO tuspadmin;
ALTER TABLE ONLY global_config ADD CONSTRAINT global_config_pk PRIMARY KEY (id);
CREATE INDEX fki_global_config_fk1 ON global_config USING btree (key);
CREATE SEQUENCE global_config_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE ONLY global_config ALTER COLUMN id SET DEFAULT nextval('global_config_id_seq'::regclass);
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.global.db_version', '1');
    
-- [Version 2]

SELECT 1;

-- [Version 3]

SELECT 2;

-- [Version 4]

CREATE TABLE test (
	id integer NOT NULL
);
    
-- [Version 5]

DROP TABLE test;
    
    
-- [Version 6]
ALTER TABLE public.courseofstudyservice_courseofstudy ALTER COLUMN id TYPE bigint;
ALTER TABLE public.precheck_studiengang ALTER COLUMN courseofstudy_id TYPE bigint;
ALTER TABLE public.precheck_studiengang ALTER COLUMN id TYPE bigint;
    

-- [Version 7]
ALTER TABLE valueservice_countries ADD COLUMN eu_member INTEGER;
ALTER TABLE valueservice_countries ADD COLUMN ewr_member INTEGER;
    
-- [Version 8]
CREATE TABLE weiche_abschluss (
    id SERIAL PRIMARY KEY,
    de VARCHAR(255) NOT NULL,
    en VARCHAR(255) NOT NULL,
    his_degree_id INTEGER NOT NULL,
 	FOREIGN KEY (his_degree_id) REFERENCES courseofstudyservice_degree (id)
);
CREATE TABLE weiche_studiengang (
    id SERIAL PRIMARY KEY,
    de VARCHAR(255) NOT NULL,
    en VARCHAR(255) NOT NULL,
    weiche_abschluss_id INTEGER NOT NULL,
 	FOREIGN KEY (weiche_abschluss_id) REFERENCES weiche_abschluss (id)
);



-- [Version 9]
ALTER TABLE fame_input ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_input ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_input ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_input ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_input ADD COLUMN kommentar VARCHAR(255);

ALTER TABLE fame_input_berechtigung ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_input_berechtigung ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_input_berechtigung ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_input_berechtigung ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_input_berechtigung ADD COLUMN kommentar VARCHAR(255);
ALTER TABLE fame_status_berechtigung ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_status_berechtigung ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_status_berechtigung ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_status_berechtigung ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_status_berechtigung ADD COLUMN kommentar VARCHAR(255);
ALTER TABLE fame_input_type ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_input_type ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_input_type ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_input_type ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_input_type ADD COLUMN kommentar VARCHAR(255);
ALTER TABLE fame_workflow ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_workflow ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_workflow ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_workflow ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_workflow ADD COLUMN kommentar VARCHAR(255);
ALTER TABLE fame_status ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_status ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_status ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_status ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_status ADD COLUMN kommentar VARCHAR(255);
ALTER TABLE fame_status_workflow ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_status_workflow ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_status_workflow ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_status_workflow ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_status_workflow ADD COLUMN kommentar VARCHAR(255);
ALTER TABLE fame_value ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_value ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_value ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_value ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_vorgang ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_vorgang ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_vorgang ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_vorgang ADD COLUMN bis TIMESTAMP;

-- [Version 10]
CREATE TABLE IF NOT EXISTS Student (
    id SERIAL PRIMARY KEY  
);

-- [Version 11]
ALTER TABLE Student ADD COLUMN hisId integer;


-- [Version 12]
ALTER TABLE fame_input_type ADD COLUMN uniq_name VARCHAR(255);

-- [Version 13 data require sync try]
INSERT INTO weiche_abschluss(de, en, his_degree_id) VALUES ('Bachelor of Science', 'Bachelor of Science', 1);
INSERT INTO weiche_abschluss(de, en, his_degree_id) VALUES ('Master of Science (deutsch- oder zweisprachig)', 'Master of Science (german or bilingual)', 2);
INSERT INTO weiche_abschluss(de, en, his_degree_id) VALUES ('Master of Science (englischsprachig)', 'Master of Science (english)', 2);

-- [Version 14 data require sync try]
-- veraltet

-- [Version 15 data require sync try]
DELETE FROM weiche_studiengang;
-- BS
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Allgemeine Ingenieurwissenschaften', 'General Engineering Science (German program)', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Bau- und Umweltingenieurwesen', 'Civil- and Environmental Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Bioverfahrenstechnik', 'Bioprocess Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Computer Science', 'Computer Science', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Elektrotechnik', 'Electrical Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Energie- und Umwelttechnik', 'Energy and Environmental Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('General Engineering Science', 'General Engineering Science (English program)', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Informatik-Ingenieurwesen', 'Computational Science and Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Logistik und Mobilität', 'Logistics and Mobility', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Maschinenbau', 'Mechanical Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Mechatronik', 'Mechatronics', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Schiffbau', 'Naval Architecture', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Technomathematik', 'Technomathematics', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Verfahrenstechnik', 'Process Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Bachelor of Science'));


-- MD 
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Bauingenieurwesen', 'Civil Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Bioverfahrenstechnik', 'Bioprocess Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Computer Science', 'Computer Science', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Elektrotechnik', 'Electrical Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Energietechnik', 'Energy Systems', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Energie- und Umwelttechnik', 'Energy and Environmental Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Flugzeug-Systemtechnik', 'Aircraft Systems Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Informatik-Ingenieurwesen', 'Computational Science and Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Internationales Wirtschaftsingenieurwesen', 'International Management and Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Logistik, Infrastruktur und Mobilität', 'Logistics, Infrastructure and Mobility', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Materialwissenschaft', 'Materials Science', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Mediziningenieurwesen', 'Biomedical Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Produktentwicklung, Werkstoffe und Produktion', 'Product Development, Materials and Production', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Regenerative Energien', 'Renewable Energies', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Schiffbau und Meerestechnik', 'Naval Architecture and Ocean Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Theoretischer Maschinenbau', 'Theoretical Mechanical Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Verfahrenstechnik', 'Process Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Wasser- und Umweltingenieurwesen', 'Water and Environmental Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));

-- AOS
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Chemical and Bioprocess Engineering', 'Chemical and Bioprocess Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Environmental Engineering', 'Environmental Engineering', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Mechanical Engineering and Management', 'Mechanical Engineering and Management', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Mechatronics', 'Mechatronics', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Microelectronics and Microsystems', 'Microelectronics and Microsystems', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Information and Communication Systems', 'Information and Communication Systems', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));



-- INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Global Innovation Management', 'Global Innovation Management', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));
-- INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('International Production Management', 'International Production Management', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (deutsch- oder zweisprachig)'));
-- INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Ship and Offshore Technology', 'Ship and Offshore Technology', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));



-- [Version 16]
ALTER TABLE personservice_person ADD COLUMN "valid" BOOLEAN;
ALTER TABLE rightsandrolesservice_rolesforperson ADD COLUMN "valid" BOOLEAN;
ALTER TABLE rightsandroleservice_roles ADD COLUMN "valid" BOOLEAN;
ALTER TABLE student ADD COLUMN "valid" BOOLEAN;
ALTER TABLE rightsandroleservice_roles RENAME TO rightsandrolesservice_roles;

-- [Version 17]
CREATE TABLE log4j_sync(
	id SERIAL PRIMARY KEY,
    message  character varying(2000),
    class    character varying(255),
   	priority character varying(64),
    log_date date
 );
 CREATE TABLE log4j_ldap(
	id SERIAL PRIMARY KEY,
    message  character varying(2000),
    class    character varying(255),
   	priority character varying(64),
    log_date date
 );
 CREATE TABLE log4j_unihh(
	id SERIAL PRIMARY KEY,
    message  character varying(2000),
    class    character varying(255),
   	priority character varying(64),
    log_date date
 );
 CREATE TABLE log4j_sop(
	id SERIAL PRIMARY KEY,
    message  character varying(2000),
    class    character varying(255),
   	priority character varying(64),
    log_date date
 );

-- [Version 18]
ALTER TABLE weiche_abschluss DROP CONSTRAINT "weiche_abschluss_his_degree_id_fkey" ;

-- [Version 19]
CREATE TABLE vorabcheck_studiengang
(
    id SERIAL NOT NULL,
    courseofstudy_id integer NOT NULL,
    aktiv integer NOT NULL
);

ALTER TABLE vorabcheck_studiengang OWNER TO tuspadmin;

ALTER TABLE ONLY vorabcheck_studiengang
    ADD CONSTRAINT vorabcheck_studiengang_fk0 FOREIGN KEY (courseofstudy_id) REFERENCES courseofstudyservice_courseofstudy(id);

-- [Version 20]
ALTER TABLE fame_input ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE fame_input ALTER COLUMN workflow_id SET DATA TYPE BIGINT;
ALTER TABLE fame_input ALTER COLUMN input_type_id SET DATA TYPE BIGINT;
ALTER TABLE fame_input_berechtigung ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE fame_input_berechtigung ALTER COLUMN status_id SET DATA TYPE BIGINT;
ALTER TABLE fame_input_berechtigung ALTER COLUMN rolle_id SET DATA TYPE BIGINT;
ALTER TABLE fame_input_berechtigung ALTER COLUMN input_id SET DATA TYPE BIGINT;
ALTER TABLE fame_status_berechtigung ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE fame_status_berechtigung ALTER COLUMN status_to_id SET DATA TYPE BIGINT;
ALTER TABLE fame_status_berechtigung ALTER COLUMN status_from_id SET DATA TYPE BIGINT;
ALTER TABLE fame_status_berechtigung ALTER COLUMN workflow_id SET DATA TYPE BIGINT;
ALTER TABLE fame_status_berechtigung ALTER COLUMN rolle_id SET DATA TYPE BIGINT;
ALTER TABLE fame_input_type ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE fame_status ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE fame_value ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE fame_value ALTER COLUMN vorgang_id SET DATA TYPE BIGINT;
ALTER TABLE fame_value ALTER COLUMN input_id SET DATA TYPE BIGINT;
ALTER TABLE fame_vorgang ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE fame_vorgang ALTER COLUMN status_id SET DATA TYPE BIGINT;
ALTER TABLE fame_vorgang ALTER COLUMN workflow_id SET DATA TYPE BIGINT;
ALTER TABLE fame_workflow ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE fame_status_workflow ALTER COLUMN status_id SET DATA TYPE BIGINT;

-- [Version 21 data require sync try]
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(420, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(431, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(444, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(768, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(422, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(426, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(432, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(425, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(423, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(253, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(414, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(936, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(428, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(424, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(636, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(429, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(427, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(430, 1);
INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv) VALUES(421, 1);

-- [Version 22 struct]
ALTER TABLE vorabcheck_studiengang ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE vorabcheck_studiengang ALTER COLUMN courseofstudy_id SET DATA TYPE BIGINT;

-- [Version 23 data require sync]
DELETE FROM vorabcheck_studiengang; -- sicherheitshalber aufräumen

INSERT INTO vorabcheck_studiengang(courseofstudy_id, aktiv_vorab, aktiv_vervollst) SELECT id, 1, 1 FROM courseofstudyservice_courseofstudy WHERE defaulttext_de IN (
'M.Sc.(D) Bauingenieurwesen (2008)', 
'M.Sc.(D) Bioverfahrenstechnik (2008)', 
'M.Sc.(D) Computational Informatics (2009)', 
'M.Sc.(D) Computer Science (2014)', 
'M.Sc.(D) Energietechnik (2008)', 
'M.Sc.(D) Elektrotechnik (2008)', 
'M.Sc.(D) Energie- u. Umwelttechn. (2008)', 
'M.Sc.(D) Flugzeug-Systemtechnik (2008)', 
'M.Sc.(D) Informatik-Ingenieurwesen (2008)', 
'M.Sc.(D) Int. Wirtschaftsing. IWI IWA (2013)', 
'M.Sc.(D) Logistik, Infrastr. Mob. (2007)', 
'M.Sc.(D) Materialwissenschaft (2015)', 
'M.Sc.(D) Mediziningenieurwesen (2008)', 
'M.Sc.(D) Prodentw., Werkst., Prod. (2008)', 
'M.Sc.(D) Regenerative Energien M (2012)', 
'M.Sc.(D) Schiffbau u. Meerestech. (2008)', 
'M.Sc.(D) Theoret. Maschinenbau (2008)', 
'M.Sc.(D) Verfahrenstechnik (2008)', 
'M.Sc.(D) Wasser- und Umwelting. (2008)'
);

-- [Version 24 data]
INSERT INTO fame_input_type (name) VALUES ('SELECT');
INSERT INTO fame_input_type (name) VALUES ('TEXTAREA');
INSERT INTO fame_input_type (name) VALUES ('TEXT');
INSERT INTO fame_input_type (name) VALUES ('CHECKBOX');
INSERT INTO fame_input_type (name) VALUES ('RADIOBUTTON');
INSERT INTO fame_input_type (name) VALUES ('DATE');
INSERT INTO fame_input_type (name) VALUES ('DATETIME');

-- [Version 25 struct]
DROP TABLE IF EXISTS fame_status_workflow;
DELETE FROM fame_status;
ALTER TABLE fame_status ADD COLUMN workflow_id BIGINT NOT NULL;
ALTER TABLE fame_status ADD FOREIGN KEY(workflow_id) REFERENCES fame_workflow(id);

-- [Version 26 data]
INSERT INTO fame_input_type (name) VALUES ('ALL');
ALTER TABLE fame_status ADD FOREIGN KEY(workflow_id) REFERENCES fame_workflow(id);

-- [Version 27 struct ]
CREATE TABLE log_statistik(
	id SERIAL NOT NULL,
    stat_error  INTEGER,
    stat_success   INTEGER,
   	stat_date TIMESTAMP,
    import_name varchar(10)
 );
 -- [Version 28 struct]
DROP TABLE IF EXISTS log4j_sync;
DROP TABLE IF EXISTS log4j_ldap;
DROP TABLE IF EXISTS log4j_unihh;
DROP TABLE IF EXISTS log4j_sop;
CREATE TABLE log4j_sync(
	id SERIAL PRIMARY KEY,
    message  character varying(2000),
    class    character varying(255),
   	priority character varying(64),
    log_date date
 );
 CREATE TABLE log4j_ldap(
	id SERIAL PRIMARY KEY,
    message  character varying(2000),
    class    character varying(255),
   	priority character varying(64),
    log_date date
 );
 CREATE TABLE log4j_unihh(
	id SERIAL PRIMARY KEY,
    message  character varying(2000),
    class    character varying(255),
   	priority character varying(64),
    log_date date
 );
 CREATE TABLE log4j_sop(
	id SERIAL PRIMARY KEY,
    message  character varying(2000),
    class    character varying(255),
   	priority character varying(64),
    log_date date
 );

-- [Version 29 struct]
ALTER TABLE fame_status_berechtigung DROP COLUMN workflow_id;
INSERT INTO public.global_rolle(name, beschreibung) VALUES ('Fame Testrolle', 'Damit es weitergehen kann gibts diese Rolle');
ALTER TABLE fame_status ADD COLUMN position BIGINT DEFAULT 0;

-- [Version 30 struct]
CREATE TABLE vorabcheck_bereich(
	id SERIAL PRIMARY KEY,
	stg_id    INTEGER NOT NULL,
    name      character varying(255),
    punkte    INTEGER
 );
 CREATE TABLE vorabcheck_fach(
	id SERIAL  PRIMARY KEY,
	bereich_id INTEGER NOT NULL,
    name       character varying(255),
    punkte     INTEGER
 );

-- [Version 31 struct]
ALTER TABLE vorabcheck_studiengang ADD PRIMARY KEY(id);
ALTER TABLE vorabcheck_bereich ADD FOREIGN KEY(stg_id) REFERENCES vorabcheck_studiengang(id);
ALTER TABLE vorabcheck_fach ADD FOREIGN KEY(bereich_id) REFERENCES vorabcheck_bereich(id);

-- [Version 32 data try]
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Bauingenieurwesen (2008)')
	), 'Mathematik', 18);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Bauingenieurwesen (2008)')
	), 'Mechanik', 12);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Bauingenieurwesen (2008)')
	), 'Ingenieurwiss. Grundlagen', 46);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Bioverfahrenstechnik (2008)')
	), 'Naturwiss. und allgemeine Grundlagen', 42);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Bioverfahrenstechnik (2008)')
	), 'Ingenieurwiss. Grundlagen', 48);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Computer Science (2014)')
	), 'Mathematik', 28);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Computer Science (2014)')
	), 'Praktische Informatik', 24);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Computer Science (2014)')
	), 'Theoretische Informatik', 18);

-- [Version 33 data try]
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Mathematik'), 'Analysis, Lineare Algebra, Differentialgleichungen', 16);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Mathematik'), 'Statistik', 2);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Mechanik'), 'Stereostatik / Elastostatik', 10);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Mechanik'), 'Hydromechanik / Hydraulik / Hydrostatik', 2);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Baustoffkunde', 6);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Baustatik', 6);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Bodenmechanik und Grundbau', 6);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Stahlbau', 6);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Stahlbetonbau', 6);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Wasserbau', 3);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Bauphysik / Baukonstruktion', 6);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Chemie', 3);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ingenieurwiss. Grundlagen'), 'Betriebswirtschaft und Rechtslehre', 4);

--[Version 34 struct]
ALTER TABLE vorabcheck_bereich ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE vorabcheck_bereich ALTER COLUMN stg_id SET DATA TYPE BIGINT;
ALTER TABLE vorabcheck_fach ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE vorabcheck_fach ALTER COLUMN bereich_id SET DATA TYPE BIGINT;

-- [Version 35 struct]
ALTER TABLE fame_input_berechtigung DROP CONSTRAINT IF EXISTS fame_input_berechtigung_fk1;
ALTER TABLE fame_status_berechtigung DROP CONSTRAINT IF EXISTS fame_status_berechtigung_fk2;
CREATE TABLE fame_rollen(
	id SERIAL PRIMARY KEY,
    name  character varying(511)
);
INSERT INTO fame_rollen(id, name) VALUES (1, 'ADMIN');
ALTER TABLE fame_input_berechtigung ADD FOREIGN KEY(rolle_id) REFERENCES fame_rollen(id);
ALTER TABLE fame_status_berechtigung ADD FOREIGN KEY(rolle_id) REFERENCES fame_rollen(id);


-- [Version 36 struct]
ALTER TABLE fame_rollen ALTER COLUMN id SET DATA TYPE BIGINT;

-- [Version 37 struct]
ALTER TABLE fame_workflow ADD COLUMN parent BIGINT NULL;
ALTER TABLE fame_workflow ADD FOREIGN KEY(parent) REFERENCES fame_workflow(id);

-- [Version 38 struct]
CREATE TABLE vorabcheck_ects(
	interessent_id BIGINT PRIMARY KEY NOT NULL,
	bereich_id BIGINT,
	fach_id BIGINT NOT NULL,
    punkte INTEGER
 );
 
-- [Version 39 struct]
ALTER TABLE vorabcheck_ects ADD FOREIGN KEY(bereich_id) REFERENCES vorabcheck_bereich(id);
ALTER TABLE vorabcheck_ects ADD FOREIGN KEY(fach_id) REFERENCES vorabcheck_fach(id);
  

-- [Version 40 struct]
CREATE TABLE fame_workflow_instance(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	workflow_id BIGINT NOT NULL,
	name VARCHAR(255) NOT NULL,
    parameter VARCHAR(4096)
);
ALTER TABLE fame_workflow_instance ADD COLUMN create_by VARCHAR(255);
ALTER TABLE fame_workflow_instance ADD COLUMN change_by VARCHAR(255);
ALTER TABLE fame_workflow_instance ADD COLUMN von TIMESTAMP;
ALTER TABLE fame_workflow_instance ADD COLUMN bis TIMESTAMP;
ALTER TABLE fame_workflow_instance ADD COLUMN kommentar VARCHAR(255);
DELETE FROM fame_value;
DELETE FROM fame_vorgang;
ALTER TABLE fame_workflow_instance ADD FOREIGN KEY(workflow_id) REFERENCES fame_workflow(id);
ALTER TABLE fame_vorgang DROP CONSTRAINT IF EXISTS fame_vorgang_fk0;
ALTER TABLE fame_vorgang RENAME COLUMN workflow_id TO workflow_instance_id;
ALTER TABLE fame_vorgang ADD FOREIGN KEY(workflow_instance_id) REFERENCES fame_workflow_instance(id);
ALTER TABLE fame_workflow DROP COLUMN parent;
ALTER TABLE fame_input ADD COLUMN pflicht INT NULL;
ALTER TABLE fame_input ADD COLUMN hidden INT NULL;
ALTER TABLE fame_input ADD COLUMN regex VARCHAR(511) NULL;
ALTER TABLE fame_workflow DROP COLUMN bulkmail_intervall;


-- [Version 41 struct]
ALTER TABLE fame_status ADD COLUMN hidden INT NULL;


-- [Version 42 struct]
ALTER TABLE fame_input ADD COLUMN connector_mapping VARCHAR(255) NULL;
INSERT INTO fame_input_type (name) VALUES ('CONNECTOR_TEXT');
INSERT INTO fame_input_type (name) VALUES ('CONNECTOR_FILE_NAME');
INSERT INTO fame_input_type (name) VALUES ('CONNECTOR_FILE_CONTENT');
UPDATE fame_input_type SET uniq_name = name;

-- [Version 43 struct]
--ALTER TABLE vorabcheck_ects DROP PRIMARY KEY;
--ALTER TABLE vorabcheck_ects DROP COLUMN bereich_id;

-- [Version 44 struct]
ALTER TABLE vorabcheck_ects DROP CONSTRAINT IF EXISTS vorabcheck_ects_bereich_id_fkey;
ALTER TABLE vorabcheck_ects DROP CONSTRAINT IF EXISTS vorabcheck_ects_pkey;

-- [Version 45 struct]
ALTER TABLE vorabcheck_ects DROP COLUMN bereich_id;

-- [Version 46 struct]
ALTER TABLE vorabcheck_bereich ADD COLUMN zusatz VARCHAR(255);
ALTER TABLE vorabcheck_fach ADD COLUMN zusatz VARCHAR(255);

-- [Version 47 data try]
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Int. Wirtschaftsing. IWI IWA (2013)')
	), 'Mathematik', 14);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Int. Wirtschaftsing. IWI IWA (2013)')
	), 'Mechanik', 10);
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Int. Wirtschaftsing. IWI IWA (2013)')
	), 'Ökonomie', 8);
INSERT INTO vorabcheck_bereich (stg_id, name, zusatz, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Int. Wirtschaftsing. IWI IWA (2013)')
	), 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich', 'Gesamt (da nur eine Vertiefung gefordert)', 40);
	
-- [Version 48 data try] 
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Mathematik' AND stg_id = (SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Int. Wirtschaftsing. IWI IWA (2013)'))), 'Analysis, Lineare Algebra, Statistik', 14);	
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Mechanik' AND stg_id = (SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Int. Wirtschaftsing. IWI IWA (2013)'))), 'Stereostatik, Elastostatik, Hydrostatik, Dynamik', 10);		
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ökonomie'), 'Betriebswirtschaft, Volkswirtschaft, ggf. Recht (Grundkonzepte der BWL, z.B. Rechnungswesen, Management; Grundkonzepte der VWL, z.B. Mikro- oder Makroökonomie; rechtliche Grundlagen, z.B. Wirtschaftsrecht, bürgerliches Recht)', 8);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Bauingenieurwesen', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Elektrotechnik', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Energie- und Umwelttechnik', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Informationstechnologie', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Logistik', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Luftfahrtsysteme', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Mechatronik', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Maschinenbau / Produktentwicklung und Produktion', 40);	
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Regenerative Energien', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Schiffbau', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Umwelttechnik', 40);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Vertiefte Kenntnisse in einer ingenieurs-wissen-schaftlichen Fachrichtung erforderlich'), 'Verfahrenstechnik und Biotechnologie', 40);

-- [Version 49 struct] 
--ALTER TABLE vorabcheck_studiengang ADD COLUMN prozentsatz INTEGER;

-- [Version 50 data try] 
--UPDATE vorabcheck_studiengang SET prozentsatz = 75;

-- [Version 51 struct] 
ALTER TABLE vorabcheck_studiengang ADD COLUMN prozentsatz INTEGER;

-- [Version 52 data try] 
UPDATE vorabcheck_studiengang SET prozentsatz = 75;

-- [Version 53 struct] 
ALTER TABLE vorabcheck_studiengang ADD COLUMN standardkz VARCHAR(2);

-- [Version 54 data try]
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Logistik, Infrastr. Mob. (2007)')
	), 'Mathematik, Informatik', 23);
	
INSERT INTO vorabcheck_bereich (stg_id, name, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Logistik, Infrastr. Mob. (2007)')
	), 'Ökonomie', 12);
	
INSERT INTO vorabcheck_bereich (stg_id, name, zusatz, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Logistik, Infrastr. Mob. (2007)')
	), 'Ingenieurwiss. Grundlagen Logistik und Mobilität', 'Kenntnisse in mindestens einem Anwendungsbereich erforderlich', 30);

-- [Version 55 data try]
INSERT INTO vorabcheck_bereich (stg_id, name, zusatz, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Logistik, Infrastr. Mob. (2007)')
	), 'Anwendungsbereich Produktion und Logistik', 'Gesamt', 30);
	
INSERT INTO vorabcheck_bereich (stg_id, name, zusatz, punkte) VALUES (
	(SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Logistik, Infrastr. Mob. (2007)')
	), 'Anwendungsbereich Infrastruktur und Mobilität', 'Gesamt', 30);

INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Mathematik, Informatik'), 'Analysis, Lineare Algebra, Statistik', 18);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Mathematik, Informatik'), 'Informatik, Geoinformationssysteme (GIS), Simulationsmethoden', 5);
INSERT INTO vorabcheck_fach (bereich_id, name, punkte) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Ökonomie' AND stg_id = (SELECT id FROM vorabcheck_studiengang WHERE courseofstudy_id = 
		(SELECT id from courseofstudyservice_courseofstudy WHERE defaulttext_de = 'M.Sc.(D) Logistik, Infrastr. Mob. (2007)'))), 'BWL, VWL (Grundkonzepte der BWL und VWL, bspw. Rechnungswesen, Controlling, Marketing, Management, Operations Research, Makroökonomie)', 12);

-- [Version 56 data try]
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Verkehrslogistik, Transportlogistik');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Beschaffungslogistik');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Materialflusstechnik, Fördertechnik, Lagerorganisation');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Fertigungstechnik');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Produktionslogistik');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Verkehrswirtschaft, Transportwirtschaft');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Bachelorarbeit in diesem Bereich*');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Verkehrsplanung, Mobilität');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Verkehrstechnik');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Bahnverkehr, öffentliche Verkehrssysteme');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Luftverkehr');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Seeverkehr');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Wirtschaftsverkehr, Güterverkehr');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Stadtplanung/räumliche Planung');
--INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Bachelorarbeit in diesem Bereich*');

-- [Version 57 data try]
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Verkehrslogistik, Transportlogistik');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Beschaffungslogistik');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Materialflusstechnik, Fördertechnik, Lagerorganisation');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Fertigungstechnik');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Produktionslogistik');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Verkehrswirtschaft, Transportwirtschaft');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Produktion und Logistik'), 'Bachelorarbeit in diesem Bereich*');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Verkehrsplanung, Mobilität');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Verkehrstechnik');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Bahnverkehr, öffentliche Verkehrssysteme');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Luftverkehr');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Seeverkehr');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Wirtschaftsverkehr, Güterverkehr');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Stadtplanung/räumliche Planung');
INSERT INTO vorabcheck_fach (bereich_id, name) VALUES ((SELECT id FROM vorabcheck_bereich WHERE name = 'Anwendungsbereich Infrastruktur und Mobilität'), 'Bachelorarbeit in diesem Bereich*');

-- [Version 58 struct]
ALTER TABLE fame_input ADD COLUMN table_pos INTEGER;
ALTER TABLE fame_vorgang ADD COLUMN gesperrt_durch VARCHAR(255);
ALTER TABLE fame_vorgang ADD COLUMN gesperrt_datum TIMESTAMP;
ALTER TABLE public.fame_value ALTER COLUMN eingabe SET DEFAULT NULL;

-- [Version 59 data]
INSERT INTO precheck_studiengang(courseofstudy_id, aktiv) SELECT id, 1 FROM courseofstudyservice_courseofstudy WHERE defaulttext_de IN (
'M.Sc. Int Chemical Bioprocess Eng. (2018)', 
'M.Sc. Int Biotechnology (2018)', 
'M.Sc. Int Environmental Engineering (2018)', 
'M.Sc. Int Electromagnetics, Optics (DPO 2018)', 
'M.Sc. Int Inform. a. Comm. Systems (2018)', 
'M.Sc. Int Materials Science (DPO 2018)', 
'M.Sc. Int Mechatronics (2018)', 
'M.Sc. Int Microelectronics & -syst. (2018)', 
'M.Sc. Int Process Engineering (DPO 2018)', 
'M.Sc. Int Structural Engineering (DPO 2018)', 
'M.Sc. Int Int. Production Mngm. (2018)'
);

-- [Version 59 struct]
ALTER TABLE fame_value ADD COLUMN create_time TIMESTAMP;
ALTER TABLE public.fame_value ALTER COLUMN create_time SET DEFAULT '1970-01-01 00:00:00';


-- [Version 60 struct]
ALTER TABLE valueservice_countries ALTER COLUMN id SET DATA TYPE BIGINT;
ALTER TABLE precheck_interessent ALTER COLUMN geburtsland SET DATA TYPE BIGINT;
ALTER TABLE precheck_interessent ALTER COLUMN studienland SET DATA TYPE BIGINT;

-- [Version 61 struct]
CREATE TABLE webservice_gwlehrer_stg(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	stg VARCHAR(10) NOT NULL,
	astat_unihh VARCHAR(10) NOT NULL,
    astat_tuhh VARCHAR(10) NOT NULL,
    dtxt VARCHAR(255) NOT NULL
);

-- [Version 60 data]
INSERT INTO webservice_gwlehrer_stg(stg, astat_unihh,astat_tuhh,dtxt) values
('GE','048','0048','Elektrotechnik, GewL'),
('GMT','108','0108','Metalltechnik'),
('GM','108','0108','Metalltechnik'),
('GB','304','0017','Bautechnik'),
('GE','48','0048','Elektrotechnik, GewL'),
('GH','535','0202','Holz-und Kunstst.technik'),
('GBH','635','0017','Bau- und Holztechnik'),
('GEI','636','0048','Elektrotechnik-Informationstechnik'),
('GF','713','0017','Farbt.u.Raumg.'),
('GG','723','0201','Graf. Techn / Medientechn'),
('GI','734','0201','Fach Informatik'),
('GT','747','0201','Technologie'),
('GME','768','0201','Medientechnik'),
('GAT','773','0201','Arbeitslehre / Technik');

-- [Version 62 struct]
DELETE FROM fame_input_type WHERE uniq_name = 'CONNECTOR_FILE_CONTENT';
CREATE TABLE fame_file(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	value_id BIGINT NOT NULL,
	mime_type VARCHAR(255) NOT NULL,
	content BYTEA NOT NULL,
    create_by VARCHAR(255) NULL,
    change_by VARCHAR(255) NULL,
    von TIMESTAMP NULL,
    bis TIMESTAMP NULL
);

-- [Version 63 struct]
CREATE TABLE vorabcheck_studiengang_archiv (
    id BIGINT PRIMARY KEY NOT NULL,
    courseofstudy_id INTEGER NOT NULL,
    aktiv INTEGER NOT NULL,
    prozentsatz INTEGER,
    standardkz VARCHAR(2)
);

-- [Version 64 struct]
ALTER TABLE ONLY vorabcheck_studiengang_archiv
    ADD CONSTRAINT vorabcheck_studiengang_archiv_fk0 FOREIGN KEY (courseofstudy_id) REFERENCES courseofstudyservice_courseofstudy(id);
    
-- [Version 65 struct]
CREATE TABLE vorabcheck_bereich_archiv(
	id BIGINT PRIMARY KEY NOT NULL,
	stg_id BIGINT NOT NULL,
    name character varying(255),
    punkte INTEGER,
    zusatz character varying(255)
 );
 CREATE TABLE vorabcheck_fach_archiv(
	id BIGINT PRIMARY KEY NOT NULL,
	bereich_id BIGINT NOT NULL,
    name character varying(255),
    punkte INTEGER,
    zusatz character varying(255)
 );
 
-- [Version 66 struct]
ALTER TABLE vorabcheck_bereich_archiv ADD FOREIGN KEY(stg_id) REFERENCES vorabcheck_studiengang_archiv(id);
ALTER TABLE vorabcheck_fach_archiv ADD FOREIGN KEY(bereich_id) REFERENCES vorabcheck_bereich_archiv(id);

-- [Version 67 struct]
ALTER TABLE vorabcheck_studiengang_archiv ALTER COLUMN courseofstudy_id SET DATA TYPE BIGINT;

-- [Version 68 struct]
ALTER TABLE vorabcheck_studiengang_archiv ADD COLUMN zeitstempel TIMESTAMP NOT NULL;
ALTER TABLE vorabcheck_studiengang_archiv ADD COLUMN grund VARCHAR(255) NOT NULL;

-- [Version 69 struct]
ALTER TABLE vorabcheck_studiengang_archiv ADD COLUMN origin_id BIGINT NOT NULL;
ALTER TABLE vorabcheck_bereich_archiv ADD COLUMN origin_id BIGINT NOT NULL;
ALTER TABLE vorabcheck_fach_archiv ADD COLUMN origin_id BIGINT NOT NULL;

-- [Version 70 struct]
ALTER TABLE vorabcheck_studiengang_archiv ADD COLUMN bearbeiter VARCHAR(255);
ALTER TABLE vorabcheck_bereich_archiv DROP COLUMN origin_id;
ALTER TABLE vorabcheck_fach_archiv DROP COLUMN origin_id;

-- [Version 71 struct]
CREATE SEQUENCE vorabcheck_studiengang_archiv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE SEQUENCE vorabcheck_bereich_archiv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE SEQUENCE vorabcheck_fach_archiv_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- [Version 72 struct]
ALTER TABLE ONLY vorabcheck_studiengang_archiv ALTER COLUMN id SET DEFAULT nextval('vorabcheck_studiengang_archiv_id_seq'::regclass);
ALTER TABLE ONLY vorabcheck_bereich_archiv ALTER COLUMN id SET DEFAULT nextval('vorabcheck_bereich_archiv_id_seq'::regclass);
ALTER TABLE ONLY vorabcheck_fach_archiv ALTER COLUMN id SET DEFAULT nextval('vorabcheck_fach_archiv_id_seq'::regclass);

-- [Version 73 struct]
ALTER TABLE ONLY vorabcheck_bereich_archiv ADD COLUMN origin_id BIGINT NOT NULL;

-- [Version 74 struct]
DELETE FROM vorabcheck_studiengang_archiv;
ALTER TABLE vorabcheck_studiengang_archiv ADD COLUMN session_id BIGINT NOT NULL;
ALTER TABLE vorabcheck_bereich_archiv ADD COLUMN session_id BIGINT NOT NULL;
ALTER TABLE vorabcheck_fach_archiv ADD COLUMN session_id BIGINT NOT NULL;
CREATE SEQUENCE vorabcheck_studiengang_archiv_session_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE ONLY vorabcheck_studiengang_archiv ALTER COLUMN session_id SET DEFAULT nextval('vorabcheck_studiengang_archiv_session_id_seq'::regclass);


-- [Version 75 struct]
-- [Version 76 struct]
do $$
begin

	IF NOT exists (
		select constraint_name 
		from information_schema.table_constraints 
		where table_name = 'log_statistik' 
		and constraint_type = 'PRIMARY KEY'
	) THEN

	ALTER TABLE log_statistik
	  ADD CONSTRAINT log_statistik_pkey PRIMARY KEY (id);

	END IF;

	IF EXISTS (
		SELECT 1 FROM information_schema.table_constraints 
		WHERE constraint_name = 'weiche_abschluss__weiche_abschluss_his_degree_id_fkey'
	) THEN
		ALTER TABLE public.weiche_abschluss
			RENAME CONSTRAINT weiche_abschluss__weiche_abschluss_his_degree_id_fkey TO weiche_abschluss_his_degree_id_fkey;
	END IF;

	IF NOT EXISTS (
		SELECT 1 FROM information_schema.table_constraints 
		WHERE constraint_name = 'weiche_abschluss_his_degree_id_fkey'
	) THEN
		ALTER TABLE weiche_abschluss
			ADD CONSTRAINT weiche_abschluss_his_degree_id_fkey
			FOREIGN KEY (his_degree_id) REFERENCES courseofstudyservice_degree(id);
	END IF;

	ALTER TABLE public.log4j_ldap ALTER COLUMN id TYPE bigint ;
	ALTER TABLE public.log4j_sop ALTER COLUMN id TYPE bigint ;
	ALTER TABLE public.log4j_sync ALTER COLUMN id TYPE bigint ;
	ALTER TABLE public.log4j_unihh ALTER COLUMN id TYPE bigint ;
	ALTER TABLE public.log_statistik ALTER COLUMN id TYPE bigint ;
end
$$ 

-- [Version 77 struct]
ALTER TABLE public.log4j_ldap
    ALTER COLUMN log_date TYPE timestamp without time zone ;
    
-- [Version 78 struct]    
ALTER TABLE log_statistik RENAME stat_success TO stat_new;
ALTER TABLE log_statistik ADD COLUMN stat_updated integer;

-- [Version 78 data]
INSERT INTO webservice_gwlehrer_stg (stg, astat_unihh,astat_tuhh,dtxt) VALUES ('TMA','118','0118','Technomathenatik');
    
    
-- [Version 79 struct] 
ALTER TABLE precheck_berechtigung DROP CONSTRAINT precheck_berechtigung_fk0;
DROP TABLE global_rolle_user;
DROP TABLE global_user;
DROP TABLE global_rolle;

-- [Version 80 struct]
ALTER TABLE fame_status_berechtigung DROP COLUMN bedingungen;
ALTER TABLE fame_status_berechtigung DROP COLUMN kommentar;
ALTER TABLE fame_status ADD COLUMN bedingungen VARCHAR(8192);

-- [Version 81 struct] 
DELETE FROM vorabcheck_ects;

-- [Version 82 struct]
ALTER TABLE vorabcheck_ects RENAME interessent_id TO bew_nr;
ALTER TABLE vorabcheck_ects ADD COLUMN id bigint NOT NULL;
ALTER TABLE vorabcheck_ects ADD COLUMN bereich_id bigint NOT NULL;
ALTER TABLE vorabcheck_ects ADD COLUMN erwerb bigint;
ALTER TABLE vorabcheck_ects ADD COLUMN modul bigint;
ALTER TABLE vorabcheck_ects ADD COLUMN nachweis bigint;
CREATE SEQUENCE vorabcheck_ects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE ONLY vorabcheck_ects ADD CONSTRAINT vorabcheck_ects_bereich_id_fkey FOREIGN KEY (bereich_id) REFERENCES vorabcheck_bereich(id);
ALTER TABLE ONLY vorabcheck_ects ALTER COLUMN id SET DEFAULT nextval('vorabcheck_ects_id_seq'::regclass);
CREATE TABLE vorabcheck_bewerber (
    id bigint NOT NULL,
    bew_nr bigint NOT NULL,
    stg_id bigint NOT NULL,
    anmerk character varying(255),
    zusatz_erbracht character varying(255),
    zusatz_ausstehend character varying(255)
);

-- [Version 83 struct]
CREATE SEQUENCE vorabcheck_bewerber_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE ONLY vorabcheck_bewerber ALTER COLUMN id SET DEFAULT nextval('vorabcheck_bewerber_id_seq'::regclass);

-- [Version 84 struct]
DELETE FROM vorabcheck_ects;
ALTER TABLE ONLY vorabcheck_ects RENAME bew_nr TO bew_id;

-- [Version 85 struct]
ALTER TABLE ONLY vorabcheck_bewerber ADD CONSTRAINT vorabcheck_bewerber_pkey PRIMARY KEY (id);
ALTER TABLE ONLY vorabcheck_ects ADD CONSTRAINT vorabcheck_ects_bew_id_fkey FOREIGN KEY (bew_id) REFERENCES vorabcheck_bewerber(id);

-- [Version 86 struct]
CREATE TABLE vorabcheck_extra (
    id SERIAL PRIMARY KEY,
    stg_id bigint NOT NULL,
    titel character varying(255),
    name character varying(255)
);
ALTER TABLE ONLY vorabcheck_studiengang ADD COLUMN extrakz VARCHAR(2);
ALTER TABLE ONLY vorabcheck_studiengang_archiv ADD COLUMN extrakz VARCHAR(2);
ALTER TABLE ONLY vorabcheck_extra ADD CONSTRAINT vorabcheck_extra_stg_id_fkey FOREIGN KEY (stg_id) REFERENCES vorabcheck_studiengang(id);

-- [Version 87 struct]
ALTER TABLE ONLY vorabcheck_studiengang ADD COLUMN extratitel VARCHAR(255);

-- [Version 88 struct]
CREATE TABLE psv_student(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	hisid BIGINT NOT NULL,
	valid BOOLEAN
);
INSERT INTO psv_student (SELECT * FROM student);
DROP TABLE student;

-- [Version 89 struct]
ALTER TABLE courseofstudyservice_courseofstudy ADD COLUMN uniquename VARCHAR(255);

-- [Version 90 struct]
ALTER TABLE orgunitservice_orgunits ADD COLUMN uniquename VARCHAR(255);
ALTER TABLE orgunitservice_orgunits ADD COLUMN shorttext_en VARCHAR(255);


-- [Version 91 struct]
CREATE TABLE valueservice_academicdegree (
    id BIGINT PRIMARY KEY,
    defaulttext_de character varying(255),
    defaulttext_en character varying(255),
    shorttext_de character varying(255),
    shorttext_en character varying(255),
    longtext_de character varying(255),
    longtext_en character varying(255),
    uniquename character varying(255),
    valid boolean
);

ALTER TABLE personservice_person ADD COLUMN academicdegree_suffix VARCHAR(255);
ALTER TABLE personservice_person ADD COLUMN academicdegree_id BIGINT;
ALTER TABLE personservice_person ADD CONSTRAINT fk_valueservice_academicdegree_academicdegree_id FOREIGN KEY (academicdegree_id) REFERENCES valueservice_academicdegree(id);


-- [Version 92 struct]
CREATE TABLE valueservice_addresstag (
    id BIGINT PRIMARY KEY,
    defaulttext_de character varying(255),
    defaulttext_en character varying(255),
    shorttext_de character varying(255),
    shorttext_en character varying(255),
    longtext_de character varying(255),
    longtext_en character varying(255),
    uniquename character varying(255),
    valid boolean
);
CREATE TABLE valueservice_eaddresstype (
    id BIGINT PRIMARY KEY,
    defaulttext_de character varying(255),
    defaulttext_en character varying(255),
    shorttext_de character varying(255),
    shorttext_en character varying(255),
    longtext_de character varying(255),
    longtext_en character varying(255),
    uniquename character varying(255),
    addresstype character varying(255),
    valid boolean
);


-- [Version 93 struct]
ALTER TABLE valueservice_addresstag ADD COLUMN sortorder character varying(255);
ALTER TABLE valueservice_eaddresstype ADD COLUMN sortorder character varying(255);


-- [Version 94 struct]

ALTER TABLE courseofstudyservice_degree ADD COLUMN lid BIGINT;
ALTER TABLE courseofstudyservice_degree ADD COLUMN uniquename VARCHAR(255);
ALTER TABLE courseofstudyservice_degree ADD COLUMN valid_from DATE;
ALTER TABLE courseofstudyservice_degree ADD COLUMN valid_to DATE;
ALTER TABLE courseofstudyservice_degree ADD COLUMN astat_bund VARCHAR(255);
ALTER TABLE courseofstudyservice_degree ADD COLUMN astat_land VARCHAR(255);

CREATE TABLE valueservice_districtvalue (
    id BIGINT,
    lid BIGINT,
    uniquename VARCHAR(255),
    shorttext_de VARCHAR(255),
    shorttext_en VARCHAR(255),
    defaulttext_de VARCHAR(255),
    defaulttext_en VARCHAR(255),
    longtext_de VARCHAR(255),
    longtext_en VARCHAR(255),
    sortorder VARCHAR(255),
    valid_from DATE,
    valid_to DATE,
    astat VARCHAR(255),
    valid boolean
);

CREATE TABLE valueservice_enrollmentvalue (
    id BIGINT,
    uniquename VARCHAR(255),
    shorttext_de VARCHAR(255),
    shorttext_en VARCHAR(255),
    defaulttext_de VARCHAR(255),
    defaulttext_en VARCHAR(255),
    longtext_de VARCHAR(255),
    longtext_en VARCHAR(255),
    sortorder VARCHAR(255),
    astat VARCHAR(255),
    valid boolean
);

CREATE TABLE valueservice_notificationcategoryvalue (
    id BIGINT,
    uniquename VARCHAR(255),
    shorttext_de VARCHAR(255),
    shorttext_en VARCHAR(255),
    defaulttext_de VARCHAR(255),
    defaulttext_en VARCHAR(255),
    longtext_de VARCHAR(255),
    longtext_en VARCHAR(255),
    sortorder VARCHAR(255),
    valid boolean
);

CREATE TABLE valueservice_studentstatusvalue (
    id BIGINT,
    uniquename VARCHAR(255),
    shorttext_de VARCHAR(255),
    shorttext_en VARCHAR(255),
    defaulttext_de VARCHAR(255),
    defaulttext_en VARCHAR(255),
    longtext_de VARCHAR(255),
    longtext_en VARCHAR(255),
    sortorder VARCHAR(255),
    valid_from DATE,
    valid_to DATE,
    astat VARCHAR(255),
    priority BIGINT,
    valid boolean
);

CREATE TABLE valueservice_studystatusvalue (
    id BIGINT,
    uniquename VARCHAR(255),
    shorttext_de VARCHAR(255),
    shorttext_en VARCHAR(255),
    defaulttext_de VARCHAR(255),
    defaulttext_en VARCHAR(255),
    longtext_de VARCHAR(255),
    longtext_en VARCHAR(255),
    sortorder VARCHAR(255),
    astat VARCHAR(255),
    priority BIGINT,
    valid boolean
);

CREATE TABLE valueservice_subject (
    id BIGINT,
    lid BIGINT,
    uniquename VARCHAR(255),
    shorttext_de VARCHAR(255),
    shorttext_en VARCHAR(255),
    defaulttext_de VARCHAR(255),
    defaulttext_en VARCHAR(255),
    longtext_de VARCHAR(255),
    longtext_en VARCHAR(255),
    sortorder VARCHAR(255),
    valid_from DATE,
    valid_to DATE,
    astat_bund VARCHAR(255),
    astat_land VARCHAR(255),
    valid boolean
);

CREATE TABLE valueservice_termtypevalue (
    id BIGINT,
    uniquename VARCHAR(255),
    shorttext_de VARCHAR(255),
    shorttext_en VARCHAR(255),
    defaulttext_de VARCHAR(255),
    defaulttext_en VARCHAR(255),
    longtext_de VARCHAR(255),
    longtext_en VARCHAR(255),
    sortorder VARCHAR(255),
    term_category BIGINT,
    term_number BIGINT,
    valid boolean
);

CREATE TABLE valueservice_titlevalue (
    id BIGINT,
    uniquename VARCHAR(255),
    shorttext_de VARCHAR(255),
    shorttext_en VARCHAR(255),
    defaulttext_de VARCHAR(255),
    defaulttext_en VARCHAR(255),
    longtext_de VARCHAR(255),
    longtext_en VARCHAR(255),
    sortorder VARCHAR(255),
    shorttext_female_de VARCHAR(255),
    shorttext_female_en VARCHAR(255),
    defaulttext_female_de VARCHAR(255),
    defaulttext_female_en VARCHAR(255),
    longtext_female_de VARCHAR(255),
    longtext_female_en VARCHAR(255),
    lettersalutation_de VARCHAR(255),
    lettersalutation_en VARCHAR(255),
    lettersalutation_female_de VARCHAR(255),
    lettersalutation_female_en VARCHAR(255),
    valid boolean
);


-- [Version 95 struct]
ALTER TABLE valueservice_districtvalue ADD PRIMARY KEY (id);
ALTER TABLE valueservice_enrollmentvalue ADD PRIMARY KEY (id);
ALTER TABLE valueservice_notificationcategoryvalue ADD PRIMARY KEY (id);
ALTER TABLE valueservice_studentstatusvalue ADD PRIMARY KEY (id);
ALTER TABLE valueservice_studystatusvalue ADD PRIMARY KEY (id);
ALTER TABLE valueservice_subject ADD PRIMARY KEY (id);
ALTER TABLE valueservice_termtypevalue ADD PRIMARY KEY (id);
ALTER TABLE valueservice_titlevalue ADD PRIMARY KEY (id);

-- [Version 96 struct]
DROP TABLE orgunitservice;

-- [Version 97 data require sync try]
INSERT INTO weiche_studiengang(de, en, weiche_abschluss_id) VALUES('Global Technology and Innovation Management & Entrepreneurship', 'Global Technology and Innovation Management & Entrepreneurship', (SELECT id FROM weiche_abschluss WHERE de = 'Master of Science (englischsprachig)'));

-- [Version 98 data]
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.stu.gdm.sequenceNumber', '1000');

-- [Version 99 struct]
TRUNCATE TABLE vorabcheck_fach_archiv, vorabcheck_bereich_archiv, vorabcheck_studiengang_archiv;

-- [Version 100 struct]
ALTER TABLE ONLY vorabcheck_ects
    ADD CONSTRAINT vorabcheck_ects_pk PRIMARY KEY (id);
    
-- [Version 101 struct]
ALTER TABLE ONLY vorabcheck_extra ALTER COLUMN id TYPE bigint;


-- [Version 102 struct]
CREATE TABLE gdm_processed_file (
    id BIGSERIAL,
    in_name VARCHAR(255),
    out_name VARCHAR(255),
	hash VARCHAR(255),
    verarbeitet DATE
);
CREATE UNIQUE INDEX  gdm_processed_file_in_name ON gdm_processed_file(in_name);

-- [Version 103 struct]
CREATE TABLE IF NOT EXISTS mobon_hio_country_mapping (
    id BIGINT,
    mobon_name VARCHAR(100),
    hio_country_id BIGINT
);
ALTER TABLE mobon_hio_country_mapping ADD PRIMARY KEY (id);


-- [Version 104 struct]
CREATE TABLE gdm_history (
    id BIGSERIAL,
    filename VARCHAR(255),
    username VARCHAR(255),
    zeit TIMESTAMP
);
CREATE INDEX gdm_history_filename ON gdm_history(filename);

-- [Version 105 struct]
ALTER TABLE ONLY gdm_history
    ADD CONSTRAINT gdm_history_pk PRIMARY KEY (id);
UPDATE gdm_processed_file SET id = tmp.id
	FROM (SELECT in_name, ROW_NUMBER() OVER (ORDER BY in_name) AS id FROM gdm_processed_file) AS tmp
	WHERE gdm_processed_file.in_name = tmp.in_name;
CREATE SEQUENCE gdm_processed_file_seq MINVALUE 1000;
ALTER TABLE gdm_processed_file ALTER id SET DEFAULT nextval('gdm_processed_file_seq');
ALTER SEQUENCE gdm_processed_file_seq OWNED BY gdm_processed_file.id;
ALTER TABLE ONLY gdm_processed_file
    ADD CONSTRAINT gdm_processed_file_pk PRIMARY KEY (id);


    
-- [Version 106 struct]
CREATE TABLE global_identity(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL,
	private_key BYTEA NOT NULL,
	public_key BYTEA NOT NULL,
	valid BOOLEAN
);
CREATE TABLE global_known_host(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	hostname VARCHAR(255) NOT NULL,
	host_key BYTEA NOT NULL,
	valid BOOLEAN
);

-- [Version 107 struct]
ALTER TABLE global_config ADD CONSTRAINT key_is_unique UNIQUE (key);

-- [Version 108 struct]
ALTER TABLE fame_workflow ADD COLUMN input_validation_code TEXT;

-- [Version 109 struct]
ALTER TABLE vorabcheck_bewerber RENAME bew_nr TO codenummer;
ALTER TABLE vorabcheck_bewerber ADD COLUMN vorabcheck_zeitstempel timestamp without time zone;
ALTER TABLE vorabcheck_bewerber ADD COLUMN bew_vervollst_zeitstempel timestamp without time zone;
ALTER TABLE precheck_studiengang ADD UNIQUE (courseofstudy_id);
ALTER TABLE precheck_interessent DROP CONSTRAINT precheck_interessent_fk2;
ALTER TABLE precheck_interessent ADD FOREIGN KEY (stg_tuhh)
  REFERENCES precheck_studiengang(courseofstudy_id);
  
-- [Version 110 struct]  
ALTER TABLE precheck_interessent ALTER COLUMN stg_tuhh SET DATA TYPE BIGINT;

-- [Version 111 struct] 
ALTER TABLE precheck_interessent ADD COLUMN tuhh_note REAL;

-- [Version 112 struct]  
ALTER TABLE precheck_interessent ALTER COLUMN status SET DATA TYPE VARCHAR(255);

-- [Version 113 data try] 
insert into precheck_note(note, countries) values (9.9 , (select id from valueservice_countries where iso3166_1_alpha = 'AF'));

-- [Version 114 data try]
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='EG'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='AL'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='AD'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='AO'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='AG'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='GQ'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='AR'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='AM'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='AZ'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='ET'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='AU'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='BS'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='BH'));
insert into precheck_note(note,countries) values(2.1, (select id from valueservice_countries where iso3166_1_alpha ='BD'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='BB'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='BY'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='BE'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='BZ'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='BJ'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='BT'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='BO'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='BA'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='BW'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='BR'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='BN'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='BG'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='BF'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='BI'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='CV'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='CL'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='CK'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='CR'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='CI'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='DM'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='DO'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='DJ'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='EC'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='SV'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='ER'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='EE'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='FJ'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='FI'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='FR'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='GA'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='GM'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='GE'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='GH'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='GD'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='GR'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='GT'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='GN'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='GW'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='GY'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='HT'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='HN'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='HK'));
insert into precheck_note(note,countries) values(0, (select id from valueservice_countries where iso3166_1_alpha ='IN'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='ID'));
insert into precheck_note(note,countries) values(2.875, (select id from valueservice_countries where iso3166_1_alpha ='IQ'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='IR'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='IE'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='IS'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='IL'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='IT'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='JM'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='JP'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='YE'));
insert into precheck_note(note,countries) values(2.875, (select id from valueservice_countries where iso3166_1_alpha ='JO'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='KH'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='CM'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='CA'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='KZ'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='QA'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='KE'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='KG'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='KI'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='CO'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='KM'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='CG'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='CD'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='XK'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='HR'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='CU'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='KW'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='DZ'));
insert into precheck_note(note,countries) values(0, (select id from valueservice_countries where iso3166_1_alpha ='CN'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='DE'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='DK'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='KR'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='LA'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='LS'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='LV'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='LB'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='LR'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='LY'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='LI'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='LT'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='LU'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='MG'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='MW'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='MY'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='MV'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='ML'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='MT'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='MA'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='MH'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='MR'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='MU'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='MK'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='MX'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='FM'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='MD'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='MC'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='MN'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='ME'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='MZ'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='MM'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='NA'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='NR'));
insert into precheck_note(note,countries) values(2.1, (select id from valueservice_countries where iso3166_1_alpha ='NP'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='NZ'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='NI'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='NL'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='NE'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='NG'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='NU'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='NO'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='OM'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='AT'));
insert into precheck_note(note,countries) values(2.1, (select id from valueservice_countries where iso3166_1_alpha ='PK'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='PW'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='PA'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='PG'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='PY'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='PE'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='PH'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='PL'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='PT'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='RW'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='RO'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='RU'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SB'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='ZM'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='WS'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SM'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='ST'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SA'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='SE'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='CH'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='SN'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='RS'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SC'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SL'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='ZW'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SG'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='SK'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='SI'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SO'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='ES'));
insert into precheck_note(note,countries) values(2.1, (select id from valueservice_countries where iso3166_1_alpha ='LK'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='KN'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='LC'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='VC'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='ZA'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SD'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SS'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SR'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='SZ'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='SY'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='TJ'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='TZ'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='TL'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='TG'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='TO'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='TT'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='TD'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='CZ'));
insert into precheck_note(note,countries) values(2.9, (select id from valueservice_countries where iso3166_1_alpha ='TN'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='TM'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='TV'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='UG'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='UA'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='HU'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='UY'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='UZ'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='VU'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='VA'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='VE'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='AE'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='GB'));
insert into precheck_note(note,countries) values(2, (select id from valueservice_countries where iso3166_1_alpha ='VN'));
insert into precheck_note(note,countries) values(3.1, (select id from valueservice_countries where iso3166_1_alpha ='CF'));
insert into precheck_note(note,countries) values(10, (select id from valueservice_countries where iso3166_1_alpha ='CY'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='KP'));
insert into precheck_note(note,countries) values(9.9, (select id from valueservice_countries where iso3166_1_alpha ='PS'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='TW'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='US'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='TR'));
insert into precheck_note(note,countries) values(2.5, (select id from valueservice_countries where iso3166_1_alpha ='TH'));


-- [Version 115 struct]
ALTER TABLE precheck_note ALTER COLUMN countries SET DATA TYPE BIGINT;

-- [Version 116 struct]
ALTER TABLE precheck_interessent ADD COLUMN de_note REAL; 

-- [Version 117 struct]
ALTER TABLE precheck_interessent DROP COLUMN de_note; 

-- [Version 118 struct]
CREATE TABLE precheck_status(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL
);

-- [Version 119 struct]
ALTER TABLE precheck_interessent DROP COLUMN status;
ALTER TABLE precheck_interessent ADD COLUMN status BIGINT;

-- [Version 120 struct]
ALTER TABLE precheck_interessent ADD CONSTRAINT precheck_interessent_status_id_fk2 FOREIGN KEY (status) REFERENCES precheck_status(id);

-- [Version 121 data]
insert into precheck_status (id,name) values (1,'AKZEPTIERT');
insert into precheck_status (id,name) values (2,'UNBEKANNT');
insert into precheck_status (id,name) values (3,'FRAGLICH');
insert into precheck_status (id,name) values (4,'ABGELEHNT WG. NOTE');
insert into precheck_status (id,name) values (5,'ABGELEHNT WG. STG');
insert into precheck_status (id,name) values (6,'ABGELEHNT WG. (NT./STG./HS.)');
insert into precheck_status (id,name) values (7,'GELÖSCHT');

-- [Version 123 data]
insert into precheck_status (id,name) values (8,'SONDERFALL TIE');

-- [Version 124 struct]
ALTER TABLE precheck_status ALTER COLUMN id SET DATA TYPE BIGINT;

-- [Version 125 struct]
CREATE TABLE global_jobs(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL,
	start TIMESTAMP NOT NULL,
	ende TIMESTAMP NULL,
	status VARCHAR(255),
	server VARCHAR(255)
);

-- [Version 126 struct]
-- Redundant zu 109
-- ALTER TABLE vorabcheck_bewerber RENAME bew_nr TO codenummer;
-- ALTER TABLE vorabcheck_bewerber ADD COLUMN vorabcheck_zeitstempel timestamp without time zone;
-- ALTER TABLE vorabcheck_bewerber ADD COLUMN bew_vervollst_zeitstempel timestamp without time zone;

-- [Version 127 struct]
ALTER TABLE vorabcheck_bewerber ALTER COLUMN codenummer SET DATA TYPE VARCHAR;

-- [Version 128 data]
UPDATE fame_input_type SET uniq_name = name;

-- [Version 129 struct]
ALTER TABLE precheck_interessent ADD COLUMN bearbeitet boolean;

-- [Version 130 struct]
ALTER TABLE precheck_interessent ALTER COLUMN bearbeitet SET DATA TYPE char;

-- [Version 131 struct]
ALTER TABLE precheck_interessent ALTER COLUMN bearbeitet SET DATA TYPE VARCHAR(1);

-- [Version 132 struct]
ALTER TABLE precheck_interessent ADD COLUMN verifier_code VARCHAR(255);
ALTER TABLE precheck_interessent ADD COLUMN institution VARCHAR(255);
ALTER TABLE precheck_studiengang ADD COLUMN shorttext VARCHAR(511);

-- [Version 133 struct]
CREATE TABLE fame_log(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	change_time TIMESTAMP NOT NULL,
	vorgang_id BIGINT NOT NULL,
	value_id BIGINT NOT NULL,
	"user" VARCHAR(255) NULL,
	subject VARCHAR(511)
);
ALTER TABLE fame_file DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_value DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_vorgang DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_input DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_input_berechtigung DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_input_type DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_status DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_status_berechtigung DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_workflow DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;
ALTER TABLE fame_workflow_instance DROP COLUMN von, DROP COLUMN bis, DROP COLUMN create_by, DROP COLUMN change_by;

-- [Version 134 struct]
-- ALTER TABLE fame_workflow_instance ADD COLUMN rechte_parameter VARCHAR(255);
-- ALTER TABLE fame_status DROP COLUMN bedingungen;

-- [Version 135 struct]
ALTER TABLE fame_workflow_instance ADD COLUMN rechte_parameter VARCHAR(255);
ALTER TABLE fame_status DROP COLUMN bedingungen;

-- [Version 136 struct]
CREATE TABLE bewvervollst_ects (
    bewhash BIGINT NOT NULL,
    stg_id BIGINT NOT NULL,
    bereich_id BIGINT,
    punkte INTEGER,
    ergebnis VARCHAR(5)
);

-- [Version 137 struct]
ALTER TABLE ONLY bewvervollst_ects ADD CONSTRAINT bewvervollst_ects_stg_id_fkey FOREIGN KEY (stg_id) REFERENCES vorabcheck_studiengang(id);
ALTER TABLE ONLY bewvervollst_ects ADD CONSTRAINT bewvervollst_ects_bereich_id_fkey FOREIGN KEY (bereich_id) REFERENCES vorabcheck_bereich(id);

-- [Version 138 struct]
ALTER TABLE ONLY vorabcheck_ects ALTER COLUMN erwerb SET DATA TYPE VARCHAR(100);
ALTER TABLE ONLY vorabcheck_ects ALTER COLUMN modul SET DATA TYPE VARCHAR(100);


-- [Version 139 struct]
ALTER TABLE orgunitservice_orgunits DROP COLUMN shorttext_en;
ALTER TABLE orgunitservice_orgunits DROP COLUMN longtext_en;
ALTER TABLE orgunitservice_orgunits DROP COLUMN displaytext_en;

-- [Version 140 struct]
ALTER TABLE ONLY vorabcheck_studiengang ADD COLUMN aos BOOLEAN;

-- [Version 141 struct]
ALTER TABLE ONLY vorabcheck_studiengang RENAME COLUMN aktiv TO aktiv_vorab;
ALTER TABLE ONLY vorabcheck_studiengang ADD COLUMN aktiv_vervollst BOOLEAN;

-- [Version 142 struct]
ALTER TABLE ONLY vorabcheck_studiengang ALTER COLUMN aktiv_vervollst SET DATA TYPE integer USING (aktiv_vervollst::integer);

-- [Version 143 struct]
ALTER TABLE ONLY vorabcheck_studiengang_archiv RENAME COLUMN aktiv TO aktiv_vorab;
ALTER TABLE ONLY vorabcheck_studiengang_archiv ADD COLUMN aktiv_vervollst integer;

-- [Version 144 struct]
ALTER TABLE ONLY vorabcheck_studiengang ALTER COLUMN aktiv_vervollst SET NOT NULL;
ALTER TABLE ONLY vorabcheck_studiengang_archiv ALTER COLUMN aktiv_vervollst SET NOT NULL;
update vorabcheck_studiengang set aktiv_vervollst = 0 where aktiv_vervollst is null;
update vorabcheck_studiengang_archiv set aktiv_vervollst = 0 where aktiv_vervollst is null;

-- [Version 145 struct]
DROP TABLE bewvervollst_ects;

-- [Version 146 struct]
ALTER TABLE ONLY vorabcheck_bewerber ADD COLUMN semester VARCHAR(15);

-- [Version 147 struct]
INSERT INTO fame_input_type (name) VALUES ('BEARB_DATE');
INSERT INTO fame_input_type (name) VALUES ('BEARB_NAME');
UPDATE fame_input_type SET uniq_name = name;
ALTER TABLE fame_vorgang ADD COLUMN warn VARCHAR(4096);


-- [Version 148 struct]
ALTER TABLE ONLY vorabcheck_bewerber ALTER COLUMN anmerk SET DATA TYPE VARCHAR(500);


-- [Version 149 struct]
DROP TABLE IF EXISTS abs;
DROP TABLE IF EXISTS stg;

-- [Version 150 struct]
ALTER TABLE ONLY vorabcheck_bewerber ALTER COLUMN anmerk SET DATA TYPE VARCHAR(500);

-- [Version 151 struct]
INSERT INTO fame_input_type (name) VALUES ('FILE_NAME');
UPDATE fame_input_type SET uniq_name = name WHERE uniq_name IS NULL;

-- [Version 152 struct]
ALTER TABLE global_jobs ADD COLUMN log text;
ALTER TABLE global_jobs ADD COLUMN class_name varchar(255);
ALTER TABLE global_jobs ALTER COLUMN start DROP NOT NULL;
DELETE FROM global_jobs;
ALTER TABLE global_jobs ADD CONSTRAINT name_unique UNIQUE (name);

-- [Version 155 struct]
ALTER TABLE weiche_abschluss DROP COLUMN his_degree_id;

-- [Version 156 struct]
INSERT INTO weiche_abschluss (de, en) VALUES ('Orientierungsstudium', 'Orientation Program');

-- [Version 157 struct]
ALTER TABLE psv_student RENAME COLUMN id TO mtknr;
ALTER TABLE psv_student RENAME COLUMN hisid TO person_id;
ALTER TABLE public.psv_student ADD COLUMN student_id bigint;
ALTER TABLE public.psv_student ADD COLUMN id bigserial;


-- [Version 158 struct]
ALTER TABLE fame_status DROP CONSTRAINT IF EXISTS fame_status_workflow_id_fkey1;
ALTER TABLE vorabcheck_ects ALTER COLUMN nachweis SET DATA TYPE VARCHAR(100);


-- [Version 159 struct]
ALTER TABLE mobon_hio_country_mapping RENAME COLUMN mobon_name TO his_iso;
ALTER TABLE mobon_hio_country_mapping RENAME COLUMN hio_country_id TO mobon_id;
CREATE SEQUENCE seq_mobon_hio_country_mapping_id;
ALTER TABLE mobon_hio_country_mapping ALTER COLUMN id SET DEFAULT nextval('seq_mobon_hio_country_mapping_id');



-- [Version 130 data]
INSERT INTO mobon_hio_country_mapping (mobon_id,his_iso) VALUES
(17503,'AF'),
(17500,'EG'),
(17607,'AL'),
(17505,'DZ'),
(17507,'AD'),
(17509,'AO'),
(17666,'GQ'),
(17511,'AR'),
(17514,'AM'),
(17613,'AW'),
(17518,'AZ'),
(17502,'ET'),
(17604,'AU'),
(17614,'BS'),
(17615,'BH'),
(17520,'BD'),
(17616,'BB'),
(17480,'BE'),
(17522,'BZ'),
(17523,'BJ'),
(17646,'AI'),
(17617,'BT'),
(17525,'BO'),
(17608,'BA'),
(17529,'BW'),
(17531,'BR'),
(17674,'BN'),
(17482,'BG'),
(17618,'BF'),
(17619,'BI'),
(17533,'CL'),
(17612,'CN'),
(17536,'CR'),
(17486,'DK'),
(17675,'CD'),
(17485,'DE'),
(17540,'DO'),
(17676,'DM'),
(17541,'DJ'),
(17542,'EC'),
(17662,'SV'),
(17543,'CI'),
(17677,'ER'),
(17487,'EE'),
(17649,'FO'),
(17648,'FJ'),
(17489,'FI'),
(17490,'FR'),
(17650,'GA'),
(17544,'GM'),
(17545,'GE'),
(17546,'GH'),
(17652,'GD'),
(17491,'GR'),
(17653,'GL'),
(17654,'GP'),
(17655,'GU'),
(17547,'GT'),
(17548,'GN'),
(17667,'GW'),
(17549,'GY'),
(17550,'HT'),
(17656,'HN'),
(17551,'IN'),
(17552,'ID'),
(17553,'IQ'),
(17554,'IR'),
(17586,'IE'),
(17587,'IS'),
(17555,'IL'),
(17588,'IT'),
(17556,'JM'),
(17606,'JP'),
(17664,'YE'),
(17557,'JO'),
(17558,'KH'),
(17559,'CM'),
(17560,'CA'),
(17647,'CV'),
(17561,'KZ'),
(17562,'QA'),
(17563,'KE'),
(17584,'KG'),
(17668,'KI'),
(17609,'CO'),
(17657,'KM'),
(17564,'CG'),
(17583,'XK'),
(17605,'HR'),
(17565,'CU'),
(17566,'KW'),
(17669,'LA'),
(17661,'LS'),
(17592,'LV'),
(17659,'LB'),
(17660,'LR'),
(17670,'LY'),
(17589,'LI'),
(17590,'LT'),
(17591,'LU'),
(17621,'MO'),
(17567,'MG'),
(17628,'MW'),
(17568,'MY'),
(17626,'MV'),
(17569,'ML'),
(17593,'MT'),
(17570,'MA'),
(17624,'MR'),
(17623,'MU'),
(17497,'MX'),
(17572,'FM'),
(17625,'MD'),
(17573,'MC'),
(17574,'MN'),
(17622,'CS'),
(17627,'AI'),
(17629,'MZ'),
(17576,'MM'),
(17575,'NA'),
(17671,'NR'),
(17577,'NP'),
(17498,'NZ'),
(17578,'NI'),
(17594,'NL'),
(17580,'NE'),
(17579,'NG'),
(17620,'KP'),
(17595,'NO'),
(17581,'OM'),
(17479,'AT'),
(17673,'TL'),
(17539,'PK'),
(17632,'PS'),
(17633,'PW'),
(17538,'PA'),
(17630,'PG'),
(17537,'PY'),
(17535,'PE'),
(17534,'PH'),
(17596,'PL'),
(17597,'PT'),
(17631,'PR'),
(17571,'MK'),
(17634,'RW'),
(17598,'RO'),
(17494,'RU'),
(17665,'ZM'),
(17641,'WS'),
(17637,'SM'),
(17672,'ST'),
(17532,'SA'),
(17599,'SE'),
(17481,'CH'),
(17638,'SN'),
(17610,'RS'),
(17635,'SC'),
(17636,'SL'),
(17530,'ZW'),
(17528,'SG'),
(17600,'SK'),
(17582,'SI'),
(17527,'SO'),
(17488,'ES'),
(17526,'LK'),
(17495,'ZA'),
(17524,'SD'),
(17493,'KR'),
(17639,'SR'),
(17640,'SZ'),
(17521,'SY'),
(17643,'TJ'),
(17519,'TW'),
(17517,'TZ'),
(17492,'TH'),
(17516,'TG'),
(17645,'TO'),
(17644,'TT'),
(17663,'TD'),
(17484,'CZ'),
(17515,'TN'),
(17601,'TR'),
(17642,'TM'),
(17513,'UG'),
(17602,'UA'),
(17585,'HU'),
(17512,'UY'),
(17496,'US'),
(17510,'ZU'),
(17508,'VE'),
(17506,'AE'),
(17611,'GB'),
(17504,'VN'),
(17501,'BY'),
(17499,'CF'),
(17483,'CY');

-- [Version 160 struct]
CREATE TABLE unihh_country_iso (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    unihh_iso_code1 VARCHAR(255),
    unihh_iso_code2 VARCHAR(255),
    tuhh_iso_code VARCHAR(255),
    country_name VARCHAR(255)
);

-- [Version 131 data]
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('A','AT','AT','Österreich');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ABJ','AI','ZZZ','Anguilla,Bermuda,Falkl.,Montserrat,Jungfernins');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ADN','YE','YE','Jemen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('AFG','AF','AF','Afghanistan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('AL','AL','AL','Albanien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('AND','AD','AD','Andorra');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ANG','AO','AO','Angola');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ARM','AM','AM','Armenien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ATG','AG','AG','Antigua und Barbuda');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('AUS','AU','AU','Australien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('AZ','AZ','AZ','Aserbaidschan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('B','BE','BE','Belgien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BD','BD','BD','Bangladesch');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BDS','BB','BB','Barbados');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BF','BF','BF','Burkina Faso');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BG','BG','BG','Bulgarien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BH','BZ','BZ','Belize');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BIH','BA','BA','Bosnien und Herzegowina');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BOL','BO','BO','Bolivien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BR','BR','BR','Brasilien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BRN','BH','BH','Bahrain');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BRU','BN','BN','Brunei Darussalam');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BS','BS','BS','Bahamas');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BTN','BT','BT','Bhutan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BU','BI','BI','Burundi');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BY','BY','BY','Weissrussland (Belarus)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('C','CU','CU','Kuba');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CD','CD','CG','Kongo (Demokratische Republik)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CDN','CA','CA','Kanada');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CH','CH','CH','Schweiz');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CI','CI','CI','Elfenbeinküste');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CL','LK','LK','Sri Lanka(Ceylon)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CO','CO','CO','Kolumbien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('COM','KM','KM','Komoren');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('COK','CK','CK','Cookinseln');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CPV','CV','CV','Kap Verde');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CR','CR','CR','Costa Rica');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CY','CY','CY','Zypern');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CZ','CZ','CZ','Tschechische Republik');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GUB','GW','GW','Guinea-Bissau');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('DJI','DJ','DJ','Dschibuti');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('DK','DK','DK','Dänemark');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('DOM','DO','DO','Dominikanische Republik');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('DY','BJ','BJ','Benin (Dahome)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('DZ','DZ','DZ','Algerien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('E','ES','ES','Spanien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('EAK','KE','KE','Kenia');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('EAS','SD','SD','Sudan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('EAT','TZ','TZ','Tansania');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('EAU','UG','UG','Uganda');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('EC','EC','EC','Ecuador');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ER','ER','ER','Eritrea');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ES','SV','SV','El Salvador');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('EST','EE','EE','Estland');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ET','EG','EG','Ägypten');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ETH','ET','ET','Äthiopien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('F','FR','FR','Frankreich');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('FIN','FI','FI','Finnland');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('FJI','FJ','FJ','Fidschi-Inseln');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('FL','LI','LI','Liechtenstein');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GAB','GA','GA','Gabun');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GB','GB','GB','Vereinigtes Königreich');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GBZ','GI','GI','Gibraltar (Britisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GCA','GT','GT','Guatemala');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GE','GE','GE','Georgien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GH','GH','GH','Ghana');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GNQ','GQ','GQ','Äquatorialguinea');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GR','GR','GR','Griechenland');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('D','DE','DE','Deutschland');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GUI','GN','GN','Guinea');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GUY','GY','GY','Guayana');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('H','HU','HU','Ungarn');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('HN','HN','HN','Honduras');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('HR','HR','HR','Kroatien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('I','IT','IT','Italien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('IL','IL','IL','Israel');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('IND','IN','IN','Indien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('IR','IR','IR','Iran');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('IRL','IE','IE','Irland');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('IRQ','IQ','IQ','Irak');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('IS','IS','IS','Island');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('J','JP','JP','Japan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('JA','JM','JM','Jamaika');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('JOR','JO','JO','Jordanien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('K','KH','KH','Kambodscha');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('KIR','KI','KI','Kiribati');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('KS','KG','KG','Kirgisistan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('KWT','KW','KW','Kuwait');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('KZ','KZ','KZ','Kasachstan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('L','LU','LU','Luxemburg');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('LAO','LA','LA','Laotische Demokratische Volksrepublik');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('LAR','LY','LY','Libyen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('LB','LR','LR','Liberia');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('LS','LS','LS','Lesotho');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('LT','LT','LT','Litauen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('LV','LV','LV','Lettland');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('M','MT','MT','Malta');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MA','MA','MA','Marokko');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MAL','MY','MY','Malaysia');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MC','MC','MC','Monaco');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MD','MD','MD','Moldawien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MDV','MV','MV','Malediven');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MEX','MX','MX','Mexiko');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MGL','MN','MN','Mongolei');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MIS','MH','MH','Marshall-Inseln');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MK','MK','MK','Mazedonien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MNS','FM','FM','Mikronesien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MOC','MZ','MZ','Mosambik');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MS','MU','MU','Mauritius');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MW','MW','MW','Malawi');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MYA','MM','MM','Birma/Myanmar');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('N','NO','NO','Norwegen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NA','AN','ZZZ','Niederländische Antillen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NAM','NA','NA','Namibia');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NEP','NP','NP','Nepal');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NIC','NI','NI','Nicaragua');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NIU','NU','NU','Niue');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NL','NL','NL','Niederlande');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NZ','NZ','NZ','Neuseeland');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('OA','','OA','Ohne Angabe');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('OM','OM','OM','Oman');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('P','PT','PT','Portugal');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PA','PA','PA','Panama');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PE','PE','PE','Peru');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PIT','PN','ZZZ','Pitcairn-Inseln');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PK','PK','PK','Pakistan (Islamische Republik)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PL','PL','PL','Polen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PLA','PW','PW','Palau');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PNG','PG','PG','Papua-Neuguinea');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PRK','KP','KP','Korea, Nord, Demokr. VR');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PY','PY','PY','Paraguay');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('Q','QA','QA','Katar');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RA','AR','AR','Argentinien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RB','BW','BW','Botswana');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RC','TW','TW','Taiwan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RCA','CF','CF','Zentralafrik. Republik');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RCB','CG','CD','Kongo, Volksrepublik');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RCH','CL','CL','Chile');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RFC','CM','CM','Kamerun');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RH','HT','HT','Haiti');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RI','ID','ID','Indonesien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RIM','MR','MR','Mauretanien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RL','LB','LB','Libanon');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RM','MG','MG','Madagaskar');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RMM','ML','ML','Mali');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RN','NE','NE','Niger');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RO','RO','RO','Rumänien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ROK','KR','KR','Korea (Republik)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ROU','UY','UY','Uruguay');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RP','PH','PH','Philippinen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RSM','SM','SM','San Marino');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RT','TG','TG','Togo');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RUS','RU','RU','Russische Föderation');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RWA','RW','RW','Ruanda');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('S','SE','SE','Schweden');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SA','SA','SA','Saudi-Arabien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SCG','CS','RS','Serbien und Montenegro');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SD','SZ','SZ','Swasiland');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SGP','SG','SG','Singapur');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SK','SK','SK','Slowakei');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SLB','SB','SB','Salomonen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SLO','SI','SI','Slowenien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SME','SR','SR','Surinam');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SN','SN','SN','Senegal');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SP','SO','SO','Somalia');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SHN','SH','ZZZ','St.Helena einschl.Ascension');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('THA','TH','TH','Thailand');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TIM','TL','TL','Timor-Leste');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TM','TM','TM','Turkmenistan/Turkmenien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TN','TN','TN','Tunesien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TON','TO','TO','Tonga');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TR','TR','TR','Türkei');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TT','TT','TT','Trinidad und Tobago');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('KNA','KN','KN','St.Kitts und Nevis');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('STL','','XA','staatenlos');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('STP','ST','ST','Sao Tome und Principe');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SY','SC','SC','Seychellen');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SYR','SY','SY','Arabische Republik Syrien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TAD','TJ','TJ','Tadschikistan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TCH','TD','TD','Tschad');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TUV','TV','TV','Tuvalu');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('UA','UA','UA','Ukraine');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('UAE','AE','AE','Vereinigte Arab. Emirate');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('UGK','','ZZZ','ungeklärt');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('USA','US','US','Vereinigte Staaten von Amerika');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('UZ','ZU','UZ','Usbekistan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('V','VA','VA','Vatikanstadt');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('VAN','VU','VU','Vanuatu');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('VN','VN','VN','Vietnam');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('VRC','CN','CN','China');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WAG','GM','GM','Gambia');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WAI','NR','NR','Nauru');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WAL','SL','SL','Sierra Leone');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WAN','NG','NG','Nigeria');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WD','DM','DM','Dominica');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WES','WS','WS','Samoa');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WG','GD','GD','Grenada');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WL','','LC','Windward Ins. St. Lucia');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WS','','ZZZ','übriges Ozeanien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WV','GD','VC','Grenadinen (St.Vinvent u.d.Grenadinen)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('YV','VE','VE','Venezuela');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('Z','ZM','ZM','Sambia');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ZA','ZA','ZA','Südafrika');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ZW','ZW','ZW','Simbabwe');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SRB','RS','RS','Serbien');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MNE','CG','ME','Montenegro');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('RKS','XK','XK','Kosovo');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SJM','SJ','SJ','Svalbard und Jan Mayen (u. a. Bäreninsel, Spitzbergen)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('FRO','FO','FO','Färöer (Dänisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('EA','EA','EA','Spanische Hoheitsplätze in Nordafrika');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MYT','YT','YT','Mayotte (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('VIR','VI','VI','Amerikanische Jungferninseln (US-Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ARU','AW','AW','Aruba (Niederländisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GRL','GL','GL','Grönland (Dänisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GUF','GU','ZZZ','Französisch-Guayana (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('TKL','TK','TK','Tokelau (Neuseeländisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('HMD','HM','HM','Heard und McDonaldinseln (Australisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ATF','TF','TF','Französische Süd- und Antarktisgebiete');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SSD','SS','SS','Südsudan');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PAL','PA','PS','Palästinensische Gebiete');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('HKG','HK','HK','Hongkong');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MAC','MO','ZZZ','Macau');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GGY','GG','GG','Guernsey (Britisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('JEY','JE','JE','Jersey (Britisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('IMN','IM','IM','Insel Man (Britisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('REU','RE','RE','Réunion (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PRI','PR','PR','Puerto Rico (US-Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CUW','CW','CW','Curaçao (Niederländisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SXM','SX','SX','St. Martin (niederländischer Teil)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BES','BQ','BQ','Bonaire, Saba, _x001F_St. Eustatius');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GLP','GP','GP','Guadeloupe (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MTQ','MQ','MQ','Martinique (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BLM','BL','BL','St. Barthélemy (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MAF','MF','MF','St. Martin (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('SPM','PM','PM','St. Pierre und Miquelon (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CPT','CP','CP','Clipperton (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('MNP','MP','MP','Nördliche Marianen (US-Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('ASM','AS','AS','Amerikanisch-Samoa (US-Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('GUM','GU','GU','Guam (US-Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('BVT','BV','BV','Bouvetinsel');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CCK','CC','CC','Kokosinseln (Australisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NFK','NF','NF','Norfolkinsel (Australisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('CXR','CX','CX','Weihnachtsinsel (Australisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('PYF','PF','PF','Französisch-Polynesien (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WLF','WF','WF','Wallis und Futuna (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('NCL','NC','ZZZ','Neukaledonien (Französisches Überseegebiet)');
insert into unihh_country_iso(unihh_iso_code1,unihh_iso_code2,tuhh_iso_code,country_name) values('WL','WL','LC','Lucia, St.');


-- [Version 132 data]
DELETE FROM precheck_status WHERE name = 'GELÖSCHT';

-- [Version 161 struct]  
ALTER TABLE vorabcheck_fach ALTER COLUMN name SET DATA TYPE VARCHAR(500);
ALTER TABLE vorabcheck_fach_archiv ALTER COLUMN name SET DATA TYPE VARCHAR(500);

-- [Version 133 data]
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.psv.gwl.service.fileOutput.remotePath', '/disk1/gebuehrendaten/');

-- [Version 162 struct]
CREATE TABLE gew_processed_file (
    id BIGSERIAL,
    in_name VARCHAR(255),
    out_name VARCHAR(255),
	hash VARCHAR(255),
    verarbeitet DATE
);
CREATE UNIQUE INDEX  gew_processed_file_in_name ON gew_processed_file(in_name);

CREATE TABLE gew_history (
    id BIGSERIAL,
    filename VARCHAR(255),
    username VARCHAR(255),
    zeit TIMESTAMP
);
CREATE INDEX gew_history_filename ON gew_history(filename);

ALTER TABLE ONLY gew_history
    ADD CONSTRAINT gew_history_pk PRIMARY KEY (id);
UPDATE gew_processed_file SET id = tmp.id
	FROM (SELECT in_name, ROW_NUMBER() OVER (ORDER BY in_name) AS id FROM gew_processed_file) AS tmp
	WHERE gew_processed_file.in_name = tmp.in_name;
CREATE SEQUENCE gew_processed_file_seq MINVALUE 1000;
ALTER TABLE gew_processed_file ALTER id SET DEFAULT nextval('gew_processed_file_seq');
ALTER SEQUENCE gew_processed_file_seq OWNED BY gew_processed_file.id;
ALTER TABLE ONLY gew_processed_file
    ADD CONSTRAINT gew_processed_file_pk PRIMARY KEY (id);

-- [Version 134 data]
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.psv.gwl.service.fileOutput.remoteHost', 'tune-tst-app03.rz.tu-harburg.de');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.psv.gwl.service.fileOutput.port', '25');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.psv.gwl.service.fileOutput.user', 'tomcat8');

-- [Version 135 data try]
ALTER TABLE valueservice_countries ALTER COLUMN lid SET DATA TYPE BIGINT;

-- [Version 136 data]
DELETE FROM global_config WHERE "key" = 'de.tuhh.tusp.app.precheck.ip_access_filter';
DELETE FROM global_config WHERE "key" = 'de.tuhh.tusp.app.precheck.disableAllFrom';
DELETE FROM global_config WHERE "key" = 'de.tuhh.tusp.app.precheck.allowAllUntil';
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.app.precheck.ip_access_filter', '127.0.0.1|134.28.200.*');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.app.precheck.disableAllFrom', '2020-02-28 07:00:00');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.app.precheck.allowAllUntil', '2021-03-01 00:00:00');

-- [Version 163 struct]
ALTER TABLE valueservice_academicdegree ADD COLUMN sortorder character varying(255);

-- [Version 164 struct]
CREATE TABLE tim_sonderlieferung (
    id BIGSERIAL NOT NULL,
    mtknr BIGINT,
    startDate DATE,
	endDate DATE
);

-- [Version 165 struct]

ALTER TABLE tim_sonderlieferung
    ADD CONSTRAINT tim_sonder_pk PRIMARY KEY (id);
CREATE SEQUENCE tim_sonder_id_seq MINVALUE 1;
ALTER TABLE tim_sonderlieferung ALTER id SET DEFAULT nextval('tim_sonder_id_seq');

-- [Version 166 struct]
CREATE TABLE termine (
    lv_nr_s BIGSERIAL NOT NULL,
    gbd_s VARCHAR(255),
    raum_s VARCHAR(255),
    dbeg DATE,
    dend DATE,
    beginn TIMESTAMP,
    ende TIMESTAMP 
);
CREATE TABLE lvp (
  lv_nr_s BIGSERIAL NOT NULL,
  titel VARCHAR(255),
  nutzer VARCHAR(255),
  lv_typ VARCHAR(255),
  bereich VARCHAR(255)
);

-- [Version 167 struct]
CREATE TABLE pvsb_antrag (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    timestamp_confirm timestamp NOT NULL default current_timestamp,
    complete boolean NOT NULL default false,
    examiner_name VARCHAR(255),
    examiner_email VARCHAR(255),
    examiner_account VARCHAR(255),
    rep_name VARCHAR(255),
    rep_account VARCHAR(255)
);


-- [Version 168 struct]
ALTER TABLE pvsb_antrag DROP COLUMN rep_name; 
ALTER TABLE pvsb_antrag ADD COLUMN rep_surname VARCHAR(255);
ALTER TABLE pvsb_antrag ADD COLUMN rep_firstname VARCHAR(255);
ALTER TABLE pvsb_antrag ADD COLUMN rep_email VARCHAR(255);
ALTER TABLE pvsb_antrag ADD COLUMN rep_dateOfBirth VARCHAR(10);
ALTER TABLE pvsb_antrag ADD COLUMN institute VARCHAR(255);
ALTER TABLE pvsb_antrag ADD COLUMN applicant VARCHAR(255) NOT NULL;
ALTER TABLE pvsb_antrag ADD COLUMN timestamp_process timestamp default current_timestamp;
ALTER TABLE pvsb_antrag ADD COLUMN processor VARCHAR(255);
ALTER TABLE pvsb_antrag ADD COLUMN mailsent boolean default false;



-- [Version 169 struct]
drop table weiche_studiengang;
drop table weiche_abschluss;

--[Version 137 data]
update precheck_note set note=2.5 where countries = (select id from valueservice_countries where iso3166_1_alpha ='GB');

-- [Version 170 struct]
CREATE TABLE Receiver (
  id BIGSERIAL NOT NULL,
  vorname VARCHAR(255),
  nachname VARCHAR(255),
  titel VARCHAR(255),
  anrede VARCHAR(255),
  verteilerName VARCHAR(255)
);
CREATE TABLE MailProtokoll (
  id BIGSERIAL NOT NULL,
  receiver VARCHAR(255),
  sender VARCHAR(255),
  timestamp_confirm timestamp NOT NULL,
  subject VARCHAR(255),
  body VARCHAR(255),
  username VARCHAR(255),
  status VARCHAR(255),
  replayTo VARCHAR(255)
  
);
-- [Version 171 struct]
ALTER TABLE Receiver ADD COLUMN email VARCHAR(255);


-- [Version 138 data]
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test1','test1','dr.','herr','jakus@tuhh.de','dozent');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test6','test6','dr.','herr','jakus@tuhh.de','dozent');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test7','test7','dr.','herr','jakus@tuhh.de','dozent');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test2','test2','dr.','herr','jakus@tuhh.de','pruefvertreter');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test8','test8','dr.','herr','jakus@tuhh.de','pruefvertreter');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test11','test11','dr.','herr','jakus@tuhh.de','lehrbeauftragte');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test9','test9','dr.','herr','jakus@tuhh.de','lehrbeauftragte');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test3','test3','dr.','herr','jakus@tuhh.de','lehrbeauftragte');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test4','test4','dr.','herr','jakus@tuhh.de','lehrperson');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test10','test10','dr.','herr','jakus@tuhh.de','lehrperson');
INSERT INTO Receiver ("vorname", "nachname", "titel", "anrede", "email","verteilername") VALUES ('test5','test5','dr.','herr','jakus@tuhh.de','mich');


-- [Version 172 struct]
ALTER TABLE MailProtokoll DROP COLUMN timestamp_confirm;
ALTER TABLE MailProtokoll ADD COLUMN timestamp VARCHAR(255);

---- [Version 173 struct]
ALTER TABLE MailProtokoll DROP COLUMN body;
ALTER TABLE MailProtokoll ADD COLUMN body TEXT;

---- [Version 174 struct]
ALTER TABLE Receiver ADD COLUMN personId BIGINT;
ALTER TABLE Receiver ADD COLUMN semester BIGINT;

-- [Version 139 data]
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.rztjj', 'rztjj');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.rztjj.email', 'jakus@tuhh.de');
-- [Version 140 data]
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.anonymousUser', 'anonymousUser');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.anonymousUser.email', 'jakus@tuhh.de');
-- [Version 141 data]
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.rztjm', 'rztjm');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.rztjm.email', 'j.martens@tuhh.de');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.rztmat', 'rztmat');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.rztmat.email', 'm.al@tuhh.de');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.psvdd', 'psvdd');
INSERT INTO global_config ("key", "value") VALUES ('de.tuhh.tusp.exa.massmails.psvdd.email', 'dunst@tuhh.de');

---- [Version 175 struct]
ALTER TABLE MailProtokoll DROP COLUMN replayto;
ALTER TABLE MailProtokoll ADD COLUMN replyto VARCHAR(255);

-- [Version 176 struct]
CREATE TABLE lvsb_antrag (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    timestamp_confirm timestamp NOT NULL default current_timestamp,
    complete boolean NOT NULL default false,
    teacher_name VARCHAR(255),
    teacher_email VARCHAR(255),
    teacher_account VARCHAR(255),
    rep_surname VARCHAR(255),
    rep_firstname VARCHAR(255),
    rep_account VARCHAR(255),
    rep_email VARCHAR(255),
	rep_dateOfBirth VARCHAR(10),
	institute VARCHAR(255),
	applicant VARCHAR(255) NOT NULL,
	timestamp_process timestamp default current_timestamp,
	processor VARCHAR(255),
	mailsent boolean default false
);
-- [Version 142 data]
insert into global_config("key","value") values ('de.tuhh.tusp.webservice.studystatusFilterExmatrikulation', '5');

---- [Version 177 struct]
ALTER TABLE lvsb_antrag ADD COLUMN employee boolean default false;
ALTER TABLE lvsb_antrag ADD COLUMN reason VARCHAR(500);




-- [Version 178 struct]
CREATE TABLE stg_processed_file (
    id BIGSERIAL,
    in_name VARCHAR(255),
    out_name VARCHAR(255),
	hash VARCHAR(255),
    verarbeitet DATE
);
CREATE UNIQUE INDEX  stg_processed_file_in_name ON stg_processed_file(in_name);

CREATE TABLE stg_history (
    id BIGSERIAL,
    filename VARCHAR(255),
    username VARCHAR(255),
    zeit TIMESTAMP
);
CREATE INDEX stg_history_filename ON stg_history(filename);

ALTER TABLE ONLY stg_history
    ADD CONSTRAINT stg_history_pk PRIMARY KEY (id);
UPDATE stg_processed_file SET id = tmp.id
	FROM (SELECT in_name, ROW_NUMBER() OVER (ORDER BY in_name) AS id FROM stg_processed_file) AS tmp
	WHERE stg_processed_file.in_name = tmp.in_name;
CREATE SEQUENCE stg_processed_file_seq MINVALUE 1000;
ALTER TABLE stg_processed_file ALTER id SET DEFAULT nextval('gew_processed_file_seq');
ALTER SEQUENCE stg_processed_file_seq OWNED BY stg_processed_file.id;
ALTER TABLE ONLY stg_processed_file
    ADD CONSTRAINT stg_processed_file_pk PRIMARY KEY (id);

---- [Version 179 struct]
CREATE TABLE avl_auto (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	standort varchar(255),
	sitzplaetze varchar(255),
	ladevolumen varchar(255),
	ladeflaeche varchar(255),
	lade_kante varchar(255),
	anhaengerkopplung varchar(255),
	reichweite varchar(255),
	diesel_o_elektro varchar(255),
	diesen_tankkarte_reserviert varchar(255),
	m_spanngurt varchar(255),
	hoehe varchar(255),
	breite varchar(255),
	laenge varchar(255)
);

CREATE TABLE avl_buchung (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	von timestamp,
	bis timestamp,
	avl_user_id BIGSERIAL
);

CREATE TABLE avl_user (
	id BIGSERIAL PRIMARY KEY NOT NULL,
	vorname varchar(255),
	nachname varchar(255),
	tel varchar(255),
	e_mail varchar(255),
	institut varchar(255),
	fuererschein varchar(255),
	gueltigkeit varchar(255)
);

---- [Version 180 struct]
ALTER TABLE avl_buchung DROP COLUMN von;
ALTER TABLE avl_buchung ADD COLUMN von VARCHAR(255);
ALTER TABLE avl_buchung DROP COLUMN bis;
ALTER TABLE avl_buchung ADD COLUMN bis VARCHAR(255);

---- [Version 181 struct]
AlTER TABLE avl_buchung ADD COLUMN auto_id BIGSERIAL;


---- [Version 182 struct]
CREATE TABLE saeule(
id BIGSERIAL PRIMARY KEY NOT NULL,
hisone_superx_version varchar,
hisone_datum varchar,
hisone_hotfix_nr varchar,
hisone_hotfix_datum varchar,
hiodb_quelle varchar,
hiodb_datum varchar,
sospos_version varchar,
sospos_datum varchar
);

CREATE TABLE saeule_user(
id BIGSERIAL PRIMARY KEY NOT NULL,
name varchar,
nachname varchar,
benutzername varchar,
vorgesetzter boolean,
abstimmberechtigt boolean,
saeule_id INTEGER,
CONSTRAINT saeule_user_fk
FOREIGN KEY(saeule_id)
REFERENCES saeule(id)
);

CREATE TABLE konfigurationsquelle(
id BIGSERIAL PRIMARY KEY NOT NULL,
bezeichnung varchar
);

CREATE TABLE konfigurationstransfer(
id BIGSERIAL PRIMARY KEY NOT NULL,
saeule_id INTEGER,
saeule_user_id INTEGER,
begruendung varchar,
datum varchar,
konfigurationsquelle_id INTEGER,
CONSTRAINT konfigurationstransfer_fk
FOREIGN KEY(saeule_id)
REFERENCES saeule(id),
CONSTRAINT konfigurationstransfer_fk1
FOREIGN KEY(saeule_user_id)
REFERENCES saeule_user(id),
CONSTRAINT konfigurationstransfer_fk2
FOREIGN KEY(konfigurationsquelle_id)
REFERENCES konfigurationsquelle(id)
);

CREATE TABLE konfigurationseigenschaft(
id BIGSERIAL PRIMARY KEY NOT NULL,
konfigurationstransfer_id INTEGER,
bezeichnung varchar,
angekreuzt boolean,
CONSTRAINT konfigurationseigenschaft_fk
FOREIGN KEY(konfigurationstransfer_id)
REFERENCES konfigurationstransfer(id)
);

CREATE TABLE saeuleaktualisierung(
id BIGSERIAL PRIMARY KEY NOT NULL,
datum varchar,
saeule_user_id INTEGER,
saeulenquelle_id INTEGER,
begruendung varchar,
CONSTRAINT saeuleaktualisierung_fk
FOREIGN KEY(saeule_user_id)
REFERENCES saeule_user(id),
CONSTRAINT saeuleaktualisierung_fk2
FOREIGN KEY(saeulenquelle_id)
REFERENCES saeule(id)
);

CREATE TABLE zustimmungen(
id BIGSERIAL PRIMARY KEY NOT NULL,
saeulenaktualisierungs_id INTEGER,
saeule_user_id INTEGER,
zugestimmt boolean,
CONSTRAINT zustimmungen_fk
FOREIGN KEY(saeulenaktualisierungs_id)
REFERENCES saeuleaktualisierung(id),
CONSTRAINT zustimmungen_fk1
FOREIGN KEY(saeule_user_id)
REFERENCES saeule_user(id)
);

CREATE TABLE saeulenreservierung(
id BIGSERIAL PRIMARY KEY NOT NULL,
saeule_user_id INTEGER,
von varchar,
bis varchar,
begruendung varchar,
saeule_id INTEGER,
CONSTRAINT saeulenreservierung_fk
FOREIGN KEY(saeule_user_id)
REFERENCES saeule_user(id),
CONSTRAINT saeulenreservierung_fk1
FOREIGN KEY(saeule_id)
REFERENCES saeule(id)
);

-- [Version 143 data]
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-SYS');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-BI');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-DOC');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-APP');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-STU');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-EXA');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-prod');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-tst');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-dev');
INSERT INTO konfigurationsquelle(bezeichnung) values ('TUHH-psv');

---- [Version 183 struct]
AlTER TABLE saeule ADD COLUMN bezeichnung varchar;

-- [Version 144 data]
INSERT INTO saeule(bezeichnung, hisone_superx_version, hisone_datum, hisone_hotfix_nr, hisone_hotfix_datum, hiodb_quelle, hiodb_datum, sospos_version, sospos_datum) values ('PROD SÄULE','1.0','01/12/21','#1','01/01/21','TUHH-psv','01/02/21','1.1.1','01/03/21');
INSERT INTO saeule(bezeichnung, hisone_superx_version, hisone_datum, hisone_hotfix_nr, hisone_hotfix_datum, hiodb_quelle, hiodb_datum, sospos_version, sospos_datum) values ('TEST1 SÄULE','1.1','02/12/21','#2','02/01/21','TUHH-psv','02/02/21','1.1.2','02/03/21');
INSERT INTO saeule(bezeichnung, hisone_superx_version, hisone_datum, hisone_hotfix_nr, hisone_hotfix_datum, hiodb_quelle, hiodb_datum, sospos_version, sospos_datum) values ('TEST2 SÄULE','1.2','03/12/21','#3','03/01/21','TUHH-psv','03/02/21','1.1.3','03/03/21');
INSERT INTO saeule(bezeichnung, hisone_superx_version, hisone_datum, hisone_hotfix_nr, hisone_hotfix_datum, hiodb_quelle, hiodb_datum, sospos_version, sospos_datum) values ('TEST3 SÄULE','1.3','04/12/21','#4','04/01/21','TUHH-psv','04/02/21','1.1.4','04/03/21');
INSERT INTO saeule(bezeichnung, hisone_superx_version, hisone_datum, hisone_hotfix_nr, hisone_hotfix_datum, hiodb_quelle, hiodb_datum, sospos_version, sospos_datum) values ('DEV1 SÄULE','1.4','05/12/21','#5','05/01/21','TUHH-psv','05/02/21','1.1.5','05/03/21');
INSERT INTO saeule(bezeichnung, hisone_superx_version, hisone_datum, hisone_hotfix_nr, hisone_hotfix_datum, hiodb_quelle, hiodb_datum, sospos_version, sospos_datum) values ('DEV2 SÄULE','1.5','06/12/21','#6','06/01/21','TUHH-psv','06/02/21','1.1.6','06/03/21');
INSERT INTO saeule(bezeichnung, hisone_superx_version, hisone_datum, hisone_hotfix_nr, hisone_hotfix_datum, hiodb_quelle, hiodb_datum, sospos_version, sospos_datum) values ('STANDALONE SÄULE','1.6','07/12/21','#7','07/01/21','TUHH-psv','07/02/21','1.1.7','07/03/21');

-- [Version 145 data]
INSERT INTO saeule_user(name, nachname, benutzername, vorgesetzter, abstimmberechtigt, saeule_id) values ('max1','mustermann1','cas4235', TRUE, TRUE, 1);
INSERT INTO saeule_user(name, nachname, benutzername, vorgesetzter, abstimmberechtigt, saeule_id) values ('max2','mustermann2','cas4235', TRUE, TRUE, 1);
INSERT INTO saeule_user(name, nachname, benutzername, vorgesetzter, abstimmberechtigt, saeule_id) values ('max3','mustermann3','cas4235', TRUE, TRUE, 2);
INSERT INTO saeule_user(name, nachname, benutzername, vorgesetzter, abstimmberechtigt, saeule_id) values ('max4','mustermann4','cas4235', TRUE, TRUE, 1);
INSERT INTO saeule_user(name, nachname, benutzername, vorgesetzter, abstimmberechtigt, saeule_id) values ('max5','mustermann5','cas4235', TRUE, TRUE, 3);
INSERT INTO saeule_user(name, nachname, benutzername, vorgesetzter, abstimmberechtigt, saeule_id) values ('max6','mustermann6','cas4235', TRUE, TRUE, 1);
INSERT INTO saeule_user(name, nachname, benutzername, vorgesetzter, abstimmberechtigt, saeule_id) values ('max7','mustermann7','cas4235', TRUE, TRUE, 2);

-- [Version 146 data]
INSERT INTO konfigurationstransfer(saeule_id, saeule_user_id, begruendung, datum, konfigurationsquelle_id) values (1, 3,'Aktualisierung1', '01/12/21', 1);
INSERT INTO konfigurationstransfer(saeule_id, saeule_user_id, begruendung, datum, konfigurationsquelle_id) values (1, 3,'Aktualisierung2', '02/12/21', 1);
INSERT INTO konfigurationstransfer(saeule_id, saeule_user_id, begruendung, datum, konfigurationsquelle_id) values (1, 5,'Aktualisierung3', '03/12/21', 1);
INSERT INTO konfigurationstransfer(saeule_id, saeule_user_id, begruendung, datum, konfigurationsquelle_id) values (1, 3,'Aktualisierung4', '04/12/21', 1);
INSERT INTO konfigurationstransfer(saeule_id, saeule_user_id, begruendung, datum, konfigurationsquelle_id) values (1, 3,'Aktualisierung5', '05/12/21', 1);
INSERT INTO konfigurationstransfer(saeule_id, saeule_user_id, begruendung, datum, konfigurationsquelle_id) values (1, 4,'Aktualisierung6', '06/12/21', 1);

-- [Version 147 data]
INSERT INTO saeuleaktualisierung(datum, saeule_user_id, saeulenquelle_id , begruendung) values ('01/12/21', 3, 2, 'Aktualisierung1');
INSERT INTO saeuleaktualisierung(datum, saeule_user_id, saeulenquelle_id , begruendung) values ('02/12/21', 3, 2, 'Aktualisierung2');
INSERT INTO saeuleaktualisierung(datum, saeule_user_id, saeulenquelle_id , begruendung) values ('03/12/21', 3, 2, 'Aktualisierung3');
INSERT INTO saeuleaktualisierung(datum, saeule_user_id, saeulenquelle_id , begruendung) values ('04/12/21', 5, 2, 'Aktualisierung4');
INSERT INTO saeuleaktualisierung(datum, saeule_user_id, saeulenquelle_id , begruendung) values ('05/12/21', 6, 2, 'Aktualisierung5');
INSERT INTO saeuleaktualisierung(datum, saeule_user_id, saeulenquelle_id , begruendung) values ('06/12/21', 6, 2, 'Aktualisierung6');
INSERT INTO saeuleaktualisierung(datum, saeule_user_id, saeulenquelle_id , begruendung) values ('01/12/21', 5, 2, 'Aktualisierung1');

-- [Version 148 data]
INSERT INTO zustimmungen(saeulenaktualisierungs_id, saeule_user_id, zugestimmt) values (3, 3, TRUE);
INSERT INTO zustimmungen(saeulenaktualisierungs_id, saeule_user_id, zugestimmt) values (3, 4, TRUE);
INSERT INTO zustimmungen(saeulenaktualisierungs_id, saeule_user_id, zugestimmt) values (3, 5, TRUE);
INSERT INTO zustimmungen(saeulenaktualisierungs_id, saeule_user_id, zugestimmt) values (3, 6, FALSE);

-- [Version 149 data]
INSERT INTO saeulenreservierung(saeule_user_id, von, bis, begruendung, saeule_id) values (3,'01/12/21','01/01/22', 'einfach so', 1);
INSERT INTO saeulenreservierung(saeule_user_id, von, bis, begruendung, saeule_id) values (4,'02/12/21','02/01/22', 'einfach so', 1);
INSERT INTO saeulenreservierung(saeule_user_id, von, bis, begruendung, saeule_id) values (3,'03/12/21','03/01/22', 'einfach so', 3);
INSERT INTO saeulenreservierung(saeule_user_id, von, bis, begruendung, saeule_id) values (4,'04/12/21','04/01/22', 'einfach so', 2);

---- [Version 184 struct]
ALTER TABLE saeule_user ADD COLUMN email VARCHAR(255);
---- [Version 185 struct]
ALTER TABLE saeuleaktualisierung ADD COLUMN uid INTEGER;
---- [Version 186 struct]
ALTER TABLE saeuleaktualisierung DROP COLUMN uid;
ALTER TABLE saeuleaktualisierung ADD COLUMN uid bigint;
---- [Version 187 struct]
ALTER TABLE zustimmungen ADD COLUMN uid bigint;
---- [Version 188 struct]
ALTER TABLE saeuleaktualisierung ADD COLUMN saeule_von_id bigint;

---- [Version 189 struct]
ALTER TABLE saeuleaktualisierung OWNER TO "tuspadmin";
ALTER TABLE saeuleaktualisierung OWNER TO "tusp-user";

ALTER TABLE zustimmungen OWNER TO "tuspadmin";
ALTER TABLE zustimmungen OWNER TO "tusp-user";

---- [Version 190 struct]
ALTER TABLE konfigurationstransfer OWNER TO "tuspadmin";
ALTER TABLE konfigurationstransfer OWNER TO "tusp-user";

ALTER TABLE saeulenreservierung OWNER TO "tuspadmin";
ALTER TABLE saeulenreservierung OWNER TO "tusp-user";

ALTER TABLE saeule_user OWNER TO "tuspadmin";
ALTER TABLE saeule_user OWNER TO "tusp-user";

ALTER TABLE saeule OWNER TO "tuspadmin";
ALTER TABLE saeule OWNER TO "tusp-user";

ALTER TABLE konfigurationseigenschaft OWNER TO "tuspadmin";
ALTER TABLE konfigurationseigenschaft OWNER TO "tusp-user";

ALTER TABLE konfigurationsquelle OWNER TO "tuspadmin";
ALTER TABLE konfigurationsquelle OWNER TO "tusp-user";

---- [Version 191 struct]
ALTER TABLE zustimmungen ADD COLUMN zusgestimmtDatum VARCHAR(255);
ALTER TABLE zustimmungen ADD COLUMN abgelehnt boolean;
ALTER TABLE zustimmungen ADD COLUMN ablehnungsGrund VARCHAR(1000);
ALTER TABLE zustimmungen ADD COLUMN ablehnungsDatum VARCHAR(255);
---- [Version 192 struct]
ALTER TABLE saeuleaktualisierung ADD COLUMN abgeschlossen boolean;

---- [Version 193 struct]
CREATE TABLE mailerrors(
	id BIGSERIAL PRIMARY KEY NOT NULL,
	errorname varchar (255),
	vorname varchar (255),
	nachname varchar (255),
	message varchar (255),
	maildatum varchar (255)
);
---- [Version 194 struct]
ALTER TABLE mailerrors OWNER TO "tuspadmin";
ALTER TABLE mailerrors OWNER TO "tusp-user";
---- [Version 195 struct]
ALTER TABLE mailerrors ADD COLUMN uid varchar(255);

---- [Version 196 struct]
ALTER TABLE konfigurationseigenschaft ADD COLUMN brauchtQuelle boolean;
ALTER TABLE konfigurationseigenschaft DROP COLUMN konfigurationstransfer_id;

-- [Version 150 data]
insert into konfigurationseigenschaft (brauchtQuelle, bezeichnung, angekreuzt ) 
values 
(true, 'Abgabebedingung für Bewerbungsanträge', false),
(true, 'Abonnenten von Systembenachrichtigungen', false),
(true, 'Antragsmanagement-Konfiguration (STU)', false),
(false, 'Auftragskonfiguration', false),
(false, 'Belegungskonfigurationen', false),
(false, 'Berichte des Berichtscenters', false),
(false, 'Datenselektionsdefinitionen', false),
(false, 'Bewerberstapel', false),
(false, 'Bewerbungsbestandteile', false),
(false, 'Bewerberfallgruppen', false),
(false, 'Curriculum Designer-Oberflächenkonfiguration', false),
(false, 'Definition der dynamischen Objekte', false),
(false, 'Eigene Accountgenerator-Muster', false),
(false, 'Einwilligungsinhalte', false),
(false, 'Feste Bewerbungsbestandteile', false),
(false, 'Konfigurationen zu fachlichen Ereignissen', false),
(false, 'Organisationseinheiten', false),
(false, 'Portal-Konfiguration inkl. Portlets und Zuordnung für alle Rollen', false),
(false, 'Portlets aus dem Portal (Bitte nur nach Rücksprache mit der HIS benutzen!)', false),
(false, 'Praxissemester-Konfiguration (STU)', false),
(false, 'Rollen und Rechte', false),
(false, 'Schlüsseltabellen', false),
(false, 'Studiengangsdaten inkl. Hierarchien und Kombinationsstudiengängen', false),
(false, 'Units (Status: In Bearbeitung)', false),
(false, 'Units (Enthält nur die ExternalRelation-Einträge)', false),
(false, 'Vergabeschemata', false),
(false, 'Vorbelegungen von Oberflächenkomponenten (Suchsets, Lesezeichen, Fieldset-Zustände und Tabellen-Anpassungen)', false),
(false, 'Zulassungspakete inkl. zugeordneten Bewerbungsbestandteilen, Vergabeschemata und Statusanmerkungen', false);

---- [Version 197 struct]
DROP table konfigurationstransfer;
CREATE TABLE konfigurationstransfer(
id BIGSERIAL PRIMARY KEY NOT NULL,
saeule_id INTEGER,
saeule_user_id INTEGER,
konfigurationsquelle_id INTEGER,
begruendung varchar,
datum varchar,
uid BIGINT,
abgeschlossen BOOLEAN,
CONSTRAINT konfigurationstransfer_fk
FOREIGN KEY(saeule_id)
REFERENCES saeule(id),
CONSTRAINT konfigurationstransfer_fk1
FOREIGN KEY(saeule_user_id)
REFERENCES saeule_user(id),
CONSTRAINT konfigurationstransfer_fk2
FOREIGN KEY(konfigurationsquelle_id)
REFERENCES konfigurationsquelle(id)
);
---- [Version 198 struct]
 ALTER table zustimmungen ADD COLUMN konfigurationstransfer_id INTEGER;
 
---- [Version 199 struct]

ALTER table konfigurationstransfer ADD COLUMN konfigurationseingeschaften VARCHAR (255);
