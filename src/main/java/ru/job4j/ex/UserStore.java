package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        if (login == null) {
            throw new IllegalArgumentException("login should be not null");
        }
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                return user;
            }
        }
        throw new UserNotFoundException("User with login '" + login + "' is not found");
    }

    public static boolean validate(User user) throws UserInvalidException {
        String userName = user.getUsername();
        if (!user.isValid() || userName.length() < 3) {
            String msg = "User '" + userName + "' is not valid "
                    + "or length of user's name less than 3";
            throw new UserInvalidException(msg);
        }
        return true;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException e) {
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
