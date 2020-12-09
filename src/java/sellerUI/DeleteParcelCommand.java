package sellerUI;

import manager.ParcelManager;

public class DeleteParcelCommand implements SellerCommand
{
    private final int parcelId;
    private final ParcelManager parcelMgr;

    public DeleteParcelCommand(int parcelId)
    {
        this.parcelId = parcelId;
        parcelMgr = new ParcelManager();
    }

    @Override
    public Object execute()
    {
        if (parcelMgr.deleteParcel(parcelId))
        {
            return parcelMgr
                    .findParcel(parcelId);
        }
        
        return null;
    }
}
