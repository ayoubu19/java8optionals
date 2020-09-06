package fr.norsys;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {

    public Optional<User> getUserValue(User user) {

        return Optional.ofNullable(user);
    }

    public User throwExceptionIfUserIsNull(User user) {
        return Optional.ofNullable(user).orElseThrow(NoSuchElementException::new);
    }

    public User getUserUsingOrElse(User user) {
        return Optional.ofNullable(user).orElse(new User("user@gmail.com"));
    }

    public String getUserEmail(User user) {
        //  return Optional.ofNullable(user).orElse(new User("unknown")).getEmail();
        return Optional.ofNullable(user).map(User::getEmail).orElse("unknown");
    }

    public Boolean isUserEmailValid(User user) {
        return Optional.ofNullable(user)
                .map((user1 -> user1.getEmail().contains("@"))).orElseThrow(NoSuchElementException::new);
    }

    public String getUserCountryName(User user) {
        return Optional.ofNullable(user)
                .map(user1 -> user1.getAddress()
                        .map(address -> address.getCountry()
                                .map(Country::getName
                                )
                                .orElse("unknown country")
                        )
                        .orElse("unknown address")
                )
                .orElse("unknown");

    }

    public String getUserCountryNameOrUnknown(User user) {
        return Optional.ofNullable(user)
                .flatMap(User::getAddress)
                .flatMap(Address::getCountry)
                .map(Country::getName)
                .orElse("unknown");
    }

    public String getFirstUserEmail(User user1, User user2) throws Exception {
        return Optional.ofNullable(user1).or(()->Optional.ofNullable(user2))
                .orElseThrow(NoSuchFieldException::new)
                .getEmail();

    }


    public List<User> getListFromUser(User user) {
        List<User> users = new ArrayList<>();
        Optional.ofNullable(user).ifPresent(users::add);
        return users;
    }

    public List<User> getListFromUserBis(User user) {
        List<User> users = new ArrayList<>();

       /*
       Optional.ofNullable(user)
               .ifPresentOrElse(users::add , () -> users.add(new User("random@norsys.fr")));
       return users;
        */

       users = Optional.ofNullable(user)
                .stream()
                .collect(Collectors.toList());
       if (users.isEmpty()){
           users.add(new User("random@norsys.fr"));
       }
       return users ;
    }
}
