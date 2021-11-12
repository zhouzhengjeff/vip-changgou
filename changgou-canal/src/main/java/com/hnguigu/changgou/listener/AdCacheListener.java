package com.hnguigu.changgou.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.hnguigu.changgou.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CanalEventListener
public class AdCacheListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ListenPoint(schema = "changgou_business", table = "tb_ad")
    public void onUpdateEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
//        System.out.println("广告表数据发生改变");
//        //获取改变之前的数据
//        rowData.getBeforeColumnsList().forEach((c) -> System.out.println("改变前的数据:" + c.getName
//        () + "::" + c.getValue()));
//
//        //获取改变之后的数据
//        rowData.getAfterColumnsList().forEach((c) -> System.out.println("改变之后的数据:" + c.getName
//        () + ":: " + c.getValue()));
        String position = null;
        List<CanalEntry.Column> columnList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : columnList) {
            String columnName = column.getName();
            if ("position".equals(columnName)) {
                position = column.getValue();
                break;
            }
        }


        this.rabbitTemplate.convertAndSend("", RabbitMQConfig.AD_QUEUE_NAME, position);
    }
}
