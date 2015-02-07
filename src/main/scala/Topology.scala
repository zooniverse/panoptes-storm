package panoptes_storm

import backtype.storm.Config
import backtype.storm.LocalCluster
import backtype.storm.topology.TopologyBuilder
import backtype.storm.spout.SchemeAsMultiScheme
import storm.kafka.{KafkaSpout,ZkHosts,SpoutConfig,StringScheme}

object StartTopology {
  def topology():TopologyBuilder = {

    val brokers = new ZkHosts("localhost:2181")

    val kafkaConfig = new SpoutConfig(brokers, "classifications", "", "panoptes_storm")
    kafkaConfig.scheme = new SchemeAsMultiScheme(new JSONScheme)

    val builder = new TopologyBuilder
    builder.setSpout("classifications", new KafkaSpout(kafkaConfig), 3)

    builder.setBolt("splitter", new ClassificationsSplitter, 3)
      .shuffleGrouping("classifications")

    return builder
  }

  def main( args:Array[String] ):Unit = {
    val conf = new Config
    conf.setDebug(true)
    conf.setMaxTaskParallelism(3)
    val cluster = new LocalCluster
    cluster.submitTopology("classifications", conf, StartTopology.topology.createTopology)
  }
}
