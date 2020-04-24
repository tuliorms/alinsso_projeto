package br.com.fatepi.sistemas.uteis;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("localDateTimeFacesConverter")
public class LocalDateTimeFacesConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String stringValue) {

		return stringValue;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object localDateTimeValue) {

		if (null == localDateTimeValue) {

			return "";
		}
		

		return ((LocalDateTime) localDateTimeValue).format(DateTimeFormatter
				.ofPattern("dd/MM/yyyy hh:mm:ss").withZone(
						ZoneId.systemDefault()));
	}
}
