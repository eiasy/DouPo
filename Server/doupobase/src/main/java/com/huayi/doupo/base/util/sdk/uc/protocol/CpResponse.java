package com.huayi.doupo.base.util.sdk.uc.protocol;


public class CpResponse  {

    /**
     * 客户端请求id
     */
    private long id;
    
    /**
     * 服务器处理状态
     */
    private State state;
    
    /**
     * 业务数据。不同的接口，返回的业务数据不同，具体可参考对应的接口文档
     */
    private Object data;
    
    public class State {
        private Integer code;
        private String msg;
        
        public State(){}
        public State(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String toString(){
            return String.format("state[%d, '%s']", code, msg);
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
