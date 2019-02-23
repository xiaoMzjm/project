package com.zjm.suggest.constant;

/**
 * @author:小M
 * @date:2019/2/21 12:45 AM
 */
public class Constants {

    public enum SuggestStatus {
        CREATE(1 , "创建"),
        REPLY(2 , "已回复")
        ;
        private Integer status;
        private String name;

        SuggestStatus(Integer status, String name) {
            this.status = status;
            this.name = name;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum SuggestBizKeyEnum {
        FIT("fit" , "健身小程序")

        ;
        private String code;
        private String desc;

        SuggestBizKeyEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum SuggestTypeEnum {
        ERROR("error" , "遇到问题"),
        SUGGEST("suggest" , "建议")
        ;
        private String code;
        private String desc;

        SuggestTypeEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
