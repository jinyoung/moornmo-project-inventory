# topping-isVanillaK8s

## How to Test

To publish an OrderPlaced Event :
```
docker run -i --rm --network=host\
        edenhill/kcat:1.7.1 \
                -b localhost:9092 \
                -t moornmo.project \
                -K: \
                -H "type=OrderPlaced" \
                -P <<EOF

1:{"eventType":"OrderCreated","timestamp":1694009759996,"orderId":"0001","customerId":null,"totalAmount":null,"shippingAddress":null}
EOF
```

Check whether the event is properly consumed:
```
docker exec -it infra-kafka-1 /bin/bash
[appuser@edbd637ef340 ~]$ cd /bin
[appuser@edbd637ef340 bin]$ ./kafka-console-consumer --bootstrap-server localhost:9092 --topic test
```