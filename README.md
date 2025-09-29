# Описание
Spring java сервер для обработки запросов. Основая задача реализовать игру "квадраты" против бота на сервере. Бот написан на алгоритме приоритетов, где он проходя по всей доске определяет каждой клетке приоритет и ставит свой ход на ту клетку, где больше цй
# Stack технологии
### - Java 21.
### - Spring 3.5.0
### - maven
### - Junit
### - Mock

# API
## /api/{rules}/doMove
@Request body boardDto. Post запрос на сервер для получение от бота следующее его действие на доске в виде класса MoveDto. 

## /api/board/square/checkWin
@Request body boardDto. Post запрос на сервер для получение информации о победе какой-то из сторон. Возращает string значение: "b"-победа черных, "w" - победа белых, "" - победиль неопределен, игра продолжается, "d" - ничья.

## /index.html 
выдача страницы игры "квадраты"

# Как запустить?
в release версии zip архив. Распаковать в любом удобном месте  и запустить start.bat либо командой java -jar server.jar . необходима java 21. Чтобы зайти на сайт нужно написать в браузере http://localhost:8080/index.html

# Демонстрация работы
## <img width="980" height="558" alt="image" src="https://github.com/user-attachments/assets/ac0acb10-0bc2-425c-a666-fdbedff63e81" />
## <img width="1343" height="649" alt="image" src="https://github.com/user-attachments/assets/9f4e3a4d-a5d2-4fef-b268-2312f3c0fad6" />

