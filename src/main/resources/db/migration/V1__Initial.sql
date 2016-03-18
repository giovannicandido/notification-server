
CREATE TABLE config (
    config character varying(100) NOT NULL
);


--
-- Name: config_values; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE config_values (
    config character varying(100) NOT NULL,
    value character varying(100),
    values_key character varying(100) NOT NULL
);


--
-- Name: statistic_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE statistic_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: statistics; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE statistics (
    id bigint NOT NULL,
    data timestamp without time zone,
    type character varying(255),
    userid character varying(40)
);


--
-- Name: config_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY config
    ADD CONSTRAINT config_pkey PRIMARY KEY (config);


--
-- Name: config_values_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY config_values
    ADD CONSTRAINT config_values_pkey PRIMARY KEY (config, values_key);


--
-- Name: statistics_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY statistics
    ADD CONSTRAINT statistics_pkey PRIMARY KEY (id);


--
-- Name: fk_ga1o3rm2ukfsamenstslkv407; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY config_values
    ADD CONSTRAINT fk_ga1o3rm2ukfsamenstslkv407 FOREIGN KEY (config) REFERENCES config(config);


