package manager;

import dto.ParcelDTO;
import gateway.ParcelGateway;
import java.util.ArrayList;

public class ParcelManager
{
    private ParcelGateway gateway = new ParcelGateway();
    
    public ParcelDTO findParcel(int ParcelID)
    {
        return gateway.find(ParcelID);
    }
    
    public ArrayList<ParcelDTO> getParcelSummaries()
    {
        return gateway.findAllParcelSummaries();
    }
    
    public ArrayList<ParcelDTO> getParcelSummariesByOrder(int OrderID)
    {
        return gateway.findAllSummariesByOrder(OrderID);
    }
    
    public boolean createParcel(ParcelDTO parcel) {
        
        return gateway.createParcel(parcel);
    }
    
    public boolean editParcel(ParcelDTO parcel) {
        
        return gateway.editParcel(parcel);
    }
    
    public boolean deleteParcel(int parcelId) {
        
        return gateway.deleteParcel(parcelId);
    }
}
