// Необходимо составить программу, которая помогает пользователю составить план поезда.
// После запуска программа спрашивает пользователя - хочет ли он закончить работу, или составить поезд

import kotlin.random.Random

// Основная функция программы
fun main() {
    println("Добро пожаловать в программу по созданию плана поезда!")

    var NoWay = false

    // Цикл для взаимодействия с пользователем
    while (!NoWay) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("5. Завершить программу")

        when (readLine()) {
            "1" -> OneDerection()
            "2" -> Ticki()
            "3" -> Format()
            "4" -> otpravkabubi()
            "5" -> NoWay = true
            else -> println("Некорректный ввод. Пожалуйста, выберите действие из списка.")
        }
    }
}

// Переменные для хранения состояния программы
var storona: String? = null // при создании переменной она null, после выполнения createDirection становится string
var passengers: Int = 0
var mestovvagone: Int = 0
var kolvovagon: MutableList<Int> = mutableListOf()
var skolkavvagone: MutableList<Int> = mutableListOf()

// Создание направления (города отправления и прибытия)
fun OneDerection() {
    val cities = listOf("Бийск", "Барнаул", "Москва", "Санкт-Петербург", "Ростов-на-Дону", "Владимир", "Новосибирск", "Сочи", "Красноярск", "Краснодар", "Чебоксары", "Йошкар-Ола", "Владивосток", "Рязань", "Пермь")
    val fromCity = cities.random()
    var toCity = cities.random()
    while (fromCity == toCity) {
        toCity = cities.random()
    }
    storona = "$fromCity - $toCity"
    println("Направление создано: $storona")
}

// Продажа билетов (генерация случайного количества пассажиров)
fun Ticki() {
    passengers = Random.nextInt(5, 202)
    println("Продано билетов: $passengers")
}

// Формирование поезда (генерация вместимости вагонов и распределение пассажиров)
fun Format() {
    kolvovagon.clear()
    skolkavvagone.clear()
    var remainingPassengers = passengers

    // Работает до тех пор пока все пассажиры не будут зассажены в вагоны. С кажым проходом цикла делается один вагон
    while (remainingPassengers > 0) {
        val wagonCapacity = Random.nextInt(1, 26) // рандомно создается вместимость пассажиров в вагоне
        // Распределение пассажиров вагоне, обычно работает первая скобка,
        val passengersInWagon = if (remainingPassengers >= wagonCapacity) wagonCapacity else remainingPassengers
        skolkavvagone.add(wagonCapacity)// записывает вместимость вагона в mutable list
        kolvovagon.add(passengersInWagon)// в mutable list записывается int значение, означающее кол-во пассажиров в вагоне
        remainingPassengers -= passengersInWagon // вычетается из общего кол-во пассажиров, распределеной в этом вагоне
    }

    mestovvagone = kolvovagon.size

    // Вывод информации о поезде с учетом слова "вагон"
        println("Поезд сформирован: $mestovvagone вагонов")
}

// Отправка поезда (вывод информации о направлении, количестве вагонов и пассажирах в каждом вагоне)
fun otpravkabubi() {
    // Проверка, что направление и поезд были созданы
    if (storona != null && mestovvagone > 0) {
        println("Поезд $storona, состоящий из $mestovvagone вагонов, отправлен.")
        for ((index, capacity) in kolvovagon.withIndex()) {
            println("Вагон ${index + 1}: вместимость ${skolkavvagone[index]}, пассажиров $capacity")
        }
    } else {
        println("Направление или поезд не были созданы. Пожалуйста, выполните предыдущие шаги.")
    }
}