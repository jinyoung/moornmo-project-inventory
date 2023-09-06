# topping-isVanillaK8s

## How to Test

To publish an OrderPlaced Event :
```
docker run -i --rm --network=host\
        edenhill/kcat:1.7.1 \
                -b localhost:9092 \
                -t moornmo.project \
                -H "type=OrderCreated" -H "contentType=application/json" \
                -K: \
                -P <<EOF
1:{"eventType":"OrderCreated","timestamp":1694009759996,"orderId":"0001","customerId":null,"totalAmount":null,"shippingAddress":null}
EOF
```

Check whether the event is properly consumed:
```
docker run -i --rm --network=host\
        edenhill/kcat:1.7.1 \
                -b localhost:9092 \
                -C \
                -t moornmo.project \
                -f 'Headers: %h: Message value: %s\n'
```