package github.guisofiati.introredis.controllers

import github.guisofiati.introredis.entities.Employee
import github.guisofiati.introredis.services.EmployeeService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.Arguments
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class EmployeeController(val employeeService: EmployeeService) {

    @QueryMapping
    fun findAll(): List<Employee> {
        return employeeService.findAll()
    }

    @QueryMapping
    fun findById(@Argument id: String): Employee {
        return employeeService.findById(id)
    }

    @MutationMapping
    fun insert(@Arguments employee: Employee): Employee {
        return employeeService.insert(employee)
    }

    @MutationMapping
    fun update(@Argument id: String, @Arguments employee: Employee): Employee {
        return employeeService.update(id, employee)
    }

    @MutationMapping
    fun delete(@Argument id: String): Boolean {
        employeeService.delete(id)
        return true
    }
}