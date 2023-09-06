# topping-isVanillaK8s

## How to Test

To publish an OrderPlaced Event :
```
docker run -i --rm --network=host\
        edenhill/kcat:1.7.1 \
                -b localhost:9092 \
                -t test \
                -K: \
                -P <<EOF

1:{"order_id":1,"order_ts":1534772501276,"total_amount":10.50,"customer_name":"Bob Smith"}
2:{"order_id":2,"order_ts":1534772605276,"total_amount":3.32,"customer_name":"Sarah Black"}
3:{"order_id":3,"order_ts":1534772742276,"total_amount":21.00,"customer_name":"Emma Turner"}
EOF
```

Check whether the event is properly consumed:
```
docker exec -it infra-kafka-1 /bin/bash
[appuser@edbd637ef340 ~]$ cd /bin
[appuser@edbd637ef340 bin]$ ./kafka-console-consumer --bootstrap-server localhost:9092 --topic test
```