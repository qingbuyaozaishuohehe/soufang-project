package com.xu.soufang.base;


import lombok.Data;

/**
 * ApiResponse
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
@Data
public class ApiResponse {
    private int code;

    private String message;

    private Object date;

    private boolean more;

    public ApiResponse(int code, String message, Object date) {
        this.code = code;
        this.message = message;
        this.date = date;
    }

    public ApiResponse() {
        this.code = Status.SUCCESS.getCode();
        this.message = Status.SUCCESS.getStandarMessage();
    }

    public static ApiResponse ofMessage(int code,String message){
        return new ApiResponse(code,message,null);
    }

    public static ApiResponse ofSuccess(Object date){
        return new ApiResponse(Status.SUCCESS.getCode(),Status.SUCCESS.getStandarMessage(),date);
    }

    public static ApiResponse ofStatus(Status status){
        return new ApiResponse(status.getCode(),status.getStandarMessage(),null);

    }

    public enum Status{
        /**
         * 状态码 成功
         */
        SUCCESS(200,"ok"),

        BAD_REQUEST(400,"Bad Request"),

        INTERNAL_SERVER_ERROR(500,"Unknow Internal Error"),

        NOT_VALID_PARAM(40005,"Not valid Params"),

        NOT_SUPPORTED_OPERATION(40006,"Operation not supported"),

        NOT_LOGIN(50000,"Not Login");



        private int code;
        private String standarMessage;

        Status(int code, String standarMessage) {
            this.code = code;
            this.standarMessage = standarMessage;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getStandarMessage() {
            return standarMessage;
        }

        public void setStandarMessage(String standarMessage) {
            this.standarMessage = standarMessage;
        }
    }


}
