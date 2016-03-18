CREATE TABLE config
(
    config VARCHAR(255) PRIMARY KEY NOT NULL
);
CREATE TABLE config_values
(
    config_config VARCHAR(255) NOT NULL,
    value VARCHAR(255),
    values_key VARCHAR(255) NOT NULL,
    CONSTRAINT config_values_pkey PRIMARY KEY (config_config, values_key),
    CONSTRAINT fk_config_config FOREIGN KEY (config_config) REFERENCES config (config)
);