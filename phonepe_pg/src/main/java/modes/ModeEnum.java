package modes;

public enum ModeEnum {

    UPI("UPI"),
    CARD("CARD"),
    NETBANKING("NETBANKING");

    private String modeName;

    ModeEnum(String name){
        this.modeName = name;
    }

    public static ModeEnum fromString(String text) {
        for (ModeEnum mode : ModeEnum.values()) {
            if (mode.modeName.equalsIgnoreCase(text)) {
                return mode;
            }
        }
        return null;
    }

}
