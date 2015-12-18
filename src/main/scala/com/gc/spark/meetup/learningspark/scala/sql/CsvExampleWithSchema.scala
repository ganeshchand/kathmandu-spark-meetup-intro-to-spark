package com.gc.spark.meetup.learningspark.scala.sql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType};


/**
  * Created by ganeshchand on 11/9/15.
  */
object CsvExampleWithSchema {
  Logger.getLogger("org").setLevel(Level.OFF)

  def main(args: Array[String]) {
    val sc = new SparkContext("local[*]", "Processing CSV using DataFrame")
    val sqlContext = new SQLContext(sc)
    val filePath = "/Users/ganeshchand/gh/gc/spark/kathmandu-spark-meetup-intro-to-spark/src/main/resources/data/customer.csv"

    //Define Schema
    import org.apache.spark.sql._
    val schemaString = "name age"
    val schema =
      StructType(
        schemaString.split(" ").map(fieldName => StructField(fieldName, StringType, true)))


//    val customSchema = StructType(
//      StructField("customer_id", IntegerType, true),
//      StructField("name", StringType, true),
//      StructField("age", IntegerType, true),
//      StructField("sex", StringType, true),
//      StructField("city", StringType, true),
//      StructField("state", StringType, true),
//      StructField("zip_code", StringType, true))

    val csvInputDF = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
//      .schema(customSchema)
      .load(filePath)
    csvInputDF.printSchema()
    csvInputDF.cache()
    csvInputDF.registerTempTable("customer")
    val teenAgeCustomerCountByCity = sqlContext.sql("" +
      "select count(*) as TeenAgeCount, city from customer where age between 13 and 19 group by city ")
    println(teenAgeCustomerCountByCity.show())

    teenAgeCustomerCountByCity.write
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .save("teenAgeCustomerCountByCity.csv")
  sc.stop()
  }
}
