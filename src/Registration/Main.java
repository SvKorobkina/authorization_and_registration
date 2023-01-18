package Registration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int id = 0;
        List<User> userArrayList = new ArrayList();
        List<Shop> arrayShop = new ArrayList<>();
        arrayShop.add(new Shop(1, "Платье вечернее", "Синяя", ProdEnum.DRESS, 42, 3500));
        arrayShop.add(new Shop(2, "Футболка спортивная", "Белый", ProdEnum.SHIRT, 44, 3000));
        arrayShop.add(new Shop(3, "Обувь зимняя", "Серый", ProdEnum.BOOTS, 50, 4500));
        arrayShop.add(new Shop(4, "Бейсболка", "Черный", ProdEnum.HAT, 52, 2000));
        authorization_and_registration(id, userArrayList, arrayShop);
    }

    public static void authorization_and_registration(int id, List<User> userArrayList, List<Shop> arrayShop) {
        Scanner input = new Scanner(System.in);
        System.out.println("1 - зарегистрироваться в системе\n2 - войти в систему\n3 -просмотр товаров");
        int operation = input.nextInt();
        switch (operation) {
            case 1: {
                input_user(userArrayList);
                System.out.println("Регистрация");
                System.out.println("Введите логин: ");
                input.nextLine();
                String login = input.nextLine();
                if (login.matches("^[a-zA-Z0-9._-]{3,}$")) {
                    System.out.println(" ");
                } else {
                    System.out.println("Логин не соответствует условиям: длина >=3, допустимые символы: az, AZ, 0-9, точки, тире и подчеркивания");
                    break;
                }
                ;
                System.out.println("Введите email: ");
                String email = input.nextLine();
                if (email.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}")) {
                    System.out.println(" ");
                } else {
                    System.out.println("E-mail введен некорректно");
                    break;
                }
                ;
                System.out.println("Введите пароль: ");
                String password = input.nextLine();
                if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,16}$")) {
                    System.out.println(" ");
                } else {
                    System.out.println("Пароль должен содержать от 3 до 16 символов и содержать цифры, строчные, прописные буквы и спец. символы @#$%^&+=");
                    break;
                }
                ;
                System.out.println("Введите имя: ");
                String FirstName = input.nextLine();
                System.out.println("Введите фамилию: ");
                String LastName = input.nextLine();
                System.out.println("Введите отчетсво: ");
                String Patronomic = input.nextLine();
                System.out.println("Введите номер телефона: ");
                String PhoneNumber = input.nextLine();
                if (PhoneNumber.matches("^((\\+7|7|7)+([0-9]{10})$)")) {
                    System.out.println(" ");
                } else {
                    System.out.println("Номер телефона введен некорректно");
                    break;
                }
                ;

                User newUser = new User(id, Roles.USERR, login, email, password, FirstName, LastName, Patronomic, PhoneNumber);
                id++;
                userArrayList.add(newUser);

                authorization_and_registration(id, userArrayList, arrayShop);
                break;
            }

            case 2: {
                System.out.println("Авторизация");
                System.out.println("Введите логин или email: ");
                input.nextLine();
                String login_or_email_user = input.nextLine();
                System.out.println("Введите пароль: ");
                String password_user = input.nextLine();

                User user_avtorization = new User(id, Roles.USERR, login_or_email_user, password_user);
                if (password_user.equals("Admin@1234") && login_or_email_user.equals("admin")) {
                    System.out.println("Вы вошли как админ");
                    input.nextLine();
                    admin_choise(userArrayList, arrayShop);
                }
                Iterator<User> userIterator = userArrayList.iterator();//создаем итератор
                while (userIterator.hasNext()) {//до тех пор, пока в списке есть элементы
                    User nextUser = userIterator.next();//получаем следующий элемент
                    if (nextUser.getLogin().equals(login_or_email_user)&&(nextUser.getPassword().equals(password_user))) {
                        System.out.println("Вы вошли в систему");
                        input.nextLine();
                        user_role(id, userArrayList, arrayShop);
                    }

                }

                {
                    System.out.println("Данные введены неверно.Зарегистрируйтесь или попробуйте еще раз.");
                    authorization_and_registration(id, userArrayList, arrayShop);
                }
            }
            case 3:
            { print_shop(arrayShop);
                authorization_and_registration(id, userArrayList, arrayShop);
            }
            default:
                System.out.println("Выбрано неверное действие");
        }
    }

    public static void input_user(List<User> userArrayList) { //выбор пользователем роли
        Scanner input = new Scanner(System.in);
        //    System.out.println("Добавление ролей: ");
        System.out.println("Выберите права доступа: \n1 - Отдел кадров\n2 - Бухгалтерия\n3 - " +
                "Администратор\n4 - Пользователь");
        int type_user = input.nextInt();
        Roles roles = Roles.HR;
        switch (type_user) {
            case 1: {
                roles = Roles.HR;
                break;
            }
            case 2: {
                roles = Roles.BOOKKEEPER;
                break;
            }
            case 3: {
                roles = Roles.ADMIN;
                break;
            }
            case 4: {
                roles = Roles.USERR;
                break;
            }

            default: {
                System.out.println("Вы выбрали несуществующий тип доступа");
                input_user(userArrayList);

            }
        }
        System.out.println("Вы выбрали тип доступа " + roles + " .Адмиристратор рассмотрит вашу заявку");

    }
    public static void print_user(List<User> ArrayList) { // Метод по выводу зарегистрированных пользователей
        int i = 1;
        System.out.println("Список пользователей: ");
        for (User user : ArrayList) {
            System.out.println("Пользователь №" + i);
            System.out.println("Логин: " + user.getLogin());
            System.out.println("Тип доступа: " + user.getType());
            System.out.println("Пароль: " + user.getPassword());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Имя: " + user.getFirstName());
            System.out.println("Фамилия: " + user.getLastName());
            System.out.println("Отчество: " + user.getPatronomic());
            System.out.println();
            i++;
        }
    }

    public static void admin_choise(List<User> userArrayList, List<Shop> arrayShop) {  //выбор действий админа
        Scanner input = new Scanner(System.in);
        System.out.println("Выберите действие\n1 - просмотр информации о пользователях\n2 - смена роли пользователю\n3 - добавление товаров\n4 - удаление товаров\n5 - выход");
        int operation_admin = input.nextInt();
        switch (operation_admin) {
            case 1: {
                print_user(userArrayList);
                admin_choise(userArrayList, arrayShop);
            }
            case 2: {
                change_role(userArrayList);
                admin_choise(userArrayList, arrayShop);
            }
            case 3: {
                adding(arrayShop);
                admin_choise(userArrayList, arrayShop);
            }
            case 4: {
                delete(arrayShop);
                admin_choise(userArrayList, arrayShop);

            }
            case 5: {
                System.exit(0);
            }
            default: {
                System.out.println("Вы выбрали неверное действие");
                        }
            }
        System.exit(0);
    }
    public static void change_role(List<User> userArrayList) {  //назначение роли администратором
        Scanner input = new Scanner(System.in);
        System.out.println("Выберите id для смены типа доступа");
        int il = input.nextInt();
        System.out.println("Выберите какую роль хотите назначить\n1 - администратор\n2 - бухгалтерия\n3 - отдел кадров\n4 - пользователь");
        int operation_role = input.nextInt();
        switch (operation_role) {
            case 1: {
                for (User user : userArrayList) {
                    while (user.getId() == (il - 1)) {
                        user.setType(Roles.ADMIN);
                        System.out.println("Вы удачно добавили роль админ");
                        break;
                    }
                    break;
                }
                break;
            }
            case 2: {
                for (User user : userArrayList) {
                    while (user.getId() == (il - 1)) {
                        user.setType(Roles.BOOKKEEPER);
                        System.out.println("Вы удачно добавили роль бухгалтерии");
                        break;
                    }
                    break;
                }
                break;
            }
            case 3: {
                for (User user : userArrayList) {
                    while (user.getId() == (il - 1)) {
                        user.setType(Roles.HR);
                        System.out.println("Вы удачно добавили роль отдела кадров");
                        break;
                    }
                    break;
                }
                break;
            }
            case 4: {
                for (User user : userArrayList) {
                    while (user.getId() == (il - 1)) {
                        user.setType(Roles.USERR);
                        System.out.println("Вы удачно добавили роль пользователя");
                        break;
                    }
                    break;
                }
                break;
            }

            default:
                System.out.println("Неверная операция");
        }
    }

    public static void print_shop(List<Shop> arrayShop) {
        int i = 1;
        //    System.out.println("Список товаров: ");
        for (Shop shop : arrayShop) {
            System.out.println("Товар №" + i);
            System.out.println("Наименование: " + shop.getName());
            System.out.println("Цвет: " + shop.getColor());
            System.out.println("Размер: " + shop.getSize());
            System.out.println("Тип: " + shop.getType());
            System.out.println("Цена: " + shop.getPrice());
            System.out.println();
            i++;
        }
    }

    public static void adding(List<Shop> arrayShop) {
        Scanner input = new Scanner(System.in);
        System.out.println("Добавление товаров: ");
        System.out.println("Введите наименование товара: ");

        String name = "";
        try {
            name = input.nextLine();
        } catch (ClassCastException classCastException) {
            System.out.println("Вы ввели наверные данные");
            adding(arrayShop);
        }

        System.out.println("Введите цвет товара: ");
        String color = "";
        try {
            color = input.nextLine();
        } catch (ClassCastException classCastException) {
            System.out.println("Вы ввели наверные данные");
            adding(arrayShop);
        }
        System.out.println("Выберите тип товара: \n1 - платья\n2 - футболки\n3 - " +
                "обувь\n4 - головные уборы\n");
        int adding_type = input.nextInt();
        ProdEnum ProdEnum = Registration.ProdEnum.HAT;
        switch (adding_type) {
            case 1: {
                ProdEnum = ProdEnum.DRESS;
                break;
            }
            case 2: {
                ProdEnum = ProdEnum.SHIRT;
                break;
            }
            case 3: {
                ProdEnum = ProdEnum.BOOTS;
                break;
            }
            case 4: {
                ProdEnum = ProdEnum.HAT;
                break;
            }
            default: {
                System.out.println("Вы выбрали неверный тип автомобиля");
                adding(arrayShop);
            }
        }
        int size = 0;
        System.out.println("Введите размер числом: ");
        try {
            size = input.nextInt();

        } catch (ClassCastException classCastException) {
            System.out.println("Вы ввели наверные данные");
            adding(arrayShop);
        }

        float price = 0;
        System.out.println("Введите цену в рублях: ");
        try {
            price = input.nextInt();

        } catch (ClassCastException classCastException) {
            System.out.println("Вы ввели наверные данные");
            adding(arrayShop);
        }
        int id = +1;
        arrayShop.add(new Shop(id, name, color, ProdEnum, size, price));
        System.out.println("Товар успешно добавлен");
        print_shop(arrayShop);

    }

    public static void delete(List<Shop> arrayShop) {
        Scanner input = new Scanner(System.in);
        System.out.println("Удаление товаров: ");
        System.out.println("Введите наименование товара: ");
        String delete_name = input.nextLine();

        Iterator<Shop> shopIterator = arrayShop.iterator();//создаем итератор
        while (shopIterator.hasNext()) {//до тех пор, пока в списке есть элементы
            Shop nextShop = shopIterator.next();//получаем следующий элемент
            if (nextShop.getName().equals(delete_name)) {
                shopIterator.remove();//удаляем предмет с нужным индексом}
                System.out.println("Список товаров после удаления: ");
                print_shop(arrayShop);
            }
        }
    }
    public static void user_role(int id, List<User> userArrayList, List<Shop> arrayShop) {

        System.out.println("Вы вошли как пользователь.");
   //   input.nextLine();
        System.out.println("Вам доступен просмотр списока товаров:\n");
        print_shop(arrayShop);
        authorization_and_registration(id, userArrayList, arrayShop);
    }
}



