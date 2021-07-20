package com.company.crm.commons.domain;

/**
 * @author conrad
 * @date 2021/06/26
 */
public class ResponseObject {

    private String code;

    private String message;

    private Object retData;

    public ResponseObject(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseObject(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.retData = data;
    }

    public static ResponseObject success(){
        return new ResponseObject("1", "success");
    }

    public static ResponseObject error(String msg){
        return new ResponseObject("0", msg);
    }

    public static ResponseObject success(Object data){
        return new ResponseObject("1", "success",data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }
}
