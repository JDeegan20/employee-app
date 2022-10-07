package controllers

import ie.setu.models.Employee

var lastId = 0

internal fun getId(): Int {
    return lastId++
}

class EmployeeAPI {

    private val employees = ArrayList<Employee>()

    //finds all employees
    fun findAll(): List<Employee> {
        return employees
    }

    // finds one employee
    fun findOne(id: Int): Employee? {
        return employees.find { p -> p.employeeID == id }
    }

    //create an employee
    fun create(employee: Employee) {
        employee.employeeID = getId()
        employees.add(employee)
    }

    //delete employee
    fun deleteEmployee(empId: Int){
        employees.remove(findOne(empId))
    }


}