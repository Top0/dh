package com.example.demo.common.interceptor;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RepeatableHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private static final int BUFFER_SIZE = 1024 * 8;
    private byte[] body;

    public RepeatableHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        /**
         * 如果请求是多部分请求，就直接返回，不进行后续的处理。
         * 多部分请求包含了文件数据和其他表单字段数据。
         */
        if (isMultipartContent(request)) {
            return;
        }

        BufferedReader reader = request.getReader();
        try (StringWriter writer = new StringWriter()) {
            int read;
            char[] buf = new char[BUFFER_SIZE];
            while ((read = reader.read(buf)) != -1) {
                writer.write(buf, 0, read);
            }
            this.body = writer.getBuffer().toString().getBytes(StandardCharsets.UTF_8);
        }
    }

    private boolean isMultipartContent(HttpServletRequest request) {
        return !"POST".equalsIgnoreCase(request.getMethod()) ? false :
                isMultipartContent(new ServletRequestContext(request));
    }

    private boolean isMultipartContent(ServletRequestContext ctx) {
        String contentType = ctx.getContentType();
        return contentType != null && contentType.toLowerCase().startsWith("multipart/");
    }

    /**
     * 获取请求体数据
     *
     * @return
     */
    public String getBody() {
        return new String(this.body, StandardCharsets.UTF_8);
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

}

