cd ~/kafka/kafka_2.13-3.7.0/bin/ || exit
./zookeeper-server-start.sh ../config/zookeeper.properties
./kafka-server-start.sh ../config/server.properties
