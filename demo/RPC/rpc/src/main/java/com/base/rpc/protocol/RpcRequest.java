package com.base.rpc.protocol;

import java.util.Arrays;

/**
 * @author:小M
 * @date:2020/10/13 4:14 PM
 */
public class RpcRequest {

    /**
     * 请求对象的ID
     */
    private String requestId;
    /**
     * 类名
     */
    private String interfaceName;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] parameterTypes;
    /**
     * 入参
     */
    private Object[] parameters;

    @Override
    public String toString() {
        return "RpcRequest{" +
            "requestId='" + requestId + '\'' +
            ", interfaceName='" + interfaceName + '\'' +
            ", methodName='" + methodName + '\'' +
            ", parameterTypes=" + Arrays.toString(parameterTypes) +
            ", parameters=" + Arrays.toString(parameters) +
            '}';
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
