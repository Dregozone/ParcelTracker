package Seller_UI;

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
            System.out.println("\n\nsellerUI.EditParcelCommand.execute()\n\n");
            
            return parcelMgr
                    .findParcel( parcelDTO.getId() );
        }
        
        return null;
    }
}
