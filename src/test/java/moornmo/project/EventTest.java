package moornmo.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import moornmo.project.config.kafka.KafkaProcessor;
import moornmo.project.domain.OrderCreated;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(EventTest.class);
   
   @Autowired
   private KafkaProcessor processor;
   @Autowired
   private MessageCollector messageCollector;

   @Test
   @SuppressWarnings("unchecked")
   public void testAccepted() {
      OrderCreated o = new OrderCreated();
      o.setOrderId("001");
      processor.inboundTopic().send(MessageBuilder.withPayload(o).build());
      Message<OrderCreated> received = (Message<OrderCreated>) messageCollector.forChannel(processor.outboundTopic()).poll();

      LOGGER.info("Order response received: {}", received.getPayload());
      assertNotNull(received.getPayload());
      assertEquals("001", received.getPayload().getOrderId());
   }


}