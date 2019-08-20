/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : mybatis

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 20/08/2019 14:56:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `parent_id` int(255) NOT NULL,
  `type` int(255) NULL DEFAULT 0,
  `commentator` int(255) NOT NULL,
  `gmt_create` bigint(255) NULL DEFAULT NULL,
  `gmt_modified` bigint(255) NULL DEFAULT NULL,
  `like_count` int(255) NULL DEFAULT 0,
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment_count` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 75 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 15, 1, 15, 1564308050656, 1564308050655, 1, 'this is a reply', 19);
INSERT INTO `comment` VALUES (2, 19, 2, 15, 1564312365469, 1564312365469, 1, '这是第2个评论', 19);
INSERT INTO `comment` VALUES (3, 19, 2, 15, 1564312747242, 1564312747242, 1, '这是第3个评论', 19);
INSERT INTO `comment` VALUES (4, 19, 1, 15, 1564313237478, 1564313237478, 1, '这是第4个评论', 19);
INSERT INTO `comment` VALUES (5, 19, 1, 15, 1564313322348, 1564313322348, 1, '这是第4个评论', NULL);
INSERT INTO `comment` VALUES (6, 19, 1, 15, 1564313529981, 1564313529981, 1, '这是第5个评论', NULL);
INSERT INTO `comment` VALUES (7, 19, 1, 15, 1564313895596, 1564313895596, 1, '这是第6个评论', NULL);
INSERT INTO `comment` VALUES (8, 19, 1, 15, 1564314768183, 1564314768183, 1, '这是第7个评论', 21);
INSERT INTO `comment` VALUES (9, 19, 1, 15, 1564315081557, 1564315081557, 1, '这是第8个评论', NULL);
INSERT INTO `comment` VALUES (10, 19, 1, 15, 1564315567953, 1564315567953, 1, '这是第9个评论', NULL);
INSERT INTO `comment` VALUES (11, 19, 1, 15, 1564315601342, 1564315601342, 1, '这是第9个评论', NULL);
INSERT INTO `comment` VALUES (12, 19, 1, 15, 1564316235803, 1564316235803, 1, '这是第10个评论', NULL);
INSERT INTO `comment` VALUES (13, 19, 1, 15, 1564316849947, 1564316849947, 1, '这是第12个评论', NULL);
INSERT INTO `comment` VALUES (14, 20, 1, 15, 1564317052717, 1564317052717, 1, '这是第20号问题的第一个回复', NULL);
INSERT INTO `comment` VALUES (17, 20, 1, 15, 1564317654732, 1564317654732, 1, '这是第20号问题的第2个回复', NULL);
INSERT INTO `comment` VALUES (18, 19, 1, 15, 1564380492415, 1564380492415, 1, 'Ajax的contentType打错了', NULL);
INSERT INTO `comment` VALUES (19, 19, 1, 15, 1564388742295, 1564388742295, 1, 'test 登录', NULL);
INSERT INTO `comment` VALUES (20, 19, 1, 15, 1564388825183, 1564388825183, 1, 'test 登录', NULL);
INSERT INTO `comment` VALUES (21, 19, 1, 15, 1564391681194, 1564391681194, 1, '测试是否隐藏', NULL);
INSERT INTO `comment` VALUES (22, 19, 1, 15, 1564391705908, 1564391705908, 1, '测试是否隐藏2', NULL);
INSERT INTO `comment` VALUES (23, 19, 1, 15, 1564392257858, 1564392257858, 1, '测试是否隐藏2debugger', NULL);
INSERT INTO `comment` VALUES (24, 19, 1, 15, 1564392388539, 1564392388539, 1, '2debug', NULL);
INSERT INTO `comment` VALUES (25, 19, 1, 15, 1564392483930, 1564392483930, 1, '3debug', NULL);
INSERT INTO `comment` VALUES (26, 19, 1, 15, 1564392847591, 1564392847591, 1, 'charu3', NULL);
INSERT INTO `comment` VALUES (27, 19, 1, 15, 1564393222578, 1564393222578, 1, '123', NULL);
INSERT INTO `comment` VALUES (28, 19, 1, 15, 1564393562846, 1564393562846, 1, '测试hide（）', NULL);
INSERT INTO `comment` VALUES (29, 21, 1, 15, 1564409554314, 1564409554314, 1, '21号的第一个回复', NULL);
INSERT INTO `comment` VALUES (30, 22, 1, 15, 1564409879355, 1564409879355, 1, '22号的第一个回复', NULL);
INSERT INTO `comment` VALUES (31, 20, 1, 15, 1564459019612, 1564459019612, 1, '这是第三个回复', NULL);
INSERT INTO `comment` VALUES (32, 20, 1, 15, 1564459115448, 1564459115448, 1, '这是第四个回复', NULL);
INSERT INTO `comment` VALUES (33, 20, 1, 15, 1564459987535, 1564459987535, 1, '这是测试自动reload', NULL);
INSERT INTO `comment` VALUES (34, 20, 1, 15, 1564460194756, 1564460194756, 1, '上一个测试失败，第二次测试reload！', NULL);
INSERT INTO `comment` VALUES (35, 20, 1, 15, 1564477058647, 1564477058647, 1, '测试封装后的js是否起作用', NULL);
INSERT INTO `comment` VALUES (36, 21, 1, 15, 1564482721153, 1564482721153, 1, '22号的第二个评论', NULL);
INSERT INTO `comment` VALUES (39, 29, 2, 15, 1564489679408, 1564489679408, 0, '123', NULL);
INSERT INTO `comment` VALUES (40, 29, 2, 15, 1564489741041, 1564489741041, 0, '123', NULL);
INSERT INTO `comment` VALUES (41, 29, 2, 15, 1564490236899, 1564490236899, 0, '123', NULL);
INSERT INTO `comment` VALUES (42, 29, 2, 15, 1564490326588, 1564490326588, 0, '321', NULL);
INSERT INTO `comment` VALUES (43, 29, 2, 15, 1564490904917, 1564490904917, 0, '29号问题测试2级评论', NULL);
INSERT INTO `comment` VALUES (44, 29, 2, 15, 1564492416254, 1564492416254, 0, '测试29号问题的2级评论3', NULL);
INSERT INTO `comment` VALUES (45, 29, 2, 15, 1564492824823, 1564492824823, 0, '测试29号问题的2级评论4', NULL);
INSERT INTO `comment` VALUES (46, 29, 2, 15, 1564555518773, 1564555518773, 0, '测试第二次评论是否成功', NULL);
INSERT INTO `comment` VALUES (47, 4, 2, 15, 1564624302537, 1564624302537, 0, '啦啦啦', NULL);
INSERT INTO `comment` VALUES (48, 30, 2, 15, 1564631908560, 1564631908560, 0, '回复一下', NULL);
INSERT INTO `comment` VALUES (55, 19, 1, 15, 1564971579922, 1564971579922, 1, '最新回复', NULL);
INSERT INTO `comment` VALUES (56, 24, 1, 15, 1565586710822, 1565586710822, 1, 'replies功能开发成功', NULL);
INSERT INTO `comment` VALUES (57, 56, 2, 15, 1565586778990, 1565586778990, 0, '二级也能评论', NULL);
INSERT INTO `comment` VALUES (65, 25, 1, 15, 1565776515583, 1565776515583, 0, '插入一个notification', NULL);
INSERT INTO `comment` VALUES (70, 65, 2, 15, 1565779009870, 1565779009870, 0, 'commentFirst.getParentId()', NULL);
INSERT INTO `comment` VALUES (71, 65, 2, 15, 1565779100941, 1565779100941, 0, 'commentFirst.getParentId()', NULL);
INSERT INTO `comment` VALUES (72, 65, 2, 15, 1565779192536, 1565779192536, 0, 'commentFirst.getParentId()3', NULL);
INSERT INTO `comment` VALUES (73, 4, 2, 15, 1565779704012, 1565779704012, 0, 'questionCreate 测试是否是question.creater', NULL);
INSERT INTO `comment` VALUES (74, 4, 2, 15, 1565780275223, 1565780275223, 0, '上一个失败了，看看这个', NULL);
INSERT INTO `comment` VALUES (75, 4, 2, 15, 1565780750634, 1565780750634, 0, '上一个还是失败了，又发现了问题，修正', NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 'aa');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(2) NULL DEFAULT NULL,
  `d_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 's', '123', 2, 4);
INSERT INTO `employee` VALUES (2, 'w', '321', 1, 2);

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `notifier` int(255) NOT NULL,
  `receiver` int(255) NOT NULL,
  `outer_id` int(255) NOT NULL,
  `type` int(255) NOT NULL,
  `gmt_create` bigint(255) NOT NULL,
  `status` int(255) NOT NULL DEFAULT 0,
  `question_id` int(255) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES (1, 15, 15, 19, 0, 1564971579942, 0, 19);
INSERT INTO `notification` VALUES (2, 15, 15, 24, 0, 1565586710900, 0, 24);
INSERT INTO `notification` VALUES (3, 15, 15, 56, 1, 1565586779014, 0, 19);
INSERT INTO `notification` VALUES (4, 15, 15, 25, 0, 1565776520147, 0, 25);
INSERT INTO `notification` VALUES (5, 15, 15, 65, 2, 1565779009886, 0, 25);
INSERT INTO `notification` VALUES (6, 15, 15, 65, 2, 1565779100956, 0, 25);
INSERT INTO `notification` VALUES (7, 15, 15, 65, 2, 1565779192551, 0, 25);
INSERT INTO `notification` VALUES (8, 15, 15, 4, 2, 1565779704025, 0, 25);
INSERT INTO `notification` VALUES (9, 15, 15, 4, 2, 1565780275238, 0, 19);
INSERT INTO `notification` VALUES (10, 15, 15, 4, 2, 1565780750649, 0, 19);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `gmt_create` bigint(255) NULL DEFAULT 0,
  `gmt_modified` bigint(255) NULL DEFAULT 0,
  `creater` int(255) NULL DEFAULT 0,
  `comment_count` int(255) NULL DEFAULT 0,
  `view_count` int(255) NULL DEFAULT 0,
  `like_count` int(255) NULL DEFAULT 0,
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (19, '学习SpringBoot2心得4', 'th:value 在input标签中显示值', 1563956786874, 1564633314613, 15, 2, 292, 0, 'thymeleaf,input');
INSERT INTO `question` VALUES (20, '学习SpringBoot2心得2', '耐心&戒骄戒躁', 1563958543935, 1563958543935, 15, 0, 43, 0, '耐心');
INSERT INTO `question` VALUES (21, '学习SpringBoot2心得3', 'th：class用法：th:class=\"${row.even}? \'even\' : \'odd\'\"\r\nth:class=\"${row.even}? \'alt\'\"', 1563961208324, 1563961208324, 15, 0, 99, 0, 'thymeleaf');
INSERT INTO `question` VALUES (22, '学习SpringBoot2心得5', '怎么学习Sringboot', NULL, NULL, 15, 0, 14, 0, 'Springboot');
INSERT INTO `question` VALUES (23, 'Spring学习笔记-01', '![](/imgs/fly.png)\r\n###自己动手写出注解版Mybatis的mapper\r\n###@getmapper的使用\r\n```java\r\n@GetMapping(\"/profile/{action}\")\r\n    public String profile(@PathVariable(\"action\") String action,\r\n                          Model model,\r\n                          HttpServletRequest request,\r\n                          @RequestParam(name = \"page\",defaultValue = \"1\") Integer page,\r\n                          @RequestParam(name = \"size\",defaultValue = \"5\") Integer size){\r\n\r\n        User user =(User)request.getSession().getAttribute(\"user\");\r\n        if (user==null){\r\n            return \"redirect:/\";\r\n        }\r\n\r\n        if (\"questions\".equals(action)){\r\n            model.addAttribute(\"section\",\"questions\");\r\n            model.addAttribute(\"sectionName\",\"我的提问\");\r\n            PaginationDTO paginationDTO = questionService.listQuestion(user.getId(), page, size);\r\n            model.addAttribute(\"pagination\",paginationDTO);\r\n        }else if (\"replies\".equals(action)){\r\n\r\n            PaginationDTO paginationDTO =notificationService.listReplies(user.getId(), page, size);\r\n            model.addAttribute(\"pagination\",paginationDTO);\r\n            model.addAttribute(\"section\",\"replies\");\r\n            model.addAttribute(\"sectionName\",\"最新回复\");\r\n\r\n        }\r\n        return \"profile\";\r\n    }\r\n}```', 1564931585292, 1565000724974, 15, 0, 10, 0, 'java,SpringBoot');
INSERT INTO `question` VALUES (24, 'springboot学习笔记-02', 'replies功能正在开发中', 1564931675006, 1564931675006, 15, 0, 6, 0, 'java,SpringBoot,tomcat,MySQL,idea');
INSERT INTO `question` VALUES (25, '栈的压入、弹出序列', '####输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）\r\n\r\n我的代码：\r\n```java\r\nimport java.util.Stack;\r\n\r\npublic class Solution {\r\n	public static boolean IsPopOrder(int [] pushA,int [] popA) {\r\n		int index=pushA.length-1;\r\n		int j=1;\r\n		if (pushA==null||popA==null) {\r\n			return false;\r\n		}\r\n	    Stack<Integer> pushStack=new Stack<>();\r\n	    pushStack.push(pushA[0]);\r\n	    for(int i=0;i<popA.length;i++){\r\n			while (popA[i]!=pushStack.peek()) {\r\n				if (j>pushA.length-1) {\r\n					System.out.println(\"false\");\r\n					return false;\r\n				}\r\n				for (; j<pushA.length; j++) {\r\n					pushStack.push(pushA[j]);\r\n					if (pushStack.peek()==popA[i]) {\r\n						j++;\r\n						break;\r\n					}\r\n				}\r\n			}\r\n			pushStack.pop();\r\n	   }\r\n	    System.out.println(\"true\");\r\n	    return true;\r\n    }\r\n	public static void main(String[] args) {\r\n		int[] A=new int[]{1};\r\n		int[] B=new int[]{2};\r\n		IsPopOrder(A, B);\r\n	}\r\n}\r\n```', 1565584868919, 1565584868919, 15, 0, 20, 0, 'java,eclipse');

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `last_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, NULL, NULL);
INSERT INTO `tbl_user` VALUES (2, '123', 'ww');
INSERT INTO `tbl_user` VALUES (4, '123456@qq.com', 'lisa');
INSERT INTO `tbl_user` VALUES (5, '123456@qq.com', 'lisa');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  `token` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` bigint(255) NULL DEFAULT 0,
  `gmt_modified` bigint(255) NULL DEFAULT 0,
  `avatar_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (15, '48831043', 'whathes', 'dd44cc38-05f6-402e-86d9-23677f1096b2', 1563956090420, 1566281551333, 'https://avatars3.githubusercontent.com/u/48831043?v=4');

SET FOREIGN_KEY_CHECKS = 1;
