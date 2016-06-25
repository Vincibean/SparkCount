package org.vincibean.spark

import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Vincibean on 24/06/16.
  */
object WordCount {

  def main(args: Array[String]): Unit = {
    val sqlContext = new SQLContext(new SparkContext(new SparkConf()
      .setAppName("Apache Spark Word Count Example")
      .setMaster("local[*]")))

    val shakespeareRDD = sqlContext.sparkContext
      .textFile(getClass.getResource("/all-shakespeare.txt").getPath)
      .flatMap(_.split("""\w+"""))
      .map(Row(_))

    val schema = StructType(Array(StructField("word", StringType)))

    val shakespeareDataFrame = sqlContext.createDataFrame(shakespeareRDD, schema)

    val wordCount = shakespeareDataFrame
      .count

    println(wordCount)

  }

}
