package org.ricone.config;

import org.ricone.error.exception.ConfigException;
import org.ricone.init.ConfigProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

/**
 * @project: ricone
 * @author: Dan on 11/14/2017.
 */
public abstract class AbstractService {

    private RestTemplate rt;
    private HttpHeaders headers = new HttpHeaders();
    private HttpEntity<?> entity;

    public AbstractService() {
        rt = new RestTemplate();
        headers = new HttpHeaders();
        headers.set("Authorization", ConfigService.getInstance().getAccessToken());
        entity = new HttpEntity<>(headers);
    }

    public RestTemplate restTemplate() {
        return rt;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public HttpEntity<?> getEntity() {
        return entity;
    }

    public String getUrl() throws ConfigException {
        return ConfigProperties.getInstance().getProperty("component.config.href");
    }

    public String getUsername() throws ConfigException {
        return ConfigProperties.getInstance().getProperty("component.config.username");
    }

    public String getPassword() throws ConfigException {
        return ConfigProperties.getInstance().getProperty("component.config.password");
    }
}
