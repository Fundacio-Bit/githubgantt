--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2021-03-23 11:06:45




--
-- TOC entry 177 (class 1259 OID 38848)
-- Name: ghg_account_seq; Type: SEQUENCE; Schema: public; Owner: githubgantt
--

CREATE SEQUENCE public.ghg_account_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ghg_account_seq OWNER TO githubgantt;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 38841)
-- Name: ghg_account; Type: TABLE; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE TABLE public.ghg_account (
    accountid bigint DEFAULT nextval('public.ghg_account_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    token character varying(255) NOT NULL
);


ALTER TABLE public.ghg_account OWNER TO githubgantt;

--
-- TOC entry 171 (class 1259 OID 38798)
-- Name: ghg_githubgantt_seq; Type: SEQUENCE; Schema: public; Owner: githubgantt
--

CREATE SEQUENCE public.ghg_githubgantt_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ghg_githubgantt_seq OWNER TO githubgantt;

--
-- TOC entry 172 (class 1259 OID 38800)
-- Name: ghg_fitxer; Type: TABLE; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE TABLE public.ghg_fitxer (
    fitxerid bigint DEFAULT nextval('public.ghg_githubgantt_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.ghg_fitxer OWNER TO githubgantt;

--
-- TOC entry 179 (class 1259 OID 38856)
-- Name: ghg_gantt_seq; Type: SEQUENCE; Schema: public; Owner: githubgantt
--

CREATE SEQUENCE public.ghg_gantt_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ghg_gantt_seq OWNER TO githubgantt;

--
-- TOC entry 178 (class 1259 OID 38853)
-- Name: ghg_gantt; Type: TABLE; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE TABLE public.ghg_gantt (
    ganttid bigint DEFAULT nextval('public.ghg_gantt_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    organization character varying(255) NOT NULL,
    repository character varying(255) NOT NULL,
    project character varying(255) NOT NULL,
    startdate timestamp without time zone NOT NULL,
    numeroprogramadors integer DEFAULT 1 NOT NULL,
    accountid bigint NOT NULL,
    projectnom character varying(255) NOT NULL
);


ALTER TABLE public.ghg_gantt OWNER TO githubgantt;

--
-- TOC entry 173 (class 1259 OID 38808)
-- Name: ghg_idioma; Type: TABLE; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE TABLE public.ghg_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.ghg_idioma OWNER TO githubgantt;

--
-- TOC entry 174 (class 1259 OID 38813)
-- Name: ghg_traduccio; Type: TABLE; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE TABLE public.ghg_traduccio (
    traduccioid bigint DEFAULT nextval('public.ghg_githubgantt_seq'::regclass) NOT NULL
);


ALTER TABLE public.ghg_traduccio OWNER TO githubgantt;

--
-- TOC entry 175 (class 1259 OID 38817)
-- Name: ghg_traducciomap; Type: TABLE; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE TABLE public.ghg_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.ghg_traducciomap OWNER TO githubgantt;

--
-- TOC entry 1873 (class 2606 OID 38852)
-- Name: ghg_account_pk; Type: CONSTRAINT; Schema: public; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY public.ghg_account
    ADD CONSTRAINT ghg_account_pk PRIMARY KEY (accountid);


--
-- TOC entry 1860 (class 2606 OID 38824)
-- Name: ghg_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY public.ghg_fitxer
    ADD CONSTRAINT ghg_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1877 (class 2606 OID 38873)
-- Name: ghg_gantt_pk; Type: CONSTRAINT; Schema: public; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY public.ghg_gantt
    ADD CONSTRAINT ghg_gantt_pk PRIMARY KEY (ganttid);


--
-- TOC entry 1863 (class 2606 OID 38826)
-- Name: ghg_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY public.ghg_idioma
    ADD CONSTRAINT ghg_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1866 (class 2606 OID 38828)
-- Name: ghg_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY public.ghg_traduccio
    ADD CONSTRAINT ghg_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1871 (class 2606 OID 38830)
-- Name: ghg_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY public.ghg_traducciomap
    ADD CONSTRAINT ghg_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1874 (class 1259 OID 38879)
-- Name: ghg_account_pk_i; Type: INDEX; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_account_pk_i ON public.ghg_account USING btree (accountid);


--
-- TOC entry 1861 (class 1259 OID 38831)
-- Name: ghg_fitxer_pk_i; Type: INDEX; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_fitxer_pk_i ON public.ghg_fitxer USING btree (fitxerid);


--
-- TOC entry 1875 (class 1259 OID 38881)
-- Name: ghg_gantt_accountid_fk_i; Type: INDEX; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_gantt_accountid_fk_i ON public.ghg_gantt USING btree (accountid);


--
-- TOC entry 1878 (class 1259 OID 38880)
-- Name: ghg_gantt_pk_i; Type: INDEX; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_gantt_pk_i ON public.ghg_gantt USING btree (ganttid);


--
-- TOC entry 1864 (class 1259 OID 38832)
-- Name: ghg_idioma_pk_i; Type: INDEX; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_idioma_pk_i ON public.ghg_idioma USING btree (idiomaid);


--
-- TOC entry 1867 (class 1259 OID 38833)
-- Name: ghg_traduccio_pk_i; Type: INDEX; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_traduccio_pk_i ON public.ghg_traduccio USING btree (traduccioid);


--
-- TOC entry 1868 (class 1259 OID 38834)
-- Name: ghg_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_traducciomap_idiomaid_fk_i ON public.ghg_traducciomap USING btree (idiomaid);


--
-- TOC entry 1869 (class 1259 OID 38835)
-- Name: ghg_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_traducciomap_pk_i ON public.ghg_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1880 (class 2606 OID 38874)
-- Name: ghg_gantt_account_aco_fk; Type: FK CONSTRAINT; Schema: public; Owner: githubgantt
--

ALTER TABLE ONLY public.ghg_gantt
    ADD CONSTRAINT ghg_gantt_account_aco_fk FOREIGN KEY (accountid) REFERENCES public.ghg_account(accountid);


--
-- TOC entry 1879 (class 2606 OID 38836)
-- Name: ghg_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: githubgantt
--

ALTER TABLE ONLY public.ghg_traducciomap
    ADD CONSTRAINT ghg_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.ghg_traduccio(traduccioid);


--
-- TOC entry 1995 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2021-03-23 11:06:46

--
-- PostgreSQL database dump complete
--

