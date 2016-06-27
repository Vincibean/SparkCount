/*
 * Copyright (C) 2016  Vincibean <Andre Bessi>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.vincibean.spark

import org.apache.spark.sql.types.{StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory

/**
  * Apache Spark main and only job. To be used locally.
  * Load a text file, convert it into a DataFrame, then count the number of words.
  *
  * Created by Vincibean on 24/06/16.
  */
object WordCount {

  def main(args: Array[String]): Unit = {
    // Create the logger
    val logger = LoggerFactory.getLogger(WordCount.getClass)
    logger.info("Hello from the Pizza class")
    // Create the SQLContext from a new SparkContext, which in turn will take a new SparkConf
    val sqlContext = new SQLContext(new SparkContext(new SparkConf()
      .setAppName("Apache Spark Word Count Example")                                  // Set a name to this job
      .setMaster("local[*]")))                                                        // Run the job locally; use all cores.

    val shakespeareRDD = sqlContext.sparkContext                                      // Load the Spark Context
      .textFile(getClass.getResource("/all-shakespeare.txt").getPath)                 // Load the text file from the resources folder
      .flatMap(_.split("""\w+"""))                                                    // Use a Regex to split per word
      .map(Row(_))                                                                    // Create a new Row with each String
    // Perform a first count using the existing RDD
    val wordCountRDD = shakespeareRDD.count
    logger.info(s"There are $wordCountRDD words contained in the documents. Using an RDD.")
    // Define the Schema of the RDD
    val schema = StructType(Array(StructField("word", StringType)))
    // Create a new DataFrame from the RDD given the information contained in the Schema
    val shakespeareDataFrame = sqlContext.createDataFrame(shakespeareRDD, schema)
    // Perform the actual computation: counting
    val wordCountDataFrame = shakespeareDataFrame.count
    // Print the result
    logger.info(s"There are $wordCountDataFrame words contained in the documents. Using a Data Frame.")
  }

}
