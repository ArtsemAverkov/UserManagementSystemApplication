# Привет, я [Артем](https://www.linkedin.com/in/артем-аверков-aa7663239/)
Введение
Этот проект представляет собой Java-приложение, котороевыводит чек купленных товаров у. Приложение построено с использованием 17 Java и использует Spring-boot, Hibernate, Maven, MySql, Docker, Unit 5, Mockito, для достижения своей функциональности.

Начиная
Чтобы начать работу с этим проектом, на вашем компьютере должна быть установлена ​​17 Java. Вы можете загрузить и установить последнюю версию Java с сайта https://www.oracle.com/java/.

Далее вам нужно будет скачать исходный код проекта с [GitHub](https://github.com/ArtsemAverkov/UserManagementSystemApplication.git)  Получив исходный код, вы можете импортировать проект в среду IDE IntelliJ и запустить его оттуда либо воспользоваться Docker_compose и запустить приложение в [Docker](https://www.docker.com).

Применение
Чтобы использовать приложение, нужно использовать например Postman, по методу [GET](http://localhost:8080/users) чтобы получить всех пользователей. 

Функции
Приложение имеет следующие возможности:
Получение всех потзователей [GET](http://localhost:8080/users)
Получения пользователя по id [GET{id}](http://localhost:8080/users/1)
Добавление пользователя по методу [POST](http://localhost:8080/users) и тело например:
{
"username": "test",
"surname": "test",
"email": "test",
"role": "test"
}
Удаление пользователя по id по методу [DELETE{id}](http://localhost:8080/users/1)
Обновление пользователя по методу [PATCH{id}](http://localhost:8080/users/1)


Заключение
Благодарю вас за интерес к этому Java-проекту. Если у вас есть какие-либо вопросы или отзывы, пожалуйста, не стесняйтесь
[linkedin](https://www.linkedin.com/in/артем-аверков-aa7663239/).
