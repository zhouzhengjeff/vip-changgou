package com.hnguigu.changgou.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.hnguigu.changgou.config.RabbitMQConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.UpdateListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CanalEventListener
public class SkuCanalListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @UpdateListenPoint(schema = "changgou_goods", table = "tb_spu")
    public void onUpdateEvent(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        String beforeIsMarketable = null;
        String afterIsMarketable = null;
        String spuId = null;
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        for (CanalEntry.Column column : beforeColumnsList) {
            String columnName = column.getName();
            if ("is_marketable".equals(columnName)) {
                beforeIsMarketable = column.getValue();
                break;
            }
        }

        // TODO 向队列发送消息（spuId）
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();

        for (CanalEntry.Column column : afterColumnsList) {
            String columnName = column.getName();
            if ("id".equals(columnName)) {
                spuId = column.getValue();
                break;
            }
        }

        for (CanalEntry.Column column : afterColumnsList) {
            String columnName = column.getName();
            if ("is_marketable".equals(columnName)) {
                afterIsMarketable = column.getValue();
                if ("0".equals(beforeIsMarketable) && "1".equals(afterIsMarketable)) {
                    this.rabbitTemplate.convertAndSend("", RabbitMQConfig.SPU_PULL_QUEUE_NAME,
                            spuId);
                }
            }
        }
    }
}
