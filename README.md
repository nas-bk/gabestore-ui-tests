# Демонстрационный проект по автоматизации UI тестирования.
Тесты реализованы для сайта <a target="_blank" href="https://gabestore.ru/">gabestore</a>. 

## Содержание:

* <a href="#tools">Технологии и инструменты</a>
* <a href="#cases">Реализованные тесты</a>
* <a href="#console">Запуск тестов</a>
* <a href="#allure">Отчеты в Allure</a>
* <a href="#testops">Интеграция с Allure TestOps</a>
* <a href="#telegram">Уведомления в Telegram с использованием бота</a>
*  <a href="#browserstack">Пример прогона теста на Selenoid</a>

<a id="tools"></a>
## Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/intellij-original.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/java-original.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/github-original.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/junit-original.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/gradle-original.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="images/logo/Selenide.png" width="50" height="50" alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.png" width="50" height="50" alt="Selenoid"/></a>
<a href="https://rest-assured.io/"><img src="images/logo/RestAssured.png" width="50" height="50" alt="RestAssured"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/jenkins-original.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://github.com/allure-framework/"><img src="images/logo/AllureReports.png" width="50" height="50" alt="Allure Report"/></a>
<a href="https://qameta.io/"><img src="images/logo/AllureTestOps.svg" width="50" height="50" alt="Allure TestOps"/></a> 
<a href="https://telegram.org/"><img src="images/logo/Telegram.png" width="50" height="50" alt="Telegram"/></a>
</p>

* Автотесты написаны на Java.
* Gradle — используется как инструмент автоматизации сборки.
* JUnit5 — для выполнения тестов.
* Selenide - фреймворка для автоматизации тестирования.
* Selenoid — для удаленного запуска браузера в Docker контейнерах.
* Jenkins — CI/CD для удаленного запуска тестов.
* Allure Report — для формирования отчетов тестирования.
* Allure TestOps — система управления тестированием.
* Telegram Bot — для уведомлений о результатах тестирования.


<a id="cases"></a>
## Реализованные тесты

### Автоматизированные тесты
* Тесты для главной страницы
  * Параметризованный тест содержимого хэдер меню
  * Тесты поисковой строки магазина
  * Тест открытия корзины без авторизации
* Тесты для страницы каталога товаров
  * Тест добавления товара в избранное без авторизации
  * Параметризованный тест работы фильтров
  * Тест работы кнопки 'Все фильтры'
  * Тест добавления товара в корзину

### Ручные тесты
* Добавление товара в избранное авторизованным пользователем
* Попытка осуществить вход без заполнения полей авторизации
* Регистрация с использованием существующего в базе email

<a id="console"></a>
## Запуск тестов

Для локального запуска используется команда:

```
gradle clean test -Denv=<env>
```

Для удаленного запуска на Selenoid используется следующая команда:

```
gradle clean test -Denv=<env> -Dlogin=<login_Selenoid> -Dpassword=<password_Selenoid>
```

**env** определяет среду запуска тестов. Принимаемые значение: 
* *local* - для локального запуска тестов
* *remote* - для удаленного запуска на Selenoid

Для удаленного запуска обязательно передавать логин и пароль от Selenoid в переменные *login_Selenoid* и *password_Selenoid*

Дополнительные свойства извлекаются из соответствующих файлов *.properties*.

### Запуск в Jenkins

Для запуска проекта через Jenkins была создана <a target="_blank" href="https://jenkins.autotests.cloud/job/C29-bochkareva_a-gabestore-ui-tests/">**задача**</a>.
Задание настроено на удаленный запуск.
Для запуска используете кнопку Build Now/Собрать сейчас. 

![Jenkins_build](/images/screens/jenkins.jpg)

После выполнения сборки, результаты тестов будут доступны в Allure Report и Allure TestOps.

<a id="allure"></a>
## Отчеты в <a target="_blank" href="https://jenkins.autotests.cloud/job/C29-bochkareva_a-gabestore-ui-tests/allure/">**Allure**</a>

На главной странице Allure отчета возможно узнать основную информацию о сборке и тенденцию выполнения тестов за все запуски.

![allure](/images/screens/allure.jpg)

На странице Suites представлен список тестов с описанием шагов, визуализацией результатов и с информацией о продолжительности выполнения.

![allure](/images/screens/allure_suites.jpg)

Дополнительно реализованы тестовые артефакты:
* Скриншот
* Лог браузера
* Page Source
* Видео прохождения теста 
> Пример видео представлен ниже

![allure](/images/screens/allure_attach.jpg)

<a id="testops"></a>
## Интеграция с <a target="_blank" href="https://allure.autotests.cloud/project/4500/dashboards">**Allure TestOps**</a>

![allure](/images/screens/allure_testops.jpg)

Код тестов импортируется в тест-кейсы проекта.

![allure](/images/screens/auto_tests.jpg)

Примеры ручных тест-кейсов

![allure](/images/screens/tests.jpg)

<a id="telegram"></a>
## Уведомления в Telegram с использованием бота

После завершения сборки Telegram бот автоматически обрабатывает и отправляет сообщение с отчетом о прогоне тестов.

![allure](/images/screens/telegram_bot.jpg)

<a id="browserstack"></a>
## Пример прогона теста в Selenoid

![This is an image](/images/screens/video.gif)
