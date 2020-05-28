mvn compile test
export MAVEN_OPTS="-Xms4g -Xmx200g"; mvn exec:java -Dexec.mainClass="personicle.datagen.user.SimpleGenerator" -Dexec.args="1000000" -Dexec.classpathScope="test"
export MAVEN_OPTS="-Xms4g -Xmx200g"; mvn exec:java -Dexec.mainClass="personicle.datagen.observation.Food.FoodLogGenerator" -Dexec.classpathScope="test"
export MAVEN_OPTS="-Xms4g -Xmx200g"; mvn exec:java -Dexec.mainClass="personicle.datagen.nosqlcomp.food.Food.FoodLogGenerator" -Dexec.classpathScope="test"
mvn exec:java -Dexec.mainClass="personicle.datagen.Sandbox"
export MAVEN_OPTS="-Xms4g -Xmx10g"; mvn exec:java -Dexec.mainClass="IdQuery"
export MAVEN_OPTS="-Xms4g -Xmx200g"; mvn exec:java -Dexec.mainClass="asterix.record.RecordEfficiencyTest" -Dexec.classpathScope="test"
export MAVEN_OPTS="-Xms4g -Xmx200g"; LD_PRELOAD=`jemalloc-config --libdir`/libjemalloc.so.`jemalloc-config --revision` mvn exec:java -Dexec.mainClass="asterix.record.RecordEfficiencyTest" -Dexec.classpathScope="test"