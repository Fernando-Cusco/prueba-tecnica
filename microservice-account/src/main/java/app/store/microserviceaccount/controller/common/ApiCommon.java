package app.store.microserviceaccount.controller.common;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiCommon {

    protected void logRequest(HttpServletRequest request) {
        log.info("### Request URL: {}", request.getRequestURL());
        log.info("### Request URI: {}", request.getRequestURI());
        log.info("### Request Method: {}", request.getMethod());
        log.info("### Request Protocol: {}", request.getProtocol());
        log.info("### Request Remote Host: {}", request.getRemoteHost());
        log.info("### Request Remote Port: {}", request.getRemotePort());
        log.info("### Request Local Address: {}", request.getLocalAddr());
        log.info("### Request Local Name: {}", request.getLocalName());
        log.info("### Request Local Port: {}", request.getLocalPort());
        log.info("### Request Context Path: {}", request.getContextPath());
        log.info("### Request Servlet Path: {}", request.getServletPath());
        log.info("### Request Query String: {}", request.getQueryString());
        log.info("### Request Path Info: {}", request.getPathInfo());
        log.info("### Request Content Type: {}", request.getContentType());
        log.info("### Request Content Length: {}", request.getContentLength());
        log.info("### Request Character Encoding: {}", request.getCharacterEncoding());
        log.info("### Request Server Name: {}", request.getServerName());
        log.info("### Request Server Port: {}", request.getServerPort());
        log.info("### Request Server Protocol: {}", request.getProtocol());
        log.info("### Request Headers: {}", request.getHeaderNames());
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName -> log.info("### Request Header: {}={}", headerName, request.getHeader(headerName)));
    }
}
