# Join Topic Demo
Joining Kafka topics using Kafka Streams
## Task
We have two topics: **profile** and **event**.


TODO: Enrich event data with profile information and downstream to enriched topic called **enriched**.


## Solution
Using Kafka Streams do:
* Make KTable ***profileTable*** for **profile** topic
* Make KStream ***eventStream*** for **event** topic
* Join ***eventStream*** with ***profileTable*** using KafkaStreams *join* method
* Downstream the result into *enriched* topic using KafkaStreams *to* method

## Example

Let's assume we have **event**, **profile** and **enriched** topics at local Kafka

Now let's add entities to the topics
```
kafka-console-producer --broker-list localhost:9092 --topic profile --property "parse.key=true" --property "key.separator=:"
"123": {"ownerId":"123", "trackerId":"456", "email":"email@example.com","gender":0,"file_name":"test123.jpg","status":0}

kafka-console-producer --broker-list localhost:9092 --topic event --property "parse.key=true" --property "key.separator=:"
"123": {"objectid":"123", "timestamp":1596717652, "latitude":12.34, "longitude": -23.45}
```
The result at **enriched** should be
```
"123"-{"objectid":"123","timestamp":1596717652,"latitude":12.34,"longitude":-23.45,"ownerId":"123","trackerId":"456"}
```