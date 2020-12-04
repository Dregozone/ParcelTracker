package userUI;

import manager.ParcelManager;

public class FindParcelCommand implements UserCommand
{

    private final int parcelId;
    private final ParcelManager parcelMgr;

    public FindParcelCommand(int parcelId)
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
