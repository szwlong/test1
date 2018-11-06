package utils

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapred.TextInputFormat
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession


object QuCi {
  def main(args: Array[String]): Unit = {

    //创建SparkConf()并设置App名称
    val sparkConf = new SparkConf().setAppName("qcc").setMaster("local[*]")
    //创建SparkContext，该对象是提交spark App的入口
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    val sc = spark.sparkContext
    //使用sc创建RDD并执行相应的transformation和action
    val rdd = sc.hadoopFile("C:\\Users\\lizs\\Desktop\\我的脚本\\表名_字段_sqoop.txt", classOf[TextInputFormat], classOf[LongWritable], classOf[Text], 1)
      .map(p => new String(p._2.getBytes, 0, p._2.getLength, "utf-8"))
      .map(x => {
        val tableName = x.split("!!")(0)
        val columns = x.split("!!")(1)
        //val comments = x.split("\t")(2)
        //val s = x.replaceAll(":", "\t")
        (tableName, columns)

      }).groupByKey()
      .saveAsTextFile("C:\\Users\\lizs\\Desktop\\result6")


    //中文乱码
    /*val rdd = sc.hadoopFile("C:\\Users\\lizs\\Desktop\\我的脚本\\1_sqoop抽取数据库\\oracle中表的字段\\字段及注释信息.txt", classOf[TextInputFormat], classOf[LongWritable], classOf[Text], 1)
      .map(p => new String(p._2.getBytes, 0, p._2.getLength, "GBK"))
      .map(x => {
        val splits = x.split("\t")
        (splits(0), splits(1) + ":" + splits(2))
      //(tableName, column + ":" + comment)
    }).saveAsTextFile("C:\\Users\\lizs\\Desktop\\yuju2.txt")*/

    //停止sc，结束该任务
    sc.stop()
  }
}
