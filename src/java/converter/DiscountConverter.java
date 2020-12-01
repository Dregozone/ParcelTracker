package converter;

import dto.DiscountDTO;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "discountConverter")
public class DiscountConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String newValue) throws ConverterException
    {
        String[] part = newValue.split("[\\(\\%]");
        try
        {
            return new DiscountDTO(part[0], Double.parseDouble(part[1]));
        }
        catch (NumberFormatException nfe)
        {
            throw new ConverterException("Rate failed to convert");
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException
    {
        if (value == null)
        {
            return "";
        }

        if (value instanceof DiscountDTO)
        {
            return value.toString();
        }

        String msgDetail = "Unexpected type " + value.getClass().getName();
        FacesMessage msg = new FacesMessage("Discount rate conversion error",
                msgDetail);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        throw new ConverterException(msg);
    }
}
