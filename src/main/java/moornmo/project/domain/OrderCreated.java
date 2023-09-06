package moornmo.project.domain;

import java.util.*;
import lombok.*;
import moornmo.project.domain.*;
import moornmo.project.infra.AbstractEvent;

@Data
@ToString
public class OrderCreated extends AbstractEvent {

    private String orderId;
    private String customerId;
    private Object totalAmount;
    private Object shippingAddress;
}
