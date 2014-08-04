package dto;

import entity.Config;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public class GeralConfig implements dto.Config {
    public static final String CONFIG_NAME = "geral_config";
    private boolean habilitarEstatisticas = false;
    public GeralConfig(Config config){
        this.parseConfig(config);
    }
    public GeralConfig(){

    }

    public boolean getHabilitarEstatisticas() {
        return habilitarEstatisticas;
    }

    public void setHabilitarEstatisticas(boolean habilitarEstatisticas) {
        this.habilitarEstatisticas = habilitarEstatisticas;
    }


    @Override
    public Config convertToConfig() {
        Config config = new Config();
        config.setConfig(CONFIG_NAME);
        config.getValues().put("habilitarEstatistica", new Boolean(habilitarEstatisticas).toString());
        return config;
    }

    @Override
    public void parseConfig(Config config) {
        if(!config.getConfig().equalsIgnoreCase(CONFIG_NAME)){
            throw new RuntimeException("Config is not a GeralConfig");
        }
        try {
            this.habilitarEstatisticas = Boolean.parseBoolean(config.getValues().get("habilitarEstatistica"));
        }catch (Exception ex){

        }

    }
}
