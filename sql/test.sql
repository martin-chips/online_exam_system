/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.24-0ubuntu0.16.04.1 : Database - my_exam_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_exam_test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `my_exam_test`;

/*Table structure for table `exam` */

DROP TABLE IF EXISTS `exam`;

CREATE TABLE `exam` (
  `exam_id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(128) DEFAULT NULL COMMENT '试卷名',
  `exam_start_date` datetime DEFAULT NULL COMMENT '考试开始时间',
  `exam_last_time` bigint(20) DEFAULT NULL COMMENT '考试持续的时间，单位为分钟',
  `reviewer_id` int(11) DEFAULT NULL COMMENT '审批老师的id',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `score` decimal(10,0) DEFAULT NULL COMMENT '试卷总分',
  PRIMARY KEY (`exam_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='试卷表';

/*Data for the table `exam` */

insert  into `exam`(`exam_id`,`exam_name`,`exam_start_date`,`exam_last_time`,`reviewer_id`,`create_by`,`create_date`,`update_by`,`update_date`,`score`) values 
(11,'Java试卷1号','2019-05-12 00:00:00',60,25,NULL,'2019-05-09 00:06:06',NULL,'2019-05-10 14:28:58',0),
(12,'测试试卷2号','2019-05-12 00:00:00',60,26,NULL,'2019-05-09 01:13:43',NULL,NULL,6),
(13,'测试试卷3','2019-05-09 00:00:00',60,26,NULL,'2019-05-09 01:26:07',NULL,NULL,319);

/*Table structure for table `exam_detail` */

DROP TABLE IF EXISTS `exam_detail`;

CREATE TABLE `exam_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) DEFAULT NULL COMMENT '试卷的id',
  `stu_id` int(11) DEFAULT NULL COMMENT '学生的id',
  `score` decimal(10,0) DEFAULT NULL COMMENT '考试成绩',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试的信息';

/*Data for the table `exam_detail` */

/*Table structure for table `exam_question` */

DROP TABLE IF EXISTS `exam_question`;

CREATE TABLE `exam_question` (
  `eq_id` int(11) NOT NULL AUTO_INCREMENT,
  `exam_id` int(11) DEFAULT NULL COMMENT '试卷的id',
  `question_id` int(11) DEFAULT NULL COMMENT '问题的id',
  PRIMARY KEY (`eq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8 COMMENT='试卷和问题的关联表';

/*Data for the table `exam_question` */

insert  into `exam_question`(`eq_id`,`exam_id`,`question_id`) values 
(222,12,55),
(223,12,56),
(224,13,55),
(225,13,56),
(226,13,29),
(227,13,30),
(228,13,25),
(229,13,22),
(230,13,23),
(231,13,24),
(232,13,31),
(233,13,32),
(234,13,33),
(235,13,34),
(236,13,35),
(237,13,36),
(238,13,37),
(239,13,38),
(240,13,39),
(241,13,40),
(242,13,41),
(243,13,42),
(244,13,43),
(245,13,44),
(246,13,45),
(247,13,46),
(248,13,47),
(249,13,48),
(250,13,49),
(251,13,50),
(252,13,51),
(253,13,52),
(254,13,53),
(255,13,54);

/*Table structure for table `exam_record` */

DROP TABLE IF EXISTS `exam_record`;

CREATE TABLE `exam_record` (
  `exam_id` int(11) NOT NULL,
  `stu_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer` text,
  `final_score` decimal(10,0) DEFAULT '0' COMMENT '最终成绩',
  PRIMARY KEY (`exam_id`,`stu_id`,`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考试记录';

/*Data for the table `exam_record` */

insert  into `exam_record`(`exam_id`,`stu_id`,`question_id`,`answer`,`final_score`) values 
(11,23,45,'123',0),
(11,23,46,'123',0),
(11,23,47,'123',0),
(11,23,48,'123',0),
(11,23,49,'1',3),
(11,23,50,'1',3),
(11,23,51,'1',0),
(11,23,52,'1',0),
(11,23,53,'1',3),
(11,23,54,'1',3),
(12,22,55,'1',3),
(12,22,56,'1',3),
(12,23,55,'1',3),
(12,23,56,'1',3),
(12,24,55,'0',0),
(12,24,56,'1',0),
(13,22,22,'B',0),
(13,22,23,'D',0),
(13,22,24,'C',0),
(13,22,25,'C',0),
(13,22,29,'A',5),
(13,22,30,'B',0),
(13,22,31,'C',0),
(13,22,32,'A',0),
(13,22,33,'B',0),
(13,22,34,'B,C,D',0),
(13,22,35,'B,C,D',0),
(13,22,36,'B,C',0),
(13,22,37,'B,C',0),
(13,22,38,'B,D',0),
(13,22,39,'123',5),
(13,22,40,'123',3),
(13,22,41,'123',0),
(13,22,42,'123',1),
(13,22,43,'123',0),
(13,22,44,'123',2),
(13,22,45,'123',3),
(13,22,46,'123',5),
(13,22,47,'123',0),
(13,22,48,'123',3),
(13,22,49,'0',0),
(13,22,50,'1',3),
(13,22,51,'1',0),
(13,22,52,'1',0),
(13,22,53,'0',0),
(13,22,54,'1',3),
(13,22,55,'1',3),
(13,22,56,'1',3),
(13,24,22,'D',0),
(13,24,23,'B',0),
(13,24,24,'D',0),
(13,24,25,'B',0),
(13,24,29,'A',5),
(13,24,30,'C',0),
(13,24,31,'C',0),
(13,24,32,'C',0),
(13,24,33,'C',0),
(13,24,34,'D',0),
(13,24,35,'D',0),
(13,24,36,'B',0),
(13,24,37,'C',0),
(13,24,38,'',0),
(13,24,39,'11111',0),
(13,24,40,'111',0),
(13,24,41,'1111',0),
(13,24,42,'11111111111111111111111',0),
(13,24,43,'111111111111111111',0),
(13,24,44,'1111111111',0),
(13,24,45,'111111111111111',0),
(13,24,46,'1111111111111111111',0),
(13,24,47,'',0),
(13,24,48,'',0),
(13,24,49,'0',0),
(13,24,50,'0',0),
(13,24,51,'0',0),
(13,24,52,'0',0),
(13,24,53,'0',0),
(13,24,54,'0',0),
(13,24,55,'0',0),
(13,24,56,'0',0);

/*Table structure for table `exam_student` */

DROP TABLE IF EXISTS `exam_student`;

CREATE TABLE `exam_student` (
  `es_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学生的id',
  `exam_id` int(11) DEFAULT NULL COMMENT '试卷的id',
  `status` char(1) DEFAULT '0' COMMENT '状态，0表示还未参加考试，1表示参加考试完成',
  `total_score` decimal(10,0) DEFAULT NULL COMMENT '总分',
  `reading` char(1) DEFAULT '1' COMMENT '1表示正在阅卷中，0表示阅卷完成',
  PRIMARY KEY (`es_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8 COMMENT='试卷和学生的关联表';

/*Data for the table `exam_student` */

insert  into `exam_student`(`es_id`,`student_id`,`exam_id`,`status`,`total_score`,`reading`) values 
(60,22,12,'1',6,'0'),
(61,23,12,'1',6,'0'),
(62,24,12,'0',NULL,'1'),
(63,22,13,'1',39,'0'),
(64,23,13,'0',NULL,'1'),
(65,24,13,'0',NULL,'1'),
(66,22,11,'1',0,'0'),
(67,23,11,'0',NULL,'1'),
(68,24,11,'0',NULL,'1');

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告的id',
  `title` varchar(128) DEFAULT NULL COMMENT '公告的标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '公告的内容',
  `type` char(1) DEFAULT NULL COMMENT '公告的类型：1表示需要弹框提示2表示页面提示',
  `create_by` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='公告管理';

/*Data for the table `notice` */

insert  into `notice`(`notice_id`,`title`,`content`,`type`,`create_by`,`create_date`,`update_by`,`update_date`) values 
(5,'考试通知','即将开始考试，请各位老师在5月12日批阅试卷','2',NULL,'2019-05-09 18:39:17',NULL,'2019-05-09 18:39:42');

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` char(1) DEFAULT NULL COMMENT '问题类型：1表示单选，2表示多选，3表示天空，4表示判断，5表示问答',
  `title` text COMMENT '题干',
  `option_a` varchar(5120) DEFAULT NULL COMMENT 'A选项答案',
  `option_b` varchar(5120) DEFAULT NULL COMMENT 'B选项答案',
  `option_c` varchar(5120) DEFAULT NULL COMMENT 'C选项答案',
  `option_d` varchar(5120) DEFAULT NULL COMMENT 'D选项答案',
  `answer` text COMMENT '答案',
  `analyse` text COMMENT '解析',
  `score` decimal(10,0) DEFAULT NULL COMMENT '该题的分数',
  `create_by` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='问题表';

/*Data for the table `question` */

insert  into `question`(`id`,`type`,`title`,`option_a`,`option_b`,`option_c`,`option_d`,`answer`,`analyse`,`score`,`create_by`,`create_date`,`update_by`,`update_date`) values 
(22,'1','关于sleep()和wait()，以下描述错误的一项是？','sleep是线程类（Thread）的方法，wait是Object类的方法； ','sleep不释放对象锁，wait放弃对象锁； ','sleep暂停线程、但监控状态仍然保持，结束后会自动恢复；','wait后进入等待锁定池，只有针对此对象发出notify方法后获得对象锁进入运行状态。','D','Notify后是进入对象锁定池，准备获得锁，而不是立即获得。',5,NULL,'2019-05-08 23:39:53',NULL,NULL),
(23,'1','提供Java存取数据库能力的包是？','java.sql ','java.awt ','java.lang ','java.swing','A',NULL,5,NULL,'2019-05-08 23:40:28',NULL,NULL),
(24,'1','方法resume()负责恢复哪些线程的执行？','通过调用stop()方法而停止的线程。 ','通过调用sleep()方法而停止的线程。','通过调用wait()方法而停止的线程。 ','通过调用suspend()方法而停止的线程。','D',NULL,5,NULL,'2019-05-08 23:41:03',NULL,NULL),
(25,'1','Java I/O程序设计中，下列描述正确的是','OutputStream用于写操作 ','InputStream用于写操作 ','I/O库不支持对文件可读可写API','以上都对','A',NULL,5,NULL,'2019-05-08 23:41:54',NULL,NULL),
(29,'1','分析选项中关于Java中this关键字的说法正确的是','this关键字是在对象内部指代自身的引用 ',' this关键字可以在类中的任何位置使用 ','this关键字和类关联，而不是和特定的对象关联 ','同一个类的不同对象共用一个this','A',NULL,5,NULL,'2019-05-08 23:44:20',NULL,NULL),
(30,'1','在JAVA中，LinkedList类和ArrayList类同属于集合框架类，下列（ ）选项中的方法是LinkedList类有而ArrayList类没有的。',' add(Object o) ',' add(int index，Object o) ',' remove(Object o) ','removeLast()','D',NULL,5,NULL,'2019-05-08 23:46:28',NULL,NULL),
(31,'1','在JAVA中ArrayList类实现了可变大小的数组，便于遍历元素和随机访问元素，已知 获得了ArrayList类的对象bookTypeList，则下列语句中能够实现判断列表中是否存在字符串“小说”的','基本数据类型和String相加结果一定是字符串型 ','char类型和int类型相加结果一定是字符 ','double类型可以自动转换为int ',' char + int + double +”” 结果一定是double','A',NULL,5,NULL,'2019-05-08 23:47:20',NULL,NULL),
(32,'1','对象的特征在类中表示为变量，称为类的','对象 ','属性','方法 ','数据类型','B',NULL,5,NULL,'2019-05-08 23:47:58',NULL,'2019-05-08 23:48:24'),
(33,'1','在Java中,关于构造方法，下列说法错误的是','构造方法的名称必须与类名相同 ',' 构造方法可以带参数 ','构造方法不可以重载 ','构造方法绝对不能有返回值','C',NULL,5,NULL,'2019-05-08 23:48:52',NULL,NULL),
(34,'2','下面哪几个函数是public void example(){…}的重载函数（  ），选两项','public void example( int m){…}','public int example(){…}','public void example2(){…}','public int example ( int m, float f){…}','A,D,','方法名一定相同，参数签名（参数数量，类型，顺序）一定不同。',10,NULL,'2019-05-08 23:49:38',NULL,NULL),
(35,'2','已知如下定义：String s = “story”; 下面哪个表达式是合法的（ ），选两项',' s += “books”','char c = s[1];','int len = s.length;','String t = s.toLowerCase();','A,D,','String类型没有length属性，这个专属于数组。',10,NULL,'2019-05-08 23:51:36',NULL,NULL),
(36,'2',' 如下哪些字串是Java中的标识符（ ），选两项','fieldname','super','#number','$number','A,C,','标识符以￥$_或者字母开头，不能用关键字。',10,NULL,'2019-05-08 23:52:12',NULL,NULL),
(37,'2','如下哪些是Java中有效的关键字（ ）',' const','false','this','native','A,B,C,D,',NULL,10,NULL,'2019-05-08 23:52:58',NULL,NULL),
(38,'2','如下哪些是Java中正确的整数表示','22','0x22','022','22H','A,B,C,','从上到下依次十进制，八进制，十六进制。',10,NULL,'2019-05-08 23:53:40',NULL,NULL),
(39,'5','父类的静态方法能否被子类重写',NULL,NULL,NULL,NULL,'不能。重写只适用于实例方法,不能用于静态方法，而子类当中含有和父类相同签名的静态方法，我们一般称之为隐藏。',NULL,20,NULL,'2019-05-08 23:55:26',NULL,NULL),
(40,'5','java 创建对象的几种方式',NULL,NULL,NULL,NULL,'采用new\n\n通过反射\n\n采用clone\n\n通过序列化机制',NULL,20,NULL,'2019-05-08 23:55:56',NULL,NULL),
(41,'5','String s1=”ab”, String s2=”a”+”b”, String s3=”a”, String s4=”b”, s5=s3+s4请问s5==s2返回什么？',NULL,NULL,NULL,NULL,'返回false。在编译过程中，编译器会将s2直接优化为”ab”，会将其放置在常量池当中，s5则是被创建在堆区，相当于s5=new String(“ab”);',NULL,20,NULL,'2019-05-08 23:56:05',NULL,NULL),
(42,'5','java当中的四种引用',NULL,NULL,NULL,NULL,'强引用，软引用，弱引用，虚引用。不同的引用类型主要体现在GC上:\n\n强引用：如果一个对象具有强引用，它就不会被垃圾回收器回收。即使当前内存空间不足，JVM也不会回收它，而是抛出 OutOfMemoryError 错误，使程序异常终止。如果想中断强引用和某个对象之间的关联，可以显式地将引用赋值为null，这样一来的话，JVM在合适的时间就会回收该对象。\n\n软引用：在使用软引用时，如果内存的空间足够，软引用就能继续被使用，而不会被垃圾回收器回收，只有在内存不足时，软引用才会被垃圾回收器回收。\n\n弱引用：具有弱引用的对象拥有的生命周期更短暂。因为当 JVM 进行垃圾回收，一旦发现弱引用对象，无论当前内存空间是否充足，都会将弱引用回收。不过由于垃圾回收器是一个优先级较低的线程，所以并不一定能迅速发现弱引用对象。\n\n虚引用：顾名思义，就是形同虚设，如果一个对象仅持有虚引用，那么它相当于没有引用，在任何时候都可能被垃圾回收器回收。\n\n ',NULL,20,NULL,'2019-05-08 23:56:21',NULL,NULL),
(43,'5','有没有可能两个不相等的对象有相同的hashcode',NULL,NULL,NULL,NULL,'有可能，两个不相等的对象可能会有相同的 hashcode 值，这就是为什么在 hashmap 中会有冲突。如果两个对象相等，必须有相同的hashcode 值，反之不成立。',NULL,20,NULL,'2019-05-08 23:56:40',NULL,NULL),
(44,'5','内部类的作用',NULL,NULL,NULL,NULL,'内部类可以有多个实例，每个实例都有自己的状态信息，并且与其他外围对象的信息相互独立.在单个外围类当中，可以让多个内部类以不同的方式实现同一接口，或者继承同一个类.创建内部类对象的时刻不依赖于外部类对象的创建。内部类并没有令人疑惑的”is-a”管系，它就像是一个独立的实体。\n\n内部类提供了更好的封装，除了该外围类，其他类都不能访问。',NULL,20,NULL,'2019-05-08 23:56:56',NULL,NULL),
(45,'5','final有哪些用法',NULL,NULL,NULL,NULL,'final也是很多面试喜欢问的地方，能回答下以下三点就不错了：\n1.被final修饰的类不可以被继承 \n2.被final修饰的方法不可以被重写 \n3.被final修饰的变量不可以被改变。如果修饰引用，那么表示引用不可变，引用指向的内容可变。\n4.被final修饰的方法，JVM会尝试将其内联，以提高运行效率 \n5.被final修饰的常量，在编译阶段会存入常量池中。\n\n回答出编译器对final域要遵守的两个重排序规则更好：\n1.在构造函数内对一个final域的写入，与随后把这个被构造对象的引用赋值给一个引用变量,这两个操作之间不能重排序。\n2.初次读一个包含final域的对象的引用，与随后初次读这个final域,这两个操作之间不能重排序。',NULL,20,NULL,'2019-05-08 23:57:11',NULL,NULL),
(46,'5','64位的JVM当中,int的长度是多少?',NULL,NULL,NULL,NULL,'Java 中，int 类型变量的长度是一个固定值，与平台无关，都是 32 位。意思就是说，在 32 位 和 64 位 的Java 虚拟机中，int 类型的长度是相同的。',NULL,20,NULL,'2019-05-08 23:57:26',NULL,NULL),
(47,'5','String和StringBuffer',NULL,NULL,NULL,NULL,'String和StringBuffer主要区别是性能：String是不可变对象，每次对String类型进行操作都等同于产生了一个新的String对象，然后指向新的String对象。所以尽量不在对String进行大量的拼接操作，否则会产生很多临时对象，导致GC开始工作，影响系统性能。\n\nStringBuffer是对对象本身操作，而不是产生新的对象，因此在有大量拼接的情况下，我们建议使用StringBuffer。\n\n但是需要注意现在JVM会对String拼接做一定的优化：\nString s=“This is only ”+”simple”+”test”会被虚拟机直接优化成String s=“This is only simple test”，此时就不存在拼接过程。',NULL,20,NULL,'2019-05-08 23:57:40',NULL,NULL),
(48,'5','你知道哪些垃圾回收算法?',NULL,NULL,NULL,NULL,'垃圾回收从理论上非常容易理解,具体的方法有以下几种: \n1. 标记-清除 \n2. 标记-复制 \n3. 标记-整理 \n4. 分代回收 ',NULL,20,NULL,'2019-05-08 23:58:01',NULL,NULL),
(49,'4','覆盖的同名方法中，子类方法不能比父类方法的访问权限低',NULL,NULL,NULL,NULL,'1',NULL,3,NULL,'2019-05-08 23:58:51',NULL,NULL),
(50,'4','接口是特殊的类，所以接口也可以继承，子接口将继承父接口的所有常量和抽象方法。',NULL,NULL,NULL,NULL,'1',NULL,3,NULL,'2019-05-08 23:59:05',NULL,NULL),
(51,'4','Java支持多重继承。',NULL,NULL,NULL,NULL,'0',NULL,3,NULL,'2019-05-08 23:59:16',NULL,NULL),
(52,'4','抽象方法没有方法体。',NULL,NULL,NULL,NULL,'0',NULL,3,NULL,'2019-05-08 23:59:28',NULL,NULL),
(53,'4','一个Java类可以有一个直接父类，并可以实现多个接口。',NULL,NULL,NULL,NULL,'1',NULL,3,NULL,'2019-05-08 23:59:44',NULL,NULL),
(54,'4','final类中的属性和方法都必须是final的。',NULL,NULL,NULL,NULL,'1',NULL,3,NULL,'2019-05-08 23:59:58',NULL,NULL),
(55,'4','一个类中含有几个构造方法，称为构造方法的重载。对于重载的方法，其参数列表可以相同。',NULL,NULL,NULL,NULL,'1',NULL,3,NULL,'2019-05-09 00:00:13',NULL,NULL),
(56,'4','成员变量的值会因为对象的不同而不同。',NULL,NULL,NULL,NULL,'1',NULL,3,NULL,'2019-05-09 00:00:29',NULL,NULL);

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '请求类型',
  `title` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '日志标题',
  `remote_addr` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作IP地址',
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '操作用户昵称',
  `request_uri` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '请求URI',
  `http_method` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '操作方式',
  `class_method` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '请求类型.方法',
  `data` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '操作提交的数据',
  `session_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'sessionId',
  `response` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '返回内容',
  `use_time` bigint(11) DEFAULT NULL COMMENT '方法执行时间',
  `browser` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '浏览器信息',
  `area` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '地区',
  `province` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '省',
  `city` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '市',
  `isp` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '网络服务提供商',
  `exception` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '异常信息',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=276 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`type`,`title`,`remote_addr`,`username`,`request_uri`,`http_method`,`class_method`,`data`,`session_id`,`response`,`use_time`,`browser`,`area`,`province`,`city`,`isp`,`exception`,`create_by`,`create_date`,`update_by`,`update_date`,`remark`,`del_flag`) values 
(245,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','5CAEB9A5D87F7BF38A71D805559AA017','\"index\"',3116,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 20:11:04',NULL,NULL,NULL,1),
(246,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','5CAEB9A5D87F7BF38A71D805559AA017','\"index\"',2978,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 20:11:28',NULL,NULL,NULL,1),
(247,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','46DC0FA83818FCE8BACAFDAB7FB2D784','\"index\"',346,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:11:28',NULL,NULL,NULL,1),
(248,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',374,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:13:35',NULL,NULL,NULL,1),
(249,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:16:40',NULL,NULL,NULL,1),
(250,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:20:46',NULL,NULL,NULL,1),
(251,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:21:24',NULL,NULL,NULL,1),
(252,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:21:38',NULL,NULL,NULL,1),
(253,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:30:33',NULL,NULL,NULL,1),
(254,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',1,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:34:53',NULL,NULL,NULL,1),
(255,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:35:07',NULL,NULL,NULL,1),
(256,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','77234EF345D3E4EAB1E47CC4F5E636C0','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:39:04',NULL,NULL,NULL,1),
(257,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','0867AC5AAC20935470EC1C142FC06434','\"index\"',351,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:44:32',NULL,NULL,NULL,1),
(258,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','0867AC5AAC20935470EC1C142FC06434','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:53:58',NULL,NULL,NULL,1),
(259,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','0867AC5AAC20935470EC1C142FC06434','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:53:58',NULL,NULL,NULL,1),
(260,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','A80444B3D83F9C74364699C205F9C0F3','\"index\"',364,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:55:42',NULL,NULL,NULL,1),
(261,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','717D53CBC56E6E928F22773B0CF13138','\"index\"',362,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:57:28',NULL,NULL,NULL,1),
(262,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','717D53CBC56E6E928F22773B0CF13138','\"index\"',1,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:57:47',NULL,NULL,NULL,1),
(263,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','717D53CBC56E6E928F22773B0CF13138','\"index\"',NULL,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:59:05',NULL,NULL,NULL,1),
(264,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','717D53CBC56E6E928F22773B0CF13138','\"index\"',1,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 22:59:10',NULL,NULL,NULL,1),
(265,'Ajax请求','登录系统','127.0.0.1','超级管理员','http://localhost:8080/login','POST','com.dimple.controller.LoginController.login','[\"1\",\"test\",\"true\",\"org.apache.shiro.web.servlet.ShiroHttpServletRequest@270f5b56\"]','B0A82583066CCEDAC2EC985B48E3B8B9','{\"data\":{\"url\":\"index\"},\"success\":true,\"message\":\"登录成功\"}',2370,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 23:04:15',NULL,NULL,NULL,1),
(266,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','B0A82583066CCEDAC2EC985B48E3B8B9','\"index\"',14,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 23:04:15',NULL,NULL,NULL,1),
(267,'Ajax请求','删除日志','127.0.0.1','超级管理员','http://localhost:8080/system/log','DELETE','com.dimple.controller.SysLogController.delete','[\"257,258,259,260,261,262,263,264,265,266\"]','B0A82583066CCEDAC2EC985B48E3B8B9','{\"msg\":\"操作成功\",\"code\":0}',3886,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 23:04:31',NULL,NULL,NULL,1),
(268,'Ajax请求','删除日志','127.0.0.1','超级管理员','http://localhost:8080/system/log','DELETE','com.dimple.controller.SysLogController.delete','[\"267\"]','B0A82583066CCEDAC2EC985B48E3B8B9','{\"msg\":\"操作成功\",\"code\":0}',190,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 23:18:19',NULL,NULL,NULL,1),
(269,'Ajax请求','删除日志','127.0.0.1','超级管理员','http://localhost:8080/system/log','DELETE','com.dimple.controller.SysLogController.delete','[\"268\"]','B0A82583066CCEDAC2EC985B48E3B8B9','{\"msg\":\"操作成功\",\"code\":0}',141,'Windows-Chrome-74.0.3729.108',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-10 23:18:23',NULL,NULL,NULL,NULL),
(270,'Ajax请求','登录系统','127.0.0.1','超级管理员','http://localhost:8080/login','POST','com.dimple.controller.LoginController.login','[\"1\",\"test\",\"true\",\"org.apache.shiro.web.servlet.ShiroHttpServletRequest@6598cf35\"]','B11278A93F69E1AF4EEFA09EF4DA0B02','{\"data\":{\"url\":\"index\"},\"success\":true,\"message\":\"登录成功\"}',2385,'Windows-Chrome-74.0.3729.131',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-12 00:11:06',NULL,NULL,NULL,NULL),
(271,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','B11278A93F69E1AF4EEFA09EF4DA0B02','\"index\"',11,'Windows-Chrome-74.0.3729.131',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-12 00:11:06',NULL,NULL,NULL,NULL),
(272,'普通请求','','127.0.0.1','超级管理员','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','B11278A93F69E1AF4EEFA09EF4DA0B02','\"index\"',1,'Windows-Chrome-74.0.3729.131',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-12 00:11:34',NULL,NULL,NULL,NULL),
(273,'Ajax请求','登录系统','127.0.0.1','测试学生3号','http://localhost:8080/login','POST','com.dimple.controller.LoginController.login','[\"123456\",\"student3\",\"true\",\"org.apache.shiro.web.servlet.ShiroHttpServletRequest@3e50bd9b\"]','00A4C23A8618BA7CC46B113BE5EA1D0D','{\"data\":{\"url\":\"index\"},\"success\":true,\"message\":\"登录成功\"}',199,'Windows-Chrome-74.0.3729.131',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-12 00:14:21',NULL,NULL,NULL,NULL),
(274,'普通请求','','127.0.0.1','测试学生3号','http://localhost:8080/index','GET','com.dimple.controller.IndexController.index','[]','00A4C23A8618BA7CC46B113BE5EA1D0D','\"index\"',NULL,'Windows-Chrome-74.0.3729.131',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-12 00:14:21',NULL,NULL,NULL,NULL),
(275,'普通请求','开始考试','127.0.0.1','测试学生3号','http://localhost:8080/exam/student/13','GET','com.dimple.controller.StudentExamController.startExam','[13,{}]','00A4C23A8618BA7CC46B113BE5EA1D0D','\"exam/makeExam\"',6384,'Windows-Chrome-74.0.3729.131',NULL,NULL,NULL,NULL,NULL,NULL,'2019-05-12 00:14:34',NULL,NULL,NULL,NULL);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(36) DEFAULT NULL COMMENT '登录名',
  `nick_name` varchar(40) DEFAULT NULL COMMENT '昵称',
  `icon` varchar(2000) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `salt` varchar(40) DEFAULT NULL COMMENT 'shiro加密盐',
  `tel` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱地址',
  `locked` tinyint(2) DEFAULT NULL COMMENT '是否锁定',
  `create_date` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT NULL,
  `user_type` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`login_name`,`nick_name`,`icon`,`password`,`salt`,`tel`,`email`,`locked`,`create_date`,`create_by`,`update_date`,`update_by`,`remark`,`del_flag`,`user_type`) values 
(1,'test','超级管理员','https://static.mysiteforme.com/3c5b69f4-2e39-4f88-b302-a6eb48e4c43a.jpg','810339f5426fe2dcaf92c5cac718acc6471a034b','3fb62b5aeede1bbf','13699058124','b@qq.com',0,'2017-11-27 22:19:39',1,'2019-05-04 14:26:56',1,'',0,'1'),
(22,'student1','测试学生1号',NULL,'7c3180072403723fa1e113ebb4bc0ba60da99dc8','6fd6764aacc60a45','13699058124','test@test.com',NULL,'2019-05-09 00:01:13',1,'2019-05-09 00:01:57',NULL,NULL,NULL,'3'),
(23,'student2','测试学生2号',NULL,'c4429996c153fb21ab84dd5479d97944139283f0','6e2768cb76c1d32b','13699058124','test@test.com',NULL,'2019-05-09 00:01:48',1,NULL,NULL,NULL,NULL,'3'),
(24,'student3','测试学生3号',NULL,'dff7b08a6322affdcc93f87acc5735e956ac59f8','58892915c95376dd','13699058124','test@test.com',NULL,'2019-05-09 00:02:25',1,NULL,NULL,NULL,NULL,'3'),
(25,'teacher1','测试教师1号',NULL,'7fdec6502fdfca5612c2fb4be2c912e540513146','61893829523e58b7','13699058124','test@test.com',NULL,'2019-05-09 00:02:51',1,NULL,NULL,NULL,NULL,'2'),
(26,'teacher2','测试教师2号',NULL,'5a7a8ada80488f7bea72e02170c4326f6c39ea23','b82517efc4f7241c','13699058124','test@test.com',NULL,'2019-05-09 00:03:18',1,'2019-05-09 00:03:28',NULL,NULL,NULL,'2'),
(27,'teacher3','测试教师3号',NULL,'2fd9a93a91e905979b629c230e8c508006230c26','689e881f3d45e06e','13699058124','test@test.com',NULL,'2019-05-09 00:04:02',1,NULL,NULL,NULL,NULL,'2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
