package userUI;

import dto.ParcelDTO;
import manager.ParcelManager;

public class CreateParcelCommand implements UserCommand
{

    private final ParcelDTO parcelDTO;
    private final ParcelManager parcelMgr;

    public CreateParcelCommand(ParcelDTO parcelDTO)
    {
        this.parcelDTO = parcelDTO;
        parcelMgr = new ParcelManager();
    }

    @Override
    public Object execute()
    {
        if (parcelMgr.createParcel(parcelDTO))
        {
            return parcelMgr
                    .findParcel( parcelDTO.getId() );
        }
        
        return null;
    }
}
