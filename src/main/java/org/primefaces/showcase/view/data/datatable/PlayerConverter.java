package org.primefaces.showcase.view.data.datatable;
 
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.showcase.domain.Player;
 
@FacesConverter("playerConverter")
public class PlayerConverter implements Converter {
 
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && value.trim().length() > 0) {
			return this.getAttributesFrom(uic).get(value);
		}
		return null;
	}
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Player) object).getName());
        }
        else {
            return null;
        }
    }
    
    private Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}
}     