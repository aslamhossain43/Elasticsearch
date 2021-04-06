# Elasticsearch
Elasticsearch with spring boot

1. sudo docker run -d --name es762 -p 9200:9200 -e "discovery.type=single-node" elasticsearch:7.6.2
2. mvn clean install -DskipTests
3. check  http://localhost:8080/findAll , http://localhost:8080/findByFName/Aslam







