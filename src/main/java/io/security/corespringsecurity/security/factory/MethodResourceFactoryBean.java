package io.security.corespringsecurity.security.factory;

import io.security.corespringsecurity.service.SecurityResourceService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;

import java.util.LinkedHashMap;
import java.util.List;

public class MethodResourceFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {

    private SecurityResourceService securityResourceService;

    private LinkedHashMap<String, List<ConfigAttribute>> resourceMap = new LinkedHashMap<>();

    private String resourceType;

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getObject() {

        if (resourceMap.isEmpty()) {
            init();
        }

        return resourceMap;
    }

    private void init() {
        switch (resourceType) {
            case "method":
                resourceMap = securityResourceService.getMethodResourceList();
                break;
            case "pointcut":
                resourceMap = securityResourceService.getPointcutResourceList();
                break;
        }
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
