package fr.ebiz.cdb.webapp.rest.exception;

/**
 * Created by Bertrand Pestre on 07/04/17.
 */
public class Error {

    private int code;
    private String type;
    private String message;
    private Object error;

    /**
     * Builder.
     *
     * @param builder builder
     */
    private Error(Builder builder) {
        code = builder.code;
        type = builder.type;
        message = builder.message;
        error = builder.error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public static final class Builder {
        private int code;
        private String type;
        private String message;
        private Object error;

        /**
         *
         */
        public Builder() {
        }

        /**
         * @param val val
         * @return Builder
         */
        public Builder code(int val) {
            code = val;
            return this;
        }

        /**
         * @param val val
         * @return Builder
         */
        public Builder type(String val) {
            type = val;
            return this;
        }

        /**
         * @param val val
         * @return Builder
         */
        public Builder message(String val) {
            message = val;
            return this;
        }

        /**
         * @param val val
         * @return Builder
         */
        public Builder error(Object val) {
            error = val;
            return this;
        }

        /**
         * @return Builder
         */
        public Error build() {
            return new Error(this);
        }
    }
}
