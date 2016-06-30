package org.primefaces.showcase.domain;
 
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
 
public class Player {
     
    private String name;
    
    private String apelido;
     
    private Map<Integer,Integer> goals;
     
    public Player() {
        goals = new LinkedHashMap<Integer,Integer>();
    }
     
    public Player(String name, Map<Integer,Integer> goals) {
        this.name = name;
        this.goals = goals;
    }
 
    public Player(String name, String apelido,
			Map<Integer, Integer> goals) {
		this.name = name;
		this.apelido = apelido;
        this.goals = goals;
	}
    
    @Override
    public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append("Nome: ");
    	s.append(name);
    	s.append(" Apelido: ");
    	s.append(apelido);
    	return s.toString();
    }

	public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
     
    public int getGoals(int year) {
        return goals.get(year);
    }

	/**
	 * @return the apelido
	 */
	public String getApelido() {
		return apelido;
	}

	/**
	 * @param apelido the apelido to set
	 */
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(apelido);
		hcb.append(goals);
		hcb.append(name);
		return hcb.toHashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		EqualsBuilder eb = new EqualsBuilder();
        eb.append(name, other.name);
        eb.append(apelido, other.apelido);
        eb.append(goals, other.goals);
        return eb.isEquals();
	}
	
    
    
}