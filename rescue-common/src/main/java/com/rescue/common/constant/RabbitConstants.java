package com.rescue.common.constant;

/**
 * rabbitmq topic
 * @author Wang Jin
 * @date 2026/03/06
 **/
public interface RabbitConstants {

    //消息交换机名称
    String MSG_CHANGE_NAME="RESCUE-TOPIC";
    //消息路由key
    String MSG_ROUTING_KEY="msg.sysMsg";

    /** 广播交换机名称 */
    String FANOUT_EXCHANGE_URLS_NAME = "RESCUE-FANOUT-URLS";
    /** 白名单更新广播队列 */
    String QUEUE_FANOUT_URLS = "RESCUE-FANOUT-QUEUE-URLS";
    /** 告警事件监听 */
    String QUEUE_ALARM_EVENT = "RESCUE-ALARM-EVENT-QUEUE";

}
