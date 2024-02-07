package github.guisofiati.introredis.controllers

import github.guisofiati.introredis.entities.Employee
import github.guisofiati.introredis.services.EmployeeService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
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

    @Cacheable(value = ["employees"], key = "#id")
    @QueryMapping
    fun findById(@Argument id: String): Employee {
        return employeeService.findById(id)
    }

    @CachePut(value = ["employees"], key = "#result.id") // necessary result.id instead employee.id
    @MutationMapping
    fun insert(@Arguments employee: Employee): Employee {
        return employeeService.insert(employee)
    }

    @CachePut(value = ["employees"], key = "#id") // update result in cache
    @MutationMapping
    fun update(@Argument id: String, @Arguments employee: Employee): Employee {
        return employeeService.update(id, employee)
    }

    @CacheEvict(value = ["employees"], key = "#id", allEntries = false) // remove result of cache
    @MutationMapping
    fun delete(@Argument id: String): Boolean {
        employeeService.delete(id)
        return true
    }
}