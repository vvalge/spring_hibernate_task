package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Car2", "series1");
      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);
      userService.add(user1);
      Car car2 = new Car("Car2", "series2");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);
      userService.add(user2);
      Car car3 = new Car("Car3", "series2");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      user3.setCar(car3);
      userService.add(user3);
      Car car4 = new Car("Car2", "series2");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      user4.setCar(car4);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         userService.showUser(user);
      }

      List<User> foundUsers = userService.findUsersByCar("Car2", "series2");
      System.out.println("Found users:");
      for (User foundUser : foundUsers) {
         userService.showUser(foundUser);
      }
      context.close();
   }
}
