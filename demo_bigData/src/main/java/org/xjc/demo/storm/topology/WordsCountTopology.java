package org.xjc.demo.storm.topology;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.xjc.demo.storm.bolt.MyBolt;
import org.xjc.demo.storm.bolt.PrintBolt;
import org.xjc.demo.storm.sout.MySpout;

/**
 * Created by xjc on 2018-10-24.
 */
public class WordsCountTopology {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("word", new MySpout(), 1);
        builder.setBolt("exclaim", new MyBolt(), 1).shuffleGrouping("word");
        builder.setBolt("print", new PrintBolt(), 1).shuffleGrouping("exclaim");

        Config config = new Config();
        config.setDebug(false);

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("test", config, builder.createTopology());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        localCluster.killTopology("test");
        localCluster.shutdown();
        System.out.println("End!");
    }

}
