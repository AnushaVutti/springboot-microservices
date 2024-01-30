# springboot-microservices
It consists of 4 different microservices namely inventory service(contains info about the products in stocks or not), product service(creates and views products, acts as product catalog), order serrvice(order deatils it has), notification service (notification to be sent regarding order status)
We have load balancer to equally distribute the load among different service instances
Discovery server to register the different service instances and enable communication between different services
API GW which authneticates and routes requests to respective services and also acts as load balancer balancing load across servers
We have dockerized the application using docker compose
We have also monitoring the metrics with the help of monitoring tools Prometheus and Grafana
Resilience4J as a resilient and fault tolerant mechanism
When an order is placed, first the order service calls inventory service (sync communication) to check if the product is in stock. If it's in stock, order will be placed
and order service then calls notification service to notify the user (async communication)
