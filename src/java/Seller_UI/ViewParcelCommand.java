package Seller_UI;

import manager.ParcelManager;

public class ViewParcelCommand implements SellerCommand
{
    private final int parcelId;
    private final ParcelManager parcelMgr;

    public ViewParcelCommand(int parcelId)
    {
        this.parcelId = parcelId;
        parcelMgr = new ParcelManager();
    }

    @Override
    public Object execute()
    {
        return parcelMgr.findParcel(parcelId);
    }
}
