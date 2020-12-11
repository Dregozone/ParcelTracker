package manager;

import dto.ParcelDTO;
import gateway.ParcelGateway;
import java.util.ArrayList;

public class ParcelManager
{
    private final ParcelGateway gateway = new ParcelGateway();
    
    public boolean createParcel(ParcelDTO parcel) {
        
        return gateway.insertParcel(parcel);
    }
    
    public boolean editParcel(ParcelDTO parcel) {
        
        return gateway.updateParcel(parcel);
    }
    
    public boolean deleteParcel(int parcelId) {
        
        return gateway.deleteParcel(parcelId);
    }
    
    public ParcelDTO findParcel(int ParcelID)
    {
        return gateway.find(ParcelID);
    }
    
    public ArrayList<ParcelDTO> viewAllParcels()
    {
        return gateway.findAllParcels();
    }
}
