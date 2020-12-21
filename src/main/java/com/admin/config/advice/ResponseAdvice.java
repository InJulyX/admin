package com.admin.config.advice;

//@RestControllerAdvice(basePackages = "com.admin")
//public class ResponseAdvice implements ResponseBodyAdvice {
//    @Override
//    public boolean supports(MethodParameter methodParameter, Class aClass) {
//        return true;
//    }
//
//    @Override
//    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
//        if (Objects.isNull(o)) {
//            return BaseResult.builder().msg("success").build();
//        }
//        if (o instanceof BaseResult) {
//
//            return o;
//        }
//
//        return BaseResult.builder().code(200).msg("请求成功").data(o).build();
//    }
//}
