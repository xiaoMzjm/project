package com.base.rpc.provider;

/**
 * @author:小M
 * @date:2020/10/13 8:49 PM
 */
public class RpcProvider {

    private String interfaceName;

    private Object bean;

    public RpcProvider(String interfaceName, Object bean) {
        this.interfaceName = interfaceName;
        this.bean = bean;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
