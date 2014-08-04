package dto;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public interface Config {
    public entity.Config convertToConfig();
    public void parseConfig(entity.Config config);
}
