package github.guisofiati.introredis.services

import github.guisofiati.introredis.entities.Employee
import github.guisofiati.introredis.repositories.EmployeeRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.BeanUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.logging.Logger

@Service
class EmployeeService(val employeeRepository: EmployeeRepository) {

    private val log = Logger.getLogger("EmployeeService::class")

    fun findAll(): List<Employee> {
        log.info("[FIND ALL] finding all employees in database...")
        return employeeRepository.findAll()
    }

    fun findById(employeeId: String): Employee {
        val employeeUUID = UUID.fromString(employeeId);
        val employee = employeeRepository.findByIdOrNull(employeeUUID)
        log.info("[FIND ONE] finding employee in database...")
        if (employee == null) {
            throw EntityNotFoundException("Employee id: $employeeId not found")
        }
        return employee
    }

    fun insert(employee: Employee): Employee {
        val newEmployee = Employee()
        newEmployee.name = employee.name
        newEmployee.age = employee.age
        newEmployee.email = employee.email
        newEmployee.phone = employee.phone
        log.info("[INSERT] saving employee in database...")
        return employeeRepository.save(newEmployee)
    }

    fun update(employeeId: String, employee: Employee): Employee {
        val employeeUUID = UUID.fromString(employeeId);
        val employeeEntity = employeeRepository.findByIdOrNull(employeeUUID)
        log.info("[UPDATE] finding employee in database...")
        if (employeeEntity == null) {
            throw EntityNotFoundException("Employee id: $employeeId not found")
        }
        if (employee.name != null) employeeEntity.name = employee.name
        if (employee.age != null) employeeEntity.age = employee.age
        if (employee.email != null) employeeEntity.email = employee.email
        if (employee.phone != null) employeeEntity.phone = employee.phone
        log.info("[UPDATE] updating employee in database...")
        return employeeRepository.save(employeeEntity)
    }

    fun delete(employeeId: String): Boolean {
        val employeeUUID = UUID.fromString(employeeId);
        val employee = employeeRepository.findByIdOrNull(employeeUUID)
        log.info("[DELETING] finding employee in database...")
        if (employee == null) {
            throw EntityNotFoundException("Employee id: $employeeId not found")
        }
        log.info("[DELETING] deleting employee in database...")
        employeeRepository.deleteById(employeeUUID)
        return true
    }
}