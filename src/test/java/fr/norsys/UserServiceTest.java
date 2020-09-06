package fr.norsys;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Series of test to tryout Optional
 * every method implementation should not use null checks, or throw nullPointExceptions
 */
public class UserServiceTest {

    private UserService userService;

    @Before
    public void setup() {
        userService = new UserService();
    }

    /**
     * Getting user using Optional
     */

    @Test
    public void shouldReturnEmptyIfUserIsNull() {
        User user = null;
        assertThat(userService.getUserValue(user)).isEmpty();
    }

    @Test
    public void shouldReturnPresentIfUserIsNotNull() {
        User user = new User();
        assertThat(userService.getUserValue(user)).isPresent();
    }

    /**
     * create a method that throws a NoSuchElementException if null
     * else returns the user itself
     */

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowExceptionIfElementIsNotFound() {
        User user = null;
        userService.throwExceptionIfUserIsNull(user);
    }

    /**
     * OrElse and OrElseGet
     * create a method that returns a new user using email constructor if the passed user is null
     */

    @Test
    public void shouldReturnNewUserWithEmailIfUserisNull() {
        User user = new User();
        assertThat(userService.getUserUsingOrElse(user)).isNotNull();
    }

    /**
     * Map
     * Create a method that gets the user email from user and "unknown" is user is null
     */

    @Test
    public void shouldReturnUserEmailIsPresent() {
        User user = new User("test@norsys.fr");
        assertThat(userService.getUserEmail(user)).isEqualTo("test@norsys.fr");
    }

    @Test
    public void shouldReturnUknownIfUserIsEmpty() {
        User user = null;
        assertThat(userService.getUserEmail(user)).isEqualTo("unknown");
    }

    /**
     * Map
     * Create a method that returns true is email is valid, false if not valid, and throws NoSuchElementException if user is null
     */

    @Test
    public void shouldReturnTrueIfUserHasAValidEmail() {
        User user = new User("makouz@norsys.fr");
        assertThat(userService.isUserEmailValid(user)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfUserIsNotNullButInvalidEmail() {
        User user = new User("makouz-norsys.fr");
        assertThat(userService.isUserEmailValid(user)).isFalse();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnFalseIfUserIsNull() {
        User user = null;
        userService.isUserEmailValid(user);
    }

    /**
     * Map
     * create a function that returns the country name of the user
     * returns "unknown" if user is null
     * returns "unknown address" if user address is null
     * returns "unknown country" if user country is null
     */

    @Test
    public void shouldReturnUserCountryName() {
        User user = new User("makouz@norsys.fr", new Address(new Country("Morocco")));
        assertThat(userService.getUserCountryName(user)).isEqualTo("Morocco");
    }


    @Test
    public void shouldReturnUknownCountryIfUserCountryIsNull() {
        User user = new User("makouz@norsys.fr", new Address(null));
        assertThat(userService.getUserCountryName(user)).isEqualTo("unknown country");
    }


    @Test
    public void shouldReturnUknownAddressIfUserCountryIsNull() {
        User user = new User("makouz@norsys.fr");
        assertThat(userService.getUserCountryName(user)).isEqualTo("unknown address");
    }

    @Test
    public void shouldReturnUknownUserIfUserCountryIsNull() {
        User user = null;
        assertThat(userService.getUserCountryName(user)).isEqualTo("unknown");
    }

    
    /**
     * or
     * create a method that takes two users as an argument
     * returns first user email, IF it's not null
     * else returns second user email, IF first user is null and second user is not null
     * else returns noSuchElementException
     */

    @Test
    public void shouldReturnsFirstUserEmailIfUserIsNotNull() throws Exception {
        User user = new User("makouz@norsys.fr");
        assertThat(userService.getFirstUserEmail(user, null)).isEqualTo("makouz@norsys.fr");
    }

    @Test
    public void shouldReturnSecondUserEmailIfFirstIsNull() throws Exception {
        User user2 = new User("user2@norsys.fr");
        assertThat(userService.getFirstUserEmail(null, user2)).isEqualTo("user2@norsys.fr");
    }

    @Test(expected = NoSuchFieldException.class)
    public void shouldThrowsFirstUserEmailIfUserIsNotNull() throws Exception {
        userService.getFirstUserEmail(null, null);
    }

    /**
     * IfPresent
     * Create a method that return a list of one user if it exists
     * and an Empty List if Empty
     */

    @Test
    public void shouldReturnAlistOfOneObjectIfUserIsNotNullBis() {
        User user = new User("makouz@norsys.fr");
        List<User> users = userService.getListFromUser(user);
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getEmail()).isEqualTo("makouz@norsys.fr");
    }

    @Test
    public void shouldReturnAnEmptyListIfUserIsNull() {
        List<User> users = userService.getListFromUser(null);
        assertThat(users).isEmpty();
    }

    /**
     * IfPresentOrElse / Stream
     * Create a method that return a list of one user if it not null
     * else returns a list with user "random@norsys.fr"
     */

    @Test
    public void shouldReturnAlistOfOneObjectIfUserIsNotNull() {
        User user = new User("makouz@norsys.fr");
        List<User> users = userService.getListFromUserBis(user);
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getEmail()).isEqualTo("makouz@norsys.fr");
    }

    @Test
    public void shouldReturnAnEmptyListIfUserIsNullBis() {
        List<User> users = userService.getListFromUserBis(null);
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getEmail()).isEqualTo("random@norsys.fr");
    }

}
