package sellerUI;

import dto.ParcelDTO;
import manager.ParcelManager;

public class EditParcelCommand implements SellerCommand
{
    private final ParcelDTO parcelDTO;
    private final ParcelManager parcelMgr;

    public EditParcelCommand(ParcelDTO parcelDTO)
    {
        this.parcelDTO = parcelDTO;
        parcelMgr = new ParcelManager();
    }

    @Override
    public Object execute()
    {
        if (parcelMgr.editParcel(parcelDTO))
        {
            return parcelMgr
                    .findParcel( parcelDTO.getId() );
        }
        
        return null;
    }
}
