package icu.oo7.yyds.common.util.enums;

/**
 * 用户状态枚举
 *
 * @author peng.zhao
 */
public enum UserStatusEnum {
    /**
     * 正常
     */
    NORMAL {
        @Override
        public Integer getValue() {
            return 1;
        }
        @Override
        public String getDescription() {
            return "正常";
        }
    },
    /**
     * 锁定
     */
    LOCKED {
        @Override
        public Integer getValue() {
            return 2;
        }
        @Override
        public String getDescription() {
            return "锁定";
        }
    },
    /**
     * 禁用
     */
    DISABLED {
        @Override
        public Integer getValue() {
            return 3;
        }

        @Override
        public String getDescription() {
            return "禁用";
        }
    },
    /**
     * 过期
     */
    EXPIRED {
        @Override
        public Integer getValue() {
            return 4;
        }

        @Override
        public String getDescription() {
            return "过期";
        }
    };

    public abstract Integer getValue();
    public abstract String getDescription();
}
