package indi.xm.exception;

import indi.xm.utils.XMJSONResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.exception
 * @ClassName: CustomExceptionHandler
 * @Author: albert.fang
 * @Description: 自定义的异常助手类
 * @Date: 2021/10/18 17:42
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 上传文件超过500k,捕获异常
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public XMJSONResult handlerMaxUploadFile(MaxUploadSizeExceededException e){
        return XMJSONResult.errorMsg("文件上传大小不能超过500k，请压缩图片");
    }

}
