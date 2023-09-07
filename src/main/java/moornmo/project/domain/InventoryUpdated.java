package moornmo.project.domain;

import moornmo.project.infra.AbstractEvent; //이게 좀....
import lombok.*;

@Data
public class InventoryUpdated extends AbstractEvent{

    public InventoryUpdated(){} //for jason parsers

    public InventoryUpdated(Inventory inventory){
        super(inventory);
    }

    Long id;
    Long stock;

}
