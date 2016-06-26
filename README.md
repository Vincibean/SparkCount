# Apache Spark Word Count Example
An [Apache Spark](http://spark.apache.org/) example job. Developed for showing that shuffling doesn't occur when
[RDD](http://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.rdd.RDD).count()
or [DataFrame](http://spark.apache.org/docs/latest/api/scala/index.html#org.apache.spark.sql.DataFrame).count()
is invoked.

## Requirements
- [`Scala`](http://www.scala-lang.org/)
- [`SBT`](http://www.scala-sbt.org/)

## Usage
From the command line, run:

    $ sbt clean compile run

## License
Unless stated elsewhere, all files herein are licensed under the GPLv3 license.
For more information, please see the [LICENSE](https://github.com/Vincibean/SparkCount/blob/master/LICENSE) file.