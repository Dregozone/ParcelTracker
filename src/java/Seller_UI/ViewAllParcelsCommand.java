package Seller_UI;

import manager.ParcelManager;

public class ViewAllParcelsCommand implements SellerCommand
{
    private final ParcelManager parcelMgr;

    public ViewAllParcelsCommand()
    {
        parcelMgr = new ParcelManager();
    }

    @Override
    public Object execute()
    {
        return parcelMgr.viewAllParcels();
    }
}
