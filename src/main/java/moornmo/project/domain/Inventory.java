package moornmo.project.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import moornmo.project.InventoryApplication;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long stock;

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = InventoryApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateInventory(OrderCreated orderCreated) {

        // 1. 주문된 상품의 ID를 사용하여 현재 재고를 조회한다.
        InventoryRepository inventoryRepository = repository();
        Inventory currentInventory = inventoryRepository.findById(orderCreated.getProductId()).orElse(null);

        // 2. 재고가 존재하고 재고량이 0보다 큰 경우
        if (currentInventory != null && currentInventory.getStock() > 0) {

            // 3. 재고량을 1 줄인다.
            currentInventory.setStock(currentInventory.getStock() - 1);
            inventoryRepository.save(currentInventory); // 수정된 재고량을 저장한다.

            // 4. 줄어든 재고에 대한 이벤트를 발행한다. (이 부분은 Kafka나 다른 메시징 시스템과의 연동이 필요하며, 여기서는 간략하게 표현했습니다.)
            InventoryUpdated inventoryUpdated = new InventoryUpdated();
            inventoryUpdated.setId(currentInventory.getId());
            inventoryUpdated.setStock(currentInventory.getStock());
            inventoryUpdated.publishAfterCommit();
            // 여기에 이벤트를 발행하는 코드를 추가합니다.
        }

        // repository().findById(orderCreated.getProductId()).ifPresent(inventory ->{
        //     inventory.setStock(inventory.getStock() - 1);
        //     repository().save(inventory);
    
        //     InventoryUpdated inventoryUpdated = new InventoryUpdated(inventory);
        //     inventoryUpdated.publishAfterCommit();
        // });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
