CREATE TABLE `pay_order` (
  `id` bigint(20) NOT NULL COMMENT '标识符Id',
  `trade_type` varchar(64) NOT NULL COMMENT '交易类型',
  `trade_id` bigint(20) NOT NULL COMMENT '交易标识符',
  `amount` bigint(20) NOT NULL COMMENT '交易金额',
  `discount` bigint(20) DEFAULT 0 COMMENT '优惠金额',
  `name` varchar(128) NOT NULL COMMENT '交易名称',
  `pay_type` varchar(32) NOT NULL COMMENT '支付方式',
  `third_account` varchar(128) DEFAULT NULL COMMENT '第三方支付账号',
  `state` varchar(32) DEFAULT NULL COMMENT '订单状态',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `trade_file_url` varchar(32) DEFAULT NULL COMMENT '交易凭证url',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除',
  `updated_at` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `created_at` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付订单';

CREATE TABLE `pay_notify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识符Id',
  `trade_no` varchar(64) NOT NULL COMMENT '第三方交易号',
  `out_trade_no` varchar(64) NOT NULL COMMENT '商户订单号',
  `trade_status` varchar(32) NOT NULL COMMENT '交易状态',
  `notify_data` text DEFAULT NULL COMMENT '通知内容',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '是否删除',
  `update_at` bigint(20) DEFAULT NULL COMMENT '更新时间',
  `create_at` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付通知';