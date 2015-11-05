#Installing Spark

###Pre-requisite: You have [JAVA 7.x](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html) installed.


 

##Installing on Mac

//Todo



##Installing on Windows

1. Download spark - http://spark.apache.org/downloads.html
  1. Choose a Spark release: 1.5.1
  2. Choose a package type: Pre-built for Hadoop 2.6 and later
  3. Choose a download type: Select Apache Mirror
  
2. Download spark-1.5.1-bin-hadoop2.6.tgz
3. Extract spark-1.5.1-bin-hadoop2.6.tgz
4. Go to spark-1.5.1-bin-hadoop2.6/bin
5. spark-shell --master "local[*]"

Note: You may get an error related to sqlContext. If you do, create sqlContext explicitly
val sqlContext = new org.apache.spark.sql.SQLContext(sc);


##Installing other tools


