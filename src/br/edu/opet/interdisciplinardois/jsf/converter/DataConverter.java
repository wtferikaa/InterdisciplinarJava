package br.edu.opet.interdisciplinardois.jsf.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("dataConverter")
public class DataConverter implements Converter
{
    private static final String FORMATO_DATA = "dd/MM/uuuu";

    @Override
    public Object getAsObject(FacesContext pCtx, UIComponent pComponente, String pObjeto)
    {
        if (pObjeto == null || pObjeto.trim().isEmpty())
            return null;

        LocalDate tData = null;
        try
        {
            tData = LocalDate.parse(pObjeto, DateTimeFormatter.ofPattern(FORMATO_DATA));
        }
        catch (DateTimeParseException e)
        {
            throw new ConverterException("Formato da data inválido");
        }

        return tData;
    }

    @Override
    public String getAsString(FacesContext pCtx, UIComponent pComponente, Object pObjeto)
    {
        if (pObjeto == null)
            return "";

        return ((LocalDate) pObjeto).format(DateTimeFormatter.ofPattern(FORMATO_DATA));
    }
}
