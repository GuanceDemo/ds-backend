
CREATE DATABASE ds;

USE ds;

CREATE TABLE `userinfo` (

  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  password VARCHAR(20) DEFAULT NULL,
  role VARCHAR(6) NOT NULL DEFAULT 'USER' COMMENT '用户角色：ADMIN - 管理员，USER - 普通用户',
  valid BOOL NOT NULL DEFAULT TRUE,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  KEY idx_name_role(`name`, `role`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户信息表';


INSERT INTO userinfo(name, password, role) VALUES('admin', 'admin', 'ADMIN');
INSERT INTO userinfo(name, password) VALUES('guest', 'guest');
INSERT INTO userinfo(name, password) VALUES('qcbm', 'qcbm');


CREATE TABLE `bookinfo` (

  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  isbn BIGINT NOT NULL,
  title VARCHAR(100) NOT NULL,
  author VARCHAR(50) NOT NULL,
  press VARCHAR(50) NOT NULL,
  publish_date DATE NOT NULL,
  price FLOAT,
  brief_intro VARCHAR(500) NOT NULL comment '摘要',
  img_url VARCHAR(500) DEFAULT NULL,

  UNIQUE KEY idx_isbn(`isbn`),
  KEY idx_title(`title`(10))

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '图书信息表';

insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787121349959, 'Kubernetes in Action 中文版', '[美] Marko Luksa（马尔科卢克沙）', '电子工业出版社', '2018-12-01', 71.80, '本书主要讲解如何在 Kubernetes 中部署分布式容器应用。本书开始部分概要介绍了 Docker 和Kubernetes 的由来和发展，然后通过在 Kubernetes 中部署一个应用程序，一点点增加功能，逐步加深我们对于Kubernetes架构的理解和操作的实践。在本书的后面部分，也可以学习一些高阶的主题，比如监控、调试及伸缩。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787121362354, 'Kubernetes 权威指南：从Docker到Kubernetes实践全接触（第4版）', '龚正，吴治辉，崔秀龙，闫健勇 著', '电子工业出版社', '2019-06-01', 84, '本书阐述了Kubernetes的基本概念、实践指南、核心原理、开发指导、运维指南、新特性演进等内容；并围绕在生产环境中可能出现的问题，给出了大量的典型案例，比如安全配置方案、网络方案、共享存储方案、高可用方案及Trouble Shooting技巧等，有很强的实战指导意义。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787111608523, 'Docker技术入门与实战（第3版）', '杨保华，戴王剑，曹亚仑 著', '机械工业出版社', '2018-09-01', 69, '本书从Docker基本原理开始，深入浅出地讲解Docker的构建与操作，内容系统全面，可帮助开发人员、运维人员快速部署Docker应用。本书分为四大部分：基础入门、实战案例、进阶技能、开源项目。第3版根据 Docker 18.x 系列版本对全书内容进行了全面修订。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787121303067, 'Docker in Action 中文版', '[美] Jeff Nickoloff（杰夫·尼克罗夫） 著，胡震 译', '电子工业出版社', '2016-11-01', 37.8, '自从2013年3月Docker 0.1版本发布以来，以其为代表的容器技术发展也走上了快车道，Docker容器在很大程度上改变了软件的架构设计、开发和运维部署方式。本书适用于互联网、云计算及企业级软件开发、架构、测试及运维人员快速上手熟悉Docker容器，搭建以Docker为核心的基础设施，并在生产环境中快速部署应用以及管理容器集群。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787115402844, 'Redis 实战', '[美] 约西亚 L.卡尔森（Josiah，L.，Carlson） 著，黄健宏 译', '人民邮电出版社', '2015-11-01', 57.8, '本书对Redis本身以及它的键值对模型进行了介绍，读者将接触到包括缓存、分布式广告定向等实际使用案例，学到如何从小型的作业任务开始，扩展Redis以适应大规模的数据集，以及如何与其他传统的关系数据库或是其他NoSQL存储系统进行集成。有经验的开发者应该会对集群和服务器脚本编程等较为深入的内容感兴趣。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787121198854, '高性能MySQL（第3版）', 'Baron Schwartz，Peter Zaitsev，Vadim Tkachenko 著', '电子工业出版社', '2013-04-01', 62.7, '《高性能MySQL（第3版）》是MySQL 领域的经典之作，拥有广泛的影响力。第3 版更新了大量的内容，不但涵盖了MySQL5.5版本的新特性，也讲述了关于固态盘、高可扩展性设计和云计算环境下的数据库相关的新内容，原有的基准测试和性能优化部分也做了大量的扩展和补充。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787508353944, 'O''Reilly：深入理解LINUX内核（第3版）', '[美] 博韦，西斯特 著，陈莉君，张琼声，张宏伟 译', '电子工业出版社', '2018-01-01', 49, '为了透彻理解Linux的工作机理，以及为何它在各种系统上能顺畅运行，你需要深入到内核的心脏。cPu与外部世界的所有交互活动都是由内核处理的，哪些程序会分享处理器的时间，以什么样的顺序来分享。内核不遗余力地管理有限的内存，以使数以千计的进程有效地共享系统资源。内核还精心组织数据传送，使得cPu不再受限于慢速硬盘。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787115516756, 'UNIX环境高级编程 第3版', '[美] 理查德·史蒂文斯 史蒂芬 拉戈 著，戚正伟，张亚英，尤晋元 译', '人民邮电出版社', '2019-10-01', 116.7, '书中除了介绍UNIX文件和目录、标准I/O库、系统数据文件和信息、进程环境、进程控制、进程关系、信号、线程、线程控制、守护进程、各种I/O、进程间通信、网络IPC、伪终端等方面的内容，还在此基础上介绍了众多应用实例，包括如何创建数据库函数库以及如何与网络打印机通信等。此外，还在附录中给出了函数原型和部分习题的答案。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787115469380, 'Kafka技术内幕', '郑奇煌 著', '人民邮电出版社', '2017-11-01', 89.6, 'Kafka自LinkedIn开源以来就以高性能、高吞吐量、分布式的特性著称，本书以0.10版本的源码为基础，深入分析了Kafka的设计与实现，包括生产者和消费者的消息处理流程，新旧消费者不同的设计方式，存储层的实现，协调者和控制器如何确保Kafka集群的分布式和容错特性，两种同步集群工具MirrorMaker和uReplicator，流处理的两种API以及Kafka的一些高级特性等。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787115551382, 'React全家桶 前端开发与实例详解', '[美] 安东尼·阿科马佐（Anthony Accomazzo） 著，欧阳奖 译', '人民邮电出版社', '2021-01-01', 116.70, 'web开发人员需要考虑使用不同的代码解决浏览器兼容性问题。React改变了这种局面，它不仅可以帮你为用户创建良好的应用程序，而且还可以让你成为一名更出色的开发人员。本书介绍了React的整个生态系统，包括React核心库和许多工具。读完本书后，你和你的团队将拥有构建可靠且功能强大的React应用程序所需的一切知识。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787115335500, '深入浅出Node.js', '朴灵 著', '人民邮电出版社', '2013-12-01', 54.50, '本书从不同的视角介绍了 Node 内在的特点和结构。由首章Node 介绍为索引，涉及Node 的各个方面，主要内容包含模块机制的揭示、异步I/O 实现原理的展现、异步编程的探讨、内存控制的介绍、二进制数据Buffer 的细节、Node 中的网络编程基础、Node 中的Web 开发、进程间的消息传递、Node 测试以及通过Node 构建产品需要的注意事项。附录介绍了Node 的安装、调试、编码规范和NPM 仓库等事宜。');
insert into bookinfo(isbn, title, author, press, publish_date, price, brief_intro) values(9787121215186, 'Nginx高性能Web服务器详解', '苗泽 著', '电子工业出版社', '2013-10-01', 28.90, '全书一共分为四大部分，分别从入门、功能、实现和应用等四个方面对Nginx服务器的知识进行完整阐述，从而满足广大读者在应用Nginx服务器时的普遍性需求。同时也深入剖析了Nginx服务器的工作原理和实现技术，对其中使用到的数据结构和方法进行了详细阐述，并且结合实际的应用情况给出了多个基于Nginx服务器，同时还部署有其他典型服务器的分布式网站架构部署配置。');


CREATE TABLE `orders` (

  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  user_name VARCHAR(20) NOT NULL,
  isbn BIGINT NOT NULL,
  book_title VARCHAR(50) NOT NULL,
  author VARCHAR(50) NOT NULL,
  price FLOAT,
  purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  key idx_user_id (`user_id`, `isbn`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单表';

CREATE TABLE `favorites` (

  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  isbn BIGINT NOT NULL,

  key idx_user_id (`user_id`, `isbn`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '收藏夹表';