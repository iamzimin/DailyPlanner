# Daily Planner

Стек: Kotlin, Clean Architecture, Jetpack Compose, Coroutine, MVI, Room, Dagger Hilt, Jetpack Navigation, JUnit.
<br><br>

## Инструкция по запуску
- [Скачать apk](https://github.com/iamzimin/DailyPlanner/releases/latest) последней версии и установить.
<br><br>

## Функционал приложения
1. **Список дел с календарем.** <br>
На экране реализована возможность выбрать интересующую дату для просмотра дел. 
После выбора дня автоматически обновляется таблица всех задач: каждая ячейка соответствует одному часу (например, 14:00–15:00). 
Задачи, занимающие весь день, выводятся отдельным блоком. Для каждого события отображаются его название и время.
В данном модуле предусмотрено 2 unit теста и анимации при навигации на другие экраны.

2. **Подробное описание дела.** <br>
Отображает полную информацию о выбранном деле: название, дату, время и краткое текстовое описание.

3. **Создание дела.** <br>
Экран создания включает поля для ввода названия, выбора даты и времени, а также добавления краткого описания.
<br><br>

## Демонстрация
<img src="https://github.com/user-attachments/assets/e5ea1b59-3f59-4d90-bed5-4d40bffd3292" alt="GIF1" width="200"/>
<img src="https://github.com/user-attachments/assets/ad84a562-78c8-4b12-a293-1ff2f4a19ea0" alt="Image1" width="200"/>
<img src="https://github.com/user-attachments/assets/f9d146aa-aed7-4796-be43-d05cdfae3dfc" alt="Image2" width="200"/>
<img src="https://github.com/user-attachments/assets/d4fe50db-a384-46d6-b11a-ed37795a070f" alt="Image3" width="200"/>