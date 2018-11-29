package org.xjc.bigdata.storm.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * Created by xjc on 2018-10-24.
 */
public class PrintBolt extends BaseRichBolt {

//    private static final Logger LOGGER = LoggerFactory.getLogger(PrintBolt.class);
    private OutputCollector collector;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        collector = outputCollector;
    }

    public void execute(Tuple tuple) {
        System.out.println(tuple.getString(0) + " Hello World!");
        collector.ack(tuple);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
