package sellerUI;

import manager.ParcelManager;

public class GetParcelSumariesCommand implements SellerCommand
{

    private final ParcelManager parcelMgr;

    public GetParcelSumariesCommand()
    {
        parcelMgr = new ParcelManager();
    }

    @Override
    public Object execute()
    {
        return parcelMgr.getParcelSummaries();
    }
}
