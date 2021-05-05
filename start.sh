echo "Inicializando Build";
mvn package;
echo "Build realizado com sucesso!";
echo "Inicializando Vacina Ja";
cd target;
java -jar vacinaja-0.0.1-SNAPSHOT.jar;
