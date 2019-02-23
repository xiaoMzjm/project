package com.zjm.advertisement.constant;

/**
 * @author:小M
 * @date:2019/2/19 11:39 PM
 */
public class Constants {

    public enum AdBizKeyEnum {
        FIT("fit" , "健身小程序")

        ;
        private String code;
        private String desc;

        AdBizKeyEnum(String code, String desc) {
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

    public enum AdTypeEnum {
        HOME_PAGE("homePage" , "首页")
        ;
        private String code;
        private String desc;

        AdTypeEnum(String code, String desc) {
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
