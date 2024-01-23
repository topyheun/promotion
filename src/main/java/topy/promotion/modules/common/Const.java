package topy.promotion.modules.common;

public final class Const {

    public static final String SUCCESS = "성공";

    public static final String USER_USED_ACCOUNT = "사용중인 계정입니다.";
    public static final String USER_NOT_FOUND_ACCOUNT = "계정을 찾을 수 없습니다.";

    public static final String USER_DTO_NO_USERNAME = "아이디를 입력해주세요";
    public static final String USER_DTO_NO_PASSWORD = "비밀번호를 입력해주세요";
    public static final String USER_DTO_NO_EMAIL = "이메일을 입력해주세요";
    public static final String USER_DTO_WRONG_USERNAME_FORMAT = "올바르지 않은 아이디 양식입니다 (영문 소문자와 숫자를 사용해서 생성해주세요 글자 수 제한은 4 ~ 10자리 입니다)";
    public static final String USER_DTO_WRONG_PASSWORD_FORMAT = "올바르지 않은 비밀번호 양식입니다";
    public static final String USER_DTO_WRONG_EMAIL_FORMAT = "올바르지 않은 이메일 양식입니다";

    public static final String PROMOTION_USED_PROMOTION = "사용중인 프로모션 입니다";
    public static final String PROMOTION_NOT_FOUND_PROMOTION = "프로모션을 찾을 수 없습니다.";
    public static final String PROMOTION_NOTHING_REGISTERED_PROMOTION = "현재 등록된 프로모션이 없습니다.";
    public static final String PROMOTION_WRONG_PROMOTION_PERIOD = "프로모션의 시작일자와 종료일자를 다시 한번 확인해주세요.";
    public static final String PROMOTION_NOT_PROCEEDING_PROMOTION = "현재 진행하고 있는 프로모션이 아닙니다.";
    public static final String PROMOTION_DUPLICATE_PARTICIPATION_PROMOTION = "이미 참여한 프로모션입니다. (프로모션은 하루에 한 번만 참여할 수 있습니다.)";

    public static final String PROMOTION_DTO_NO_VALUES = "프로모션 정보를 작성해주세요";

    public static final String REWARD_DUPLICATE_REWARD = "해당 프로모션에 이미 존재하는 경품입니다.";

    public static final String REWARD_DTO_NO_VALUES = "경품 정보를 작성해주세요";
    public static final String REWARD_DTO_ENTER_NUMBER_GREATER_THAN_ZERO = "0 이상의 경품 수량을 입력해주세요";

    public static final String PARTICIPATION_DTO_FAIL_MESSAGE = "다음 기회에,,,";
}
