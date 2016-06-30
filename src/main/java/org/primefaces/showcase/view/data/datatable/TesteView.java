package org.primefaces.showcase.view.data.datatable;
 
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.showcase.domain.Player;
import org.primefaces.showcase.domain.Sale;
 
@RequestScoped
@ManagedBean(name = "testeView")
public class TesteView implements Serializable {
     
    /**
	 * Serial version
	 */
	private static final long serialVersionUID = 1L;
	
    private BigDecimal big;
    
    private Map<Integer, BigDecimal> listaValorNotaCaixa;
    
         
    @PostConstruct
    public void init() {
         listaValorNotaCaixa = new LinkedHashMap<Integer, BigDecimal>();
         listaValorNotaCaixa.put(1, null);
    }
    
    public void grava() {
         System.out.println("Gravou");
    }


	/**
	 * @return the big
	 */
	public BigDecimal getBig() {
		return big;
	}


	/**
	 * @param big the big to set
	 */
	public void setBig(BigDecimal big) {
		this.big = big;
	}

	/**
	 * @return the listaValorNotaCaixa
	 */
	public Map<Integer, BigDecimal> getListaValorNotaCaixa() {
		return listaValorNotaCaixa;
	}

	/**
	 * @param listaValorNotaCaixa the listaValorNotaCaixa to set
	 */
	public void setListaValorNotaCaixa(Map<Integer, BigDecimal> listaValorNotaCaixa) {
		this.listaValorNotaCaixa = listaValorNotaCaixa;
	}
	
	
    
    
}