package Users;

public class UserSession {
    private static int userId;

    public static void setUser(int id) {
        userId = id;
    }

    public static int getUser() {
        return userId;
    }
}
