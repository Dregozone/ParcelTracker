package dto;

import java.io.Serializable;

public class DiscountDTO implements Serializable
{
    private String code;
    private double rate;

    public DiscountDTO()
    {
        this("??", 0);
    }
    
    public DiscountDTO(String code, double rate)
    {
        this.code = code;
        this.rate = rate;
    }

    public String getCode()
    {
        return code;
    }

    public double getRate()
    {
        return rate;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof DiscountDTO))
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }
        
        DiscountDTO obj1 = (DiscountDTO)obj;
        
        return obj1.code.equalsIgnoreCase(code) &&
                (int)(obj1.rate*100) == (int)(rate*100);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + this.code.hashCode();
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.rate) ^ (Double.doubleToLongBits(this.rate) >>> 32));
        return hash;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public void setRate(double rate)
    {
        this.rate = rate;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s (%1.2f%s", code, rate, "%)");
    }
}
