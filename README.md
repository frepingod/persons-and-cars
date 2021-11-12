# Persons and Cars

## Описание
Реализовано REST приложение с пятью URL, каждый URL делает следующее:

### 1. Сервис сохраняет объект Person
**URL:** /person

**Метод:** POST

**Request Body:**

    объект Person
    {
        id: Long (not null, уникальный),
        name: String (not null),
        birthdate: Date (not null, формат dd.MM.yyyy, дата рождения в прошлом)
    }

**Response Body:** возвращает сохраненный объект Person

**Response Code:** 200 OK - если все хорошо, 400 Bad Request - ошибки валидации

### 2. Сервис сохраняет объект Car
**URL:** /car

**Метод:** POST

**Request Body:**

    объект Car
    {
        id: Long (not null, уникальный),
        model: String (not null, в формате vendor-model например BMW-X5, причем vendor никогда не содержит “-” и не пустой, model не пустой),
        horsepower: Integer (not null, horsepower > 0),
        ownerId: Long (not null, owner старше 18 лет)
    }

**Response Body:** возвращает сохраненный объект Car

**Response Code:** 200 OK - если все хорошо, 400 Bad Request - ошибки валидации

### 3. Сервис возврщает объект PersonWithCars (Person с массивом Cars)
**URL:** /personWithCars?personId=...

**Метод:** GET, Параметр - personId

**Request Body:** Пусто

**Response Body:** 

    объект PersonWithCars
    {
        id: Long (not null, уникальный),
        name: String (not null),
        birthdate: Date (not null, формат dd.MM.yyyy, дата рождения в прошлом),
        cars: Array of Cars [{}, {}, ...] (not null)
    }

**Response Code:** 200 OK - если все хорошо, 400 Bad Request - ошибки валидации, 404 Not Found - если не найдет PersonWithCars с id=personId

### 4. Сервис возврщает объект Statistics
**URL:** /statistics

**Метод:** GET

**Request Body:** Пусто

**Response Body:**

    объект Statistics    
    {
        personCount: Long (количество сохраненных и прошедших валидацию объектов Person),
        personWithCarsCount: Long (количество сохраненных и прошедших валидацию объектов PersonWithCars),
        carCount: Long (количество сохраненных и прошедших валидацию объектов Car), 
        uniqueVendorCount: Long (количество уникальных производителей среди сохраненных и прошедших валидацию объектов Car, регистронезависимо) 
    }

**Response Code:** 200 OK

### 5. Сервис удаляет все ранее сохраненные объекты (Person, PersonWithCars, Car)
**URL:** /clear

**Метод:** GET

**Request Body:** Пусто

**Response Body:** Пусто

**Response Code:** 200 OK