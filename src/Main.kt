import java.lang.Exception
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.random.Random

val FORBIDDEN = 0
val EXECUTE = 1
val WRITE = 2
val READ = 4


fun ___Excercise___(number: Int) = println("================ $number ================")
fun main(args: Array<String>) {

    ___Excercise___(1)
    val int1 = -123
    val int2 = 0xF4
    val int3 = 0b101011

    val byte1: Byte = 127
    val byte2: Byte = -0x7F
    val byte3: Byte = 0b1111111

    val short1: Short = 128
    val short2: Short = 0xF12
    val short3: Short = -0b1

    val long1 = 4_000_000_000_000
    val long2 = 4L
    val long3 = -1L

    val float1 = .34f
    val float2 = 3.14f

    val double1 = .000_000_001
    val double2 = 2.123e3
    val double3 = 3.14

    val char1 = 'a'
    val char2 = '\u0024'

    val boolean = true
    val boolean2 = false

    val intValue = 65
//    val doubleValue: Double = intValue //need conversion
//    val longValue: Long = intValue //need conversion
//    val charValue: Char = intValue //need conversion

    ___Excercise___(2)
    fun checkPermissions(perms: Int) = perms and (READ or EXECUTE) == (READ or EXECUTE)

    println(checkPermissions(READ or EXECUTE or WRITE))
    println(checkPermissions(READ or WRITE))
    println(checkPermissions(READ))
    println(checkPermissions(FORBIDDEN))

    ___Excercise___(3)
    val original = "kot"
    var refs = original
    println(original == refs)
    println(original === refs)
    println("System.in")
    refs = Scanner(System.`in`).use { it.next() }
    println(original == refs)
    println(original === refs)

    ___Excercise___(4)
    val months = """Styczeń, Luty, Marzec, Kwiecień, Maj, Czerwiec, 
                   |Lipiec, Sierpień, Wrzesień, Październik, Listopad, 
                   |Grudzień""".trimMargin()
    val monthsList = months.replace("\\s+".toRegex(), "").split(",").toList()

    for (month in 0 until monthsList.size) {
        print("${monthsList[month]} ")
    }
    println()

    for (month in monthsList) {
        if (month.startsWith("L")) print("$month ")
    }
    println()

    monthsList.forEachIndexed { i, month -> if (i % 2 == 0) print("$i.$month ") }
    println()

    val monthIterator = monthsList.iterator()
    while (monthIterator.hasNext()) {
        print("${monthIterator.next()} ")
    }
    println()

    fun rec(month: List<String>) {
        tailrec fun _rec(i: Int) {
            print("${month[i]} ")
            val nextI = i + 1
            if (nextI < month.size) _rec(nextI)
        }
        _rec(0)
    }
    rec(monthsList)
    println()

    println(monthsList.joinToString(separator = " "))

    val monthsString = monthsList
            .filter { !it.startsWith("P") }
            .map { it.replace('e', '_') }
            .map { "$it " }
            .forEach(::print)
    println(monthsString)

    ___Excercise___(5)
    val priceList = mapOf(
            "Banan" to 1.20,
            "Masło" to 4.99,
            "Chleb" to 4.50,
            "Herbata" to 6.00
    )

    val salesPriceList = priceList.mapValues { it.value * 0.80 }
    priceList.forEach { (product, price) ->
        println("%s - %.2f".format(product, price))
    }
    println()
    salesPriceList.forEach { (product, price) ->
        println("%s - %.2f".format(product, price))
    }

    ___Excercise___(6)
    fun printPrice(product: String) {
        val price: Double? = priceList[product]
        println("Cena produktu $product: ${price?.let { "%.2f zł".format(it) } ?: "Brak na stanie"}")
    }
    printPrice("Drożdże")
    printPrice("Masło")

    ___Excercise___(7)
    val randomMonth = monthsList.random().also { print("+ $it: ") }
    fun getMonthType(monthName: String) = when (monthName.toLowerCase(Locale.forLanguageTag("PL"))) {
        in listOf("luty", "lipiec", "sierpień", "wrzesień") -> "Ferie"
        "październik", "listopad", "grudzień", "styczeń" -> "Semestr zimowy"
        "marzec", "kwiecień", "maj", "czerwiec" -> "Semestr letni"
        else -> "Nie ma takiego miesiąca"
    }
    println(getMonthType(randomMonth))

    val randomMonths = Array(Random.nextInt(monthsList.size)) { monthsList.random() }
            .also { println("+ ${it.joinToString()}") }

    //    fun getMonthsTypes(vararg monthNames: String) = monthNames.associateWith { getMonthType(it) }
    fun getMonthsTypes(vararg monthNames: String) = monthNames.associateWith(::getMonthType)
    println(getMonthsTypes(*randomMonths))

    ___Excercise___(8)
    val people = mutableListOf(
            Person("Jan", "Kowalski", Sex.MALE),
            Person("Ewelina", "Nowak", Sex.FEMALE),
            Person("Adam", "Abacki", Sex.MALE)
    )
    people += "Barbara;Babacka;FEMALE".toPerson()
    println(people)

    val (name, surname, sex) = people[1]
    println("$name -> $sex")

    ___Excercise___(9)
    val acc = BankAccount(100.0)
    val acc2 = BankAccount()

    println(acc.balance)
//    acc.balance = 12.0
    acc.withdraw(50.0)
    acc.payInto(50.0)
    acc.withdraw(100.0)
    println(acc.balance)

    ___Excercise___(10)
    var p = Permission()
    println(p)

    p++
    println(p)

    p--
    println(p)

    p += EXECUTE
    p += READ
    println(p)

    p -= EXECUTE
    println(p)

    println(READ in p)
    try {
        p += 123
    } catch (e: IllegalArgumentException) {
        System.err.println(e)
    }
}

enum class Sex {
    MALE, FEMALE
}

data class Person(
        val name: String,
        val surname: String,
        val sex: Sex
) {
    override fun toString(): String = "$name;$surname;$sex"
}

fun String.toPerson(): Person {
    val parts = this.split(";")
    return Person(parts[0], parts[1], Sex.valueOf(parts[2]))
}

class BankAccount(startBalance: Double) {
    private var _balance: Double = startBalance
    val balance: Double
        get() = _balance

    constructor() : this(0.0)

    fun payInto(amount: Double) {
        _balance += amount
    }

    fun withdraw(amount: Double) {
        if (_balance >= amount) _balance -= amount
        else throw UnsupportedOperationException("Not enough founds")
    }
}

class Permission(private var permissions: Int = FORBIDDEN) {
    private val allowed = listOf(FORBIDDEN, EXECUTE, WRITE, READ)

    operator fun inc(): Permission = apply { this += WRITE }

    operator fun dec(): Permission = apply { this -= WRITE }

    operator fun contains(perm: Int): Boolean {
        requireAllowed(perm)
        return permissions and perm == perm
    }

    operator fun plusAssign(perm: Int) {
        requireAllowed(perm)
        permissions = permissions or perm
    }

    operator fun minusAssign(perm: Int) {
        requireAllowed(perm)
        if (perm in this) permissions = permissions xor perm
    }

    private fun requireAllowed(perm: Int) {
        if (perm !in allowed) throw IllegalArgumentException("Permission not allowed")
    }

    override fun toString(): String {
        return permissions.toString()
    }
}