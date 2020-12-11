package dto;

import java.io.Serializable;
import java.util.Objects;

public class MetricDTO implements Serializable
{
    private final String name;
    private final int daysToComplete;
    private final int deliveryCount;

    public MetricDTO(String name, int daysToComplete, int deliveryCount)
    {
        this.name = name;
        this.daysToComplete = daysToComplete;
        this.deliveryCount = deliveryCount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + this.daysToComplete;
        hash = 89 * hash + this.deliveryCount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MetricDTO other = (MetricDTO) obj;
        if (this.daysToComplete != other.daysToComplete) {
            return false;
        }
        if (this.deliveryCount != other.deliveryCount) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    public String getName() {
        return name;
    }

    public int getDeliveryCount() {
        return deliveryCount;
    }

    public int getDaysToComplete() {
        return daysToComplete;
    }
}
