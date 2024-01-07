./gradlew build
scp  -i ~/.ssh/id_rsa_helios -P 2222 ./build/libs/*.war s369074@se.ifmo.ru:/home/studs/s369074/wildfly-29.0.1.Final/standalone/deployments
echo "Starting SSH tunnel..."
ssh -i ~/.ssh/id_rsa_helios  -L localhost:8111:127.0.0.1:36907 s369074@se.ifmo.ru -p 2222 -N
