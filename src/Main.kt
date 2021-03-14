val FORBIDDEN = 0
val EXECUTE = 1
val WRITE = 2
val READ = 4

fun main() {

    fun checkPermissions(perms: Int) =
        perms and (READ or EXECUTE) == (READ or EXECUTE)

    println(checkPermissions(READ or EXECUTE or WRITE))
    println(checkPermissions(READ or WRITE))
    println(checkPermissions(READ or EXECUTE))
    println(checkPermissions(FORBIDDEN))
    println("========================")
    var original = "kot"
    var refs = original

    println(original == refs)
    println(original === refs)

//    val sc = Scanner(System.`in`)
//    refs = sc.next()

    println(original == refs)
    println(original === refs)

    println("========================")

    val months = """Styczeń, Luty, Marzec, Kwiecień, Maj, Czerwiec,
|Lipiec, Sierpień, Wrzesień, Październik, Listopad,
|Grudzień""".trimMargin()

    val monthsList = months.split(",( |(\r?\n))".toRegex())

    print(monthsList)

    for (i in monthsList.indices) {
        print("${monthsList[i]} ")
    }
    println()

    for (month in monthsList) {
        if (month.startsWith("L")) print("$month ")
    }
    println()

    monthsList.forEachIndexed { index, month ->
        if (index % 2 == 0) print("$index $month ")
    }
    println()

    val iter = monthsList.iterator()
    while (iter.hasNext()) {
        print("${iter.next()} ")
    }
    println()

    fun printRec(monthList: List<String>) {
        tailrec fun printRec(i: Int) {
            print("${monthList[i]} ")
            val nextI = i + 1
            if (nextI < monthList.size) printRec(nextI)
        }
        printRec(0)
    }
    println()

    printRec(monthsList)
    println()

    println(monthsList.joinToString(" "))

    monthsList.filter { !it.startsWith("P") }
        .map { it.replace("e", "_") }
        .forEach { print("$it ") }
    println()

    println("========================")



    println("========================")



    println("========================")
    val randomMonth = monthsList.random()

    fun getSemesterForOneMonth(month: String): String = when (month.toLowerCase()) {
        "lipiec", "luty", "sierpień", "wrzesień" -> "Ferie"
        "październik", " listopad", "grudzień", "styczeń" -> "Semestr zimowy"
        "marzec", "kwiecień", "maj", "czerwiec" -> "Semestr letni"
        else -> {
            "Nie ma takiego miesiąca"
        }
    }

    fun getSemester(vararg random: String) =
        random.associateWith { getSemesterForOneMonth(it) }



    println(getSemesterForOneMonth(randomMonth))

    println(getSemester(randomMonth))


    println("========================")

    val personList = listOf(
        Person("Jan", "Kowalsk", Sex.MALE),
        Person("adam", "Nowak", Sex.MALE)
    )
}

enum class Sex {
    MALE, FEMALE
}

data class Person(val name: String, val surname: String, val sex: Sex) {


}
