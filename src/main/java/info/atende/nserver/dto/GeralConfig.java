package info.atende.nserver.dto;

import info.atende.webutil.jpa.ConfigDTO;
/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public class GeralConfig implements ConfigDTO {
    public static final String CONFIG_NAME = "geral_config";
    private boolean habilitarEstatisticas = false;
    public GeralConfig(){

    }

    public boolean getHabilitarEstatisticas() {
        return habilitarEstatisticas;
    }

    public void setHabilitarEstatisticas(boolean habilitarEstatisticas) {
        this.habilitarEstatisticas = habilitarEstatisticas;
    }


    @Override
    public String configName() {
        return CONFIG_NAME;
    }
}
