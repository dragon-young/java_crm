package cn.sixboys.domain;

/**
 * 返回json对象实体类
 * @author FuqiangWu
 */
public class JsonResult {

        private boolean success;
        private String msg;
        private Object data;

        public JsonResult() {
        }

        public JsonResult(boolean success, String msg) {
            this.success = success;
            this.msg = msg;
        }

        public JsonResult(boolean success, String msg, Object data) {
            this.success = success;
            this.msg = msg;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
