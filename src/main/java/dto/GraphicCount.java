package dto;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public class GraphicCount {
    private String value;
    private Long count;
    public GraphicCount(Object value, Long count){
        this.value = value.toString();
        this.count = count;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
