package com.xu.soufang.base;


import lombok.Data;

/**
 * @author XUZHENQIN 19/03/28
 *
 */
@Data
public class ApiDataTableResponse extends ApiResponse{

     private int draw;

     private long recordsTotal;

     private long recordsFiltered;


     public ApiDataTableResponse(int code,String message,Object data){
          super(code,message,data);
     }

     public ApiDataTableResponse(ApiResponse.Status status){
          this(status.getCode(),status.getStandarMessage(),null);
     }

}
