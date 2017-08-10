package com.chinaredstar.cms.api.component;

import java.io.Serializable;

/**
 * Created by xiaobc on 16-8-16.
 */
public class MdmServiceResult<T> implements Serializable{
    private static final long serialVersionUID = 1112149670302251011L;

    /** 200 成功 */
    private String invoke_code;

    /** 具体详细msg */
    private String invoke_msg;
    /**  表明  */
    private String name;

    /** 服务端返回数据 */
    private T results;

    public MdmServiceResult() {

    }

    public MdmServiceResult(String invoke_code) {
        this.invoke_code = invoke_code;
    }

    public MdmServiceResult(String invoke_code, String invoke_msg, String name) {
        this.invoke_code = invoke_code;
        this.invoke_msg = invoke_msg;
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getInvoke_code() {
        return invoke_code;
    }

    public void setInvoke_code(String invoke_code) {
        this.invoke_code = invoke_code;
    }

    public String getInvoke_msg() {
        return invoke_msg;
    }

    public void setInvoke_msg(String invoke_msg) {
        this.invoke_msg = invoke_msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MdmServiceResult{" +
                "invoke_code='" + invoke_code + '\'' +
                ", invoke_msg='" + invoke_msg + '\'' +
                ", name='" + name + '\'' +
                ", results=" + results +
                '}';
    }
}
