package com.gc.spark.meetup.learningspark.scala.sql

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileUtil
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import java.io.File
import org.apache.hadoop.fs._

/**
 * Created by ganeshchand on 11/9/15.
 */
object CsvExample {
  Logger.getLogger("org").setLevel(Level.OFF)

  def main(args: Array[String]) {

    def merge(srcPath: String, dstPath: String): Unit = {
      val hadoopConfig = new Configuration()
      val hdfs = FileSystem.get(hadoopConfig)
      FileUtil.copyMerge(hdfs, new Path(srcPath), hdfs, new Path(dstPath), false, hadoopConfig, null)
    }

    val sc = new SparkContext("local[*]", "Processing CSV using DataFrame")
    val sqlContext = new SQLContext(sc)
    val filePath = "/Users/ganeshchand/gh/gc/spark/kathmandu-spark-meetup-intro-to-spark/src/main/resources/data/customer.csv"
    val csvInputDF = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .load(filePath)
    csvInputDF.printSchema()
    csvInputDF.cache()
    csvInputDF.registerTempTable("customer")
    val teenAgeCustomerCountByCity = sqlContext.sql("" +
      "select count(*) as TeenAgeCount, city from customer where age between 13 and 19 group by city ")
    println(teenAgeCustomerCountByCity.show())

    val file = "/tmp/primaryTypes.csv"
    FileUtil.fullyDelete(new File(file))

    val destinationFile = "/tmp/singlePrimaryTypes.csv"
    FileUtil.fullyDelete(new File(destinationFile))

    teenAgeCustomerCountByCity
      .coalesce(1) // to produce a single file output
      .write
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .save(file)
    merge(file, destinationFile)
    sc.stop()
  }
}
