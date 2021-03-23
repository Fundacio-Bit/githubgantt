--
-- PostgreSQL database dump
--

-- Dumped from database version 8.4.14
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-08-25 12:39:21

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = githubgantt, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 150 (class 1259 OID 69544)
-- Name: ghg_fitxer; Type: TABLE; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE TABLE ghg_fitxer (
    fitxerid bigint DEFAULT nextval('ghg_githubgantt_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(45) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE githubgantt.ghg_fitxer OWNER TO githubgantt;

--
-- TOC entry 169 (class 1259 OID 92635)
-- Name: ghg_idioma; Type: TABLE; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE TABLE ghg_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE githubgantt.ghg_idioma OWNER TO githubgantt;

--
-- TOC entry 184 (class 1259 OID 210385)
-- Name: ghg_traduccio; Type: TABLE; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE TABLE ghg_traduccio (
    traduccioid bigint DEFAULT nextval('ghg_githubgantt_seq'::regclass) NOT NULL
);


ALTER TABLE githubgantt.ghg_traduccio OWNER TO githubgantt;

--
-- TOC entry 183 (class 1259 OID 210326)
-- Name: ghg_traducciomap; Type: TABLE; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE TABLE ghg_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE githubgantt.ghg_traducciomap OWNER TO githubgantt;

--
-- TOC entry 1836 (class 2606 OID 70326)
-- Name: ghg_fitxer_pk; Type: CONSTRAINT; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY ghg_fitxer
    ADD CONSTRAINT ghg_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1839 (class 2606 OID 96099)
-- Name: ghg_idioma_pk; Type: CONSTRAINT; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY ghg_idioma
    ADD CONSTRAINT ghg_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1846 (class 2606 OID 210396)
-- Name: ghg_traduccio_pk; Type: CONSTRAINT; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY ghg_traduccio
    ADD CONSTRAINT ghg_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1842 (class 2606 OID 210501)
-- Name: ghg_traducciomap_pk; Type: CONSTRAINT; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

ALTER TABLE ONLY ghg_traducciomap
    ADD CONSTRAINT ghg_traducciomap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1837 (class 1259 OID 202159)
-- Name: ghg_fitxer_pk_i; Type: INDEX; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_fitxer_pk_i ON ghg_fitxer USING btree (fitxerid);


--
-- TOC entry 1840 (class 1259 OID 202163)
-- Name: ghg_idioma_pk_i; Type: INDEX; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_idioma_pk_i ON ghg_idioma USING btree (idiomaid);


--
-- TOC entry 1847 (class 1259 OID 210461)
-- Name: ghg_traduccio_pk_i; Type: INDEX; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_traduccio_pk_i ON ghg_traduccio USING btree (traduccioid);


--
-- TOC entry 1843 (class 1259 OID 210529)
-- Name: ghg_traducmap_idiomaid_pk_i; Type: INDEX; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_traducmap_idiomaid_pk_i ON ghg_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1844 (class 1259 OID 210528)
-- Name: ghg_traducmap_tradmapid_pk_i; Type: INDEX; Schema: githubgantt; Owner: githubgantt; Tablespace: 
--

CREATE INDEX ghg_traducmap_tradmapid_pk_i ON ghg_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1848 (class 2606 OID 210469)
-- Name: ghg_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: githubgantt; Owner: githubgantt
--

ALTER TABLE ONLY ghg_traducciomap
    ADD CONSTRAINT ghg_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES ghg_traduccio(traduccioid);


-- Completed on 2014-08-25 12:39:21

--
-- PostgreSQL database dump complete
--

