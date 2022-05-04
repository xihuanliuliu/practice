package com.jd.edi.exception;

import com.jd.edi.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
// 针对Controller层进行处理
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalException {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());

        if (ex.getMessage().contains("Duplicate entry")) {
            String[] strings = ex.getMessage().split(" ");
            String msg = "用户[" + strings[2] + "]已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }

    @ExceptionHandler(CategoryException.class)
    public R<String> categoryExceptionHandler(CategoryException ex) {
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<String> exceptionsHandler(Exception ex) {
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }

}
