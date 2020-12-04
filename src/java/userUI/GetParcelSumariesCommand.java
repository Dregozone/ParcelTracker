package userUI;

import manager.ParcelManager;

public class GetParcelSumariesCommand implements UserCommand
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
