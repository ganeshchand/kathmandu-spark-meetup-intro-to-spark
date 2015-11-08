#Spark Basics


##Using REPL
```
$SPARK_HOME/bin/spark-shell --master "local[*]" --driver-memory 2G
```
###Spark RDD Basics

```scala

val numRDD = sc.parallelize(1 to 10)

val evenNumRDD = numRDD.filter(_ % 2 == 0)

val oddNumRDD = numRDD.filter(_ % 2 != 0)

numRDD.collect

evenNumRDD.collect

oddNumRDD.collect

val unionRDD = evenNumRDD.union(oddNumRDD)

unionRDD.collect

val sortedUnionRDD = unionRDD.collect.sorted

```


###SPark SQL Basics

####Text File

datafile is available at https://github.com/ganeshchand/kathmandu-spark-meetup-intro-to-spark/blob/master/src/main/resources/customer.txt

You can download using the command:

```
wget https://github.com/ganeshchand/kathmandu-spark-meetup-intro-to-spark/blob/master/src/main/resources/customer.txt
```

```scala
 // Path to customer.txt
 
val filePath = "customer.txt"

// Define a Schema to represent a customer
case class Customer(customer_id: Int, name: String, age: Int, sex: String, city: String, state: String, zip_code: String)

// create an RDD

val val customerRDD = sc.textFile(filePath).map(line => line.split(",")

// create a DataFrame using an RDD
val val customerDF = customerRDD.map(p => Customer(p(0).trim.toInt, p(1), p(2).trim.toInt, p(3), p(4), p(5), p(6))).toDF

// Register as table
val customerDF.registerTempTable("customer")

customerDF.cache

val customerDF.show

val customerDF.printSchema

// sample SQL constructs
customerDF.select("name", "city").show()

customerDF.filter(dfCustomers("customer_id").equalTo(500)).show()

dfCustomers.groupBy("zip_code").count().show() // Count the customers by zip code


 We can also programmatically specify the schema of the dataset. This is useful when the custom classes cannot be 
 defined ahead of time because the structure of data is encoded in a string.Following code example shows how to 
 specify the schema using the new data type classes StructType, StringType, and StructField.
 
 // Create an RDD
val rddCustomers = sc.textFile("customer.txt")
 
 // The schema is encoded in a string
val schemaString = "customer_id name age sex city state zip_code"
 
 // Import Spark SQL data types and Row.
 
import org.apache.spark.sql._
 
import org.apache.spark.sql.types._;
 
 // Generate the schema based on the string of schema
val schema = StructType(schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)))
 
 // Convert records of the RDD (rddCustomers) to Rows.
val rowRDD = rddCustomers.map(_.split(",")).map(p => Row(p(0).trim,p(1),p(2),p(3),p(4), p(5), p(6)))
 
 // Apply the schema to the RDD.
val dfCustomers = sqlContext.createDataFrame(rowRDD, schema)
 
 // Register the DataFrames as a table.
dfCustomers.registerTempTable("customers")

```

####Json File

datafile is available at https://github.com/ganeshchand/kathmandu-spark-meetup-intro-to-spark/blob/master/src/main/resources/customer.json


```scala

val customerJsonDF = sqlContext.jsonFile("customer.json")

customerJsonDF.printSchema

customerJsonDF.cache

customerJsonDF.show

customerJsonDF.registerTempTable("customer")

sqlContext.sql("select * from customer").show

sqlContext.sql("select * from customer order by id").show

sqlContext.sql("select count(*) as teenAgeCount, city from customer where age between 13 and 20 group by city ").show

```

####CSV File
Using [databricks spark-csv package](https://github.com/databricks/spark-csv)
bin/spark-shell --master "local[*]" --driver-memory 2G --packages com.databricks:spark-csv_2.10:1.2.0

*   Using SQL API
*   Using Scala API




