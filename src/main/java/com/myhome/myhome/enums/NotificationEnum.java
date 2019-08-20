package com.myhome.myhome.enums;

public enum NotificationEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论"),
    ;
    private Integer type;
    private String name;

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String nameOfType(Integer type){
        for(NotificationEnum notificationEnum : NotificationEnum.values()){
            if (notificationEnum.getType()==type){
                return notificationEnum.getName();
            }
        }
        return "";
    }
}
