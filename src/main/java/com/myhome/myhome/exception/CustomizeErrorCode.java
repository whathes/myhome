package com.myhome.myhome.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode{


    QUESTION_NOT_FOUND(2001,"该问题不存在or已被删除!"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中问题或评论！"),
    NO_LOGIN(2003,"未登录，不能评论！"),


    COMMENT_NOT_FOUND(2004,"评论未找到，请重试！" ),
    SYS_ERROR(2005,"系统错误，请重试！" ),
    COMMENT_IS_EMPTY(2006,"回复不能为空！请重试！" )
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {

        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
