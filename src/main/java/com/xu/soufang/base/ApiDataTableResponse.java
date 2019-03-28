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

     public ApiDataTableResponse(int code, String message, Object date, int draw, long recordsTotal, long recordsFiltered) {
          super(code, message, date);
          this.draw = draw;
          this.recordsTotal = recordsTotal;
          this.recordsFiltered = recordsFiltered;
     }


}
