package ie.setu

import ie.setu.models.Employee
import mu.KotlinLogging
import kotlin.math.round

var employees = controllers.EmployeeAPI()

val logger = KotlinLogging.logger {}
fun main(args: Array<String>){
    start()
    logger.info { "Launching Employee App" }
    logger.info { "Getting Ready" }
    logger.info { "Almost There" }
    logger.info { "Done! You're Employee App is launched" }

}

fun roundTwoDecimals(number: Double) = round(number * 100) / 100

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonus= readLine()!!.toDouble()
    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

    employees.create(Employee(firstName, surname, gender, 0, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction))
}
fun menu() : Int{
print("""
         |Employee Menu
         |   1. Add Employee
         |   2. List All Employees
         |   3. Search Employees
         |   4. Print Payslip for Employee
         |   5. Delete Employee
         |  -1. Exit
         |
         |Enter Option : """.trimMargin())
return readLine()!!.toInt()
}


fun start() {
    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> add()
            2 -> list()
            3 -> search()
            4 -> paySlip()
            5 -> deleteEmployee()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun list(){
    employees.findAll()
        .forEach{ println(it) }
}

fun search() {
    logger.info { "Searching......." }
    val employee = getEmployeeById()
    if (employee == null)
        println("No employee found")
    else
        println(employee)
}

internal fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getPayslip())
}

fun dummyData() {
    employees.create(Employee("Joe", "Soap", 'm', 0, 35655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Joan", "Murphy", 'f', 1, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("Mary", "Quinn", 'f', 2, 75685.41, 40.0, 8.5, 4500.0, 0.0))
}



fun deleteEmployee(){
    logger.info {"Delete an Employee"}
    println(list())
    println ("Enter the Employee ID")
    val empId = readLine()!!.toInt()
    employees.deleteEmployee(empId)
    println(list())
}



