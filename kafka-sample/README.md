download kafka_2.13-4.3.1.tgz

生成集群ID：打开终端，进入Kafka解压目录，执行以下命令生成一个唯一的集群标识符（Cluster ID）并保存它
```shell
KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
echo $KAFKA_CLUSTER_ID
```

格式化存储目录：使用上一步生成的KAFKA_CLUSTER_ID来格式化Kafka的数据存储目录。此操作只需在首次启动前执行一次
```shell
bin/kafka-storage.sh format --standalone -t $KAFKA_CLUSTER_ID -c config/server.properties
```

> 注意：请务必使用 config/server.properties 进行格式化，如果误用 config/kraft/server.properties 可能导致格式错误

启动Kafka服务：一切就绪后，执行启动命令
```shell
bin/kafka-server-start.sh config/server.properties
```