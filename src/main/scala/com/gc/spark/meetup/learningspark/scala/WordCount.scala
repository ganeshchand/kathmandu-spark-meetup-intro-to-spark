package com.gc.spark.meetup.learningspark.scala

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

/**
  * Created by ganeshchand on 11/5/15.
  * Illustrates: flatMap, map, reduceByKey, filter
  */
object WordCount {
  Logger.getLogger("org").setLevel(Level.OFF) // Turn off spark's default logger
  def main(args: Array[String]) {
    val master = args.length match {
      case x: Int if x > 0 => args(0) // first argument is spark mode
      case _ => "local[*]"
    }
    val sc = new SparkContext(master, "WordCount")
    val inputRDD = args.length match {
      case x: Int if x > 1 => sc.textFile(args(1)) // second argument is an input file path
      case _ => sc.parallelize(List("Funny facts about Kathmandu",
        "Everybody knows everybody", "Everybody is on Facebook", "Every body is a CEO or a Director",
      "Girls greet each other European style", "Contagious business policies"
      ))
    }
    val words = inputRDD.flatMap(line => line.split(" "))

    args.length match {
      case x: Int if x > 2 => { // 3rd argument is an output file path
        val bigWordCounts = words.map(word => (word, 1)).reduceByKey((x,y) => x + y)
        bigWordCounts.saveAsTextFile(args(2))
      }
      case _ => {
        /**
          * countByValue() returns the count of each unique value in this RDD as a local map of (value, count) pairs.
          * It should only be used if the resulting map is expected to be small, as
          * the whole thing is loaded into the driver's memory.
         */
        val simpleWordCounts = words.countByValue().filter(_._2 > 1) // filter out words with count < 2
        println(simpleWordCounts.mkString(","))
      }
    }




  }
}
