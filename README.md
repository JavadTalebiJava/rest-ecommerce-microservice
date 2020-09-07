# Rest-ecommerce-microservice
A simple modular Java microservice without API gateway only for training purpose

## Author
- [Javad Talebi](https://github.com/JavadTalebiJava) 

## Project setup instructions
### Requests for these four microservices

```cURL
curl -X POST \
  http://localhost:9080/api/orders \
  -H 'Accept: */*' \
  -H 'Accept-Encoding: gzip, deflate' \
  -H 'Cache-Control: no-cache' \
  -H 'Connection: keep-alive' \
  -H 'Content-Type: application/json' \
  -H 'Host: localhost:9080' \
  -H 'cache-control: no-cache' \
  -d '{
    "customerId": "1234",
    "productId": 1,
    "amount": 1,
    "cardNumber": "1234567812345678"
}'
```
