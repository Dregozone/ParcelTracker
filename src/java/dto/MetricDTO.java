package dto;

import java.io.Serializable;

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
