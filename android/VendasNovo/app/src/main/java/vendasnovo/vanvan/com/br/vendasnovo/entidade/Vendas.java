package vendasnovo.vanvan.com.br.vendasnovo.entidade;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by evandro on 15/10/17.
 */

@Root
public class Vendas {

    @Element
    private Long id;
    @Element
    private Double preco;
    @Element
    private Integer produto;
    @Element
    private Double la;
    @Element
    private Double lo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Double getLa() {
        return la;
    }

    public void setLa(Double la) {
        this.la = la;
    }

    public Double getLo() {
        return lo;
    }

    public void setLo(Double lo) {
        this.lo = lo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
